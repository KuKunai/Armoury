package com.minipg.fanster.armoury.activity;


import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.minipg.fanster.armoury.R;
import com.minipg.fanster.armoury.dao.LoginResponseItemDao;
import com.minipg.fanster.armoury.dao.RegisterResponseItemDao;
import com.minipg.fanster.armoury.manager.HttpManager;
import com.minipg.fanster.armoury.object.RegisterForm;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private FloatingActionButton fabCancle;
    private Button btnRegister;
    private EditText etName;
    private EditText etPW;
    private EditText etRPW;
    private EditText etUser;
    private RelativeLayout touchInterceptor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iniInstane();
    }

    private void iniInstane() {
        touchInterceptor = (RelativeLayout) findViewById(R.id.relativeLayoutRegister);
        fabCancle = (FloatingActionButton) findViewById(R.id.fabCancel);
        btnRegister = (Button) findViewById(R.id.bt_register);
        etName = (EditText) findViewById(R.id.et_name);
        etUser = (EditText) findViewById(R.id.et_username);
        etPW = (EditText) findViewById(R.id.et_password);
        etRPW = (EditText) findViewById(R.id.et_repeatpassword);

        initHideKeyboard(touchInterceptor);
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
        etRPW.addTextChangedListener(new TextWatcher() {
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

        etRPW.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_DONE) {
                    btnRegister.performClick();
                    handled = true;
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etRPW.getWindowToken(), 0);
                }
                return handled;
            }
        });

        fabCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean canRegister = true;
                if (etName.getText().toString().length() == 0) {
                    etName.setError("Name is required.");
                    canRegister = false;
                } else if (etName.getText().toString().length() < 4) {
                    etName.setError("Name must at least 4 letter");
                    canRegister = false;
                }
                if (etUser.getText().toString().length() == 0) {
                    etUser.setError("Username is required.");
                    canRegister = false;
                } else if (etUser.getText().toString().length() < 4) {
                    etUser.setError("Username must at least 4 letter");
                    canRegister = false;
                }
                if (etPW.getText().toString().length() == 0) {
                    etPW.setError("Password is required.");
                    canRegister = false;
                } else if (etPW.getText().toString().length() < 4) {
                    etPW.setError("Password must at least 4 letter");
                    canRegister = false;
                } else if (etRPW.getText().toString().length() == 0) {
                    etRPW.setError("Repeat password is required.");
                    canRegister = false;
                }
                if (canRegister) {
                    if (etRPW.getText().toString().equals(etPW.getText().toString())) {
                        RegisterForm registerForm = new RegisterForm(etName.getText().toString(), etUser.getText().toString(), etPW.getText().toString());
                        register(registerForm);
                    } else {
                        etRPW.setError("Password not match");
                    }
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideKeyboard();
        return super.onTouchEvent(event);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private void register(final RegisterForm registerForm) {
        Call<RegisterResponseItemDao> call = HttpManager.getInstance().getService().register(registerForm);
        call.enqueue(new Callback<RegisterResponseItemDao>() {
            @Override
            public void onResponse(Call<RegisterResponseItemDao> call, Response<RegisterResponseItemDao> response) {
                if (response.isSuccessful()) {
                    RegisterResponseItemDao dao = response.body();
                    if(!dao.isCheckName()){
                        etName.setError(registerForm.getName()+" already exists");
                    }
                    if(!dao.isCheckUserName()){
                        etUser.setError(registerForm.getUsername()+" already exists");
                    }
                    if (dao.isCheckUserName() && dao.isCheckName()) {
                        showToast("Registration Success");
                        finish();
                    } else {
                        showToast("Registration Fail");
                    }
                    //register

                } else {
                    try {
                        showToast(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseItemDao> call, Throwable t) {
                showToast("Register Fail");
            }
        });
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

    private void showToast(String username) {
        Toast.makeText(RegisterActivity.this, username.toString(), Toast.LENGTH_SHORT).show();
    }

    private void initHideKeyboard(RelativeLayout touchInterceptor) {
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setHideKeyboard(etUser,v,event);
                    setHideKeyboard(etPW,v,event);
                    setHideKeyboard(etRPW,v,event);
                    setHideKeyboard(etName,v,event);
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
