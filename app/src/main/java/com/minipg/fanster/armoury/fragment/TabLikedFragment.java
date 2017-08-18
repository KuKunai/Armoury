package com.minipg.fanster.armoury.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.LikedTopicListAdapter;
import com.minipg.fanster.armoury.adapter.TopicListAdapter;
import com.minipg.fanster.armoury.dao.TopicItemDao;

import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class TabLikedFragment extends Fragment {

    private View mView;
    private RecyclerView recycleView;
    private List<TopicItemDao> topicList;
    private LikedTopicListAdapter topicListAdapter;

    public TabLikedFragment() {
        super();
    }

    public static TabLikedFragment newInstance(Bundle userIdBundle) {
        TabLikedFragment fragment = new TabLikedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }
}
