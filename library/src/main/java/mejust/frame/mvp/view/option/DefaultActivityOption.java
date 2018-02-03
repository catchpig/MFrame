package mejust.frame.mvp.view.option;

import android.app.Activity;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;

import mejust.frame.annotation.options.StatusBarOption;
import mejust.frame.widget.title.TitleBarOptions;

/**
 * 创建时间:2017/12/24 14:19<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/24 14:19<br/>
 * 描述:默认的登录回调
 */

public interface DefaultActivityOption {
    /**
     * 登录
     * @param activity
     */
    void login(Activity activity);

    /**
     * 状态栏
     * @return
     */
    StatusBarOption statusBarOption();

    /**
     * 默认的标题栏参数
     * @return
     */
    @NonNull
    TitleBarOptions titleBarOption();
}
