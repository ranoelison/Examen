package com.example.mada_tour.controleur;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mada_tour.R;
import com.example.mada_tour.modele.Activite;
import com.example.mada_tour.utils.LangueMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class FicheController {
    private Context context;
    private String base_url;

    public FicheController(Context context) {
        this.context = context;
        base_url  = context.getString(R.string.base_url_activite);
    }

    public void getActiviteData(String activiteId, final VolleyCallback callback) {
        String url = base_url +"ficheActivite/"+ activiteId;
        System.out.println(url);
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("---------------RESPONSE JSON BRUT -----------");
                        System.out.println(response);
                        System.out.println("----------------------------------------------");
                        // Traitement de la réponse JSON
                        try {
                            // Convertir la réponse JSON en objet Activite
                            Activite activite = parseJsonResponse(response);
                            callback.onSuccess(activite);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError("Erreur lors du traitement de la réponse JSON");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError("Erreur de requête Volley");
                    }
                }
        );


        requestQueue.add(jsonObjectRequest);
    }

    //  réponse JSON en objet Activite


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

    // Interface pour renvoyer les résultats à l'appelant
    public interface VolleyCallback {
        void onSuccess(Activite activite);

        void onError(String errorMessage);
    }
}
