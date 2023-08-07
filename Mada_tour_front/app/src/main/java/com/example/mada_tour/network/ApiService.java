package com.example.mada_tour.network;
import com.example.mada_tour.modele.Utilisateur;
import com.example.mada_tour.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    static String base_url = "http://192.168.43.105:3000/";
    @POST("login") // Remplacez "login" par le chemin relatif de votre endpoint de login
    Call<LoginResponse> performLogin(@Body Utilisateur loginRequest);

    // Ajoutez d'autres méthodes pour d'autres appels API, si nécessaire
}

