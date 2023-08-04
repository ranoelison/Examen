package com.example.mada_tour.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mada_tour.R;
import com.example.mada_tour.controleur.InscriptionController;
import com.example.mada_tour.controleur.LoginController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InscriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InscriptionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText editTextDateNaissance;
    private Calendar calendar;
    Button buttonDatePicker;
    //---
    EditText nom,prenom,mail,password,confPassword;
    Button signup;
    String nomVal,prenomVal,mailVal,passwordVal,confPasswordVal,date_naissance;

    public InscriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InscriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InscriptionFragment newInstance(String param1, String param2) {
        InscriptionFragment fragment = new InscriptionFragment();
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
        View view = inflater.inflate(R.layout.fragment_inscription, container, false);

        editTextDateNaissance = view.findViewById(R.id.editTextDateNaissance);
        buttonDatePicker = view.findViewById(R.id.buttonDatePicker);

        // Initialisez le calendrier pour stocker la date sélectionnée
        calendar = Calendar.getInstance();

        // Définir un écouteur de clic pour ouvrir le DatePicker
        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Définir un écouteur de clic sur l'EditText pour également ouvrir le DatePicker
        editTextDateNaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Référence aux vues du layout ici
        nom = view.findViewById(R.id.nom);
        prenom = view.findViewById(R.id.prenom);
        mail = view.findViewById(R.id.editTextMail);
        password = view.findViewById(R.id.editTextPassword);
        confPassword = view.findViewById(R.id.editTextPasswordConfirm);
        signup = view.findViewById(R.id.buttonInscription);
        //buttonDatePicker = view.findViewById(R.id.buttonDatePicker);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs des EditText pour chaque champ
                nomVal = nom.getText().toString();
                prenomVal = prenom.getText().toString();
                mailVal = mail.getText().toString();
                passwordVal = password.getText().toString();
                confPasswordVal = confPassword.getText().toString();

                int jour = calendar.get(Calendar.DAY_OF_MONTH);
                int mois = calendar.get(Calendar.MONTH) + 1; // Les mois dans Calendar commencent à 0, donc on ajoute 1
                int annee = calendar.get(Calendar.YEAR);
                date_naissance = jour + "/" + mois + "/" + annee;
               //peut ajouter condition qualif champ
                performSignUp(nomVal,prenomVal,mailVal,passwordVal,confPasswordVal,date_naissance);
            }
        });
    }
    private void performSignUp(String nom, String prenom ,String mail,String password,String confPassword,String date_naissance) {
        InscriptionController inscriptionController = new InscriptionController(getActivity());
        inscriptionController.performSignUp(  nom,  prenom , mail, password, confPassword, date_naissance);

    }
    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Mettez à jour le calendrier avec la date sélectionnée
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Afficher la date sélectionnée dans l'EditText
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                editTextDateNaissance.setText(dateFormat.format(calendar.getTime()));
            }
        };

        // Obtenez l'année, le mois et le jour actuels à partir du calendrier
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Créez une instance du DatePickerDialog et affichez-la
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                dateSetListener,
                year,
                month,
                day
        );
        datePickerDialog.show();
    }
}