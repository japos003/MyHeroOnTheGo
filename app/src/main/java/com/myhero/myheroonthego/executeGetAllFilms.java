package com.myhero.myheroonthego;

/**
 * Created by Jon Apostol on 3/6/2015.
 */

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class executeGetAllFilms {
    public ArrayList<AllFilms> GetAllMovies() {
        //make an http request to get the list of all art tags
        //and then parse the data into a java format and then return
        String URL = "http://198.199.112.105:5000/getAllMovie";
        ArrayList<AllFilms> afilms = new ArrayList<AllFilms>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawFilmData = getrequest.request(URL);

            JSONArray allfilmsArray = new JSONArray(rawFilmData);

            for (int i =0; i < allfilmsArray.length(); i++) {

                AllFilms allfilms = new AllFilms();

                JSONObject jsonFilms = allfilmsArray.getJSONObject(i);

                String filmlink = jsonFilms.getString("movielink");
                allfilms.setFilmLink(filmlink);

                String name = jsonFilms.getString("name");
                allfilms.setName(name);

                String link = jsonFilms.getString("link");
                allfilms.setVideoLink(link);

                String caption = jsonFilms.getString("caption");
                allfilms.setVideoDesc(caption);

                afilms.add(allfilms);
            }

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

        return afilms;
    }
}
