package com.example.p10problemstatement;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag3 extends Fragment {

    ArrayList<Integer> alColour;
    Button btnChange;

    public frag3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View frag3 = inflater.inflate(R.layout.fragment_frag3, container, false);
        btnChange = frag3.findViewById(R.id.btnChange);
        String[] colors = getActivity().getResources().getStringArray(R.array.colors);
        alColour = new ArrayList<Integer>();
        for (int i =0; i< colors.length; i++){
            int newColor = Color.parseColor(colors[i]);
            alColour.add(newColor);
        }

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = new Random().nextInt(alColour.size());
                Integer randomColor = alColour.get(random);
                frag3.setBackgroundColor(randomColor);
            }
        });
        return frag3;
    }

}
