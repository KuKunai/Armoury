package com.minipg.fanster.armoury.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.XpListAdapter;
import com.minipg.fanster.armoury.dao.UserDao;
import com.minipg.fanster.armoury.dao.UserScoreDao;
import com.minipg.fanster.armoury.manager.HttpManager;
import com.minipg.fanster.armoury.manager.bus.Contextor;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class TabProfileFragment extends Fragment {

    private static Bundle userId;
    private double[] values;
    private String[] codename;
    private String[] colors;
    private View mView;
    private PieChart chart;
    private ListView listView;
    private XpListAdapter xpListAdapter;
    private ListView listView2;
    private UserDao userDao;
    private TextView tvScore;
    private TextView tvName;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String strId = "5997283de4b017a16ae94085";

    public TabProfileFragment() {
        super();
    }

    public static TabProfileFragment newInstance() {
        TabProfileFragment fragment = new TabProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        userId = new Bundle();
        if (bundle != null){
            userId = bundle.getBundle("userID");
            //strId = userId.getString("StringID");
        }
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_profile, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        mView = rootView;
        chart = (PieChart) rootView.findViewById(R.id.pieChart);
        listView = (ListView) rootView.findViewById(R.id.listXp);
        tvScore = (TextView) rootView.findViewById(R.id.tvScore);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshProfile);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("sharedUserID",Context.MODE_PRIVATE);
        strId = sharedPref.getString("userID","5997283de4b017a16ae94085");

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                showToast("Refreshed");
            }
        });
        //if (savedInstanceState == null)
            loadData();
    }

    private void loadData() {
        xpListAdapter = new XpListAdapter();
        Call<UserDao> call = HttpManager.getInstance().getService().loadUserById(strId);
        call.enqueue(new Callback<UserDao>() {
            @Override
            public void onResponse(Call<UserDao> call, Response<UserDao> response) {
                if (response.isSuccessful()) {
                    userDao = response.body();
                    tvName.setText("Name : " + userDao.getName());
                    tvScore.setText("Score : " + userDao.getTotalLiked());
                    if (userDao.getShared() != null)
                        if (userDao.getShared().size() != 0) {

                            xpListAdapter.setUserScoreDao(userDao.getShared());
                            xpListAdapter.notifyDataSetChanged();
                            listView.setAdapter(xpListAdapter);
                            initChart();
                        }
                } else {
                    try {
                        showToast(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<UserDao> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initChart() {
        final ArrayList<UserScoreDao> listScore = (ArrayList<UserScoreDao>) userDao.getShared();
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (UserScoreDao score : listScore) {
            entries.add(new PieEntry(score.getAmount(), score.getName()));
        }
//        ArrayList<PieEntry> entries = new ArrayList<>();
//        entries.add(new PieEntry(1,"IOS"));
//        entries.add(new PieEntry(0,"Android"));
//        entries.add(new PieEntry(0,"Web"));
//        entries.add(new PieEntry(0,"Service"));

        PieDataSet dataset = new PieDataSet(entries, "");
        dataset.setSelectionShift(10);
        dataset.setValueTextSize(14);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataset.setValueLinePart1Length(0.5f);
        dataset.setValueLinePart2Length(0.5f);
        dataset.setValueFormatter(new PercentFormatter());

        PieData data = new PieData(dataset);

        chart.setData(data);
        chart.setDescription("%Topic that you shared");
        chart.setHoleRadius(20);
        chart.setTransparentCircleRadius(0);
        chart.animateY(500);
        chart.setUsePercentValues(true);
    }

    private void showToast(String text) {
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }
}
