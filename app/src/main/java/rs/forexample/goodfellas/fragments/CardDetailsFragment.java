package rs.forexample.goodfellas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import rs.forexample.goodfellas.MainActivity;
import rs.forexample.goodfellas.QRScener;
import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.data.DataProvider;
import rs.forexample.goodfellas.data.model.Account;
import rs.forexample.goodfellas.data.model.Card;

public class CardDetailsFragment extends Fragment {

    private TextView tvAvailableAmount;
    private TextView tvAccountNumber;
    private TextView tvReservedAmount;
    private TextView tvLastChange;
    private TextView tvLastChangeDate;

    private CardView cardView;

    public CardDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card_details, container, false);
        tvAvailableAmount = (TextView) v.findViewById(R.id.availableAmount);
        tvAccountNumber = (TextView) v.findViewById(R.id.accountNumber);
        tvReservedAmount = (TextView) v.findViewById(R.id.reservedAmount);
        tvLastChange = (TextView) v.findViewById(R.id.lastChangeAmount);
        tvLastChangeDate = (TextView) v.findViewById(R.id.lastChangeDate);
        populateAccount();

        cardView = (CardView) v.findViewById(R.id.cardView);
        populateCard();

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).startScanner();
            }
        });

        return v;
    }

    private void populateAccount(){
        Account acc = DataProvider.getUser().getAccounts().get(0);

        tvAvailableAmount.setText(""+acc.getAvailableAmount()+""+acc.getCurrency());
        tvAccountNumber.setText(acc.getAccountNumber());
        tvReservedAmount.setText(""+acc.getReservedAmount()+""+acc.getCurrency());
        tvLastChange.setText(""+acc.getReservedAmount()+""+acc.getCurrency());
        tvLastChangeDate.setText(new SimpleDateFormat("MMM dd, yyyy", Locale.US).format(acc.getLastChangeDateTime()));
    }

    private void populateCard(){
        Card card = getActivity().getIntent().getParcelableExtra(QRScener.CARD_DETAILS);


    }
}
