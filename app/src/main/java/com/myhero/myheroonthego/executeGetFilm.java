package com.myhero.myheroonthego;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Raul on 3/9/2015.
 */
public class executeGetFilm {
    public ArrayList<WantedFilm> GetFilm(String x) {
        //make an http request to get the list of all art tags
        //and then parse the data into a java format and then return
        String URL = "http://198.199.112.105:5000/movie/" + x;
        ArrayList<WantedFilm> WFilm = new ArrayList<WantedFilm>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawFilmData = getrequest.request(URL);

            JSONObject jsonFilms = new JSONObject(rawFilmData);

            WantedFilm wfilm = new WantedFilm();

            String filmlink = jsonFilms.getString("movielink");
            wfilm.setFilmLink(filmlink);

            String caption = jsonFilms.getString("caption");
            wfilm.setFilmCap(caption);

            String name = jsonFilms.getString("name");
            wfilm.setName(name);

            String link = jsonFilms.getString("link");
            wfilm.setLink(link);

            WFilm.add(wfilm);

        }

        catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return WFilm;
    }
}
