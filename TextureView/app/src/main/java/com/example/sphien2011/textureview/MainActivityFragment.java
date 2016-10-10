package com.example.sphien2011.textureview;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.SeekBar;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements TextureView.SurfaceTextureListener, MediaPlayer.OnPreparedListener {
    private MediaPlayer mediaPlayer;
    private TextureView textureView;
    private SeekBar seekBar;
    private Handler handler = new Handler();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        textureView = (TextureView) view.findViewById(R.id.texture_view);
        textureView.setSurfaceTextureListener(this);

        seekBar = (SeekBar) view.findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(mUpdateTimeTask);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(mUpdateTimeTask);

                int totalDuration = mediaPlayer.getDuration();
                int currentPosition = Utilities.progressToTimer(seekBar.getProgress(), totalDuration);

                mediaPlayer.seekTo(currentPosition);

                updateProgressBar();
            }
        });

        return view;
    }

    public void updateProgressBar() {
        handler.postDelayed(mUpdateTimeTask, 100);
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {
            long totalDuration = mediaPlayer.getDuration();
            long currentDuration = mediaPlayer.getCurrentPosition();

            int progress = (int) (Utilities.getProgressPercentage(currentDuration, totalDuration));
            seekBar.setProgress(progress);

            handler.postDelayed(this, 100);
        }
    };

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        Surface surface = new Surface(surfaceTexture);

        try {
            AssetFileDescriptor assetFileDescriptor = getContext().getAssets().openFd("sample_video.mp4");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.setSurface(surface);
            mediaPlayer.prepareAsync();

            /*mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });*/
            mediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();

        seekBar.setProgress(0);
        seekBar.setMax(100);

        updateProgressBar();

//        mediaController = new MediaController(getContext());
//        mediaController.setMediaPlayer(this);
//        mediaController.setAnchorView(textureView);
//        mediaController.setEnabled(true);
//        mediaController.show();
    }

//    @Override
//    public void start() {
//        if (null != mediaPlayer) {
//            mediaPlayer.start();
//        }
//    }
//
//    @Override
//    public void pause() {
//        if (null != mediaPlayer) {
//            mediaPlayer.stop();
//        }
//    }
//
//    @Override
//    public int getDuration() {
//        if (null != mediaPlayer)
//            return mediaPlayer.getDuration();
//        else
//            return 0;
//    }
//
//    @Override
//    public int getCurrentPosition() {
//        if (null != mediaPlayer) {
//            return mediaPlayer.getCurrentPosition();
//        } else {
//            return 0;
//        }
//    }
//
//    @Override
//    public void seekTo(int i) {
//        if (null != mediaPlayer) {
//            mediaPlayer.seekTo(i);
//        }
//    }
//
//    @Override
//    public boolean isPlaying() {
//        if (null != mediaPlayer)
//            return mediaPlayer.isPlaying();
//        else
//            return false;
//    }
//
//    @Override
//    public int getBufferPercentage() {
//        return 0;
//    }
//
//    @Override
//    public boolean canPause() {
//        return false;
//    }
//
//    @Override
//    public boolean canSeekBackward() {
//        return false;
//    }
//
//    @Override
//    public boolean canSeekForward() {
//        return false;
//    }
//
//    @Override
//    public int getAudioSessionId() {
//        return 0;
//    }
}
