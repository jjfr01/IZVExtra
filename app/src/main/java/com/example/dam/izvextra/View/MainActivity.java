package com.example.dam.izvextra.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Model.Pojo.Group;
import com.example.dam.izvextra.Model.Pojo.Teacher;
import com.example.dam.izvextra.Presenter.Contract;
import com.example.dam.izvextra.R;
import com.example.dam.izvextra.View.Fragments.AdminFragment;
import com.example.dam.izvextra.View.Fragments.GroupFragment;
import com.example.dam.izvextra.View.Fragments.MainFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    private ArrayList<Excursion> excs = new ArrayList<>();
    private ArrayList<Group> grps = new ArrayList<>();
    private ArrayList<Teacher> tchs = new ArrayList<>();

    private String filterGroup = "";
    private String filterDate = "";
    private int fragmentSelected = 0;

    private Contract contract = new Contract();

    private final int REQUEST_EDIT = 1;
    private final int REQUEST_NEW = 2;

    private void init() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);
        drawerlayout = findViewById(R.id.drawerlayout);

        navigationView.setItemIconTintList(null);//Esto nos permite que los iconos del menu del navigation drawer tengan su propio color

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
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

            startApp();

        } else {

            //No recreamos el Activity -- Solución que le doy para evitar que se recree al girar el dispositivo
            excs = savedInstanceState.getParcelableArrayList("Array");
            grps = savedInstanceState.getParcelableArrayList("Groups");
            tchs = savedInstanceState.getParcelableArrayList("Teachers");
            fragmentSelected = savedInstanceState.getInt("FragmentSelected");

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("Array", excs);
        outState.putParcelableArrayList("Groups", grps);
        outState.putParcelableArrayList("Teachers", tchs);
        outState.putInt("FragmentSelected", fragmentSelected);

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

            case R.id.action_filter:

                if(fragmentSelected==1){

                    launchDialogFilter();

                } else if(fragmentSelected==2){

                    launchDialogFilter();

                } else {

                    Toast.makeText(this, "No se puede usar el filtro aquí", Toast.LENGTH_SHORT).show();

                }

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {

            drawerlayout.closeDrawers();

        } else {

            super.onBackPressed();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_NEW:

                startApp();

                break;

            case REQUEST_EDIT:

                startApp();

                break;


        }

    }

    public void changeFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragMain, fragment)
                .commit();

    }

    public void defaultFragment() {

        Fragment fragment = new MainFragment();
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("Array", excs);
        fragment.setArguments(bundle);

        changeFragment(fragment);

        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);

        fragmentSelected = 1;

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
                        fragmentSelected = 1;

                        break;

                    case R.id.menu_admin:

                        fragment = new AdminFragment();
                        bundle = new Bundle();
                        bundle.putParcelableArrayList("Array", excs);
                        bundle.putParcelableArrayList("Groups", grps);
                        bundle.putParcelableArrayList("Teachers", tchs);
                        bundle.putInt("New", REQUEST_NEW);
                        bundle.putInt("Edit", REQUEST_EDIT);
                        fragment.setArguments(bundle);
                        FragmentTransaction = true;
                        fragmentSelected = 2;

                        break;

                    case R.id.menu_group:
                        fragment = new GroupFragment();
                        bundle = new Bundle();
                        bundle.putParcelableArrayList("Groups", grps);
                        fragment.setArguments(bundle);
                        FragmentTransaction = true;
                        fragmentSelected = 3;
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

                if (FragmentTransaction) {

                    changeFragment(fragment);
                    item.setChecked(true);
                    drawerlayout.closeDrawers();

                }

                return true;
            }
        });


    }

    private void launchDialogFilter() {

        Button btnFilter, btnClose;

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater li = this.getLayoutInflater();
        final AlertDialog ad;

        alert.setView(li.inflate((R.layout.filter_window), null));

        alert.setTitle("Filtro");

        ad = alert.create();
        ad.show();

        initSpinnersFilter(ad);

        btnFilter = ad.findViewById(R.id.btnFilter);
        btnClose = ad.findViewById(R.id.btnClose);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filterArray();

                ad.cancel();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.cancel();
            }
        });

    }

    private void filterArray() {

        ArrayList<Excursion> result = new ArrayList<>();

        ArrayList<Excursion> aux = excs;

        if (!filterGroup.equals("Seleccione Grupo") || !filterDate.equals("Seleccione Fecha")) {

            if (filterGroup.equals("Cualquiera") && filterDate.equals("Cualquiera")) {

                result = aux;

            } else if ((filterGroup.equals("Cualquiera") || filterGroup.equals("Seleccione Grupo")) && (filterDate.equals("Cualquiera") || filterDate.equals("Seleccione Fecha"))) {

                result = aux;

            } else if ((!filterGroup.equals("Cualquiera") && !filterGroup.equals("Seleccione Grupo")) && (filterDate.equals("Cualquiera") || filterDate.equals("Seleccione Fecha"))) {

                for (int i = 0; i < aux.size(); i++) {

                    ArrayList<String> grps = new ArrayList<>(Arrays.asList(aux.get(i).getGroups().split(", ")));

                    boolean control = true;

                    for (int o = 0; o < grps.size() && control; o++) {

                        if (grps.get(o).equals(filterGroup)) {

                            control = false;

                            result.add(aux.get(i));

                        }

                    }

                }

            } else if ((filterGroup.equals("Cualquiera") || filterGroup.equals("Seleccione Grupo")) && (!filterDate.equals("Cualquiera") && !filterDate.equals("Seleccione Fecha"))) {

                for (int i = 0; i < aux.size(); i++) {

                    if (aux.get(i).getDate().equals(filterDate)) {

                        result.add(aux.get(i));

                    }

                }

            } else if ((!filterGroup.equals("Cualquiera") && !filterGroup.equals("Seleccione Grupo")) && (!filterDate.equals("Cualquiera") && !filterDate.equals("Seleccione Fecha"))) {

                for (int i = 0; i < aux.size(); i++) {

                    ArrayList<String> grps = new ArrayList<>(Arrays.asList(aux.get(i).getGroups().split(", ")));

                    boolean control = true;

                    for (int o = 0; o < grps.size() && control; o++) {

                        if (grps.get(o).equals(filterGroup) && aux.get(i).getDate().equals(filterDate)) {

                            control = false;

                            result.add(aux.get(i));

                        }

                    }

                }
            }

            sendDataFragment(result);

        }

    }

    public void sendDataFragment(ArrayList<Excursion> result) {

        switch (fragmentSelected) {

            case 1:

                MainFragment mf = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragMain);
                mf.updateFilterArray(result);

                break;

            case 2:

                AdminFragment af = (AdminFragment) getSupportFragmentManager().findFragmentById(R.id.fragMain);
                af.updateFilterArray(result);

                break;

        }

    }

    private void initSpinnersFilter(AlertDialog ad) {

        Spinner spiGroup, spiDate;

        spiGroup = ad.findViewById(R.id.spiGroup);
        spiDate = ad.findViewById(R.id.spiDate);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, getStringArrayGroups(grps), android.R.layout.simple_spinner_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_item, getStringArrayGroups(grps));
        adapter.setDropDownViewResource(R.layout.spinner_item);

        spiGroup.setAdapter(adapter);

        adapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_item, getStringArrayDate(excs));
        adapter.setDropDownViewResource(R.layout.spinner_item);

        spiDate.setAdapter(adapter);

        spiGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Object item = adapterView.getSelectedItem();

                filterGroup = item.toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spiDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Object item = adapterView.getSelectedItem();

                filterDate = item.toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private ArrayList<String> getStringArrayGroups(ArrayList<Group> arrayGroups) {

        ArrayList<String> result = new ArrayList<>();

        result.add("Seleccione Grupo");

        result.add("Cualquiera");

        for (int i = 0; i < arrayGroups.size(); i++) {

            result.add(arrayGroups.get(i).getGrupo());

        }

        return result;
    }

    private ArrayList<String> getStringArrayDate(ArrayList<Excursion> arrayExcs) {

        ArrayList<String> result = new ArrayList<>();

        result.add(arrayExcs.get(0).getDate());

        for (int i = 0; i < arrayExcs.size(); i++) {

            boolean control = true;

            for (int o = 0; o < result.size() && control; o++) {

                if (arrayExcs.get(i).getDate().equals(result.get(o))) {

                    //Está ya
                    control = false;

                }

            }

            if (control) {

                //No está y lo añadimos
                result.add(arrayExcs.get(i).getDate());

            }

        }

        //Ordenamos el Array
        orderArrayDate(result);

        ArrayList<String> aux = new ArrayList<>();

        aux.add("Seleccione Fecha");

        aux.add("Cualquiera");

        aux.addAll(result);//Añade el contenido del Array result en el aux

        result = aux;

        return result;
    }

    private void orderArrayDate(ArrayList<String> result) {

        Collections.sort(result, new Comparator<String>() {

            @Override
            public int compare(String arg0, String arg1) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                int compareResult = 0;
                try {
                    Date arg0Date = format.parse(arg0);
                    Date arg1Date = format.parse(arg1);
                    compareResult = arg0Date.compareTo(arg1Date);
                } catch (Exception e) {
                    e.printStackTrace();
                    compareResult = arg0.compareTo(arg1);
                }
                return compareResult;
            }
        });

    }

    public ArrayList<Excursion> deleteExc(int id) {

        for (int i = 0; i < excs.size(); i++) {

            if (excs.get(i).getId() == id) {

                excs.remove(i);
                break;

            }

        }


        return excs;
    }

    private void startApp() {

        contract.getArrays(this);

    }

    public void setArrayExc(ArrayList<Excursion> array) {

        excs = array;

    }

    public void setArrayGroups(ArrayList<Group> array) {

        grps = array;

    }

    public void setArrayTeachers(ArrayList<Teacher> array) {

        tchs = array;

    }

}
