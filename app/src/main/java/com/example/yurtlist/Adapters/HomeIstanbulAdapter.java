package com.example.yurtlist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yurtlist.DormsIstanbul;
import com.example.yurtlist.Helpers.DormsHelperIstanbul;
import com.example.yurtlist.R;

import java.util.ArrayList;

public class HomeIstanbulAdapter extends RecyclerView.Adapter<HomeIstanbulAdapter.HomeIstanbulHolder> {

    private Context context;
    private ArrayList<DormsIstanbul> dormsIstanbuls;
    private DormsHelperIstanbul dormsdb;
    Listener listener;


    public HomeIstanbulAdapter(Context context,ArrayList<DormsIstanbul> dormsIstanbuls) {
        this.dormsIstanbuls = dormsIstanbuls;
        dormsdb = new DormsHelperIstanbul(context);
    }
    public interface Listener {
        void onClick(int position);
    }

    public class HomeIstanbulHolder extends RecyclerView.ViewHolder{
        TextView dorm_name2;
        ImageView dorm_image2;
        CardView cardView;


        public HomeIstanbulHolder(View view) {
            super(view);
            dorm_name2 = (TextView)view.findViewById(R.id.popular_dorm_name2);
            dorm_image2 = (ImageView) view.findViewById(R.id.popular_dorm_image2);
            cardView = (CardView) view;

        }
    }
    @NonNull
    @Override
    public HomeIstanbulAdapter.HomeIstanbulHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_dorms_istanbul,parent,false);
        return new HomeIstanbulAdapter.HomeIstanbulHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeIstanbulAdapter.HomeIstanbulHolder holder, final int position) {
        final DormsIstanbul dorms = dormsIstanbuls.get(position);
        CardView cardView = holder.cardView;

        holder.dorm_name2.setText(dorms.getName());
        holder.dorm_image2.setImageResource(dorms.getImageResourceId());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    public  int getItemCount() {
        return dormsIstanbuls.size();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }
}
