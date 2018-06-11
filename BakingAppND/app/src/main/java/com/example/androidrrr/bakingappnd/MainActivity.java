package com.example.androidrrr.bakingappnd;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidrrr.bakingappnd.adapter.RecipeAdapter;
import com.example.androidrrr.bakingappnd.model.Respies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rsp_recycler)RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;
    int images[];
    NetworkInfo wifi,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ConnectivityManager cManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        wifi=cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        mobile=cManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        images= new int[]{R.drawable.nutellacake,
                R.drawable.cakerespie,
                R.drawable.yellowcake,R.drawable.cheesecake};
        if ((wifi != null && mobile !=null) && (wifi.isConnected() | mobile.isConnected())) {
            respies();
        }
        else Toast.makeText(this, "Please Check Internet", Toast.LENGTH_SHORT).show();
    }

    private void respies()
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
                        List<Respies> recipes=new ArrayList<Respies>();
                        try {
                            pd.dismiss();
                            JSONArray root=new JSONArray(response);
                            for (int i=0;i<root.length();i++)
                            {
                                JSONObject pos=root.getJSONObject(0);
                                recipes.add(new Respies(
                                        pos.optString("id"),
                                        pos.optString("name")));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        recipeAdapter=new RecipeAdapter(MainActivity.this,recipes,images);
                        recyclerView.setAdapter(recipeAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();

                    }
            });
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
