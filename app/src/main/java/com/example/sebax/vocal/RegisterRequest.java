package com.example.sebax.vocal;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebax on 16-07-2018.
 */

public class RegisterRequest extends StringRequest {

    private static final String EstadoCe_REQUEST_UML="http://192.168.0.7/EstadoVocal.php";
    private Map<String,String> params;

    public RegisterRequest(String Rut, Response.Listener<String> listener){
        super(Method.POST, EstadoCe_REQUEST_UML,listener,null);
        params=new HashMap<>();
        params.put("Rut",Rut);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
