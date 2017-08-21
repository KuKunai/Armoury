package com.minipg.fanster.armoury.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.dao.LoginResponseItemDao;
import com.minipg.fanster.armoury.manager.HttpManager;
import com.minipg.fanster.armoury.object.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.minipg.fanster.armoury.R.id.fab;

public class LoginActivity extends AppCompatActivity {
    private Button submit;
    private EditText etUser;
    private EditText etPW;
    private User user;
    private FloatingActionButton register;
    private Boolean hasNextEdittext = false;
    private RelativeLayout touchInterceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkSharedPreferences();
        initInstance();
    }

    private void checkSharedPreferences() {
        SharedPreferences sharedPref = getSharedPreferences("sharedUserID",Context.MODE_PRIVATE);
        if(sharedPref.getString("userID",null)!=null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initInstance() {
        etUser = (EditText) findViewById(R.id.et_username);
        etPW = (EditText) findViewById(R.id.et_password);
//        setHideKeyboard(etPW,etUser);
//        setHideKeyboard(etUser,null);
        submit = (Button) findViewById(R.id.bt_go);
        register = (FloatingActionButton) findViewById(fab);
        touchInterceptor = (RelativeLayout)findViewById(R.id.relativeLayout);
        initHideKeyboard(touchInterceptor);
        initRegister();
        initLogin();
    }



    private void initRegister() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initLogin() {
        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String location_name = s.toString();
                if (location_name.matches(".*[^A-Za-z^0-9_].*")) {
                    etUser.setError("Only letters, numbers and underscore are allowed!");
                }
            }
        });
        etPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String location_name = s.toString();
                if (location_name.matches(".*[^A-Za-z^0-9_].*")) {
                    etPW.setError("Only letters, numbers and underscore are allowed!");
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User(etUser.getText().toString(), etPW.getText().toString());
                if (user.getPassword().toString().length() == 0
                        && user.getUsername().toString().length() == 0) {
                    etUser.setError("Username is required!");
                    etPW.setError("Password is required!");
                    showToast("Please insert Username & Password");
                } else {
                    if (user.getUsername().toString().length() == 0) {
                        etUser.setError("Username is required!");
                        showToast("Username is required!");
                    } else if (user.getPassword().toString().length() == 0) {
                        etPW.setError("Password is required!");
                        showToast("Password is required!");
                    } else {
                        sendLoginData(user);
                    }
                }
            }
        });
    }

    private void sendLoginData(User user) {
        Call<LoginResponseItemDao> call = HttpManager.getInstance().getService().login(user);
        call.enqueue(new Callback<LoginResponseItemDao>() {
            @Override
            public void onResponse(Call<LoginResponseItemDao> call, Response<LoginResponseItemDao> response) {
                if (response.isSuccessful()) {
                    LoginResponseItemDao dao = response.body();
                    if (dao.isAccess()) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        SharedPreferences sharedPref = getSharedPreferences("sharedUserID",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("userID", dao.getUser().getId());
                        editor.commit();
                        startActivity(intent);
                        finish();
                    } else {
                        showToast("Wrong user & password");
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
            public void onFailure(Call<LoginResponseItemDao> call, Throwable t) {

            }
        });
    }

    private void showToast(String username) {
        Toast.makeText(LoginActivity.this, username.toString(), Toast.LENGTH_SHORT).show();
    }

    private void initHideKeyboard(RelativeLayout touchInterceptor) {
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setHideKeyboard(etUser,v,event);
                    setHideKeyboard(etPW,v,event);
                }
                return false;
            }
        });
    }

    private void setHideKeyboard(final EditText editText,View v, MotionEvent event) {
        if (editText.isFocused()) {
            Rect outRect = new Rect();
            editText.getGlobalVisibleRect(outRect);
            if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                editText.clearFocus();
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }
}
