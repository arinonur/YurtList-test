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

import com.example.yurtlist.Dorms;
import com.example.yurtlist.Helpers.DormsHelper;
import com.example.yurtlist.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private Context context;
    private ArrayList<Dorms> dormsHelper;
    private DormsHelper dormsdb;
    DormListAdapter.Listener listener;

    public HomeAdapter(Context context,ArrayList<Dorms> dormsHelper) {
        this.dormsHelper = dormsHelper;
        dormsdb = new DormsHelper(context);
    }
    public interface Listener {
        void onClick(int position);
    }

    public class HomeHolder extends RecyclerView.ViewHolder{
        TextView dorm_name;
        ImageView dorm_image;
        CardView cardView;


        public HomeHolder(View view) {
            super(view);
            dorm_name = (TextView)view.findViewById(R.id.popular_dorm_name);
            dorm_image = (ImageView) view.findViewById(R.id.popular_dorm_image);
            cardView = (CardView) view;

        }
    }
    @NonNull
    @Override
    public HomeAdapter.HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_dorms,parent,false);
        return new HomeAdapter.HomeHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeHolder holder, final int position) {
        final Dorms dorms = dormsHelper.get(position);
        CardView cardView = holder.cardView;

        holder.dorm_name.setText(dorms.getName());
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

    public  int getItemCount() {
        return dormsHelper.size();
    }

    public void setListener(DormListAdapter.Listener listener){
        this.listener = listener;
    }
}
