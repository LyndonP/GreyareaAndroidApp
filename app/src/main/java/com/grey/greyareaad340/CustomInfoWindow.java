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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;



public class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindow(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;

    }

    @Override
    public View getInfoWindow(Marker marker) {

        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.maps_touch_popup, null);

        TextView camName = view.findViewById(R.id.webcamName);
        ImageView camPic = view.findViewById(R.id.webcamImage);

        camName.setText(marker.getTitle());

        Webcam camData = (Webcam) marker.getTag();
        String imageURL = camData.getImageURL();

        Picasso.with(view.getContext()).load(imageURL).error(R.mipmap.ic_launcher_round).resize(480, 405).into(camPic, new MarkerCallback(marker));

        return view;
    }

    // Picasso interface called Callback
    static class MarkerCallback implements Callback {
        Marker marker = null;

        MarkerCallback(Marker marker) {
            this.marker = marker;
        }

        @Override
        public void onError() {
        }

        @Override
        public void onSuccess() {
            if (marker == null) {
                return;
            }

            if (!marker.isInfoWindowShown()) {
                return;
            }

            // refresh if InfoWindowData activity is showing

            marker.hideInfoWindow(); // Need to hide first or else error is thrown
            marker.showInfoWindow();
        }
    }


}
