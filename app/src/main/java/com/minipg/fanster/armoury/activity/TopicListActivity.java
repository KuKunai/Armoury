package com.minipg.fanster.armoury.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.topicListContainer, TopicListFragment.newInstance()) //MainFragment.newInstance())
                    .commit();
        }
        initInstance();
    }

    private void initInstance() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
