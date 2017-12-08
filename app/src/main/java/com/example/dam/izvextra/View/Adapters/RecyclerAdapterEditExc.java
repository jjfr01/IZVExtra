package com.example.dam.izvextra.View.Adapters;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerAdapterEditExc extends RecyclerView.Adapter<RecyclerAdapterEditExc.ExcursionViewHolder> {

    private ArrayList<Excursion> datos;
    private Context context;
    private FragmentActivity fragmentActivity;

    public RecyclerAdapterEditExc(ArrayList<Excursion> datos) {
        this.datos = datos;
    }

    public RecyclerAdapterEditExc(ArrayList<Excursion> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    public RecyclerAdapterEditExc(ArrayList<Excursion> datos, Context context, FragmentActivity fragmentActivity) {
        this.datos = datos;
        this.context = context;
        this.fragmentActivity = fragmentActivity;
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

        private TextView tvPlace, tvGroups, tvTeachers, tvDate, tvHour;
        private CardView cv1;


        public ExcursionViewHolder(View itemView) {
            super(itemView);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvGroups = itemView.findViewById(R.id.tvGroups);
            tvTeachers = itemView.findViewById(R.id.tvTeachers);
            tvDate = itemView.findViewById(R.id.tvDate);
            //tvHour = (TextView) itemView.findViewById(R.id.tvHour);
            cv1 = itemView.findViewById(R.id.cv1);
        }

        public void bindExcursion(final Excursion s) {
            tvPlace.setText(s.getPlace());
            tvGroups.setText("Grupos: " + getStringGroups(s.getGroups()));
            tvTeachers.setText("Profesores: " + getStringTeachers(s.getTeachers()));
            tvDate.setText(s.getDate());
            //tvHour.setText(s.getHour());

            cv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


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

    private String getDateFormat(Date date) {

        String result = "";

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        result = format.format(date);

        return result;
    }

}
