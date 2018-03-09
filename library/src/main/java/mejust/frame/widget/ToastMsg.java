package mejust.frame.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import mejust.frame.R;

/**
 * 创建时间:2018/1/15  15:46<br/>
 * 创建人: 廖斌<br/>
 * 修改人: 廖斌<br/>
 * 修改时间: 2018/1/15  15:46<br/>
 * 描述:
 */
public class ToastMsg {

    private static Toast result;
    private static TextView textView;

    public static void init(Context context) {
        result = new Toast(context);

        //获取LayoutInflater对象
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //由layout文件创建一个View对象
        View layout = inflater.inflate(R.layout.toast_in_style_layout, null);

        //实例化ImageView和TextView对象
        textView = (TextView) layout.findViewById(R.id.tip);

        result.setView(layout);
        result.setDuration(Toast.LENGTH_SHORT);
        //        result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    }

    public static void makeText(String text, int duration) {
        textView.setText(text);
        result.setDuration(duration);
        result.show();
    }

    public static void makeText(String text) {
        textView.setText(text);
        result.show();
    }
}
