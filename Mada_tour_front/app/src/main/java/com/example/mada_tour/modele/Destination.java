package com.example.mada_tour.modele;

public class Destination {
    String _id;
    String nom;
    String type;
    String region;
    String img_url;

    public Destination(String _id, String nom, String type, String region, String img_url) {
        this._id = _id;
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.img_url = img_url;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
