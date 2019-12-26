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
    private SQLiteDatabase db;
    private Cursor cursor;
    RecyclerView recyclerView;
    private ArrayList<Dorms> dorms=new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.dorm_home);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1 ,GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        dormsHelper = new DormsHelper(getContext());
        dorms = dormsHelper.dormList();

        dormListAdapter = new HomeAdapter(getContext(),dorms);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(dormListAdapter);

        dormListAdapter.setListener(new DormListAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(),
                        DormDetailActivity.class);
                intent.putExtra(DormDetailActivity.EXTRA_DORMID, position);
                startActivity(intent);
            }
        });

        return view;
    }

}
