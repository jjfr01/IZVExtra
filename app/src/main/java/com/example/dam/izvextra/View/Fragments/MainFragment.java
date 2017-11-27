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

            getArray();//Temporal

        } else {

            excs = savedInstanceState.getParcelableArrayList("array");

        }

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        ra = new RecyclerAdapterViewOnly(excs, view.getContext(), getActivity());
        rv.setAdapter(ra);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("array", excs);
    }

    private void getArray() {//Esto es temporal

            for (int i = 0; i < 2; i++) {

                ArrayList<Teacher> tchs = new ArrayList<>();
                Teacher tch = new Teacher("Juanjo", "Fernandez", 1);
                tchs.add(tch);
                tch = new Teacher("Pepe", "Pepazo", 2);
                tchs.add(tch);
                tch = new Teacher("Antonia", "Lozano", 3);
                tchs.add(tch);

                Group grp = new Group("2º DAM", 1);
                ArrayList<Group> grps = new ArrayList<>();
                grps.add(grp);

                Calendar calendar = Calendar.getInstance();
                Date newDate = calendar.getTime();

                Excursion exc = new Excursion(tchs, grps, "Prueba", "Casa", "11:15", newDate);

                excs.add(exc);

            }

            /*
            //View -- Aqui
            Presenter ps = new Presenter();

            ps.getArrayExcursions();

            //Presenter
            public ArrayList<Excursion> getArrayExcursions(){

                Model md = new Model();

                return md.getArrayFromJson();
            }

            //Model
           public ArrayList<Excursion> getArrayFromJson(){

                //Conectar con el json
                //Parsear el jsonarray a un arrayList<Excursion>
                //cuando lo tengamos lo devolvemos

                return array;

           }
*/
    }

}
