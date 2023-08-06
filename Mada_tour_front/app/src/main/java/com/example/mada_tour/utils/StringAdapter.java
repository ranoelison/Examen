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
import com.example.mada_tour.modele.Destination;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringViewHolder> {
    private List<Destination> destinations;
    private Fragment fragment;
    private String baseUrl = "http://192.168.1.176:3000";

    public StringAdapter(List<Destination> destinations, Fragment fragment,String baseUrl) {
        this.destinations = destinations;
        this.fragment = fragment;
        this.baseUrl = baseUrl;
    }

    public void setStringList(List<Destination> destinations) {
        this.destinations = destinations;
    }

    @NonNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activites_layout, parent, false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StringViewHolder holder, int position) {
//        String stringItem = stringList.get(position);
        Destination currentDestination = destinations.get(position);
        holder.textViewTitle.setText(currentDestination.getNom());
        String typeAndRegion = currentDestination.getType()+" dans la region de "+currentDestination.getRegion();
        holder.textViewTypeRegion.setText(typeAndRegion);
        String imageUrl = currentDestination.getImg_url();

        // Obtenez l'ID de la ressource drawable en utilisant le nom de l'image
        imageUrl = this.baseUrl+imageUrl;
        System.out.println("IMG"+imageUrl);
        // Utilisation de Glide pour charger l'image depuis l'URL dans l'ImageView
        Glide.with(fragment.getActivity())
                .load(imageUrl)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public static class StringViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewTypeRegion;

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.myimage);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewTypeRegion = itemView.findViewById(R.id.type_region);
        }
    }
}
