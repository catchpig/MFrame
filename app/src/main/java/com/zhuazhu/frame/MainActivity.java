package com.zhuazhu.frame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.zhuazhu.frame.data.HttpHelper;
import com.zhuazhu.frame.di.component.DaggerMainComponent;
import com.zhuazhu.frame.di.module.MainModule;
import javax.inject.Inject;

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
}
