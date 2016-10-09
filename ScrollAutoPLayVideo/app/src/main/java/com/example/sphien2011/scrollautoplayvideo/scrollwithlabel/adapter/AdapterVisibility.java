package com.example.sphien2011.scrollautoplayvideo.scrollwithlabel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sphien2011.scrollautoplayvideo.R;
import com.example.sphien2011.scrollautoplayvideo.scrollwithlabel.model.VisibilityItem;

import java.util.List;

/**
 * Created by sphien2011 on 08/10/2016.
 */
public class AdapterVisibility extends RecyclerView.Adapter<ViewHolder> {
    private final List<VisibilityItem> mList;

    public AdapterVisibility(List<VisibilityItem> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = view.getResources().getDisplayMetrics().widthPixels;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VisibilityItem item = mList.get(position);
        item.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
