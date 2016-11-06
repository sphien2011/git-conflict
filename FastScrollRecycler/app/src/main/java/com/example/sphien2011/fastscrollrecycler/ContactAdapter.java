package com.example.sphien2011.fastscrollrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by sphien2011 on 30/10/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements SectionRecyclerAdapter.SectionRecyclerDelegate {
    private List<Contact> mList;
    private Context mContext;

    private int mLineNumber = 0;

    private LinkedHashMap<String, List<Contact>> mSectionedHashMap;

    public ContactAdapter(List<Contact> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        init();
    }

    private void init() {
        mSectionedHashMap = new LinkedHashMap<>();
        Collections.sort(mList);
        mSections.clear();
        for (int i = 0; i<mList.size(); i++) {
            String ch = Utils.getFirstChar(mList.get(i).getName());
            if (ch == null || ch.isEmpty() || !Character.isUpperCase(ch.codePointAt(0)))
                ch = "#";
            List<Contact> contacts = mSectionedHashMap.get(ch);
            if (contacts == null) {
                contacts = new ArrayList<>();
            }
            contacts.add(mList.get(i));
            mSectionedHashMap.put(ch, contacts);
        }
        calculateSectionPosition();

    }

    public void calculateSectionPosition() {
        Set<String> keySet = mSectionedHashMap.keySet();
        String strings[] = new String[keySet.size()];
        keySet.toArray(strings);
        Arrays.sort(strings);
        int position = 0;
        for (String title : strings) {
            SectionRecyclerAdapter.Section section = new SectionRecyclerAdapter.Section(position, title);
            mSections.add(section);
            position += mSectionedHashMap.get(title).size();
        }
        mLineNumber = position;

    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = mList.get(position);

        holder.txtName.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
//        return mList.size();
        return mLineNumber;
    }

    @Override
    public List<SectionRecyclerAdapter.Section> getSections() {
        return mSections;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
        }
    }
}
