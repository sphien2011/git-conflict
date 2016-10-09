package com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sphien2011.scrollautoplayvideo.R;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

/**
 * Created by sphien2011 on 09/10/2016.
 */
public class VideoViewHolder extends RecyclerView.ViewHolder {
    public VideoPlayerView player;
    public TextView title;
    public ImageView cover;
    public TextView visibilityPercent;


    public VideoViewHolder(View itemView) {
        super(itemView);
        player = (VideoPlayerView) itemView.findViewById(R.id.player);
        title = (TextView) itemView.findViewById(R.id.title);
        cover = (ImageView) itemView.findViewById(R.id.cover);
        visibilityPercent = (TextView) itemView.findViewById(R.id.visibility_percent);
    }
}