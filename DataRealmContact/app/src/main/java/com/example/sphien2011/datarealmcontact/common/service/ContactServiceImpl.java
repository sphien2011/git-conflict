package com.example.sphien2011.datarealmcontact.common.service;

import android.content.Context;

import com.example.sphien2011.datarealmcontact.common.model.Contact;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by sphien2011 on 22/10/2016.
 */
public class ContactServiceImpl implements ContactService {
    private static ContactService mInstance;
    private Realm mRealm;
    private Context mContext;

    public static ContactService getInstance() {
        if (mInstance == null) {
            mInstance = new ContactServiceImpl();
        }
        return mInstance;
    }

    public ContactServiceImpl() {
        this.mRealm = Migration.getRealm();
    }

    @Override
    public void saveContact(final ArrayList<Contact> data) {
                mRealm.beginTransaction();
                /*for (int i = 0; i< data.size(); i++) {
                    mRealm.copyToRealmOrUpdate(data.get(i));
                }*/
                mRealm.copyToRealmOrUpdate(data);
                mRealm.commitTransaction();

    }

    @Override
    public Contact getContact() {
        return null;
    }
}
