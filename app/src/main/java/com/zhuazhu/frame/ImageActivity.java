package com.zhuazhu.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import mejust.frame.dialog.ImageDialog;
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
        setContentView(R.layout.activity_image);
        ImageView img1 = findViewById(R.id.image1);
        ImageView img2 = findViewById(R.id.image2);
        ImageView img3 = findViewById(R.id.image3);

        ImageUtils.show(img1,url);
        ImageUtils.showCircle(img2,url);
        ImageUtils.showRound(img3,url,10);
    }

    @Override
    public void onClick(View v) {
        new ImageDialog(this,url).show();
    }
}
