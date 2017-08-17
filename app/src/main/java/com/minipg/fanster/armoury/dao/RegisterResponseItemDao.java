package com.minipg.fanster.armoury.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Knot on 8/17/2017.
 */

public class RegisterResponseItemDao {
    @SerializedName("checkName")
    @Expose
    private boolean checkName;
    @SerializedName("checkUsername")
    @Expose
    private boolean checkUserName;

    public RegisterResponseItemDao (){

    }

    public RegisterResponseItemDao (boolean checkName,boolean checkUserName){
        this.checkName = checkName;
        this.checkUserName = checkUserName;
    }

    public boolean isCheckName() {
        return checkName;
    }

    public void setCheckName(boolean checkName) {
        this.checkName = checkName;
    }

    public boolean isCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(boolean checkUserName) {
        this.checkUserName = checkUserName;
    }
}
