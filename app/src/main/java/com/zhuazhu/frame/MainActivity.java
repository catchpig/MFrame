package com.zhuazhu.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.zhuazhu.frame.data.HttpHelper;
import com.zhuazhu.frame.di.component.DaggerMainComponent;
import com.zhuazhu.frame.di.module.MainModule;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.image:
                intent.setClass(this,ImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
