package com.example.dam.izvextra.View.Adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ExcursionViewHolder> {

    private ArrayList<Excursion> datos;
    private Context context;

    public RecyclerAdapter(ArrayList<Excursion> datos) {
        this.datos = datos;
    }

    public RecyclerAdapter(ArrayList<Excursion> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    @Override
    public ExcursionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

        ExcursionViewHolder evh = new ExcursionViewHolder(itemView);

        return evh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ExcursionViewHolder holder, int position) {

        Excursion exc = datos.get(position);

        holder.bindExcursion(exc, context);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ExcursionViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPlace;
        private CardView cv1;


        public ExcursionViewHolder(View itemView) {
            super(itemView);
            tvPlace = (TextView) itemView.findViewById(R.id.tvPlace);
            cv1 = (CardView) itemView.findViewById(R.id.cv1);
        }

        public void bindExcursion(final Excursion s, final Context context) {
            tvPlace.setText(s.getPlace());

            cv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, ""+s.getDate(), Toast.LENGTH_LONG).show();

                }
            });

        }
    }
}
