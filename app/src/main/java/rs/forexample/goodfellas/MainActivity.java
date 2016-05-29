package rs.forexample.goodfellas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import rs.forexample.goodfellas.data.model.Card;
import rs.forexample.goodfellas.fragments.DigitalCardsFragment;
import rs.forexample.goodfellas.fragments.MainFragment;
import rs.forexample.goodfellas.fragments.SlideMenuFragment;

public class MainActivity extends AppCompatActivity implements SlideMenuFragment.SlideMenuListener{

    private Card decriptedCard;

    Toolbar toolbar;
    private SlideMenuFragment slideMenuFragment;

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            decriptedCard = extras.getParcelable(QRScener.CARD_DETAILS);
        }

        slideMenuFragment = (SlideMenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        slideMenuFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        slideMenuFragment.setDrawerListener(this);

        displayFragment(3);

    }

    public void startScanner(){
        Intent intent = new Intent(MainActivity.this, QRScener.class);
        startActivity(intent);
    }

    @Override
    public void onMenuItemSelected(View view, int position) {
        displayFragment(position);
    }

    private void displayFragment(int fragmentPosition){

        switch (fragmentPosition){
            case 3:
                if (fragment == null || !(fragment instanceof MainFragment))
                    fragment = new MainFragment();
                break;

            case 4:
                if (fragment == null || !(fragment instanceof DigitalCardsFragment))
                    fragment = new DigitalCardsFragment();
                break;
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        dl.openDrawer(Gravity.LEFT);
    }
}
