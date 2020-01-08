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

import com.example.yurtlist.DormsAnkara;
import com.example.yurtlist.Helpers.DormsHelperAnkara;
import com.example.yurtlist.R;

import java.util.ArrayList;

public class HomeAnkaraAdapter extends RecyclerView.Adapter<HomeAnkaraAdapter.HomeAnkaraHolder> {

    private Context context;
    private ArrayList<DormsAnkara> dormsAnkara;
    private DormsHelperAnkara dormsdb;
    Listener listener;


    public HomeAnkaraAdapter(Context context,ArrayList<DormsAnkara> dormsAnkara) {
        this.dormsAnkara = dormsAnkara;
        dormsdb = new DormsHelperAnkara(context);
    }
    public interface Listener {
        void onClick(int position);
    }

    public class HomeAnkaraHolder extends RecyclerView.ViewHolder{
        TextView dorm_name_ankara;
        ImageView dorm_image_ankara;
        CardView cardView;


        public HomeAnkaraHolder(View view) {
            super(view);
            dorm_name_ankara = (TextView)view.findViewById(R.id.popular_dorm__ankara);
            dorm_image_ankara = (ImageView) view.findViewById(R.id.popular_dorm_image_ankara);
            cardView = (CardView) view;

        }
    }
    @NonNull
    @Override
    public HomeAnkaraAdapter.HomeAnkaraHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_dorms_ankara,parent,false);
        return new HomeAnkaraAdapter.HomeAnkaraHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAnkaraAdapter.HomeAnkaraHolder holder, final int position) {
        final DormsAnkara dorms = dormsAnkara.get(position);
        CardView cardView = holder.cardView;

        holder.dorm_name_ankara.setText(dorms.getName());
        holder.dorm_image_ankara.setImageResource(dorms.getImageResourceId());
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
        return dormsAnkara.size();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }
}
