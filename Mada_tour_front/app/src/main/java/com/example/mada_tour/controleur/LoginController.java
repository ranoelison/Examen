package com.example.mada_tour.controleur;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mada_tour.R;
import com.example.mada_tour.modele.Utilisateur;
import com.example.mada_tour.network.body.UserData;
import com.example.mada_tour.network.response.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private Context mContext;
    private String base_url;
    public LoginController(Context context) {
        mContext = context;
        base_url = context.getString(R.string.base_url_user);
    }



    // Méthode pour effectuer la requête HTTP vers l'API de login en utilisant Volley
    public void performLogin(String username, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        String loginUrl = base_url + "login";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mail", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                loginUrl,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Traitement de la réponse JSON
                        try {
                            String status = response.getString("status");
                            if (status.equals("200")) {
                                // Connexion réussie, affichez le toast et stockez le token dans le local storage
                                String token = response.getJSONObject("data").getString("token");
                                saveTokenToLocalStorage(token);
                               // showToast("Connexion réussie!");
                                System.out.println("OK CONNEXION");

                            } else {
                                // Afficher un message d'erreur de connexion échouée
                              //  showToast("Connexion échouée");
                                System.out.println("ECHEC CONNEXION ");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                           // showToast("Erreur lors de la réponse du serveur");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("VOLLEY ERREUR ");
                        error.printStackTrace();
                        System.out.println("------------------------------------------------------------");
//                        showToast("Erreur lors de la connexion");
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

    // Méthode pour afficher un toast
    private void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    // Dans votre méthode saveTokenToLocalStorage du LoginController

    private void saveTokenToLocalStorage(String token) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }
}
