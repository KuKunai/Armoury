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
    @SerializedName("shared")
    @Expose
    private List<UserScoreDao> shared = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalLiked() {
        return totalLiked;
    }

    public void setTotalLiked(int totalLiked) {
        this.totalLiked = totalLiked;
    }

    public List<UserScoreDao> getShared() {
        return shared;
    }

    public void setShared(List<UserScoreDao> shared) {
        this.shared = shared;
    }

}
