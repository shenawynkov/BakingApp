package com.example.shenawynkov.bakingapp;


import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.shenawynkov.bakingapp.models.Bake;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.R.attr.fragment;

/**
 * Created by Shenawynkov on 7/8/2017.
 */

public class DetailActivity extends AppCompatActivity implements DetailFragments.onClickedItem {
    public static boolean   mTwoBane = false;
    Bake bake;
   List<Bake.ingredients> ingredientses;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    setContentView(R.layout.activity_detail);
if(savedInstanceState==null) {

    Intent intent = getIntent();
    bake  = (Bake) intent.getSerializableExtra("bake");
   ingredientses=bake.getIngredientses();
    Bundle bundle = new Bundle();
    bundle.putSerializable("bake", bake);
    FragmentManager fragmentManager = getSupportFragmentManager();
    DetailFragments detailFragments = new DetailFragments();
    detailFragments.setArguments(bundle);


    fragmentManager.beginTransaction().add(R.id.fragment, detailFragments, detailFragments.TAG).addToBackStack(null).commit();


}
        Gson gson = new Gson();
        String string = gson.toJson(bake);
        SharedPreferences sharedPref = getSharedPreferences(
             "pref" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("key", string);


        editor.commit();


if(findViewById(R.id.step_tablet)!=null)
    mTwoBane=true;

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {


        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    public void onClicked(Bundle bundle) {
        if(mTwoBane)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.step_tablet, stepFragment, stepFragment.TAG).addToBackStack(null).commit();

        }
        else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            StepFragment stepFragment = new StepFragment();
            stepFragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.fragment, stepFragment, stepFragment.TAG).addToBackStack(null).commit();
        }
    }
}
