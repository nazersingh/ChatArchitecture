package com.nazer.saini.chatarchitecture.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nazer.saini.chatarchitecture.R;
import com.nazer.saini.chatarchitecture.application.MyApplication;
import com.nazer.saini.chatarchitecture.ui.base.BaseActivity;
import com.nazer.saini.chatarchitecture.ui.fragments.chat.ChatFragment;
import com.nazer.saini.chatarchitecture.ui.fragments.splash.SplashFragment;

public class MainActivity extends BaseActivity {
    Fragment fragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(new SplashFragment());
    }

    @Override
    public void setUpLayout() {

    }

    @Override
    public void setUpView() {

    }

    public void addFragment(Fragment fragment) {
        fragment1=fragment;
        onAddFragment(R.id.container, fragment);
    }

    public void replaceFragment(Fragment fragment ) {
        onReplaceFragment(R.id.container, fragment );
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setMainActivity(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragment1.onActivityResult(requestCode, resultCode, data);
    }
}
