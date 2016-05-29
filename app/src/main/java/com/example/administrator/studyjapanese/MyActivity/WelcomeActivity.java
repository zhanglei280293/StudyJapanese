package com.example.administrator.studyjapanese.MyActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.administrator.studyjapanese.MyAdapter.VIewPagerAdapter;
import com.example.administrator.studyjapanese.R;
import com.example.administrator.studyjapanese.Utils.PackageUtils;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private RadioGroup welcome_radio;
    private VIewPagerAdapter adapter;
    private ArrayList<ImageView> list;
    private Button bt_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        initData();
        initListener();

    }

    private void initListener() {

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //在此设置raidobutton的选中
                switch (position){
                    case 0:
                        welcome_radio.check(R.id.two);
                        bt_welcome.setVisibility(View.GONE);
                        break;
                    case 1:
                        bt_welcome.setVisibility(View.VISIBLE);
                        welcome_radio.check(R.id.three);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        welcome_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.two:
                        viewpager.setCurrentItem(0);
                        bt_welcome.setVisibility(View.GONE);
                        break;
                    case R.id.three:
                        viewpager.setCurrentItem(1);
                        bt_welcome.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        bt_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                WelcomeActivity.this.finish();
            }
        });

    }

    private void initData() {
        //获取当前的版本号
        String new_version = PackageUtils.getPackageVersion(this);
        //存入
        SharedPreferences pf = getSharedPreferences("version", MODE_PRIVATE);
        SharedPreferences.Editor edit = pf.edit();
        edit.putString("version",new_version);
        edit.commit();
        //创建欢迎页的数据
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.mipmap.welcome1);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.mipmap.welcome2);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        //添加数据到数据源
        list.add(imageView2);
        list.add(imageView3);
        //刷新适配器
        adapter.notifyDataSetChanged();
    }

    private void initView() {

        //取消ActionBar
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        bt_welcome = (Button) findViewById(R.id.bt_welcome);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        //创建Adapter
        list = new ArrayList<>();
        adapter = new VIewPagerAdapter(list);
        viewpager.setAdapter(adapter);
        welcome_radio = (RadioGroup) findViewById(R.id.welcome_radio);
        //默认选中第一个按钮
       welcome_radio.check(R.id.two);
    }

}
