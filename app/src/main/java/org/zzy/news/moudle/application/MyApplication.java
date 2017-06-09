package org.zzy.news.moudle.application;

import android.app.Application;
import android.content.Context;

import com.zhy.autolayout.config.AutoLayoutConifg;

import org.zzy.aframwork.util.LogcatHelper;


/**
 * Created by admin on 16/12/6.
 */

public class MyApplication extends Application {
    public static Context context;
    public static Context currentActivityContext;
    public static MyApplication instance;

    /*****************************************************************************************************/



    @Override
    public void onCreate() {
        super.onCreate();
        LogcatHelper.getInstance(this,"news").start();
        context = this;
        instance = this;
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);

    }


}