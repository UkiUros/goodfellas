package rs.forexample.goodfellas.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Card implements Parcelable {

    public static final int DEBIT_CARD = 101;
    public static final int CREDIT_CARD = 201;

    private String cardNumber;
    private String cardName;
    private String cvc;
    private int cardType;
    private String expirationDate;

    public Card(String cardNumber, String cardName, String cvc, int cardType, String expirationDate) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.cvc = cvc;
        this.cardType = cardType;
        this.expirationDate = expirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    protected Card(Parcel in) {
        cardNumber = in.readString();
        cardName = in.readString();
        cvc = in.readString();
        cardType = in.readInt();
        expirationDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cardNumber);
        dest.writeString(cardName);
        dest.writeString(cvc);
        dest.writeInt(cardType);
        dest.writeString(expirationDate);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}