package com.minipg.fanster.armoury.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initInstance();
    }

    private void initInstance() {
        etUser = (EditText) findViewById(R.id.et_username);
        etPW = (EditText) findViewById(R.id.et_password);
        setHideKeyboard(etPW);
        setHideKeyboard(etUser);
        submit = (Button) findViewById(R.id.bt_go);
        register = (FloatingActionButton) findViewById(fab);
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
                //TODO
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TODO
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
                //TODO
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TODO
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
                        //showToast(user.getUsername() + " " + user.getPassword());
                        sendLoginData(user);
                    }
                }
                //intentToMain();
            }
        });
    }

    private void sendLoginData(User user) {
        Call<LoginResponseItemDao> call = HttpManager.getInstance().getService().login(user);
        call.enqueue(new Callback<LoginResponseItemDao>() {
            @Override
            public void onResponse(Call<LoginResponseItemDao> call, Response<LoginResponseItemDao> response) {
                if(response.isSuccessful()){
                    LoginResponseItemDao dao = response.body();
                    if(dao.isAccess())
                        intentToMain();
                    else
                        showToast("Wrong user & password");
                }
                else {
                    try {
                        showToast("NOOB");
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

    private void intentToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void setHideKeyboard(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            }
        });
    }
}
