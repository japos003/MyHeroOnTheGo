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
public class executeStoryCategory2 {
    public ArrayList<StoryCat2> GetStoryCategory2(String x) {
        String URL = "http://198.199.112.105:5000/storyList/" + x;
        ArrayList<StoryCat2> storyC2 = new ArrayList<StoryCat2>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawStoryData = getrequest.request(URL);

            JSONArray storycatArray = new JSONArray(rawStoryData);

            for (int i =0; i < storycatArray.length(); i++) {

                StoryCat2 storycs2 = new StoryCat2();

                JSONObject jsonStory = storycatArray.getJSONObject(i);

                String storylink = jsonStory.getString("storylink");
                storycs2.setStoryLink(storylink);

                String name = jsonStory.getString("name");
                storycs2.setname(name);

                String imglink = jsonStory.getString("imglink");
                storycs2.setImglink(imglink);

                String description = jsonStory.getString("description");
                storycs2.setdescription(description);

                storyC2.add(storycs2);
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

        return storyC2;
    }
}
