package rs.forexample.goodfellas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.adapter.ImageAdapter;
import rs.forexample.goodfellas.utils.Utils;

/**
 * Created by deki on 28.5.16..
 */
public class GalleryFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        setupRecyclerView(recyclerView);
        return root;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(new ImageAdapter(Utils.fakeData(10), new ImageAdapter.OnImageClickedListener() {
            @Override
            public void onImageClicked(int image) {

            }
        }));
    }
}
