package com.myhero.myheroonthego;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class Films extends ActionBarActivity {

    private ArrayList<AllFilms> films;
    //private String video_url = "http://198.199.112.105/";
    private String video_url;
    private String video_desc;
    private  String video_title;
    //private String filmtag = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        if(films != null) {
            films.clear();
        }

        SearchAllFilms saf = new SearchAllFilms();
        saf.execute();
    }

    class SearchAllFilms extends AsyncTask<String, Integer, ArrayList<AllFilms>> {
        //call to get a list of all story tags
        @Override
        protected ArrayList<AllFilms> doInBackground(String... params) {
            executeGetAllFilms getallfilms = new executeGetAllFilms();
            //put the list of stories into an array
            films = getallfilms.GetAllMovies();
            return films;
        }

        @Override
        protected void onPostExecute(ArrayList<AllFilms> allFilms2) {
            //once we get all stories, call to display as a list
            ArrayAdapter<AllFilms> filmAdapter = new ArrayAdapter<AllFilms>(Films.this, android.R.layout.simple_list_item_1, films);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_films);
            //this function is to see if user clicks on anything in the list
            registerClickCallback();
            //This function is to make the list of story tags visible
            populateListView(filmAdapter);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    private void populateListView(ArrayAdapter<AllFilms> a) {
        //this is to set the view to display the list of stroy tags
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(a);
    }

    private void registerClickCallback() {
        //this function determines if the user clicks
        //a part of the list
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message ="Which is string: " + textView.getText().toString();
                //String tosplit = textView.getText().toString();
                String tosplit = " ";
                String link = "";
                String desc = "";
                String title = "";

                if(films != null) {
                    tosplit = films.get(position).toString();
                    link = films.get(position).getLink();
                    video_url = link;
                    desc = films.get(position).getDesc();
                    video_desc = desc;
                    title = films.get(position).getTitle();
                    video_title = title;

                    Intent i = new Intent(Films.this, VideoActivity.class);
                    i.putExtra("video_url",link);
                    i.putExtra("desc", desc);
                    i.putExtra("title", title);
                    startActivity(i);
                }
                else {

                }
                //if (textView.getText().toString().contains("~")) {
                if (tosplit.contains("~")) {
                    String[] parts = tosplit.split("~");
                    String tag = parts[0];
                    if (tag.contains(":")) {
                        String[] xparts = tag.split(":");
                        String tag2 = xparts[1];
                       // Toast.makeText(Films.this, tag2.trim(), Toast.LENGTH_LONG).show();
                        //Toast.makeText(Films.this, link, Toast.LENGTH_LONG).show();
                    }
                    else {

                    }
                }
                else {
                    Toast.makeText(Films.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //ART
    public void gotoArt(View view){
        startActivity(new Intent(Films.this, Art.class));
    }

    //STORIES
    public void gotoStories(View view){
        startActivity(new Intent(Films.this, Stories.class));
    }
}
