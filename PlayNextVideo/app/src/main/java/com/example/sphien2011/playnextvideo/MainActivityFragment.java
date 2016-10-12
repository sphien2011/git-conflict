package com.example.sphien2011.playnextvideo;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private VideoView mVideoView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private int count = 0;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mVideoView = (VideoView) view.findViewById(R.id.video_view);

        arrayList.add("android.resource://"+getContext().getPackageName()+"/"+R.raw.sample_video);
        arrayList.add("android.resource://"+getContext().getPackageName()+"/"+R.raw.sample_video);
        arrayList.add("android.resource://"+getContext().getPackageName()+"/"+R.raw.sample_video);

        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(mVideoView);

        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoPath("android.resource://"+getContext().getPackageName()+"/"+R.raw.sample_video);
        mVideoView.requestFocus();

        Log.e(TAG, "ArrayList size: " + arrayList.size());

        MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (count < arrayList.size() - 1) {
                    count++;
                    Log.e(TAG, "onCompletion: " + count);

                    mVideoView.setVideoPath(arrayList.get(count));
                    mVideoView.start();
                }
            }
        };

        mVideoView.setOnCompletionListener(mCompletionListener);
        mVideoView.start();

        return view;
    }
}
