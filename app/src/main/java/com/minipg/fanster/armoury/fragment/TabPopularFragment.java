package com.minipg.fanster.armoury.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.TopicListAdapter;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class TabPopularFragment extends Fragment {

    private View mView;
    private RecyclerView recycleView;
    private TopicListAdapter topicListAdapter;

    public TabPopularFragment() {
        super();
    }

    public static TabPopularFragment newInstance() {
        TabPopularFragment fragment = new TabPopularFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_popular, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        mView = rootView;
        topicListAdapter = new TopicListAdapter();
        recycleView = (RecyclerView) rootView.findViewById(R.id.recycleViewPop);
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
