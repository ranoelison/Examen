package com.example.mada_tour.modele;

public class Utilisateur {
   /*  "nom": String,
            "prenom":String,
            "mail": String,
            "password":String,
            "date_inscription": Date*/
    String nom;
    String prenom;
    String mail;
    String password;
    String date_inscription;

    public Utilisateur(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public Utilisateur(String nom, String prenom, String mail, String password, String date_inscription) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.date_inscription = date_inscription;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(String date_inscription) {
        this.date_inscription = date_inscription;
    }
}
