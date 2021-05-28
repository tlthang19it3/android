package com.example.googlemap;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatSan2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatSan2Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView size,tvname,tvadd;
    private TextView timepicker;
    private TextView datepicker;
    private Button button;
    private EditText edname;
    private EditText edphone;
    private String[] datetime;
    private Calendar calendar;
    private SimpleDateFormat sdf;
    private ImageView imageView;
    private String uri;
    private DatabaseReference mDatabase;
    public DatSan2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatSan2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatSan2Fragment newInstance(String param1, String param2) {
        DatSan2Fragment fragment = new DatSan2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_dat_san2, container, false);
        tvname = (TextView) view.findViewById(R.id.tvpname2);
        tvadd = (TextView) view.findViewById(R.id.tvadd2);
        edname = (EditText) view.findViewById(R.id.edname);
        edphone = (EditText) view.findViewById(R.id.edphone);
        button = (Button) view.findViewById(R.id.submitbutton);
        size = (TextView) view.findViewById(R.id.tvphone);
        timepicker = (TextView) view.findViewById(R.id.timepick);
        datepicker = (TextView) view.findViewById(R.id.datepick);
        imageView = (ImageView) view.findViewById(R.id.imgpitch2);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            datetime = bundle.getStringArray("datetime");
            uri = datetime[3];
            Picasso.get().load(uri).into(imageView);
            sdf = new SimpleDateFormat("HH:mm");
            Date date = null;
            try {
                date = sdf.parse(datetime[0]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 1);
            calendar.add(Calendar.MINUTE, 30);
            tvname.setText(datetime[4]);
            tvadd.setText(datetime[5]+", "+datetime[6]+", "+datetime[7]);
            size.setText(datetime[2]);
            timepicker.setText("Từ "+datetime[0]+" đến "
                    +String.format("%1$tH:%1$tM", calendar));
            datepicker.setText(datetime[1]);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        return view;
    }

    public static String random() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwzyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    private void submit() {
        String ticket = random();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
        String bookday = sdf1.format(new Date());
        String timebooking = datepicker.getText().toString();
        String starttime = datetime[0];
        String endtime = String.format("%1$tH:%1$tM", calendar);
        String pitchid = datetime[15];
        float dura = Float.parseFloat(datetime[17]);
        int pricep = Integer.parseInt(datetime[14]);
        float price = dura*pricep;
        String[] info = {
                ticket,
                edname.getText().toString(),
                edphone.getText().toString(),
                timepicker.getText().toString(),
                datepicker.getText().toString(),
                uri,
                datetime[4],
                datetime[5],
                datetime[6],
                datetime[7],
                datetime[8],
                datetime[9],
                datetime[10],
                datetime[11],
                datetime[12],
                datetime[13],
                datetime[14],
                String.format("%.0f",price),
                datetime[16],
                datetime[17],
        };
        //Add data
        mDatabase = FirebaseDatabase.getInstance().getReference("booking");
        String bookingId = mDatabase.push().getKey();
        Booking booking =
                new Booking(
                        FirebaseAuth.getInstance().getCurrentUser().getUid(),
                        bookday,timebooking,starttime,endtime,pitchid,
                "1",0,ticket,price,bookingId);
//        Pitch pitch1 = new Pitch(
//                "Sân bóng đại học việt-hàn",
//                "Sân còn mới cỏ tốt , với anh chủ sân rất chi là vui vẽ",
//                "1","Thành phố Đà nẵng ",
//                "Quận Ngũ Hành Sơn","Hòa Hải","0123456789","00:00",
//                "23:00",50000,5,"avatar");
        mDatabase.child(bookingId).setValue(booking);

        DatSan3Fragment fr3 = new DatSan3Fragment();
        Bundle bundle3 = new Bundle();
        bundle3.putStringArray("info", info);
        fr3.setArguments(bundle3);

        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager()
                        .beginTransaction();
        fragmentTransaction.add(R.id.frament_hihi, fr3);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}