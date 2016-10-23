package com.example.sphien2011.datarealmcontact.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.sphien2011.datarealmcontact.R;
import com.example.sphien2011.datarealmcontact.common.model.Contact;
import com.example.sphien2011.datarealmcontact.common.model.Email;
import com.example.sphien2011.datarealmcontact.common.model.Number;

import java.util.List;

/**
 * Created by sphien2011 on 15/10/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private static final String TAG = ContactAdapter.class.getSimpleName();
    private List<Contact> mList;
    private Context mContext;

    public ContactAdapter(List<Contact> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View row = inflater.inflate(R.layout.item_contact_list, parent, false);

        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact data = mList.get(position);

        holder.txtName.setText(data.getName());

        /*for (Number numbers : data.getNumbers()) {
            holder.txtNumber.setText(numbers.getNumber());
        }*/
//        Log.e(TAG, "onBindViewHolder: name: " + data.getName() + ", emails: " +data.getNumbers());

        for (Email email : data.getEmails()) {
            if (email != null) {
                holder.txtNumber.setText(email.getEmail());
            }
        }
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: " + mList.size());
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtNumber;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.name_contact);
            txtNumber = (TextView) itemView.findViewById(R.id.number_contact);
        }
    }
}
