package com.myhero.myheroonthego;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Stories extends ActionBarActivity{

    //public static final String KEY_WORD = "heroes_choice";
    //variable to get Tag
    private String storytag = " ";
    //vatiable to get storylink
    private String storylink = " ";
    private ArrayList<AllStories> stories;
    private ArrayList<StoryCat> storyc;
    private String x = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //once user clicks on stories start to get all story tags
        if(stories != null) {
            stories.clear();
        }
        if (storyc != null) {
            storyc.clear();
        }

        SearchAllStory sas = new SearchAllStory();
        sas.execute();
    }

    class SearchAllStory extends AsyncTask<String, Integer, ArrayList<AllStories2>> {
        //call to get a list of all story tags
        @Override
        protected ArrayList<AllStories2> doInBackground(String... params) {

            executeGetAllStory getallstory = new executeGetAllStory();
            executeGetAllStory2 getallstory2 = new executeGetAllStory2();
            //put the list of stories into an array
            ArrayList<AllStories2> stories2 = getallstory2.GetAllStory2();
            stories = getallstory.GetAllStory();
            return stories2;
        }

        @Override
        protected void onPostExecute(ArrayList<AllStories2> allStories2) {
            //once we get all stories, call to display as a list
            ArrayAdapter<AllStories2> storyAdapter = new ArrayAdapter<AllStories2>(Stories.this, android.R.layout.simple_list_item_1, allStories2);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_stories);
            //this function is to see if user clicks on anything in the list
            registerClickCallback();
            //This function is to make the list of story tags visible
            populateListView(storyAdapter);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    class SearchStoryCategory extends AsyncTask<String, Integer, ArrayList<StoryCat2>> {
        //call to get a list of stories related to a tag
        @Override
        protected ArrayList<StoryCat2> doInBackground(String... params) {

            executeStoryCategory getstorycategory = new executeStoryCategory();
            storyc = getstorycategory.GetStoryCategory(storytag);
            executeStoryCategory2 getstorycategory2 = new executeStoryCategory2();
            ArrayList<StoryCat2> storyc2 = getstorycategory2.GetStoryCategory2(storytag);
            return storyc2;
        }

        @Override
        protected void onPostExecute(ArrayList<StoryCat2> Storiesca2) {
            //once we get the list of stories, we display them
            ArrayAdapter<StoryCat2> storycAdapter2 = new ArrayAdapter<StoryCat2>(Stories.this, android.R.layout.simple_list_item_1, Storiesca2);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_stories);
            registerClickCallback();
            populateListView2(storycAdapter2);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    class SearchStory extends AsyncTask<String, Integer, ArrayList<StoryDetails>> {
        //call to get a list of a story detail
        @Override
        protected ArrayList<StoryDetails> doInBackground(String... params) {
            executeGetStory getstory = new executeGetStory();
            ArrayList<StoryDetails> storyd = getstory.GetStory(storylink);
            return storyd;
        }

        @Override
        protected void onPostExecute(ArrayList<StoryDetails> Storyd) {
            //once we get the list of stories, we display them
            ArrayAdapter<StoryDetails> storydAdapter = new ArrayAdapter<StoryDetails>(Stories.this, android.R.layout.simple_list_item_1, Storyd);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_stories);
            registerClickCallback();
            populateListView3(storydAdapter);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    private void populateListView3(ArrayAdapter<StoryDetails> d) {
        //this sets the view to display the list of stories under
        //a certain tag
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(d);
    }

    private void populateListView2(ArrayAdapter<StoryCat2> b) {
        //this sets the view to display the list of stories under
        //a certain tag
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(b);
    }

    private void populateListView(ArrayAdapter<AllStories2> a) {
        //this is to set the view to display the list of stroy tags
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(a);
    }

    private void registerClickCallback() {
        //this functions tries to see if the user clicks on a certain part of the list
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String tosplit= " ";

                if(storyc != null && !storyc.isEmpty()) {
                    tosplit = storyc.get(position).toString();
                }
                else if (stories != null && !stories.isEmpty()) {
                    tosplit = stories.get(position).toString();
                }

                if (tosplit.contains("~")) {
                    //separate each part of the array with ~
                    //and split the string based on each ~
                    String[] parts = tosplit.split("~");
                    String tag = parts[0];

                    if (tag.contains(":")) {
                        String[] xparts = tag.split(":");
                        String tag2 = xparts[1];
                        if(tosplit.contains("Tag:")) {
                            storytag = tag2;
                            stories.clear();
                            SearchStoryCategory ssc = new SearchStoryCategory();
                            ssc.execute();
                        }
                        else if(tosplit.contains("storylink:")) {
                            storylink = tag2;
                            //set new sotry link and clear new array
                            storyc.clear();
                            SearchStory ss = new SearchStory();
                            ss.execute();
                        }
                        else {

                        }
                    }
                    else {

                    }
                }
                else {

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.submitStory) {
            startActivity(new Intent(Stories.this, SubmitStory.class));
        }

        return super.onOptionsItemSelected(item);
    }

    //VIDEOS
    public void gotoFilms(View view){
        //startActivity(new Intent(Art.this, Films.class));
    }

    //ART
    public void gotoArt(View view){
        startActivity(new Intent(Stories.this, Art.class));
    }
}


