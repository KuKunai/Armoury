package com.minipg.fanster.armoury.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Knot on 8/15/2017.
 */

public class UserDao {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("totalLiked")
    @Expose
    private int totalLiked;
    @SerializedName("likeList")
    @Expose
    private List<String> likeList;
    @SerializedName("shared")
    @Expose
    private List<UserScoreDao> shared = null;

}
