package com.example.yurtlist;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yurtlist.Adapters.DormListAdapter;
import com.example.yurtlist.Helpers.DormsHelper;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DormListFragment extends Fragment {
    DormsHelper dormsHelper;
    private LinearLayoutManager linearLayoutManager;
    DormListAdapter dormListAdapter;
    private SQLiteDatabase db;
    private Cursor cursor;
    RecyclerView recyclerView;
    private ArrayList<Dorms> dorms=new ArrayList<>();


    public DormListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dorm_list, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.dorm_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        dormsHelper = new DormsHelper(getContext());
        dorms = dormsHelper.dormList();

        dormListAdapter = new DormListAdapter(getContext(),dorms);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(dormListAdapter);

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



        return view;
    }
}
