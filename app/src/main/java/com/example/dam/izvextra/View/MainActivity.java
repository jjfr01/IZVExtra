package com.example.dam.izvextra.View;

import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Adapters.RecyclerAdapter;
import com.example.dam.izvextra.View.Fragments.MainFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionbar;
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        navigationView.setItemIconTintList(null);//Esto nos permite que los iconos del menu del navigation drawer tengan su propio color

        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_burguer);
        actionbar.setDisplayHomeAsUpEnabled(true);

        /*RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerAdapter ra = new RecyclerAdapter(generarDatos(), this);
        rv.setAdapter(ra);

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));*/

        MainFragment mf = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragMain);
        mf.sendArray(generarDatos());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                drawerlayout.openDrawer(GravityCompat.START);

                return true;


        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Excursion> generarDatos() {

        ArrayList<Excursion> excs = new ArrayList<>();

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

        return excs;
    }
}
