package com.myhero.myheroonthego;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Art extends ActionBarActivity {
    private String y = "";
    private ArrayList<AllArt> art;
    private ArrayList<ArtCat> artcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_art);
        //once the user click on the art button
        //start to find all art tags
        SearchAllArt saa = new SearchAllArt();
        saa.execute();
    }

    class SearchAllArt extends AsyncTask<String, Integer, ArrayList<AllArt2>> {
        //call to get all a list of all art tags
        @Override
        protected ArrayList<AllArt2> doInBackground(String... params) {
            executeGetAllArt getallart = new executeGetAllArt();
            art = getallart.GetAllArt();
            executeGetAllArt2 getallart2 = new executeGetAllArt2();
            ArrayList<AllArt2> art2 = getallart2.GetAllArt2();
            return art2;
        }

        @Override
        protected void onPostExecute(ArrayList<AllArt2> allart2) {
            //once we get all art tags, call to display as a list
            ArrayAdapter<AllArt2> artAdapter2 = new ArrayAdapter<AllArt2>(Art.this, android.R.layout.simple_list_item_1, allart2);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_art);
            registerClickCallback();
            populateListView(artAdapter2);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    class SearchArtCat extends AsyncTask<String, Integer, ArrayList<ArtCat2>> {
        //call to get all a list of all art tags
        @Override
        protected ArrayList<ArtCat2> doInBackground(String... params) {
            executeGetArtCat getartcat = new executeGetArtCat();
            artcat = getartcat.GetArtCategory(y);
            executeGetArtCat2 getartcat2 = new executeGetArtCat2();
            ArrayList<ArtCat2> art2 = getartcat2.GetArtCategory2(y);
            return art2;
        }

        @Override
        protected void onPostExecute(ArrayList<ArtCat2> artcat2) {
            //once we get all art tags, call to display as a list
            ArrayAdapter<ArtCat2> artAdapter2 = new ArrayAdapter<ArtCat2>(Art.this, android.R.layout.simple_list_item_1, artcat2);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_art);
            registerClickCallback();
            populateListView2(artAdapter2);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    private void populateListView(ArrayAdapter<AllArt2> c) {
        //set the view to display all art tags
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(c);
    }

    private void populateListView2(ArrayAdapter<ArtCat2> d) {
        //set the view to display all art tags
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(d);
    }

    private void registerClickCallback() {
        //this function determines if the user clicks
        //a part of the list
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String tosplit = " ";
                if(art != null && !art.isEmpty()) {
                    tosplit = art.get(position).toString();
                }
                else if (artcat != null && !art.isEmpty()) {
                    tosplit = artcat.get(position).toString();
                }
                if (tosplit.contains("~")) {
                    String[] parts = tosplit.split("~");
                    String tag = parts[0];
                    if (tag.contains(":")) {
                        String[] xparts = tag.split(":");
                        String tag2 = xparts[1];
                        if(tosplit.contains("artlink:")) {
                            y = tag2;
                            art.clear();
                            SearchArtCat sac = new SearchArtCat();
                            sac.execute();
                        }
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.submitArt) {
            startActivity(new Intent(Art.this, SubmitArt.class));
        }

        return super.onOptionsItemSelected(item);
    }

    //ART
    public void gotoFilms(View view){
        startActivity(new Intent(Art.this, Films.class));
    }

    //STORIES
    public void gotoStories(View view){
        startActivity(new Intent(Art.this, Stories.class));
    }
}
