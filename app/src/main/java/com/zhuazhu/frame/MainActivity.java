package com.zhuazhu.frame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.zhuazhu.frame.data.HttpHelper;
import com.zhuazhu.frame.di.component.DaggerMainComponent;
import com.zhuazhu.frame.di.module.MainModule;
import javax.inject.Inject;
import mejust.frame.log.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainComponent.builder()
                .appComponent(FrameApplication.getAppComponent())
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }

    @Inject
    HttpHelper httpHelper;

    public void click(View view) {
        System.out.println(FrameConfig.DEBUG);
        Logger.d("Hello");
        Logger.i("Hello");
        Logger.e("Hello");
        Logger.w("Hello");
        Logger.d("这是tag", "Hello");
        Logger.i("这是tag", "Hello");
        Logger.e("这是tag", "Hello");
        Logger.w("这是tag", "Hello");
    }
}
