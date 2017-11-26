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
import com.example.dam.izvextra.View.Adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainFragment extends Fragment {

    private ArrayList<Excursion> excs = new ArrayList<>();
    private RecyclerAdapter ra;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {


        } else {

            excs = savedInstanceState.getParcelableArrayList("array");

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        ra = new RecyclerAdapter(excs, view.getContext());
        rv.setAdapter(ra);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("array", excs);
    }

    public void sendArray(ArrayList<Excursion> arrayRecovered) {

        excs = arrayRecovered;
        ra.updateArray(excs);//Método para actualizar el array interno del Adaptador
        ra.notifyDataSetChanged();

    }

}
