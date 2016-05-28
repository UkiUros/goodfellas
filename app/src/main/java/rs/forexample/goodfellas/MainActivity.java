package rs.forexample.goodfellas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.IoniconsIcons;

import rs.forexample.goodfellas.fragments.CreateCardFragment;

public class MainActivity extends AppCompatActivity {

    private View btnScan;
    private View btnShop;
    private ImageView imgScan;
    private ImageView imgShop;

    CreateCardFragment cardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        String decriptedCard;

        if (extras != null) {
            decriptedCard = extras.getString(QRScener.CARD_DETAILS);
        }

        btnScan = findViewById(R.id.btnScan);
        btnShop = findViewById(R.id.btnShop);
        initializeBottomIcons();

        cardFragment = new CreateCardFragment();
        replaceFragment(cardFragment);


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanner();
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void startScanner(){
        Intent intent = new Intent(MainActivity.this, QRScener.class);
        startActivity(intent);
    }

    // I think that this should be improved somehow..I need to sleep now.. Uki
    private void replaceFragment(Fragment fragment){
        if (fragment != null){

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        }
    }

    // Maybe we need like view pager and tab layout here with disabled swipe? now we are using relative layouts and imgs to mock the looks
    private void initializeBottomIcons(){
        imgScan = (ImageView) findViewById(R.id.imgScan);
        imgShop = (ImageView) findViewById(R.id.imgShop);

        imgScan.setImageDrawable(new IconDrawable(getApplicationContext(), IoniconsIcons.ion_qr_scanner).sizeDp(30).colorRes(R.color.colorWhite));
        imgShop.setImageDrawable(new IconDrawable(getApplicationContext(), IoniconsIcons.ion_card).sizeDp(30).colorRes(R.color.colorWhite));
    }
}
