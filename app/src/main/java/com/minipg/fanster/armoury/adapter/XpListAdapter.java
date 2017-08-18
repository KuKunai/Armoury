package com.minipg.fanster.armoury.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.minipg.fanster.armoury.dao.CategoryItemDao;
import com.minipg.fanster.armoury.dao.UserDao;
import com.minipg.fanster.armoury.dao.UserScoreDao;
import com.minipg.fanster.armoury.manager.HttpManager;
import com.minipg.fanster.armoury.object.User;
import com.minipg.fanster.armoury.view.XpListItem;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Knot on 8/7/2017.
 */


public class XpListAdapter extends BaseAdapter {

    List<UserScoreDao> userScoreDao;
    List<CategoryItemDao> categoryItemDao;

    public XpListAdapter() {
        loadData();
    }

    private void loadData() {
        Call<List<CategoryItemDao>> call = HttpManager.getInstance().getService().loadAllCategoryList();
        call.enqueue(new Callback<List<CategoryItemDao>>() {
            @Override
            public void onResponse(Call<List<CategoryItemDao>> call, Response<List<CategoryItemDao>> response) {
                if (response.isSuccessful()) {
                    categoryItemDao = response.body();
                    //showToast("Load Completed");
                }
            }

            @Override
            public void onFailure(Call<List<CategoryItemDao>> call, Throwable t) {
            }
        });
    }

    @Override
    public int getCount() {
        if (userScoreDao != null)
            return userScoreDao.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        XpListItem item;
        if (view != null)
            item = (XpListItem ) view;
        else
            item = new XpListItem (viewGroup.getContext());
        boolean isNotInCate = true;
        if(userScoreDao!=null){
            if(categoryItemDao!=null){
                for(CategoryItemDao all : categoryItemDao){
                    if(all.getName().equals(userScoreDao.get(i).getName())){
                        item.setTopicXp(userScoreDao.get(i).getName(),userScoreDao.get(i).getAmount(),all.getAmount());
                    }
                }

            }
        }
        return item;
        //return new XpListItem(viewGroup.getContext());
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    public List<UserScoreDao> getUserScoreDao() {
        return userScoreDao;
    }

    public void setUserScoreDao(List<UserScoreDao> userScoreDao) {
        this.userScoreDao = userScoreDao;
    }
}
