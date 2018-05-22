package mejust.frame.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import conm.zhuazhu.common.utils.ScreenUtils;
import mejust.frame.R;

/**
 * @author wangpeifeng
 * @date 2018/05/21 17:01
 */
public class FrameDialog extends Dialog {

    private Context baseContext;

    public FrameDialog(@NonNull Context context) {
        this(context, R.style.FrameDialog);
    }

    public FrameDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.baseContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        int padding = getContext().getResources()
                .getDimensionPixelOffset(R.dimen.activity_horizontal_margin);
        int width = ScreenUtils.getScreenWidth(baseContext) - (padding * 2);
        WindowManager.LayoutParams wmlp = window.getAttributes();
        wmlp.width = width;
        wmlp.gravity = Gravity.CENTER;
        window.setAttributes(wmlp);
    }

    public static class MessageDialogBuilderBase
            extends BaseFrameDialogBuilder<MessageDialogBuilderBase> {

        protected CharSequence charSequence;

        public MessageDialogBuilderBase(Context context) {
            super(context);
        }

        public MessageDialogBuilderBase setMessage(CharSequence charSequence) {
            this.charSequence = charSequence;
            return this;
        }

        @Override
        protected void createContent(FrameDialog frameDialog, LinearLayout rootLayout,
                Context dialogContext) {
            rootLayout.setBackgroundColor(Color.WHITE);
            if (charSequence != null && charSequence.length() > 0) {
                TextView textView = new TextView(dialogContext);
                textView.setText(charSequence);
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(0, ScreenUtils.dpToPxInt(16), 0, ScreenUtils.dpToPxInt(16));
                rootLayout.addView(textView,
                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }
    }
}
