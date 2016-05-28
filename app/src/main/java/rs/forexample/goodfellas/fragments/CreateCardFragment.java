package rs.forexample.goodfellas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.adapter.ImageAdapter;

/**
 * Created by deki on 28.5.16..
 */
public class CreateCardFragment extends Fragment implements ImageAdapter.OnImageClickedListener {

    RecyclerView recyclerViewFree;
    RecyclerView recyclerViewPaid;
    private ImageView selectedImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_create_card, container, false );
        recyclerViewFree = (RecyclerView) root.findViewById(R.id.recycler_view_free);
        recyclerViewPaid = (RecyclerView) root.findViewById(R.id.my_recycler_view_paid);
        selectedImage = (ImageView) root.findViewById(R.id.selectedImage);
        setupRecyclerView(recyclerViewFree);
        setupRecyclerView(recyclerViewPaid);
        return root;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ImageAdapter adapter = new ImageAdapter(fakeData(), this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Integer> fakeData()
    {
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                ids.add(R.drawable.card_image_flower);
            } else {
                ids.add(R.drawable.card_image_rope);
            }
        }
        return ids;
    }

    @Override
    public void onImageClicked(int image) {
        Toast.makeText(getContext(), "id: " + image, Toast.LENGTH_LONG).show();
        selectedImage.setImageResource(image);
    }
}
