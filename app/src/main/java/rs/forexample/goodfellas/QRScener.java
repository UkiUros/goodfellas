package rs.forexample.goodfellas;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class QRScener extends Activity {

    public static final String CARD_DETAILS = "carddetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentIntegrator integrator = new IntentIntegrator(QRScener.this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            Intent i = new Intent(QRScener.this, MainActivity.class);
            i.putExtra(CARD_DETAILS, scanResult.getContents());
            startActivity(i);
        } else {

        }

    }



}
