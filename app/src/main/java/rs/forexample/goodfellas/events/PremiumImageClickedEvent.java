package rs.forexample.goodfellas.events;

/**
 * Created by deki on 28.5.16..
 */
public class PremiumImageClickedEvent {
    private int imageId;

    public PremiumImageClickedEvent(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
