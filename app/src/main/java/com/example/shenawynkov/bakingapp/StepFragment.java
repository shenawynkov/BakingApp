package com.example.shenawynkov.bakingapp;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.shenawynkov.bakingapp.models.Bake;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.URL;
import java.util.List;

import static android.R.attr.id;
import static android.media.CamcorderProfile.get;

/**
 * Created by Shenawynkov on 7/10/2017.
 */

public class StepFragment extends Fragment {
    public final static String TAG= "step";

    private SimpleExoPlayer player;
    private SimpleExoPlayerView playerView;
    private  Bake bake;
    private int id;
   private TextView textView;
    private     List <Bake.steps> steps;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=  inflater.inflate(R.layout.step_fragment, container, false);




















        Bundle bundle=getArguments();
        bake =(Bake)bundle.getSerializable("bake");
         id =bundle.getInt("id");
         steps=bake.getStepses();
        String url=steps.get(id).getVideoUrl();
        textView=(TextView)v.findViewById(R.id.step_description);
        textView.setText(steps.get(id).getDescription());
           playerView=(SimpleExoPlayerView) v.findViewById(R.id.exo_view);

        initPlayer();

        if(!url.equals("")) {

            prepare_player(Uri.parse(url));
            player.setPlayWhenReady(true);
        }
        else
        {
            player.stop();
            playerView.setVisibility(View.INVISIBLE);



        }


        Button next=(Button)v.findViewById(R.id.next_button);
        Button previous=(Button)v.findViewById(R.id.previous_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             id++;
                String url;
                if(id<steps.size()) {

                    url = steps.get(id).getVideoUrl();


                    textView.setText(steps.get(id).getDescription());
                    if(!url.equals("")) {
                       playerView.setVisibility(View.VISIBLE);

                        prepare_player(Uri.parse(url));
                        player.setPlayWhenReady(true);
                    }
                    else {
                        player.stop();
                        playerView.setVisibility(View.INVISIBLE);
                    }
                }
                else
                    id=steps.size()-1;
            }
        });



        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id--;
                String url;
                if(id>=0) {

                    url = steps.get(id).getVideoUrl();


                    textView.setText(steps.get(id).getDescription());
                    if(!url.equals("")) {
                        playerView.setVisibility(View.VISIBLE);


                        prepare_player(Uri.parse(url));
                        player.setPlayWhenReady(true);
                    }
                    else {
                        player.stop();
                        playerView.setVisibility(View.INVISIBLE);
                    }
                }
                else
                    id=0;
            }

        });











        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE&&!DetailActivity.mTwoBane ) {

            playerView.getLayoutParams().width =ViewGroup.LayoutParams.MATCH_PARENT;
            playerView.getLayoutParams().height =ViewGroup.LayoutParams.MATCH_PARENT;

            hideSystemUI();}












        return v;

    }
    private void hideSystemUI() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void initPlayer(){
       BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
       TrackSelection.Factory videoTrackSelectionFactory =
               new AdaptiveTrackSelection.Factory(bandwidthMeter);
       TrackSelector trackSelector =
               new DefaultTrackSelector(videoTrackSelectionFactory);

// 2. Create the player
        player =
               ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
       playerView.setPlayer(player);


   }
    private void prepare_player(Uri url)
    {
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
// Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "Baking App"), bandwidthMeter);
// Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
// This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(url,
                dataSourceFactory, extractorsFactory, null, null);
// Prepare the player with the source.
        player.prepare(videoSource);
    }

    @Override
    public void onDestroyView() {
            player.stop();
            player.release();

        super.onDestroyView();
    }

}
