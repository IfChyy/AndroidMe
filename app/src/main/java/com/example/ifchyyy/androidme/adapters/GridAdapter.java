package com.example.ifchyyy.androidme.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> imageList;

    public GridAdapter(Context context, ArrayList<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // If the view is not recycled, this creates a new ImageView to hold an image
            imageView = new ImageView(context);
            // Define the layout parameters
           imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            Picasso.get().load(imageList.get(position)).into(imageView);
        } else {
            imageView = (ImageView) convertView;
        }

        // Set the image resource and return the newly created ImageView
        //imageView.setImageResource(imageList.get(position));
        return imageView;
    }
}
