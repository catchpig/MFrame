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
import mejust.frame.image.GlideLoadManager;
import mejust.frame.image.IImageLoadManager;
import mejust.frame.image.ImageLoadConfig;
import mejust.frame.widget.dialog.ImageDialog;

/**
 * 创建时间:2017/12/20 16:28<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/12/20 16:28<br/>
 * 描述:
 */

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {

    private String url = "http://pic.sc.chinaz.com/files/pic/pic9/201805/wpic863.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityImageBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_image);
        ImageView img1 = findViewById(R.id.image1);
        ImageView img2 = findViewById(R.id.image2);
        ImageView img3 = findViewById(R.id.image3);
        IImageLoadManager imageLoad = new GlideLoadManager();
        imageLoad.loadNet(img1, url);
        imageLoad.loadNet(img2, url, new ImageLoadConfig(true));
        imageLoad.loadNet(img3, url, new ImageLoadConfig(30));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image1:
                new ImageDialog(this, url).show();
                break;
            case R.id.image2:
                new ImageDialog(this, url, url).show();
                break;
            case R.id.image3:
                List<String> list = new ArrayList<>();
                list.add(url);
                list.add(url);
                list.add(url);
                new ImageDialog(this, list).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
