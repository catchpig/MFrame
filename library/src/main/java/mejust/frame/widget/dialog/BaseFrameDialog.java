package mejust.frame.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import mejust.frame.R;

/**
 * @author wangpeifeng
 * @date 2018/05/15 17:23
 */
public abstract class BaseFrameDialog extends Dialog {

    private LinearLayout layoutRoot;

    public BaseFrameDialog(@NonNull Context context) {
        this(context, R.style.FrameDialog);
    }

    public BaseFrameDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
        setContentView(createContentView());
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams wmlp = window.getAttributes();
        wmlp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wmlp.gravity = Gravity.CENTER;
        window.setAttributes(wmlp);
    }

    private View createContentView() {
        layoutRoot = new LinearLayout(getContext());
        layoutRoot.setOrientation(LinearLayout.VERTICAL);
        layoutRoot.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        layoutRoot.setBackground(
                ContextCompat.getDrawable(getContext(), R.drawable.shape_dialog_border_frame));
        layoutRoot.addView(createTitle());
        layoutRoot.addView(createContent());
        return layoutRoot;
    }

    protected abstract View createContent();

    private View createTitle() {
        TextView textView = new TextView(getContext());
        return textView;
    }
}
