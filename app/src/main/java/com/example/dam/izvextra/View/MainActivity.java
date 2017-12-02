package com.example.dam.izvextra.View;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Fragments.AdminFragment;
import com.example.dam.izvextra.View.Fragments.MainFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionbar;
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    private Excursion holderExcursion;
    private ArrayList<Excursion> excs = new ArrayList<>();

    private void init() {

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);
        drawerlayout = findViewById(R.id.drawerlayout);


        navigationView.setItemIconTintList(null);//Esto nos permite que los iconos del menu del navigation drawer tengan su propio color

        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_burguer);//Cargamos el icono burger para el Navigation Drawer
        actionbar.setDisplayHomeAsUpEnabled(true);

        setSettingNavigationDrawer();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        if (savedInstanceState == null) {

            getArray();

            defaultFragment();

        } else {

            //No recreamos el Activity -- Solución que le doy para evitar que se recree al girar el dispositivo

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void changeFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragMain, fragment)
                .commit();

    }

    private void defaultFragment() {

        Fragment fragment = new MainFragment();
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("Array", excs);
        fragment.setArguments(bundle);

        changeFragment(fragment);

        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);

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

    private void setSettingNavigationDrawer() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean FragmentTransaction = false;
                Fragment fragment = null;
                Bundle bundle;

                switch (item.getItemId()) {

                    case R.id.menu_home:
                        fragment = new MainFragment();
                        bundle = new Bundle();
                        bundle.putParcelableArrayList("Array", excs);
                        fragment.setArguments(bundle);
                        FragmentTransaction = true;
                        break;

                    case R.id.menu_admin:
                        fragment = new AdminFragment();
                        bundle = new Bundle();
                        bundle.putParcelableArrayList("Array", excs);
                        fragment.setArguments(bundle);
                        FragmentTransaction = true;
                        break;

                    case R.id.menu_group:
                        //fragment = new MainFragment();
                        //FragmentTransaction = true;
                        break;

                    case R.id.menu_teacher:
                        //fragment = new MainFragment();
                        //FragmentTransaction = true;
                        break;

                    case R.id.menu_setting:

                        break;

                    case R.id.menu_help:

                        break;


                }

                if (FragmentTransaction == true) {

                    changeFragment(fragment);
                    item.setChecked(true);
                    drawerlayout.closeDrawers();
                }

                return true;
            }
        });


    }

    public void uncheckedHomeItem() {

        navigationView.getMenu().getItem(0).setChecked(false);

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
