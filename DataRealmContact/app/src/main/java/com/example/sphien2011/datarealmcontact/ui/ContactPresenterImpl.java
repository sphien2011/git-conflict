package com.example.sphien2011.datarealmcontact.ui;

import android.content.Context;

import com.example.sphien2011.datarealmcontact.common.model.Contact;

import java.util.ArrayList;

/**
 * Created by sphien2011 on 23/10/2016.
 */
public class ContactPresenterImpl implements ContactPresenter, OnContactResponse {
    private static ContactPresenterImpl mInstance;
    private View mView;
    private ContactInteractor mInteractor;

    public ContactPresenterImpl(View view, Context context) {
        this.mView = view;
        this.mInteractor = new ContactInteractorImpl(this, context);
    }

    public static ContactPresenterImpl getInstance(View view, Context context) {
        if (mInstance == null) {
            mInstance = new ContactPresenterImpl(view, context);
        }
        mInstance.mView = view;
        return mInstance;
    }

    //implement ContactPresenter
    @Override
    public void getContactFromPhone() {
        mInteractor.getContactFromPhone();
    }

    @Override
    public void saveContact(ArrayList<Contact> data) {
        mInteractor.saveContact(data);
    }

    //implement response listener
    @Override
    public void OnGetContactFromPhoneResponse(ArrayList<Contact> data) {
        mView.onGetContactUpdateFromPhone(data);
    }


    public interface View {
        void onGetContactUpdateFromPhone(ArrayList<Contact> data);
    }
}
