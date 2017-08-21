package com.minipg.fanster.armoury.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
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
    private View viewOutsideDialog;
    private RelativeLayout dialogLogoutB;
    private Button btnCancelB;
    private Button btnClose;
    private View viewOutsideDialogB;
    private boolean close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.viewPager, TabProfileFragment.newInstance()) //MainFragment.newInstance())
                    .commit();
        }


        initInstance();
    }

    private void initInstance() {

        //Logout Dialog
        initLogoutDialog();
        //Back press Dialog
        initBackDialog();
        //View Play
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        final PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
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

    private void initBackDialog() {
        close = false;
        dialogLogoutB = (RelativeLayout) findViewById(R.id.dialogBack);
        viewOutsideDialogB = (View) findViewById(R.id.viewOutsideDialog2);
        btnCancelB = (Button) findViewById(R.id.btnCancel2);
        btnClose = (Button) findViewById(R.id.btnLogout2);
        viewOutsideDialogB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btnCancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogoutB.setVisibility(view.GONE);
                close = false;
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close = true;
                finish();
            }
        });
    }

    private void initLogoutDialog() {
        dialogLogout = (RelativeLayout) findViewById(R.id.dialogLogout);
        viewOutsideDialog = (View) findViewById(R.id.viewOutsideDialog);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        viewOutsideDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogLogout.setVisibility(view.GONE);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                SharedPreferences sharedPref = getSharedPreferences("sharedUserID", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("userID", null);
                editor.commit();
                startActivity(intent);
                //TODO: Clear user
                close = true;
                finish();
            }
        });
    }

    private void createTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_profile).setText(null);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_library_white).setText(null);
        tabLayout.getTabAt(2).setIcon(R.mipmap.ic_rating).setText(null);
        tabLayout.getTabAt(3).setIcon(R.mipmap.ic_faverite).setText(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            dialogLogout.setVisibility(View.VISIBLE);
            //finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        if(close)
            super.finish();
        if (dialogLogout.getVisibility() == View.VISIBLE) {
            dialogLogout.setVisibility(View.GONE);
        } else if (dialogLogoutB.getVisibility() == View.VISIBLE) {
            dialogLogoutB.setVisibility(View.GONE);
            close = false;
        } else dialogLogoutB.setVisibility(View.VISIBLE);

    }
}
