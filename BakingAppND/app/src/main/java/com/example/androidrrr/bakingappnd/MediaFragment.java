package com.example.androidrrr.bakingappnd;


import android.annotation.SuppressLint;
import android.media.session.MediaSessionManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidrrr.bakingappnd.model.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class MediaFragment extends Fragment {

    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer exoPlayer;
    String ss;

    @SuppressLint("ValidFragment")
    public MediaFragment(String video) {
        // Required empty public constructor
        ss=video;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_media, container, false);
        Toast.makeText(getActivity(), ""+ss, Toast.LENGTH_SHORT).show();
        simpleExoPlayerView=(SimpleExoPlayerView) view.findViewById(R.id.exo_player_view);
        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance((RenderersFactory) this, trackSelector);
            Uri videoURI = Uri.parse(ss);
            Toast.makeText(getContext(), ""+videoURI, Toast.LENGTH_SHORT).show();
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);
            simpleExoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        } catch (Exception e) {
            Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
        }    return view;
    }

}


