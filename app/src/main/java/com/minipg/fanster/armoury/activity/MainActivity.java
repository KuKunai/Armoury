package com.minipg.fanster.armoury.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private RelativeLayout dialogLogout;
    private Button btnCancel;
    private Button btnLogout;
    private TextView tvExit;
    private View viewOutsideDialog;


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
        //Logout Dialog
        dialogLogout = (RelativeLayout) findViewById(R.id.dialogLogout);
        tvExit = (TextView) findViewById(R.id.tvExit);
        viewOutsideDialog = (View)findViewById(R.id.viewOutsideDialog);
        viewOutsideDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogout.setVisibility(view.GONE);
            }
        });
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_logout) {
            dialogLogout.setVisibility(View.VISIBLE);
            tvExit.setText("Are you sure dude?");
            //finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
