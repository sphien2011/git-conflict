package com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.model;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

/**
 * Created by sphien2011 on 09/10/2016.
 */
public class AssetVideoItem extends BaseVideoItem {
    private AssetFileDescriptor assetFileDescriptor;
    private String title;

    private Picasso imageLoader;
    private int imageResource;

    public AssetVideoItem(VideoPlayerManager<MetaData> mVideoPlayerManager, AssetFileDescriptor assetFileDescriptor, String title, Picasso imageLoader, int imageResource) {
        super(mVideoPlayerManager);
        this.assetFileDescriptor = assetFileDescriptor;
        this.title = title;
        this.imageLoader = imageLoader;
        this.imageResource = imageResource;
    }

    @Override
    public void update(int position, VideoViewHolder view, VideoPlayerManager videoPlayerManager) {
        view.title.setText(title);
        view.cover.setVisibility(View.VISIBLE);
        imageLoader.load(imageResource).into(view.cover);
    }

    @Override
    public void playNewVideo(MetaData currentItemMetaData, VideoPlayerView player, VideoPlayerManager<MetaData> videoPlayerManager) {
        videoPlayerManager.playNewVideo(currentItemMetaData, player, assetFileDescriptor);
    }

    @Override
    public void stopPlayback(VideoPlayerManager videoPlayerManager) {
        videoPlayerManager.stopAnyPlayback();
    }

    @Override
    public String toString() {
        return getClass() + ", title [" + title + "]";
    }
}
