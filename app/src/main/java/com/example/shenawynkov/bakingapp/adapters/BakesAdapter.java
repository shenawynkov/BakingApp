package com.example.shenawynkov.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shenawynkov.bakingapp.R;
import com.example.shenawynkov.bakingapp.models.Bake;

import java.util.List;



/**
 * Created by Shenawynkov on 7/8/2017.
 */

public class BakesAdapter extends RecyclerView.Adapter<BakesAdapter.viewHolder> {
    private List<Bake> list;
    final  ClickListner mclickListner;

    public BakesAdapter(List<Bake> list, ClickListner mclickListner) {
        this.list = list;
        this.mclickListner = mclickListner;
    }

    public  interface ClickListner
    {
        public void onItemClicked(Bake bake);

    }


    public  class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView text;

        public viewHolder(View itemView) {

            super(itemView);



            text = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            mclickListner.onItemClicked(list.get(getAdapterPosition()));
        }
    }

    @Override
    public BakesAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BakesAdapter.viewHolder holder, int position) {
        Bake bake=list.get(position);
        holder.text.setText(bake.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
