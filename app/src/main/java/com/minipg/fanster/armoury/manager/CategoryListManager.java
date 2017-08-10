package com.minipg.fanster.armoury.manager;

import android.content.Context;
import com.minipg.fanster.armoury.dao.CategoryItemDao;
import com.minipg.fanster.armoury.manager.bus.Contextor;

import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class CategoryListManager {

    private Context mContext;
    private List<CategoryItemDao> dao;

    private  static CategoryListManager instance;

    public static CategoryListManager getInstance() {
        if (instance == null)
            instance = new CategoryListManager();
        return instance;
    }

    public CategoryListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public List<CategoryItemDao> getDao() {
        return dao;
    }

    public void setDao(List<CategoryItemDao> dao) {
        this.dao = dao;
    }

    public int getCount(){
        if(dao == null)
            return 0;
        return dao.size();
    }
}
