package com.example.mada_tour.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Enregistrer le token dans SharedPreferences
    public void saveToken(String token) {
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    // Obtenir le token enregistré dans SharedPreferences
    public String getToken() {
        return  sharedPreferences.getString(KEY_TOKEN, "");

    }

    // Supprimer le token et déconnecter l'utilisateur
    public void disconnect() {
        editor.remove(KEY_TOKEN);
        editor.apply();
    }
}
