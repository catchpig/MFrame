package mejust.frame.mvp;

import android.content.Context;

/**
 * 创建时间:2017-12-21 16:17<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 16:17<br/>
 * 描述:
 */

public interface BaseContract {

    interface View {

        void showLoading();

        void showContent();

        Context getContext();
    }

    interface Presenter {

        void onCreate();

        void onResume();

        void onPause();

        void onDestroy();

        Context getContext();
    }
}
