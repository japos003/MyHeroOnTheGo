package com.myhero.myheroonthego;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Raul Madrigal Jr on 3/5/2015.
 */
public class executeStoryCategory {
    //make an http request to get a list of stories under a certain tag
    //and then parse the JSON array into a java array and return the results
    public ArrayList<StoryCat> GetStoryCategory(String x) {
        String URL = "http://198.199.112.105:5000/storyList/" + x;
        ArrayList<StoryCat> storyC = new ArrayList<StoryCat>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawStoryData = getrequest.request(URL);

            JSONArray storycatArray = new JSONArray(rawStoryData);

            for (int i =0; i < storycatArray.length(); i++) {

                StoryCat storycs = new StoryCat();

                JSONObject jsonStory = storycatArray.getJSONObject(i);

                String storylink = jsonStory.getString("storylink");
                storycs.setStoryLink(storylink);

                String name = jsonStory.getString("name");
                storycs.setname(name);

                String imglink = jsonStory.getString("imglink");
                storycs.setImglink(imglink);

                String description = jsonStory.getString("description");
                storycs.setdescription(description);

                storyC.add(storycs);
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

        return storyC;
    }

}
