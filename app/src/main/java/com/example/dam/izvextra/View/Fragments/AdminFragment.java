package com.example.dam.izvextra.View.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dam.izvextra.R;

public class AdminFragment extends Fragment {

    private EditText etPrueba;


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

        } else {

            etPrueba.setText(savedInstanceState.getString("Texto"));

        }

        return view;
    }

    private void init(View view){

        etPrueba = (EditText) view.findViewById(R.id.etPrueba);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Texto", etPrueba.getText().toString());
    }
}
