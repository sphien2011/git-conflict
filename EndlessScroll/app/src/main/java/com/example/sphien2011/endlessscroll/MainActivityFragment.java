package com.example.sphien2011.endlessscroll;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<User> listUser;

    protected Handler handler;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        handler = new Handler();

        listUser = loadData();
        Log.e(TAG, "lisUSer: " + listUser.size());

        final List<User> temArray = new ArrayList<>(listUser.subList(0, 20));

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new UserAdapter(temArray, mRecyclerView);

        Log.e(TAG, "onCreateView: " + temArray);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                temArray.add(null);

                //add progress
                mAdapter.notifyItemInserted(temArray.size() - 1);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //remove progress
                        temArray.remove(temArray.size() - 1);
                        mAdapter.notifyItemRemoved(temArray.size());

                        int start = temArray.size();
                        int end = start + 20;
                        if (end <= listUser.size()) {
                            temArray.addAll(listUser.subList(start, end));
                            mAdapter.notifyItemInserted(temArray.size());
                        }

                        mAdapter.setLoaded();
                    }
                }, 2000);

            }
        });

        return view;
    }

    private List<User> loadData() {
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(new User("User " + i, "Android student " + i + "@gmail.com"));
        }
        return list;
    }
}
