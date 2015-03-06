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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //once user clicks on stories start to get all story tags
        SearchAllStory sas = new SearchAllStory();
        sas.execute();
        //setContentView(R.layout.activity_stories);
        //showList();

    }

    class SearchAllStory extends AsyncTask<String, Integer, ArrayList<AllStories>> {
        //call to get a list of all story tags
        @Override
        protected ArrayList<AllStories> doInBackground(String... params) {

            executeGetAllStory getallstory = new executeGetAllStory();
            //put the list of stories into an array
            ArrayList<AllStories> stories = getallstory.GetAllStory();
            return stories;
        }

        @Override
        protected void onPostExecute(ArrayList<AllStories> allStories) {
            //once we get all stories, call to display as a list
            ArrayAdapter<AllStories> storyAdapter = new ArrayAdapter<AllStories>(Stories.this, android.R.layout.simple_list_item_1, allStories);
            setProgressBarIndeterminateVisibility(false);
            String ta565g = allStories.get(1).toString();
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

    class SearchStoryCategory extends AsyncTask<String, Integer, ArrayList<StoryCat>> {
        //call to get a list of stories related to a tag
        @Override
        protected ArrayList<StoryCat> doInBackground(String... params) {

            executeStoryCategory getstorycategory = new executeStoryCategory();
            ArrayList<StoryCat> storyc = getstorycategory.GetStoryCategory(storytag);
            return storyc;
        }

        @Override
        protected void onPostExecute(ArrayList<StoryCat> Storiesca) {
            //once we get the list of stories, we display them
            ArrayAdapter<StoryCat> storycAdapter = new ArrayAdapter<StoryCat>(Stories.this, android.R.layout.simple_list_item_1, Storiesca);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_stories);
            registerClickCallback();
            populateListView2(storycAdapter);
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

    private void populateListView2(ArrayAdapter<StoryCat> b) {
        //this sets the view to display the list of stories under
        //a certain tag
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(b);
    }

    private void populateListView(ArrayAdapter<AllStories> a) {
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
                String message ="Which is string: " + textView.getText().toString();
                String tosplit = textView.getText().toString();
                //Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                if (textView.getText().toString().contains("~")) {
                    //separate each part of the array with ~
                    //and split the string based on each ~
                    String[] parts = tosplit.split("~");
                    String tag = parts[0];
                    if (tag.contains(":")) {
                        String[] xparts = tag.split(":");
                        String tag2 = xparts[1];
                        if (textView.getText().toString().contains("Tag:")) {
                            storytag = tag2;
                            SearchStoryCategory ssc = new SearchStoryCategory();
                            ssc.execute();
                        }
                        else if(textView.getText().toString().contains("storylink:")) {
                            storylink = tag2;
                            SearchStory ss = new SearchStory();
                            ss.execute();
                            //Toast.makeText(Stories.this, tag2.trim(), Toast.LENGTH_LONG).show();
                        }
                        else {
                           //Toast.makeText(Stories.this, tag2.trim(), Toast.LENGTH_LONG).show();
                        }
                    }
                    else {

                    }
                }
                else {
                    //Toast.makeText(Stories.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /*
    private void showList(){

        //LISTS
        final String[] listOfValues = new String[] { "Aids", "Angels", "Animals",
                "Artists", "Business", "Community", "Earthkeepers", "Explorers", "Faith",
                "Family", "Freedom", "Health", "Hero's Hero", "Heroes in the News"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfValues);

        //PUTS TO LISTVIEW
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                //GOES TO DIFFERENT PAGE BASED ON ID

                String nameOfChoice = listOfValues[(int)id];
                bundle.putString("name", nameOfChoice);
                Intent sendToSH = new Intent(Stories.this, StoriesHeroes.class);
                sendToSH.putExtras(bundle);

                startActivity(sendToSH);
            }
        });

    }
    */


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


