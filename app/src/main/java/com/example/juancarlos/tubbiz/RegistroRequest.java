package com.example.juancarlos.tubbiz;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rein on 30/04/2016.
 */
public class RegistroRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://m13tubbiz.esy.es/registro.php";
    private Map<String, String> params;

    public RegistroRequest(String dni, String nombre, String apellido, String email, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("dni",dni);
        params.put("nombre", nombre);
        params.put("apellido",apellido);
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}