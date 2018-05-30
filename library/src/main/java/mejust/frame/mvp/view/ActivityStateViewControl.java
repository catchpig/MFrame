package mejust.frame.mvp.view;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import mejust.frame.R;
import mejust.frame.widget.dialog.FrameDialog;
import mejust.frame.widget.dialog.FrameDialogAction;

/**
 * @author wangpeifeng
 * @date 2018/05/24 10:08
 */
public class ActivityStateViewControl {

    private BaseActivity baseActivity;
    private FrameLayout layoutRoot;

    private Dialog loadingDialog;
    private Dialog messageDialog;
    private View loadingView;

    public ActivityStateViewControl(BaseActivity baseActivity, FrameLayout layoutRoot) {
        this.baseActivity = baseActivity;
        this.layoutRoot = layoutRoot;
    }

    public void showLoading(boolean isDialog) {
        if (isDialog) {
            if (loadingDialog != null) {
                return;
            }
            loadingDialog = new FrameDialog.LoadingDialogBuilder(baseActivity).create();
            loadingDialog.show();
        } else {
            if (loadingView != null) {
                return;
            }
            loadingView = baseActivity.getLayoutInflater().inflate(R.layout.view_loading, null);
            layoutRoot.addView(loadingView);
        }
    }

    public void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
            loadingDialog = null;
        }
        if (loadingView != null) {
            layoutRoot.removeView(loadingView);
            loadingView = null;
        }
    }

    public void showDetermineMessageDialog(String title, CharSequence msg,
            @NonNull FrameDialogAction.ActionListener actionListener) {
        messageDialog = new FrameDialog.MessageDialogBuilder(baseActivity).setTitle(title)
                .setMessage(msg)
                .setCanceledOnTouchOutside(false)
                .setCancelable(false)
                .addAction(new FrameDialogAction(baseActivity.getString(R.string.determine_frame),
                        actionListener))
                .create();
        messageDialog.show();
    }

    public void destroyControl() {
        if (messageDialog != null) {
            messageDialog.cancel();
            messageDialog = null;
        }
    }
}
