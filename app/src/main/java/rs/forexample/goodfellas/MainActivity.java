package rs.forexample.goodfellas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.IoniconsIcons;
import com.joanzapata.iconify.fonts.MaterialIcons;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import rs.forexample.goodfellas.data.model.Card;
import rs.forexample.goodfellas.fragments.CardDetailsFragment;
import rs.forexample.goodfellas.fragments.CreateCardFragment;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Card decriptedCard;

    private static final int tabIconSize = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            decriptedCard = extras.getParcelable(QRScener.CARD_DETAILS);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager();

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);
        setCurrentTabIconColor(tabLayout.getTabAt(0), 0);

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(new IconDrawable(getApplicationContext(), IoniconsIcons.ion_card).colorRes(R.color.colorWhite).sizeDp(tabIconSize));
        tabLayout.getTabAt(1).setIcon(new IconDrawable(getApplicationContext(), MaterialIcons.md_color_lens).colorRes(R.color.colorWhite).sizeDp(tabIconSize));
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
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
                tab.setIcon(new IconDrawable(getApplicationContext(), IoniconsIcons.ion_card).colorRes(R.color.colorWhite).sizeDp(tabIconSize));
                break;

            case 1:
                tab.setIcon(new IconDrawable(getApplicationContext(), MaterialIcons.md_color_lens).colorRes(R.color.colorWhite).sizeDp(tabIconSize));
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

    public void startScanner(){
        Intent intent = new Intent(MainActivity.this, QRScener.class);
        startActivity(intent);
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
