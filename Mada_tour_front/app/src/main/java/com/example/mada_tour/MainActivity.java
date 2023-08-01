package com.example.mada_tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mada_tour.vue.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Liez la Toolbar à l'activité
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        // Pour activer le bouton "Retour" dans la Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Pour ajouter un événement de clic sur la Toolbar
        toolbar.setNavigationOnClickListener(view -> {
            // Définir l'action lorsque la Toolbar est cliquée (retour, par exemple)
            onBackPressed();
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_login) {
            // Lorsque l'élément "Login" de la Toolbar est sélectionné,
            // lancez l'activité ActivityLogin
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}