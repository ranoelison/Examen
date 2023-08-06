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
import com.example.mada_tour.utils.LangueMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class ActiviteController {
    private final Context context;
    private String base_url;
    public ActiviteController(Context context, String base_url){
        this.context = context;
        this.base_url = base_url;
    }

    private RequestQueue mRequestQueue;
//    A changer en l'adresse IP de l'ordi si API local

    public void getListeActivites(ActivityCallback callback,String filter){
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(context);
        // String Request initialized
        String url = base_url+"/activities/"+filter;
        List<Activite> activities = new ArrayList<Activite>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i =0; i<response.length();i++) {
                        JSONObject responseObj = response.getJSONObject(i);
                        String id = responseObj.getString("_id");
                        JSONArray nomJsonArray  = responseObj.getJSONArray("nom");
                        String type_activite = responseObj.getString("type_activite");
                        String region = responseObj.getString("region");
                        String img_url = responseObj.getString("images_url");
                        // Créer une liste pour les attributs 'nom' et 'description'
                        List<LangueMap> nomList = new ArrayList<>();
                        // Boucle d'extracton objets LangueMap du tableau JSON
                        for (int j = 0; j < nomJsonArray.length(); j++) {
                            JSONObject nomJsonObject = nomJsonArray.getJSONObject(j);
                            String langue = nomJsonObject.getString("langue");
                            String value = nomJsonObject.getString("value");
                            String _id = nomJsonObject.getString("_id");

                            // Créer un objet LangueMap et l'ajouter à la liste 'nomList'
                            LangueMap langueMap = new LangueMap(langue, value, _id);
                            nomList.add(langueMap);

                        }
                        Activite activite = new Activite(id,nomList,type_activite,region,img_url);
                        activities.add(activite);
                    }
                    callback.onActiviteListReady(activities);
                }
                catch (Exception exception){
                    Toast.makeText(context, "Error :" + exception, Toast.LENGTH_LONG).show();
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

