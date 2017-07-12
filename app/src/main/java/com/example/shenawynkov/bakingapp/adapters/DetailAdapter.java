package com.example.shenawynkov.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shenawynkov.bakingapp.R;
import com.example.shenawynkov.bakingapp.models.Bake;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shenawynkov on 7/8/2017.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.viewHolder> {
    private List<Bake.steps> list;
    final  ClickListner mclickListner;
    Context mContext;


    public DetailAdapter(List<Bake.steps> list, ClickListner mclickListner,Context context) {
        mContext=context;
        this.list = list;
        this.mclickListner = mclickListner;
    }

    public  interface ClickListner
    {
        public void onItemClicked(int id);

    }


    public  class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         ImageView imageView;


        public TextView text;

        public viewHolder(View itemView) {

            super(itemView);



            text = (TextView) itemView.findViewById(R.id.text);
            imageView=(ImageView)itemView.findViewById(R.id.image) ;

            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            mclickListner.onItemClicked(list.get(getAdapterPosition()).getId());
        }
    }

    @Override
    public DetailAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new viewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        Bake.steps step=list.get(position);
        holder.text.setText(step.getShortDescription());
        if(!step.getThumbnailURL().isEmpty())
        Picasso.with(mContext).load(step.getThumbnailURL()).into(  holder.imageView);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
