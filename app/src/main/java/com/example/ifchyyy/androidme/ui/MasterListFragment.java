package com.example.ifchyyy.androidme.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.example.ifchyyy.androidme.R;
import com.example.ifchyyy.androidme.adapters.GridAdapter;
import com.example.ifchyyy.androidme.data.AndroidImageAssets;

import java.util.ArrayList;

public class MasterListFragment extends Fragment {

    private GridView gridView;

    private OnImageClickListener callback;

    //init interface to select item picked for master list fragment
    public interface OnImageClickListener {
        void onImageselected(int position);

    }

    public MasterListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.master_list_fragment, container, false);


        //init gridview
        gridView = view.findViewById(R.id.images_grid_view);

        //init adapter
        GridAdapter adapter = new GridAdapter(getActivity(), (ArrayList<Integer>) AndroidImageAssets.getAll());
        //set adapter
        gridView.setAdapter(adapter);
        //set grid view on item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //trigger our callback method with the approprient position of item lcicked
                callback.onImageselected(position);
            }
        });


        return view;

    }

    //implement the callback in onatach method

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            callback = (OnImageClickListener) context;
        }catch (ClassCastException e ){

        }
    }
}
