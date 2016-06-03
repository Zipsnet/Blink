package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageButton;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;
import android.widget.VideoView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.activities.VideoLiveViewActivity;
import com.immediasemi.blink.activities.VideoPlayerActivity;
import com.immediasemi.blink.api.requests.BlinkDownloadVideoClip;
import com.immediasemi.blink.api.requests.BlinkDownloadVideoClip.Error;
import com.immediasemi.blink.api.requests.BlinkDownloadVideoClip.Result;
import com.immediasemi.blink.models.Video;
import com.immediasemi.blink.utils.CustomMediaController;
import com.immediasemi.blink.utils.OnClick;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class VideoPlayerFragment
  extends BaseFragment
{
  private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 111;
  private static final int PLAY_FROM_FILE = 2;
  private static final int PLAY_VIDEO = 1;
  private PlayerHandler handler = new PlayerHandler(null);
  private String mCameraName;
  private View mControlsContainer;
  private ImageButton mDeleteButton;
  private boolean mHasPlayed = false;
  private ImageButton mLiveViewButton;
  private View mMainView;
  private CustomMediaController mMediaController;
  private VideoView mPlayerView;
  private View mProgress;
  private int mSectionNumber;
  private ImageButton mShareButton;
  private TextView mTimeStampTextView;
  private Video mVideo;
  private String mVideoClip;
  private String mVideoDate;
  private String mVideoDay;
  
  private void downloadAndPlayVideo()
  {
    new BlinkDownloadVideoClip(Uri.parse(BlinkApp.getApp().getServerUrl() + this.mVideo.getAddress()), new BlinkDownloadVideoClip.Result()new BlinkDownloadVideoClip.Error
    {
      public void result(String paramAnonymousString)
      {
        if (VideoPlayerFragment.this.getActivity() == null) {}
        for (;;)
        {
          return;
          VideoPlayerFragment.access$002(VideoPlayerFragment.this, paramAnonymousString);
          VideoPlayerFragment.this.mProgress.setVisibility(4);
          if (VideoPlayerFragment.this.mPlayerView != null) {
            VideoPlayerFragment.this.mPlayerView.setVideoPath(VideoPlayerFragment.this.mVideoClip);
          }
          paramAnonymousString = VideoPlayerFragment.this.handler.obtainMessage(2, VideoPlayerFragment.this);
          VideoPlayerFragment.this.handler.sendMessageDelayed(paramAnonymousString, 100L);
        }
      }
    }, new BlinkDownloadVideoClip.Error()
    {
      public void error(String paramAnonymousString)
      {
        if (VideoPlayerFragment.this.getActivity() == null) {}
        for (;;)
        {
          return;
          VideoPlayerFragment.this.mProgress.setVisibility(4);
          new AlertDialog.Builder(VideoPlayerFragment.this.getActivity()).setTitle("Error").setMessage(paramAnonymousString).setPositiveButton(2131100007, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              VideoPlayerFragment.this.playVideo();
            }
          }).setNegativeButton(2131099778, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              if (VideoPlayerFragment.this.getActivity() != null) {
                VideoPlayerFragment.this.getActivity().finish();
              }
            }
          }).create().show();
        }
      }
    });
  }
  
  private void handleMessage(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      if (getActivity() != null) {
        if (this.mPlayerView != null)
        {
          playVideo();
        }
        else
        {
          Message localMessage = this.handler.obtainMessage(1, this);
          this.handler.sendMessageDelayed(localMessage, 100L);
          continue;
          if ((getActivity() != null) && (this.mPlayerView != null)) {
            playFromFile();
          }
        }
      }
    }
  }
  
  public static VideoPlayerFragment newInstance(int paramInt, Video paramVideo, String paramString, String[] paramArrayOfString)
  {
    VideoPlayerFragment localVideoPlayerFragment = new VideoPlayerFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localVideoPlayerFragment.initData(paramString, paramArrayOfString, paramVideo);
    localVideoPlayerFragment.setArguments(localBundle);
    return localVideoPlayerFragment;
  }
  
  private void playFromFile()
  {
    if (getActivity() == null) {
      return;
    }
    if (this.mMediaController == null)
    {
      this.mMediaController = new CustomMediaController(getActivity(), false);
      CustomNewMediaPlayerControl localCustomNewMediaPlayerControl = new CustomNewMediaPlayerControl();
      this.mMediaController.setAnchorView(this.mPlayerView);
      this.mMediaController.setMediaPlayer(localCustomNewMediaPlayerControl);
      this.mPlayerView.setMediaController(this.mMediaController);
    }
    if (this.mSectionNumber < 0) {
      setMediaPrevNextButtons(true, true);
    }
    if (((VideoPlayerActivity)getActivity()).isPortrait())
    {
      this.mMediaController.show(-1);
      setMediaControllerIsHidden(false);
    }
    for (;;)
    {
      this.mProgress.setVisibility(4);
      this.mPlayerView.start();
      break;
      this.mMediaController.show(3000);
    }
  }
  
  private void playNext()
  {
    if (this.mListener != null) {
      this.mListener.onFragmentInteraction(this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.PLAY_NEXT, null);
    }
  }
  
  private void playPrevious()
  {
    if (this.mListener != null) {
      this.mListener.onFragmentInteraction(this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.PLAY_PREVIOUS, null);
    }
  }
  
  private void playVideo()
  {
    this.mHasPlayed = true;
    downloadAndPlayVideo();
  }
  
  private void shareTheClip()
  {
    Object localObject3 = new File(this.mVideoClip);
    Object localObject1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    localObject1 = new File((String)localObject1 + "/" + "video_clip.mp4");
    if ((localObject1 != null) && (((File)localObject1).exists())) {
      ((File)localObject1).delete();
    }
    Object localObject2;
    try
    {
      localObject2 = new java/io/FileInputStream;
      ((FileInputStream)localObject2).<init>((File)localObject3);
      localObject3 = new java/io/FileOutputStream;
      ((FileOutputStream)localObject3).<init>((File)localObject1);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = ((InputStream)localObject2).read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        ((OutputStream)localObject3).write(arrayOfByte, 0, i);
      }
      return;
    }
    catch (Exception localException)
    {
      new AlertDialog.Builder(getActivity()).setTitle(2131099816).setMessage("2131099779 - " + localException.getLocalizedMessage()).setPositiveButton(2131099891, null).create().show();
    }
    for (;;)
    {
      ((InputStream)localObject2).close();
      ((OutputStream)localObject3).close();
      localObject2 = new android/content/Intent;
      ((Intent)localObject2).<init>("android.intent.action.SEND");
      ((Intent)localObject2).putExtra("android.intent.extra.SUBJECT", getString(2131099939));
      ((Intent)localObject2).putExtra("android.intent.extra.TEXT", getString(2131099937));
      localObject3 = new java/lang/StringBuilder;
      ((StringBuilder)localObject3).<init>();
      ((Intent)localObject2).putExtra("android.intent.extra.HTML_TEXT", "<b>" + getString(2131099937) + "/b>");
      ((Intent)localObject2).putExtra("android.intent.extra.STREAM", Uri.fromFile(localException));
      ((Intent)localObject2).setType("message/rfc822");
      startActivity(Intent.createChooser((Intent)localObject2, getString(2131099938)));
    }
  }
  
  public void initData(String paramString, String[] paramArrayOfString, Video paramVideo)
  {
    this.mCameraName = paramString;
    this.mVideoDay = paramArrayOfString[0];
    this.mVideoDate = paramArrayOfString[1];
    this.mVideo = paramVideo;
    BlinkApp.getApp().setLastCameraId(String.valueOf(this.mVideo.getCamera_id()));
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(this.mCameraName);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    View localView = this.mMainView.findViewById(2131558643);
    if (paramConfiguration.orientation == 2)
    {
      this.mTimeStampTextView.setVisibility(4);
      localView.setBackgroundColor(-16777216);
    }
    for (;;)
    {
      this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          VideoPlayerFragment.this.toggleStatusBar();
        }
      }, 100L);
      return;
      this.mTimeStampTextView.setVisibility(0);
      localView.setBackgroundColor(-1);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
    setRetainInstance(true);
    this.mHasPlayed = false;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mMainView = paramLayoutInflater.inflate(2130903118, paramViewGroup, false);
    this.mControlsContainer = this.mMainView.findViewById(2131558685);
    this.mShareButton = ((ImageButton)this.mMainView.findViewById(2131558691));
    this.mShareButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (VideoPlayerFragment.this.getActivity() == null) {}
        for (;;)
        {
          return;
          if (VideoPlayerFragment.this.mVideoClip == null)
          {
            new AlertDialog.Builder(VideoPlayerFragment.this.getActivity()).setTitle(2131099816).setMessage(2131099916).setPositiveButton(2131099891, null).create().show();
          }
          else
          {
            if (Build.VERSION.SDK_INT < 23) {}
            for (;;)
            {
              if (1 != 0) {
                break label210;
              }
              new AlertDialog.Builder(VideoPlayerFragment.this.getActivity()).setTitle(2131099954).setMessage(2131099779).setPositiveButton(2131099891, null).create().show();
              break;
              if (!BlinkApp.getApp().getPermissionsGranted()) {
                if (VideoPlayerFragment.this.getActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0)
                {
                  if (VideoPlayerFragment.this.shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    new AlertDialog.Builder(VideoPlayerFragment.this.getActivity()).setTitle(2131099885).setMessage(2131099831).setPositiveButton(2131099891, null).create().show();
                  }
                  VideoPlayerFragment.this.requestPermissions(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 111);
                  break;
                }
              }
            }
            label210:
            VideoPlayerFragment.this.shareTheClip();
          }
        }
      }
    });
    this.mLiveViewButton = ((ImageButton)this.mMainView.findViewById(2131558690));
    this.mLiveViewButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (VideoPlayerFragment.this.getActivity() == null) {}
        for (;;)
        {
          return;
          if (OnClick.ok())
          {
            VideoPlayerFragment.this.handler.removeMessages(1);
            if (VideoPlayerFragment.this.mPlayerView != null) {
              VideoPlayerFragment.this.mPlayerView.stopPlayback();
            }
            paramAnonymousView = new Intent(VideoPlayerFragment.this.getActivity(), VideoLiveViewActivity.class);
            VideoPlayerFragment.this.startActivityForResult(paramAnonymousView, 109);
          }
        }
      }
    });
    this.mDeleteButton = ((ImageButton)this.mMainView.findViewById(2131558675));
    this.mDeleteButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (VideoPlayerFragment.this.getActivity() == null) {}
        for (;;)
        {
          return;
          if (VideoPlayerFragment.this.mVideo.getId() > 0) {
            BlinkApp.getApp().setLastVideoId(String.valueOf(VideoPlayerFragment.this.mVideo.getId()));
          }
          if (VideoPlayerFragment.this.mListener != null) {
            VideoPlayerFragment.this.mListener.onFragmentInteraction(VideoPlayerFragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.DELETE_CLIP, null);
          }
        }
      }
    });
    this.mPlayerView = ((VideoView)this.mMainView.findViewById(2131558686));
    this.mTimeStampTextView = ((TextView)this.mMainView.findViewById(2131558687));
    this.mTimeStampTextView.setText(this.mVideoDay + " " + this.mVideoDate);
    this.mProgress = this.mMainView.findViewById(2131558541);
    this.mProgress.setVisibility(0);
    ((VideoPlayerActivity)getActivity()).initActionBar(this.mMainView);
    return this.mMainView;
  }
  
  public void onPause()
  {
    this.handler.removeMessages(1);
    this.handler.removeMessages(2);
    if (this.mPlayerView != null)
    {
      this.mPlayerView.pause();
      this.mPlayerView.stopPlayback();
    }
    if (this.mMediaController != null) {
      this.mMediaController.unConditionalHide();
    }
    super.onPause();
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      if (paramArrayOfInt[0] == 0)
      {
        BlinkApp.getApp().setPermissionsGranted(true);
        shareTheClip();
      }
      else
      {
        BlinkApp.getApp().setPermissionsGranted(false);
        new AlertDialog.Builder(getActivity()).setTitle(2131099954).setMessage(2131099779).setPositiveButton(2131099891, null).create().show();
      }
    }
  }
  
  public void onResume()
  {
    super.onResume();
    toggleStatusBar();
    if (!this.mHasPlayed)
    {
      Message localMessage = this.handler.obtainMessage(1, this);
      this.handler.sendMessageDelayed(localMessage, 100L);
    }
  }
  
  public void playClip(int paramInt, Video paramVideo, String paramString, String[] paramArrayOfString)
  {
    new Bundle().putInt("arg_section_number", paramInt);
    this.mSectionNumber = getArguments().getInt("arg_section_number");
    initData(paramString, paramArrayOfString, paramVideo);
    this.mTimeStampTextView.setText(this.mVideoDay + " " + this.mVideoDate);
    ((VideoPlayerActivity)getActivity()).initActionBar(this.mMainView);
    this.mProgress.setVisibility(0);
    paramVideo = this.handler.obtainMessage(1, this);
    this.handler.sendMessageDelayed(paramVideo, 100L);
  }
  
  public void removeMediaController()
  {
    ((ViewManager)this.mMediaController.getParent()).removeView(this.mMediaController);
    this.mMediaController = null;
  }
  
  public void setMediaControllerIsHidden(boolean paramBoolean)
  {
    if (this.mMediaController != null)
    {
      if (!((VideoPlayerActivity)getActivity()).isPortrait()) {
        break label52;
      }
      this.mControlsContainer.setVisibility(0);
      this.mControlsContainer.bringToFront();
      this.mMediaController.show(-1);
      this.mMediaController.setVisibility(0);
    }
    for (;;)
    {
      return;
      label52:
      this.mControlsContainer.setVisibility(8);
      if (paramBoolean)
      {
        this.mMediaController.show(0);
        this.mMediaController.setVisibility(4);
      }
      else
      {
        this.mMediaController.show(3000);
        this.mMediaController.setVisibility(0);
      }
    }
  }
  
  public void setMediaPrevNextButtons(boolean paramBoolean1, boolean paramBoolean2)
  {
    View.OnClickListener local8 = null;
    if (paramBoolean1) {}
    for (View.OnClickListener local7 = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (VideoPlayerFragment.this.getActivity() == null) {}
            for (;;)
            {
              return;
              if (OnClick.ok()) {
                VideoPlayerFragment.this.playPrevious();
              }
            }
          }
        };; local7 = null)
    {
      if (paramBoolean2) {
        local8 = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (VideoPlayerFragment.this.getActivity() == null) {}
            for (;;)
            {
              return;
              if (OnClick.ok()) {
                VideoPlayerFragment.this.playNext();
              }
            }
          }
        };
      }
      if (this.mMediaController != null) {
        this.mMediaController.setPrevNextListeners(local7, local8);
      }
      return;
    }
  }
  
  void toggleStatusBar() {}
  
  public class CustomNewMediaPlayerControl
    implements MediaController.MediaPlayerControl
  {
    public CustomNewMediaPlayerControl() {}
    
    public boolean canPause()
    {
      return true;
    }
    
    public boolean canSeekBackward()
    {
      return true;
    }
    
    public boolean canSeekForward()
    {
      return true;
    }
    
    public int getAudioSessionId()
    {
      return 0;
    }
    
    public int getBufferPercentage()
    {
      return VideoPlayerFragment.this.mPlayerView.getBufferPercentage();
    }
    
    public int getCurrentPosition()
    {
      return VideoPlayerFragment.this.mPlayerView.getCurrentPosition();
    }
    
    public int getDuration()
    {
      return VideoPlayerFragment.this.mPlayerView.getDuration();
    }
    
    public boolean isPlaying()
    {
      return VideoPlayerFragment.this.mPlayerView.isPlaying();
    }
    
    public void pause()
    {
      VideoPlayerFragment.this.mPlayerView.pause();
    }
    
    public void seekTo(int paramInt)
    {
      VideoPlayerFragment.this.mPlayerView.seekTo(paramInt);
    }
    
    public void start()
    {
      VideoPlayerFragment.this.mPlayerView.start();
    }
  }
  
  private static class PlayerHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      ((VideoPlayerFragment)paramMessage.obj).handleMessage(paramMessage.what);
      super.handleMessage(paramMessage);
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/VideoPlayerFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */