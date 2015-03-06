package com.myhero.myheroonthego;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Raul on 3/5/2015.
 */
public class executeGetAllArt {
    public ArrayList<AllArt> GetAllArt() {
        //make an http request to get the list of all art tags
        //and then parse the data into a java format and then return
        String URL = "http://198.199.112.105:5000/getArtMedium";
        ArrayList<AllArt> aart = new ArrayList<AllArt>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawStoryData = getrequest.request(URL);

            JSONArray allartArray = new JSONArray(rawStoryData);

            for (int i =0; i < allartArray.length(); i++) {

                AllArt allart = new AllArt();

                JSONObject jsonStory = allartArray.getJSONObject(i);

                String artlink = jsonStory.getString("artlink");
                allart.setArtLink(artlink);

                String name = jsonStory.getString("name");
                allart.setName2(name);

                String imglink = jsonStory.getString("imglink");
                allart.setImglink2(imglink);

                aart.add(allart);
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

        return aart;
    }
}
