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
public class executeGetAllStory2 {
    public ArrayList<AllStories2> GetAllStory2() {
        //make an http request to get the list of all stroy tags
        //and then parse the data into a java format and then return
        String URL = "http://198.199.112.105:5000/getAllStory";
        ArrayList<AllStories2> allstory2 = new ArrayList<AllStories2>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawStoryData = getrequest.request(URL);

            JSONArray allstoryArray = new JSONArray(rawStoryData);

            for (int i =0; i < allstoryArray.length(); i++) {

                AllStories2 story = new AllStories2();

                JSONObject jsonStory = allstoryArray.getJSONObject(i);

                String tag = jsonStory.getString("tag");
                story.setTag(tag);

                String type = jsonStory.getString("type");
                story.setType(type);

                String description = jsonStory.getString("description");
                story.setDescription(description);

                allstory2.add(story);
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

        return allstory2;
    }
}
