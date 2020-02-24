package com.zybooks.countryindex;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



public class countryAdapter extends ArrayAdapter<Countries> {
    private String TAG = "countryAdapter";
    ArrayList<Countries> list;
    FavoriteDatabase database;
    public countryAdapter(Context context, int resourceId, ArrayList<Countries> countries){
        super(context, resourceId, countries);
        list = countries;
        database = new FavoriteDatabase(context);
    }

    @Override
    public int getCount(){
        return super.getCount();
    }


    @Override
    public View getView(int pos, View newView, ViewGroup parent){

        Context context = parent.getContext();
        View updateView;
        LayoutInflater inflate = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        updateView = inflate.inflate(R.layout.mainrowview, null);
        ImageView flag = (ImageView) updateView.findViewById(R.id.flagImg);
        TextView name = (TextView) updateView.findViewById(R.id.countryName);
        ImageView fav = (ImageView) updateView.findViewById(R.id.favorite);
        name.setText(list.get(pos).getCountryName());
        String flagUri = list.get(pos).getFlagKey().toLowerCase()+".png";

        Log.d(TAG, "getView: flaguri is " + flagUri);

        Drawable flagImg = null;
        try {
            flagImg = Drawable.createFromStream(context.getAssets().open(flagUri), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "getView: " + flagImg);

        flag.setImageDrawable(flagImg);
        fav.setTag(R.string.index, pos);
        if(database.isFavorite(list.get(pos).getCountryName())){
            fav.setImageResource(R.drawable.fullheart);
            fav.setTag(R.string.star_tag, R.drawable.fullheart);
        }
        else{
            fav.setImageResource(R.drawable.emptyheart);
            fav.setTag(R.string.star_tag, R.drawable.emptyheart);
        }

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer currImage = (Integer) view.getTag(R.string.star_tag);
                Integer index = (Integer) view.getTag(R.string.index);
                LinearLayout row = (LinearLayout) ((ViewGroup) view.getParent());
                ImageView star = row.findViewById(R.id.favorite);
                TextView country = row.findViewById(R.id.countryName);
                String name = country.getText().toString();
                if(currImage == R.drawable.emptyheart){
                    star.setImageResource(R.drawable.fullheart);
                    star.setTag(R.drawable.fullheart);
                    database.insert(name);
                }
                else{
                    star.setImageResource(R.drawable.emptyheart);
                    star.setTag(R.drawable.emptyheart);
                    database.delete(name);
                    if(MainActivity.fav){
                        list.remove(index.intValue());
                    }
                }
                notifyDataSetChanged();
            }
        });
        return updateView;
    }

}
