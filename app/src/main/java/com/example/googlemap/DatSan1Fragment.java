package com.example.googlemap;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatSan1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatSan1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText sldate;
    TextView size, tvname, tvadd,tvphone,tvtime;
    DatePickerDialog.OnDateSetListener setListener;
    Button bttime1;
    Button bttime2;
    Button bttime3;
    Button bttime4;
    ImageView img;
    String[] pitch;
    String uri;

    public DatSan1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatSan1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatSan1Fragment newInstance(String param1, String param2) {
        DatSan1Fragment fragment = new DatSan1Fragment();
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

        View view = inflater.inflate(R.layout.fragment_dat_san1, container, false);
        sldate = (EditText) view.findViewById(R.id.datepicker);
        size = (TextView) view.findViewById(R.id.loaisan);
        img = (ImageView) view.findViewById(R.id.imgpitch1);
        tvname = (TextView) view.findViewById(R.id.tvpname1);
        tvadd = (TextView) view.findViewById(R.id.tvadd1);
        tvphone = (TextView) view.findViewById(R.id.tvphone1);
        tvtime = (TextView) view.findViewById(R.id.tvtime1);
        bttime1 = view.findViewById(R.id.bttime1);
        bttime2 = view.findViewById(R.id.bttime2);
        bttime3 = view.findViewById(R.id.bttime3);
        bttime4 = view.findViewById(R.id.bttime4);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            pitch = bundle.getStringArray("pitch1");
            uri = pitch[0];
            Picasso.get().load(uri).into(img);
            tvname.setText(pitch[1]);
            tvadd.setText(pitch[2]+", "+pitch[3]+", "+pitch[4]);
            tvphone.setText(pitch[5]);
            tvtime.setText(pitch[6]+" - "+pitch[7]);
            size.setText("SÂN "+pitch[13]);
        }
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                sldate.setText(date);
            }
        };

        sldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(
                            DatePicker view, int year, int month, int dayOfMonth
                    ) {
                        month = month+1;
                        String date = dayOfMonth+"/"+month+"/"+year;
                        sldate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        bttime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatSan1(v);
            }
        });

        bttime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatSan2(v);
            }
        });

        bttime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatSan3(v);
            }
        });

        bttime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatSan4(v);
            }
        });

        return view;
    }

    private void openDatSan1(View v) {
        String[] datetime = {
                "16:00",sldate.getText().toString(),size.getText().toString(),
                uri,
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
                pitch[13],
                "1.5"
        };
        DatSan2Fragment fr2 = new DatSan2Fragment();
        Bundle bundle2 = new Bundle();
        bundle2.putStringArray("datetime", datetime);
        fr2.setArguments(bundle2);

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openDatSan2(View v) {
        String[] datetime = {"17:30",sldate.getText().toString(),size.getText().toString(),uri,
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
                pitch[13],
                "1.5"
        };
        DatSan2Fragment fr2 = new DatSan2Fragment();
        Bundle bundle2 = new Bundle();
        bundle2.putStringArray("datetime", datetime);
        fr2.setArguments(bundle2);

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openDatSan3(View v) {
        String[] datetime = {"19:00",sldate.getText().toString(),size.getText().toString(),uri,
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
                pitch[13],
                "1.5"
        };
        DatSan2Fragment fr2 = new DatSan2Fragment();
        Bundle bundle2 = new Bundle();
        bundle2.putStringArray("datetime", datetime);
        fr2.setArguments(bundle2);

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openDatSan4(View v) {
        String[] datetime = {"20:30",sldate.getText().toString(),size.getText().toString(),uri,
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
                pitch[13],
                "1.5"
        };
        DatSan2Fragment fr2 = new DatSan2Fragment();
        Bundle bundle2 = new Bundle();
        bundle2.putStringArray("datetime", datetime);
        fr2.setArguments(bundle2);

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}