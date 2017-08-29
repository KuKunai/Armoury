package com.minipg.fanster.armoury.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.PageAdapter;
import com.minipg.fanster.armoury.adapter.PageTopicListAdapter;
import com.minipg.fanster.armoury.fragment.TopicListFragment;


/**
 * Created by Knot on 8/10/2017.
 */

public class TopicListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String category = "Category";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        initInstance();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.topicListContainer, TopicListFragment.newInstance(category)) //MainFragment.newInstance())
                    .commit();
        }

    }

    private void initInstance() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        category = getIntent().getStringExtra("type_device");
        toolbar.setTitle(category);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        final PageTopicListAdapter pageAdapter = new PageTopicListAdapter(getSupportFragmentManager(),category);
        viewPager.setAdapter(pageAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons(tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar.setTitle(pageAdapter.getPageTitle(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void createTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setText("Latest");
        tabLayout.getTabAt(1).setText("Popular");
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
