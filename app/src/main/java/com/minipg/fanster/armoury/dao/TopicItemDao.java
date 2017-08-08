package com.minipg.fanster.armoury.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Knot on 8/8/2017.
 */

public class TopicItemDao {

    @SerializedName("head") private String head;
    @SerializedName("description") private String description;
    @SerializedName("category") private String category;
    @SerializedName("link") private String link;
    @SerializedName("score") private Integer score;
    @SerializedName("poster") private String poster;
    @SerializedName("createDate") private Integer createDate;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }
}
