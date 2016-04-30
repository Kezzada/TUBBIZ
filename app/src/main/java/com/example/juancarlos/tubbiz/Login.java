package com.example.juancarlos.tubbiz;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etDni = (EditText) findViewById(R.id.etDNI);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegistro);
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        if (tvRegisterLink != null) {
            tvRegisterLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent registerIntent = new Intent(Login.this, Registro.class);
                    Login.this.startActivity(registerIntent);
                }
            });
        }

        if (bLogin != null) {
            bLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String dni = etDni.getText().toString();
                    final String password = etPassword.getText().toString();

                    // Respuesta del servidor
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    String nombre = jsonResponse.getString("nombre");
                                    String apellido = jsonResponse.getString("apellido");
                                    String email = jsonResponse.getString("email");

                                    Intent intent = new Intent(Login.this, AreaUsuario.class);
                                    intent.putExtra("dni", dni);
                                    intent.putExtra("nombre", nombre);
                                    intent.putExtra("apellido", apellido);
                                    intent.putExtra("email",email);
                                    intent.putExtra("password",password);
                                    Login.this.startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                    builder.setMessage("¡Fallo al logear!")
                                            .setNegativeButton("Reintentar", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(dni, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    queue.add(loginRequest);
                }
            });
        }
    }
}