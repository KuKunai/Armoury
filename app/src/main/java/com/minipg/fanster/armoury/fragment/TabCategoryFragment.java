package com.minipg.fanster.armoury.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.adapter.CategoryAdapter;
import com.minipg.fanster.armoury.dao.CategoryItemDao;
import com.minipg.fanster.armoury.manager.HttpManager;
import com.minipg.fanster.armoury.manager.bus.Contextor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabCategoryFragment extends Fragment {

    private View mView;
    private CategoryAdapter categoryListAdapter;
    private RecyclerView recyclerView;
    private List<CategoryItemDao> categoryList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public TabCategoryFragment() {
        super();
    }

    @SuppressWarnings("unused")

    public static TabCategoryFragment newInstance() {
        TabCategoryFragment fragment = new TabCategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_category, container, false);
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
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rc_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryListAdapter = new CategoryAdapter(this, categoryList, TabCategoryFragment.this);
        recyclerView.setAdapter(categoryListAdapter);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshCategory);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                showToast("Refreshed");
            }
        });
        if (savedInstanceState == null)
            loadData();
    }

    private void loadData() {
        Call<List<CategoryItemDao>> call = HttpManager.getInstance().getService().loadAllCategoryList();
        call.enqueue(new Callback<List<CategoryItemDao>>() {
            @Override
            public void onResponse(Call<List<CategoryItemDao>> call, Response<List<CategoryItemDao>> response) {
                if (response.isSuccessful()) {
                    List<CategoryItemDao> dao = response.body();
                    categoryListAdapter.setData(dao);
                    categoryListAdapter.notifyDataSetChanged();

                    //showToast("Load Completed");
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
            public void onFailure(Call<List<CategoryItemDao>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showToast("Load Fail");
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

}
