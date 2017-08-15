package com.minipg.fanster.armoury.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.object.User;

public class LoginActivity extends AppCompatActivity {
    private Button submit;
    private EditText etUser;
    private EditText etPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initInstance();
    }

    private void initInstance() {
        etUser = (EditText)findViewById(R.id.et_username);
        etPW = (EditText)findViewById(R.id.et_password);
        setHideKeyboard(etPW);
        setHideKeyboard(etUser);
        submit = (Button)findViewById(R.id.bt_go);
        login();
    }

    private void login() {
        User user = new User(etUser.getText().toString(),etPW.getText().toString());
        showToast(user.getUsername()+" "+user.getPassword());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToMain();
            }
        });
    }

    private void showToast(String username) {
        Toast.makeText(LoginActivity.this, username, Toast.LENGTH_SHORT).show();
    }

    private void intentToMain(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    private void setHideKeyboard(final EditText editText){
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            }
        });
    }
}
