package com.zhuazhu.frame.widget;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import conm.zhuazhu.common.utils.ScreenUtils;
import mejust.frame.widget.dialog.FrameDialog;
import mejust.frame.widget.dialog.FrameDialogAction;

/**
 * @author wangpeifeng
 * @date 2018/05/23 10:46
 */
public class PositiveDialogAction extends FrameDialogAction {

    public PositiveDialogAction(CharSequence charSequence, ActionListener actionListener) {
        super(charSequence, actionListener);
    }

    @Override
    public View buildActionView(FrameDialog frameDialog, int index) {
        TextView textView = new TextView(frameDialog.getContext());
        textView.setLayoutParams(
                new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        textView.setTextColor(Color.RED);
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
}
