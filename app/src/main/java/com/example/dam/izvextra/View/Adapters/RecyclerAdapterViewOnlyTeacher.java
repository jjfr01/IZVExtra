package com.example.dam.izvextra.View.Adapters;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;

import java.util.ArrayList;

public class RecyclerAdapterViewOnlyTeacher extends RecyclerView.Adapter<RecyclerAdapterViewOnlyTeacher.TeacherViewHolder> {

    private ArrayList<Teacher> datos;
    private Context context;
    private FragmentActivity fragmentActivity;

    public RecyclerAdapterViewOnlyTeacher(ArrayList<Teacher> datos) {
        this.datos = datos;
    }

    public RecyclerAdapterViewOnlyTeacher(ArrayList<Teacher> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    public RecyclerAdapterViewOnlyTeacher(ArrayList<Teacher> datos, Context context, FragmentActivity fragmentActivity) {
        this.datos = datos;
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public TeacherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_teacher, parent, false);

        TeacherViewHolder tvh = new TeacherViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(TeacherViewHolder holder, int position) {

        Teacher tch = datos.get(position);

        holder.bindTeacher(tch);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void updateArray(ArrayList<Teacher> arrayUpdated) {

        datos = arrayUpdated;

    }

    public class TeacherViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameTeacher;


        public TeacherViewHolder(View itemView) {
            super(itemView);
            tvNameTeacher = itemView.findViewById(R.id.tvNameTeacher);
        }

        public void bindTeacher(final Teacher s) {
            tvNameTeacher.setText(s.getNombre());

        }
    }

}
