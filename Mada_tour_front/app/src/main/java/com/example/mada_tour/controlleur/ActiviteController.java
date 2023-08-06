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
import com.example.mada_tour.R;
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
import org.json.JSONException;
import org.json.JSONObject;

public class ActiviteController {
    private final Context context;
    private String base_url ;//"http://192.168.1.176:3000";
    public ActiviteController(Context context){
        this.context = context;
        base_url = context.getString(R.string.host);
    }

    private RequestQueue mRequestQueue;
//    A changer en l'adresse IP de l'ordi si API local


    public void getListeActivites(ActivityCallback callback){
        // RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(context);
        // String Request initialized
        String url = base_url+"/activity";
        List<Activite> activities = new ArrayList<Activite>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
//                    Toast.makeText(context, "Response :" + response.length(), Toast.LENGTH_LONG).show();//display the response on screen
                    for(int i =0; i<response.length();i++) {
                        JSONObject responseObj = response.getJSONObject(i);
                    /*    String id = responseObj.getString("_id");
                        String nom = responseObj.getString("nom");
                        String type_activite = responseObj.getString("type_activite");
                        String region = responseObj.getString("region");
                        String img_url = responseObj.getString("images_url");
                        String desc = responseObj.getString("description");*/
                        Activite activite =parseJsonResponse(responseObj);
                        activities.add(activite);
                    }
//                    Toast.makeText(context, "ACTIVITY 2 :" + activities.size(), Toast.LENGTH_LONG).show();
                    callback.onActiviteListReady(activities);
                }
                catch (Exception exception){
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
    private Activite parseJsonResponse(JSONObject response) throws JSONException {
        // Extraire les données JSON nécessaires pour créer l'objet Activite
        JSONObject dataObject = response.getJSONObject("data");
        String id = dataObject.getString("_id");
        JSONArray nomJsonArray = dataObject.getJSONArray("nom");
        JSONArray descriJsonArray = dataObject.getJSONArray("description");
        String typeActivite = dataObject.getString("type_activite");
        String region = dataObject.getString("region");
        String images_url = dataObject.getString("images_url");
        String tarifA = dataObject.getString("tarifA");
        String tarifE = dataObject.getString("tarifE");
        String horaires = dataObject.getString("horaires");
        String dayOff = dataObject.getString("dayOff");

        // Créer une liste pour les attributs 'nom' et 'description'
        List<LangueMap> nomList = new ArrayList<>();
        List<LangueMap> descriptionList = new ArrayList<>();

        // Boucle d'extracton objets LangueMap du tableau JSON
        for (int i = 0; i < nomJsonArray.length(); i++) {
            JSONObject nomJsonObject = nomJsonArray.getJSONObject(i);
            String langue = nomJsonObject.getString("langue");
            String value = nomJsonObject.getString("value");
            String _id = nomJsonObject.getString("_id");

            // Créer un objet LangueMap et l'ajouter à la liste 'nomList'
            LangueMap langueMap = new LangueMap(langue, value, _id);
            nomList.add(langueMap);
            //
            JSONObject descriJsonObject = descriJsonArray.getJSONObject(i);
            String langue_d = descriJsonObject.getString("langue");
            String value_d = descriJsonObject.getString("value");
            String _id_d = descriJsonObject.getString("_id");


            LangueMap langueMap_d = new LangueMap(langue_d, value_d, _id_d);
            descriptionList.add(langueMap_d);
        }


        Activite activite = new Activite(id,nomList,typeActivite,region,images_url,descriptionList,tarifA,tarifE,horaires,dayOff);

        return activite;
    }
}

