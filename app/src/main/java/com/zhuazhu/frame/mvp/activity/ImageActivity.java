package com.zhuazhu.frame.mvp.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zhuazhu.frame.R;
import com.zhuazhu.frame.databinding.ActivityImageBinding;

import java.util.ArrayList;
import java.util.List;

import mejust.frame.widget.dialog.ImageDialog;
import mejust.frame.image.ImageUtils;

/**
 * 创建时间:2017/12/20 16:28<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/20 16:28<br/>
 * 描述:
 */

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{
    private String url = "http://image.tianjimedia.com/uploadImages/2015/285/24/586K2UOWHG9D.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image);
        ActivityImageBinding binding = DataBindingUtil.setContentView(this,R.layout
                .activity_image);
        ImageView img1 = findViewById(R.id.image1);
        ImageView img2 = findViewById(R.id.image2);
        ImageView img3 = findViewById(R.id.image3);
        binding.setImageUrl(url);
//        ImageUtils.show(img1,url);
        ImageUtils.showCircle(img2,url);
        ImageUtils.showRound(img3,url,10);
        LeakThread leakThread = new LeakThread();
        leakThread.start();
    }
    public static class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(6 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image1:
                new ImageDialog(this,url).show();
                break;
            case R.id.image2:
                new ImageDialog(this,url,url).show();
                break;
            case R.id.image3:
                List<String> list = new ArrayList<>();
                list.add(url);
                list.add(url);
                list.add(url);
                new ImageDialog(this,list).show();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
