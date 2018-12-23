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

public class Registro extends AppCompatActivity {
    EditText EditextRut;
    Button BTNregistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditextRut = findViewById(R.id.Editextrut);

        BTNregistrar = findViewById(R.id.BTNregistrar);

        BTNregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Rut = EditextRut.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            Boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                String Rut=jsonResponse.getString("Rut");
                                String Codigo_carnet=jsonResponse.getString("Codigo_carnet");
                                String Sufragio=jsonResponse.getString("Sufragio");
                                String estatus=jsonResponse.getString("Estado");
                                String Nombre=jsonResponse.getString("Nombre");
                                if (Sufragio.equals("si") ) {
                                    if (estatus.equals("no")||estatus.equals("ep") ) {



                                        Intent intent = new Intent(Registro.this, Registrar.class);
                                        intent.putExtra("Rut", Rut);
                                        intent.putExtra("Codigo_carnet", Codigo_carnet);


                                        Registro.this.startActivity(intent);
                                    }else {

                                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                                        builder.setMessage("Usted ya voto ").setNegativeButton("Retry",null).create().show();
                                    }

                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                                    builder.setMessage("No esta habiitado para sufragar").setNegativeButton("Retry",null).create().show();
                                }

                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                                builder.setMessage("Intentar otra vez").setNegativeButton("Retry",null).create().show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                ResultadoRequest loginRequest = new ResultadoRequest(Rut,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Registro.this);
                queue.add(loginRequest);

            }
        });

    }
}