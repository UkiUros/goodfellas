package rs.forexample.goodfellas.fragments;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import rs.forexample.goodfellas.Manifest;
import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.adapter.ImageAdapter;
import rs.forexample.goodfellas.adapter.UriAdapter;
import rs.forexample.goodfellas.events.GalleryImageClickedEvent;
import rs.forexample.goodfellas.utils.Utils;

/**
 * Created by deki on 28.5.16..
 */
public class GalleryFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        setupRecyclerView(recyclerView);
        return root;
    }

    private void setupRecyclerView(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(mLayoutManager);

        Utils.getLocalPictures(getContext(), new Utils.Callback<ArrayList<Uri>>() {
            @Override
            public void onResult(ArrayList<Uri> result) {
                recyclerView.setAdapter(new UriAdapter(result, new UriAdapter.OnImageClickedListener() {
                    @Override
                    public void onImageClicked(Uri image) {
                        EventBus.getDefault().post(new GalleryImageClickedEvent(image));
                    }
                }));
            }

            @Override
            public void onError() {

            }
        });

    }
}
