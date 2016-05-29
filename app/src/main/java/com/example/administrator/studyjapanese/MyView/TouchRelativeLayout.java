package com.example.administrator.studyjapanese.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.example.administrator.studyjapanese.MyFragment.HiraganaFragment;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public class TouchRelativeLayout extends RelativeLayout {
    private float witch_old;
    private float witch_new;
      public TouchRelativeLayout(Context context) {
        super(context);
    }

    public TouchRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN :
                witch_new = event.getX();
                witch_old = witch_new;
                break;
            case MotionEvent.ACTION_UP :
                witch_new = event.getX();
                Log.d("action_up","action_up");
                if (witch_new - witch_old < -20.00){
                    //消息发送器（genHiraganaFragment进行通信）
                    EventBus.getDefault().post(true);
                }
                break;
        }

        return true;
    }

}
