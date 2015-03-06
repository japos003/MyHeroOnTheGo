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
public class executeGetStory {
    public ArrayList<StoryDetails> GetStory(String y) {
        //make an http request to get the list of all story tags
        //and then parse the data into a java format and then return
        String URL = "http://198.199.112.105:5000/story/" + y;
        ArrayList<StoryDetails> story = new ArrayList<StoryDetails>();
        GetRequest getrequest = new GetRequest();
        try {
            String rawStoryData = getrequest.request(URL);

            JSONObject storyraw = new JSONObject(rawStoryData);

            JSONArray storyArray = storyraw.getJSONArray("content");

            for (int i =0; i < storyArray.length(); i++) {

                StoryDetails storydetails = new StoryDetails();

                JSONObject jsonStory = storyArray.getJSONObject(i);

                if(jsonStory.toString().contains("text")) {
                    String text = jsonStory.getString("text");
                    storydetails.setText(text);
                    story.add(storydetails);
                }

                /*
                else {
                    String description = jsonStory.getString("description");
                    storydetails.setDescription3(description);

                    String kind = jsonStory.getString("kind");
                    storydetails.setKind(kind);

                    String link = jsonStory.getString("link");
                    storydetails.setLink(link);
                }
                */

                //story.add(storydetails);
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

        return story;
    }
}
