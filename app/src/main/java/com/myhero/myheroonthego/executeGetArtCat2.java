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
public class executeGetArtCat2 {
    public ArrayList<ArtCat2> GetArtCategory2(String x) {
        String URL = "http://198.199.112.105:5000/artList/" + x + "/1";
        ArrayList<ArtCat2> artC2 = new ArrayList<ArtCat2>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawStoryData = getrequest.request(URL);

            JSONArray artcatArray = new JSONArray(rawStoryData);

            for (int i =0; i < artcatArray.length(); i++) {

                ArtCat2 artcs2 = new ArtCat2();

                JSONObject jsonStory = artcatArray.getJSONObject(i);

                String artist = jsonStory.getString("artist");
                artcs2.setArtist(artist);

                String imglink = jsonStory.getString("imglink");
                artcs2.setImglink(imglink);

                String cap = jsonStory.getString("cap");
                artcs2.setCap(cap);

                String name = jsonStory.getString("name");
                artcs2.setname(name);

                String artlink = jsonStory.getString("artlink");
                artcs2.setartLink(artlink);

                artC2.add(artcs2);
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

        return artC2;
    }
}
