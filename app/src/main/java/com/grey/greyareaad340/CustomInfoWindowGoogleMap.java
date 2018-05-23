package com.grey.greyareaad340;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.greyarea.grey.greyareaad340.R;
import com.greyarea.grey.greyareaad340.Webcam;
import com.squareup.picasso.Picasso;

import javax.security.auth.callback.Callback;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context context){
        this.context = context;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = ((MapsActivity)context).getLayoutInflater()
                .inflate(R.layout.maps_touch_popup, null);

        TextView name =  view.findViewById(R.id.webcamName);

        ImageView urlImage =  view.findViewById(R.id.webcamImage);

        //ImageView img =  view.findViewById(R.id.webcamImage);


        Webcam infoWindowData = (Webcam) marker.getTag();


        Picasso.with(context).load(infoWindowData.getImageURL()).into(urlImage);

        name.setText(infoWindowData.getCamDescription().toString());


        return view;
    }


    }
