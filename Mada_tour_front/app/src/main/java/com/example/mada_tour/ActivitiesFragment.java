package com.example.mada_tour;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mada_tour.controlleur.ActiviteController;
import com.example.mada_tour.modele.Activite;
import com.example.mada_tour.modele.ActivityCallback;
import com.example.mada_tour.utils.ActivitiesAdapter;
import com.example.mada_tour.utils.StringAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment {

    private String baseUrl;
    private RecyclerView recyclerView;
    private ActivitiesAdapter activitiesAdapter;
    public ActivitiesFragment() {
        // Required empty public constructor
    }

    public static ActivitiesFragment newInstance() {
        ActivitiesFragment fragment = new ActivitiesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.baseUrl = getActivity().getString(R.string.host);
        // Initialisez la RecyclerView et l'adapter
        recyclerView = view.findViewById(R.id.recyclerViewActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        ActiviteController actctl = new ActiviteController(getContext(),this.baseUrl);
        List<Activite> activitesListmp = new ArrayList<>();
        activitiesAdapter = new ActivitiesAdapter(activitesListmp,this, baseUrl);
        recyclerView.setAdapter(activitiesAdapter);
        try{
            actctl.getListeActivites(new ActivityCallback() {
                @Override
                public void onActiviteListReady(List<Activite> activites) {
                    activitiesAdapter.setStringList(activites);
                    activitiesAdapter.notifyDataSetChanged();
                }
            });
        }
        catch (Exception exception){
            exception.printStackTrace();
            Toast.makeText(getContext(), "ERROR fetching activities :" + exception.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}