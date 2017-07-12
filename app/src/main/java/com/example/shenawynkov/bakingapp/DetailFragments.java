package com.example.shenawynkov.bakingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shenawynkov.bakingapp.adapters.DetailAdapter;
import com.example.shenawynkov.bakingapp.models.Bake;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shenawynkov on 7/8/2017.
 */

public class DetailFragments extends Fragment implements DetailAdapter.ClickListner{
    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";
    RecyclerView recyclerView;

    public final static String TAG= "detail";
    private FragmentActivity myContext;
    Bake bake;
    List<Bake.steps> list;
    DetailAdapter detailAdapter;
    List<Bake.ingredients> ingredientses;
    boolean mTwoBane;
public  onClickedItem callback;

       public  interface onClickedItem{
           public void onClicked(Bundle bundle);
       }
    public  DetailFragments()
    {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v=  inflater.inflate(R.layout.fragment_detail, container, false);
         bake=(Bake)getArguments().getSerializable("bake");
      TextView textView=(TextView) v.findViewById(R.id.ingrediant);

       list=bake.getStepses();
        ingredientses=bake.getIngredientses();
        textView.setText(ingredientses.get(0).getIngredient()  );
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IngredientFregment ingredientFregment=new IngredientFregment();


                Bundle bundle = new Bundle();
                bundle.putSerializable("bake", bake);
                FragmentManager fragmentManager = myContext.getSupportFragmentManager();

               ingredientFregment.setArguments(bundle);

                fragmentManager.beginTransaction().replace(R.id.fragment,ingredientFregment,ingredientFregment.TAG).addToBackStack(null).commit();

            }
        });

        recyclerView =v.findViewById(R.id.recyclerSteps);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

         detailAdapter=new DetailAdapter(list,this,getContext());
        recyclerView.setAdapter(detailAdapter);

        return v;
    }

    @Override
    public void onItemClicked(int id) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("bake", bake);
        bundle.putInt("id",id);
      callback.onClicked(bundle);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {


            callback = (onClickedItem) context;
        }
        catch (Exception e)
        {}

    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());

    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null)
        {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

}


