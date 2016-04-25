package com.example.juancarlos.tubbiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Tubbiz extends Activity {

    protected TextView fuentePers;
    protected String font_titulo = "font/Roboto-Regular.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tubbiz);
        fuentePers = (TextView) findViewById(R.id.registrar);
        Typeface font = Typeface.createFromAsset(getAssets(), font_titulo);
        fuentePers.setTypeface(font);

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
