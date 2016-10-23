package com.example.sphien2011.datarealmcontact.ui;

import android.content.Context;
import android.util.Log;

import com.example.sphien2011.datarealmcontact.common.model.Contact;
import com.example.sphien2011.datarealmcontact.common.service.ContactFilter;
import com.example.sphien2011.datarealmcontact.common.service.ContactService;
import com.example.sphien2011.datarealmcontact.common.service.ContactServiceImpl;

import java.util.ArrayList;

/**
 * Created by sphien2011 on 23/10/2016.
 */
public class ContactInteractorImpl implements ContactInteractor {
    private static final String TAG = ContactInteractorImpl.class.getSimpleName();
    private OnContactResponse mListener;
    private ContactService mService;
    private Context mContext;

    public ContactInteractorImpl(OnContactResponse mListener, Context mContext) {
        this.mListener = mListener;
        this.mContext = mContext;
        this.mService = ContactServiceImpl.getInstance();
    }

    @Override
    public void getContactFromPhone() {
//        ArrayList<Contact> data = new ContactFilter(mContext).filterContact(ContactFilter.uriEmail, ContactFilter.idEmail, ContactFilter.dataEmail1);
//        Log.e(TAG, "getContactFromPhone: " + data.get(7).getEmails());

        ArrayList<Contact> data = new ContactFilter(mContext).filterContact(ContactFilter.uriNumber);

        mListener.OnGetContactFromPhoneResponse(data);
    }

    @Override
    public void saveContact(ArrayList<Contact> data) {
        mService.saveContact(data);
    }

    @Override
    public void getContact() {

    }
}
