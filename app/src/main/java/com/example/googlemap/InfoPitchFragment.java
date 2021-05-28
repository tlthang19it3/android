package com.example.googlemap;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoPitchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoPitchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View buttonDS;
    private ImageView imgds;
    public String imguri;
    private ImageView imageView,img1,img2,img3,imgmap;
    private TextView tvname,tvadd,tvphone,tvtime,tvad1,tvad2,tvad3,tvprice,tvmap;
    private String[] pitch;

    public InfoPitchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoPitchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoPitchFragment newInstance(String param1, String param2) {
        InfoPitchFragment fragment = new InfoPitchFragment();
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
        View view = inflater.inflate(R.layout.fragment_info_pitch, container, false);
        imageView = (ImageView) view.findViewById(R.id.imgpitch);
        img1 = (ImageView) view.findViewById(R.id.imgad1);
        img2 = (ImageView) view.findViewById(R.id.imgad2);
        img3 = (ImageView) view.findViewById(R.id.imgad3);
        tvname = (TextView) view.findViewById(R.id.tvpname);
        tvadd = (TextView) view.findViewById(R.id.tvadd);
        tvphone = (TextView) view.findViewById(R.id.tvphone);
        tvtime = (TextView) view.findViewById(R.id.tvtime);
        tvad1 = (TextView) view.findViewById(R.id.tvad1);
        tvad2 = (TextView) view.findViewById(R.id.tvad2);
        tvad3 = (TextView) view.findViewById(R.id.tvad3);
        tvprice = (TextView) view.findViewById(R.id.tvprice);

        tvmap = (TextView) view.findViewById(R.id.vitri);
        imgmap = (ImageView) view.findViewById(R.id.imageVitri);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pitch = bundle.getStringArray("pitch");
            imguri = pitch[0];
            Picasso.get().load(imguri).into(imageView);
            tvname.setText(pitch[1]);
            tvadd.setText(pitch[2]+", "+pitch[3]+", "+pitch[4]);
            tvphone.setText(pitch[5]);
            tvtime.setText(pitch[6]+" - "+pitch[7]);
            if (Integer.parseInt(pitch[8]) == 0) {
                img1.setImageResource(R.drawable.circelwarning);
                tvad1.setText("Tốn phí nước uống");
            } else {
                img1.setImageResource(R.drawable.verify);
                tvad1.setText("Miễn phí nước uống");
            }
            if (Integer.parseInt(pitch[9]) == 0) {
                img2.setImageResource(R.drawable.circelwarning);
                tvad2.setText("Tốn phí thuê bóng");
            } else{
                img2.setImageResource(R.drawable.verify);
                tvad2.setText("Miễn phí thuê bóng");
            }
            if (Integer.parseInt(pitch[10]) == 0) {
                img3.setImageResource(R.drawable.circelwarning);
                tvad3.setText("Cần đặt cọc");
            } else{
                img3.setImageResource(R.drawable.verify);
                tvad3.setText("Không cần đặt cọc");
            }

            tvprice.setText(Integer.parseInt(pitch[11])/1000+"k");
        }

        buttonDS = view.findViewById(R.id.datsan);
        imgds = view.findViewById(R.id.imageDatsan);
        buttonDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatSan();
            }
        });
        imgds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatSan();
            }
        });

        tvmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        imgmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        return view;
    }

    private void openMap() {
        startActivity(new Intent(getActivity(),MapsActivity.class));
    }

    private void openDatSan() {
        String[] pitch1 = {
                imguri,
                pitch[1],
                pitch[2],
                pitch[3],
                pitch[4],
                pitch[5],
                pitch[6],
                pitch[7],
                pitch[8],
                pitch[9],
                pitch[10],
                pitch[11],
                pitch[12],
                pitch[13]

        };
        DatSan1Fragment fr1 = new DatSan1Fragment();
        Bundle bundle1 = new Bundle();
        bundle1.putStringArray("pitch1", pitch1);
        fr1.setArguments(bundle1);

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}