package mejust.frame.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import mejust.frame.R;

/**
 * 创建时间: 2018/01/20 14:06<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2018/01/20 14:06<br/>
 * 描述:
 */
public class ToastDialog extends Dialog implements View.OnClickListener {

    private CharSequence content;
    private View.OnClickListener determineListener;
    private View.OnClickListener cancelListener;
    private boolean determineShow;
    private boolean cancelShow;

    public ToastDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.FrameDialog);
        this.content = builder.content;
        this.determineListener = builder.determineListener;
        this.cancelListener = builder.cancelListener;
        this.determineShow = builder.determineShow;
        this.cancelShow = builder.cancelShow;
        setCancelable(builder.cancelable);
    }

    public static class Builder {
        private CharSequence content;
        private View.OnClickListener determineListener;
        private View.OnClickListener cancelListener;
        private boolean cancelable;
        private boolean determineShow;
        private boolean cancelShow;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder content(CharSequence content) {
            this.content = content;
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setDetermine(boolean determineShow, View.OnClickListener determineListener) {
            this.determineShow = determineShow;
            this.determineListener = determineListener;
            return this;
        }

        public Builder setCancel(boolean cancelShow, View.OnClickListener cancelListener) {
            this.cancelShow = cancelShow;
            this.cancelListener = cancelListener;
            return this;
        }

        public ToastDialog build() {
            return new ToastDialog(context, this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        TextView content = findViewById(R.id.text_content);
        if (TextUtils.isEmpty(this.content)) {
            content.setText("");
        } else {
            content.setText(this.content);
        }
        TextView submit = findViewById(R.id.text_submit);
        TextView cancel = findViewById(R.id.text_cancel);
        submit.setVisibility(determineShow ? View.VISIBLE : View.GONE);
        cancel.setVisibility(cancelShow ? View.VISIBLE : View.GONE);
        findViewById(R.id.view_button_divider).setVisibility(
                determineShow && cancelShow ? View.VISIBLE : View.GONE);
        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        cancel();
        if (id == R.id.text_submit) {
            if (determineListener != null) {
                determineListener.onClick(v);
            }
        } else if (id == R.id.text_cancel) {
            if (cancelListener != null) {
                cancelListener.onClick(v);
            }
        }
    }
}
