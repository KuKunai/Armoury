package com.minipg.fanster.armoury.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.activity.TopicActivity;
import com.minipg.fanster.armoury.dao.TopicItemDao;
import com.minipg.fanster.armoury.fragment.TabPopularFragment;
import com.minipg.fanster.armoury.fragment.TopicListFragment;

import java.util.List;


/**
 * Created by Knot on 8/7/2017.
 */



public class PopularTopicListAdapter extends RecyclerView.Adapter<PopularTopicListAdapter.TopicListItemHolder> {

    TabPopularFragment fragmentCategory;
    List<TopicItemDao> categoryList;
    TabPopularFragment fragmentCategory1;

    public PopularTopicListAdapter(TabPopularFragment fragmentCategory,
                                   List<TopicItemDao> categoryList, TabPopularFragment fragmentCategory1){
        this.fragmentCategory = fragmentCategory;
        this.categoryList = categoryList;
        this.fragmentCategory1 = fragmentCategory1;
    }

    public PopularTopicListAdapter(){
    }

    @Override
    public TopicListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_topic,parent,false);
        return new TopicListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopicListItemHolder holder, int position) {
        holder.tvTitle.setText("Title" + position);
        holder.tvAuthor.setText("By Author");
        holder.tvStory.setText("Story");
        holder.tvDate.setText("Date");
        holder.tvLiked.setText(position + "Liked");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fragmentCategory.getActivity(), TopicActivity.class);
                fragmentCategory1.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    static class  TopicListItemHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvAuthor;
        private final TextView tvStory;
        private final TextView tvDate;
        private final TextView tvLiked;
        private final CardView cardView;

        public TopicListItemHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewTopic);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvStory = (TextView) itemView.findViewById(R.id.tvStory);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvLiked = (TextView) itemView.findViewById(R.id.tvLiked);
        }
    }
}
