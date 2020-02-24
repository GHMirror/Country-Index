package com.zybooks.countryindex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String TAG = "MAIN";

    private Context ctx;
    FavoriteDatabase database;
    ArrayList<Countries> countryBundle;
    ArrayList<Countries> fullCountryBundle;
    countryAdapter adapt;
    static boolean all = true;
    static boolean fav = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        database = new FavoriteDatabase(ctx);
        //countryBundle = countryData.getList();
        //fullCountryBundle = countryData.getList();

        try {
            countryBundle = testGetJSONList();
            fullCountryBundle = testGetJSONList();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final ListView allCountries = (ListView) findViewById(R.id.countryList);

        adapt = new countryAdapter(this, R.layout.mainrowview, countryBundle);
        allCountries.setAdapter(adapt);

        allCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View rowview, int i, long l) {
                TextView singleCountry = (TextView) rowview.findViewById(R.id.countryName);
                String name = singleCountry.getText().toString();
                Boolean isFav = database.isFavorite(name);
                String isFavStr;
                if(isFav) {
                    isFavStr = "yes";
                } else {
                    isFavStr = "no";
                }
                Intent intent = new Intent(ctx, CountryOverview.class);
                Countries country = getCountryFromName(countryBundle, name);
                Gson gson = new Gson();
                String countryJson = gson.toJson(country);
                intent.putExtra("country", countryJson);
                intent.putExtra("countryName", name);
                intent.putExtra("isFavorite", isFavStr);
                startActivity(intent);
            }
        });
    }

    public void toggleFavorites(View view){
        String btn = view.getTag().toString();
        TextView allView = (TextView) findViewById(R.id.allButton);
        TextView favView = (TextView) findViewById(R.id.favButton);
        if (btn.equals("All")){
            allView.setClickable(false);
            favView.setClickable(true);
            countryBundle.clear();
            countryBundle.addAll(fullCountryBundle);
            adapt.notifyDataSetChanged();
            fav = false;
            all = true;
        }
        else{
            allView.setClickable(true);
            favView.setClickable(false);
            fav = true;
            all = false;
            ArrayList<Countries> favBundle = new ArrayList<>();
            for(int i = 0; i < fullCountryBundle.size(); i++){
                if(database.isFavorite(fullCountryBundle.get(i).getCountryName())){
                    favBundle.add(fullCountryBundle.get(i));
                }
            }
            countryBundle.clear();
            countryBundle.addAll(favBundle);
            adapt.notifyDataSetChanged();
        }
    }

    public void goToMap(View view) {
        Intent intent = new Intent(ctx, MapsActivity.class);
        startActivity(intent);
    }

    public void searchImages(View view){
        ArrayList<Countries> searchBundle = new ArrayList<>();
        TextView search = (TextView) findViewById(R.id.searchBar);
        String filter = search.getText().toString().toLowerCase();
        Log.i("Filter", filter);
        for(int i = 0; i < fullCountryBundle.size(); i++){
            if(fullCountryBundle.get(i).getCountryName().toLowerCase().contains(filter)){
                searchBundle.add(fullCountryBundle.get(i));
            }
        }
        countryBundle.clear();
        countryBundle.addAll(searchBundle);
        adapt.notifyDataSetChanged();
    }

    public Countries getCountryFromName(ArrayList<Countries> countryBundle, String name) {
        Log.d(TAG, "getCountryFromName: " + name);
        for (int i = 0; i < countryBundle.size(); i++) {
            if (countryBundle.get(i).getCountryName().equals(name)) {
                Log.d(TAG, "getCountryFromName: " + countryBundle.get(i));
                return countryBundle.get(i);
            }
        }
        Log.d(TAG, "getCountryFromName: hit null");
        return null;
    }

    public ArrayList<Countries> testGetJSONList() throws JSONException {

        Context ctx = this;
        String json = null;
        try {
            InputStream is = ctx.getAssets().open("countrydata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        ArrayList<Countries> countryList = new ArrayList<>();
        JSONArray mJsonArray = new JSONArray(json);
//    ]

        for (int i = 0; i < mJsonArray.length(); i++) {
            JSONObject mJsonObjectProperty = mJsonArray.getJSONObject(i);

            String name = mJsonObjectProperty.getString("country");
            String flagKey = mJsonObjectProperty.getString("flagKey");
            String capital = mJsonObjectProperty.getString("capital");
            String continent = mJsonObjectProperty.getString("continent");
            String currency = mJsonObjectProperty.getString("currency");
            String language = mJsonObjectProperty.getString("languages");
            String foods = mJsonObjectProperty.getString("foods");
            String religion = mJsonObjectProperty.getString("religion");
            String region = mJsonObjectProperty.getString("region");
            String population = mJsonObjectProperty.getString("population");
            String government = mJsonObjectProperty.getString("government");
            String latitude = mJsonObjectProperty.getString("latitude");
            String longitude = mJsonObjectProperty.getString("longitude");

            Log.d("JSON", "testGetJSONList: " + name);

            countryList.add(new Countries(name,
                    flagKey,
                    capital,
                    continent,
                    currency,
                    language,
                    foods,
                    religion,
                    region,
                    population,
                    government,
                    latitude,
                    longitude
                    ));
        }
        return countryList;
    }
}
