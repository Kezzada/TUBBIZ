package com.example.juancarlos.tubbiz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Registro extends Activity implements View.OnClickListener {

    private EditText edDni;
    private EditText edNombre;
    private EditText edApellido;
    private EditText edEmail;
    private EditText edPassword;

    private Button bRegistro;

    private static final String REGISTER_URL = "http://m13tubbiz.esy.es/registro.php";


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
        if(v == bRegistro){
            registerUser();
        }
    }

    private void registerUser() {
        String dni = edDni.getText().toString().trim().toLowerCase();
        String nombre = edNombre.getText().toString().trim().toLowerCase();
        String apellido = edApellido.getText().toString().trim().toLowerCase();
        String email = edEmail.getText().toString().trim().toLowerCase();
        String password = edPassword.getText().toString().trim().toLowerCase();

        register(dni,nombre,apellido,email,password);
    }

    private void register(String dni, String nombre, String apellido, String email, String password) {
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Registro.this, "Por favor, espera",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("dni",params[0]);
                data.put("nombre",params[1]);
                data.put("apellido",params[2]);
                data.put("email",params[3]);
                data.put("password",params[4]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(dni,nombre,apellido,password,email);
    }
}