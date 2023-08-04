package com.example.mada_tour.modele;

public class Activite {
    String _id;
    String nom;
    String type_activite;
    String region;
    String images_url;
    String description;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType_activite() {
        return type_activite;
    }

    public void setType_activite(String type_activite) {
        this.type_activite = type_activite;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getImages_url() {
        return images_url;
    }

    public void setImages_url(String images_url) {
        this.images_url = images_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Activite(String id ,String nom, String type_activite, String region, String images_url, String description) {
        this._id = id;
        this.nom = nom;
        this.type_activite = type_activite;
        this.region = region;
        this.images_url = images_url;
        this.description = description;
    }

    public Activite() {
    }
}
