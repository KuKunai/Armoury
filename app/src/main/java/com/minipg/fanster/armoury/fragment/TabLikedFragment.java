package com.minipg.fanster.armoury.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.LikedTopicListAdapter;
import com.minipg.fanster.armoury.adapter.TopicListAdapter;
import com.minipg.fanster.armoury.dao.TopicItemDao;
import com.minipg.fanster.armoury.manager.HttpManager;
import com.minipg.fanster.armoury.manager.bus.Contextor;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class TabLikedFragment extends Fragment {

    private View mView;
    private RecyclerView recycleView;
    private List<TopicItemDao> topicList;
    private LikedTopicListAdapter topicListAdapter;
    private String strId = "5997283de4b017a16ae94085";
    private SwipeRefreshLayout swipeRefreshLayout;

    public TabLikedFragment() {
        super();
    }

    public static TabLikedFragment newInstance() {
        TabLikedFragment fragment = new TabLikedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_liked, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        mView = rootView;
        topicListAdapter = new LikedTopicListAdapter(this,topicList,TabLikedFragment.this);
        recycleView = (RecyclerView) rootView.findViewById(R.id.recycleViewLike);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(topicListAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLiked);
        
        SharedPreferences sharedPref = getActivity().getSharedPreferences("sharedUserID", Context.MODE_PRIVATE);
        strId = sharedPref.getString("userID","5997283de4b017a16ae94085");
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                showToast("Refreshed");
            }
        });
        loadData();
    }

    private void loadData() {
        Call<List<TopicItemDao>> call = HttpManager.getInstance().getService().loadAllLikeList(strId);
        call.enqueue(new Callback<List<TopicItemDao>>() {
            @Override
            public void onResponse(Call<List<TopicItemDao>> call, Response<List<TopicItemDao>> response) {
                if(response.isSuccessful()){
                    topicListAdapter.setData(response.body());
                    topicListAdapter.notifyDataSetChanged();

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
            public void onFailure(Call<List<TopicItemDao>> call, Throwable t) {
                showToast("Load Fail");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
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
}
