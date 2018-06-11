package com.example.androidrrr.bakingappnd.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidrrr.bakingappnd.MediaActivity;
import com.example.androidrrr.bakingappnd.MediaFragment;
import com.example.androidrrr.bakingappnd.R;
import com.example.androidrrr.bakingappnd.RecipeDescription;
import com.example.androidrrr.bakingappnd.model.Steps;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    private Context context;
    private List<Steps> steps1;
    public StepsAdapter(Context recipeDescriptionClass, List<Steps> steps)
    {
        context=recipeDescriptionClass;
        steps1=steps;
    }

    @Override
    public StepsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.stepsdesign,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.MyViewHolder holder, final int position)
    {
        //Toast.makeText(context, "no data"+steps1.get(position).getShortDesc(), Toast.LENGTH_SHORT).show();
        holder.stepNumber.setText(holder.getAdapterPosition()+"."+steps1.get(position).getShortDesc());
        holder.stepNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (steps1.get(position).getUrlLink().equals("")) {
                    Toast.makeText(context, "No Video For This Step", Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intent=new Intent(context, MediaActivity.class);
                    //MediaActivity mediaActivity=new MediaActivity(steps1);
                    view.getContext().startActivity(intent);
                    /*Toast.makeText(context, "" + steps1.get(position).getDesc(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(steps1.get(position).getUrlLink()));
                    view.getContext().startActivity(intent);*/
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return steps1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView stepNumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            stepNumber=itemView.findViewById(R.id.step);
        }
    }
}
