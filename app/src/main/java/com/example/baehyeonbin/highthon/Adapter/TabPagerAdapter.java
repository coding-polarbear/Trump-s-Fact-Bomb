package com.example.baehyeonbin.highthon.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.baehyeonbin.highthon.Fragment.My_Request_Fragment;
import com.example.baehyeonbin.highthon.Fragment.Request_Fragment;

/**
 * Created by pc on 2017-11-05.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabPagerAdapter(FragmentManager fm,int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Request_Fragment Request_Fragment = new Request_Fragment();
                return Request_Fragment;
            case 1:

                My_Request_Fragment My_Request_Fragment = new My_Request_Fragment();
                return My_Request_Fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
