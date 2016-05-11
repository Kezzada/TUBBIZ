package com.example.juancarlos.tubbiz.Libros;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.juancarlos.tubbiz.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class InsertarLibro extends Activity implements View.OnClickListener {

    private Button bEscoger, bSubir;
    private ImageView portada;
    private EditText etIsbn, etNombre, etEditorial, etAutor, etGenero, etTipo, etPrecio;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1; // obtiene el mapa de bits de la galeria
    private String url = "http://m13tubbiz.esy.es/insertarLibro.php";
    private String key_imagen = "imagen";
    private String key_isbn = "isbn";
    private String key_nombre = "nombre";
    private String key_editorial = "editorial";
    private String key_autor = "autor";
    private String key_genero = "genero";
    private String key_tipo = "tipo";
    private String key_precio = "precio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_libro);
        bEscoger = (Button) findViewById(R.id.bEscoger);
        bSubir = (Button) findViewById(R.id.bSubir);
        etIsbn = (EditText) findViewById(R.id.etISBN);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etEditorial = (EditText) findViewById(R.id.etEditorial);
        etAutor = (EditText) findViewById(R.id.etAutor);
        etGenero = (EditText) findViewById(R.id.etGenero);
        etTipo = (EditText) findViewById(R.id.etTipo);
        etPrecio = (EditText) findViewById(R.id.etPrecio);

        portada = (ImageView) findViewById(R.id.imageView);

        bEscoger.setOnClickListener(this);
        bSubir.setOnClickListener(this);

    }

    public String pasarImagenCadena(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        //Mensaje al pulsar
        final ProgressDialog loading = ProgressDialog.show(this, "Subiendo imagen...", "Por favor espere...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(InsertarLibro.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(InsertarLibro.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = pasarImagenCadena(bitmap);

                // Recogida de valores
                String isbn = etIsbn.getText().toString();
                String name = etNombre.getText().toString().toLowerCase();
                String editorial = etEditorial.getText().toString().toLowerCase();
                String autor = etAutor.getText().toString().toLowerCase();
                String genero = etGenero.getText().toString().toLowerCase();
                String tipo = etTipo.getText().toString().toLowerCase();
                String precio = String.valueOf(etPrecio.getText());

                //Creating parameters
                Map<String, String> params = new Hashtable<>();

                //Adding parameters
                params.put(key_imagen, image);
                params.put(key_isbn, isbn);
                params.put(key_nombre, name);
                params.put(key_editorial, editorial);
                params.put(key_autor, autor);
                params.put(key_genero, genero);
                params.put(key_tipo, tipo);
                params.put(key_precio, precio);


                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void cogerImagenGaleria() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleciona una imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                Uri filePath = data.getData();
                try {
                    //Coger imagen de la galeria
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    bitmap = Bitmap.createScaledBitmap(bitmap, 350, 250, false);

                    //Pasar imagen a bitmap
                    portada.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v == bEscoger) {
            cogerImagenGaleria();
        }

        if (v == bSubir) {
            uploadImage();
        }
    }
}