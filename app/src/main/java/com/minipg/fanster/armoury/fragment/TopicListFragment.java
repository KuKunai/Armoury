package com.minipg.fanster.armoury.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.activity.AddTopicActivity;
import com.minipg.fanster.armoury.activity.TopicListActivity;
import com.minipg.fanster.armoury.adapter.TopicListAdapter;
import com.minipg.fanster.armoury.dao.CategoryItemDao;
import com.minipg.fanster.armoury.dao.TopicItemDao;
import com.minipg.fanster.armoury.manager.HttpManager;
import com.minipg.fanster.armoury.manager.bus.Contextor;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("unused")
public class TopicListFragment extends Fragment {
    private static final String KEY_CATEGORY = "category";
    private View mView;
    private RecyclerView recycleView;
    private List<TopicItemDao> topicList;
    private TopicListAdapter topicListAdapter;
    private String categoryName;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fabAddTopic;
    private String strId = "59966e96e4b017a16ae94083";

    public TopicListFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static TopicListFragment newInstance(String category) {
        TopicListFragment fragment = new TopicListFragment();
        Bundle args = new Bundle();
        args.putString(KEY_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null)
            categoryName = bundle.getString(KEY_CATEGORY);
        else
            categoryName = "Category";
        //showToast(categoryName);
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_topic_list, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        mView = rootView;
        topicListAdapter = new TopicListAdapter(this,topicList,TopicListFragment.this);
        recycleView = (RecyclerView) rootView.findViewById(R.id.recycleViewTopicList);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshTopicList);
        fabAddTopic = (FloatingActionButton) rootView.findViewById(R.id.fabAddTopic);

        fabAddTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicListFragment.this.getActivity(), AddTopicActivity.class);
                intent.putExtra("type_cate" ,categoryName);
                TopicListFragment.this.startActivity(intent);
            }
        });

        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(topicListAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                showToast("Refreshed");
            }
        });
        if (savedInstanceState == null) {
            loadData();
        }
    }

    private void loadData() {
        Call<List<TopicItemDao>> call = HttpManager.getInstance().getService().loadTopicListByType(categoryName);
        call.enqueue(new Callback<List<TopicItemDao>>() {
            @Override
            public void onResponse(Call<List<TopicItemDao>> call, Response<List<TopicItemDao>> response) {
                swipeRefreshLayout.setRefreshing(false);
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
            }

            @Override
            public void onFailure(Call<List<TopicItemDao>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showToast("Load Fail");
            }
        });
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    private void showToast(String text) {
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT).show();
    }

}
