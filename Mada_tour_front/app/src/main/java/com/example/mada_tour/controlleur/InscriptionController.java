package com.example.mada_tour.controlleur;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mada_tour.HomeFragment;
import com.example.mada_tour.R;
import com.example.mada_tour.fragments.InscriptionFragment;
import com.example.mada_tour.notification.NotificationUtils;
import com.example.mada_tour.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InscriptionController {
    private Context mContext;
    private String base_url;

    public InscriptionController(Context context) {
        mContext = context;
        base_url = context.getString(R.string.base_url_user);
    }

    public void performSignUp(String nom, String prenom ,String mail,String password,String confPassword,String date_naissance) {

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        String WSUrl = base_url + "inscription";
        System.out.println("URL"+WSUrl);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nom", nom);
            jsonObject.put("prenom", prenom);
            jsonObject.put("mail", mail);
            jsonObject.put("password", password);
            jsonObject.put("confirmPassword", confPassword);
            jsonObject.put("date_naissance", date_naissance);
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

                                String token = response.getJSONObject("data").getString("token");
                                saveTokenToLocalStorage(token);
                                showToast("Connecté");
                                //--redirection vers dernier fragment used

                                //notif
                                NotificationUtils notificationUtils = new NotificationUtils();
                                notificationUtils.showSignUpNotification(mContext);


                            } else {
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
      /*  SharedPreferences sharedPreferences = mContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();*/
        SessionManager sessionManager  = new SessionManager(mContext);
        sessionManager.saveToken(token);
    }
}
