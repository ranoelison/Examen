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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mada_tour.R;

import com.example.mada_tour.controlleur.FicheController;
import com.example.mada_tour.modele.Activite;
import com.example.mada_tour.modele.Avis;
import com.example.mada_tour.utils.LangueMap;
import com.example.mada_tour.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FicheActiviteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FicheActiviteFragment extends Fragment {
    private static final String ARG_ID_ACTIVITE = "id";
    String id_activite;
    boolean isActiviteFound = false;
    String avisVal,user_id;
    Number noteVal;
    private WebView f_nom_activite;
    private WebView f_type_activite;
    private WebView f_region;

    private WebView f_description;
    private WebView f_tarifA, f_tarifE, f_horaires, f_dayOff;
    private ImageView f_profil_image;
    private Button btnCommenter;//,submitAvis;
    private RatingBar newNote;
    private EditText textNewAvis;

    // Ajoutez d'autres TextViews pour les autres attributs
    public static FicheActiviteFragment newInstance(String id_activite) {
        FicheActiviteFragment fragment = new FicheActiviteFragment();
        Bundle args = new Bundle();
     /*   args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        args.putString(ARG_ID_ACTIVITE, id_activite);
        fragment.setArguments(args);
        System.out.println("Activite  a afficher" + id_activite);
        //  id_activite = fragment.getArguments().getString("id_activite");
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Utils utils = new Utils(view.getContext());
        boolean isLoggedIn = utils.isConnected();

        btnCommenter = view.findViewById(R.id.btn_commenter);
        newNote = view.findViewById(R.id.newNote);
        // submitAvis = view.findViewById(R.id.submitAvis);
        textNewAvis = view.findViewById(R.id.textNewAvis);

        // Show/hide the input and validation button based on login status
        if (isLoggedIn) {
            user_id = utils.getToken().split("_")[0];
            System.out.println("USER CONNECTE " + user_id);
            // User is logged in, show the input field and submit button


            btnCommenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Implement your logic to handle the comment submission here
                    // You can use the content of textNewAvis and newNote to submit the comment
                    // For example, you can send the comment to the server or save it locally.
                    // After submitting the comment, you can show a toast message to inform the user.
                    Toast.makeText(getActivity(), "Comment submitted", Toast.LENGTH_SHORT).show();
                      avisVal = textNewAvis.getText().toString();
                      noteVal= new Double(newNote.getRating());
                      submitAvis(user_id,avisVal,noteVal);
                    //= Math.round(rating);
                }
            });
        } else {
            // User is not logged in, show a toast message and redirect to the login screen when the button is clicked
            btnCommenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Redirect to the login screen
                    LoginFragment loginFragment = new LoginFragment();
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, loginFragment);
                    fragmentTransaction.addToBackStack(view.getTransitionName()); // add the transaction to the back stack to go back later
                    fragmentTransaction.commit();
                }
            });
        }
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


        ///
        btnCommenter = rootView.findViewById(R.id.btn_commenter);
        newNote = rootView.findViewById(R.id.newNote);
        // submitAvis = view.findViewById(R.id.submitAvis);
        textNewAvis = rootView.findViewById(R.id.textNewAvis);

        // Set the number of stars for the newNote (in this case, 5 stars)
        newNote.setNumStars(5);

        // Set the step size for the newNote (optional, if you want more granular ratings)
        newNote.setStepSize(1);
        // Faites l'appel à l'API et récupérez les données de l'activité
        FicheController ficheController = new FicheController(getActivity());
        ficheController.getActiviteData(this.id_activite, new FicheController.VolleyCallback() {
            @Override
            public void onSuccess(Activite activite) {

                System.out.println("ON success Activite Fragment");
                String baseUrl = ""; // Laissez vide ou spécifiez une base URL si nécessaire
                String mimeType = "text/html";
                String encoding = "UTF-8";
                LangueMap mapNom = lm.getLanguageContent(activite.getNom(), langage);
                LangueMap mapDesc = lm.getLanguageContent(activite.getDescription(), langage);
                f_nom_activite.loadDataWithBaseURL(baseUrl, mapNom.getValue(), mimeType, encoding, null);
                f_type_activite.loadDataWithBaseURL(baseUrl, activite.getType_activite(), mimeType, encoding, null);
                f_region.loadDataWithBaseURL(baseUrl, activite.getRegion(), mimeType, encoding, null);
                //   f_description.loadDataWithBaseURL(baseUrl,mapDesc.getValue() ,mimeType, encoding, null);
                f_tarifA.loadDataWithBaseURL(baseUrl, "<b>Adulte:</b>" + activite.getTarifA(), mimeType, encoding, null);
                f_tarifE.loadDataWithBaseURL(baseUrl, "<b>Enfant:</b>" + activite.getTarifE(), mimeType, encoding, null);
                f_horaires.loadDataWithBaseURL(baseUrl, "<b>Horaires:</b>" + activite.getHoraires(), mimeType, encoding, null);
                // f_horaires.loadDataWithBaseURL(baseUrl,"<b>:</b>"+activite.getHoraires() ,mimeType, encoding, null);
                f_dayOff.loadDataWithBaseURL(baseUrl, "<b>Jour(s) de Fermeture:</b>" + activite.getJoursFermetureFormatted(), mimeType, encoding, null);
                //web view
                // Activer l'interprétation des balises HTML
                f_description.getSettings().setJavaScriptEnabled(true);

                // Charger le contenu HTML dans le WebView
                String htmlDescri = mapDesc.getValue();

                f_description.loadDataWithBaseURL(baseUrl, htmlDescri, mimeType, encoding, null);

                // Optionnel : Définir un WebViewClient pour gérer les événements de navigation (liens cliqués, etc.)
                f_description.setWebViewClient(new WebViewClient());
                //set image
                String imageUrl = getActivity().getString(R.string.host) + activite.getImages_url();
                System.out.println("IMG" + imageUrl);
                // Utilisation de Glide pour charger l'image depuis l'URL dans l'ImageView
                Glide.with(getActivity()).load(imageUrl).into(f_profil_image);
            }

            @Override
            public void onError(String errorMessage) {
                // Gérez les erreurs ici, par exemple afficher un message d'erreur à l'utilisateur
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    String dayOffValue(String dayOff) {
        String dval = "";

        return dval;
    }

    //vis(String utilisateurs_id, String activite_id, Number note, String contenu) {
    private void submitAvis(String user_id, String commentaire, Number note) {

        Avis newComm = new Avis(user_id, this.id_activite, note, commentaire);
        FicheController ficheController = new FicheController(getActivity());
        ficheController.addAvis(newComm);
    }
}