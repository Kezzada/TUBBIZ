package com.example.juancarlos.tubbiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AreaUsuario extends Activity {

    private TextView dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_usuario);

        dni = (TextView) findViewById(R.id.tvDNI);

        Intent intent = getIntent();

        dni.setText("Bienvenido " + intent.getStringExtra(Login.KEY_USERNAME));
    }
}