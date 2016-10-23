package com.example.sphien2011.datarealmcontact.common.model;

import io.realm.RealmObject;

/**
 * Created by sphien2011 on 21/10/2016.
 */
public class Email extends RealmObject {
    private String email;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
