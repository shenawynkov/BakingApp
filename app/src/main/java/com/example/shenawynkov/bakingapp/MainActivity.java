package com.example.shenawynkov.bakingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.shenawynkov.bakingapp.adapters.BakesAdapter;
import com.example.shenawynkov.bakingapp.models.Bake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements   BakesAdapter.ClickListner{
    private RecyclerView mRecyclerView;
    private BakesAdapter adapter;
    private List<Bake> list=new ArrayList<Bake>();

    @Override
    protected void onStart() {
        super.onStart();
        new BakingTask().execute();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter=new BakesAdapter(list,this);
        mRecyclerView.setAdapter(adapter);

        AndroidNetworking.initialize(getApplicationContext());


    }

    @Override
    public void onItemClicked(Bake bake) {
        Intent intent=new Intent(this,DetailActivity.class);
        intent.putExtra("bake",bake);
        startActivity(intent);

    }

    public class BakingTask extends AsyncTask<Void,Void,Void>
    {
        JSONArray array=null ;


        @Override
        protected Void doInBackground(Void... voids) {

              String  url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

        /*    AndroidNetworking.get("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            // do anything with response
                            try {
                                parsJason(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                        }
                    });*/
            try {
                parsJason(doGetRequest(url));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        OkHttpClient client = new OkHttpClient();
        // code request code here
        String doGetRequest(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        void parsJason(String str) throws JSONException {
            JSONArray jArray;
   jArray=new JSONArray(str);
              if(jArray!=null)
            //JSONArray jArray = new JSONArray(str);
            for (int i = 0; i < jArray.length(); i++) {
                Bake bake=new Bake();
                JSONObject jsonObject= jArray.getJSONObject(i);
                bake.setName(jsonObject.getString("name"));
                list.add(bake);
              JSONArray array=jsonObject.getJSONArray("ingredients");
                for(int j=0;j<array.length();j++)
                {
                    JSONObject object= (JSONObject) array.get(j);

                   int q=object.getInt("quantity");
                    String Measure =object.getString("measure");
               String ingredient=object.getString("ingredient");
                    bake.addIngredient(Measure,ingredient,q);

                }
                 array=jsonObject.getJSONArray("steps");
                for(int j=0;j<array.length();j++)
                {
                    JSONObject object= (JSONObject) array.get(j);

                    int Id=(object.getInt("id"));
                    String ShortDescription=(object.getString("shortDescription"));
                    String Description=(object.getString("description"));
                    String t=object.getString("thumbnailURL");
                    String VideoUrl=(object.getString("videoURL"));
                    bake.addStep(Id,ShortDescription,Description,t,VideoUrl);

                }

            }







        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
           // mRecyclerView.setAdapter(adapter);
        }
    }
}
