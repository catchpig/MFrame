package mejust.frame.mvp;

import android.os.Handler;
import android.support.annotation.NonNull;
import io.reactivex.Flowable;
import mejust.frame.net.Callback;
import mejust.frame.net.Optional;
import mejust.frame.widget.dialog.FrameDialogAction;

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
         * 显示loading view，两种样式dialog or view
         *
         * @param isLoadingDialog true is dialog，otherwise is view
         */
        void showLoading(boolean isLoadingDialog);

        /**
         * 显示Toast的提示信息
         *
         * @param msg msg
         */
        void showToast(String msg);

        /**
         * 显示dialog提醒信息
         */
        void showMessageDialog(String title, CharSequence msg,
                @NonNull FrameDialogAction.ActionListener actionListener);

        /**
         * 关闭loading
         */
        void hideLoading();

        /**
         * 获取Activity中Handler
         */
        Handler getHandler();

        /**
         * 关闭activity
         */
        void finishActivity();
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
    }
}
