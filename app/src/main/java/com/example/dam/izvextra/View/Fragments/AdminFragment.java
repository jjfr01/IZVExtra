package com.example.dam.izvextra.View.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Adapters.RecyclerAdapterEditExc;
import com.example.dam.izvextra.View.EditActivity;
import com.example.dam.izvextra.View.MainActivity;

import java.util.ArrayList;

public class AdminFragment extends Fragment {

    private ArrayList<Excursion> excs = new ArrayList<>();
    private ArrayList<Group> grps = new ArrayList<>();
    private ArrayList<Teacher> tchs = new ArrayList<>();
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

        if (savedInstanceState == null) {

            //Iniciar Vacio

            excs = getArguments().getParcelableArrayList("Array");
            grps = getArguments().getParcelableArrayList("Groups");
            tchs = getArguments().getParcelableArrayList("Teachers");

        } else {

            excs = savedInstanceState.getParcelableArrayList("Array");
            grps = savedInstanceState.getParcelableArrayList("Groups");
            tchs = savedInstanceState.getParcelableArrayList("Teachers");

        }

        init(view);

        return view;
    }

    private void init(View view) {

        RecyclerView rv = view.findViewById(R.id.rvEditExc);
        ra = new RecyclerAdapterEditExc(excs, view.getContext(), getActivity(), getArguments(), grps, tchs);
        rv.setAdapter(ra);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        Button btnAddExc = view.findViewById(R.id.btnAddExc);

        btnAddExc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), EditActivity.class);
                int accion = 0;
                intent.putExtra("Accion", accion);
                intent.putExtra("Groups", grps);
                intent.putExtra("Teachers", tchs);
                getActivity().startActivityForResult(intent, getArguments().getInt("New"));

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Array", excs);
        outState.putParcelableArrayList("Groups", grps);
        outState.putParcelableArrayList("Teachers", tchs);
    }

    public void updateFilterArray(ArrayList<Excursion> updated) {

        excs = updated;
        ra.updateArray(updated);
        ra.notifyDataSetChanged();

    }

}
