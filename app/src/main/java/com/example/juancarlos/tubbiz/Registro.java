package com.example.juancarlos.tubbiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class Registro extends Activity {

    protected EditText Nombre;
    protected EditText DNI;
    protected EditText Apellidos;
    protected EditText Email;
    protected EditText Pass;
    protected Button Registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        Nombre = (EditText) findViewById(R.id.txtNombre);
        DNI= (EditText) findViewById(R.id.txtDNI);
        Apellidos = (EditText) findViewById(R.id.txtApellidos);
        Email= (EditText) findViewById(R.id.txtEmail);
        Pass= (EditText) findViewById(R.id.txtPass);
        Registrar= (Button) findViewById(R.id.btnRegister);

        final Firebase ref= new Firebase(Tubbiz.FIREBASE_URL);

        Registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String dni= DNI.getText().toString();
                String password= Pass.getText().toString();

                dni= dni.trim();
                password= password.trim();

                if(dni.isEmpty() || password.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                    builder.setMessage(R.string.error_registro)
                            .setTitle(R.string.error_registro_titulo)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    ref.createUser(dni, password, new Firebase.ResultHandler(){
                        @Override
                        public void onSuccess(){
                            AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                            builder.setMessage(R.string.registro_correcto)
                                    .setPositiveButton(R.string.login_button_label, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Registro.this, Login.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                        @Override
                        public void onError(FirebaseError firebaseError){
                            AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                            builder.setMessage(firebaseError.getMessage())
                                    .setTitle(R.string.error_registro_titulo).setPositiveButton(android.R.string.ok,null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                }
            }
        });
    }
}
