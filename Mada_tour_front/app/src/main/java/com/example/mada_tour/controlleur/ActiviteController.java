package com.example.mada_tour.controlleur;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mada_tour.modele.Activite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mada_tour.modele.ActivityCallback;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActiviteController {
    private final Context context;
    public ActiviteController(Context context){
        this.context = context;
    }

    private RequestQueue mRequestQueue;
//    A changer en l'adresse IP de l'ordi si API local
    private String base_url = "http://192.168.1.176:3000";

    public void getListeActivites(ActivityCallback callback){
//        // RequestQueue initialized
//        mRequestQueue = Volley.newRequestQueue(context);
//        // String Request initialized
//        String url = base_url+"/activity";
//        List<Activite> activities = new ArrayList<Activite>();
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try{
////                    Toast.makeText(context, "Response :" + response.length(), Toast.LENGTH_LONG).show();//display the response on screen
//                    for(int i =0; i<response.length();i++) {
//                        JSONObject responseObj = response.getJSONObject(i);
//                        String id = responseObj.getString("_id");
//                        String nom = responseObj.getString("nom");
//                        String type_activite = responseObj.getString("type_activite");
//                        String region = responseObj.getString("region");
//                        String img_url = responseObj.getString("images_url");
//                        String desc = responseObj.getString("description");
//                        Activite activite = new Activite(id, nom, type_activite,
//                                region, img_url, desc);
//                        activities.add(activite);
//                    }
////                    Toast.makeText(context, "ACTIVITY 2 :" + activities.size(), Toast.LENGTH_LONG).show();
//                    callback.onActiviteListReady(activities);
//                }
//                catch (Exception exception){
//                    Toast.makeText(context, "Error :" + exception, Toast.LENGTH_LONG).show();//display the response on screen
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("TAG Volley API", "Error Volley API:" + error.toString());
//                Toast.makeText(context, "Failed to load activities :" + error, Toast.LENGTH_LONG).show();//display the response on screen
//            }
//        });
//        mRequestQueue.add(jsonArrayRequest);
    }
}

