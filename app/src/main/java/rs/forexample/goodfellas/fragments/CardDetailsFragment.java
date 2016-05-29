package rs.forexample.goodfellas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Locale;

import rs.forexample.goodfellas.MainActivity;
import rs.forexample.goodfellas.QRScener;
import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.data.DataProvider;
import rs.forexample.goodfellas.data.model.Account;
import rs.forexample.goodfellas.data.model.Card;
import rs.forexample.goodfellas.events.GalleryImageClickedEvent;

public class CardDetailsFragment extends Fragment {

    private TextView tvAvailableAmount;
    private TextView tvAccountNumber;
    private TextView tvReservedAmount;
    private TextView tvLastChange;
    private TextView tvLastChangeDate;

    private TextView tvCardNumber;
    private TextView tvCVC;
    private TextView tvOwner;
    private TextView tvExpire;
    private TextView tvCardType;
    private TextView tvCardName;
    private ImageView scanCardButton;

    private RelativeLayout cardView;
    private ImageView scanCardImage;

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


        cardView = (RelativeLayout) v.findViewById(R.id.cardView);
        tvCardNumber = (TextView) v.findViewById(R.id.cardNumber);
        tvCardName = (TextView) v.findViewById(R.id.cardName);
        tvCVC = (TextView) v.findViewById(R.id.cvc);
        tvOwner = (TextView) v.findViewById(R.id.owner);
        tvExpire = (TextView) v.findViewById(R.id.expiration);
        tvCardType = (TextView) v.findViewById(R.id.type);
        scanCardButton = (ImageView) v.findViewById(R.id.scan_card_button);
        scanCardImage = (ImageView) v.findViewById(R.id.scan_card_image);

        if (getActivity().getIntent().hasExtra(QRScener.CARD_DETAILS)){
            //deki slika
            scanCardImage.setImageResource(R.drawable.card_front_validated);
            scanCardButton.setVisibility(View.GONE);
            populateAccount();
        }


        scanCardButton.setOnClickListener(new View.OnClickListener() {
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

    public void populateCard(){
        populateAccount();
        Card card = getActivity().getIntent().getParcelableExtra(QRScener.CARD_DETAILS);
        tvCardNumber.setText(card.getCardNumber());
        tvCardName.setText(card.getCardName());
        tvCVC.setText(card.getCvc());
        tvOwner.setText(card.getOwnerName());
        tvExpire.setText(card.getExpirationDate());
        tvCardType.setText(card.getCardType() == Card.CREDIT_CARD ? "Credit card" : "Debit card");

    }


}
