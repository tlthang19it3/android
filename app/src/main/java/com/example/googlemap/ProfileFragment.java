package com.example.googlemap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements MyAdapter.OnNoteListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<Pitch> list;
    public ProgressDialog progressDialog;
    Pitch pitchlist;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_pitch, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.pitchlist);
        database = FirebaseDatabase.getInstance().getReference("pitch");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(getActivity(),list, this);
        recyclerView.setAdapter(myAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pitch pitch = dataSnapshot.getValue(Pitch.class);
                    list.add(pitch);
                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }

    @Override
    public void onNoteClick(int position) {
        pitchlist = list.get(position);
        String[] pitch = {
                pitchlist.getAvatar(),
                pitchlist.getPitchname(),
                pitchlist.getAddress(),
                pitchlist.getDistrict(),
                pitchlist.getCity(),
                pitchlist.getPhone(),
                pitchlist.getStarttime(),
                pitchlist.getEndtime(),
                String.valueOf(pitchlist.getAd1()),
                String.valueOf(pitchlist.getAd2()),
                String.valueOf(pitchlist.getAd3()),
                String.valueOf(pitchlist.getPrice()),
                pitchlist.getKey(),
                String.valueOf(pitchlist.getSize())
        };
        InfoPitchFragment fr = new InfoPitchFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("pitch", pitch);
        fr.setArguments(bundle);

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}