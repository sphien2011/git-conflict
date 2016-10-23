package com.example.sphien2011.datarealmcontact.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sphien2011.datarealmcontact.R;
import com.example.sphien2011.datarealmcontact.adapter.ContactAdapter;
import com.example.sphien2011.datarealmcontact.common.model.Contact;
import com.example.sphien2011.datarealmcontact.common.service.ContactFilter;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactActivityFragment extends Fragment implements ContactPresenterImpl.View {
    private static final String TAG = ContactActivityFragment.class.getSimpleName();
    private ArrayList<Contact> mList;
    private RecyclerView mRecyclerContact;
    private ContactAdapter mAdapter;

    private ContactPresenter mPresenter;

    public ContactActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = ContactPresenterImpl.getInstance(this, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        mRecyclerContact = (RecyclerView) view.findViewById(R.id.recycler_contact);

//        if (mList != null) {
            /*mList = new ContactFilter(getContext()).filterContact(ContactFilter.uriNumber, ContactFilter.idNumber, ContactFilter.dataNumber);
        Log.e(TAG, "onCreateView: " +mList.size());
            ContactAdapter mAdapter = new ContactAdapter(mList, getContext());
            mRecyclerContact.setAdapter(mAdapter);*/
//        } else {
            mPresenter.getContactFromPhone();
//        }

        /*ContactFilter contactFetch = new ContactFilter(getContext());
        for(int i=0; i<4;i++){
            contactFetch.writeContact("oht" + i, "01234567238" );
        }*/

        mRecyclerContact.setLayoutManager(new LinearLayoutManager(getContext()));

        /*new AsyncTask<Void, Void, List<Contact>>() {

            @Override
            protected List<Contact> doInBackground(Void... voids) {
                mList = new ContactFilter(getContext()).fetchAll();
                Log.e(TAG, "doInBackground: " + mList.size());
                return mList;
            }

            @Override
            protected void onPostExecute(List<Contact> contacts) {
                super.onPostExecute(contacts);
                ContactAdapter mAdapter = new ContactAdapter(contacts, getContext());
                mRecyclerContact.setAdapter(mAdapter);
            }
        }.execute();
*/
        return view;
    }

    //implement view
    @Override
    public void onGetContactUpdateFromPhone(ArrayList<Contact> data) {
        mList = data;
        mPresenter.saveContact(mList);

        mAdapter = new ContactAdapter(mList, getContext());
        mRecyclerContact.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
    }
}
