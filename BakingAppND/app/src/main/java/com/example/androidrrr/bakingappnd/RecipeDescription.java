package com.example.androidrrr.bakingappnd;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidrrr.bakingappnd.adapter.IngredientsAdapter;
import com.example.androidrrr.bakingappnd.adapter.StepsAdapter;
import com.example.androidrrr.bakingappnd.model.IngredientModel;
import com.example.androidrrr.bakingappnd.model.Respies;
import com.example.androidrrr.bakingappnd.model.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDescription extends AppCompatActivity {

    @BindView(R.id.ingredients)TextView ingredientsText;
    @BindView(R.id.setps_recycler)RecyclerView recyclerView;
    @BindView(R.id.ing1_recycler)RecyclerView ingRecyclerView;
    private StepsAdapter stepsAdapter;
    private IngredientsAdapter ingredientsAdapter;
    int position;
    NetworkInfo wifi,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_description);
        ButterKnife.bind(this);
        ConnectivityManager cManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        wifi=cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        mobile=cManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
         position=Integer.parseInt(getIntent().getStringExtra("ppp"));
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
       ingredientsText.setText(getIntent().getStringExtra("recipeName")+" Ingrtedients");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if ((wifi != null && mobile !=null) && (wifi.isConnected() | mobile.isConnected())) {
            getRecipeSteps();
        }
        else Toast.makeText(this, "Please Check Internet", Toast.LENGTH_SHORT).show();
        ingRecyclerView.setVisibility(View.GONE);
    }


    private void getRecipeSteps()
    {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Please wait...");
        pd.show();
        String sApi="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, sApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        List<Steps> steps=new ArrayList<Steps>();
                        try {
                            pd.dismiss();
                            JSONArray root=new JSONArray(response);
                                JSONObject pos = root.getJSONObject(position);
                                JSONArray stepPos = pos.getJSONArray("steps");
                                for (int j = 0; j < stepPos.length(); j++)
                                {
                                    JSONObject step=stepPos.getJSONObject(j);
                                    steps.add(new Steps(
                                            step.optString("id"),
                                            step.optString("shortDescription"),
                                            step.optString("description"),
                                            step.optString("videoURL"),
                                            step.optString("thumbnailURL")));
                                }

                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        stepsAdapter=new StepsAdapter(RecipeDescription.this,steps);
                        recyclerView.setAdapter(stepsAdapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecipeDescription.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void ingre(View view)
    {
        storeIngredients();
        ingRecyclerView.setVisibility(View.VISIBLE);
    }


    private void storeIngredients()
    {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Please wait...");
        pd.show();
        String sApi="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, sApi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        List<IngredientModel> ingre=new ArrayList<IngredientModel>();
                        try {
                            pd.dismiss();
                            JSONArray root=new JSONArray(response);
                            JSONObject pos = root.getJSONObject(position);
                            JSONArray ingpos = pos.getJSONArray("ingredients");
                            for (int j = 0; j < ingpos.length(); j++)
                            {
                                JSONObject ingredient=ingpos.getJSONObject(j);
                                ingre.add(new IngredientModel(
                                        ingredient.optString("ingredient"),
                                        ingredient.optString("quantity"),
                                        ingredient.optString("measure")));
                            }
                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ingredientsAdapter=new IngredientsAdapter(RecipeDescription.this,ingre);
                        ingRecyclerView.setAdapter(ingredientsAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecipeDescription.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
