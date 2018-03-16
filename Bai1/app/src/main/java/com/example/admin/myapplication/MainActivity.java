package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private Button b;
    private TextView txtV;
    private TextView x,y;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.btn);
        txtV = (TextView) findViewById(R.id.txtView);

        requestQueue = Volley.newRequestQueue(this);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a,b;
                a =  ((EditText)findViewById(R.id.txte1)).getText().toString();
               b =  ((EditText)findViewById(R.id.txte2)).getText().toString();
                JsonObjectRequest request = new JsonObjectRequest("https://maps.googleapis.com/maps/api/geocode/json?latlng="+a+ ","+b+ "&key=AIzaSyDx_m1v5P2X5rSMEUOLVeT1_LNUjdcoHDI", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String address = response.getJSONArray("results").getJSONObject(0).getString("formatted_address");
                                    txtV.setText(address);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                requestQueue.add(request) ;

            }
        });
    }
}
