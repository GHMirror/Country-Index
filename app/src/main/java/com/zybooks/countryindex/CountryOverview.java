package com.zybooks.countryindex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

public class CountryOverview extends AppCompatActivity {

    String TAG = "CountryOverview";
    Countries countryInfo;
    FavoriteDatabase database;

    String countryName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_overview);


        String country;
        String countryJson;
        String isFavStr = "";
        database = new FavoriteDatabase(this);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                country= null;
            } else {
                country= extras.getString("countryName");
                isFavStr = extras.getString("isFavorite");
                countryJson = extras.getString("country");

                countryName = country;

                Gson gson = new Gson();
                countryInfo = gson.fromJson(countryJson, Countries.class);
            }
        } else {
            country = (String) savedInstanceState.getSerializable("countryName");
            isFavStr = (String) savedInstanceState.getSerializable("isFavorite");
            countryJson = (String) savedInstanceState.getSerializable("country");
            Gson gson = new Gson();
            countryInfo = gson.fromJson(countryJson, Countries.class);
        }

        Log.d(TAG, "onCreate: " + countryInfo);



        ImageView flagImage = findViewById(R.id.flagImage);
        ImageView heart = findViewById(R.id.heart);
        TextView countryName = findViewById(R.id.countryName);

        if(isFavStr.equals("yes")) {
            heart.setImageResource(R.drawable.fullheart);
        }

        TextView capitalValue = findViewById(R.id.capitalValue);
        TextView populationValue = findViewById(R.id.populationValue);
        TextView currencyValue = findViewById(R.id.currencyValue);
        TextView religionsList = findViewById(R.id.religionsList);
        TextView languagesList = findViewById(R.id.languagesList);
        TextView locationList = findViewById(R.id.locationList);
        TextView nationalDishList = findViewById(R.id.nationalDishList);
        TextView govermentList = findViewById(R.id.governmentList);

        String countryNameString = country;


        String capitalString = countryInfo.getCapital();
        String populationString = countryInfo.getPopulation();
        String currencyString = countryInfo.getCurrency();
        String religionsString = countryInfo.getReligion();
        String languagesString = countryInfo.getLanguage();
        String region = countryInfo.getRegion();
        String nationalDishes = countryInfo.getFoods();
        String continent = countryInfo.getContinent();
        String government = countryInfo.getGovernment();

        String location = continent + ", " + region;

        String flagKeyString = countryInfo.getFlagKey();
        String flagUri = flagKeyString.toLowerCase()+".png";

        Context context = getApplicationContext();

        Drawable flagImg = null;
        try {
            flagImg = Drawable.createFromStream(context.getAssets().open(flagUri), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "getView: " + flagImg);

        flagImage.setImageDrawable(flagImg);
        countryName.setText(countryNameString);
        capitalValue.setText(capitalString);
        populationValue.setText(populationString);
        currencyValue.setText(currencyString);
        languagesList.setText(languagesString);
        locationList.setText(location);
        religionsList.setText(religionsString);
        nationalDishList.setText(nationalDishes);
        govermentList.setText(government);
    }

    public void goToMap(View view) {

        Bundle extras = getIntent().getExtras();
        String country= extras.getString("countryName");
        String isFavStr = extras.getString("isFavorite");
        String countryJson = extras.getString("country");
        Context ctx = this;
        Intent intent = new Intent(ctx, MapsActivity.class);
        intent.putExtra("country", countryJson);
        intent.putExtra("countryName", country);
        intent.putExtra("isFavorite", isFavStr);
        startActivity(intent);
    }

    public void onBackClicked(View view) {
        Context ctx = this;
        Intent intent = new Intent(ctx, MainActivity.class);
        startActivity(intent);
    }

    public void overviewFavorite(View view) {
        TextView countryName = findViewById(R.id.countryName);
        ImageView heart = findViewById(R.id.heart);
        String country = countryName.getText().toString();
        if (database.isFavorite(country)) {
            database.delete(country);
            heart.setImageResource(R.drawable.emptyheart);
        } else {
            database.insert(country);
            heart.setImageResource(R.drawable.fullheart);
        }

    }

    public void onLanguageClicked(View view) {
        Context ctx = this;
        Intent intent = new Intent(ctx, DetailedCountryInformation.class);
        intent.putExtra("LANGUAGES", countryInfo.getLanguage());
        intent.putExtra("COUNTRY", countryName);

        startActivity(intent);
    }

    public void onReligionClicked(View view) {
        Context ctx = this;
        Intent intent = new Intent(ctx, DetailedCountryInformation.class);
        intent.putExtra("RELIGIONS", countryInfo.getLanguage());
        intent.putExtra("COUNTRY", countryName);

        startActivity(intent);
    }

    public void onFoodClicked(View view) {
        Context ctx = this;
        Intent intent = new Intent(ctx, DetailedCountryInformation.class);
        intent.putExtra("FOODS", countryInfo.getLanguage());
        intent.putExtra("COUNTRY", countryName);

        startActivity(intent);
    }
}
