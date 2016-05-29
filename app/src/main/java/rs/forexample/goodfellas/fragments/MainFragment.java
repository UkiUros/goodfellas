package rs.forexample.goodfellas.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rs.forexample.goodfellas.R;

public class MainFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    private static final int tabIconSize = 100;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        setupViewPager();

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);
        setCurrentTabIconColor(tabLayout.getTabAt(0), 0);

        return v;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.credit_card_icon_tab);
        tabLayout.getTabAt(1).setIcon(R.drawable.palette_ico_tab_inactive);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new CardDetailsFragment());
        adapter.addFragment(new CreateCardFragment());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        setCurrentTabIconColor(tab, tab.getPosition());
    }

    //    TODO
    private void setCurrentTabIconColor(TabLayout.Tab tab, int position) {
        setupTabIcons();
        switch (position) {
            case 0:
                tab.setIcon(R.drawable.credit_card_icon_tab);
                break;

            case 1:
                tab.setIcon(R.drawable.palette_ico_tab);
                break;
        }
    }

    public void switchToTab(int tabPosition){
        onTabSelected(tabLayout.getTabAt(tabPosition));
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }

}
