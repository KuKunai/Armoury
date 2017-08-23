package com.minipg.fanster.armoury.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.activity.TopicActivity;
import com.minipg.fanster.armoury.dao.UserRankingDao;
import com.minipg.fanster.armoury.fragment.TabLikedFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Knot on 8/7/2017.
 */


public class RankingTopicListAdapter extends RecyclerView.Adapter<RankingTopicListAdapter.UserListItemHolder> {

    RankingTopicListAdapter fragmentTopic;
    List<UserRankingDao> userList;
    int oldRank = 0;
    int chance = 0;

    public RankingTopicListAdapter(RankingTopicListAdapter fragment,
                                   List<UserRankingDao> userList) {
        this.fragmentTopic = fragment;
        this.userList = userList;
    }

    public RankingTopicListAdapter(List<UserRankingDao> userList) {
        this.userList = userList;
    }

    public RankingTopicListAdapter() {
    }

    @Override
    public UserListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_user, parent, false);
        return new UserListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserListItemHolder holder, final int position) {
        if (userList != null) {
            holder.tvName.setText(userList.get(position).getName());
//            holder.tvShare.setText(userList.get(position).getDescription());
            holder.tvScore.setText("Score : " + userList.get(position).getScore());
            holder.tvRank.setText(""+userList.get(position).getRank());
            if(userList.get(position).getRank()>oldRank){
                oldRank = userList.get(position).getRank();
                chance++;
            }
            if(chance==1)
                holder.tvRank.setTextColor(Color.parseColor("#FDD835"));
            if(chance==2)
                holder.tvRank.setTextColor(Color.parseColor("#C0C0C0"));
            if(chance==3)
                holder.tvRank.setTextColor(Color.parseColor("#c87533"));
        }

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(fragmentTopic.getActivity(), TopicActivity.class);
//                fragmentTopic1.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (userList == null)
            return 5;
        return userList.size();
    }


    static class UserListItemHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final TextView tvName;
        private final TextView tvScore;
        private final TextView tvShare;
        private final TextView tvRank;

        public UserListItemHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewRanking);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvScore = (TextView) itemView.findViewById(R.id.tvScore);
            tvShare = (TextView) itemView.findViewById(R.id.tvShare);
            tvRank = (TextView) itemView.findViewById(R.id.tvRanking);
        }
    }

    public void setData(List<UserRankingDao> data) {
        if (data != null) {
            this.userList = data;
        }
        Log.d("dataaaa", "onResponse: " + data);
    }
}
