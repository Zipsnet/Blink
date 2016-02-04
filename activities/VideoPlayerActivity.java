package com.immediasemi.blink.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.fragments.BaseFragment.OnFragmentInteractionListener.InteractionAction;
import com.immediasemi.blink.fragments.VideoPlayerFragment;
import com.immediasemi.blink.models.Video;
import com.immediasemi.blink.utils.BlinkClipArray;
import com.immediasemi.blink.utils.OnClick;
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
  private boolean firstClip = true;
  private ThisHandler handler;
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
      localObject1 = Util.reformatDate(Util.getLocalDateYearTime((String)localObject1)).split("%");
      localObject2 = getSupportFragmentManager();
      if (!this.firstClip) {
        break label219;
      }
      this.firstClip = false;
      localObject2 = ((FragmentManager)localObject2).beginTransaction();
      if (!this.mDeeplinked) {
        break label213;
      }
      label166:
      ((FragmentTransaction)localObject2).replace(2131558534, VideoPlayerFragment.newInstance(i, localVideo, this.mClipName, (String[])localObject1)).commit();
    }
    label213:
    label219:
    do
    {
      return;
      localVideo = getListRowItem(this.mCurrentSelection);
      this.mClipName = localVideo.getCamera_name();
      localObject1 = localVideo.getCreated_at();
      break;
      i = -1;
      break label166;
      localObject2 = ((FragmentManager)localObject2).getFragments();
    } while (localObject2 == null);
    Object localObject2 = ((List)localObject2).iterator();
    label235:
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (Fragment)((Iterator)localObject2).next();
      if (localObject3.getClass().equals(VideoPlayerFragment.class))
      {
        localObject3 = (VideoPlayerFragment)localObject3;
        if (!this.mDeeplinked) {
          break label301;
        }
      }
    }
    label301:
    for (i = 0;; i = -1)
    {
      ((VideoPlayerFragment)localObject3).playClip(i, localVideo, this.mClipName, (String[])localObject1);
      break label235;
      break;
    }
  }
  
  private void deleteCurrent()
  {
    int i = Integer.valueOf(BlinkApp.getApp().getLastVideoId()).intValue();
    if (i > 0)
    {
      this.mNumberOfClipsDeleted += 1;
      playPrevious();
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(i));
    this.mVideos.deleteVideoIDs(localArrayList);
  }
  
  private void dispatchMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    }
    do
    {
      return;
      delayedLaunchPlayerFragment();
      return;
    } while (!this.mIsLandscape);
    paramMessage = getSupportFragmentManager().getFragments();
    if (paramMessage != null)
    {
      paramMessage = paramMessage.iterator();
      while (paramMessage.hasNext()) {
        if (((Fragment)paramMessage.next()).getClass().equals(VideoPlayerFragment.class))
        {
          hideBars();
          return;
        }
      }
    }
    paramMessage = this.handler.obtainMessage(1, this);
    this.handler.sendMessageDelayed(paramMessage, 2000L);
  }
  
  private void endOfListDialog()
  {
    new AlertDialog.Builder(this).setTitle(getString(2131099745)).setMessage("").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        VideoPlayerActivity.this.finish();
      }
    }).create().show();
  }
  
  private Video getListRowItem(int paramInt)
  {
    return this.mVideos.objectAtIndex(paramInt);
  }
  
  private void hideBars()
  {
    findViewById(2131558675).setVisibility(8);
    this.mActionBarView.setVisibility(4);
    Object localObject = getSupportFragmentManager().getFragments();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Fragment localFragment = (Fragment)((Iterator)localObject).next();
        if (localFragment.getClass().equals(VideoPlayerFragment.class)) {
          ((VideoPlayerFragment)localFragment).setMediaControllerIsHidden(true);
        }
      }
    }
  }
  
  private void launchPlayerFragment()
  {
    Message localMessage = this.handler.obtainMessage(0, this);
    this.handler.sendMessageDelayed(localMessage, 300L);
  }
  
  private void onDoneClicked(View paramView)
  {
    setResult(this.mNumberOfClipsDeleted);
    paramView = findViewById(2131558538);
    if (paramView != null) {
      paramView.setVisibility(4);
    }
    super.onBackPressed();
  }
  
  private void playNext()
  {
    if (this.mCurrentSelection < this.mVideos.count() - 1)
    {
      this.mCurrentSelection += 1;
      this.mVideos.setCurrentIndex(this.mCurrentSelection);
      this.mVideos.objectAtIndex(this.mCurrentSelection).setViewed(Util.getTodayFormatted());
      launchPlayerFragment();
      return;
    }
    endOfListDialog();
  }
  
  private void playPrevious()
  {
    if (this.mCurrentSelection > 0)
    {
      this.mCurrentSelection -= 1;
      this.mVideos.setCurrentIndex(this.mCurrentSelection);
      this.mVideos.objectAtIndex(this.mCurrentSelection).setViewed(Util.getTodayFormatted());
      launchPlayerFragment();
      return;
    }
    endOfListDialog();
  }
  
  private void showBars()
  {
    findViewById(2131558675).setVisibility(8);
    this.mActionBarView.setVisibility(0);
    Object localObject = getSupportFragmentManager().getFragments();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Fragment localFragment = (Fragment)((Iterator)localObject).next();
        if (localFragment.getClass().equals(VideoPlayerFragment.class)) {
          ((VideoPlayerFragment)localFragment).setMediaControllerIsHidden(false);
        }
      }
    }
  }
  
  private void showHideDoneButton()
  {
    if (this.mIsLandscape)
    {
      this.mActionBarView.findViewById(2131558679).setVisibility(4);
      this.mActionBarView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), 2131492877));
      return;
    }
    this.mActionBarView.findViewById(2131558679).setVisibility(0);
    this.mActionBarView.setBackgroundColor(ContextCompat.getColor(getBaseContext(), 2131492875));
  }
  
  public void initActionBar(View paramView)
  {
    this.mActionBarView = paramView.findViewById(2131558678);
    ((TextView)this.mActionBarView.findViewById(2131558680)).setText(this.mClipName);
    this.mActionBarView.findViewById(2131558679).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        VideoPlayerActivity.this.onDoneClicked(paramAnonymousView);
      }
    });
  }
  
  public boolean isPortrait()
  {
    return !this.mIsLandscape;
  }
  
  public void onBackPressed()
  {
    onDoneClicked(null);
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
      showHideDoneButton();
      return;
      if (paramConfiguration.orientation == 1) {
        this.mIsLandscape = false;
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903071);
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
      }
      for (;;)
      {
        launchPlayerFragment();
        return;
        this.mVideos = BlinkClipArray.instance();
        this.mCurrentSelection = paramBundle.getInt("current_selection");
      }
    }
    this.mCurrentSelection = paramBundle.getInt("current_selection");
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
    case ???: 
    case ???: 
      do
      {
        do
        {
          return;
        } while (this.mDeeplinked);
        playNext();
        return;
      } while (this.mDeeplinked);
      playPrevious();
      return;
    }
    if (this.mDeeplinked)
    {
      this.mVideos = BlinkClipArray.instance();
      deleteCurrent();
      finish();
      return;
    }
    deleteCurrent();
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    this.mCurrentSelection = paramBundle.getInt("current_selection");
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("current_selection", this.mCurrentSelection);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool;
    if ((((WindowManager)getSystemService("window")).getDefaultDisplay().getRotation() & 0x1) != 0)
    {
      bool = true;
      this.mIsLandscape = bool;
      if (!this.mIsLandscape) {
        break label74;
      }
      showBars();
      Message localMessage = this.handler.obtainMessage(1, this);
      this.handler.sendMessageDelayed(localMessage, 4000L);
    }
    for (;;)
    {
      return super.onTouchEvent(paramMotionEvent);
      bool = false;
      break;
      label74:
      findViewById(2131558675).setVisibility(0);
      findViewById(2131558675).bringToFront();
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


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/activities/VideoPlayerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */