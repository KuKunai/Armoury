package com.minipg.fanster.armoury.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.minipg.fanster.armoury.fragment.TabCategoryFragment;
import com.minipg.fanster.armoury.fragment.TabLikedFragment;
import com.minipg.fanster.armoury.fragment.TabPopularFragment;
import com.minipg.fanster.armoury.fragment.TabProfileFragment;
import com.minipg.fanster.armoury.fragment.TabRankingFragment;

/**
 * Created by Knot on 8/8/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new TabProfileFragment();
            case 1: return new TabCategoryFragment();
            case 2: return new TabPopularFragment();
            case 3: return new TabLikedFragment();
            case 4: return new TabRankingFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Profile";
            case 1: return "Category";
            case 2: return "Popular";
            case 3: return "Liked";
            case 4: return "Ranking";
            default: return null;
        }
    }

}
