package com.example.mada_tour.controlleur;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mada_tour.modele.Activite;
import com.example.mada_tour.modele.Destination;
import com.example.mada_tour.modele.DestinationCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActualiteController {
    private final Context context;
    public ActualiteController(Context context){
        this.context = context;
    }
    private RequestQueue mRequestQueue;
    //    A changer en l'adresse IP de l'ordi si API local
    private String base_url = "http://192.168.1.176:3000";

    public void getDestinations(DestinationCallback destinationCallback) {
        mRequestQueue = Volley.newRequestQueue(context);
        String url = base_url + "/actu";
        List<Destination> destinations = new ArrayList<Destination>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Toast.makeText(context, "Response :" + response.length(), Toast.LENGTH_LONG).show();//display the response on screen
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject responseObj = response.getJSONObject(i);
                        String id = responseObj.getString("_id");
                        String nom = responseObj.getString("nom");
                        String type_activite = responseObj.getString("type");
                        String region = responseObj.getString("region");
                        String img_url = responseObj.getString("img_url");
                        Destination destination = new Destination(id, nom, type_activite, region, img_url);
                        destinations.add(destination);
                    }
                    Toast.makeText(context, "DEST 2 :" + destinations.size(), Toast.LENGTH_LONG).show();
                    destinationCallback.onDestinationListReady(destinations);
                } catch (Exception exception) {
                    Toast.makeText(context, "Error :" + exception, Toast.LENGTH_LONG).show();//display the response on screen
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG Volley API", "Error Volley API:" + error.toString());
                Toast.makeText(context, "Failed to load activities :" + error, Toast.LENGTH_LONG).show();//display the response on screen
            }
        });
        mRequestQueue.add(jsonArrayRequest);
    }
}
