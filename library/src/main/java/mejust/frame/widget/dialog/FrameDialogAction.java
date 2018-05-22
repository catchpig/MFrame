package mejust.frame.widget.dialog;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import conm.zhuazhu.common.utils.ScreenUtils;

/**
 * @author wangpeifeng
 * @date 2018/05/21 17:25
 */
public class FrameDialogAction {

    protected CharSequence charSequence;

    protected ActionListener actionListener;

    public FrameDialogAction(CharSequence charSequence, ActionListener actionListener) {
        this.charSequence = charSequence;
        this.actionListener = actionListener;
    }

    public View buildActionView(final FrameDialog frameDialog, final int index) {
        TextView textView = new TextView(frameDialog.getContext());
        textView.setLayoutParams(
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(14);
        textView.setGravity(Gravity.CENTER);
        textView.setText(charSequence);
        textView.setPadding(0, ScreenUtils.dpToPxInt(16), 0, ScreenUtils.dpToPxInt(16));
        textView.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onClick(frameDialog, index);
            }
        });
        return textView;
    }

    public interface ActionListener {

        void onClick(FrameDialog dialog, int index);
    }
}
