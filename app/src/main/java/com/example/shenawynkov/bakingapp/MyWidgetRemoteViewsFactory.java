package com.example.shenawynkov.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.shenawynkov.bakingapp.models.Bake;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Shenawynkov on 7/11/2017.
 */

public class MyWidgetRemoteViewsFactory  implements RemoteViewsService.RemoteViewsFactory {
    private List< Bake.ingredients> mingredients;
    private Context mContext;
    public MyWidgetRemoteViewsFactory(Context applicationContext, Intent intent) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        SharedPreferences sharedPref = mContext.getSharedPreferences(
                "pref", Context.MODE_PRIVATE);
        String string;
        string = sharedPref.getString("key", "");

            Gson gson = new Gson();
        Bake bake = gson.fromJson(string, Bake.class);
        if(bake!=null)
        mingredients = bake.getIngredientses();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
       if(mingredients==null)return 0;
        return mingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.ingred_widget_item);
            views.setTextViewText(R.id.widget_item, mingredients.get(i).getIngredient());

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

