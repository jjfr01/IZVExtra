package com.example.dam.izvextra.View.Adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.Presenter.Contract;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.EditActivity;
import com.example.dam.izvextra.View.MainActivity;

import java.util.ArrayList;

public class RecyclerAdapterEditExc extends RecyclerView.Adapter<RecyclerAdapterEditExc.ExcursionViewHolder> {

    private ArrayList<Excursion> datos;
    private ArrayList<Group> grps;
    private ArrayList<Teacher> tchs;
    private Context context;
    private FragmentActivity fragmentActivity;
    private Bundle bundle;

    private Contract contract = new Contract();

    public RecyclerAdapterEditExc(ArrayList<Excursion> datos) {
        this.datos = datos;
    }

    public RecyclerAdapterEditExc(ArrayList<Excursion> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    public RecyclerAdapterEditExc(ArrayList<Excursion> datos, Context context, FragmentActivity fragmentActivity, Bundle bundle, ArrayList<Group> grps, ArrayList<Teacher> tchs) {
        this.datos = datos;
        this.context = context;
        this.fragmentActivity = fragmentActivity;
        this.bundle = bundle;
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

        holder.bindExcursion(exc, position);

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
        private ImageView ibtnEdit, ibtnDel;

        public ExcursionViewHolder(View itemView) {
            super(itemView);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvGroups = itemView.findViewById(R.id.tvGroups);
            tvTeachers = itemView.findViewById(R.id.tvTeachers);
            tvDate = itemView.findViewById(R.id.tvDate);
            ibtnEdit = itemView.findViewById(R.id.ibtnEdit);
            ibtnDel = itemView.findViewById(R.id.ibtnDel);
        }

        public void bindExcursion(final Excursion s, final int position) {
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
                    fragmentActivity.startActivityForResult(intent, bundle.getInt("Edit"));


                }
            });

            ibtnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    deleteExc(position, s);

                }
            });


        }
    }

    private String getStringTeachers(ArrayList<Teacher> arrayTeachers) {

        String result = "";

        result = result + arrayTeachers.get(0).getNombre();

        if (arrayTeachers.size() == 1) {

        } else {

            for (int i = 1; i < arrayTeachers.size(); i++) {

                result = result + ", " + arrayTeachers.get(i).getNombre() + " ";

            }
        }

        return result;
    }

    private String getStringGroups(ArrayList<Group> arrayGroups) {

        String result = "";

        result = result + arrayGroups.get(0).getGrupo();

        if (arrayGroups.size() == 1) {

        } else {

            for (int i = 1; i < arrayGroups.size(); i++) {

                result = result + ", " + arrayGroups.get(i).getGrupo() + " ";

            }
        }

        return result;
    }

    public void deleteExc(final int position, final Excursion exc) {

        Button btnAceptar1, btnCancelar1;

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater li = LayoutInflater.from(context);
        final AlertDialog ad;

        alert.setView(li.inflate((R.layout.delete_window), null));

        alert.setTitle("ELIMINAR");

        ad = alert.create();
        ad.show();

        btnAceptar1 = ad.findViewById(R.id.btnAceptar1);
        btnCancelar1 = ad.findViewById(R.id.btnCancelar1);

        btnAceptar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateArray(((MainActivity)fragmentActivity).deleteExc(exc.getId()));

                contract.deleteExc(context, exc.getId());

                notifyDataSetChanged();//Recargar Recycler

                ad.cancel();
            }
        });

        btnCancelar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.cancel();
            }
        });

    }

}
