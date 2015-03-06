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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_art);\
        SearchAllArt saa = new SearchAllArt();
        saa.execute();
    }

    class SearchAllArt extends AsyncTask<String, Integer, ArrayList<AllArt>> {
        //call to get all a list all art
        @Override
        protected ArrayList<AllArt> doInBackground(String... params) {

            executeGetAllArt getallart = new executeGetAllArt();
            ArrayList<AllArt> art = getallart.GetAllArt();
            return art;
        }

        @Override
        protected void onPostExecute(ArrayList<AllArt> allart) {
            //once we get all stories, call to display as a list
            ArrayAdapter<AllArt> artAdapter = new ArrayAdapter<AllArt>(Art.this, android.R.layout.simple_list_item_1, allart);
            setProgressBarIndeterminateVisibility(false);
            setContentView(R.layout.activity_art);
            registerClickCallback();
            populateListView(artAdapter);
        }
        //doesn't really do anything
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

    }

    private void populateListView(ArrayAdapter<AllArt> c) {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(c);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message ="Which is string: " + textView.getText().toString();
                String tosplit = textView.getText().toString();
                //Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                if (textView.getText().toString().contains("~")) {
                    String[] parts = tosplit.split("~");
                    String tag = parts[0];
                    if (tag.contains(":")) {
                        String[] xparts = tag.split(":");
                        String tag2 = xparts[1];
                        Toast.makeText(Art.this, tag2.trim(), Toast.LENGTH_LONG).show();
                        /*
                        if (textView.getText().toString().contains("Tag:")) {
                            storytag = tag2;
                            SearchStoryCategory ssc = new SearchStoryCategory();
                            ssc.execute();
                        }
                        else {
                            Toast.makeText(Art.this, tag2.trim(), Toast.LENGTH_LONG).show();
                        }
                        */
                    }
                    else {

                    }
                }
                else {
                    Toast.makeText(Art.this, message, Toast.LENGTH_LONG).show();
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
        //startActivity(new Intent(Art.this, Films.class));
    }

    //STORIES
    public void gotoStories(View view){
        startActivity(new Intent(Art.this, Stories.class));
    }
}
