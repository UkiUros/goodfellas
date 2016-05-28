package rs.forexample.goodfellas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.IoniconsIcons;

public class MainActivity extends AppCompatActivity {

    private View btnScan;
    private View btnShop;
    private ImageView imgScan;
    private ImageView imgShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = findViewById(R.id.btnScan);
        btnShop = findViewById(R.id.btnShop);
        initializeBottomIcons();




    }

    private void initializeBottomIcons(){
        imgScan = (ImageView) findViewById(R.id.imgScan);
        imgShop = (ImageView) findViewById(R.id.imgShop);

        imgScan.setImageDrawable(new IconDrawable(getApplicationContext(), IoniconsIcons.ion_qr_scanner).sizeDp(30).colorRes(R.color.colorWhite));
        imgScan.setImageDrawable(new IconDrawable(getApplicationContext(), IoniconsIcons.ion_card).sizeDp(30).colorRes(R.color.colorWhite));
    }
}
