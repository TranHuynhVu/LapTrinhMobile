package com.example.bottomnavigation_tablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bottomnavigation_tablayout.fragment.HomeFragment;
import com.example.bottomnavigation_tablayout.fragment.ProfileFragment;
import com.example.bottomnavigation_tablayout.fragment.ProjectFragment;
import com.example.bottomnavigation_tablayout.tabproject.Tab1Fragment;
import com.example.bottomnavigation_tablayout.tabproject.Tab2Fragment;

public class ProjectViewPageAdapter extends FragmentPagerAdapter {
    public ProjectViewPageAdapter(FragmentManager manager, int behavior) {
        super(manager, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Tab1Fragment();
            case 1:
                return new Tab2Fragment();

            default:
                return new Tab1Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Dự án cá nhân";
            case 1:
                return "Tab 2";
            default:
                return "Tab 1";
        }
    }
}
