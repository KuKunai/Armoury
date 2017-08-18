package com.minipg.fanster.armoury.object;

/**
 * Created by Knot on 8/17/2017.
 */

public class TopicForm {
    String title;
    String link;
    String description;
    String category;

    public TopicForm(){

    }

    public TopicForm(String title, String link, String description, String category) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
}
