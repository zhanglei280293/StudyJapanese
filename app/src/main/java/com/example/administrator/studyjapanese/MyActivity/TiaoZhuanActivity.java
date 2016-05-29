package com.example.administrator.studyjapanese.MyActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.studyjapanese.R;
import com.example.administrator.studyjapanese.Utils.PackageUtils;

public class TiaoZhuanActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiao_zhuan);

        //取消ActionBar
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        //获取以前的版本号
        SharedPreferences preferences = getSharedPreferences("version", MODE_PRIVATE);
        String old_version = preferences.getString("version", null);
        //打开编辑模式
        SharedPreferences.Editor edit = preferences.edit();
        //将当前的版本号和以前的版本号进行比较
        String new_version = PackageUtils.getPackageVersion(this);
        if (new_version.equals(old_version)){
            //表示用户用过一次或app没有更新（跳转到主界面）
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(TiaoZhuanActivity.this, MainActivity.class));
                    TiaoZhuanActivity.this.finish();
                }
            }, 1500);

        }else{
            //表示用户没有用过此app或app更新了（进入欢迎页）
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(TiaoZhuanActivity.this, WelcomeActivity.class));
                    TiaoZhuanActivity.this.finish();
                }
            },1500);

        }
    }
}
