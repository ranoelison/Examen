package com.example.mada_tour.network.body;

import com.example.mada_tour.modele.Utilisateur;
import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("user")
    private Utilisateur user;

    @SerializedName("token")
    private String token;

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
