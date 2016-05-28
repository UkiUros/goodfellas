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
            String cvc = card.getString("cvc");
            int cardType = card.getInt("cardType");
            String expirationDate = card.getString("expirationDate");
            String ownerName = card.getString("ownerName");

            return new Card(cardNumber,"Visa Electron",cvc,cardType,expirationDate,ownerName, "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    return null;
    }
}
