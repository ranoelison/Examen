package com.example.mada_tour.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SessionManager {

    private static final String SHARED_PREF_NAME = "MyPrefs";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    public SessionManager(Context ctx) {
        context = ctx;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public boolean isConnected(){
        if(getToken()!=null&&getToken()!=""){
            System.out.println("TOKEN"+getToken());
            return true;
        }else{
            return false;
        }
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
        editor.clear();
        editor.apply();
        Toast.makeText(context, "Déconnecté", Toast.LENGTH_SHORT).show();
    }
}
