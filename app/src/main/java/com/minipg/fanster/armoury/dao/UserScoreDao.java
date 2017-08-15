package com.minipg.fanster.armoury.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Knot on 8/15/2017.
 */

public class UserScoreDao {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount")
    @Expose
    private int amount;
}
