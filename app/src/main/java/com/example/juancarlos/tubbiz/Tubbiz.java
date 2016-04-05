package com.example.juancarlos.tubbiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Tubbiz extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tubbiz);

    }

    public void lanzarLogin(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
    public void lanzarRegistro(View view) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }


}
