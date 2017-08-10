package com.minipg.fanster.armoury.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.PageAdapter;
import com.minipg.fanster.armoury.fragment.TabProfileFragment;
import com.minipg.fanster.armoury.fragment.TopicListFragment;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Knot on 8/10/2017.
 */

public class TopicListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.topicContainer, TopicListFragment.newInstance()) //MainFragment.newInstance())
                    .commit();
        }
        initInstance();
    }
    private void initInstance() {
    }
}
