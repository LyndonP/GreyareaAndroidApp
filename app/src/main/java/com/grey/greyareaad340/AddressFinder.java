package com.grey.greyareaad340;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.greyarea.grey.greyareaad340.Webcam;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class AddressFinder {
    private Context context;

    public AddressFinder(Context context) {
        this.context = context;
    }

    public List<Address> getFromLocation(Location location) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        return geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);


    }
}