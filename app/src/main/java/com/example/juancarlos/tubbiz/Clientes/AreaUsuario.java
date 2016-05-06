package com.example.juancarlos.tubbiz.Clientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.juancarlos.tubbiz.R;

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