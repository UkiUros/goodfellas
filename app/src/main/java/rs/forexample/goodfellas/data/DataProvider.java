package rs.forexample.goodfellas.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import rs.forexample.goodfellas.data.model.Account;
import rs.forexample.goodfellas.data.model.Card;
import rs.forexample.goodfellas.data.model.User;

public class DataProvider {

    public static User getUser(){
        return new User(User.FAKE_UKI_ID, "Uros Simic", "Belgrade", "Dedinje bb", "0655737733", getAccounts());
    }

    private static ArrayList<Account> getAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(new Account(Account.UKI_ACCOUNT, Account.CURRENCY_RSD, 15000.00, 2000.00, 500.00, getLastChangeDate(), getCards()));

        return accounts;
    }

    private static ArrayList<Card> getCards(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("4844 8778 2779 5030", "VISA", "419", Card.DEBIT_CARD, getExpirationDate()));

        return cards;
    }


    private static String getExpirationDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 8);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy", Locale.US);
        return dateFormat.format(calendar.getTime());
    }

    private static Date getLastChangeDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        return calendar.getTime();
    }
}
