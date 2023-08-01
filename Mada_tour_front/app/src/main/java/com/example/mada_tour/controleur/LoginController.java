package com.example.mada_tour.controleur;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.mada_tour.modele.*;
import com.example.mada_tour.network.ApiService;
import com.example.mada_tour.network.body.UserData;
import com.example.mada_tour.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginController {

    private Context mContext;

    public LoginController(Context context) {
        mContext = context;
    }
    static String base_url = "http://192.168.43.105:3000/utilisateur/";
    // Méthode pour effectuer la requête HTTP vers l'API de login en utilisant Retrofit
    public void performLogin(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url) // Remplacez par l'URL de votre API de login
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        System.out.println("PERFORM LOGIN OPENED "+base_url);
        ApiService apiService = retrofit.create(ApiService.class);
        Utilisateur loginRequest = new Utilisateur(username, password); // LoginRequest
        Call<LoginResponse> call = apiService.performLogin(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null && loginResponse.getStatus().equals("200")) {
                        // Connexion réussie, affichez le toast et stockez le token dans le local storage
                        String token = loginResponse.getData().getToken();
                        saveTokenToLocalStorage(token);
                        showToast("Connexion réussie!");
                    } else {
                        // Afficher un message d'erreur de connexion échouée
                        showToast("Connexion échouée");
                    }
                } else {
                    // Afficher un message d'erreur de connexion échouée (réponse non réussie)
                    showToast("Connexion échouée");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Gérer les erreurs liées à la requête (par exemple, problème de réseau)
                showToast("Erreur lors de la connexion");
            }
        });
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


