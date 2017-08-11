package com.minipg.fanster.armoury.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.DeviceTypeItem;
import com.minipg.fanster.armoury.adapter.PageAdapter;
import com.minipg.fanster.armoury.dao.TopicItemDao;
import com.minipg.fanster.armoury.fragment.TabProfileFragment;
import com.minipg.fanster.armoury.fragment.TopicListFragment;
import com.minipg.fanster.armoury.manager.HttpManager;

import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Knot on 8/10/2017.
 */

public class TopicListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String category = "Category";
    private RecyclerView recyclerView;
    private DeviceTypeItem deviceTypeItem;

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
        category = getIntent().getStringExtra("type_device");
        toolbar.setTitle(category);
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
