package com.example.juancarlos.tubbiz.Clientes;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juancarlos.tubbiz.R;

import java.util.HashMap;
import java.util.Map;


public class Login extends Activity implements View.OnClickListener{

    private EditText etDni;
    private EditText etPass;

    public static final String LOGIN_URL = "http://m13tubbiz.esy.es/login.php";

    public static final String KEY_USERNAME="dni";
    public static final String KEY_PASSWORD="password";

    private Button buttonLogin;

    private String dni;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etDni = (EditText) findViewById(R.id.etDNI);
        etPass = (EditText) findViewById(R.id.etPassword);

        buttonLogin = (Button) findViewById(R.id.bLogin);
        buttonLogin.setOnClickListener(this);
    }


    private void userLogin() {
        dni = etDni.getText().toString().trim();
        pass = etPass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            openProfile();
                        }else{
                            Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put(KEY_USERNAME,dni);
                map.put(KEY_PASSWORD,pass);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        Intent intent = new Intent(this, AreaUsuario.class);
        intent.putExtra(KEY_USERNAME, dni);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        userLogin();
    }
}