package com.zhuazhu.frame.mvp.activity;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import butterknife.OnClick;
import com.zhuazhu.frame.R;
import mejust.frame.annotation.LayoutId;
import mejust.frame.annotation.TitleBarConfig;
import mejust.frame.annotation.TitleBarMenu;
import mejust.frame.annotation.TitleBarMenuLocation;
import mejust.frame.mvp.view.BaseActivity;

/**
 * @author wangpeifeng
 * @date 2018/04/18 9:50
 */
@LayoutId(R.layout.activity_title_bar)
@TitleBarConfig(textValue = "测试", textColor = R.color.colorAccent)
public class TitleBarActivity extends BaseActivity {

    private static final String TAG = "TitleBarActivity";

    private Handler handler = new Handler();

    @TitleBarMenu(location = TitleBarMenuLocation.rightSecondMenu, text = "退出")
    public void clickToast(View view) {
        Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
    }

    @TitleBarMenu(location = TitleBarMenuLocation.rightFirstMenu, text = "登录")
    public void clickSecond(View view) {
        Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
    }

    @OnClick({ R.id.bt_loading_dialog, R.id.bt_loading_view, R.id.bt_message_dialog })
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.bt_loading_dialog:
                showLoading(true);
                handler.postDelayed(this::hideLoading, 5000);
                break;
            case R.id.bt_loading_view:
                showLoading(false);
                handler.postDelayed(this::hideLoading, 5000);
                break;
            case R.id.bt_message_dialog:
                showMessageDialog("",
                        "随着 Web 技术的不断发展，前端开发框架层出不穷，各有千秋，今天小编为大家奉上前端 UI 框架的开源项目，希望大家能够喜欢！",
                        (dialog, index) -> dialog.dismiss());
                break;
            default:
                break;
        }
    }
}
