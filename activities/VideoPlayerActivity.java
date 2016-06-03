package com.immediasemi.blink.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.fragments.BaseFragment.OnFragmentInteractionListener.InteractionAction;
import com.immediasemi.blink.fragments.VideoPlayerFragment;
import com.immediasemi.blink.models.Video;
import com.immediasemi.blink.utils.BlinkClipArray;
import com.immediasemi.blink.utils.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VideoPlayerActivity
  extends BaseActivity
{
  public static final String ARG_CAMERA_DATES = "camera_dates";
  public static final String ARG_CAMERA_NAME = "camera_name";
  public static final String ARG_CAMERA_VIDEO = "camera_video";
  public static final String ARG_CURRENT_SELECTION = "current_selection";
  public static final String ARG_START_ALERT_VIEW = "start_alert_view";
  public static final String ARG_VIDEO_DICT = "video_dictionary";
  public static final String ARG_VIDEO_LIST = "video_list";
  private static final int DELAYED_LAUNCH = 0;
  private static final int HIDE_ACTIONBAR = 1;
  private static final long HIDE_ACTIONBAR_DELAY = 2000L;
  private static final long LAUNCH_DELAY = 300L;
  public static final String VIDEO_DICT_ENTRY_CAMERA = "camera";
  public static final String VIDEO_DICT_ENTRY_CAMERA_NAME = "camera_name";
  public static final String VIDEO_DICT_ENTRY_CREATED_DATE = "created_at";
  public static final String VIDEO_DICT_ENTRY_NETWORK = "network";
  public static final String VIDEO_DICT_ENTRY_THUMBNAIL = "thumbnail";
  public static final String VIDEO_DICT_ENTRY_VIDEO = "video";
  private BroadcastReceiver backbuttonReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (VideoPlayerActivity.this.getApplicationContext() == null) {}
      for (;;)
      {
        return;
        VideoPlayerActivity.this.onBackButtonTapped();
      }
    }
  };
  private boolean firstClip = true;
  private ThisHandler handler;
  private boolean hasNext;
  private boolean hasPrevious;
  private View mActionBarView;
  private String mClipName;
  private int mCurrentSelection;
  private boolean mDeeplinked = false;
  private boolean mIsLandscape = false;
  private int mNumberOfClipsDeleted = 0;
  private Bundle mVideoDict;
  private BlinkClipArray mVideos;
  
  private void delayedLaunchPlayerFragment()
  {
    int i = 0;
    Object localObject1;
    Video localVideo;
    if (this.mDeeplinked)
    {
      this.mClipName = this.mVideoDict.getString("camera_name");
      localObject1 = this.mVideoDict.getString("created_at");
      localVideo = new Video();
      localVideo.setAddress(this.mVideoDict.getString("thumbnail"));
      localVideo.setCamera_id(Integer.valueOf(this.mVideoDict.getString("camera")).intValue());
      localVideo.setCamera_name(this.mClipName);
      localVideo.setCreated_at((String)localObject1);
      localVideo.setNetwork_id(Integer.valueOf(this.mVideoDict.getString("network")).intValue());
      localVideo.setEvent_id(Integer.valueOf(this.mVideoDict.getString("video")).intValue());
      BlinkApp.getApp().setLastCameraId(this.mVideoDict.getString("camera"));
      BlinkApp.getApp().setLastVideoId(this.mVideoDict.getString("video"));
      localObject1 = Util.reformatDate(Util.getLocalDateYearTime((String)localObject1)).split("%");
      localObject2 = getSupportFragmentManager();
      if (!this.firstClip) {
        break label261;
      }
      this.firstClip = false;
      localObject2 = ((FragmentManager)localObject2).beginTransaction();
      if (!this.mDeeplinked) {
        break label256;
      }
      label198:
      ((FragmentTransaction)localObject2).replace(2131558537, VideoPlayerFragment.newInstance(i, localVideo, this.mClipName, (String[])localObject1)).commit();
    }
    label256:
    label261:
    do
    {
      return;
      localVideo = getListRowItem(this.mCurrentSelection);
      if (localVideo != null)
      {
        this.mClipName = localVideo.getCamera_name();
        localObject1 = localVideo.getCreated_at();
        break;
      }
      localObject1 = Util.getTodayFormatted();
      break;
      i = -1;
      break label198;
      localObject2 = ((FragmentManager)localObject2).getFragments();
    } while (localObject2 == null);
    Object localObject2 = ((List)localObject2).iterator();
    label282:
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (Fragment)((Iterator)localObject2).next();
      if (localObject3.getClass().equals(VideoPlayerFragment.class))
      {
        localObject3 = (VideoPlayerFragment)localObject3;
        if (!this.mDeeplinked) {
          break label348;
        }
      }
    }
    label348:
    for (i = 0;; i = -1)
    {
      ((VideoPlayerFragment)localObject3).playClip(i, localVideo, this.mClipName, (String[])localObject1);
      break label282;
      break;
    }
  }
  
  private void deleteCurrent()
  {
    if (this.mVideos == null) {}
    for (;;)
    {
      return;
      int i = Integer.valueOf(BlinkApp.getApp().getLastVideoId()).intValue();
      if (i != 0)
      {
        if (i > 0)
        {
          this.mNumberOfClipsDeleted += 1;
          playPrevious();
        }
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(Integer.valueOf(i));
        this.mVideos.deleteVideoIDs(localArrayList);
      }
    }
  }
  
  private void dispatchMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default: 
      ;;
    }
    for (;;)
    {
      delayedLaunchPlayerFragment();
      continue;
      if (this.mIsLandscape)
      {
        paramMessage = getSupportFragmentManager();
        if (paramMessage != null)
        {
          paramMessage = paramMessage.getFragments();
          if (paramMessage != null)
          {
            paramMessage = paramMessage.iterator();
            if (paramMessage.hasNext())
            {
              Fragment localFragment = (Fragment)paramMessage.next();
              if (localFragment != null)
              {
                if (!localFragment.getClass().equals(VideoPlayerFragment.class)) {
                  break;
                }
                hideBars();
              }
            }
          }
        }
      }
    }
  }
  
  private Video getListRowItem(int paramInt)
  {
    if (this.mVideos == null) {}
    for (Video localVideo = null;; localVideo = this.mVideos.objectAtIndex(paramInt)) {
      return localVideo;
    }
  }
  
  private void hideBars()
  {
    Object localObject = findViewById(2131558685);
    if (localObject != null) {
      ((View)localObject).setVisibility(8);
    }
    if (this.mActionBarView != null) {
      this.mActionBarView.setVisibility(4);
    }
    localObject = getSupportFragmentManager();
    if (localObject == null) {}
    for (;;)
    {
      return;
      localObject = ((FragmentManager)localObject).getFragments();
      if (localObject != null)
      {
        Iterator localIterator = ((List)localObject).iterator();
        while (localIterator.hasNext())
        {
          localObject = (Fragment)localIterator.next();
          if ((localObject != null) && (localObject.getClass().equals(VideoPlayerFragment.class))) {
            ((VideoPlayerFragment)localObject).setMediaControllerIsHidden(true);
          }
        }
      }
    }
  }
  
  private void launchPlayerFragment()
  {
    Message localMessage = this.handler.obtainMessage(0, this);
    this.handler.sendMessageDelayed(localMessage, 300L);
  }
  
  private void onBackButtonTapped()
  {
    setResult(this.mNumberOfClipsDeleted);
    View localView = findViewById(2131558541);
    if (localView != null) {
      localView.setVisibility(4);
    }
    super.onBackPressed();
  }
  
  private void playNext()
  {
    Video localVideo;
    if ((this.mVideos != null) && (this.mCurrentSelection < this.mVideos.count() - 1))
    {
      this.mCurrentSelection += 1;
      this.mVideos.setCurrentIndex(this.mCurrentSelection);
      localVideo = this.mVideos.objectAtIndex(this.mCurrentSelection);
      if (localVideo != null) {}
    }
    else
    {
      return;
    }
    localVideo.setViewed(Util.getTodayFormatted());
    launchPlayerFragment();
    if (this.mCurrentSelection != this.mVideos.count() - 1) {}
    for (boolean bool = true;; bool = false)
    {
      this.hasNext = bool;
      updateMediaControllerButtons();
      break;
    }
  }
  
  private void playPrevious()
  {
    Video localVideo;
    if ((this.mCurrentSelection > 0) && (this.mVideos != null))
    {
      this.mCurrentSelection -= 1;
      this.mVideos.setCurrentIndex(this.mCurrentSelection);
      localVideo = this.mVideos.objectAtIndex(this.mCurrentSelection);
      if (localVideo != null) {}
    }
    else
    {
      return;
    }
    localVideo.setViewed(Util.getTodayFormatted());
    launchPlayerFragment();
    if (this.mCurrentSelection != 0) {}
    for (boolean bool = true;; bool = false)
    {
      this.hasPrevious = bool;
      updateMediaControllerButtons();
      break;
    }
  }
  
  private void showBars()
  {
    Object localObject = findViewById(2131558685);
    if (localObject != null) {
      ((View)localObject).setVisibility(8);
    }
    if (this.mActionBarView != null)
    {
      this.mActionBarView.setVisibility(0);
      if (!this.mIsLandscape) {
        break label118;
      }
      this.mActionBarView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), 2131492878));
    }
    for (;;)
    {
      localObject = getSupportFragmentManager().getFragments();
      if (localObject == null) {
        break;
      }
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        localObject = (Fragment)localIterator.next();
        if (localObject.getClass().equals(VideoPlayerFragment.class)) {
          ((VideoPlayerFragment)localObject).setMediaControllerIsHidden(false);
        }
      }
      label118:
      this.mActionBarView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), 2131492875));
    }
  }
  
  private void updateMediaControllerButtons()
  {
    Object localObject = getSupportFragmentManager().getFragments();
    if (localObject != null)
    {
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        localObject = (Fragment)localIterator.next();
        if (localObject.getClass().equals(VideoPlayerFragment.class)) {
          ((VideoPlayerFragment)localObject).setMediaPrevNextButtons(this.hasPrevious, this.hasNext);
        }
      }
    }
  }
  
  public void initActionBar(View paramView)
  {
    this.mActionBarView = paramView.findViewById(2131558688);
    ((TextView)this.mActionBarView.findViewById(2131558689)).setText(this.mClipName);
  }
  
  public boolean isPortrait()
  {
    if (!this.mIsLandscape) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onBackPressed()
  {
    onBackButtonTapped();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (paramConfiguration.orientation == 2)
    {
      this.mIsLandscape = true;
      paramConfiguration = this.handler.obtainMessage(1, this);
      this.handler.sendMessageDelayed(paramConfiguration, 2000L);
    }
    for (;;)
    {
      showBars();
      return;
      if (paramConfiguration.orientation == 1) {
        this.mIsLandscape = false;
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903074);
    this.handler = new ThisHandler();
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null)
    {
      localActionBar.setDisplayHomeAsUpEnabled(false);
      localActionBar.hide();
    }
    if (paramBundle == null)
    {
      paramBundle = getIntent().getExtras();
      if (paramBundle.getBoolean("start_alert_view", false))
      {
        this.mDeeplinked = true;
        this.mVideoDict = paramBundle.getBundle("video_dictionary");
        launchPlayerFragment();
      }
    }
    for (;;)
    {
      return;
      this.mVideos = BlinkClipArray.instance();
      this.mCurrentSelection = paramBundle.getInt("current_selection");
      break;
      this.mCurrentSelection = paramBundle.getInt("current_selection");
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
  
  public void onFragmentInteraction(int paramInt, BaseFragment.OnFragmentInteractionListener.InteractionAction paramInteractionAction, Object paramObject)
  {
    switch (paramInteractionAction)
    {
    default: 
      super.onFragmentInteraction(paramInt, paramInteractionAction, paramObject);
    }
    for (;;)
    {
      return;
      if (!this.mDeeplinked)
      {
        playNext();
        continue;
        if (!this.mDeeplinked)
        {
          playPrevious();
          continue;
          if (this.mDeeplinked)
          {
            this.mVideos = BlinkClipArray.instance();
            deleteCurrent();
            finish();
          }
          else
          {
            deleteCurrent();
          }
        }
      }
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(this.backbuttonReceiver);
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    this.mCurrentSelection = paramBundle.getInt("current_selection");
  }
  
  protected void onResume()
  {
    super.onResume();
    LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.backbuttonReceiver, new IntentFilter("media_backbutton_notification"));
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("current_selection", this.mCurrentSelection);
  }
  
  public void onStop()
  {
    this.handler.removeMessages(1);
    this.handler.removeMessages(0);
    this.mVideos = null;
    super.onStop();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool;
    Object localObject;
    if ((((WindowManager)getSystemService("window")).getDefaultDisplay().getRotation() & 0x1) != 0)
    {
      bool = true;
      this.mIsLandscape = bool;
      if (!this.mIsLandscape) {
        break label74;
      }
      showBars();
      localObject = this.handler.obtainMessage(1, this);
      this.handler.sendMessageDelayed((Message)localObject, 4000L);
    }
    for (;;)
    {
      return super.onTouchEvent(paramMotionEvent);
      bool = false;
      break;
      label74:
      localObject = findViewById(2131558685);
      if (localObject != null)
      {
        ((View)localObject).setVisibility(0);
        ((View)localObject).bringToFront();
      }
    }
  }
  
  static class ThisHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.obj != null) {
        ((VideoPlayerActivity)paramMessage.obj).dispatchMessage(paramMessage);
      }
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/VideoPlayerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */