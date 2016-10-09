package com.example.sphien2011.scrollautoplayvideo.scrollwithlabel.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sphien2011.scrollautoplayvideo.R;
import com.example.sphien2011.scrollautoplayvideo.scrollwithlabel.model.VisibilityItem;
import com.example.sphien2011.scrollautoplayvideo.scrollwithlabel.adapter.AdapterVisibility;
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.ItemsPositionGetter;
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
//public class MainActivityFragment extends Fragment implements VisibilityItem.ItemCallback {
public class MainActivityFragment extends Fragment {

//    public interface VisibilityUtilsCallback {
//        void setTitle(String title);
//    }

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
//    private VisibilityUtilsCallback visibilityUtilsCallback;

    private List<VisibilityItem> mList = new ArrayList<>(Arrays.asList(
//            new VisibilityItem("1", this),
//            new VisibilityItem("2", this),
//            new VisibilityItem("3", this),
//            new VisibilityItem("4", this),
//            new VisibilityItem("5", this),
//            new VisibilityItem("6", this),
//            new VisibilityItem("7", this),
//            new VisibilityItem("8", this),
//            new VisibilityItem("9", this),
//            new VisibilityItem("10", this),
//            new VisibilityItem("11", this)));
            new VisibilityItem("1"),
            new VisibilityItem("2"),
            new VisibilityItem("3"),
            new VisibilityItem("4"),
            new VisibilityItem("5"),
            new VisibilityItem("6"),
            new VisibilityItem("7"),
            new VisibilityItem("8"),
            new VisibilityItem("9"),
            new VisibilityItem("10")));

    private final ListItemsVisibilityCalculator listItemsVisibilityCalculator = new SingleListViewItemActiveCalculator(new DefaultSingleItemCalculatorCallback(), mList);

    private ItemsPositionGetter itemsPositionGetter;

    private int scrollState;
//    private Toast toast;

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            visibilityUtilsCallback = (VisibilityUtilsCallback) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString());
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        visibilityUtilsCallback = null;
//    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        AdapterVisibility adapter = new AdapterVisibility(mList);
        recyclerView.setAdapter(adapter);
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    scrollState = newState;
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && !mList.isEmpty()) {
                        listItemsVisibilityCalculator.onScrollStateIdle(itemsPositionGetter, layoutManager.findFirstVisibleItemPosition(), layoutManager.findLastVisibleItemPosition());
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (!mList.isEmpty()) {
                        listItemsVisibilityCalculator.onScroll(itemsPositionGetter, layoutManager.findFirstVisibleItemPosition(), layoutManager.findLastVisibleItemPosition() - layoutManager.findFirstVisibleItemPosition() + 1, scrollState);
                    }
                }
            });

        adapter.notifyDataSetChanged();

        itemsPositionGetter = new RecyclerViewItemPositionGetter(layoutManager, recyclerView);

        return view;
    }

//    @Override
//    public void makeToast(String text) {
//        if (toast != null) {
//            toast.cancel();
//            toast = Toast.makeText(getContext(), text, Toast.LENGTH_LONG);
//            toast.show();
//        }
//    }

//    @Override
//    public void onActiveViewChangedActive(View newActiveView, int newActiveViewPosition) {
//        visibilityUtilsCallback.setTitle("Active view at position " + newActiveViewPosition);
//    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mList.isEmpty()) {
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    listItemsVisibilityCalculator.onScrollStateIdle(itemsPositionGetter, layoutManager.findFirstVisibleItemPosition(), layoutManager.findLastVisibleItemPosition());
                }
            });
        }
    }
}
