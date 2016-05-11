package com.example.juancarlos.tubbiz.Libros;

import android.app.Activity;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Rein on 11/05/2016.
 */
public class ListarLibros extends Activity {

    public static final String URL = "http://m13tubbiz.esy.es/listarLibros.php";
    public static final String TAG_ISBN = "isbn";
    public static final String TAG_NOMBRE = "nombre";
    public static final String TAG_EDITORIAL = "editorial";
    public static final String TAG_AUTOR = "autor";
    public static final String TAG_GENERO = "genero";
    public static final String TAG_TIPO = "tipo";
    public static final String TAG_PRECIO = "precio";
    public static final String TAG_PORTADA = "imagen";

    private GridView galeria;

    private ArrayList<String> imagen;
    private ArrayList<String> isbn;




}
