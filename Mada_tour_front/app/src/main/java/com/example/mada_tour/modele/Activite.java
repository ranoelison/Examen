package com.example.mada_tour.modele;

import com.example.mada_tour.utils.LangueMap;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Activite {
    String id;
    List<LangueMap> nom;
    String type_activite;
    String region;
    String images_url;
    List<LangueMap> description;
    String tarifA;
    String tarifE;
    String horaires;
    String dayOff;


    public String getJoursFermetureFormatted() {
        List<Integer> joursFermeture = getJoursFermeture(dayOff);
        StringBuilder joursFermetureFormatted = new StringBuilder();

        DateFormatSymbols dfs = new DateFormatSymbols(Locale.getDefault());
        String[] joursSemaine = dfs.getWeekdays();

        for (int jour : joursFermeture) {
            joursFermetureFormatted.append(joursSemaine[jour]);
            joursFermetureFormatted.append("-");
        }

        // Supprimer le dernier "-"
        if (joursFermetureFormatted.length() > 0) {
            joursFermetureFormatted.setLength(joursFermetureFormatted.length() - 1);
        }

        return joursFermetureFormatted.toString();
    }
    // Méthode pour récupérer la liste des jours de fermeture à partir de la chaîne dayOff
    public List<Integer> getJoursFermeture(String dayOff) {
        List<Integer> joursFermeture = new ArrayList<>();

        // Séparer les jours de fermeture en utilisant le séparateur "-"
        String[] jours = dayOff.split("-");

        try {
            if (jours.length == 2) {
                // Convertir les jours en entiers
                int jourDebut = Integer.parseInt(jours[0]);
                int jourFin = Integer.parseInt(jours[1]);

                // Ajouter les jours de fermeture à la liste
                for (int i = jourDebut; i <= jourFin; i++) {
                    joursFermeture.add(i);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return joursFermeture;
    }


    public boolean isClosedNow() {
        // dimanche = 1, lundi = 2, ..., samedi = 7
        int jourActuel = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        List<Integer> joursFermeture = getJoursFermeture(dayOff);
        return joursFermeture.contains(jourActuel);
    }

    //Constructor - Getter & Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LangueMap> getNom() {
        return nom;
    }

    public void setNom(List<LangueMap> nom) {
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

    public List<LangueMap> getDescription() {
        return description;
    }

    public void setDescription(List<LangueMap> description) {
        this.description = description;
    }

    public String getTarifA() {
        return tarifA;
    }

    public void setTarifA(String tarifA) {
        this.tarifA = tarifA;
    }

    public String getTarifE() {
        return tarifE;
    }

    public void setTarifE(String tarifE) {
        this.tarifE = tarifE;
    }

    public String getHoraires() {
        return horaires;
    }

    public void setHoraires(String horaires) {
        this.horaires = horaires;
    }

    public String getDayOff() {
        return dayOff;
    }

    public void setDayOff(String dayOff) {
        this.dayOff = dayOff;
    }

    public Activite() {
    }

    public Activite(String id, List<LangueMap> nom, String type_activite, String region,
                    String images_url, List<LangueMap> description, String tarifA,
                    String tarifE, String horaires, String dayOff) {
        this.id = id;
        this.nom = nom;
        this.type_activite = type_activite;
        this.region = region;
        this.images_url = images_url;
        this.description = description;
        this.tarifA = tarifA;
        this.tarifE = tarifE;
        this.horaires = horaires;
        this.dayOff = dayOff;
    }

    public Activite(String id, List<LangueMap> nom, String type_activite, String region, String images_url) {
        this.id = id;
        this.nom = nom;
        this.type_activite = type_activite;
        this.region = region;
        this.images_url = images_url;
    }
}
