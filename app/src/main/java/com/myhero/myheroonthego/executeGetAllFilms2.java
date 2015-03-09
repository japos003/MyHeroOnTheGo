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
public class executeGetAllFilms2 {
    public ArrayList<AllFilms2> GetAllMovies2() {
        //make an http request to get the list of all art tags
        //and then parse the data into a java format and then return
        String URL = "http://198.199.112.105:5000/getAllMovie";
        ArrayList<AllFilms2> afilms = new ArrayList<AllFilms2>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawFilmData = getrequest.request(URL);

            JSONArray allfilmsArray = new JSONArray(rawFilmData);

            for (int i =0; i < allfilmsArray.length(); i++) {

                AllFilms2 allfilms = new AllFilms2();

                JSONObject jsonFilms = allfilmsArray.getJSONObject(i);

                String filmlink = jsonFilms.getString("movielink");
                allfilms.setFilmLink(filmlink);

                String name = jsonFilms.getString("name");
                allfilms.setName(name);

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
