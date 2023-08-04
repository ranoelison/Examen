package com.example.mada_tour.utils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mada_tour.R;

import java.util.ArrayList;
import java.util.List;

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringViewHolder> {
    private List<String> stringList;

    public StringAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activites_layout, parent, false);
        return new StringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StringViewHolder holder, int position) {
        String stringItem = stringList.get(position);
//        holder.imageView.sett(stringItem);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class StringViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.actu_image);
        }
    }
}
