package com.example.shenawynkov.bakingapp;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Shenawynkov on 7/11/2017.
 */

public class MyWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}