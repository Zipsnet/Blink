package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Cameras.EnableLiveViewRequest;
import com.immediasemi.blink.api.requests.Command.CommandDoneRequest;
import com.immediasemi.blink.api.requests.LiveViewStatsRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.LiveVideoResponse;
import com.immediasemi.blink.utils.OnClick;
import com.immediasemi.rtsp.RTSPNotificationReceiver;
import com.immediasemi.rtsp.RTSPPlayer;
import com.immediasemi.rtsp.RTSPPlayerView;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class VideoLiveViewFragment
  extends BaseFragment
  implements RTSPNotificationReceiver
{
  private static final int GET_URL = 2;
  private static final int PLAY_VIDEO = 1;
  public static final String PREF_LIVE_VIEW_MUTED = "live_view_muted";
  private Handler handler = new VideoHandler();
  private RelativeLayout mButtonBar;
  private FrameLayout mContainer;
  private Button mContinueButton;
  private boolean mDebugOverlay = false;
  private Runnable mHideTools;
  private FrameLayout mLiveViewBackground;
  private long mLiveViewDurationTime;
  private long mLiveViewEndTime;
  private View mMainView;
  private Button mMuteButton;
  private RTSPPlayer mRTSPPlayer;
  private RTSPPlayerView mRTSPPlayerView;
  private int mSectionNumber;
  public boolean mSecure = true;
  private boolean mShouldContinueCountdown;
  private ContentLoadingProgressBar mSpinner;
  String mStats;
  private Button mStopButton;
  private boolean mStopCalled = false;
  private float mSwipeStartX;
  private float mSwipeStartY;
  private boolean mToolsVisible = false;
  private String mVideoUrl;
  private ImageView thumbnailView;
  
  private void fadeThumbnail()
  {
    if (this.thumbnailView != null)
    {
      AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
      localAlphaAnimation.setDuration(300L);
      this.thumbnailView.startAnimation(localAlphaAnimation);
      this.thumbnailView.setVisibility(4);
    }
  }
  
  private void getUrl()
  {
    EnableLiveViewRequest localEnableLiveViewRequest = new EnableLiveViewRequest();
    localEnableLiveViewRequest.setNetwork(Integer.parseInt(BlinkApp.getApp().getLastNetworkId()));
    localEnableLiveViewRequest.setId(Integer.parseInt(BlinkApp.getApp().getLastCameraId()));
    localEnableLiveViewRequest.setType(0);
    BlinkAPI.BlinkAPIRequest(localEnableLiveViewRequest.getQuery(), null, localEnableLiveViewRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if ((VideoLiveViewFragment.this.getActivity() == null) || (VideoLiveViewFragment.this.mListener == null) || (VideoLiveViewFragment.this.isDetached())) {
          return;
        }
        if (paramAnonymousBlinkError.response != null) {}
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = "Could not get live view URL")
        {
          new AlertDialog.Builder(VideoLiveViewFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).setOnDismissListener(new DialogInterface.OnDismissListener()
          {
            public void onDismiss(DialogInterface paramAnonymous2DialogInterface)
            {
              if (VideoLiveViewFragment.this.getActivity() != null) {
                VideoLiveViewFragment.this.getActivity().finish();
              }
            }
          }).create().show();
          break;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((VideoLiveViewFragment.this.getActivity() == null) || (VideoLiveViewFragment.this.mListener == null) || (VideoLiveViewFragment.this.isDetached())) {}
        for (;;)
        {
          return;
          paramAnonymousBlinkData = (LiveVideoResponse)paramAnonymousBlinkData;
          BlinkApp.getApp().setLastCommand(String.valueOf(paramAnonymousBlinkData.getId()));
          VideoLiveViewFragment.access$902(VideoLiveViewFragment.this, paramAnonymousBlinkData.getServer());
          if (VideoLiveViewFragment.this.mSecure)
          {
            VideoLiveViewFragment.access$902(VideoLiveViewFragment.this, VideoLiveViewFragment.this.mVideoUrl.replace("rtsp:", "rtsps:"));
            VideoLiveViewFragment.access$902(VideoLiveViewFragment.this, VideoLiveViewFragment.this.mVideoUrl.replace(":2731/", ":3731/"));
          }
          VideoLiveViewFragment.access$1002(VideoLiveViewFragment.this, System.currentTimeMillis() / 1000L + Integer.parseInt(paramAnonymousBlinkData.getDuration()));
          paramAnonymousBlinkData = VideoLiveViewFragment.this.handler.obtainMessage(1, VideoLiveViewFragment.this);
          VideoLiveViewFragment.this.handler.sendMessage(paramAnonymousBlinkData);
        }
      }
    }, false);
  }
  
  private void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    }
    for (;;)
    {
      return;
      getUrl();
      continue;
      if (this.mVideoUrl != null)
      {
        playVideo();
      }
      else
      {
        paramMessage = this.handler.obtainMessage(1, this);
        this.handler.sendMessageDelayed(paramMessage, 100L);
      }
    }
  }
  
  private boolean isMuted()
  {
    return PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).getBoolean("live_view_muted", true);
  }
  
  private void liveViewDone()
  {
    this.mShouldContinueCountdown = false;
    if (this.mStopCalled) {}
    for (;;)
    {
      return;
      if (this.handler.hasMessages(1)) {
        this.handler.removeMessages(1);
      }
      this.handler.removeMessages(2);
      if (this.mRTSPPlayer != null)
      {
        this.mStopCalled = true;
        this.mRTSPPlayer.unregisterForNotifications(this);
        this.mStats = this.mRTSPPlayer.getStatsJSONRepresentation();
        this.mRTSPPlayer.stop();
        sendLiveViewStats();
      }
      getActivity().finish();
    }
  }
  
  public static VideoLiveViewFragment newInstance(int paramInt)
  {
    VideoLiveViewFragment localVideoLiveViewFragment = new VideoLiveViewFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localVideoLiveViewFragment.setArguments(localBundle);
    return localVideoLiveViewFragment;
  }
  
  private void playVideo()
  {
    try
    {
      RTSPPlayer localRTSPPlayer = this.mRTSPPlayer;
      URI localURI = new java/net/URI;
      localURI.<init>(this.mVideoUrl);
      localRTSPPlayer.setURI(localURI);
      this.mRTSPPlayer.play();
      return;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      for (;;)
      {
        Log.e("Blink LiveView", localURISyntaxException.getMessage());
      }
    }
  }
  
  private void sendLiveViewStats()
  {
    if (this.mStats != null)
    {
      LiveViewStatsRequest localLiveViewStatsRequest = new LiveViewStatsRequest();
      localLiveViewStatsRequest.setLive_view_stats(this.mStats);
      BlinkAPI.BlinkAPIRequest(null, null, localLiveViewStatsRequest, new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError) {}
        
        public void onResult(BlinkData paramAnonymousBlinkData) {}
      }, false);
    }
  }
  
  private void setMuteButtonBackground()
  {
    if (isMuted()) {
      this.mMuteButton.setBackground(ResourcesCompat.getDrawable(getResources(), 2130837616, null));
    }
    for (;;)
    {
      return;
      this.mMuteButton.setBackground(ResourcesCompat.getDrawable(getResources(), 2130837615, null));
    }
  }
  
  private void setupRTSPPlayerView()
  {
    boolean bool2 = false;
    this.mRTSPPlayer = new RTSPPlayer(null);
    this.mRTSPPlayer.registerForNotifications(this);
    this.mRTSPPlayer.setMuted(isMuted());
    this.mMuteButton.setVisibility(0);
    this.mRTSPPlayerView = new RTSPPlayerView(BlinkApp.getApp());
    RTSPPlayerView localRTSPPlayerView = this.mRTSPPlayerView;
    boolean bool1 = bool2;
    if (BlinkApp.getApp().isDebug())
    {
      bool1 = bool2;
      if (this.mDebugOverlay) {
        bool1 = true;
      }
    }
    localRTSPPlayerView.setDebugEnabled(bool1);
    this.mRTSPPlayerView.setPlayer(this.mRTSPPlayer);
    this.mRTSPPlayer.setPlayerView(this.mRTSPPlayerView);
    this.mContainer.addView(this.mRTSPPlayerView);
    this.mRTSPPlayerView.registerForNotifications(this);
  }
  
  private void teardownRTSPPlayer()
  {
    if (this.mRTSPPlayer != null)
    {
      this.mRTSPPlayer.dispose();
      this.mRTSPPlayer = null;
    }
    if (this.mRTSPPlayerView != null)
    {
      this.mRTSPPlayerView.unregisterForNotifications(this);
      ((ViewGroup)this.mRTSPPlayerView.getParent()).removeView(this.mRTSPPlayerView);
      this.mRTSPPlayerView.dispose();
      this.mRTSPPlayerView = null;
    }
  }
  
  private void toggleMute()
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext());
    if (!localSharedPreferences.getBoolean("live_view_muted", true)) {}
    for (boolean bool = true;; bool = false)
    {
      this.mRTSPPlayer.setMuted(bool);
      localSharedPreferences.edit().putBoolean("live_view_muted", bool).apply();
      setMuteButtonBackground();
      return;
    }
  }
  
  private void toggleTools()
  {
    RelativeLayout.LayoutParams localLayoutParams;
    if (getResources().getConfiguration().orientation == 2)
    {
      if (this.mRTSPPlayerView != null) {
        this.mRTSPPlayerView.setBackgroundColor(-16777216);
      }
      this.mMainView.setSystemUiVisibility(4);
      if (BlinkApp.getApp().isDebug())
      {
        localLayoutParams = (RelativeLayout.LayoutParams)this.mContainer.getLayoutParams();
        localLayoutParams.removeRule(3);
        localLayoutParams.addRule(6, 2131558646);
        this.mContainer.setLayoutParams(localLayoutParams);
      }
      this.mButtonBar.setBackgroundResource(2131492877);
      this.mContinueButton.setBackgroundResource(2130837622);
      this.mContinueButton.setTextColor(-1);
      this.mStopButton.setVisibility(4);
      if (this.mToolsVisible)
      {
        this.mToolsVisible = false;
        this.mButtonBar.setVisibility(4);
      }
    }
    for (;;)
    {
      return;
      this.mToolsVisible = true;
      this.mButtonBar.setVisibility(0);
      this.mButtonBar.bringToFront();
      this.mHideTools = new Runnable()
      {
        public void run()
        {
          VideoLiveViewFragment.this.mButtonBar.setVisibility(4);
          VideoLiveViewFragment.access$702(VideoLiveViewFragment.this, false);
        }
      };
      this.handler.postDelayed(this.mHideTools, 4000L);
      continue;
      if (this.mRTSPPlayerView != null) {
        this.mRTSPPlayerView.setBackgroundColor(-1);
      }
      this.mMainView.setSystemUiVisibility(0);
      if (BlinkApp.getApp().isDebug())
      {
        localLayoutParams = (RelativeLayout.LayoutParams)this.mContainer.getLayoutParams();
        localLayoutParams.removeRule(6);
        localLayoutParams.addRule(3, 2131558646);
        this.mContainer.setLayoutParams(localLayoutParams);
      }
      this.handler.removeCallbacks(this.mHideTools);
      this.mButtonBar.setBackgroundResource(2131492875);
      this.mContinueButton.setBackgroundResource(2130837621);
      this.mContinueButton.setTextColor(-16777216);
      this.mStopButton.setVisibility(0);
      this.mToolsVisible = false;
      this.mButtonBar.setVisibility(0);
      this.mButtonBar.bringToFront();
    }
  }
  
  private void updateTimeRemaining()
  {
    if ((!this.mShouldContinueCountdown) || (this.mRTSPPlayer == null) || (this.mRTSPPlayerView == null)) {}
    for (;;)
    {
      return;
      long l2 = System.currentTimeMillis() / 1000L;
      long l1 = this.mLiveViewDurationTime - l2;
      l2 = this.mLiveViewEndTime - l2;
      if ((l1 <= 0L) || (l2 <= 0L))
      {
        stopVideo();
      }
      else
      {
        if ((l1 <= 10L) && (l2 >= 30L))
        {
          this.mContinueButton.setVisibility(0);
          this.mContinueButton.bringToFront();
        }
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            VideoLiveViewFragment.this.updateTimeRemaining();
          }
        }, 500L);
      }
    }
  }
  
  public boolean ismDebugOverlay()
  {
    return this.mDebugOverlay;
  }
  
  public void notificationReceived(final Object paramObject, final String paramString)
  {
    Log.i("VideoLiveViewFragment", "notificationReceived: " + paramString);
    if (getActivity() != null) {
      getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          VideoLiveViewFragment.this.runNotificationReceived(paramObject, paramString);
        }
      });
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099993));
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    toggleTools();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  /* Error */
  public View onCreateView(android.view.LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: ldc_w 588
    //   5: aload_2
    //   6: iconst_0
    //   7: invokevirtual 594	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   10: putfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   13: aload_0
    //   14: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   17: new 8	com/immediasemi/blink/fragments/VideoLiveViewFragment$1
    //   20: dup
    //   21: aload_0
    //   22: invokespecial 595	com/immediasemi/blink/fragments/VideoLiveViewFragment$1:<init>	(Lcom/immediasemi/blink/fragments/VideoLiveViewFragment;)V
    //   25: invokevirtual 599	android/view/View:setOnTouchListener	(Landroid/view/View$OnTouchListener;)V
    //   28: aload_0
    //   29: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   32: ldc_w 600
    //   35: invokevirtual 604	android/view/View:findViewById	(I)Landroid/view/View;
    //   38: checkcast 606	android/widget/TextView
    //   41: invokestatic 182	com/immediasemi/blink/BlinkApp:getApp	()Lcom/immediasemi/blink/BlinkApp;
    //   44: invokevirtual 609	com/immediasemi/blink/BlinkApp:getLastCameraName	()Ljava/lang/String;
    //   47: invokevirtual 613	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   50: aload_0
    //   51: aload_0
    //   52: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   55: ldc_w 614
    //   58: invokevirtual 604	android/view/View:findViewById	(I)Landroid/view/View;
    //   61: checkcast 401	android/widget/FrameLayout
    //   64: putfield 399	com/immediasemi/blink/fragments/VideoLiveViewFragment:mContainer	Landroid/widget/FrameLayout;
    //   67: aload_0
    //   68: aload_0
    //   69: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   72: ldc_w 615
    //   75: invokevirtual 604	android/view/View:findViewById	(I)Landroid/view/View;
    //   78: checkcast 617	android/support/v4/widget/ContentLoadingProgressBar
    //   81: putfield 619	com/immediasemi/blink/fragments/VideoLiveViewFragment:mSpinner	Landroid/support/v4/widget/ContentLoadingProgressBar;
    //   84: aload_0
    //   85: aload_0
    //   86: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   89: ldc_w 468
    //   92: invokevirtual 604	android/view/View:findViewById	(I)Landroid/view/View;
    //   95: checkcast 479	android/widget/RelativeLayout
    //   98: putfield 134	com/immediasemi/blink/fragments/VideoLiveViewFragment:mButtonBar	Landroid/widget/RelativeLayout;
    //   101: aload_0
    //   102: aload_0
    //   103: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   106: ldc_w 620
    //   109: invokevirtual 604	android/view/View:findViewById	(I)Landroid/view/View;
    //   112: checkcast 360	android/widget/Button
    //   115: putfield 126	com/immediasemi/blink/fragments/VideoLiveViewFragment:mContinueButton	Landroid/widget/Button;
    //   118: aload_0
    //   119: getfield 126	com/immediasemi/blink/fragments/VideoLiveViewFragment:mContinueButton	Landroid/widget/Button;
    //   122: new 12	com/immediasemi/blink/fragments/VideoLiveViewFragment$2
    //   125: dup
    //   126: aload_0
    //   127: invokespecial 621	com/immediasemi/blink/fragments/VideoLiveViewFragment$2:<init>	(Lcom/immediasemi/blink/fragments/VideoLiveViewFragment;)V
    //   130: invokevirtual 625	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   133: aload_0
    //   134: aload_0
    //   135: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   138: ldc_w 626
    //   141: invokevirtual 604	android/view/View:findViewById	(I)Landroid/view/View;
    //   144: checkcast 360	android/widget/Button
    //   147: putfield 489	com/immediasemi/blink/fragments/VideoLiveViewFragment:mStopButton	Landroid/widget/Button;
    //   150: aload_0
    //   151: getfield 489	com/immediasemi/blink/fragments/VideoLiveViewFragment:mStopButton	Landroid/widget/Button;
    //   154: new 14	com/immediasemi/blink/fragments/VideoLiveViewFragment$3
    //   157: dup
    //   158: aload_0
    //   159: invokespecial 627	com/immediasemi/blink/fragments/VideoLiveViewFragment$3:<init>	(Lcom/immediasemi/blink/fragments/VideoLiveViewFragment;)V
    //   162: invokevirtual 625	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   165: aload_0
    //   166: aload_0
    //   167: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   170: ldc_w 628
    //   173: invokevirtual 604	android/view/View:findViewById	(I)Landroid/view/View;
    //   176: checkcast 360	android/widget/Button
    //   179: putfield 347	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMuteButton	Landroid/widget/Button;
    //   182: aload_0
    //   183: getfield 347	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMuteButton	Landroid/widget/Button;
    //   186: new 16	com/immediasemi/blink/fragments/VideoLiveViewFragment$4
    //   189: dup
    //   190: aload_0
    //   191: invokespecial 629	com/immediasemi/blink/fragments/VideoLiveViewFragment$4:<init>	(Lcom/immediasemi/blink/fragments/VideoLiveViewFragment;)V
    //   194: invokevirtual 625	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   197: aload_0
    //   198: getfield 347	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMuteButton	Landroid/widget/Button;
    //   201: iconst_4
    //   202: invokevirtual 376	android/widget/Button:setVisibility	(I)V
    //   205: aload_0
    //   206: getfield 93	com/immediasemi/blink/fragments/VideoLiveViewFragment:handler	Landroid/os/Handler;
    //   209: iconst_2
    //   210: aload_0
    //   211: invokevirtual 232	android/os/Handler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
    //   214: astore_1
    //   215: aload_0
    //   216: getfield 93	com/immediasemi/blink/fragments/VideoLiveViewFragment:handler	Landroid/os/Handler;
    //   219: aload_1
    //   220: ldc2_w 233
    //   223: invokevirtual 238	android/os/Handler:sendMessageDelayed	(Landroid/os/Message;J)Z
    //   226: pop
    //   227: aload_0
    //   228: invokespecial 436	com/immediasemi/blink/fragments/VideoLiveViewFragment:setMuteButtonBackground	()V
    //   231: aload_0
    //   232: invokespecial 99	com/immediasemi/blink/fragments/VideoLiveViewFragment:toggleTools	()V
    //   235: new 631	java/io/File
    //   238: dup
    //   239: aload_0
    //   240: invokevirtual 634	com/immediasemi/blink/fragments/VideoLiveViewFragment:getContext	()Landroid/content/Context;
    //   243: invokevirtual 640	android/content/Context:getCacheDir	()Ljava/io/File;
    //   246: ldc_w 642
    //   249: invokespecial 645	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   252: astore 4
    //   254: aconst_null
    //   255: astore_3
    //   256: aconst_null
    //   257: astore_2
    //   258: new 647	java/io/FileInputStream
    //   261: astore_1
    //   262: aload_1
    //   263: aload 4
    //   265: invokespecial 650	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   268: aload 4
    //   270: invokevirtual 653	java/io/File:exists	()Z
    //   273: ifeq +41 -> 314
    //   276: aload_0
    //   277: aload 4
    //   279: invokevirtual 656	java/io/File:getPath	()Ljava/lang/String;
    //   282: invokestatic 662	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   285: invokevirtual 666	com/immediasemi/blink/fragments/VideoLiveViewFragment:toGrayscale	(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    //   288: astore_2
    //   289: aload_0
    //   290: aload_0
    //   291: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   294: ldc_w 667
    //   297: invokevirtual 604	android/view/View:findViewById	(I)Landroid/view/View;
    //   300: checkcast 164	android/widget/ImageView
    //   303: putfield 151	com/immediasemi/blink/fragments/VideoLiveViewFragment:thumbnailView	Landroid/widget/ImageView;
    //   306: aload_0
    //   307: getfield 151	com/immediasemi/blink/fragments/VideoLiveViewFragment:thumbnailView	Landroid/widget/ImageView;
    //   310: aload_2
    //   311: invokevirtual 671	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   314: aload_1
    //   315: ifnull +7 -> 322
    //   318: aload_1
    //   319: invokevirtual 674	java/io/FileInputStream:close	()V
    //   322: aload_0
    //   323: invokespecial 676	com/immediasemi/blink/fragments/VideoLiveViewFragment:setupRTSPPlayerView	()V
    //   326: aload_0
    //   327: getfield 453	com/immediasemi/blink/fragments/VideoLiveViewFragment:mMainView	Landroid/view/View;
    //   330: areturn
    //   331: astore_1
    //   332: aload_1
    //   333: invokevirtual 679	java/io/IOException:printStackTrace	()V
    //   336: goto -14 -> 322
    //   339: astore_1
    //   340: aload_2
    //   341: astore_1
    //   342: aload_1
    //   343: ifnull -21 -> 322
    //   346: aload_1
    //   347: invokevirtual 674	java/io/FileInputStream:close	()V
    //   350: goto -28 -> 322
    //   353: astore_1
    //   354: aload_1
    //   355: invokevirtual 679	java/io/IOException:printStackTrace	()V
    //   358: goto -36 -> 322
    //   361: astore_2
    //   362: aload_3
    //   363: astore_1
    //   364: aload_1
    //   365: ifnull +7 -> 372
    //   368: aload_1
    //   369: invokevirtual 674	java/io/FileInputStream:close	()V
    //   372: aload_2
    //   373: athrow
    //   374: astore_1
    //   375: aload_1
    //   376: invokevirtual 679	java/io/IOException:printStackTrace	()V
    //   379: goto -7 -> 372
    //   382: astore_2
    //   383: goto -19 -> 364
    //   386: astore_2
    //   387: goto -45 -> 342
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	390	0	this	VideoLiveViewFragment
    //   0	390	1	paramLayoutInflater	android.view.LayoutInflater
    //   0	390	2	paramViewGroup	ViewGroup
    //   0	390	3	paramBundle	Bundle
    //   252	26	4	localFile	java.io.File
    // Exception table:
    //   from	to	target	type
    //   318	322	331	java/io/IOException
    //   258	268	339	java/io/IOException
    //   346	350	353	java/io/IOException
    //   258	268	361	finally
    //   368	372	374	java/io/IOException
    //   268	314	382	finally
    //   268	314	386	java/io/IOException
  }
  
  public void onPause()
  {
    this.handler.removeCallbacks(this.mHideTools);
    stopVideo();
    super.onPause();
  }
  
  public void onStop()
  {
    teardownRTSPPlayer();
    super.onStop();
  }
  
  public void runNotificationReceived(Object paramObject, String paramString)
  {
    if (paramString.equals(RTSPPlayerView.IS_READY_FOR_DISPLAY_NOTIFICATION))
    {
      this.mSpinner.hide();
      if ((this.thumbnailView != null) && (this.thumbnailView.getVisibility() == 0)) {
        fadeThumbnail();
      }
      if (this.mLiveViewDurationTime == 0L)
      {
        this.mLiveViewDurationTime = (System.currentTimeMillis() / 1000L + 30L);
        this.mShouldContinueCountdown = true;
        updateTimeRemaining();
      }
    }
    if ((paramString.equals(RTSPPlayer.HAS_STOPPED_NOTIFICATION)) && (getActivity() != null)) {
      getActivity().finish();
    }
  }
  
  public void setmDebugOverlay(boolean paramBoolean)
  {
    if ((BlinkApp.getApp().isDebug()) && (paramBoolean)) {}
    for (boolean bool = true;; bool = false)
    {
      if (this.mRTSPPlayerView != null) {
        this.mRTSPPlayerView.setDebugEnabled(bool);
      }
      if ((this.thumbnailView != null) && (bool)) {
        this.thumbnailView.setVisibility(4);
      }
      this.mDebugOverlay = paramBoolean;
      return;
    }
  }
  
  public void stopVideo()
  {
    if (this.mStopCalled) {}
    for (;;)
    {
      return;
      BlinkAPI.BlinkAPIRequest(null, null, new CommandDoneRequest(), new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError) {}
        
        public void onResult(BlinkData paramAnonymousBlinkData) {}
      }, false);
      liveViewDone();
    }
  }
  
  public Bitmap toGrayscale(Bitmap paramBitmap)
  {
    int i = paramBitmap.getHeight();
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), i, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    ColorMatrix localColorMatrix = new ColorMatrix();
    localColorMatrix.setSaturation(0.0F);
    localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    return localBitmap;
  }
  
  static class VideoHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      ((VideoLiveViewFragment)paramMessage.obj).handleMessage(paramMessage);
      super.handleMessage(paramMessage);
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/VideoLiveViewFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */