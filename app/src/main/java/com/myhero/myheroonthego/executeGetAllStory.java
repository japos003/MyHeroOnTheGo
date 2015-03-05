package com.myhero.myheroonthego;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Raul Madrigal Jr on 3/4/2015.
 */
public class executeGetAllStory {

    public ArrayList<AllStories> GetAllStory() {
        String URL = "http://198.199.112.105:5000/getAllStory";
        ArrayList<AllStories> allstory = new ArrayList<AllStories>();
        GetRequest getrequest = new GetRequest();
        try {

            String rawStoryData = getrequest.request(URL);

            JSONArray allstoryArray = new JSONArray(rawStoryData);

            for (int i =0; i < allstoryArray.length(); i++) {

                AllStories story = new AllStories();

                JSONObject jsonStory = allstoryArray.getJSONObject(i);

                String tag = jsonStory.getString("tag");
                story.setTag(tag);

                String type = jsonStory.getString("type");
                story.setType(type);

                String description = jsonStory.getString("description");
                story.setDescription(description);

                allstory.add(story);
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

        return allstory;
    }

}
