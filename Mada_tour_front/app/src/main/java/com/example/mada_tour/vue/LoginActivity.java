package com.example.mada_tour.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mada_tour.R;
import com.example.mada_tour.controlleur.LoginController;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login;
    String userVar, passVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userVar = username.getText().toString();
                passVar = password.getText().toString();
                if (userVar.equals("")) {
                    Toast.makeText(getApplicationContext(), "Username cannot be blank", Toast.LENGTH_SHORT).show();
                } else if (passVar.equals("")) {
                    Toast.makeText(getApplicationContext(), "password cannot be blank", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getApplicationContext(), "login method to proceed", Toast.LENGTH_SHORT).show();
                    performLogin(userVar, passVar);
                }
            }
        });
    }
    private void performLogin(String username, String password) {
        System.out.println("LOGINNNND "+username);
        LoginController loginController = new LoginController(LoginActivity.this);

        loginController.performLogin(username, password);
       // Toast.makeText(LoginActivity.this, "Connexion réussie!", Toast.LENGTH_SHORT).show();
    }
}