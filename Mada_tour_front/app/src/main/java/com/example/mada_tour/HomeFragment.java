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
    private List<String> stringList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    VideoView videoView;

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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        videoView = view.findViewById(R.id.videoview);
//        Uri uri = Uri.parse("android:resource://"+getActivity().getPackageName()+"/"+R.raw.backgroundmdtrim);
//        videoView.setVideoURI(uri);
//        videoView.start();
//
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                mediaPlayer.setLooping(true);
//            }
//        });

        // Initialisez la RecyclerView et l'adapter
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        stringAdapter = new StringAdapter(stringList);
        recyclerView.setAdapter(stringAdapter);

        // Obtenez le tableau de String à partir de l'apiResponse
        // par exemple : stringList = apiResponse.getStringList();
        // Assurez-vous que stringList contient les String que vous voulez afficher
        List<Destination> destinations = new ArrayList<>();
        ActualiteController actctl = new ActualiteController(requireContext());
        try{
            actctl.getDestinations(new DestinationCallback() {
                @Override
                public void onDestinationListReady(List<Destination> destinations) {
                    for (Destination destination: destinations) {
                        stringList.add(destination.getNom());
                        stringList.add(destination.getType());
                        Toast.makeText(getContext(), "NAME " + stringList.get(0), Toast.LENGTH_LONG).show();
                        stringAdapter.setStringList(stringList);
                        // Rafraîchissez l'adapter avec les nouvelles données
                        stringAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }

//    @Override
//    public void onStart() {
//        videoView.start();
//        super.onStart();
//    }
//
//    @Override
//    public void onPause() {
//        videoView.suspend();
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        videoView.stopPlayback();
//        super.onDestroy();
//    }
}