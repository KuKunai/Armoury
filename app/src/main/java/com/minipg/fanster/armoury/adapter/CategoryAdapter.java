package com.minipg.fanster.armoury.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.activity.LoginActivity;
import com.minipg.fanster.armoury.dao.CategoryItemDao;
import com.minipg.fanster.armoury.fragment.TabCategoryFragment;
import com.minipg.fanster.armoury.manager.CategoryListManager;
import com.minipg.fanster.armoury.view.CategoryListItem;

import java.util.List;
import java.util.Locale;

/**
 * Created by MFEC on 8/7/2017.
 */

//public class CategoryAdapter extends BaseAdapter {
//
//    List<CategoryItemDao> dao;
//
//    public void setDao(List<CategoryItemDao> dao) {
//        this.dao = dao;
//    }
//
//    @Override
//    public int getCount() {
//        if (dao == null)
//            return 0;
//        return dao.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return CategoryListManager.getInstance().getDao().get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        CategoryListItem item;
//        if(view != null)
//            item = (CategoryListItem) view;
//        else
//            item = new CategoryListItem(viewGroup.getContext());
//        CategoryItemDao dao = (CategoryItemDao) getItem(i);
//        item.setCategory(dao.getName());
//        item.setAmount(i);
//        return item;
//    }
//}

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryListItemHolder>{

    TabCategoryFragment fragmentCategory;
    List<CategoryItemDao> categoryList;
    TabCategoryFragment fragmentCategory1;


    public CategoryAdapter(TabCategoryFragment fragmentCategory, List<CategoryItemDao> categoryList, TabCategoryFragment fragmentCategory1){
        this.fragmentCategory = fragmentCategory;
        this.categoryList = categoryList;
        this.fragmentCategory1 = fragmentCategory1;
    }

    @Override
    public CategoryListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_category,parent,false);
        return new CategoryListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryListItemHolder holder, int position) {
        holder.tvCate.setText("IOS");
        holder.tvAmount.setText("Amount : "+ position+1);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragmentCategory.getActivity(), LoginActivity.class);
                fragmentCategory1.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class  CategoryListItemHolder extends RecyclerView.ViewHolder {

        private final TextView tvCate;
        private final TextView tvAmount;
        private final CardView cardView;


        public CategoryListItemHolder(View itemView) {
            super(itemView);
            tvCate = (TextView) itemView.findViewById(R.id.tvCategory);
            tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);
            cardView = (CardView) itemView.findViewById(R.id.cardViewCategory);
        }
    }
}
