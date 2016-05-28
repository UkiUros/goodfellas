package rs.forexample.goodfellas.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Account implements Parcelable {

    public static final String UKI_ACCOUNT = "160-5100102609002-59";

    public static final String CURRENCY_RSD = "RSD";
    public static final String CURRENCY_EUR = "EUR";

    private String accountNumber;
    private String currency;
    private double availableAmount;
    private double reservedAmount;
    private double lastChangeAmount;
    private Date lastChangeDateTime;
    private ArrayList<Card> cards;

    public Account(String accountNumber, String currency, double availableAmount, double reservedAmount, double lastChangeAmount, Date lastChangeDateTime, ArrayList<Card> cards) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.availableAmount = availableAmount;
        this.reservedAmount = reservedAmount;
        this.lastChangeAmount = lastChangeAmount;
        this.lastChangeDateTime = lastChangeDateTime;
        this.cards = cards;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public double getReservedAmount() {
        return reservedAmount;
    }

    public void setReservedAmount(double reservedAmount) {
        this.reservedAmount = reservedAmount;
    }

    public double getLastChangeAmount() {
        return lastChangeAmount;
    }

    public void setLastChangeAmount(double lastChangeAmount) {
        this.lastChangeAmount = lastChangeAmount;
    }

    public Date getLastChangeDateTime() {
        return lastChangeDateTime;
    }

    public void setLastChangeDateTime(Date lastChangeDateTime) {
        this.lastChangeDateTime = lastChangeDateTime;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    protected Account(Parcel in) {
        accountNumber = in.readString();
        currency = in.readString();
        availableAmount = in.readDouble();
        reservedAmount = in.readDouble();
        lastChangeAmount = in.readDouble();
        long tmpLastChangeDateTime = in.readLong();
        lastChangeDateTime = tmpLastChangeDateTime != -1 ? new Date(tmpLastChangeDateTime) : null;
        if (in.readByte() == 0x01) {
            cards = new ArrayList<Card>();
            in.readList(cards, Card.class.getClassLoader());
        } else {
            cards = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accountNumber);
        dest.writeString(currency);
        dest.writeDouble(availableAmount);
        dest.writeDouble(reservedAmount);
        dest.writeDouble(lastChangeAmount);
        dest.writeLong(lastChangeDateTime != null ? lastChangeDateTime.getTime() : -1L);
        if (cards == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(cards);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}