package com.minipg.fanster.armoury.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Knot on 8/16/2017.
 */

public class LoginResponseItemDao {
    @SerializedName("user")
    @Expose
    private UserDao user;
    @SerializedName("access")
    @Expose
    private boolean access;

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
