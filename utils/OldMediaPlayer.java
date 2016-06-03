package com.immediasemi.blink.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

public class OldMediaPlayer
  implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener
{
  private final String TAG = "OldMediaPlayer";
  int mBufferPercentage;
  Context mContext;
  SurfaceHolder mHolder;
  boolean mIsVideoReadyToBePlayed;
  boolean mIsVideoSizeKnown;
  CustomMediaController mMediaController;
  MediaPlayer mMediaPlayer;
  MediaPlayer.OnPreparedListener mOnPreparedListener;
  int mVideoHeight;
  int mVideoWidth;
  
  private void doCleanUp()
  {
    this.mVideoWidth = 0;
    this.mVideoHeight = 0;
    this.mIsVideoReadyToBePlayed = false;
    this.mIsVideoSizeKnown = false;
  }
  
  private void releaseMediaPlayer()
  {
    if (this.mMediaPlayer != null)
    {
      this.mMediaPlayer.release();
      this.mMediaPlayer = null;
    }
  }
  
  private void startVideoPlayback()
  {
    Log.v("OldMediaPlayer", "startVideoPlayback");
    this.mHolder.setFixedSize(this.mVideoWidth, this.mVideoHeight);
    this.mMediaPlayer.start();
  }
  
  public int getBufferPercentage()
  {
    return this.mBufferPercentage;
  }
  
  public int getCurrentPosition()
  {
    if (this.mMediaPlayer != null) {}
    for (int i = this.mMediaPlayer.getCurrentPosition();; i = 0) {
      return i;
    }
  }
  
  public int getDuration()
  {
    if (this.mMediaPlayer != null) {}
    for (int i = this.mMediaPlayer.getDuration();; i = 0) {
      return i;
    }
  }
  
  public boolean isPlaying()
  {
    if (this.mMediaPlayer != null) {}
    for (boolean bool = this.mMediaPlayer.isPlaying();; bool = false) {
      return bool;
    }
  }
  
  public void onBufferingUpdate(MediaPlayer paramMediaPlayer, int paramInt)
  {
    this.mBufferPercentage = paramInt;
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer) {}
  
  public void onDestroy()
  {
    releaseMediaPlayer();
    doCleanUp();
  }
  
  public void onPause()
  {
    releaseMediaPlayer();
    doCleanUp();
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    Log.d("OldMediaPlayer", "onPrepared called");
    this.mIsVideoReadyToBePlayed = true;
    if ((this.mIsVideoReadyToBePlayed) && (this.mIsVideoSizeKnown))
    {
      startVideoPlayback();
      showControls();
      if (this.mOnPreparedListener != null) {
        this.mOnPreparedListener.onPrepared(null);
      }
    }
  }
  
  public void onVideoSizeChanged(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    Log.v("OldMediaPlayer", "onVideoSizeChanged called");
    if ((paramInt1 == 0) || (paramInt2 == 0)) {
      Log.e("OldMediaPlayer", "invalid video width(" + paramInt1 + ") or height(" + paramInt2 + ")");
    }
    for (;;)
    {
      return;
      this.mIsVideoSizeKnown = true;
      this.mVideoWidth = paramInt1;
      this.mVideoHeight = paramInt2;
      if ((this.mIsVideoReadyToBePlayed) && (this.mIsVideoSizeKnown)) {
        startVideoPlayback();
      }
    }
  }
  
  public void pause()
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.pause();
    }
  }
  
  public void playVideo(Context paramContext, SurfaceHolder paramSurfaceHolder, String paramString)
  {
    this.mHolder = paramSurfaceHolder;
    this.mContext = paramContext;
    doCleanUp();
    try
    {
      paramContext = new android/media/MediaPlayer;
      paramContext.<init>();
      this.mMediaPlayer = paramContext;
      this.mMediaPlayer.setDataSource(paramString);
      this.mMediaPlayer.setDisplay(paramSurfaceHolder);
      this.mMediaPlayer.prepare();
      this.mMediaPlayer.setOnBufferingUpdateListener(this);
      this.mMediaPlayer.setOnCompletionListener(this);
      this.mMediaPlayer.setOnPreparedListener(this);
      this.mMediaPlayer.setOnVideoSizeChangedListener(this);
      this.mMediaPlayer.setAudioStreamType(3);
      return;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.e("OldMediaPlayer", "error: " + paramContext.getMessage(), paramContext);
      }
    }
  }
  
  public void seekTo(int paramInt)
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.seekTo(paramInt);
    }
  }
  
  public void setMediaController(CustomMediaController paramCustomMediaController)
  {
    this.mMediaController = paramCustomMediaController;
  }
  
  public void setOnPreparedListener(MediaPlayer.OnPreparedListener paramOnPreparedListener)
  {
    this.mOnPreparedListener = paramOnPreparedListener;
  }
  
  public void showControls()
  {
    if (this.mMediaController != null)
    {
      if ((((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getRotation() & 0x1) != 0) {
        break label41;
      }
      this.mMediaController.show(-1);
    }
    for (;;)
    {
      return;
      label41:
      this.mMediaController.show(3000);
    }
  }
  
  public void start()
  {
    if (this.mMediaPlayer != null) {
      this.mMediaPlayer.start();
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/OldMediaPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */