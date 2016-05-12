package com.example.juancarlos.tubbiz.Libros;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
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
import com.example.juancarlos.tubbiz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListarLibros extends Activity {

    public static final String URL = "http://m13tubbiz.esy.es/listarLibros.php";

    private GridView galeria;
    private AdapterLibro adaptador;
    ArrayList<BeanLibro> listaLibros = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_admin);
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
                            adaptador = new AdapterLibro(ListarLibros.this, listaLibros);
                            galeria.setAdapter(adaptador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Showing toast message of the response
                        Toast.makeText(ListarLibros.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ListarLibros.this, volleyError.getMessage().toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
