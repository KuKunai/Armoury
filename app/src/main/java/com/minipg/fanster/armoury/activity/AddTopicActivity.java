package com.minipg.fanster.armoury.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.minipg.fanster.armoury.R;

/**
 * Created by Knot on 8/18/2017.
 */

public class AddTopicActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDesc;
    private EditText etLink;
    private RelativeLayout relativeLayoutAddTopic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtopic);
        initInstance();
    }

    private void initInstance() {
        etTitle = (EditText) findViewById(R.id.et_title);
        etDesc = (EditText) findViewById(R.id.et_description);
        etLink = (EditText) findViewById(R.id.et_Link);
        relativeLayoutAddTopic = (RelativeLayout) findViewById(R.id.relativeLayoutAddTopic);
        initHideKeyboard(relativeLayoutAddTopic);
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
}
