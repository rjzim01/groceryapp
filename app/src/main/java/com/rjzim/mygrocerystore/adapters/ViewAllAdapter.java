package com.rjzim.mygrocerystore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rjzim.mygrocerystore.R;
import com.rjzim.mygrocerystore.activities.DetailedActivity;
import com.rjzim.mygrocerystore.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> list;

    public ViewAllAdapter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.ratting.setText(list.get(position).getRatting());
        holder.price.setText(list.get(position).getPrice() + "/kg");

        if (list.get(position).getType().equals("egg")){
            holder.price.setText(list.get(position).getPrice() + "/dozen");
        }

        if (list.get(position).getType().equals("milk")){
            holder.price.setText(list.get(position).getPrice() + "/litre");
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailedActivity.class);
                i.putExtra("detail",list.get(holder.getAdapterPosition()));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description,price,ratting;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.view_img);
            description = itemView.findViewById(R.id.view_des);
            price = itemView.findViewById(R.id.view_price);
            ratting = itemView.findViewById(R.id.view_ratting);
            name = itemView.findViewById(R.id.view_name);
        }
    }
}
