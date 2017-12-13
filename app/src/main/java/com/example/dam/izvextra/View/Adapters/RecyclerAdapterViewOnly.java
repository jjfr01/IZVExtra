package com.example.dam.izvextra.View.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Fragments.MainFragment;
import com.example.dam.izvextra.View.MainActivity;
import com.example.dam.izvextra.View.ViewActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerAdapterViewOnly extends RecyclerView.Adapter<RecyclerAdapterViewOnly.ExcursionViewHolder> {

    private ArrayList<Excursion> datos;
    private Context context;
    private FragmentActivity fragmentActivity;

    public RecyclerAdapterViewOnly(ArrayList<Excursion> datos) {
        this.datos = datos;
    }

    public RecyclerAdapterViewOnly(ArrayList<Excursion> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    public RecyclerAdapterViewOnly(ArrayList<Excursion> datos, Context context, FragmentActivity fragmentActivity) {
        this.datos = datos;
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public ExcursionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        ExcursionViewHolder evh = new ExcursionViewHolder(itemView);

        return evh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterViewOnly.ExcursionViewHolder holder, int position) {

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
            cv1 = itemView.findViewById(R.id.cv1);
        }

        public void bindExcursion(final Excursion s) {
            tvPlace.setText(s.getPlace());
            tvGroups.setText("Grupos: " + s.getGroups());
            tvTeachers.setText("Profesores: " + s.getTeachers());
            tvDate.setText(s.getDate());
            //tvHour.setText(s.getHour());

            cv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("Excursion", s);
                    context.startActivity(intent);
                }
            });

        }
    }

}
