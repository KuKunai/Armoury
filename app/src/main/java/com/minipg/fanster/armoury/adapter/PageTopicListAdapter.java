package com.minipg.fanster.armoury.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.minipg.fanster.armoury.fragment.TabCategoryFragment;
import com.minipg.fanster.armoury.fragment.TabLikedFragment;
import com.minipg.fanster.armoury.fragment.TabPopularFragment;
import com.minipg.fanster.armoury.fragment.TabProfileFragment;
import com.minipg.fanster.armoury.fragment.TabRankingFragment;
import com.minipg.fanster.armoury.fragment.TopicListFragment;
import com.minipg.fanster.armoury.fragment.TopicListPopularFragment;

/**
 * Created by Knot on 8/8/2017.
 */

public class PageTopicListAdapter extends FragmentPagerAdapter {

    String cate = "";

    public PageTopicListAdapter(FragmentManager fm) {
        super(fm);
    }

    public PageTopicListAdapter(FragmentManager fm,String cate) {
        super(fm);this.cate = cate;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new TopicListFragment(cate);
            case 1: return new TopicListPopularFragment(cate);
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return cate;
            case 1: return cate;
            default: return cate;
        }
    }

}
