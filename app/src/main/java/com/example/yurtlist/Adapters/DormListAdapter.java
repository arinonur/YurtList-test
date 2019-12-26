package com.example.yurtlist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yurtlist.Dorms;
import com.example.yurtlist.Helpers.DormsHelper;
import com.example.yurtlist.R;

import java.util.ArrayList;

public class DormListAdapter extends RecyclerView.Adapter<DormListAdapter.DormListHolder> {

    private Context context;
    private ArrayList<Dorms> dormsHelper;
    private DormsHelper dormsdb;
    Listener listener;

    public interface Listener {
        void onClick(int position);
    }

    public DormListAdapter(Context context,ArrayList<Dorms> dormsHelper) {
        this.dormsHelper = dormsHelper;
        dormsdb = new DormsHelper(context);
    }


    public class DormListHolder extends RecyclerView.ViewHolder{
        TextView dorm_name;
        TextView province;
        ImageView dorm_image;
        CardView cardView;


        public DormListHolder(View view) {
            super(view);
            dorm_name = (TextView)view.findViewById(R.id.dorm_name);
            province = (TextView)view.findViewById(R.id.dorm_city);
            dorm_image = (ImageView) view.findViewById(R.id.image_dorm);
            cardView = (CardView) view;

        }
    }

    @Override
    public DormListAdapter.DormListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dorm_list,parent,false);
        return new DormListAdapter.DormListHolder(V);
    }

    @Override
    public void onBindViewHolder(final DormListHolder holder, final int position) {
        final Dorms dorms = dormsHelper.get(position);
        CardView cardView = holder.cardView;

        holder.dorm_name.setText(dorms.getName());
        holder.province.setText(dorms.getProvince());
        holder.dorm_image.setImageResource(dorms.getImageResourceId());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }



    @Override
    public  int getItemCount() {
        return dormsHelper.size();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }
}


