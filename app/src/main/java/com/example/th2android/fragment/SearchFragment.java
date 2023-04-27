package com.example.th2android.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.th2android.MainActivity;
import com.example.th2android.R;
import com.example.th2android.adapter.RecycleViewAdapter;
import com.example.th2android.adapter.SearchAdapter;
import com.example.th2android.dal.SQLHelper;
import com.example.th2android.model.Item;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private RecycleViewAdapter adapter;
    private SearchView search;
    private RecyclerView recyclerView;
    private List<Item> mList;
    private SQLHelper db;
    private RadioButton rb1, rb2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.recycleView);
        rb1 = view.findViewById(R.id.rb1);
        rb2 = view.findViewById(R.id.rb2);
        adapter = new RecycleViewAdapter();
        db = new SQLHelper(getContext());
        mList = db.getAll();
        adapter.setList(mList);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb1.isChecked()){
                    List<Item> filterList = new ArrayList<>();
                    for(Item i : mList){
                        if((i.getPhamvi().toLowerCase().contains(rb1.getText().toString().toLowerCase()))){
                            filterList.add(i);

                        }
                    }
                    if(filterList.isEmpty()){
                        Toast.makeText(getContext(), "Khong co du lieu khop", Toast.LENGTH_SHORT).show();

                    }else {
                        adapter.setList(filterList);
                    }

                }
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb2.isChecked()){
                    List<Item> filterList = new ArrayList<>();
                    for(Item i : mList){
                        if((i.getPhamvi().toLowerCase().contains(rb2.getText().toString().toLowerCase()))){
                            filterList.add(i);

                        }
                    }
                    if(filterList.isEmpty()){
                        Toast.makeText(getContext(), "Khong co du lieu khop", Toast.LENGTH_SHORT).show();

                    }else {
                        adapter.setList(filterList);
                    }

                }
            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }

            private void filter(String s) {
                List<Item> filterList = new ArrayList<>();
                for(Item i : mList){
                    if((i.getName().toLowerCase().contains(s.toLowerCase())) || (i.getAuthor().toLowerCase().contains(s.toLowerCase()))){
                        filterList.add(i);

                    }
                }
                if(filterList.isEmpty()){
                    Toast.makeText(getContext(), "Khong co du lieu khop", Toast.LENGTH_SHORT).show();

                }else {
                    adapter.setList(filterList);
                }
            }
        });

    }


}