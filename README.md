# MFrame
  [![Release](https://jitpack.io/v/zhuazhu/MFrame.svg)](https://jitpack.io/#zhuazhu/MFrame)

  ---
  [在线文档地址](https://jitpack.io/com/github/zhuazhu/MFrame/0.1.4/javadoc)
  ```
  如果在线文档不是最新的,请用这个地址访问:https://jitpack.io/com/github/zhuazhu/MFrame/{最新的版本号}/javadoc
  ```
## 使用Gradle方式
   在Project的build.gradle中添加:
   ```
   allprojects {
    	repositories {
    		maven { url 'https://jitpack.io' }
    	}
    }
   ```

   添加依赖:

   ```
   implementation 'com.android.support:appcompat-v7:26.1.0'
   implementation 'com.android.support:recyclerview-v7:26.1.0'
   implementation 'com.github.zhuazhu:MFrame:{last_version}'
   annotationProcessor 'com.google.dagger:dagger-compiler:2.13'
   annotationProcessor 'com.github.bumptech.glide:compiler:4.4.0'
   annotationProcessor 'com.jakewharton:butterknife-compiler:8.5.0'
   ```

## 项目基类库

 集成了常用的第三方库，使用MVP模式的搭建的基类库，便于项目的开发。集成的库列表：

     //刷新控件
     api 'com.scwang.smartrefresh:SmartRefreshLayout:1.0.3'
     //工具包
     api ('com.github.zhuazhu:Common:1.0.0',{
         exclude group: 'com.android.support'
     })
     api 'com.google.code.gson:gson:2.8.2'
     api 'com.google.dagger:dagger:2.13'
     annotationProcessor 'com.google.dagger:dagger-compiler:2.13'
     api 'com.squareup.okhttp3:okhttp:3.9.1'
     api 'com.squareup.okhttp3:logging-interceptor:3.9.1'
     api 'com.squareup.retrofit2:retrofit:2.3.0'
     api 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
     api 'com.squareup.retrofit2:converter-gson:2.3.0'
     api "io.reactivex.rxjava2:rxjava:2.1.7"
     api 'io.reactivex.rxjava2:rxandroid:2.0.1'
     //图片异步加载框架
     api ('com.github.bumptech.glide:glide:4.4.0',{
         exclude group:"com.android.support"
     })
     annotationProcessor 'com.github.bumptech.glide:compiler:4.4.0'
     api ("com.github.bumptech.glide:okhttp3-integration:4.4.0", {
         transitive = false
     })
     //图片放大缩小控件
     api 'com.github.chrisbanes:PhotoView:2.1.3'
     api 'com.jakewharton.timber:timber:4.6.0'
     api 'com.jakewharton:butterknife:8.5.0'
     //动画
     api 'com.wang.avi:library:2.1.3'
     //状态栏
     api 'com.gyf.barlibrary:barlibrary:2.3.0'

## 提供功能

    1. Activity,Fragment,Presenter基类
    2. Adapter基类，开启dataBinding，提供HeadView、FootView添加
    3. 网络请求结果转换封装，自动处理异常
    4. 列表刷新加载封装
    5. 日志工具库
    6. APP更新库

## 接入步骤
1.在Application中添加
```
    private static AppComponent appComponent;
    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(Utils.getApp()))
                    .netModule(new NetModule())
                    .build();
        }
        return appComponent;
    }
```