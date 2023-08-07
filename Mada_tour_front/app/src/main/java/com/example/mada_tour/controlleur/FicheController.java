package com.example.mada_tour.controlleur;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mada_tour.R;
import com.example.mada_tour.modele.Activite;
import com.example.mada_tour.modele.Avis;
import com.example.mada_tour.modele.AvisCallback;
import com.example.mada_tour.notification.NotificationUtils;
import com.example.mada_tour.utils.LangueMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FicheController {
    private Context context;
    private String base_url;

    public FicheController(Context context) {
        this.context = context;
        base_url  = context.getString(R.string.base_url_activite);
    }

    public void getActiviteAvisList(String activiteId, final AvisCallback callback) {
        String url = base_url + "listeAvisActivite/" + activiteId;
        System.out.println(url);
        List<Avis> listAvis = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");

                    System.out.println("----------------REPONSE LISTE AVIS ACTIVITE------------------");
                    System.out.println(data);
                    System.out.println("----------------------------------------------------------");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject avisObj = data.getJSONObject(i);

                        String id = avisObj.getString("_id");
                        String utilisateurs_id = avisObj.getString("utilisateurs_id");
                        String activite_id = avisObj.getString("activite_id");
                        Double note = avisObj.getDouble("note");
                        String contenu = avisObj.getString("contenu");
                        String date_pub = avisObj.getString("date_submit");

                        Avis avis = new Avis(id, utilisateurs_id, activite_id, note, contenu,date_pub);
                        listAvis.add(avis);
                    }
                    callback.onAvisListReady(listAvis);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error parsing JSON response", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG Volley API", "Error Volley API:" + error.toString());
                Toast.makeText(context, "Failed to load listAvis :" + error, Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
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
    public void addAvis(Avis avis){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String WSUrl = base_url + "addAvis";
        System.out.println("URL add avis "+WSUrl);
        /*
          utilisateurs_id : String,
    activite_id : String,
    note : Number,//entier note / 0<note<5
    contenu : String
         */
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("utilisateurs_id",avis.getUtilisateurs_id());
            jsonObject.put("activite_id",avis.getActivite_id());
            jsonObject.put("note",avis.getNote());
            jsonObject.put("contenu",avis.getContenu());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("----------------jsonObject---------------");
        System.out.println(jsonObject);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                WSUrl,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Traitement de la réponse JSON
                        try {
                            String status = response.getString("status");
                            if (status.equals("200")) {

                                Toast.makeText(context, "Avis envoyé", Toast.LENGTH_SHORT).show();
                                //--redirection vers dernier fragment used


                            } else {
                                Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();
                                throw new Exception(
                                        "Status"+response.getString("status")+"-"+response.getString("message")
                                );
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("VOLLEY ERREUR ");
                        error.printStackTrace();
                        System.out.println("------------------------------------------------------------");
                        Toast.makeText(context, "Echec de connexion au serveur", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Si vous avez besoin d'envoyer des en-têtes supplémentaires avec la requête, vous pouvez le faire ici
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }
}
