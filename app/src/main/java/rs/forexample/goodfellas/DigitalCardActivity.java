package rs.forexample.goodfellas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import rs.forexample.goodfellas.data.DataProvider;
import rs.forexample.goodfellas.data.model.Account;
import rs.forexample.goodfellas.data.model.Card;

public class DigitalCardActivity extends AppCompatActivity {

    Toolbar toolbar;

    private TextView tvCardNumber;
    private TextView tvCVC;
    private TextView tvOwner;
    private TextView tvExpire;
    private TextView tvCardName;
    private CardView cardView;

    private TextView tvAvailableAmount;
    private TextView tvAccountNumber;
    private TextView tvReservedAmount;
    private TextView tvLastChange;
    private TextView tvLastChangeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_card);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Card card = getIntent().getParcelableExtra(QRScener.CARD_DETAILS);

        cardView = (CardView) findViewById(R.id.cardView);
        tvCardNumber = (TextView) findViewById(R.id.cardNumber);
        tvCardName = (TextView) findViewById(R.id.cardName);
        tvCVC = (TextView) findViewById(R.id.cvc);
        tvOwner = (TextView) findViewById(R.id.owner);
        tvExpire = (TextView) findViewById(R.id.expiration);

        tvAvailableAmount = (TextView) findViewById(R.id.availableAmount);
        tvAccountNumber = (TextView) findViewById(R.id.accountNumber);
        tvReservedAmount = (TextView) findViewById(R.id.reservedAmount);
        tvLastChange = (TextView) findViewById(R.id.lastChangeAmount);
        tvLastChangeDate = (TextView) findViewById(R.id.lastChangeDate);

        populateCard(card);

    }

    private void populateCard(Card card){
        tvCardNumber.setText(card.getCardNumber());
        tvCardName.setText(card.getCardName());
        tvCVC.setText(card.getCvc());
        tvOwner.setText(card.getOwnerName());
        tvExpire.setText(card.getExpirationDate());

        populateAccount();

    }

    private void populateAccount(){
        Account acc = DataProvider.getUser().getAccounts().get(0);

        tvAvailableAmount.setText(""+acc.getAvailableAmount()+""+acc.getCurrency());
        tvAccountNumber.setText(acc.getAccountNumber());
        tvReservedAmount.setText(""+acc.getReservedAmount()+""+acc.getCurrency());
        tvLastChange.setText(""+acc.getReservedAmount()+""+acc.getCurrency());
        tvLastChangeDate.setText(new SimpleDateFormat("MMM dd, yyyy", Locale.US).format(acc.getLastChangeDateTime()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
