package com.example.googlemap;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class mapFragment extends Fragment implements View.OnClickListener {



    public mapFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        Button btn = v.findViewById(R.id.button);
        btn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(),MapsActivity.class));
    }
}