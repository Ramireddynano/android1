package com.example.androidrrr.bakingappnd.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.example.androidrrr.bakingappnd.R;
import com.example.androidrrr.bakingappnd.RecipeDescription;
import com.example.androidrrr.bakingappnd.model.Respies;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private Context context;
    private List<Respies> recipes1;
    private int imagesM[];
    public RecipeAdapter(Context listener, List<Respies> recipes, int[] images)
    {
        context=listener;
        recipes1=recipes;
        imagesM=images;
    }

    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.respiedesign,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeAdapter.MyViewHolder holder, final int position)
    {
        Glide.with(context)
                .load(imagesM[position])
                .into(holder.recipeImg);
        holder.recipeName.setText(recipes1.get(position).getRespieName());

        holder.recipeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, RecipeDescription.class);
                intent.putExtra("recipeName",recipes1.get(position).getRespieName());
                String spo= String.valueOf(holder.getAdapterPosition());
                intent.putExtra("ppp",spo);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView recipeName;
        private ImageView recipeImg;
        public MyViewHolder(View itemView) {
            super(itemView);
            recipeName=itemView.findViewById(R.id.reci_name);
            recipeImg=itemView.findViewById(R.id.reci_image);
        }
    }
}
