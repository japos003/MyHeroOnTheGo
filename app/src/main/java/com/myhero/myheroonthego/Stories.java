package com.myhero.myheroonthego;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;



public class Stories extends ActionBarActivity{

    public static final String KEY_WORD = "heroes_choice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);

        showList();

    }

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


