package com.greyarea.grey.greyareaad340;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<String> movie_images;
    private ArrayList<String> movie_url;
    private ArrayList<String> movie_year;
    private ArrayList<String> movie_director;
    private ArrayList<String> movie_description;
    private Context mContext;

    public RecyclerAdapter(ArrayList<String> imageNames, ArrayList<String> images, ArrayList<String>  years,
                           ArrayList<String> directors, ArrayList<String> descriptions, Context context){
        movie_url = imageNames;
        movie_images = images;
        movie_year = years;
        movie_director = directors;
        movie_description = descriptions;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // takes context, tells glide the url we are looking for, and loads it into the image view
        Glide.with(mContext).asBitmap().load(movie_images.get(position)).into(holder.image);

        // sets image name for each part
        holder.title.setText(movie_url.get(position));

        // makes sure click is working
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

        Intent intent = new Intent(mContext, MovieDetails.class);
        intent.putExtra("image_url", movie_images.get(position));
        intent.putExtra("image_name", movie_url.get(position));
        intent.putExtra("image_year", movie_year.get(position));
        intent.putExtra("image_director", movie_director.get(position));
        intent.putExtra("image_description", movie_description.get(position));
        mContext.startActivity(intent);
            }
        });
    }

    // number of items in the recycler view
    @Override
    public int getItemCount() {
        return movie_url.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView title;
        TextView year;

        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }



}