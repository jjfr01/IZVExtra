package com.example.dam.izvextra.View;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Fragments.AdminFragment;
import com.example.dam.izvextra.View.Fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionbar;
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    private Excursion holderExcursion;

    private void init() {

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);
        drawerlayout =  findViewById(R.id.drawerlayout);


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

        if(savedInstanceState == null) {

            defaultFragment();

        } else {

            //No recreamos el Activity -- Solución que le doy para evitar que se recree al girar el dispositivo

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void changeFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragMain, fragment)
                .commit();

    }

    private void defaultFragment() {

        changeFragment(new MainFragment());

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

                switch (item.getItemId()) {

                    case R.id.menu_home:
                        fragment = new MainFragment();
                        FragmentTransaction = true;
                        break;

                    case R.id.menu_admin:
                        fragment = new AdminFragment();
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

    public void holdExcursion(Excursion exc){

        holderExcursion = exc;

    }

    public Excursion recoverHolderExcursion(){


        return holderExcursion;
    }

    public void uncheckedHomeItem(){

        navigationView.getMenu().getItem(0).setChecked(false);

    }

}
