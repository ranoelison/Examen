package com.example.mada_tour;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mada_tour.controlleur.ActiviteController;
import com.example.mada_tour.controlleur.ActualiteController;
import com.example.mada_tour.modele.Activite;
import com.example.mada_tour.modele.ActivityCallback;
import com.example.mada_tour.modele.Destination;
import com.example.mada_tour.modele.DestinationCallback;
import com.example.mada_tour.utils.StringAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private StringAdapter stringAdapter;
    private String base_url;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
//        return inflater.inflate(R.layout.fragment_home, container, false);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        VideoView videoView = rootView.findViewById(R.id.videoview);
        videoView.setVideoPath("android.resource://" + requireActivity().getPackageName() + "/" + R.raw.background);

        // Ajouter un MediaController pour permettre à l'utilisateur de contrôler la vidéo (play, pause, etc.)
        MediaController mediaController = new MediaController(requireContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        // Démarrer automatiquement la lecture de la vidéo
        videoView.start();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.base_url = getActivity().getString(R.string.host);

        List<Destination> destinationList = new ArrayList<>();
        // Initialisez la RecyclerView et l'adapter
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        ActualiteController actctl = new ActualiteController(requireContext(),this.base_url);
        try{
            actctl.getDestinations(new DestinationCallback() {
                @Override
                public void onDestinationListReady(List<Destination> destinations) {
//                    destinationList = destinations;
                    stringAdapter.setStringList(destinations);
                    // Rafraîchissez l'adapter avec les nouvelles données
                    stringAdapter.notifyDataSetChanged();
                }
            });
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        stringAdapter = new StringAdapter(destinationList,this, this.base_url);
        recyclerView.setAdapter(stringAdapter);

    }

}