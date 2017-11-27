package com.example.dam.izvextra.View.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.MainActivity;

public class ViewFragment extends Fragment {

    private TextView tvPrueba;
    private View view;
    private Excursion exc;


    public ViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_view, container, false);

        init();

        if (savedInstanceState == null){

            getData();

        } else {

            exc = savedInstanceState.getParcelable("Excursion");

        }

        setData();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("Excursion", exc);
    }

    private void init(){

        tvPrueba = view.findViewById(R.id.tvPrueba);

    }

    public void getData(){

        MainActivity ma = (MainActivity) getActivity();

        exc = ma.recoverHolderExcursion();

    }

    private void setData(){

        tvPrueba.setText(exc.getPlace());

    }

}
