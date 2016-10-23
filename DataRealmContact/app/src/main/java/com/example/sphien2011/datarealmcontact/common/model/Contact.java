package com.example.sphien2011.datarealmcontact.common.model;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sphien2011 on 15/10/2016.
 */
public class Contact extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
//    private ArrayList<String> numbers;
//    private ArrayList<String> emails;
    private RealmList<Email> emails;
    private RealmList<Number> numbers;

    public Contact() {
    }

    public Contact(String id, String name) {
        this.id = id;
        this.name = name;
        this.emails = new RealmList<>();
        this.numbers = new RealmList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public ArrayList<String> getNumbers() {
//        return numbers;
//    }
//
//    public void setNumbers(ArrayList<String> numbers) {
//        this.numbers = numbers;
//    }
//
//    public ArrayList<String> getEmails() {
//        return emails;
//    }
//
//    public void setEmails(ArrayList<String> emails) {
//        this.emails = emails;
//    }


    public RealmList<Email> getEmails() {
        return emails;
    }

    public void setEmails(RealmList<Email> emails) {
        this.emails = emails;
    }

    public RealmList<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(RealmList<Number> numbers) {
        this.numbers = numbers;
    }

    public void addEmail(String email) {
        emails.add(new Email(email));
    }

    public void addNumber(String number) {
        numbers.add(new Number(number));
    }
}
