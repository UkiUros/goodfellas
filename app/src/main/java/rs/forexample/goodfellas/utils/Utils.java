package rs.forexample.goodfellas.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

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
            if (i % 4 == 3) {
                ids.add(R.drawable.card_image_flower);
            } else if (i % 4 == 2){
                ids.add(R.drawable.card_image_rope);
            } else if (i % 4 == 1) {
                ids.add(R.drawable.card_image_beer);
            } else if (i % 4 == 0) {
                ids.add(R.drawable.card_image_frut);
            }
        }
        return ids;
    }

    public static void getLocalPictures(Context context, final Callback<ArrayList<Uri>> callback) {
        final ArrayList<Uri> localPics = new ArrayList<>();
        final Cursor cc = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null,
                null);
        if (cc != null) {
            new Thread() {
                public void run() {
                    try {
                        cc.moveToFirst();
                        for (int i = 0; i < cc.getCount(); i++) {
                            cc.moveToPosition(i);
                            localPics.add(Uri.parse(cc.getString(1)));
                        }
                        if (callback != null) callback.onResult(localPics);

                    } catch (Exception e) {
                        if (callback != null) {
                            callback.onError();
                        }
                    } finally {
                        cc.close();
                    }
                }
            }.start();
        }
    }

    public interface Callback<T> {
        void onResult(T result);
        void onError();
    }
}
