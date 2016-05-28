package rs.forexample.goodfellas.data.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jovan on 5/28/2016.
 */
public class JsonParser {

    public static Card parseCard(String cardJson){

        try {

            JSONObject card = new JSONObject(cardJson);
            String cardNumber = card.getString("");
            String cardName = card.getString("");
            String cvc = card.getString("");
            int cardType = card.getInt("");
            String expirationDate = card.getString("");
            String ownerName = card.getString("");
            String cardID = card.getString("");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    return null;
    }
}
