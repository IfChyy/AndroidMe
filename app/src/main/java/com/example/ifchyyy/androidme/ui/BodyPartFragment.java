package com.example.ifchyyy.androidme.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ifchyyy.androidme.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to host a fragment containing the body part of the adnroid we are going to show
 * and present it to the user
 *
 */
public class BodyPartFragment extends Fragment implements OnClickListener{


    private ImageView bodyPartImageView;
    private List<Integer> imageIds;
    private int listIndex;

    //method to init our body part fragment in activity class
    public BodyPartFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //init fragment view ( hosting our layout)
        View view = inflater.inflate(R.layout.fragment_body_art, container, false);

        if(savedInstanceState != null){
            imageIds = savedInstanceState.getIntegerArrayList("indexArray");
            listIndex = savedInstanceState.getInt("index");
        }

        //init our imageview holding our body part
        bodyPartImageView = view.findViewById(R.id.bodyPartImageView);
        bodyPartImageView.setOnClickListener(this);
        //check if imageid has been set
        if(imageIds != null){
            bodyPartImageView.setImageResource(imageIds.get(listIndex));
        }else{
            Toast.makeText(getActivity(), "You have not selected an image for this part",
                    Toast.LENGTH_SHORT).show();
        }


        return view;
    }



    //---------------SETTTERS

    public void setImageIds(List<Integer> imageIds) {
        this.imageIds = imageIds;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    //------------------------------------------


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList("indexArray", (ArrayList<Integer>) imageIds);
        outState.putInt("index", listIndex);
    }

    @Override
    public void onClick(View v) {
        //increment index of image if clicked
        listIndex++;
        //check if index goes out of bounds
        if(listIndex > imageIds.size()-1){
            listIndex = 0;
        }
        //set index
        bodyPartImageView.setImageResource(imageIds.get(listIndex));
    }
}
