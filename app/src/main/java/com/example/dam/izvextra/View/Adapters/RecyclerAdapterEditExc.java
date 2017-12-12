package com.example.dam.izvextra.View.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.EditActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerAdapterEditExc extends RecyclerView.Adapter<RecyclerAdapterEditExc.ExcursionViewHolder> {

    private ArrayList<Excursion> datos;
    private ArrayList<Group> grps;
    private ArrayList<Teacher> tchs;
    private Context context;
    private FragmentActivity fragmentActivity;

    public RecyclerAdapterEditExc(ArrayList<Excursion> datos) {
        this.datos = datos;
    }

    public RecyclerAdapterEditExc(ArrayList<Excursion> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    public RecyclerAdapterEditExc(ArrayList<Excursion> datos, Context context, FragmentActivity fragmentActivity, ArrayList<Group> grps, ArrayList<Teacher> tchs) {
        this.datos = datos;
        this.context = context;
        this.fragmentActivity = fragmentActivity;
        this.grps = grps;
        this.tchs = tchs;
    }

    @Override
    public ExcursionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_edit, parent, false);


        ExcursionViewHolder evh = new ExcursionViewHolder(itemView);

        return evh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterEditExc.ExcursionViewHolder holder, int position) {

        Excursion exc = datos.get(position);

        holder.bindExcursion(exc);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void updateArray(ArrayList<Excursion> arrayUpdated) {

        datos = arrayUpdated;

    }

    public class ExcursionViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPlace, tvGroups, tvTeachers, tvDate;
        private ImageView ibtnEdit;

        public ExcursionViewHolder(View itemView) {
            super(itemView);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvGroups = itemView.findViewById(R.id.tvGroups);
            tvTeachers = itemView.findViewById(R.id.tvTeachers);
            tvDate = itemView.findViewById(R.id.tvDate);
            ibtnEdit = itemView.findViewById(R.id.ibtnEdit);
        }

        public void bindExcursion(final Excursion s) {
            tvPlace.setText(s.getPlace());
            tvGroups.setText("Grupos: " + s.getGroups());
            tvTeachers.setText("Profesores: " + s.getTeachers());
            tvDate.setText(s.getDate());

            ibtnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int accion = 1;

                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("Excursion", s);
                    intent.putExtra("Groups", grps);
                    intent.putExtra("Teachers", tchs);
                    intent.putExtra("Accion", accion);
                    context.startActivity(intent);


                }
            });



        }
    }

    private String getStringTeachers(ArrayList<Teacher> arrayTeachers) {

        String result = "";

        result = result + arrayTeachers.get(0).getNameTeacher();

        if (arrayTeachers.size() == 1) {

        } else {

            for (int i = 1; i < arrayTeachers.size(); i++) {

                result = result + ", " + arrayTeachers.get(i).getNameTeacher() + " ";

            }
        }

        return result;
    }

    private String getStringGroups(ArrayList<Group> arrayGroups) {

        String result = "";

        result = result + arrayGroups.get(0).getNameGroup();

        if (arrayGroups.size() == 1) {

        } else {

            for (int i = 1; i < arrayGroups.size(); i++) {

                result = result + ", " + arrayGroups.get(i).getNameGroup() + " ";

            }
        }

        return result;
    }

}
