package com.example.dam.izvextra.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Adapters.RecyclerAdapterViewOnlyGroup;
import com.example.dam.izvextra.View.Adapters.RecyclerAdapterViewOnlyTeacher;

import java.util.ArrayList;

public class TeacherFragment extends Fragment {

    private ArrayList<Teacher> tchs = new ArrayList<>();
    private RecyclerAdapterViewOnlyTeacher ra;


    public TeacherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        if (savedInstanceState == null) {

            tchs = getArguments().getParcelableArrayList("Teachers");

        } else {

            tchs = savedInstanceState.getParcelableArrayList("Teachers");

        }

        RecyclerView rv = view.findViewById(R.id.rvViewOnly);
        ra = new RecyclerAdapterViewOnlyTeacher(tchs, view.getContext(), getActivity());
        rv.setAdapter(ra);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Teachers", tchs);
    }



}
