//package com.minipg.fanster.armoury.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.minipg.fanster.armoury.R;
//import com.minipg.fanster.armoury.adapter.CategoryAdapter;
//import com.minipg.fanster.armoury.dao.CategoryItemDao;
//import com.minipg.fanster.armoury.manager.CategoryListManager;
//import com.minipg.fanster.armoury.manager.HttpManager;
//import com.minipg.fanster.armoury.manager.bus.Contextor;
//
//import java.io.IOException;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * Created by nuuneoi on 11/16/2014.
// */
//@SuppressWarnings("unused")
//public class TabCategory2Fragment extends Fragment {
//
//    private View mView;
//    private CategoryAdapter categoryListAdapter;
//    private ListView listView;
//    private List<CategoryItemDao> categoryList;
//    private CategoryListManager categoryListManager;
//
//    public TabCategory2Fragment() {
//        super();
//    }
//
//    @SuppressWarnings("unused")
//    public static TabCategory2Fragment newInstance() {
//        TabCategory2Fragment fragment = new TabCategory2Fragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        init(savedInstanceState);
//
//        if (savedInstanceState != null)
//            onRestoreInstanceState(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_tab_category, container, false);
//        initInstances(rootView, savedInstanceState);
//        return rootView;
//    }
//
//    private void init(Bundle savedInstanceState) {
//        // Init Fragment level's variable(s) here
//        categoryListManager = new CategoryListManager();
//    }
//
//    @SuppressWarnings("UnusedParameters")
//    private void initInstances(View rootView, Bundle savedInstanceState) {
//        // Init 'View' instance(s) with rootView.findViewById here
//        listView = (ListView) rootView.findViewById(com.minipg.fanster.armoury.R.id.rc_list);
//        categoryListAdapter = new CategoryAdapter();
//        //listView.setAdapter(categoryListAdapter);
//        //loadData();
//    }
//
//    private void loadData() {
//        Call<List<CategoryItemDao>> call = HttpManager.getInstance().getService().loadAllCategoryList();
//        call.enqueue(new Callback<List<CategoryItemDao>>() {
//            @Override
//            public void onResponse(Call<List<CategoryItemDao>> call, Response<List<CategoryItemDao>> response) {
//                if (response.isSuccessful()) {
//                    List<CategoryItemDao> dao = response.body();
//                    categoryListManager.setDao(dao);
//                    //categoryListAdapter.setDao(categoryListManager.getDao());
//                    categoryListAdapter.notifyDataSetChanged();
//                    showToast("Load Completed");
//                } else {
//                    try {
//                        showToast(response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<CategoryItemDao>> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void showToast(String text) {
//        Toast.makeText(Contextor.getInstance().getContext(),
//                text,
//                Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
//
//    /*
//     * Save Instance State Here
//     */
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        // Save Instance State here
//    }
//
//    /*
//     * Restore Instance State Here
//     */
//    @SuppressWarnings("UnusedParameters")
//    private void onRestoreInstanceState(Bundle savedInstanceState) {
//        // Restore Instance State here
//    }
//
//}
