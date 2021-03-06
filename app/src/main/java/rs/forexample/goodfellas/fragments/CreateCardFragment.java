package rs.forexample.goodfellas.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.InterpolatorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.IoniconsIcons;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;

import rs.forexample.goodfellas.CardSubmitActivity;
import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.adapter.ImageAdapter;
import rs.forexample.goodfellas.adapter.PremiumImageAdapter;
import rs.forexample.goodfellas.events.GalleryImageClickedEvent;
import rs.forexample.goodfellas.events.PremiumImageClickedEvent;

/**
 * Created by deki on 28.5.16..
 */
public class CreateCardFragment extends Fragment {

    TabLayout   bottomTabLayout;
    private ImageView selectedImage;
    private ViewGroup fragmentContainer;
    private Button buyDesignButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_create_card, container, false );
        selectedImage = (ImageView) root.findViewById(R.id.cardBackground);
        fragmentContainer = (ViewGroup) root.findViewById(R.id.fragment_container);
        bottomTabLayout = (TabLayout) root.findViewById(R.id.bottom_tabs);
        buyDesignButton = (Button) root.findViewById(R.id.buy_design_button);
        buyDesignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CardSubmitActivity.class);
                startActivity(intent);
            }
        });
        setupBottomTab(bottomTabLayout);
        showFragment(0);
        return root;
    }

    private void setupBottomTab(TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText("Gallery").setIcon(new IconDrawable(getContext(), IoniconsIcons.ion_images)));
        tabLayout.addTab(tabLayout.newTab().setText("Shop").setIcon(new IconDrawable(getContext(), IoniconsIcons.ion_social_euro)));
        tabLayout.addTab(tabLayout.newTab().setText("Google Plus").setIcon(new IconDrawable(getContext(), IoniconsIcons.ion_social_googleplus_outline)));
        tabLayout.addTab(tabLayout.newTab().setText("Twitter").setIcon(new IconDrawable(getContext(), IoniconsIcons.ion_social_twitch_outline)));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    showFragment(0);
                } else if (tab.getPosition() == 1) {
                    showFragment(1);
                } else if (tab.getPosition() == 2) {
                    // nope
                } else {
                    // damir wuz here
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showFragment(int type) {
        Fragment fragment;
        if (type == 0 ) {
            fragment = new GalleryFragment();
        } else if (type == 1) {
            fragment = new ShopFragment();
        } else {
            return;
        }
        android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Subscribe
    public void onGalleryClicked(GalleryImageClickedEvent event)
    {
        File file = new File(event.getImageUri().toString());
        Picasso.with(getContext()).load(file).fit().into(selectedImage);
    }

    @Subscribe
    public void onPremiumImageClicked(PremiumImageClickedEvent event)
    {
        Picasso.with(getContext()).load(event.getImageId()).fit().into(selectedImage);
    }
}
