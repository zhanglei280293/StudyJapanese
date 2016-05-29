package com.example.administrator.studyjapanese.Utils;

import android.content.Context;
import android.os.Bundle;

import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class ShareUtils {


    private static IWXAPI wxapi;
    public static final boolean isWxFriend = true;
    public static final boolean isWxQuan = false;


    //微信分享的方法
    public static void wxTextShare(Context context ,String text , Boolean b){

        //注册微信
        if (wxapi == null){
            wxapi = WXAPIFactory.createWXAPI(context, SJApi.AppID, true);
            wxapi.registerApp(SJApi.AppID);
        }

        //初始化一个WXTextObject对象，用来填写分享到微信的文本内容
        WXTextObject wto = new WXTextObject();
        wto.text = text;
        //初始化一个WXMediaMessage
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = wto;
        msg.description = text;
        //初始化一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "text";
        req.message = msg;
        /**
         * WXSceneSession  会话列表
         * WXSceneTimeline  朋友圈
         */
        req.scene = b ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        //向微信发送请求
        wxapi.sendReq(req);
    }

    //qq分享的方法
    public static Bundle getToQQMessageBundle(){
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "要分享的摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "http://www.qq.com/news/1.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "测试应用222222");
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        return params;
    }

}
