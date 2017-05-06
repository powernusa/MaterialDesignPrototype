package com.powernusa.andy.mycheese;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class CheeseDetailActivity extends AppCompatActivity {
    public static final String EXTRA_CHEESE_NAME = "extra_cheese_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        if(getIntent() != null){
            String cheeseString = getIntent().getStringExtra(EXTRA_CHEESE_NAME);
            //Toast.makeText(this,"Passed string: " + cheeseString,Toast.LENGTH_SHORT).show();
            getSupportActionBar().setTitle(cheeseString);
        }

        loadBackdrop((ImageView) findViewById(R.id.backdrop));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        setupFAB(fab);


    }

    private void loadBackdrop(ImageView imageview){
        Picasso.with(this)
                .load(Cheeses.getRandomCheeseDrawable())
                .into(imageview);
    }


    /**
     * *********************************************************************************************
     *  setting up
     *
     * *********************************************************************************************
     */
    private void setupFAB(FloatingActionButton fab){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}