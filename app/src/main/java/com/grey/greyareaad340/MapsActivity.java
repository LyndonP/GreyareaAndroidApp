package com.grey.greyareaad340;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.greyarea.grey.greyareaad340.R;
import com.greyarea.grey.greyareaad340.Webcam;
import com.greyarea.grey.greyareaad340.WebcamAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    GoogleMap mMap;
    GoogleMap mGoogleMap;
    LocationManager locationManager;
    LocationListener locationListener;
    RecyclerView camView;
    WebcamAdapter mAdapter;
    List<Webcam> webcamArrList;
    RequestQueue requestQueue;
    ArrayList<Marker> markers;
    Location lastLocation;
    Marker currLocMarker;
    Geocoder geocoder;
    Marker currMarker;
    String url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    //boolean not_first_time_showing_info_window;
    List<Address> geocoded;
    private Hashtable<String, String> allMarkers = new Hashtable<>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_maps);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        webcamArrList = new ArrayList<>();

        webcamArrList = parseJson();


        // Add a marker in BC and move the camera
        LatLng bc = new LatLng(53.7267, -127.6476);
        mMap.addMarker(new MarkerOptions().position(bc).title("BC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bc));

        locationPermitted();
        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);
        mMap.setOnMyLocationClickListener(onMyLocationClickListener);


        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(6);

        mMap.setOnMarkerClickListener(this);

        // Set a listener for info window events.
        mMap.setOnInfoWindowClickListener(this);


    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
        marker.getTag();
        marker.getTitle();
        marker.getId();

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d("MARKERCLICK", "value: ");
        return false;
    }

    public List<Webcam> parseJson() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting Ready..");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("Features");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject camObj = array.getJSONObject(i);
                        String camLabel = "";
                        String camImage = "";
                        String camOwnership = "";
                        String camAdd = "";

                        Webcam camera = new Webcam(camLabel, camImage, camOwnership);
                        JSONArray camLocation = camObj.getJSONArray("PointCoordinate");

                        camera.setLatitude(camLocation.getDouble(0));
                        camera.setLongitude(camLocation.getDouble(1));


                        JSONArray camArray = camObj.getJSONArray("Cameras");
                        for (int j = 0; j < camArray.length(); j++) {
                            JSONObject cameras = camArray.getJSONObject(j);
                            camera.setCamLabel(cameras.getString("Id"));
                            camera.setCamDescription(cameras.getString("Description"));
                            camera.setCamOwnership(cameras.getString("Type"));
                            camera.setImageUrl(cameras.getString("ImageUrl"));
                        }

                        MarkerOptions markerOptions = new MarkerOptions();
                        LatLng newCamLocation = new LatLng(camera.getLatitude(),
                                camera.getLongitude());
                        if (camera.getCamOwnership().equals(("wsdot"))) {
                            markerOptions.position(newCamLocation).title(camera.getLabel())
                                    .snippet(camera.getCamDescription())
                                    .icon(BitmapDescriptorFactory
                                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        } else {
                            markerOptions.position(newCamLocation).title(camera.getLabel())
                                    .snippet(camera.getCamDescription())
                                    .icon(BitmapDescriptorFactory
                                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        }

                        mMap.setInfoWindowAdapter(new CustomInfoWindow(MapsActivity.this));
                        Marker m = mMap.addMarker((markerOptions));
                        m.setTag(camera);
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(newCamLocation));

                        webcamArrList.add(camera);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

        return webcamArrList;
    }


    private void locationPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }

    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "Showing North Seattle College",
                Toast.LENGTH_SHORT).show();
        LatLng northSeattleCollege = new LatLng(47.692830562, -122.333);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(northSeattleCollege));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermitted();

                } else {
                    showDefaultLocation();
                }
                return;
            }

        }
    }

    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(6);
                    return false;
                }
            };

    private GoogleMap.OnMyLocationClickListener onMyLocationClickListener =
            new GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(@NonNull Location location) {

                    mMap.setMinZoomPreference(6);

                    CircleOptions circleOptions = new CircleOptions();
                    circleOptions.center(new LatLng(location.getLatitude(),
                            location.getLongitude()));

                    circleOptions.radius(220);
                    circleOptions.fillColor(Color.RED);
                    circleOptions.strokeWidth(6);

                    mMap.addCircle(circleOptions);
                }
            };

}





