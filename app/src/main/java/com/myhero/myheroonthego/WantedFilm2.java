package com.myhero.myheroonthego;

import java.io.Serializable;

/**
 * Created by Raul on 3/9/2015.
 */
public class WantedFilm2 implements Serializable {
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getFilmCap() {
        return Caption;
    }
    public void setFilmCap(String Caption) {
        this.Caption = Caption;
    }

    public String getFilmLink() {
        return movielink;
    }
    public void setFilmLink(String movielink) {
        this.movielink = movielink;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String Caption;
    private String link;
    private String movielink;
    private String name;

    @Override
    public String toString() {
        return Caption;
    }
}
