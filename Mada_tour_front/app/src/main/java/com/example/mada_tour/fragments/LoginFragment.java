package com.example.mada_tour.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mada_tour.HomeFragment;
import com.example.mada_tour.R;
import com.example.mada_tour.controlleur.LoginController;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText username, password;
    Button login;
    String userVar, passVar;


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Référence aux vues du layout ici
        username = view.findViewById(R.id.loginEmail);
        password = view.findViewById(R.id.loginPassword);
        login = view.findViewById(R.id.loginButton);

        // Appel de l'action login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userVar = username.getText().toString();
                passVar = password.getText().toString();
                if (userVar.equals("")) {
                    Toast.makeText(getActivity(), "Username cannot be blank", Toast.LENGTH_SHORT).show();
                } else if (passVar.equals("")) {
                    Toast.makeText(getActivity(), "password cannot be blank", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getApplicationContext(), "login method to proceed", Toast.LENGTH_SHORT).show();
                    performLogin(userVar, passVar);
                }
            }
        });

        //vers inscription

        TextView textViewSignup = view.findViewById(R.id.textViewSignup);


        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InscriptionFragment fragmentInscription = new InscriptionFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragmentInscription);
                fragmentTransaction.addToBackStack(null); // ajouter la transaction à la pile pour revenir en arrière
                fragmentTransaction.commit();
            }
        });
    }
    private void performLogin(String username, String password) {
        System.out.println("LOGINNNND "+username);
        LoginController loginController = new LoginController(getActivity());
        loginController.performLogin(username, password);
        //Toast.makeText(LoginActivity.this, "Connexion réussie!", Toast.LENGTH_SHORT).show();
        HomeFragment fragmentHome = new HomeFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragmentHome);
        fragmentTransaction.addToBackStack(null); // ajouter la transaction à la pile pour revenir en arrière
        fragmentTransaction.commit();
    }

}