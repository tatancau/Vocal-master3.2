package com.example.sebax.vocal;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {
    Button BTNdatos;
    EditText  ETrut;
    TextView TVresultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BTNdatos = findViewById(R.id.BTNdatos);
        ETrut = findViewById(R.id.ETrut);


        BTNdatos.setOnClickListener(new View.OnClickListener() {


    @Override
    public void onClick(View view) {
        final String Rut = ETrut.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    Boolean success = jsonResponse.getBoolean("success");
                    if (success) {

                        String Vocal=jsonResponse.getString("Vocal");
                        if (Vocal.equals("si")) {
                            Intent intentReg = new Intent(MainActivity.this, Registro.class);
                            MainActivity.this.startActivity(intentReg);


                        }

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Usted no tiene permitido usar esta aplicacion").setNegativeButton("Retry", null).create().show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        ResultadoRequest resultadoRequest = new ResultadoRequest( Rut, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(resultadoRequest);
    }
});
    }
}
