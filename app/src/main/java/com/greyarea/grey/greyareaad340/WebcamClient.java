package com.greyarea.grey.greyareaad340;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WebcamClient extends AppCompatActivity  {

    private RecyclerView camView;
    private WebcamAdapter mAdapter;
    private ArrayList<Webcam> webcamArrList;
    private RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webcam_main);

        Toolbar mToolbar = findViewById(R.id.action_search);
        setSupportActionBar(mToolbar);


        camView = findViewById(R.id.camList);
        camView.setHasFixedSize(true);
        camView.setLayoutManager(new LinearLayoutManager(this));
        webcamArrList = new ArrayList<Webcam>();
        mAdapter = new WebcamAdapter(WebcamClient.this, webcamArrList);
        camView.setAdapter(mAdapter);

        if (!isOnline()){
            Toast.makeText(WebcamClient.this,
                    "Sorry, please connect to the Internet and try again",
                    Toast.LENGTH_SHORT).show();
        }


        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }


    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

            invalidateOptionsMenu();
            menu.findItem(R.id.about_us).setVisible(true);
            menu.findItem(R.id.action_setting).setVisible(true);
            menu.findItem(R.id.action_search).setVisible(true);
            menu.findItem(R.id.nav_zombies).setVisible(true);

        return super.onPrepareOptionsMenu(menu);
    }


    private void parseJSON() {
        String url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Features");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject feature = jsonArray.getJSONObject(i);

                                JSONArray cameras = feature.getJSONArray("Cameras");
                                for (int j = 0; j < cameras.length(); j++) {
                                    JSONObject camera = cameras.getJSONObject(j);
                                    String type = camera.getString("Type");
                                    String imageURL = camera.getString("ImageUrl");
                                    if (type.equals("sdot")) {
                                        imageURL = "http://www.seattle.gov/trafficcams/images/" + imageURL;
                                    } else {
                                        imageURL = "http://images.wsdot.wa.gov/nw/" + imageURL;
                                    }
                                    String camDescription = camera.getString("Description");
                                    webcamArrList.add(new Webcam(camDescription, imageURL, type));
                                }
                            }

                            mAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        requestQueue.add(request);
    }

    //Create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(WebcamClient.this,
                    "Settings Clicked",
                    Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


}



