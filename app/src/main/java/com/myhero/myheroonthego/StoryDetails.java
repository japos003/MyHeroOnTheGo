package com.myhero.myheroonthego;

import java.io.Serializable;

/**
 * Created by Raul on 3/5/2015.
 */
public class StoryDetails implements Serializable {
    public String getName4() {
        return name4;
    }
    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getkind() {
        return Kind;
    }
    public void setKind(String Kind) {
        this.Kind = Kind;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription3k() {
        return description3;
    }
    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    private String Kind;
    private String link;
    private String description3;
    private String text;
    private String name4;

    @Override
    public String toString() {
        //since the JSON array has an object for text and
        //Kind, Link, and Description
        //only display the stuff that isn't null
        if (text != null) {
            return text;
        }
        //else if (link != null) {

            //return "Link: " + link;
            //return "Kind:" + Kind  + "~link:" + link + "~description:" + description3;
        //}
        else {
            return name4 + "\n";
        }
    }
}
