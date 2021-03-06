package com.myhero.myheroonthego;

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

import java.util.ArrayList;


public class Films extends ActionBarActivity {

    private ArrayList<AllFilms> films;
    private ArrayList<WantedFilm> fm;
    private String filmtag = " ";
    private String filmlink = " ";

    ProgressDialog pd;
    VideoView vv;
    String VideoTitle = " ";
    // placeholder for fetching video description
    String VideoDescription = " ";
    // placeholder for fetching creator of video
    String quote = "\"";

    TextView titleView;
    TextView descView;

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

    class SearchAllFilms extends AsyncTask<String, Integer, ArrayList<AllFilms2>> {
        //call to get a list of all story tags
        @Override
        protected ArrayList<AllFilms2> doInBackground(String... params) {
            executeGetAllFilms2 getallfilms2 = new executeGetAllFilms2();
            executeGetAllFilms getallfilms = new executeGetAllFilms();
            //put the list of stories into an array
            ArrayList<AllFilms2> films2 = getallfilms2.GetAllMovies2();
            films = getallfilms.GetAllMovies();
            return films2;
        }

        @Override
        protected void onPostExecute(ArrayList<AllFilms2> allFilms2) {
            //once we get all stories, call to display as a list
            ArrayAdapter<AllFilms2> filmAdapter = new ArrayAdapter<AllFilms2>(Films.this, android.R.layout.simple_list_item_1, allFilms2);
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


    class SearchFilm extends AsyncTask<String, Integer, ArrayList<WantedFilm2>> {
        //call to get a list of all story tags
        @Override
        protected ArrayList<WantedFilm2> doInBackground(String... params) {
            executeGetFilm2 getfilm2 = new executeGetFilm2();
            executeGetFilm getfilm = new executeGetFilm();
            //put the list of stories into an array
            ArrayList<WantedFilm2> film2 = getfilm2.GetFilm2(filmtag);
            fm = getfilm.GetFilm(filmtag);
            return film2;
        }

        @Override
        protected void onPostExecute(ArrayList<WantedFilm2> wantedfilm2) {
            //once we get all stories, call to display as a list
            ArrayAdapter<WantedFilm2> filmAdapter = new ArrayAdapter<WantedFilm2>(Films.this, android.R.layout.simple_list_item_1, wantedfilm2);
            setProgressBarIndeterminateVisibility(false);
            //setContentView(R.layout.activity_films);
            //this function is to see if user clicks on anything in the list
            //registerClickCallback();
            //This function is to make the list of story tags visible
            //populateListView2(filmAdapter);
            String tosplit4 = fm.get(0).toString();
            String[] parts8 = tosplit4.split("~");
            String desc = parts8[0];
            String fl = parts8[1];
            String title = parts8[2];
            filmlink = fl;
            VideoDescription = desc;
            VideoTitle = title;
            //System.out.println(filmlink); //for debugging to see what filmlink is

            //Ryan's code
            setContentView(R.layout.activity_video);

            titleView = (TextView) findViewById(R.id.title);
            //titleView.setText(quote + VideoTitle + quote + "\n" + by + "\n" + author);\
            titleView.setText(quote + VideoTitle + quote);

            descView = (TextView) findViewById(R.id.description);
            descView.setText(VideoDescription);
            descView.setMovementMethod(new ScrollingMovementMethod());

            vv = (VideoView) findViewById(R.id.VideoView);

            pd = new ProgressDialog(Films.this);
            pd.setTitle("MyHero on the Go Videos");
            pd.setMessage("Buffering...");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();

            try {
                MediaController mc = new MediaController(Films.this);
                mc.setAnchorView(vv);

                Uri video = Uri.parse(filmlink);
                vv.setMediaController(mc);
                vv.setVideoURI(video);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            vv.requestFocus();
            vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    pd.dismiss();
                    vv.start();
                }
            });
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    private void populateListView(ArrayAdapter<AllFilms2> a) {
        //this is to set the view to display the list of stroy tags
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(a);
    }

    private void populateListView2(ArrayAdapter<WantedFilm2> a) {
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
                //String message ="Which is string: " + textView.getText().toString();
                //String tosplit = textView.getText().toString();
                String tosplit = " ";
                if(films != null && !films.isEmpty()) {
                    tosplit = films.get(position).toString();
                }
                if(fm != null && !fm.isEmpty()) {
                    tosplit = fm.get(position).toString();
                }
                //if (textView.getText().toString().contains("~")) {
                if (tosplit.contains("~")) {
                    String[] parts = tosplit.split("~");
                    String tag = parts[0];
                    if (tag.contains(":")) {
                        if(tosplit.contains("name:")) {
                            filmtag = films.get(position).getFilmLink().toString();
                            films.clear();
                            SearchFilm sf = new SearchFilm();
                            sf.execute();

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

    //ART
    public void gotoArt(View view){
        startActivity(new Intent(Films.this, Art.class));
    }

    //STORIES
    public void gotoStories(View view){
        startActivity(new Intent(Films.this, Stories.class));
    }
}
