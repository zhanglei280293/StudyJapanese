package com.example.administrator.studyjapanese.MyFragment;


import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.studyjapanese.MyView.TouchRelativeLayout;
import com.example.administrator.studyjapanese.R;
import com.example.administrator.studyjapanese.Utils.Rt_Number;

import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class HiraganaFragment extends Fragment implements View.OnClickListener{

    private TextView text_hiragana;
    private TextView text_tuyin;
    private String[] data;
    private String[] luomazi;
    private TouchRelativeLayout touch;
    private Button bt_hiragana;
    private View v;
    private PopupWindow pop;
    private ImageView image_share;
    private DrawerLayout dl;
    private RelativeLayout rl_drawer;

    public HiraganaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hiragana, container, false);
        initview(view);
        initdata();
        return view;
    }

    private void initdata() {
        Bundle bundle = getArguments();
        int tag = bundle.getInt("tag", 0);
        switch (tag){
            case 0 :
                break;
            case 1:
                //将数据变成平假名
                data = getResources().getStringArray(R.array.hiragana);
                touch.setBackgroundResource(R.mipmap.yeye);
                break;
            case 2:
                //数据变成片假名
                data = getResources().getStringArray(R.array.katakana);
                touch.setBackgroundResource(R.mipmap.zuozhu);
                text_hiragana.setTextColor(Color.BLUE);
                break;
        }
        //通过工具类获取一个0到45的随机数
        int index = Rt_Number.getIndex();
        luomazi = getResources().getStringArray(R.array.luomazi);
        //设置要显示的内容
        text_hiragana.setText(data[index]);
        text_tuyin.setText(luomazi[index]);
    }

    private void initview(View view) {

        v = LayoutInflater.from(getActivity()).inflate(R.layout.iamge,null);
        bt_hiragana = (Button) view.findViewById(R.id.bt_hiragana);
        touch = (TouchRelativeLayout) view.findViewById(R.id.touch);
        text_hiragana = (TextView) view.findViewById(R.id.hira_text);
        text_tuyin = (TextView) view.findViewById(R.id.text_duyin);
        image_share = (ImageView) view.findViewById(R.id.image_share);
        rl_drawer = (RelativeLayout) view.findViewById(R.id.rl_drawer);
        //注册EventBus
        EventBus.getDefault().register(this);
        //设置控件的透明度
        bt_hiragana.getBackground().setAlpha(90);
        image_share.getBackground().setAlpha(90);
        //设置点击事件
        bt_hiragana.setOnClickListener(this);
        text_hiragana.setOnClickListener(this);
        image_share.setOnClickListener(this);
        //创建一个popupwindow
        pop = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,false);
        //设置参数
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
    }

    //接收数据
    public void onEvent(Boolean b){
        if (b){
            //通过工具类获取一个0到45的随机数
            int index = Rt_Number.getIndex();
            //设置要显示的内容
            text_hiragana.setText(data[index]);
            text_tuyin.setText(luomazi[index]);
            text_tuyin.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_hiragana :
                //弹出一个popwindow，显示五十音标图
                if (pop.isShowing()){
                    pop.dismiss();
                }else{
                    //设置popupwindow的位置为居中
                    pop.showAtLocation(v,Gravity.CENTER,0,0);
                }
                break;
            case R.id.hira_text :
                text_tuyin.setVisibility(View.VISIBLE);
                break;
            case R.id.image_share :
                //弹出分享按钮
               if (listener != null){
                   listener.OnShareClick();
               }
                break;
        }
    }


    private OnShareClickListener listener;
    //分享按钮点击时间的回调接口
    public interface OnShareClickListener{
        void OnShareClick();
    }

    public void setOnShareListener(OnShareClickListener listener){
        this.listener = listener;
    }

}
