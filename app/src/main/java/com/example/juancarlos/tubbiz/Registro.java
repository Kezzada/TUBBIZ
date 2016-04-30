package com.example.juancarlos.tubbiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final EditText etDni = (EditText) findViewById(R.id.etDNI);
        final EditText etNombre = (EditText) findViewById(R.id.etNombre);
        final EditText etApellido = (EditText) findViewById(R.id.etApellido);
        final EditText etEmail = (EditText) findViewById(R.id.etMail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bRegistro = (Button) findViewById(R.id.bRegistro);


        if (bRegistro != null) {
            bRegistro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String dni = etDni.getText().toString();
                    final String nombre = etNombre.getText().toString();
                    final String apellido = etApellido.getText().toString();
                    final String email = etEmail.getText().toString();
                    final String password = etPassword.getText().toString();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                              JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    Intent i = new Intent(Registro.this, Login.class);
                                    Registro.this.startActivity(i);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                                    builder.setMessage("Registro fallido").setNegativeButton("Reintentar", null).create().show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RegistroRequest registroRequest = new RegistroRequest(dni, nombre, apellido, email, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Registro.this);
                    queue.add(registroRequest);
                }
            });
        }


    }
}
