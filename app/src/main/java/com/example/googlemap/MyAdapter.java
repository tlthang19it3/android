package com.example.googlemap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Pitch> list;
    private OnNoteListener mOnNoteListener;

    public MyAdapter(Context context, ArrayList<Pitch> list, OnNoteListener onNoteListener) {
        this.context = context;
        this.list = list;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pitch pitch = list.get(position);
        holder.tvname.setText(pitch.getPitchname());
        holder.tvadd.setText(pitch.getAddress()+", "+pitch.getDistrict()+", "+pitch.getCity());
        holder.tvphone.setText(pitch.getPhone());

        String imguri = null;
        imguri = pitch.getAvatar();
        Picasso.get().load(imguri).into(holder.pitchimg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvname, tvadd, tvphone;
        ImageView pitchimg;
        OnNoteListener onNoteListener;
        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            pitchimg = (ImageView) itemView.findViewById(R.id.pitchimg);
            tvname = (TextView) itemView.findViewById(R.id.pitchname);
            tvadd = (TextView) itemView.findViewById(R.id.pitchadd);
            tvphone = (TextView) itemView.findViewById(R.id.pitchphone);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
