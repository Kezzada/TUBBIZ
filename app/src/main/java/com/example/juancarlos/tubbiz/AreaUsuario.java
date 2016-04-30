package com.example.juancarlos.tubbiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AreaUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_usuario);

        final EditText etDni = (EditText) findViewById(R.id.etDNI);
        final EditText etNombre = (EditText) findViewById(R.id.etNombre);
        final TextView mensajeBienvenida = (TextView) findViewById(R.id.tvBienvenido);


    }
}
