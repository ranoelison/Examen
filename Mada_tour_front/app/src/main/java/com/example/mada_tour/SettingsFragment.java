package com.example.mada_tour;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences sharedPreferences;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        // Obtenez une référence aux préférences partagées
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());

        // Ajoutez un écouteur pour écouter les changements du SwitchPreferenceCompat
        SwitchPreferenceCompat switchPreference = findPreference("dark");
        if (switchPreference != null) {
            switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    // Mettez à jour le thème de l'application en fonction de la nouvelle valeur du switch
                    boolean isDarkModeEnabled = (boolean) newValue;
                    if (isDarkModeEnabled) {
                        // Appliquer le thème sombre
                        getActivity().setTheme(R.style.NightTheme);
                    } else {
                        // Appliquer le thème par défaut (clair)
                        getActivity().setTheme(R.style.LightTheme);
                    }

                    // Recréez l'activité pour que le nouveau thème prenne effet
                    getActivity().recreate();

                    return true; // Indique que le changement de préférence doit être sauvegardé
                }
            });
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
}