package com.example.sebax.vocal;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebax on 16-07-2018.
 */

public class ResultadoRequest extends StringRequest {

    private static final String RESULTADO_REQUEST_URL="http://192.168.0.7/Vocal.php";
    private Map<String,String> params;
    public ResultadoRequest( String Rut, Response.Listener<String> listener){
        super(Request.Method.POST, RESULTADO_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("Rut",Rut);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
