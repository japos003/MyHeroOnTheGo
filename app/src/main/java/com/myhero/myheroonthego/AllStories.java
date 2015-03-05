package com.myhero.myheroonthego;

import java.io.Serializable;

/**
 * Created by Raul Madrigal Jr on 3/4/2015.
 */
public class AllStories implements Serializable {

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    private String tag;
    private String type;
    private String description;

    @Override
    public String toString() {
        return "Tag: " + tag  + "~ Type: " + type + "~ Description: " + description;
    }
}
