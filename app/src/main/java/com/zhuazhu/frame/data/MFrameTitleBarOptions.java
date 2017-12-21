package com.zhuazhu.frame.data;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.zhuazhu.frame.R;
import mejust.frame.widget.TitleBarOptions;

/**
 * 创建时间:2017-12-21 16:51<br/>
 * 创建人: 王培峰<br/>
 * 修改人: 王培峰<br/>
 * 修改时间: 2017-12-21 16:51<br/>
 * 描述:
 */

public class MFrameTitleBarOptions extends TitleBarOptions {

    public MFrameTitleBarOptions(final Activity activity) {
        this.setTitleStringColor(Color.WHITE);
        this.setBackgroundColor(R.color.colorPrimary);
        this.setClickBackground(R.drawable.selector_title);
        this.setTitleStringSize(18);
        this.setTextLeftSize(14);
        this.setTextRightSize(14);
        this.setImgLeftMainId(R.mipmap.ic_launcher);
        this.setTextLeft("返回");
        this.setLeftMainListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
}
