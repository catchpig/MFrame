package mejust.frame.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import conm.zhuazhu.common.utils.Utils;
import mejust.frame.R;
import mejust.frame.data.annotation.Title;
import mejust.frame.mvp.view.BaseActivity;
import mejust.frame.widget.NetWorkControlView;
import mejust.frame.widget.title.TitleBarConfig;

/**
 * 处理标题栏和状态栏
 * @author zhuazhu
 **/
public class BarLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {
    private TitleBarConfig mTitleBarConfig;

    public BarLifecycleCallbacksImpl(TitleBarConfig titleBarConfig) {
        mTitleBarConfig = titleBarConfig;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Title title = activity.getClass().getAnnotation(Title.class);
        if (activity instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) activity;
            //标题栏
            FrameLayout titleBar = baseActivity.findViewById(R.id.title_bar);
            //返回按钮
            ImageView back = baseActivity.findViewById(R.id.back);
            //标题
            TextView titleTxt = baseActivity.findViewById(R.id.title);
            //右边按钮
            TextView rightFirstText = baseActivity.findViewById(R.id.rightFirstText);
            TextView rightSecondText = baseActivity.findViewById(R.id.rightSecondText);
            ImageView rightFirstDrawable = baseActivity.findViewById(R.id.rightFirstDrawable);
            ImageView rightSecondDrawable = baseActivity.findViewById(R.id.rightSecondDrawable);

            if(title!=null&&mTitleBarConfig!=null){
                int textColor = mTitleBarConfig.getTitleTextColor();
                titleBar.setVisibility(View.VISIBLE);
                titleBar.setBackgroundColor(ContextCompat.getColor(activity,mTitleBarConfig.getBackgroundColor()));

                back.setImageResource(mTitleBarConfig.getBackDrawable());
                back.setOnClickListener(v -> {
                    //返回上一级页面
                    Utils.getTopActivity().finish();
                });

                titleTxt.setTextSize(mTitleBarConfig.getTitleTextSize());
                titleTxt.setTextColor(ContextCompat.getColor(activity,textColor));
                titleTxt.setText(title.value());


                rightFirstText.setTextColor(ContextCompat.getColor(activity,textColor));
                if(!TextUtils.isEmpty(title.rightFirstText())){
                    rightFirstText.setVisibility(View.VISIBLE);
                    rightFirstText.setText(title.rightFirstText());
                }

                rightFirstText.setTextSize(mTitleBarConfig.getMenuTextSize());
                if(!TextUtils.isEmpty(title.rightSecondText())){
                    rightSecondText.setVisibility(View.VISIBLE);
                    rightSecondText.setText(title.rightSecondText());
                }

                rightSecondText.setTextColor(ContextCompat.getColor(activity,textColor));
                if(title.rightFirstDrawable()!=-1){
                    rightFirstDrawable.setVisibility(View.VISIBLE);
                    rightFirstDrawable.setImageResource(title.rightFirstDrawable());
                }

                rightSecondText.setTextSize(mTitleBarConfig.getMenuTextSize());
                if(title.rightSecondDrawable()!=-1){
                    rightSecondDrawable.setVisibility(View.VISIBLE);
                    rightSecondDrawable.setImageResource(title.rightSecondDrawable());
                }
            }
            baseActivity.setStateBarColor(mTitleBarConfig.getBackgroundColor());
        }

    }


    @Override
    public void onActivityStarted(Activity activity) {
        if(activity instanceof BaseActivity){
            NetWorkControlView netWorkControlView = activity.findViewById(R.id.network_control_view);
            netWorkControlView.registerNetChangeListener();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity instanceof BaseActivity){
            NetWorkControlView netWorkControlView = activity.findViewById(R.id.network_control_view);
            netWorkControlView.unRegisterNetChangeListener();
        }

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
