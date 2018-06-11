package com.example.androidrrr.bakingappnd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidrrr.bakingappnd.R;
import com.example.androidrrr.bakingappnd.model.IngredientModel;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>
{
   private Context ctx;
   private List<IngredientModel> ingModel1;
    public IngredientsAdapter(Context context, List<IngredientModel> ingModel)
    {
        ctx=context;
        ingModel1=ingModel;
    }

    @Override
    public IngredientsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(ctx).inflate(R.layout.ingredientsdesign,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsAdapter.MyViewHolder holder, int position)
    {
        holder.textIngre.setText("Ingredient: \t"+ingModel1.get(position).getIngredient1()
        +"\n"+"Measurment: \t"+ingModel1.get(position).getMeasure1()
                +"\n"+"Quntity: \t"+ingModel1.get(position).getQuantity1()+"\n");

    }

    @Override
    public int getItemCount() {
        return ingModel1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textIngre;
        public MyViewHolder(View itemView) {
            super(itemView);
            textIngre=itemView.findViewById(R.id.ing_text);
        }
    }
}
