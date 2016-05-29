package rs.forexample.goodfellas;

import android.app.Activity;
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
        Toast.makeText(this, "Order made", Toast.LENGTH_SHORT).show();
    }
}
