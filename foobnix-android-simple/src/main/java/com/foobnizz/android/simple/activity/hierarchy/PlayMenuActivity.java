package com.foobnizz.android.simple.activity.hierarchy;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.foobnix.commons.LOG;
import com.foobnix.commons.TimeUtil;
import com.foobnix.commons.ViewBinder;
import com.foobnizz.android.simple.R;
import com.foobnizz.android.simple.mediaengine.MediaEngineState;
import com.foobnizz.android.simple.mediaengine.MediaService;

public class PlayMenuActivity extends TopSecondLineActivity {
    private boolean isMenuVisible = true;
    private SeekBar seekBar;
    private TextView trackTime;
    private TextView trackBuffer;
    private TextView trackInfo;
    private ImageView playPause;
    private LinearLayout menuLayout;

    @Override
    public void onActivate(Activity activity) {
        super.onActivate(activity);
        ViewBinder.onClick(activity, R.id.playlist_next, onNext);
        ViewBinder.onClick(activity, R.id.playlist_prev, onPrev);

        playPause = (ImageView) activity.findViewById(R.id.playlist_play_pause);
        playPause.setOnClickListener(onPlayPause);

        seekBar = (SeekBar) activity.findViewById(R.id.playilst_seek);
        seekBar.setOnSeekBarChangeListener(seekLisner);

        trackTime = (TextView) activity.findViewById(R.id.playlist_track_time);
        trackBuffer = (TextView) activity.findViewById(R.id.playlist_buffering);
        trackInfo = (TextView) activity.findViewById(R.id.playlist_info_line);

        menuLayout = (LinearLayout) activity.findViewById(R.id.menuLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LOG.d("On menu Press");
        isMenuVisible = !isMenuVisible;
        displayImageMenu();
        return false;
    }

    public void disablePlayerMenu() {
        menuLayout.setVisibility(View.GONE);
    }

    private void displayImageMenu() {
        if (isMenuVisible) {
            menuLayout.setVisibility(View.VISIBLE);
            Animation a = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
            a.setFillAfter(true);
            menuLayout.setLayoutAnimation(new LayoutAnimationController(a));
            menuLayout.startLayoutAnimation();

        } else {
            Animation a = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
            a.setFillAfter(true);
            menuLayout.setLayoutAnimation(new LayoutAnimationController(a));
            a.setAnimationListener(new AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    menuLayout.setVisibility(View.GONE);

                }
            });
            menuLayout.startLayoutAnimation();
        }
    }

    OnSeekBarChangeListener seekLisner = new OnSeekBarChangeListener() {
        private int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            this.progress = progress;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            MediaService.seekTo(progress);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            MediaService.seekTo(progress);
        }

    };

    View.OnClickListener onPlayPause = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            MediaService.playPause();
        }
    };

    View.OnClickListener onPrev = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            MediaService.prev();

        }
    };
    View.OnClickListener onNext = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            MediaService.next();

        }
    };

    @Override
    public void onUpdateUIListener(MediaEngineState state) {
        seekBar.setMax(state.getDuration());
        seekBar.setProgress(state.getCurrentPosition());
        
        String cur = TimeUtil.durationToString(state.getCurrentPosition());
        String dur = TimeUtil.durationToString(state.getDuration());
        trackTime.setText(String.format("%s/%s", cur, dur));
        
        trackBuffer.setText("buffer:" + state.getBuffering() + "%");

        if (state.getModel() != null) {
            trackInfo.setText(state.getModel().getArtistTitle());
        }

        if (state.isPlaying()) {
            playPause.setImageResource(android.R.drawable.ic_media_pause);
        } else {
            playPause.setImageResource(android.R.drawable.ic_media_play);
        }
    }

}
