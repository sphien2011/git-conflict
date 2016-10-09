package com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.model;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sphien2011.scrollautoplayvideo.R;
import com.volokh.danylo.video_player_manager.manager.VideoItem;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.CurrentItemMetaData;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.MediaPlayerWrapper;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;
import com.volokh.danylo.visibility_utils.items.ListItem;

/**
 * Created by sphien2011 on 08/10/2016.
 */
public abstract class BaseVideoItem implements VideoItem, ListItem {

    private final Rect mCurrentViewRect = new Rect();
    private final VideoPlayerManager<MetaData> mVideoPlayerManager;

    public abstract void update(int position, VideoViewHolder view, VideoPlayerManager videoPlayerManager);

    public BaseVideoItem(VideoPlayerManager<MetaData> mVideoPlayerManager) {
        this.mVideoPlayerManager = mVideoPlayerManager;
    }

    public View createView(ViewGroup parent, int screenWidth) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = screenWidth;

        final VideoViewHolder videoViewHolder = new VideoViewHolder(view);
        view.setTag(videoViewHolder);

        videoViewHolder.player.addMediaPlayerListener(new MediaPlayerWrapper.MainThreadMediaPlayerListener() {
            @Override
            public void onVideoSizeChangedMainThread(int width, int height) {

            }

            @Override
            public void onVideoPreparedMainThread() {
                videoViewHolder.cover.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onVideoCompletionMainThread() {

            }

            @Override
            public void onErrorMainThread(int what, int extra) {

            }

            @Override
            public void onBufferingUpdateMainThread(int percent) {

            }

            @Override
            public void onVideoStoppedMainThread() {
                videoViewHolder.cover.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    @Override
    public int getVisibilityPercents(View view) {

        int percent = 100;

        view.getLocalVisibleRect(mCurrentViewRect);

        int height = view.getHeight();

        if (viewIsPartiallyHiddenTop()) {
            percent = (height - mCurrentViewRect.top) * 100 / height;
        } else if (viewIsPartiallyHiddenBottom(height)) {
            percent = mCurrentViewRect.bottom * 100 / height;
        }

        setVisibilityPercentText(view, percent);
        return percent;
    }

    @Override
    public void setActive(View newActiveView, int newActiveViewPosition) {
        VideoViewHolder viewHolder = (VideoViewHolder) newActiveView.getTag();
        playNewVideo(new CurrentItemMetaData(newActiveViewPosition, newActiveView), viewHolder.player, mVideoPlayerManager);
    }

    @Override
    public void deactivate(View currentView, int position) {
        stopPlayback(mVideoPlayerManager);
    }

    private boolean viewIsPartiallyHiddenBottom(int height) {
        return mCurrentViewRect.bottom > 0 && mCurrentViewRect.bottom < height;
    }

    private boolean viewIsPartiallyHiddenTop() {
        return mCurrentViewRect.top > 0;
    }

    private void setVisibilityPercentText(View currentView, int percent) {
        VideoViewHolder videoViewHolder = (VideoViewHolder) currentView.getTag();
        String percentText = "Visibility percent: " + String.valueOf(percent);

        videoViewHolder.visibilityPercent.setText(percentText);;
    }


}
