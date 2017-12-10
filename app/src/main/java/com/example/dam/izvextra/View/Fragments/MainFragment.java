package com.example.dam.izvextra.View.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Adapters.RecyclerAdapterViewOnly;
import com.example.dam.izvextra.View.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainFragment extends Fragment {

    private ArrayList<Excursion> excs = new ArrayList<>();
    private RecyclerAdapterViewOnly ra;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        if (savedInstanceState == null) {

            excs = getArguments().getParcelableArrayList("Array");

        } else {

            excs = savedInstanceState.getParcelableArrayList("Array");

        }

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rvViewOnly);
        ra = new RecyclerAdapterViewOnly(excs, view.getContext(), getActivity());
        rv.setAdapter(ra);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Array", excs);
    }

    public void updateFilterArray(ArrayList<Excursion> updated){

        excs = updated;
        ra.updateArray(updated);
        ra.notifyDataSetChanged();

    }



}
