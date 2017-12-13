package com.example.dam.izvextra.View.Adapters;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.R;

import java.util.ArrayList;

public class RecyclerAdapterViewOnlyGroup extends RecyclerView.Adapter<RecyclerAdapterViewOnlyGroup.GroupViewHolder> {

    private ArrayList<Group> datos;
    private Context context;
    private FragmentActivity fragmentActivity;

    public RecyclerAdapterViewOnlyGroup(ArrayList<Group> datos) {
        this.datos = datos;
    }

    public RecyclerAdapterViewOnlyGroup(ArrayList<Group> datos, Context context) {
        this.datos = datos;
        this.context = context;
    }

    public RecyclerAdapterViewOnlyGroup(ArrayList<Group> datos, Context context, FragmentActivity fragmentActivity) {
        this.datos = datos;
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_group, parent, false);

        GroupViewHolder evh = new GroupViewHolder(itemView);

        return evh;
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {

        Group grp = datos.get(position);

        holder.bindGroup(grp);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void updateArray(ArrayList<Group> arrayUpdated) {

        datos = arrayUpdated;

    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameGroup;


        public GroupViewHolder(View itemView) {
            super(itemView);
            tvNameGroup = itemView.findViewById(R.id.tvNameGroup);
        }

        public void bindGroup(final Group s) {
            tvNameGroup.setText(s.getGrupo());

        }
    }

}
