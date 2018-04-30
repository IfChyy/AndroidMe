/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ifchyyy.androidme.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ifchyyy.androidme.R;
import com.example.ifchyyy.androidme.data.AndroidImageAssets;


// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        //init fragment manager
        fragmentManager = getFragmentManager();
        //create new fragments only if old ones do not exist
        if (savedInstanceState == null) {
            //init headFragment
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            //get extra passed from parent activity
            int headIndex = getIntent().getIntExtra("headIndex", 0);
            headFragment.setListIndex(headIndex);
            fragmentManager.beginTransaction().add(R.id.headCointaner, headFragment).commit();

            //init bodyFragment
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            //get extra passed from parent activity
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            bodyFragment.setListIndex(bodyIndex);

            fragmentManager.beginTransaction().add(R.id.bodyContainer, bodyFragment).commit();

            //init legFragment
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            //get extra passed from parent activity
            int legIndex = getIntent().getIntExtra("legIndex", 0);
            legFragment.setListIndex(legIndex);

            fragmentManager.beginTransaction().add(R.id.feetContainer, legFragment).commit();
        }

    }


}
