package rs.forexample.goodfellas.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.IoniconsIcons;

import java.util.ArrayList;

import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.adapter.ImageAdapter;

/**
 * Created by deki on 28.5.16..
 */
public class CreateCardFragment extends Fragment {

    RecyclerView recyclerViewFree;
    RecyclerView recyclerViewPaid;
    TabLayout   bottomTabLayout;
    private ImageView selectedImage;
    private ViewGroup fragmentContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_create_card, container, false );
        selectedImage = (ImageView) root.findViewById(R.id.selectedImage);
        selectedImage.setImageResource(R.drawable.card_image_flower);
        fragmentContainer = (ViewGroup) root.findViewById(R.id.fragment_container);
        bottomTabLayout = (TabLayout) root.findViewById(R.id.bottom_tabs);
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
}
