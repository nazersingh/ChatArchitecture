package com.nazer.saini.chatarchitecture.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nazer.saini.chatarchitecture.R;
import com.nazer.saini.chatarchitecture.callbacks.GenericCallBack;


public abstract class BaseActivity extends AppCompatActivity {
    private long TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed = 0;
    private GenericCallBack genricCallback;

    public abstract void setUpLayout();

    public abstract void setUpView();

    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void setToolbarSubTitle(String title) {
        getSupportActionBar().setSubtitle(title);
    }

    public void hideToolbar() {
        getSupportActionBar().hide();
    }

    public void showToolbar() {
        getSupportActionBar().show();
    }

    public void onAddFragment(int container, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(container, fragment, fragment.getTag());
        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commitAllowingStateLoss();
    }


    public void onReplaceFragment(int container, Fragment fragment ) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment, fragment.getTag());
            fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public void setSubtitle(String subtitle) {
        getSupportActionBar().setSubtitle(subtitle);
    }



    public void popFragment() {
        getSupportFragmentManager().popBackStack();
    }

    public void setCurrentFragmentCallBack(GenericCallBack genricCallback) {
        this.genricCallback=genricCallback;
    }

    public void clearBackStack() {
        getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//            popFragment();
            super.onBackPressed();
            Fragment ffragment = getSupportFragmentManager().findFragmentById(R.id.container);
            ffragment.setUserVisibleHint(true);
        } else {

            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                finish();
            } else {
                mBackPressed = System.currentTimeMillis();
                Toast.makeText(getBaseContext(), "Press again to exit", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
