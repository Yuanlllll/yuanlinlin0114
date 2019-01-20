package com.example.dell.yuanlinlin0114;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    private static CrashHandler mInstance;

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private Context mContext;
    public static synchronized CrashHandler getInstance() {
        if (null == mInstance) {
            mInstance = new CrashHandler();
        }
        return mInstance;
    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为系统默认的
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //自己处理
        if (!handleException(e) && mDefaultHandler != null) {

            mDefaultHandler.uncaughtException(t, e);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException es) {
                Log.e(TAG, "错误 : ", es);
            }
        }
    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //添加自定义信息
        addCustomInfo();
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                Toast.makeText(mContext, "程序报错", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        return true;
    }

    private void addCustomInfo() {
        Log.i(TAG, "addCustomInfo: 程序出错了");
    }
}

