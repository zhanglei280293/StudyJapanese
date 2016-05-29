package com.example.administrator.studyjapanese.MyActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.administrator.studyjapanese.MyFragment.HiraganaFragment;
import com.example.administrator.studyjapanese.R;
import com.example.administrator.studyjapanese.Utils.ShareUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class MainActivity extends AppCompatActivity implements HiraganaFragment.OnShareClickListener, IUiListener {

    private RadioGroup group;
    private FragmentManager manager;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private RadioButton wx_friend;
    private RadioButton wx_quan;
    private RadioGroup rg_share;
    private DrawerLayout dl;
    private RelativeLayout rl;
    private HiraganaFragment fragment;
    private Tencent tencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initClickListener();
    }

    private void initClickListener() {

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //开启事务
                FragmentTransaction action = manager.beginTransaction();
                HiraganaFragment fragment = new HiraganaFragment();
                fragment.setOnShareListener(MainActivity.this);
                switch (checkedId){
                    case R.id.ping :
                        Bundle bundle = new Bundle();
                        //根据标签显示使fragment显示不同的内容
                        bundle.putInt("tag",1);
                        fragment.setArguments(bundle);
                        action.replace(R.id.relative,fragment);
                        break;
                    case R.id.pian :
                        Bundle bundle1 = new Bundle();
                        //根据标签显示使fragment显示不同的内容
                        bundle1.putInt("tag",2);
                        fragment.setArguments(bundle1);
                        action.replace(R.id.relative,fragment);
                        break;
                }

                action.commit();
            }
        });

        rg_share.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.wx_friend :
                        //分享到微信好友
                        ShareUtils.wxTextShare(MainActivity.this,"会话列表", ShareUtils.isWxFriend);
                        break;
                    case R.id.wx_quan :
                        //分享到微信朋友圈
                        ShareUtils.wxTextShare(MainActivity.this,"朋友圈", ShareUtils.isWxQuan);
                        break;
                    case R.id.qq_Session :
                        //分享到qq会话列表
                        tencent.shareToQQ(MainActivity.this,
                                ShareUtils.getToQQMessageBundle(),MainActivity.this);
                        break;
                    case R.id.qq_zone :
                        //分享到qq空间

                        break;
                }
            }
        });

    }

    private void initView() {
        //初始化腾讯接口的实例
        tencent = Tencent.createInstance("",getApplicationContext());

        actionBar = getSupportActionBar();
        actionBar.hide();

        group = (RadioGroup) findViewById(R.id.radio_gp);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_layout);
        wx_friend = (RadioButton) findViewById(R.id.wx_friend);
        wx_quan = (RadioButton) findViewById(R.id.wx_quan);
        rg_share = (RadioGroup) findViewById(R.id.rg_share);
        rl = (RelativeLayout) findViewById(R.id.rl_drawer);
        //获取Fragment管理者对象
        manager = getSupportFragmentManager();
        //默认选中平假名按钮
        group.check(R.id.ping);
        //进入app先让其加载平假名界面
        FragmentTransaction transaction = manager.beginTransaction();
        fragment = new HiraganaFragment();
        fragment.setOnShareListener(this);
        Bundle bundle = new Bundle();
        //根据标签显示使fragment显示不同的内容
        bundle.putInt("tag",1);
        fragment.setArguments(bundle);
        transaction.add(R.id.relative, fragment);
        transaction.commit();
    }

    /**
     * 分享按钮的回调
     */
    @Override
    public void OnShareClick() {
        drawerLayout.openDrawer(rl);
    }

    @Override
    public void onComplete(Object o) {

    }

    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {

    }
}
