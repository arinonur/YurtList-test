package com.example.yurtlist;


import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yurtlist.Adapters.DormListAdapter;
import com.example.yurtlist.Adapters.HomeAdapter;
import com.example.yurtlist.Adapters.HomeAnkaraAdapter;
import com.example.yurtlist.Adapters.HomeIstanbulAdapter;
import com.example.yurtlist.Helpers.DormsHelper;
import com.example.yurtlist.Helpers.DormsHelperAnkara;
import com.example.yurtlist.Helpers.DormsHelperIstanbul;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ActionBar actionBar;
    DormsHelper dormsHelper;
    DormsHelperIstanbul dormsHelperIstanbul;
    DormsHelperAnkara dormsHelperAnkara;
    private LinearLayoutManager linearLayoutManager;
    HomeAdapter dormListAdapter;
    HomeIstanbulAdapter dormListAdapter1;
    HomeAnkaraAdapter dormListAdapter2;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ArrayList<Dorms> dorms_izmir = new ArrayList<>();
    private ArrayList<DormsIstanbul> dorms_istanbul = new ArrayList<>();
    private ArrayList<DormsAnkara> dorms_ankara = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_home);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dorm_home);
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.dorm_home_2);
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.dorm_home_3);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setHasFixedSize(true);
        dormsHelper = new DormsHelper(getContext());
        dormsHelperIstanbul = new DormsHelperIstanbul(getContext());
        dormsHelperAnkara = new DormsHelperAnkara(getContext());
        dorms_izmir = dormsHelper.dormListIzmir();
        dorms_istanbul = dormsHelperIstanbul.dormListIstanbul();
        dorms_ankara = dormsHelperAnkara.dormListAnkara();

        dormListAdapter = new HomeAdapter(getContext(), dorms_izmir);
        dormListAdapter1 = new HomeIstanbulAdapter(getContext(), dorms_istanbul);
        dormListAdapter2 = new HomeAnkaraAdapter(getContext(), dorms_ankara);
        recyclerView.setAdapter(dormListAdapter);
        recyclerView1.setAdapter(dormListAdapter1);
        recyclerView2.setAdapter(dormListAdapter2);


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
        dormListAdapter1.setListener(new HomeIstanbulAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(),
                        DormDetailIstanbul.class);
                position = position + 1;
                intent.putExtra(DormDetailIstanbul.EXTRA_DORMID_IST, position);
                startActivity(intent);
            }
        });
        dormListAdapter2.setListener(new HomeAnkaraAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(),
                        DormDetailAnkara.class);
                position = position + 1;
                intent.putExtra(DormDetailAnkara.EXTRA_DORMID_ANK, position);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actionbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.turkish:
                Locale locale = new Locale("tr");
                Locale.setDefault(locale);
                Configuration config = getActivity().getBaseContext().getResources().getConfiguration();
                config.locale = locale;
                getActivity().getBaseContext().getResources().updateConfiguration(config,
                        getActivity().getBaseContext().getResources().getDisplayMetrics());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

                return true;
            case R.id.english:
                Locale locale_en = new Locale("en");
                Locale.setDefault(locale_en);
                Configuration config_en = getActivity().getBaseContext().getResources().getConfiguration();
                config_en.locale = locale_en;
                getActivity().getBaseContext().getResources().updateConfiguration(config_en,
                        getActivity().getBaseContext().getResources().getDisplayMetrics());
                Intent intent_en = new Intent(getActivity(), MainActivity.class);
                startActivity(intent_en);
                Toast.makeText(getActivity(), "Language set to English", Toast.LENGTH_SHORT).show();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }


}
