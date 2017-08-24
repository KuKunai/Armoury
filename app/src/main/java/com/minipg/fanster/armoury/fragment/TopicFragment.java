package com.minipg.fanster.armoury.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.ShareActionProvider;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.dao.TopicItemDao;
import com.minipg.fanster.armoury.dao.UserDao;
import com.minipg.fanster.armoury.manager.HttpManager;
import com.minipg.fanster.armoury.manager.bus.Contextor;
import com.minipg.fanster.armoury.object.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class TopicFragment extends Fragment {

    private View mView;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvDate;
    private TextView tvDescribtion;
    private TextView tvLike;
    private Bundle topic;
    private TextView tvLink;

    final String KEY_HEAD = "head";
    final String KEY_DESC = "desc";
    final String KEY_LINK = "link";
    final String KEY_LIKE = "like";
    final String KEY_POSTER = "author";
    final String KEY_DATE = "date";
    final String KEY_ID = "topicId";

    private TopicItemDao dao;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fabLike;
    private String strId;

    public TopicFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static TopicFragment newInstance(Bundle topicBundle) {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        if (topicBundle != null)
            args.putBundle("topic", topicBundle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        Bundle bundle = getArguments();
        topic = new Bundle();
        if (bundle != null)
            topic = bundle.getBundle("topic");
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_topic, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        mView = rootView;
        tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
        tvAuthor = (TextView) rootView.findViewById(R.id.tvAuthor);
        tvDate = (TextView) rootView.findViewById(R.id.tvDate);
        tvDescribtion = (TextView) rootView.findViewById(R.id.tvStory);
        tvLike = (TextView) rootView.findViewById(R.id.tvLiked);
        tvLink = (TextView) rootView.findViewById(R.id.tvLink);
        fabLike = (FloatingActionButton) rootView.findViewById(R.id.fabLike);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("sharedUserID", getActivity().MODE_PRIVATE);
        strId = sharedPref.getString("userID", "");
        if (topic != null) {
            initView(topic.getString(KEY_HEAD),
                    topic.getString(KEY_POSTER),
                    topic.getLong(KEY_DATE),
                    topic.getString(KEY_DESC),
                    topic.getString(KEY_LINK),
                    topic.getInt(KEY_LIKE));
        }
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshTopic);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                showToast("Refreshed");
            }
        });
        fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                like();
            }
        });
        if (savedInstanceState == null)
            loadData();
    }

    private void like() {
        if (dao != null) {
            if (dao.getId() != null) {
                swipeRefreshLayout.setRefreshing(true);
                Call<Boolean> call = HttpManager.getInstance().getService().likeTopic(strId, dao.getId());
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        swipeRefreshLayout.setRefreshing(false);
                        if (response.isSuccessful()) {
                            if (response.body()) {
                                fabLike.setBackgroundTintList(ColorStateList.valueOf(Color
                                        .parseColor("#ffaaaa")));
                                showToast("Liked");
                            } else {
                                fabLike.setBackgroundTintList(ColorStateList.valueOf(Color
                                        .parseColor("#424242")));
                                showToast("Disliked");
                            }
                            loadData();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        swipeRefreshLayout.setRefreshing(false);
                        showToast("Try again");
                    }
                });
            }
        }
    }

    private void initView(String title, String poster, long date, String desc, String link, int score) {
        tvTitle.setText(title);
        tvAuthor.setText("by " + poster);
        tvDate.setText("Posted on " + convertUnixToDate(date));
        tvDescribtion.setText(desc);
        String html = "Link => <a href =\"" + link + "\">" + link + "</a>";
//            String html = "<a href =\"http://www.apple.com\">Test</a>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            tvLink.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        else
            tvLink.setText(Html.fromHtml(html));
        tvLink.setMovementMethod(LinkMovementMethod.getInstance());
        tvLike.setText(score + " Liked");
    }

    private void loadData() {
        if (topic != null) {
            Call<TopicItemDao> call = HttpManager.getInstance().getService().loadTopicById(topic.getString(KEY_ID));
            call.enqueue(new Callback<TopicItemDao>() {
                @Override
                public void onResponse(Call<TopicItemDao> call, Response<TopicItemDao> response) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (response.isSuccessful()) {
                        dao = response.body();
                        if (dao != null) {
                            initView(dao.getTitle(), dao.getPoster(), dao.getCreateDate(), dao.getDescription(), dao.getLink(), dao.getScore());
                            checkLikeState(dao.getUserLike());
                        }
                    } else {
                        try {
                            showToast(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<TopicItemDao> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                    showToast("Load Fail");
                }
            });
        }
    }

    private void checkLikeState(List<String> listUser) {
        if (listUser != null) {
            for (String user : listUser) {
                if (strId.equals(user)) {
                    fabLike.setBackgroundTintList(ColorStateList.valueOf(Color
                            .parseColor("#ffaaaa")));
                    break;
                } else {
                    fabLike.setBackgroundTintList(ColorStateList.valueOf(Color
                            .parseColor("#424242")));
                }
            }
        }
    }


    public String convertUnixToDate(Long date) {
        Date dateT = new Date();
        dateT.setTime(date * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a  dd/MM/yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-7"));
        String formattedDate = sdf.format(dateT);
        return formattedDate;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_topic, menu);
        MenuItem menuItem = (MenuItem) menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(menuItem);
        shareActionProvider.setShareIntent(getSharIntent());
    }

    private Intent getSharIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String textTitle = tvTitle.getText() + " " + tvAuthor.getText();
        intent.putExtra(Intent.EXTRA_SUBJECT, textTitle);
        intent.putExtra(Intent.EXTRA_TITLE, textTitle);
        intent.putExtra(Intent.EXTRA_TEXT, tvDescribtion.getText() + "\n" + tvLink.getText());
        return intent;
    }

    private void showToast(String text) {
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                Toast.LENGTH_SHORT).show();
    }
}
