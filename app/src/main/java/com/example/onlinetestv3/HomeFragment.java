package com.example.onlinetestv3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.listRecyclerView);
        List<String> itemList = Arrays.asList("C Programming", "Python Programming", "Java Programming", "Database Concepts");
        CustomListAdapter adapter = new CustomListAdapter(itemList);
        recyclerView.setAdapter(adapter);

        return v;
    }
}