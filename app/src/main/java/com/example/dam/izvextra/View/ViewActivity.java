package com.example.dam.izvextra.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam.izvextra.Model.Pojo.Excursion;
import com.example.dam.izvextra.Presenter.Contract;
import com.example.dam.izvextra.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewActivity extends AppCompatActivity {

    private TextView tvPlace, tvDescripcion, tvGroup, tvTeacher, tvDate, tvHour;
    private FloatingActionButton fabPrint;
    private String permission;
    private Excursion exc;

    private Contract contract = new Contract();
    private final int PERMISSION_REQUEST_WRITE = 1;


    private void init() {

        tvPlace = findViewById(R.id.tvVPlace);
        tvDescripcion = findViewById(R.id.tvVDescription);
        tvGroup = findViewById(R.id.tvVGroup);
        tvTeacher = findViewById(R.id.tvVTeacher);
        tvDate = findViewById(R.id.tvVDate);
        tvHour = findViewById(R.id.tvVHour);
        fabPrint = findViewById(R.id.fabPrintV);

        fabPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
                checkPermission();
                Toast.makeText(ViewActivity.this, "Creado PDF en carpeta de DESCARGAS", Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbarV = findViewById(R.id.toolbarV);

        setSupportActionBar(toolbarV);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        init();

        exc = getIntent().getParcelableExtra("Excursion");

        loadData();

    }

    private void loadData() {

        ArrayList<String> grps = new ArrayList<>(Arrays.asList(exc.getGroups().split(", ")));

        ArrayList<String> tchs = new ArrayList<>(Arrays.asList(exc.getTeachers().split(", ")));

        tvPlace.setText(exc.getPlace());
        tvDescripcion.setText(exc.getDescription());
        tvGroup.setText(getStringtoArray(grps));
        tvTeacher.setText(getStringtoArray(tchs));
        tvDate.setText(exc.getDate());
        tvHour.setText(exc.getHour());

    }

    private String getStringtoArray(ArrayList<String> array) {

        String result = "";

        for (int i = 0; i < array.size(); i++) {

            if (i != array.size() - 1) {

                result += array.get(i) + "\n";

            } else {

                result += array.get(i);

            }

        }

        return result;
    }

    private void checkPermission() {

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                permission);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // Ha aceptado
            contract.printPDF(ViewActivity.this, exc);

        } else {
            // Ha denegado o es la primera vez que se le pregunta
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                // No se le ha preguntado aún
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSION_REQUEST_WRITE);
            } else {
                // Ha denegado
                Toast.makeText(this, "Por favor, habilite los permisos", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(i);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Estamos en el caso del teléfono
        switch (requestCode) {
            case PERMISSION_REQUEST_WRITE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(permission)) {
                    // Comprobar si ha sido aceptado o denegado la petición de permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        // Concedió su permiso
                        contract.printPDF(ViewActivity.this, exc);
                    } else {
                        // No concendió su permiso
                        Toast.makeText(this, "Has negado los permisos", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

}
