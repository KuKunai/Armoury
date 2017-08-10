package com.minipg.fanster.armoury.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.PageAdapter;
import com.minipg.fanster.armoury.fragment.MainFragment;
import com.minipg.fanster.armoury.fragment.TabProfileFragment;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.viewPager,TabProfileFragment.newInstance(),"TabProfileFragment") //MainFragment.newInstance(), "MainFragment")
                .commit();


        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.viewPager, TabProfileFragment.newInstance()) //MainFragment.newInstance())
                    .commit();
        }


        initInstance();
    }

    private void initInstance() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons(tabLayout);
    }

    private void createTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_profile).setText(R.string.tab_profile_title);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_library_white).setText(R.string.tab_library_title);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_rating).setText(R.string.tab_popular_title);
        tabLayout.getTabAt(3).setIcon(R.mipmap.ic_faverite).setText(R.string.tab_liked_title);
    }
}
