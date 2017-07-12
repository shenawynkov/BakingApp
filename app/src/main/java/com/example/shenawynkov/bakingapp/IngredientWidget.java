package com.example.shenawynkov.bakingapp;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;

import com.example.shenawynkov.bakingapp.models.Bake;
import com.google.gson.Gson;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

     /*   CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object


        // Instruct the widget manager to update the widget
    */



    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(
                    context.getPackageName(),
                    R.layout.ingredient_widget
            );

            Intent intent = new Intent(context, MyWidgetRemoteViewsService.class);
            views.setRemoteAdapter(R.id.widget_list, intent);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.widget_list);
            appWidgetManager.updateAppWidget(appWidgetId, views);
    }}

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

