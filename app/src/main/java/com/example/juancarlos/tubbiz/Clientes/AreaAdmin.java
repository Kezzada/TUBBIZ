package com.example.juancarlos.tubbiz.Clientes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juancarlos.tubbiz.Adaptadores.AdapterLibro;
import com.example.juancarlos.tubbiz.Beans.BeanLibro;
import com.example.juancarlos.tubbiz.Libros.InsertarLibro;
import com.example.juancarlos.tubbiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AreaAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String URL = "http://m13tubbiz.esy.es/listarLibros.php";
    private GridView galeria;
    private AdapterLibro adaptador;
    ArrayList<BeanLibro> listaLibros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        galeria = (GridView) findViewById(R.id.gridView);
        conectar();
    }
    public void conectar() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        listaLibros.clear();
                        try {
                            JSONArray jsonArray = new JSONArray(s);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject listaJson = jsonArray.getJSONObject(i);
                                String portada = listaJson.getString("portada");
                                String nombre = listaJson.getString("nombre");
                                Double precio = listaJson.getDouble("precio");

                                // Pasar imagen a Bitmap
                                byte[] imagenPortada = Base64.decode(portada, Base64.DEFAULT);
                                Bitmap bitmap = BitmapFactory.decodeByteArray(imagenPortada, 0, imagenPortada.length);

                                listaLibros.add(new BeanLibro(nombre, precio, bitmap));
                            }
                            adaptador = new AdapterLibro(AreaAdmin.this, listaLibros);
                            galeria.setAdapter(adaptador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Showing toast message of the response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(AreaAdmin.this, volleyError.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.area_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(this, InsertarLibro.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
