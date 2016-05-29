package rs.forexample.goodfellas.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rs.forexample.goodfellas.DigitalCardActivity;
import rs.forexample.goodfellas.QRScener;
import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.data.model.Card;

import static rs.forexample.goodfellas.data.DataProvider.getExpirationDate;

public class DigitalCardsFragment extends Fragment {

    private ListView lv;
    ArrayList<Card> list;
    public DigitalCardsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_digital_cards, container, false);
        lv = (ListView) v.findViewById(R.id.listView);

        generateCards();
        lv.setAdapter(new CardAdapter(getActivity(), list));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), DigitalCardActivity.class).putExtra(QRScener.CARD_DETAILS, list.get(position)));
                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.stay_in_place);
            }
        });

        return v;
    }

    private class CardAdapter extends BaseAdapter {

        private Context ctx;
        private List<Card> cards;

        private LayoutInflater inflater;

        private TextView tvCardNumber;
        private TextView tvCVC;
        private TextView tvOwner;
        private TextView tvExpire;
        private TextView tvCardName;
        private CardView cardView;

        CardAdapter(Context context, List<Card> cards) {
            this.ctx = context;
            this.cards = cards;

            inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return cards.size();
        }

        @Override
        public Object getItem(int position) {
            return cards.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            if (v == null)
                v = inflater.inflate(R.layout.item_card, parent, false);

            cardView = (CardView) v.findViewById(R.id.cardView);
            tvCardNumber = (TextView) v.findViewById(R.id.cardNumber);
            tvCardName = (TextView) v.findViewById(R.id.cardName);
            tvCVC = (TextView) v.findViewById(R.id.cvc);
            tvOwner = (TextView) v.findViewById(R.id.owner);
            tvExpire = (TextView) v.findViewById(R.id.expiration);

            populateCard(cards.get(position));

            return v;
        }

        private void populateCard(Card card){
            tvCardNumber.setText(card.getCardNumber());
            tvCardName.setText(card.getCardName());
            tvCVC.setText(card.getCvc());
            tvOwner.setText(card.getOwnerName());
            tvExpire.setText(card.getExpirationDate());

        }
    }

    private void generateCards(){
        list = new ArrayList<>();
        list.add(new Card("4844 8778 2779 5030", "VISA", "444", Card.DEBIT_CARD, getExpirationDate(), "Jovan Rudic", "crvena"));
        list.add(new Card("5544 8118 3432 1451", "VISA", "401", Card.CREDIT_CARD, getExpirationDate(), "Jovan Rudic", "crvena"));
        list.add(new Card("5676 9919 2279 1111", "VISA", "433", Card.DEBIT_CARD, getExpirationDate(), "Jovan Rudic", "crvena"));
    }

}
