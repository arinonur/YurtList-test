package com.example.yurtlist;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yurtlist.Adapters.DormListAdapter;
import com.example.yurtlist.Adapters.HomeAdapter;
import com.example.yurtlist.Helpers.DormsHelper;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    DormsHelper dormsHelper;
    private LinearLayoutManager linearLayoutManager;
    HomeAdapter dormListAdapter;
    HomeAdapter dormListAdapter1;
    private SQLiteDatabase db;
    private Cursor cursor;
    RecyclerView recyclerView;
    private ArrayList<Dorms> dorms_izmir=new ArrayList<>();
    private ArrayList<Dorms> dorms_istanbul=new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.dorm_home);
        RecyclerView recyclerView1 = (RecyclerView)view.findViewById(R.id.dorm_home_2);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1 ,GridLayoutManager.HORIZONTAL, false);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 1 ,GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setHasFixedSize(true);
        dormsHelper = new DormsHelper(getContext());
        dorms_izmir = dormsHelper.dormListIzmir();
        dorms_istanbul = dormsHelper.dormListIstanbul();

        dormListAdapter = new HomeAdapter(getContext(),dorms_izmir);
        dormListAdapter1 = new HomeAdapter(getContext(),dorms_istanbul);
        recyclerView.setAdapter(dormListAdapter);
        recyclerView1.setAdapter(dormListAdapter1);


        dormListAdapter.setListener(new DormListAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(),
                        DormDetailActivity.class);
                position = position + 1;
                intent.putExtra(DormDetailActivity.EXTRA_DORMID, position);
                startActivity(intent);
            }
        });
        dormListAdapter1.setListener(new DormListAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(),
                        DormDetailActivity.class);
                position = position + 1;
                intent.putExtra(DormDetailActivity.EXTRA_DORMID, position);
                startActivity(intent);
            }
        });

        return view;
    }

}
