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
public class frag2 extends Fragment {

    ArrayList<Integer> alColour;
    Button btnChange;

    public frag2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View frag2 = inflater.inflate(R.layout.fragment_frag2, container, false);
        btnChange = frag2.findViewById(R.id.btnChange);
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
                frag2.setBackgroundColor(randomColor);
            }
        });
        return frag2;
    }

}
