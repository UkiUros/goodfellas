package rs.forexample.goodfellas;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CardSubmitActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_submit);
    }

    public void onOrderClicked(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(CardSubmitActivity.this);
        alert.setTitle("Order");
        alert.setMessage("You can pick you card in out branch in 15 minutes.");
        alert.setPositiveButton("OK", null);
        alert.show();
    }
}
