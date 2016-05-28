package rs.forexample.goodfellas.utils;

import java.util.ArrayList;

import rs.forexample.goodfellas.R;

/**
 * Created by deki on 28.5.16..
 */
public class Utils {

    public static ArrayList<Integer> fakeData(int number)
    {
        ArrayList<Integer> ids = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            if (i % 2 == 0) {
                ids.add(R.drawable.card_image_flower);
            } else {
                ids.add(R.drawable.card_image_rope);
            }
        }
        return ids;
    }
}
