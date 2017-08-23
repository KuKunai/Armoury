package com.minipg.fanster.armoury.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.dao.TopicItemDao;
import com.minipg.fanster.armoury.manager.HttpManager;
import com.minipg.fanster.armoury.object.TopicForm;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Knot on 8/18/2017.
 */

public class AddTopicActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDesc;
    private EditText etLink;
    private RelativeLayout relativeLayoutAddTopic;
    private FloatingActionButton fabCancelAddTopic;
    private Button btnAddTopic;
    private String category;
    private TextView addTopic;
    private String strId;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtopic);
        initInstance();

    }

    private void initInstance() {

        fabCancelAddTopic = (FloatingActionButton) findViewById(R.id.fabCancelAddTopic);
        btnAddTopic = (Button) findViewById(R.id.btnADD);
        etTitle = (EditText) findViewById(R.id.et_title);
        etDesc = (EditText) findViewById(R.id.et_description);
        etLink = (EditText) findViewById(R.id.et_Link);
        relativeLayoutAddTopic = (RelativeLayout) findViewById(R.id.relativeLayoutAddTopic);
        addTopic = (TextView) findViewById(R.id.tvAddTopic);

        category = getIntent().getExtras().getString("cate_type");

        SharedPreferences sharedPref = getSharedPreferences("sharedUserID",Context.MODE_PRIVATE);
        strId = sharedPref.getString("userID","5997283de4b017a16ae94085");

        initHideKeyboard(relativeLayoutAddTopic);
        initButton();
        addTopic.setText("Add Topic in "+category);
    }

    private void initButton() {
        fabCancelAddTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TopicForm topicForm = new TopicForm(etTitle.getText().toString(),
                        etLink.getText().toString(),
                        etDesc.getText().toString(),
                        category);
                showToast(topicForm.getForm());
                postData(topicForm);
                finish();
            }
        });
    }

    private void initHideKeyboard(RelativeLayout touchInterceptor) {
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setHideKeyboard(etDesc, v, event);
                    setHideKeyboard(etLink, v, event);
                    setHideKeyboard(etTitle, v, event);
                }
                return false;
            }
        });
    }

    private void setHideKeyboard(final EditText editText, View v, MotionEvent event) {
        if (editText.isFocused()) {
            Rect outRect = new Rect();
            editText.getGlobalVisibleRect(outRect);
            if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                editText.clearFocus();
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    private void showToast(String username) {
        Toast.makeText(AddTopicActivity.this, username.toString(), Toast.LENGTH_SHORT).show();
    }

    private void postData(TopicForm topic){
        Call<String> call = HttpManager.getInstance().getService().addTopic(strId,topic);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    showToast("Success");
                } else {
                    try {
                        showToast(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
