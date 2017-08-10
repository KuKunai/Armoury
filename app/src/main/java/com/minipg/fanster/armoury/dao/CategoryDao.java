package com.minipg.fanster.armoury.dao;

/**
 * Created by Knot on 8/10/2017.
 */

public class CategoryDao {
    private String cateName;

    public CategoryDao(String cateName){
        this.cateName = cateName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
