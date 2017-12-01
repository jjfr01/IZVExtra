package com.example.dam.izvextra.View.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Adapters.RecyclerAdapterEditExc;
import com.example.dam.izvextra.View.Adapters.RecyclerAdapterViewOnly;

import java.util.ArrayList;

public class AdminFragment extends Fragment {

    private ArrayList<Excursion> excs = new ArrayList<>();
    private RecyclerAdapterEditExc ra;

    public AdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        init(view);

        if(savedInstanceState == null){

            //Iniciar Vacio

            excs = getArguments().getParcelableArrayList("Array");

        } else {

            excs = savedInstanceState.getParcelableArrayList("Array");

        }

        init(view);

        return view;
    }

    private void init(View view){

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rvEditExc);
        ra = new RecyclerAdapterEditExc(excs, view.getContext(), getActivity());
        rv.setAdapter(ra);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Array", excs);
    }
}
