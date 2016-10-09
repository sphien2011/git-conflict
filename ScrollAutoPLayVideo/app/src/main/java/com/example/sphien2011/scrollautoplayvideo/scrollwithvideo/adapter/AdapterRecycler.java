package com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.model.BaseVideoItem;
import com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.model.VideoViewHolder;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;

import java.util.List;

/**
 * Created by sphien2011 on 09/10/2016.
 */
public class AdapterRecycler extends RecyclerView.Adapter<VideoViewHolder> {

    private VideoPlayerManager videoPlayerManager;
    private List<BaseVideoItem> mList;
    private Context context;

    public AdapterRecycler(VideoPlayerManager videoPlayerManager, List<BaseVideoItem> mList, Context context) {
        this.videoPlayerManager = videoPlayerManager;
        this.mList = mList;
        this.context = context;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        BaseVideoItem videoItem = mList.get(position);
        View resultView = videoItem.createView(parent, context.getResources().getDisplayMetrics().widthPixels);
        return new VideoViewHolder(resultView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        BaseVideoItem videoItem = mList.get(position);
        videoItem.update(position, holder, videoPlayerManager);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
