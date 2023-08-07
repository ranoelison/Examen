package com.example.mada_tour.utils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mada_tour.R;
import com.example.mada_tour.fragments.FicheActiviteFragment;
import com.example.mada_tour.fragments.InscriptionFragment;
import com.example.mada_tour.modele.Activite;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ViewHolder> {
    private List<Activite> activiteList = new ArrayList<Activite>();
    private Fragment fragment;
    private String baseUrl;

    public ActivitiesAdapter(List<Activite> activites, Fragment fragment,String baseUrl) {
        this.activiteList = activites;
        this.fragment = fragment;
        this.baseUrl = baseUrl;
    }

    public void setStringList(List<Activite> activites) {
        this.activiteList = activites;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String stringItem = stringList.get(position);
        Activite currentActivite = activiteList.get(position);
        String langage = fragment.getString(R.string.user_lang);
        LangueMap lm = new LangueMap();
        LangueMap mapNom =  lm.getLanguageContent(currentActivite.getNom(),langage);
        holder.textViewTitle.setText(mapNom.getValue());
        String type = currentActivite.getType_activite();
        String region = currentActivite.getRegion();
        holder.textViewType.setText(type);
        holder.textViewRegion.setText(region);
        String imageUrl = currentActivite.getImages_url();
        // Obtenez l'ID de la ressource drawable en utilisant le nom de l'image
        imageUrl = this.baseUrl+imageUrl;
        System.out.println("IMG"+imageUrl);
        // Utilisation de Glide pour charger l'image depuis l'URL dans l'ImageView
        Glide.with(fragment.getActivity())
                .load(imageUrl)
                .into(holder.imageViewActivite);
        //redirection vers fiche-------------------------------nadd

        holder.textViewId_Activite.setText(currentActivite.getId());
        holder.textViewTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String id_activite = holder.textViewId_Activite.getText().toString();
                System.out.println("-------------affichage de la fiche de l'activite en cours--------------");
                System.out.println("---------id  activite ------------"+id_activite+"------");
                FicheActiviteFragment ficheActiviteFragment = new FicheActiviteFragment().newInstance(holder.textViewId_Activite.getText().toString());
                FragmentManager fragmentManager = fragment.getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, ficheActiviteFragment);
                fragmentTransaction.addToBackStack(null); // ajouter la transaction à la pile pour revenir en arrière
                fragmentTransaction.commit();
            }
        });
        //---nadd-----------------------------------------------------
    }

    @Override
    public int getItemCount() {
        return activiteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewActivite;
        TextView textViewTitle;
        TextView textViewType;
        TextView textViewRegion;
        //---------gerer id redirection nadd------------
        TextView textViewId_Activite;
        //---------------
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewActivite = itemView.findViewById(R.id.imageViewActivite);
            textViewTitle = itemView.findViewById(R.id.titleActivite);
            textViewType = itemView.findViewById(R.id.type);
            textViewRegion = itemView.findViewById(R.id.region);
            textViewId_Activite = itemView.findViewById(R.id.id_Activite);

        }
    }
}
