package com.example.mada_tour.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mada_tour.R;
import com.example.mada_tour.controleur.FicheController;
import com.example.mada_tour.modele.Activite;
import com.example.mada_tour.utils.LangueMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FicheActiviteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FicheActiviteFragment extends Fragment {
    private  static final String ARG_ID_ACTIVITE = "id" ;
    String id_activite;

    private WebView f_nom_activite;
    private WebView f_type_activite;
    private WebView f_region;

    private WebView f_description;
    private WebView  f_tarifA,f_tarifE,f_horaires,f_dayOff;
    private ImageView f_profil_image;
    // Ajoutez d'autres TextViews pour les autres attributs
    public static FicheActiviteFragment newInstance(String id_activite) {
        FicheActiviteFragment fragment = new FicheActiviteFragment();
        Bundle args = new Bundle();
     /*   args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        args.putString(ARG_ID_ACTIVITE,id_activite);
        fragment.setArguments(args);
        System.out.println("Activite  a afficher"+id_activite);
      //  id_activite = fragment.getArguments().getString("id_activite");
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
          /*  mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
            this.id_activite = getArguments().getString(ARG_ID_ACTIVITE);
        }
        View rootView = inflater.inflate(R.layout.fragment_fiche_activite, container, false);
        String langage = this.getString(R.string.user_lang);
        LangueMap lm = new LangueMap();
        ///
        f_nom_activite = rootView.findViewById(R.id.f_nom_activite);
        f_type_activite = rootView.findViewById(R.id.f_type_activite);
        f_region = rootView.findViewById(R.id.f_region);
        f_description = rootView.findViewById(R.id.f_description);
        f_tarifA = rootView.findViewById(R.id.f_tarifA);
        f_tarifE = rootView.findViewById(R.id.f_tarifE);
        f_horaires = rootView.findViewById(R.id.f_horaires);
        f_dayOff = rootView.findViewById(R.id.f_dayOff);
        f_profil_image = rootView.findViewById(R.id.f_profil_image);

        // Faites l'appel à l'API et récupérez les données de l'activité
        FicheController ficheController = new FicheController(getActivity());
        ficheController.getActiviteData(this.id_activite, new FicheController.VolleyCallback() {
            @Override
            public void onSuccess(Activite activite) {
                System.out.println("ON success Activite Fragment");
                String baseUrl = ""; // Laissez vide ou spécifiez une base URL si nécessaire
                String mimeType = "text/html";
                String encoding = "UTF-8";
                LangueMap mapNom =  lm.getLanguageContent(activite.getNom(),langage);
                LangueMap mapDesc = lm.getLanguageContent(activite.getDescription(),langage);
                f_nom_activite.loadDataWithBaseURL(baseUrl,mapNom.getValue() ,mimeType, encoding, null);
                f_type_activite.loadDataWithBaseURL(baseUrl,activite.getType_activite() ,mimeType, encoding, null);
                f_region.loadDataWithBaseURL(baseUrl,activite.getRegion() ,mimeType, encoding, null);
                //   f_description.loadDataWithBaseURL(baseUrl,mapDesc.getValue() ,mimeType, encoding, null);
                f_tarifA.loadDataWithBaseURL(baseUrl,"<b>Adulte:</b>"+activite.getTarifA() ,mimeType, encoding, null);
                f_tarifE.loadDataWithBaseURL(baseUrl,"<b>Enfant:</b>"+activite.getTarifE() ,mimeType, encoding, null);
                f_horaires.loadDataWithBaseURL(baseUrl,"<b>Horaires:</b>"+activite.getHoraires() ,mimeType, encoding, null);
               // f_horaires.loadDataWithBaseURL(baseUrl,"<b>:</b>"+activite.getHoraires() ,mimeType, encoding, null);
                f_dayOff.loadDataWithBaseURL(baseUrl,"<b>Jour(s) de Fermeture:</b>"+activite.getJoursFermetureFormatted() ,mimeType, encoding, null);
                //web view
                // Activer l'interprétation des balises HTML
                f_description.getSettings().setJavaScriptEnabled(true);

                // Charger le contenu HTML dans le WebView
                String htmlDescri = mapDesc.getValue();

                f_description.loadDataWithBaseURL(baseUrl, htmlDescri, mimeType, encoding, null);

                // Optionnel : Définir un WebViewClient pour gérer les événements de navigation (liens cliqués, etc.)
                f_description.setWebViewClient(new WebViewClient());
                //set image
                String imageUrl = getActivity().getString(R.string.host)+activite.getImages_url();
                System.out.println("IMG"+imageUrl);
                // Utilisation de Glide pour charger l'image depuis l'URL dans l'ImageView
                Glide.with(getActivity())
                        .load(imageUrl)
                        .into(f_profil_image);
            }

            @Override
            public void onError(String errorMessage) {
                // Gérez les erreurs ici, par exemple afficher un message d'erreur à l'utilisateur
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
    String dayOffValue(String dayOff){
        String dval="";

        return dval;
    }
}