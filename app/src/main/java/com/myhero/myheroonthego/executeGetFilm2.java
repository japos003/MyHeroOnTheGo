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
public class executeGetFilm2 {
    public ArrayList<WantedFilm2> GetFilm2(String x) {
        //make an http request to get the list of all art tags
        //and then parse the data into a java format and then return
        String URL = "http://198.199.112.105:5000/movie/" + x;
        ArrayList<WantedFilm2> WFilm2 = new ArrayList<WantedFilm2>();
        GetRequest getrequest = new GetRequest();
        try {
            String rawFilmData = getrequest.request(URL);

            JSONObject jsonFilms = new JSONObject(rawFilmData);

            WantedFilm2 wfilm2 = new WantedFilm2();

            String filmlink = jsonFilms.getString("movielink");
            wfilm2.setFilmLink(filmlink);

            String caption = jsonFilms.getString("caption");
            wfilm2.setFilmCap(caption);

            String name = jsonFilms.getString("name");
            wfilm2.setName(name);

            String link = jsonFilms.getString("link");
            wfilm2.setLink(link);

            WFilm2.add(wfilm2);

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

        return WFilm2;
    }
}
