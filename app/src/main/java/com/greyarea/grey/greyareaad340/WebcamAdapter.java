package com.greyarea.grey.greyareaad340;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebcamAdapter extends RecyclerView.Adapter<WebcamAdapter.TrafficViewHolder>  {


    private Context context;
    private ArrayList<Webcam> trafficCamArrayList;
    private ArrayList<Webcam> mFilteredList;

    //create an adapter
    public WebcamAdapter(Context context, ArrayList<Webcam> trafficCamArrayList){
        this.context = context;
        this.trafficCamArrayList = trafficCamArrayList;
        mFilteredList = trafficCamArrayList;
    }

    @NonNull
    @Override
    public TrafficViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_webcam_recycler, parent, false);
        return new TrafficViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrafficViewHolder holder, int position) {
        Webcam trafficCam = trafficCamArrayList.get(position);
        String imageURL = trafficCam.getImageURL();
        String trafficCamDescription = trafficCam.getLabel();
        String trafficCamType = trafficCam.getCamOwnership();

        holder.camLabel.setText(trafficCamDescription);
        holder.camOwnership.setText(trafficCamType);
        Picasso.with(context).load(imageURL).fit().centerInside().into(holder.imageWebam);
    }

    @Override
    public int getItemCount() {
        return trafficCamArrayList.size();
    }


    public class TrafficViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageWebam;
        public TextView camLabel;
        public TextView camOwnership;

        public TrafficViewHolder(View itemView) {
            super(itemView);
            imageWebam = itemView.findViewById(R.id.image_view);
            camLabel = itemView.findViewById(R.id.text_view_camera_label);
            camOwnership = itemView.findViewById(R.id.text_view_ownership);
        }
    }

}

