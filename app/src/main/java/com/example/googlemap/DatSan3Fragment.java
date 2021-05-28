package com.example.googlemap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatSan3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatSan3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tvticket;
    private TextView tvname;
    private TextView tvphone;
    private TextView tvtime;
    private TextView tvdate,tvpname,tvadd,tvprice,tvprice2,tvprice3,tvticket2;
    private ImageView imageView;
    private String uri;

    public DatSan3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatSan3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatSan3Fragment newInstance(String param1, String param2) {
        DatSan3Fragment fragment = new DatSan3Fragment();
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
        View view = inflater.inflate(R.layout.fragment_dat_san3, container, false);
        tvprice2 = (TextView) view.findViewById(R.id.textView36);
        tvprice3 = (TextView) view.findViewById(R.id.textView40);
        tvticket2 = (TextView) view.findViewById(R.id.textView41);
        tvprice = (TextView) view.findViewById(R.id.textView29);
        tvticket = (TextView) view.findViewById(R.id.ticket);
        tvname = (TextView) view.findViewById(R.id.tvname);
        tvphone = (TextView) view.findViewById(R.id.phone2);
        tvtime = (TextView) view.findViewById(R.id.timepick);
        tvdate = (TextView) view.findViewById(R.id.datepick);
        imageView = (ImageView) view.findViewById(R.id.imgpitch3);
        tvpname = (TextView) view.findViewById(R.id.tvpname3);
        tvadd = (TextView) view.findViewById(R.id.tvadd3);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String[] info = bundle.getStringArray("info");
            uri = info[5];
            tvticket.setText("Mã vé:  "+info[0]);
            tvname.setText("Tên:  "+info[1]);
            tvphone.setText("Số điện thoại:  "+info[2]);
            tvprice.setText(info[17]+" VNĐ");
            tvprice3.setText("Số tiền: "+info[17]+" VNĐ");
            tvticket2.setText("Nội dung chuyển khoản: "+info[0]);
            tvprice2.setText(
                    "Thực hiện chuyển tiền ("+info[17]+" VNĐ) vào tài khoản 0123456789 với nội dung chuyển tiền là "+info[0]);
            tvtime.setText(info[3]);
            tvdate.setText(info[4]);
            tvpname.setText(info[6]);
            tvadd.setText(info[7]+", "+info[8]+", "+info[9]);
            Picasso.get().load(uri).into(imageView);
        }
        return view;
    }
}