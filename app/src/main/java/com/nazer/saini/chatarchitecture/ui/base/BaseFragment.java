package com.nazer.saini.chatarchitecture.ui.base;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.nazer.saini.chatarchitecture.callbacks.GenericCallBack;
import com.nazer.saini.chatarchitecture.ui.activity.MainActivity;

public abstract class BaseFragment extends Fragment {

    public abstract void setUpLayout();
    public abstract void setUpView();
    @NonNull
    public void setToolbarTitle(String title)
    {
        ((MainActivity)getActivity()).setTitle(title);
    }
    public void setToolbarSubtitleTitle(String title) {
        ((MainActivity) getContext()).setSubtitle(title);
    }
    public void setToolbarHide()
    {
        ((MainActivity)getContext()).hideToolbar();
    }
    public void setToolbarShow()
    {
        ((MainActivity)getContext()).showToolbar();
    }
    public void addFragment(Fragment fragment)
    {
        ((MainActivity)getContext()).addFragment(fragment);
    }
    public void popFragment()
    {
        ((MainActivity)getContext()).popFragment();
    }





    public void replaceFragment(Fragment fragment) {
        ((MainActivity) getContext()).replaceFragment(fragment);
    }

    public void clearBackStack() {
        ((MainActivity) getContext()).clearBackStack();
    }

    /**
     * if Searching has in fragment using Toolbar of Main Activity
     *
     * @param callBack
     */
    public void setCallBack(GenericCallBack callBack) {
        ((MainActivity) getContext()).setCurrentFragmentCallBack(callBack);
    }

    /**
     * false to hide the option menu for fragment
     * true to show the option menu for fragment
     *
     * @param isOptionMenu
     */
    public void isOptionMenu(boolean isOptionMenu) {
        setHasOptionsMenu(isOptionMenu);
    }

}
