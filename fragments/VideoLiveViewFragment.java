package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Cameras.EnableLiveViewRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.LiveVideoResponse;
import com.immediasemi.blink.utils.OnClick;
import com.immediasemi.rtsp.RTSPNotificationReceiver;
import com.immediasemi.rtsp.RTSPPlayer;
import com.immediasemi.rtsp.RTSPPlayerView;
import java.net.URI;
import java.net.URISyntaxException;

public class VideoLiveViewFragment
  extends BaseFragment
  implements RTSPNotificationReceiver
{
  private static final int GET_URL = 2;
  private static final int PLAY_VIDEO = 1;
  public static final String PREF_LIVE_VIEW_MUTED = "live_view_muted";
  private Handler handler = new VideoHandler();
  private TextView mBetaWarning;
  private RelativeLayout mButtonBar;
  private FrameLayout mContainer;
  private Button mContinueButton;
  private Runnable mHideTools;
  private FrameLayout mLiveViewBackground;
  private long mLiveViewDurationTime;
  private long mLiveViewEndTime;
  private View mMainView;
  private Button mMuteButton;
  private RTSPPlayer mRTSPPlayer;
  private RTSPPlayerView mRTSPPlayerView;
  private int mSectionNumber;
  private boolean mShouldContinueCountdown;
  private ContentLoadingProgressBar mSpinner;
  private Button mStopButton;
  private boolean mStopCalled = false;
  private boolean mToolsVisible = false;
  private String mVideoUrl;
  
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
        new AlertDialog.Builder(VideoLiveViewFragment.this.getActivity()).setTitle("Error").setMessage("Could not start live view").setPositiveButton(2131099886, null).setOnDismissListener(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymous2DialogInterface)
          {
            if (VideoLiveViewFragment.this.getActivity() != null) {
              VideoLiveViewFragment.this.getActivity().finish();
            }
          }
        }).create().show();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((VideoLiveViewFragment.this.getActivity() == null) || (VideoLiveViewFragment.this.mListener == null) || (VideoLiveViewFragment.this.isDetached())) {
          return;
        }
        VideoLiveViewFragment.access$702(VideoLiveViewFragment.this, ((LiveVideoResponse)paramAnonymousBlinkData).getServer());
        VideoLiveViewFragment.access$802(VideoLiveViewFragment.this, System.currentTimeMillis() / 1000L + Integer.parseInt(((LiveVideoResponse)paramAnonymousBlinkData).getDuration()));
        paramAnonymousBlinkData = VideoLiveViewFragment.this.handler.obtainMessage(1, VideoLiveViewFragment.this);
        VideoLiveViewFragment.this.handler.sendMessage(paramAnonymousBlinkData);
      }
    }, false);
  }
  
  private void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default: 
      return;
    case 2: 
      getUrl();
      return;
    }
    if (this.mVideoUrl != null)
    {
      playVideo();
      return;
    }
    paramMessage = this.handler.obtainMessage(1, this);
    this.handler.sendMessageDelayed(paramMessage, 100L);
  }
  
  private boolean isMuted()
  {
    return PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).getBoolean("live_view_muted", true);
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
    this.mRTSPPlayer = new RTSPPlayer(null);
    this.mRTSPPlayer.registerForNotifications(this);
    this.mRTSPPlayer.setMuted(isMuted());
    this.mRTSPPlayerView = new RTSPPlayerView(BlinkApp.getApp());
    this.mRTSPPlayerView.setDebugEnabled(false);
    this.mRTSPPlayerView.setPlayer(this.mRTSPPlayer);
    this.mRTSPPlayer.setPlayerView(this.mRTSPPlayerView);
    this.mContainer.addView(this.mRTSPPlayerView);
    this.mRTSPPlayerView.registerForNotifications(this);
    try
    {
      this.mRTSPPlayer.setURI(new URI(this.mVideoUrl));
      this.mRTSPPlayer.play();
      return;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      Log.e("Blink LiveView", localURISyntaxException.getMessage());
    }
  }
  
  private void setMuteButtonBackground()
  {
    if (isMuted())
    {
      this.mMuteButton.setBackground(ResourcesCompat.getDrawable(getResources(), 2130837616, null));
      return;
    }
    this.mMuteButton.setBackground(ResourcesCompat.getDrawable(getResources(), 2130837615, null));
  }
  
  private void teardownRTSPPlayer()
  {
    if (this.mRTSPPlayer != null)
    {
      this.mRTSPPlayer.unregisterForNotifications(this);
      this.mRTSPPlayer.dispose();
      this.mRTSPPlayer = null;
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
    if (getResources().getConfiguration().orientation == 2)
    {
      this.mMainView.setSystemUiVisibility(4);
      this.mLiveViewBackground.setBackgroundColor(-16777216);
      this.mButtonBar.setBackgroundResource(2131492876);
      this.mContinueButton.setBackgroundResource(2130837622);
      this.mContinueButton.setTextColor(-1);
      this.mStopButton.setVisibility(4);
      if (this.mToolsVisible)
      {
        this.mToolsVisible = false;
        this.mButtonBar.setVisibility(4);
        return;
      }
      this.mToolsVisible = true;
      this.mButtonBar.setVisibility(0);
      this.mButtonBar.bringToFront();
      this.mHideTools = new Runnable()
      {
        public void run()
        {
          VideoLiveViewFragment.this.mButtonBar.setVisibility(4);
          VideoLiveViewFragment.access$502(VideoLiveViewFragment.this, false);
        }
      };
      this.handler.postDelayed(this.mHideTools, 4000L);
      return;
    }
    this.mMainView.setSystemUiVisibility(0);
    this.handler.removeCallbacks(this.mHideTools);
    this.mLiveViewBackground.setBackgroundColor(-1);
    this.mButtonBar.setBackgroundResource(2131492875);
    this.mContinueButton.setBackgroundResource(2130837621);
    this.mContinueButton.setTextColor(-16777216);
    this.mStopButton.setVisibility(0);
    this.mToolsVisible = false;
    this.mButtonBar.setVisibility(0);
    this.mButtonBar.bringToFront();
  }
  
  private void updateTimeRemaining()
  {
    if ((!this.mShouldContinueCountdown) || (this.mRTSPPlayer == null) || (this.mRTSPPlayerView == null)) {
      return;
    }
    long l2 = System.currentTimeMillis() / 1000L;
    long l1 = this.mLiveViewDurationTime - l2;
    l2 = this.mLiveViewEndTime - l2;
    if ((l1 <= 0L) || (l2 <= 0L))
    {
      stopVideo();
      return;
    }
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
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099986));
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        VideoLiveViewFragment.this.toggleTools();
      }
    }, 100L);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mMainView = paramLayoutInflater.inflate(2130903102, paramViewGroup, false);
    this.mMainView.setSystemUiVisibility(4);
    this.mMainView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (paramAnonymousMotionEvent.getActionMasked() == 1) {
          VideoLiveViewFragment.this.toggleTools();
        }
        return true;
      }
    });
    ((TextView)this.mMainView.findViewById(2131558642)).setText(BlinkApp.getApp().getLastCameraName());
    this.mLiveViewBackground = ((FrameLayout)this.mMainView.findViewById(2131558636));
    this.mContainer = ((FrameLayout)this.mMainView.findViewById(2131558637));
    this.mSpinner = ((ContentLoadingProgressBar)this.mMainView.findViewById(2131558638));
    this.mButtonBar = ((RelativeLayout)this.mMainView.findViewById(2131558640));
    this.mBetaWarning = ((TextView)this.mMainView.findViewById(2131558639));
    this.mContinueButton = ((Button)this.mMainView.findViewById(2131558547));
    this.mContinueButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        VideoLiveViewFragment.access$114(VideoLiveViewFragment.this, 30L);
        VideoLiveViewFragment.this.mContinueButton.setVisibility(4);
      }
    });
    this.mStopButton = ((Button)this.mMainView.findViewById(2131558641));
    this.mStopButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        VideoLiveViewFragment.this.stopVideo();
      }
    });
    this.mMuteButton = ((Button)this.mMainView.findViewById(2131558643));
    this.mMuteButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        VideoLiveViewFragment.this.toggleMute();
      }
    });
    paramLayoutInflater = this.handler.obtainMessage(2, this);
    this.handler.sendMessageDelayed(paramLayoutInflater, 100L);
    setMuteButtonBackground();
    toggleTools();
    return this.mMainView;
  }
  
  public void onPause()
  {
    if (this.mRTSPPlayerView != null) {
      this.mRTSPPlayerView.onPause();
    }
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.mRTSPPlayerView != null) {
      this.mRTSPPlayerView.onResume();
    }
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
      this.mBetaWarning.setVisibility(4);
      if (this.mLiveViewDurationTime == 0L)
      {
        this.mLiveViewDurationTime = (System.currentTimeMillis() / 1000L + 30L);
        this.mShouldContinueCountdown = true;
        updateTimeRemaining();
      }
    }
    if (paramString.equals(RTSPPlayer.HAS_STOPPED_NOTIFICATION))
    {
      teardownRTSPPlayer();
      if (getActivity() != null) {
        getActivity().finish();
      }
    }
  }
  
  public void stopVideo()
  {
    this.mShouldContinueCountdown = false;
    if (this.mStopCalled) {}
    do
    {
      return;
      if (this.handler.hasMessages(1)) {
        this.handler.removeMessages(1);
      }
    } while (this.mRTSPPlayer == null);
    this.mStopCalled = true;
    this.mRTSPPlayer.stop();
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


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/VideoLiveViewFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */