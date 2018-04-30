package com.example.ifchyyy.androidme.ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.ifchyyy.androidme.R;
import com.example.ifchyyy.androidme.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex, bodyIndex, legIndex;
    private boolean twoPane = false;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init fragment manager
        fragmentManager = getFragmentManager();

        //check if layout for tablet mode created
        if (findViewById(R.id.android_me_linear_layout) != null) {
            twoPane = true;

            //remove next button in tablet mode
            Button nextButton = findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            //set gridview collumns to 2 for tablet mode
            GridView gridView = findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);
            //create new fragments only if old ones do not exist
            if (savedInstanceState == null) {
                //init headFragment in tablet mode
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                fragmentManager.beginTransaction().add(R.id.headCointaner, headFragment).commit();

                //init bodyFragment in tablet mode
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction().add(R.id.bodyContainer, bodyFragment).commit();

                //init legFragment in tablet mode
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction().add(R.id.feetContainer, legFragment).commit();
            }
        }
    }

    @Override
    public void onImageselected(int position) {


        //int bodypart is going to be 0 1 or 2 if we devide by 12, thats how we know which part was selected
        int bodyPart = position / 12;

        //get the index of item clicked from 0 to 11
        int listIndex = position - 12 * bodyPart;

        //check if tablet mode or phone
        if (twoPane) {

            //init bodyPartFramgnet
            BodyPartFragment bodyPartFragment = new BodyPartFragment();

            switch (bodyPart) {
                case 0:

                    bodyPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setListIndex(listIndex);
                    getFragmentManager().beginTransaction().replace(R.id.headCointaner, bodyPartFragment).commit();
                    break;
                case 1:

                    bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());
                    bodyPartFragment.setListIndex(listIndex);
                    getFragmentManager().beginTransaction().replace(R.id.bodyContainer, bodyPartFragment).commit();
                    break;
                case 2:
                    bodyPartFragment.setImageIds(AndroidImageAssets.getLegs());
                    bodyPartFragment.setListIndex(listIndex);
                    getFragmentManager().beginTransaction().replace(R.id.feetContainer, bodyPartFragment).commit();
                    break;
                default:
                    break;
            }

        } else {

            //init all parts with the approprient item lcicked and part
            switch (bodyPart) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }

            //add the indexes to a bundle
            Bundle bundle = new Bundle();
            bundle.putInt("headIndex", headIndex);
            bundle.putInt("bodyIndex", bodyIndex);
            bundle.putInt("legIndex", legIndex);

            //create a new bundle to pass the indexes with extra
            final Intent in = new Intent(this, AndroidMeActivity.class);
            in.putExtras(bundle);

            //button to start a new activity showing the android
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(in);
                }
            });
        }
    }
}

