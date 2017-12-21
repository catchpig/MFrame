package com.zhuazhu.frame.data;

import android.content.Context;
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

    public MFrameTitleBarOptions(Context context) {
        this.setTitleStringColor(context.getResources().getColor(R.color.colorPrimary));
        this.setTitleStringSize(18);
        this.setTextLeftSize(14);
        this.setTextRightSize(14);
        this.setImgLeftMinorId(R.mipmap.ic_launcher);
        this.setTextLeft("返回");
    }
}
