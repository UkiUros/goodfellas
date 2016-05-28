package rs.forexample.goodfellas.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.adapter.PremiumImageAdapter;
import rs.forexample.goodfellas.events.PremiumImageClickedEvent;
import rs.forexample.goodfellas.utils.Utils;


/**
 * Created by deki on 28.5.16..
 */
public class ShopFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_shop, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        setupRecyclerVeiw(recyclerView);
        return root;
    }

    private void setupRecyclerVeiw(final RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(new PremiumImageAdapter(Utils.fakeData(4), new PremiumImageAdapter.OnImageClickedListener() {
            @Override
            public void onImageClicked(int image) {
               EventBus.getDefault().post(new PremiumImageClickedEvent(image));
            }
        }));

    }
}
