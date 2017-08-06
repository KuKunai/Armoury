package com.minipg.fanster.armoury.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.fragment.MainFragment;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentContainer, MainFragment.newInstance(), "MainFragment")
                .commit();


        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }


        initInstance();
    }

    private void initInstance() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        createTabIcons(tabLayout);


    }

    private void createTabIcons(TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("Profile").setIcon(R.mipmap.ic_profile));
        tabLayout.addTab(tabLayout.newTab().setText("Library").setIcon(R.mipmap.ic_folder));
        tabLayout.addTab(tabLayout.newTab().setText("Popular").setIcon(R.mipmap.ic_rating));
        tabLayout.addTab(tabLayout.newTab().setText("Loved").setIcon(R.mipmap.ic_faverite));
    }
}
