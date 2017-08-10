package com.minipg.fanster.armoury.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minipg.fanster.armoury.R;


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

    public TopicFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static TopicFragment newInstance() {
        TopicFragment fragment = new TopicFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_topic, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
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
        intent.putExtra(Intent.EXTRA_SUBJECT, textTitle;
        intent.putExtra(Intent.EXTRA_TITLE, textTitle);
        intent.putExtra(Intent.EXTRA_TEXT, tvDescribtion.getText());
        return intent;
    }
}
