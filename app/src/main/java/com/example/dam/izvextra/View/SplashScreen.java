package com.example.dam.izvextra.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.dam.izvextra.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {

    // Establece duracion del splash
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        // Tambies se puede hacer con un Thread
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Inicia la nueva actividad
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);

                // Finaliza la actividad porque no se puede volver dandole al boton atras
                finish();
            }
        };

        // Simula una proceso de carga largo
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

}