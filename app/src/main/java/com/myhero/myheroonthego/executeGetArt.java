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
public class executeGetArt {
    public ArrayList<ArtUrl> GetArt(String x) {
        String URL = "http://198.199.112.105:5000/art/" + x;
        ArrayList<ArtUrl> ArtUrl = new ArrayList<ArtUrl>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawStoryData = getrequest.request(URL);

            JSONObject jsonStory = new JSONObject(rawStoryData);

            ArtUrl arturl = new ArtUrl();

            String title = jsonStory.getString("title");
            arturl.setTitle4(title);

            String caption = jsonStory.getString("caption");
            arturl.setCaption(caption);

            String artist = jsonStory.getString("artist");
            arturl.setArtist(artist);

            String imglink = jsonStory.getString("imglink");
            arturl.setImglink(imglink);

            String description = jsonStory.getString("description");
            arturl.setDescription8(description);

            ArtUrl.add(arturl);
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

        return ArtUrl;
    }
}
