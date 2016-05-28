package rs.forexample.goodfellas.events;

import android.net.Uri;

import rs.forexample.goodfellas.adapter.UriAdapter;

/**
 * Created by deki on 28.5.16..
 */
public class GalleryImageClickedEvent {
    private Uri imageUri;

    public GalleryImageClickedEvent(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Uri getImageUri() {

        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
