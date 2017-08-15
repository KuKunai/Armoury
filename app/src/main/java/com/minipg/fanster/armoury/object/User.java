package com.minipg.fanster.armoury.object;

import android.text.Editable;

/**
 * Created by Knot on 8/15/2017.
 */

public class User {
    String username;
    String password;

    public User (String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
