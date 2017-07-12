package com.example.shenawynkov.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shenawynkov.bakingapp.R;
import com.example.shenawynkov.bakingapp.models.Bake;

import java.util.List;

/**
 * Created by Shenawynkov on 7/8/2017.
 */

public class IngradiantAdapter extends RecyclerView.Adapter<IngradiantAdapter.viewHolder> {
    private List<Bake.ingredients> list;
    final  ClickListner mclickListner;

    public IngradiantAdapter(List<Bake.ingredients> list, ClickListner mclickListner) {
        this.list = list;
        this.mclickListner = mclickListner;
    }

    public  interface ClickListner
    {
        public void onItemClicked(Bake.ingredients ingredients);

    }


    public  class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView ingredient,quantity,measure;

        public viewHolder(View itemView) {

            super(itemView);



            ingredient = (TextView) itemView.findViewById(R.id.ingrediant);
            quantity=(TextView)itemView.findViewById(R.id.quantity);
            measure=(TextView)itemView.findViewById(R.id.measure);
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            mclickListner.onItemClicked(list.get(getAdapterPosition()));
        }
    }

    @Override
    public IngradiantAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_item, parent, false);

        return new viewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Bake.ingredients ingredients=list.get(position);
        holder.ingredient.setText(ingredients.getIngredient());
        holder.measure.setText(ingredients.getMeasure());
        holder.quantity.setText(ingredients.getQuantity()+"");



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
