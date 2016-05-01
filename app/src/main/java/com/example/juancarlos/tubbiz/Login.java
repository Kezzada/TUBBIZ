package com.example.juancarlos.tubbiz;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Login extends Activity{

    private EditText etDni;
    private EditText etPass;

    public static final String USER_DNI = "dni";
    HttpURLConnection urlConnection = null;
    String dni;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etDni = (EditText) findViewById(R.id.etDNI);
        etPass = (EditText) findViewById(R.id.etPassword);
    }

    public void invokeLogin(View view) {
        dni = etDni.getText().toString();
        password = etPass.getText().toString();

        login(dni, password);

    }

    private void login(final String dni, String password) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(Login.this, "Espera por favor", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String dni = params[0];
                String pass = params[1];

                InputStream is = null;
                ContentValues values = new ContentValues();
                values.put("dni", dni);
                values.put("password", pass);
                String result = null;

                try {
                    /*
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("m13tubbiz.esy.es/login.php");
                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();
*/

                    URL url = new URL("http://m13tubbiz.esy.es/login.php");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    is = urlConnection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                String s = result.trim();
                loadingDialog.dismiss();
                if (s.equalsIgnoreCase("success")) {
                    Intent intent = new Intent(Login.this, AreaUsuario.class);
                    intent.putExtra(USER_DNI, dni);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "DNI o Password invalidos", Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(dni, password);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
}