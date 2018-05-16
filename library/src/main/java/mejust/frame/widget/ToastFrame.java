package mejust.frame.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import mejust.frame.R;

/**
 * 创建时间:2018/1/15  15:46<br/>
 * 创建人: 廖斌<br/>
 * 修改人: 廖斌<br/>
 * 修改时间: 2018/1/15  15:46<br/>
 * 描述:
 */
public class ToastFrame {

    private static Toast toast;

    public static void init(Context context) {
        toast = new Toast(context.getApplicationContext());
        //获取LayoutInflater对象
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        //由layout文件创建一个View对象
        View layout = inflater.inflate(R.layout.layout_toast_frame, null);
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);
        // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    }

    public static void show(String text, int duration) {
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }

    public static void show(String text) {
        toast.setText(text);
        toast.show();
    }
}
