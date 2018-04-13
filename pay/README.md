1. 权限声明

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
<uses-permission android:name="org.simalliance.openmobileapi.SMARTCARD" />
<uses-permission android:name="android.permission.NFC" />
<uses-feature android:name="android.hardware.nfc.hce"/>
```

2. Activity注册

```xml
支付宝注册
<activity
    android:name="com.alipay.sdk.app.H5PayActivity"
    android:configChanges="orientation|keyboardHidden|navigation|screenSize"
    android:exported="false"
    android:screenOrientation="behind"
    android:windowSoftInputMode="adjustResize|stateHidden" >
</activity>
 <activity
    android:name="com.alipay.sdk.app.H5AuthActivity"
    android:configChanges="orientation|keyboardHidden|navigation"
    android:exported="false"
    android:screenOrientation="behind"
    android:windowSoftInputMode="adjustResize|stateHidden" >
</activity>
微信注册
<!-- 微信支付 -->
<activity
   android:name="mejust.frame.pay.wechat.WXPayEntryActivity"
   android:configChanges="orientation|keyboardHidden|navigation|screenSize"
   android:launchMode="singleTop"
   android:theme="@android:style/Theme.Translucent.NoTitleBar" />
<activity-alias
   android:name=".wxapi.WXPayEntryActivity"
   android:exported="true"
   android:targetActivity="com.jpay.weixin.WXPayEntryActivity" />
        <!-- 微信支付 end -->
```

