package com.example.mada_tour.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mada_tour.R;
import com.example.mada_tour.controlleur.FicheController;
import com.example.mada_tour.modele.Avis;
import com.example.mada_tour.modele.AvisCallback;
import com.example.mada_tour.utils.AvisAdapter;

import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AvisActiviteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvisActiviteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_ID_ACTIVITE = "id";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String id_activite;


    //--
    private TextView textViewMoyenneNotes;
    public AvisActiviteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id_activite
     * @return A new instance of fragment AvisActiviteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AvisActiviteFragment newInstance(String id_activite) {
        AvisActiviteFragment fragment = new AvisActiviteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID_ACTIVITE, id_activite);
        fragment.setArguments(args);
        System.out.println(" Activite Avis   a afficher" + id_activite);
        return fragment;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ...
        textViewMoyenneNotes = view.findViewById(R.id.textViewMoyenneNotes);
        FicheController ficheController = new FicheController(getContext());

        ficheController.getActiviteAvisList(id_activite, new AvisCallback() {
            @Override
            public void onAvisListReady(List<Avis> avisList) {
                // Ici, vous avez la liste des avis pour l'activité spécifiée
                // Vous pouvez maintenant les utiliser pour afficher les données sur l'interface utilisateur

                // Par exemple, vous pouvez utiliser cette liste pour mettre à jour une RecyclerView contenant les avis
                RecyclerView recyclerViewAvis = view.findViewById(R.id.recyclerViewAvis);
                AvisAdapter avisAdapter = new AvisAdapter(avisList);
                recyclerViewAvis.setAdapter(avisAdapter);
                recyclerViewAvis.setLayoutManager(new LinearLayoutManager(getContext()));
                // Mettez à jour la moyenne des notes en haut du RecyclerView
                float moyenneNotes = calculateAverage(avisList);
                String formattedMoyenne = String.format(Locale.getDefault(), "%.2f", moyenneNotes);
                textViewMoyenneNotes.setText("Moyenne des notes : " + formattedMoyenne);
            }
        });

        // ...
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            this.id_activite = getArguments().getString(ARG_ID_ACTIVITE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
          /*  mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
            this.id_activite = getArguments().getString(ARG_ID_ACTIVITE);
        }
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_avis_activite, container, false);
    }
    // Méthode pour calculer la moyenne des notes
    private float calculateAverage(List<Avis> avisList) {
        float totalNotes = 0;
        for (Avis avis : avisList) {
            totalNotes = totalNotes + avis.getNote().floatValue();
        }
        return avisList.size() > 0 ? totalNotes / avisList.size() : 5; //si aucune avis = note complet
    }
}