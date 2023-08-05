package com.example.mada_tour.utils;

import java.util.List;

public class LangueMap {
    String langue;
    String value;
    String _id;

    public LangueMap() {
    }

    public LangueMap getLanguageContent(List<LangueMap> obj, String language){
        for(int i=0;i<obj.size();i++){
            LangueMap langueMap = obj.get(i);
            if(langueMap.langue.equalsIgnoreCase(language)){
                return langueMap;
            }
        }
        return null;
    }
    public String getValueForLanguage(String language) {
        if (langue.equals(language)) {
            return value;
        } else if ("FR".equals(language)) {
            return value; // Valeur par défaut si la langue spécifiée n'est pas trouvée
        }
        return "none"; // Valeur par défaut si la langue spécifiée et "FR" ne sont pas trouvées
    }
    public LangueMap(String langue, String value) {
        this.langue = langue;
        this.value = value;
    }
    public LangueMap(String langue, String value, String _id) {
        this.langue = langue;
        this.value = value;
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
