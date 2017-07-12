package com.example.shenawynkov.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shenawynkov.bakingapp.adapters.DetailAdapter;
import com.example.shenawynkov.bakingapp.adapters.IngradiantAdapter;
import com.example.shenawynkov.bakingapp.models.Bake;

import java.util.List;

/**
 * Created by Shenawynkov on 7/8/2017.
 */

public class IngredientFregment   extends Fragment implements IngradiantAdapter.ClickListner{
    public final static String TAG= "ingredient";

    IngradiantAdapter ingradiantAdapter;
    List <Bake.ingredients> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.ingredient_fragment, container, false);
        Bake bake=(Bake)getArguments().getSerializable("bake");
        RecyclerView recyclerView=v.findViewById(R.id.recycleringredient);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
   list=bake.getIngredientses();
        ingradiantAdapter=new IngradiantAdapter(list,this);
        recyclerView.setAdapter(ingradiantAdapter);


        return v;
    }


    @Override
    public void onItemClicked(Bake.ingredients ingredients) {

    }
}
