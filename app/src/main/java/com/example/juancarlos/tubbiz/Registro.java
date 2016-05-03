package com.example.juancarlos.tubbiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends Activity implements View.OnClickListener {

    private EditText edDni;
    private EditText edNombre;
    private EditText edApellido;
    private EditText edEmail;
    private EditText edPassword;

    private Button bRegistro;

    private static final String REGISTER_URL = "http://m13tubbiz.esy.es/registro.php";
    private String respuesta = "registrado correctamente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edDni = (EditText) findViewById(R.id.etDNI);
        edNombre = (EditText) findViewById(R.id.etNombre);
        edApellido = (EditText) findViewById(R.id.etApellido);
        edEmail = (EditText) findViewById(R.id.etMail);
        edPassword = (EditText) findViewById(R.id.etPassword);
        bRegistro = (Button) findViewById(R.id.bRegistro);

        bRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bRegistro) {
            registerUser();
        }
    }

    private void registerUser() {
        String dni = edDni.getText().toString().trim().toLowerCase();
        String nombre = edNombre.getText().toString().trim().toLowerCase();
        String apellido = edApellido.getText().toString().trim().toLowerCase();
        String email = edEmail.getText().toString().trim().toLowerCase();
        String password = edPassword.getText().toString().trim().toLowerCase();

        register(dni, nombre, apellido, email, password);
    }

    private void register(final String dni, final String nombre, final String apellido, final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals(respuesta)) {
                            Toast.makeText(Registro.this,response,Toast.LENGTH_LONG).show();
                            onBackPressed();
                            finish();
                        } else {
                            Toast.makeText(Registro.this, "Hay campos vacíos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registro.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("dni", dni);
                params.put("nombre", nombre);
                params.put("apellido", apellido);
                params.put("email", email);
                params.put("password", password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}