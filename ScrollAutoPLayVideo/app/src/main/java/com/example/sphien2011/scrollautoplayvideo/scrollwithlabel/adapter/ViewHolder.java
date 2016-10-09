package com.example.sphien2011.scrollautoplayvideo.scrollwithlabel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sphien2011.scrollautoplayvideo.R;

/**
 * Created by sphien2011 on 08/10/2016.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    public final TextView positionView;

    public ViewHolder(View itemView) {
        super(itemView);
        positionView = (TextView) itemView.findViewById(R.id.position);
    }
}
