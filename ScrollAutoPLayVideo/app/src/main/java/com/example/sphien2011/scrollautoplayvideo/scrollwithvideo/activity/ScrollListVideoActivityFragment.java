package com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.sphien2011.scrollautoplayvideo.R;
import com.example.sphien2011.scrollautoplayvideo.scrollwithlabel.adapter.AdapterVisibility;
import com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.adapter.AdapterRecycler;
import com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.model.AssetVideoItem;
import com.example.sphien2011.scrollautoplayvideo.scrollwithvideo.model.BaseVideoItem;
import com.squareup.picasso.Picasso;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.ItemsPositionGetter;
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScrollListVideoActivityFragment extends Fragment {
    private ArrayList<BaseVideoItem> mList = new ArrayList<>();
    private ListItemsVisibilityCalculator listItemsVisibilityCalculator = new SingleListViewItemActiveCalculator(new DefaultSingleItemCalculatorCallback(), mList);

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private ItemsPositionGetter itemsPositionGetter;

    private VideoPlayerManager<MetaData> videoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
        @Override
        public void onPlayerItemChanged(MetaData currentItemMetaData) {

        }
    });

    private int scrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

    public ScrollListVideoActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll_list_video, container, false);

        try {
            mList.add(new AssetVideoItem(videoPlayerManager, getContext().getAssets().openFd("video_sample_1.mp4"), "video_sample_1.mp4", Picasso.with(getContext()), R.mipmap.ic_launcher));
            mList.add(new AssetVideoItem(videoPlayerManager, getContext().getAssets().openFd("video_sample_1.mp4"), "video_sample_2.mp4", Picasso.with(getContext()), R.mipmap.ic_launcher));
            mList.add(new AssetVideoItem(videoPlayerManager, getContext().getAssets().openFd("video_sample_1.mp4"), "video_sample_3.mp4", Picasso.with(getContext()), R.mipmap.ic_launcher));
            mList.add(new AssetVideoItem(videoPlayerManager, getContext().getAssets().openFd("video_sample_1.mp4"), "video_sample_4.mp4", Picasso.with(getContext()), R.mipmap.ic_launcher));
            mList.add(new AssetVideoItem(videoPlayerManager, getContext().getAssets().openFd("video_sample_1.mp4"), "video_sample_5.mp4", Picasso.with(getContext()), R.mipmap.ic_launcher));
            mList.add(new AssetVideoItem(videoPlayerManager, getContext().getAssets().openFd("video_sample_1.mp4"), "video_sample_6.mp4", Picasso.with(getContext()), R.mipmap.ic_launcher));
            mList.add(new AssetVideoItem(videoPlayerManager, getContext().getAssets().openFd("video_sample_1.mp4"), "video_sample_7.mp4", Picasso.with(getContext()), R.mipmap.ic_launcher));
            mList.add(new AssetVideoItem(videoPlayerManager, getContext().getAssets().openFd("video_sample_1.mp4"), "video_sample_8.mp4", Picasso.with(getContext()), R.mipmap.ic_launcher));
        } catch (IOException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        AdapterRecycler adapter = new AdapterRecycler(videoPlayerManager, mList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    listItemsVisibilityCalculator.onScroll(itemsPositionGetter,layoutManager.findFirstVisibleItemPosition(), layoutManager.findLastVisibleItemPosition() - layoutManager.findFirstVisibleItemPosition() + 1, scrollState);
                }
            }
        });

        itemsPositionGetter = new RecyclerViewItemPositionGetter(layoutManager, recyclerView);
        return view;
    }

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

    @Override
    public void onStop() {
        super.onStop();
        videoPlayerManager.resetMediaPlayer();
    }
}
