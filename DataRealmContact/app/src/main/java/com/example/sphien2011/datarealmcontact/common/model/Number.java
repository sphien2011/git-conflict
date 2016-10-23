package com.example.sphien2011.datarealmcontact.common.model;

import io.realm.RealmObject;

/**
 * Created by sphien2011 on 22/10/2016.
 */
public class Number extends RealmObject {
    private String number;

    public Number() {
    }

    public Number(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
