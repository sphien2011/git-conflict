package com.example.sphien2011.datarealmcontact.common.service;

import com.example.sphien2011.datarealmcontact.common.model.Contact;

import java.util.ArrayList;

/**
 * Created by sphien2011 on 22/10/2016.
 */
public interface ContactService {

    void saveContact(ArrayList<Contact> data);

    public Contact getContact();
}
