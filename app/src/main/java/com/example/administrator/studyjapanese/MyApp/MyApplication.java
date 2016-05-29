package com.example.administrator.studyjapanese.MyApp;

import android.app.Application;

import com.example.administrator.studyjapanese.Utils.SJApi;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //注册微信
        WXAPIFactory.createWXAPI(this, SJApi.AppID,true).registerApp(SJApi.AppID);
    }
}
