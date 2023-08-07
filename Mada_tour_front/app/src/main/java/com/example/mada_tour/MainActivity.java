package com.example.mada_tour;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.mada_tour.databinding.ActivityMainBinding;
import com.example.mada_tour.fragments.FicheActiviteFragment;
import com.example.mada_tour.fragments.LoginFragment;
import com.example.mada_tour.notification.NotificationHelper;
import com.example.mada_tour.notification.NotificationUtils;
import com.example.mada_tour.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Vérifiez l'état actuel du switch lors de la création de l'activité
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("dark", false);
        // Maintenant, "isDarkModeEnabled" contient l'état actuel du switch
        if (isDarkModeEnabled) {
            // Le mode sombre est activé
            setTheme(R.style.NightTheme);
        } else {
            // Le mode sombre est désactivé
            setTheme(R.style.LightTheme);
        }
        NotificationHelper notifHelper = new NotificationHelper();
        notifHelper.createNotificationChannels(this);
        NotificationUtils notif = new NotificationUtils();
        notif.showNotification(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.activites) {
                replaceFragment(new ActivitiesFragment());
                //ici exemple comment appeler la fiche activite
                //appel new instance pas le constructeur
               // replaceFragment(new FicheActiviteFragment().newInstance("64cf95cf648338184d19eb8c"));
            } else if (itemId == R.id.settings) {
                replaceFragment(new SettingsFragment());
            }else if (itemId == R.id.login) {
                //System.out.println("YOU CLICKED on login nav");
                SessionManager sessionManager = new SessionManager(this);
                if(sessionManager.isConnected()){
                    showLogoutConfirmationDialog();
                }else{
                    replaceFragment(new LoginFragment());
                }
            }
            return true;
        });
//        setContentView(R.layout.activity_main);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Déconnexion");
        builder.setMessage("Voulez-vous vous déconnecter ?");
        builder.setPositiveButton("Déconnexion", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Appeler la méthode de déconnexion ici
                performLogout();
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void performLogout() {
        SessionManager sessionManager = new SessionManager(this);
        sessionManager.disconnect();
        replaceFragment(new LoginFragment());
    }
}