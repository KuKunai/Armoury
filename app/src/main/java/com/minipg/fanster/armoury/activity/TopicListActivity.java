package com.minipg.fanster.armoury.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.fragment.TopicListFragment;

/**
 * Created by Knot on 8/10/2017.
 */

public class TopicListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private String category = "Category";
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
