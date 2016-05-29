package rs.forexample.goodfellas.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import rs.forexample.goodfellas.R;
import rs.forexample.goodfellas.data.model.Card;

public class DigitalCardsFragment extends Fragment {


    private ListView lv;
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



        return v;
    }

    private class CardAdapter extends BaseAdapter {

        private TextView tvTitle;
        private ImageView ivIcon;
        private Context ctx;
        private List<Card> cards;

        private LayoutInflater inflater;

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


            return v;
        }
    }

}
