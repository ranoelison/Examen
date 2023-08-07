package com.example.mada_tour.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mada_tour.R;
import com.example.mada_tour.modele.Avis;

import java.util.List;

import org.ocpsoft.prettytime.PrettyTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AvisAdapter extends RecyclerView.Adapter<AvisAdapter.ViewHolder> {

    private List<Avis> avisList;

    public AvisAdapter(List<Avis> avisList) {
        this.avisList = avisList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Avis avis = avisList.get(position);
        holder.bind(avis);
    }

    @Override
    public int getItemCount() {
        return avisList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewContenu;
        private RatingBar ratingBarNote;
        private  TextView textViewUtilisateur;
        private TextView textViewDatePublication;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContenu = itemView.findViewById(R.id.textViewContenu);
            ratingBarNote = itemView.findViewById(R.id.ratingBarNote);
            textViewUtilisateur = itemView.findViewById(R.id.textViewUtilisateur);
            textViewDatePublication = itemView.findViewById(R.id.textViewDatePublication);
        }

        public void bind(Avis avis) {
            textViewContenu.setText(avis.getContenu());
            ratingBarNote.setRating(avis.getNote().floatValue());
            textViewUtilisateur.setText(avis.getUtilisateurs_id());
            textViewDatePublication.setText(avis.getDate_submit());
            // Formatez la date de publication en utilisant PrettyTime
            if (avis.getDate_submit() != null) {
                String formattedDate = formatDate(avis.getDate_submit());
                textViewDatePublication.setText(formattedDate);
            } else {
                textViewDatePublication.setText("");
            }

        }

        // Méthode pour formater la date en utilisant PrettyTime
        private String formatDate(String dateString) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
            try {
                Date date = sdf.parse(dateString);
                PrettyTime prettyTime = new PrettyTime(Locale.getDefault());
                return prettyTime.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return "";
        }

    }
}
