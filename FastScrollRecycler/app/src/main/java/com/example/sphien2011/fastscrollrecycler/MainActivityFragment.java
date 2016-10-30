package com.example.sphien2011.fastscrollrecycler;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ArrayList<Contact> mList;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mList = new ArrayList<>();
        mList.add(new Contact("hien"));
        mList.add(new Contact("lan"));
        mList.add(new Contact("phuc"));
        mList.add(new Contact("mo"));
        mList.add(new Contact("ai"));
        mList.add(new Contact("dan"));
        mList.add(new Contact("zelan"));
        mList.add(new Contact("van"));
        mList.add(new Contact("tue"));
        mList.add(new Contact("minh"));
        mList.add(new Contact("hong"));
        mList.add(new Contact("men"));
        mList.add(new Contact("nga"));
        mList.add(new Contact("linh"));
        mList.add(new Contact("lien"));
        mList.add(new Contact("thanh"));
        mList.add(new Contact("nhoc"));
        mList.add(new Contact("oc"));
        mList.add(new Contact("mi"));
        mList.add(new Contact("cong"));
        mList.add(new Contact("xua"));
        mList.add(new Contact("hien"));
        mList.add(new Contact("lan"));
        mList.add(new Contact("phuc"));
        mList.add(new Contact("mo"));
        mList.add(new Contact("ai"));
        mList.add(new Contact("dan"));
        mList.add(new Contact("zelan"));
        mList.add(new Contact("van"));
        mList.add(new Contact("tue"));
        mList.add(new Contact("minh"));
        mList.add(new Contact("hong"));
        mList.add(new Contact("men"));
        mList.add(new Contact("nga"));
        mList.add(new Contact("linh"));
        mList.add(new Contact("lien"));
        mList.add(new Contact("thanh"));
        mList.add(new Contact("nhoc"));
        mList.add(new Contact("oc"));
        mList.add(new Contact("mi"));
        mList.add(new Contact("cong"));
        mList.add(new Contact("xua"));
        ContactAdapter adapter = new ContactAdapter(mList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
