package com.example.juancarlos.tubbiz;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class Login extends Activity {

    private EditText dniT, passT;
    private Button mEnviar;
    private ProgressDialog pDialog;
    private TextView login;
    final Firebase ref = new Firebase(Tubbiz.FIREBASE_URL);

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = (TextView) findViewById(R.id.login_error);
        dniT = (EditText) findViewById(R.id.txtDNI);
        passT = (EditText) findViewById(R.id.txtPass);
        mEnviar = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Registro.class);
                startActivity(i);
            }
        });

        mEnviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String dni = dniT.getText().toString();
                String pass = passT.getText().toString();

                dni = dni.trim();
                pass = pass.trim();

                if (dni.isEmpty() || pass.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage(R.string.login_error_mensaje).setTitle(R.string.login_error_titulo).setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    final String dniCliente = dni;


                    // Login con DNI/Password
                    ref.authWithPassword(dni, pass, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            // Autenticación correcta
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("dni", dniCliente);
                            ref.child("Clientes").child(authData.getUid()).updateChildren(map);
                            Intent i = new Intent(Login.this, Principal.class);
                            i.addFlags(i.FLAG_ACTIVITY_NEW_TASK);
                            i.addFlags(i.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            // Autenticación incorrecta
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setMessage(firebaseError.getMessage())
                                    .setTitle(R.string.login_error_titulo)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }

                    });
                }
            }
        });
    }
}

