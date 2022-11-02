package com.rjzim.mygrocerystore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rjzim.mygrocerystore.R;
import com.rjzim.mygrocerystore.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapters extends RecyclerView.Adapter<RecommendedAdapters.ViewHolder> {

    Context context;
    private List<RecommendedModel> recommendedModelList;

    public RecommendedAdapters(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(recommendedModelList.get(position).getImg_url()).into(holder.recomImg);
        holder.name.setText(recommendedModelList.get(position).getName());
        holder.rating.setText(recommendedModelList.get(position).getRatting());
        holder.description.setText(recommendedModelList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recomImg;
        TextView name,description,rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recomImg = itemView.findViewById(R.id.recom_img);
            name = itemView.findViewById(R.id.recom_name);
            description = itemView.findViewById(R.id.recom_des);
            rating = itemView.findViewById(R.id.recom_rating);

        }
    }
}
