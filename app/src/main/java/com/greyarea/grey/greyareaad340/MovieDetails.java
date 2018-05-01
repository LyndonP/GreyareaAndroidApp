package com.greyarea.grey.greyareaad340;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity{
    private static final String TAG = "MovieDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar xToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(xToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Log.d(TAG, "Started program.");
        getGalleryIntent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.zombie_menu, menu);

        return true;
    }


    private void getGalleryIntent(){
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){
            Log.d(TAG,"Found intents.");
            String imageURL = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");
            String imageYear = getIntent().getStringExtra("image_year");
            String imageDirector = getIntent().getStringExtra("image_director");
            String imageDescription = getIntent().getStringExtra("image_description");
            setImage(imageURL, imageName, imageYear, imageDirector, imageDescription);
        }
    }

    private void setImage(String imageURL, String imageName, String imageYear, String imageDirector, String imageDescription){
        TextView name = findViewById(R.id.title);
        name.setText(imageName);

        TextView year = findViewById(R.id.year);
        year.setText(imageYear);

        TextView director = findViewById(R.id.director);
        director.setText(imageDirector);

        TextView description = findViewById(R.id.description);
        description.setText(imageDescription);

        ImageView image = findViewById(R.id.image);
        Glide.with(this).asBitmap().load(imageURL).into(image);
    }
}
