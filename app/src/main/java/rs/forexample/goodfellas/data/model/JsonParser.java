package rs.forexample.goodfellas.data.model;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jovan on 5/28/2016.
 */
public class JsonParser {

    public static Card parseCard(String cardJson) {

        try {

            JSONObject card = new JSONObject(cardJson);
            String cardSecureCode = card.getString("cardSecureCode");

            if (TextUtils.equals(cardSecureCode, "123456789")) {
                return new Card("1111 22222222 3333", "Visa Electron", "123", 101, "12/26", "Jon Doe", "1");
            } else {
                return null;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
