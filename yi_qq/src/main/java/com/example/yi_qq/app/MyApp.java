package com.example.yi_qq.app;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class MyApp extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
      //  CrashHandler.getInstance().init(this);

    }
}
