package com.android.chat_apps;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainPagerAdapter extends FragmentStateAdapter {

    private int numbOfTabs;
    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position ) {

        switch (position) {
            case 0:
                return new PesananFragment();
            case 1:
                return new HomeFragment();
            default:
                return new ExtraFragment();

        }


    }

    @Override
    public int getItemCount() {

        return 3;
    }



}
