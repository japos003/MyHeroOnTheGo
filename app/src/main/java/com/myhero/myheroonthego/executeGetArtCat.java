package com.myhero.myheroonthego;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Raul on 3/6/2015.
 */
public class executeGetArtCat {
    public ArrayList<ArtCat> GetArtCategory(String x) {
        String URL = "http://198.199.112.105:5000/artList/" + x + "/1";
        ArrayList<ArtCat> artC = new ArrayList<ArtCat>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawStoryData = getrequest.request(URL);

            JSONArray artcatArray = new JSONArray(rawStoryData);

            for (int i =0; i < artcatArray.length(); i++) {

                ArtCat artcs = new ArtCat();

                JSONObject jsonStory = artcatArray.getJSONObject(i);

                String storylink = jsonStory.getString("storylink");
                artcs.setStoryLink(storylink);

                String name = jsonStory.getString("name");
                artcs.setname(name);

                String imglink = jsonStory.getString("imglink");
                artcs.setImglink(imglink);

                String description = jsonStory.getString("description");
                artcs.setdescription(description);

                artC.add(artcs);
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

        return artC;
    }
}
