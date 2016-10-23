package com.example.sphien2011.datarealmcontact.ui;

import com.example.sphien2011.datarealmcontact.common.model.Contact;

import java.util.ArrayList;

/**
 * Created by sphien2011 on 23/10/2016.
 */
public interface ContactInteractor {
    void getContactFromPhone();

    void saveContact(ArrayList<Contact> data);

    void getContact();
}
