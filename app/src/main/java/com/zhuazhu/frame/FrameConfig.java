package com.zhuazhu.frame;

import mejust.frame.app.AppConfig;

/**
 * @author : Beaven
 * @date : 2017-12-20 12:58
 */

public class FrameConfig extends AppConfig {

    public static void init() {
        DEBUG = BuildConfig.DEBUG;
    }
}
