package com.example.mada_tour.utils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mada_tour.R;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewActivite = itemView.findViewById(R.id.imageViewActivite);
            textViewTitle = itemView.findViewById(R.id.titleActivite);
            textViewType = itemView.findViewById(R.id.type);
            textViewRegion = itemView.findViewById(R.id.region);
        }
    }
}
