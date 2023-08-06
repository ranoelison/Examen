package com.example.mada_tour.modele;

public class Avis {
    /*
    * _id;
    _utilisateurs_id;
    _activite_id;
    _note;//entier note / 0<note<5
    _contenu;
    * */
    String id;
    String utilisateurs_id;
    String activite_id;
    Number note;
    String contenu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUtilisateurs_id() {
        return utilisateurs_id;
    }

    public void setUtilisateurs_id(String utilisateurs_id) {
        this.utilisateurs_id = utilisateurs_id;
    }

    public String getActivite_id() {
        return activite_id;
    }

    public void setActivite_id(String activite_id) {
        this.activite_id = activite_id;
    }

    public Number getNote() {
        return note;
    }

    public void setNote(Number note) {
        this.note = note;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Avis() {
    }

    public Avis(String id, String utilisateurs_id, String activite_id, Number note, String contenu) {
        this.id = id;
        this.utilisateurs_id = utilisateurs_id;
        this.activite_id = activite_id;
        this.note = note;
        this.contenu = contenu;
    }

    public Avis(String utilisateurs_id, String activite_id, Number note, String contenu) {
        this.utilisateurs_id = utilisateurs_id;
        this.activite_id = activite_id;
        this.note = note;
        this.contenu = contenu;
    }
}
