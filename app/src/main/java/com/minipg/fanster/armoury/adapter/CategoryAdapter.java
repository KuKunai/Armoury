package com.minipg.fanster.armoury.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.activity.LoginActivity;
import com.minipg.fanster.armoury.activity.TopicListActivity;
import com.minipg.fanster.armoury.dao.CategoryItemDao;
import com.minipg.fanster.armoury.fragment.TabCategoryFragment;

import java.util.List;
import java.util.Locale;

/**
 * Created by MFEC on 8/7/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryListItemHolder>{

    TabCategoryFragment fragmentCategory;
    List<CategoryItemDao> categoryList;
    TabCategoryFragment fragmentCategory1;
    CategoryItemDao dao;
    private List<CategoryItemDao> data;

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
    public void onBindViewHolder(CategoryListItemHolder holder, final int position) {
        if(categoryList!=null)
            dao = categoryList.get(position);
        if(dao!=null)
            holder.tvCate.setText(dao.getName());
        else
            holder.tvCate.setText("IOS");
        holder.tvAmount.setText("Amount : "+ position+1);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragmentCategory.getActivity(), TopicListActivity.class);
                intent.putExtra("type_device" ,categoryList.get(position).getName());
                fragmentCategory1.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(categoryList == null)
            return 4;
        return categoryList.size();
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

    public void setData(List<CategoryItemDao> data){
        if(data!=null) {
            Log.d("ssss", "onResponse: " + data.get(2).getName());
            this.categoryList = data;
        }
    }
}
