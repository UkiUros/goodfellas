package rs.forexample.goodfellas.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {

    public static final String FAKE_UKI_ID = "0709992181111";

    private String uuid; // jmbg
    private String name;
    private String city;
    private String address;
    private String phoneNumber;
    private ArrayList<Account> accounts;

    public User(String uuid, String name, String city, String address, String phoneNumber, ArrayList<Account> accounts) {
        this.uuid = uuid;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accounts = accounts;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    protected User(Parcel in) {
        uuid = in.readString();
        name = in.readString();
        city = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        if (in.readByte() == 0x01) {
            accounts = new ArrayList<Account>();
            in.readList(accounts, Account.class.getClassLoader());
        } else {
            accounts = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(name);
        dest.writeString(city);
        dest.writeString(address);
        dest.writeString(phoneNumber);
        if (accounts == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(accounts);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}