package mejust.frame.mvp;

import android.support.v4.app.FragmentActivity;
import io.reactivex.Flowable;
import mejust.frame.net.Callback;
import mejust.frame.net.Optional;

/**
 * 创建时间:2017-12-21 16:17<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 16:17<br/>
 * 描述:
 */

public interface BaseContract {

    interface View {
        /**
         * loading一个View<br/>
         * 可以点击返回键
         */
        void loadingView();

        /**
         * loading一个Dialog<br/>
         * 不可以点击返回键
         */
        void loadingDialog();

        /**
         * 显示Toast的提示信息
         */
        void show(String msg);

        /**
         * 显示dialog提醒信息
         */
        void showToastDialog(CharSequence msg, android.view.View.OnClickListener clickListener);

        /**
         * 关闭loading
         */
        void hidden();

        /**
         * 打开登录页面
         */
        void startLoginActivity();

        /**
         * 页面Activity
         */
        FragmentActivity getViewActivity();

        /**
         * 关闭activity
         */
        void finishView();
    }

    interface Presenter {
        /**
         * 处理请求接口(线程安全,防止内存泄露)
         */
        <T> void execute(Flowable<Optional<T>> flowable, Callback<T> callback);

        void onCreate();

        void onResume();

        void onPause();

        void onDestroy();

        FragmentActivity getViewActivity();
    }
}
