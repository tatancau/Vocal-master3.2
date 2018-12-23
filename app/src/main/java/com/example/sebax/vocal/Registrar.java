package com.example.sebax.vocal;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registrar extends AppCompatActivity {
EditText etvotante;
Button btnregistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

   etvotante=findViewById(R.id.EDTvotante);
   btnregistrar=findViewById(R.id.BTNregistrar);



   btnregistrar.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String Rut=etvotante.getText().toString();
           Response.Listener<String> responListener = new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   try {
                       JSONObject jsonObject = new JSONObject(response);
                       Boolean success = jsonObject.getBoolean("success");
                       if (success) {
                           AlertDialog.Builder builder = new AlertDialog.Builder(Registrar.this);
                           builder.setMessage("Usuario Registrado").setNegativeButton("Retry", null).create().show();
                           Intent intent = new Intent(Registrar.this, Registro.class);
                           Registrar.this.startActivity(intent);
                       } else {
                           AlertDialog.Builder builder = new AlertDialog.Builder(Registrar.this);
                           builder.setMessage("Intentar otra vez").setNegativeButton("Retry", null).create().show();

                       }
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }

           };
          RegisterRequest candiRequest = new RegisterRequest(Rut,responListener);
           RequestQueue queue = Volley.newRequestQueue(Registrar.this);
           queue.add(candiRequest);

       }
   });

    }
}
