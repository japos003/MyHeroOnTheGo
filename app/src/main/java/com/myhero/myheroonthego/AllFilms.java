package com.myhero.myheroonthego;

/**
 * Created by Jon Apostol on 3/6/2015.
 */
public class AllFilms {
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

    private String name;
    private String movielink;

    @Override
    public String toString() {
        return "name:" + name  + "~ movielink:" + movielink;
    }
}
