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

                String tag = jsonStory.getString("tag");
                //storycs.setTag(tag);

                String type = jsonStory.getString("type");
                //storycs.setType(type);

                String description = jsonStory.getString("description");
                //storycs.setDescription(description);

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
