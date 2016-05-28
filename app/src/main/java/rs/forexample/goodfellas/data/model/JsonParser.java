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
            String cardNumber = card.getString("cardNumber");
            String cardName = card.getString("cardName");
            String cvc = card.getString("cvc");
            int cardType = card.getInt("cardType");
            String expirationDate = card.getString("expirationDate");
            String ownerName = card.getString("ownerName");
            String cardID = card.getString("cardId");

            return new Card(cardNumber,cardName,cvc,cardType,expirationDate,ownerName,cardID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    return null;
    }
}
