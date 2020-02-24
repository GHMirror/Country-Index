package com.zybooks.countryindex;


import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class DetailedCountryInformation extends AppCompatActivity {

    private GestureDetector gdt;
    private static final int MIN_SWIPPING_DISTANCE = 50;
    private static final int THRESHOLD_VELOCITY = 50;
    int counter = 0;
    int i = 1;
    String countryName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_country_information);



        // basic information setting setup
        TextView topicView = (TextView) findViewById(R.id.topicView);
        TextView referenceView = (TextView) findViewById(R.id.referenceView);

        ImageView topicImage = findViewById(R.id.topicImage);

        TextView reference1 = (TextView) findViewById(R.id.reference1);


        TextView note = (TextView) findViewById(R.id.noteView);


        reference1.setMovementMethod(new ScrollingMovementMethod());
        gdt = new GestureDetector(new GestureListener());
        topicImage.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                return true;
            } });


        Bundle extras = getIntent().getExtras();
        String languages = extras.getString("LANGUAGES");
        String religions = extras.getString("RELIGIONS");
        String foods = extras.getString("FOODS");
        String country = extras.getString("COUNTRY");

        countryName = country;

        if ( foods != null) {
            counter = 1;
        }



        if (languages != null) {
            topicView.setText("Language Breakdown");
            AssetManager assetManager = getAssets();
            try {

                InputStream inputStream = assetManager.open("Language Pictures/" + country + ".png");
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                topicImage.setImageBitmap(bitmap);



                note.setText("Note: Graph only shows census of primary language spoken");
                referenceView.setText("Additional Info");


                InputStream input = assetManager.open("Language Pictures/" + country + ".txt");

                int size = input.available();
                byte[] buffer = new byte[size];
                input.read(buffer);
                input.close();

                // byte buffer into a string
                String text = new String(buffer);

                reference1.setText(text);

            }
            catch(IOException ex) {
                return;
            }

        }

        else if (religions != null) {
            topicView.setText("Religion Breakdown");
            AssetManager assetManager = getAssets();
            try {

                InputStream inputStream = assetManager.open("Religion Pictures/" + country + ".png");
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                topicImage.setImageBitmap(bitmap);

                note.setText("");
                referenceView.setText("");
                reference1.setText("");
                Linkify.addLinks(reference1, Linkify.WEB_URLS);
            }
            catch(IOException ex) {
                return;
            }

        }

        else {
            topicView.setText("National Dish");
            AssetManager assetManager = getAssets();
            try {

                InputStream inputStream = assetManager.open("Food Pictures/" + country + ".jpg");
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                topicImage.setImageBitmap(bitmap);

                referenceView.setText("Ingredients");
                note.setText("Note: There are many different ways of cooking this dish, thus appearance and/or ingredients may differ from what is shown");

                InputStream input = assetManager.open("Food Pictures/" + country + ".txt");

                int size = input.available();
                byte[] buffer = new byte[size];
                input.read(buffer);
                input.close();

                // byte buffer into a string
                String text = new String(buffer);

                reference1.setText(text);



            }
            catch(IOException ex) {
                return;
            }
        }


    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener
    {
        ImageView topicImage = findViewById(R.id.topicImage);

        TextView reference1 = (TextView) findViewById(R.id.reference1);

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            if ( counter == 1) {




                AssetManager assetManager = getAssets();

                if (e1.getX() - e2.getX() > MIN_SWIPPING_DISTANCE && Math.abs(velocityX) > THRESHOLD_VELOCITY)
                {

                    i = i +1;



                    try {

                        InputStream inputStream = assetManager.open("Food Pictures/" + countryName + i + ".jpg");
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        topicImage.setImageBitmap(bitmap);


                        InputStream input = assetManager.open("Food Pictures/" + countryName + i + ".txt");

                        int size = input.available();
                        byte[] buffer = new byte[size];
                        input.read(buffer);
                        input.close();

                        // byte buffer into a string
                        String text = new String(buffer);

                        reference1.setText(text);


                    }
                    catch(IOException ex) {
                        return false;
                    }
                }

                else if (e2.getX() - e1.getX() > MIN_SWIPPING_DISTANCE && Math.abs(velocityX) > THRESHOLD_VELOCITY)
                {

                    i = i - 1;

                    if (i == 1) {

                        try {

                            InputStream inputStream = assetManager.open("Food Pictures/" + countryName +  ".jpg");
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            topicImage.setImageBitmap(bitmap);


                            InputStream input = assetManager.open("Food Pictures/" + countryName +  ".txt");

                            int size = input.available();
                            byte[] buffer = new byte[size];
                            input.read(buffer);
                            input.close();

                            // byte buffer into a string
                            String text = new String(buffer);

                            reference1.setText(text);


                        }
                        catch(IOException ex) {
                            return false;
                        }

                    }
                    else {
                        try {

                            InputStream inputStream = assetManager.open("Food Pictures/" + countryName + i + ".jpg");
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            topicImage.setImageBitmap(bitmap);


                            InputStream input = assetManager.open("Food Pictures/" + countryName + i + ".txt");

                            int size = input.available();
                            byte[] buffer = new byte[size];
                            input.read(buffer);
                            input.close();

                            // byte buffer into a string
                            String text = new String(buffer);

                            reference1.setText(text);


                        }
                        catch(IOException ex) {
                            return false;
                        }
                    }
                    return false;
                }
            }


            return false;
        }
    }


}
