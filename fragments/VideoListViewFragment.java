package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.activities.VideoListViewActivity;
import com.immediasemi.blink.activities.VideoPlayerActivity;
import com.immediasemi.blink.models.Video;
import com.immediasemi.blink.models.Videos;
import com.immediasemi.blink.utils.BlinkClipArray;
import com.immediasemi.blink.utils.ImageLoader;
import com.immediasemi.blink.utils.OnClick;
import com.immediasemi.blink.utils.SectionAdapter;
import com.immediasemi.blink.utils.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VideoListViewFragment
  extends BaseFragment
{
  private static final int POLL_FOR_REFRESH = 1;
  private static final int REFRESH_INTERVAL = 20000;
  private int REL_SWIPE_MAX_OFF_PATH;
  private int REL_SWIPE_MIN_DISTANCE;
  private int REL_SWIPE_THRESHOLD_VELOCITY;
  private Button mDeleteButton;
  private List<Integer> mDeleteCheckArray;
  private List<Boolean> mDeleteFlagArray;
  private boolean mDeleteMode;
  private Button mEditButton;
  private boolean mEditMode;
  private View mFragmentView;
  private LayoutInflater mInflater;
  private VideoSectionAdapter mListAdapter;
  private int mListPosition;
  private int mListTop;
  private ListView mListView;
  private Button mMarkAll;
  private boolean mMarkAllHasBeenSelected;
  private boolean mPaused = true;
  private PollHandler mPollHandler = null;
  private View mProgress;
  private int mSectionNumber;
  private int mSelection = -1;
  private int mVideoCount;
  private BlinkClipArray mVideos;
  private BroadcastReceiver receiveDidProcess = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (VideoListViewFragment.this.getActivity() == null) {}
      do
      {
        return;
        VideoListViewFragment.this.mProgress.setVisibility(4);
      } while ((VideoListViewFragment.this.mDeleteMode) || (VideoListViewFragment.this.mEditMode));
      int i = VideoListViewFragment.this.mVideos.count();
      if (i != VideoListViewFragment.this.mVideoCount) {
        VideoListViewFragment.access$1802(VideoListViewFragment.this, i);
      }
      if (VideoListViewFragment.this.mDeleteFlagArray.size() != VideoListViewFragment.this.mVideoCount)
      {
        i = VideoListViewFragment.this.mDeleteFlagArray.size();
        while (i < VideoListViewFragment.this.mVideos.count())
        {
          VideoListViewFragment.this.mDeleteFlagArray.add(0, Boolean.valueOf(false));
          i += 1;
        }
      }
      if (VideoListViewFragment.this.mDeleteCheckArray.size() != VideoListViewFragment.this.mVideoCount)
      {
        i = VideoListViewFragment.this.mDeleteCheckArray.size();
        while (i < VideoListViewFragment.this.mVideos.count())
        {
          VideoListViewFragment.this.mDeleteCheckArray.add(0, Integer.valueOf(0));
          i += 1;
        }
      }
      ((VideoListViewFragment.VideoSectionAdapter)VideoListViewFragment.this.mListView.getAdapter()).notifyDataSetChanged();
      VideoListViewFragment.this.mListView.setSelectionFromTop(VideoListViewFragment.this.mListPosition, VideoListViewFragment.this.mListTop);
      if (VideoListViewFragment.this.mVideos.getClipCount() == 0)
      {
        VideoListViewFragment.this.mFragmentView.findViewById(2131558670).setVisibility(0);
        VideoListViewFragment.this.mProgress.setVisibility(4);
        return;
      }
      VideoListViewFragment.this.mFragmentView.findViewById(2131558670).setVisibility(4);
      if (VideoListViewFragment.this.mVideoCount == 0)
      {
        VideoListViewFragment.this.mProgress.setVisibility(0);
        return;
      }
      VideoListViewFragment.this.mProgress.setVisibility(4);
    }
  };
  private BroadcastReceiver receiveWillProcess = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = 0;
      if (VideoListViewFragment.this.getActivity() == null) {}
      while ((VideoListViewFragment.this.mDeleteMode) || (VideoListViewFragment.this.mEditMode)) {
        return;
      }
      VideoListViewFragment.access$1402(VideoListViewFragment.this, VideoListViewFragment.this.mListView.getFirstVisiblePosition());
      paramAnonymousContext = VideoListViewFragment.this.mListView.getChildAt(0);
      paramAnonymousIntent = VideoListViewFragment.this;
      if (paramAnonymousContext == null) {}
      for (;;)
      {
        VideoListViewFragment.access$1602(paramAnonymousIntent, i);
        return;
        i = paramAnonymousContext.getTop() - VideoListViewFragment.this.mListView.getPaddingTop();
      }
    }
  };
  
  private void cancelDeleteMode()
  {
    this.mEditButton.setText(2131099811);
    this.mDeleteButton.setVisibility(4);
    int i = 0;
    while (i < this.mDeleteFlagArray.size())
    {
      this.mDeleteFlagArray.set(i, Boolean.valueOf(false));
      i += 1;
    }
    i = 0;
    while (i < this.mDeleteCheckArray.size())
    {
      this.mDeleteCheckArray.set(i, Integer.valueOf(0));
      i += 1;
    }
    this.mDeleteMode = false;
    this.mEditMode = false;
    this.mListAdapter.notifyDataSetChanged();
  }
  
  private void deleteAllVideos()
  {
    this.mVideos.deleteAllVideos();
    this.mDeleteFlagArray.clear();
    this.mDeleteCheckArray.clear();
    this.mMarkAllHasBeenSelected = false;
    this.mEditMode = false;
    this.mEditButton.setText(2131099811);
    this.mMarkAll.setVisibility(4);
    this.mDeleteButton.setVisibility(4);
  }
  
  private void deleteLine(int paramInt1, int paramInt2, Video paramVideo)
  {
    BlinkApp.getApp().setLastVideoId(String.valueOf(paramVideo.getId()));
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(paramVideo.getId()));
    this.mVideos.deleteVideoIDs(localArrayList);
    this.mVideoCount -= 1;
    this.mDeleteFlagArray.remove(paramInt2);
    this.mDeleteCheckArray.remove(paramInt2);
    int i = 1;
    paramInt1 = 0;
    for (;;)
    {
      paramInt2 = i;
      if (paramInt1 < this.mDeleteFlagArray.size())
      {
        if (((Boolean)this.mDeleteFlagArray.get(paramInt1)).booleanValue()) {
          paramInt2 = 0;
        }
      }
      else
      {
        if (paramInt2 == 0) {
          break;
        }
        cancelDeleteMode();
        return;
      }
      paramInt1 += 1;
    }
    this.mListAdapter.notifyDataSetChanged();
  }
  
  private Object getListRowItem(int paramInt1, int paramInt2)
  {
    return this.mVideos.objectAtIndex(paramInt2);
  }
  
  private void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default: 
      return;
    }
    BlinkClipArray.instance().refresh();
    paramMessage = this.mPollHandler.obtainMessage(1, this);
    this.mPollHandler.sendMessageDelayed(paramMessage, 20000L);
  }
  
  private void launchVideoPlayer(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != 0) || (paramInt2 < 0) || (paramInt2 > this.mVideos.count())) {
      return;
    }
    Object localObject1 = (Video)getListRowItem(paramInt1, paramInt2);
    Object localObject2 = ((Video)getListRowItem(paramInt1, paramInt2)).getCamera_name();
    String[] arrayOfString = Util.reformatDate(Util.getLocalDateYearTime(((Video)localObject1).getCreated_at())).split("%");
    Intent localIntent = new Intent(getActivity(), VideoPlayerActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("camera_name", (String)localObject2);
    localBundle.putStringArray("camera_dates", arrayOfString);
    localBundle.putSerializable("camera_video", (Serializable)localObject1);
    localBundle.putInt("current_selection", paramInt2);
    BlinkApp.getApp().setLastVideoId(String.valueOf(((Video)localObject1).getId()));
    BlinkApp.getApp().setLastCameraId(String.valueOf(((Video)localObject1).getCamera_id()));
    BlinkApp.getApp().setLastCameraName((String)localObject2);
    BlinkApp.getApp().setLastNetworkId(String.valueOf(((Video)localObject1).getNetwork_id()));
    localObject1 = new Videos();
    localObject2 = new Video[this.mVideos.count()];
    paramInt1 = 0;
    while (paramInt1 < localObject2.length)
    {
      localObject2[paramInt1] = ((Video)getListRowItem(0, paramInt1));
      paramInt1 += 1;
    }
    ((Videos)localObject1).setVideos((Video[])localObject2);
    localBundle.putSerializable("video_list", (Serializable)localObject1);
    localIntent.putExtras(localBundle);
    getActivity().startActivityForResult(localIntent, 1);
    this.mVideos.objectAtIndex(paramInt2).setViewed(Util.getTodayFormatted());
  }
  
  public static VideoListViewFragment newInstance(int paramInt)
  {
    VideoListViewFragment localVideoListViewFragment = new VideoListViewFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localVideoListViewFragment.setArguments(localBundle);
    return localVideoListViewFragment;
  }
  
  private void private_delete_lines()
  {
    ArrayList localArrayList = new ArrayList();
    List localList = this.mVideos.videoIDs();
    int i = this.mDeleteCheckArray.size() - 1;
    while (i >= 0)
    {
      if (((Integer)this.mDeleteCheckArray.get(i)).intValue() > 0)
      {
        removeMarkedFromLists(i);
        localArrayList.add(localList.get(i));
      }
      i -= 1;
    }
    this.mVideos.deleteVideoList(localArrayList);
    this.mEditMode = false;
    this.mEditButton.setText(2131099811);
    this.mMarkAll.setVisibility(4);
    this.mDeleteButton.setVisibility(4);
    this.mListAdapter.notifyDataSetChanged();
  }
  
  private void removeMarkedFromLists(int paramInt)
  {
    this.mDeleteFlagArray.set(paramInt, Boolean.valueOf(false));
    this.mDeleteCheckArray.set(paramInt, Integer.valueOf(-1));
    int j = 1;
    paramInt = 0;
    for (;;)
    {
      int i = j;
      if (paramInt < this.mDeleteCheckArray.size())
      {
        if (((Integer)this.mDeleteCheckArray.get(paramInt)).intValue() > 0) {
          i = 0;
        }
      }
      else
      {
        if (i == 0) {
          return;
        }
        paramInt = this.mDeleteCheckArray.size() - 1;
        while (paramInt >= 0)
        {
          if (((Integer)this.mDeleteCheckArray.get(paramInt)).intValue() == -1)
          {
            this.mVideoCount -= 1;
            this.mDeleteFlagArray.remove(paramInt);
            this.mDeleteCheckArray.remove(paramInt);
          }
          paramInt -= 1;
        }
      }
      paramInt += 1;
    }
    this.mEditMode = false;
    this.mListAdapter.notifyDataSetChanged();
  }
  
  private void rowClicked(int paramInt1, int paramInt2)
  {
    this.mSelection = paramInt2;
    this.mListAdapter.notifyDataSetChanged();
    launchVideoPlayer(paramInt1, paramInt2);
  }
  
  private void selectAllForDelete()
  {
    if ((this.mDeleteCheckArray == null) || (this.mDeleteCheckArray.size() == 0)) {}
    for (;;)
    {
      return;
      for (Integer localInteger = Integer.valueOf(0); localInteger.intValue() < this.mDeleteCheckArray.size(); localInteger = Integer.valueOf(localInteger.intValue() + 1)) {
        this.mDeleteCheckArray.set(localInteger.intValue(), Integer.valueOf(1));
      }
    }
  }
  
  private Integer selectedForDeleteCount()
  {
    Integer localInteger = Integer.valueOf(0);
    Iterator localIterator = this.mDeleteCheckArray.iterator();
    while (localIterator.hasNext()) {
      if (((Integer)localIterator.next()).intValue() == 1) {
        localInteger = Integer.valueOf(localInteger.intValue() + 1);
      }
    }
    return localInteger;
  }
  
  public void getSwipeItem(boolean paramBoolean, int paramInt)
  {
    boolean bool = true;
    if ((!this.mDeleteMode) && (!paramBoolean))
    {
      this.mDeleteMode = true;
      this.mEditButton.setText(2131099778);
    }
    int j;
    if (this.mDeleteMode)
    {
      List localList = this.mDeleteFlagArray;
      if (paramBoolean) {
        break label121;
      }
      localList.set(paramInt, Boolean.valueOf(bool));
      if (paramBoolean)
      {
        j = 1;
        paramInt = 0;
      }
    }
    for (;;)
    {
      int i = j;
      if (paramInt < this.mVideos.count())
      {
        if (((Boolean)this.mDeleteFlagArray.get(paramInt)).booleanValue()) {
          i = 0;
        }
      }
      else
      {
        if (i != 0) {
          cancelDeleteMode();
        }
        this.mListAdapter.notifyDataSetChanged();
        return;
        label121:
        bool = false;
        break;
      }
      paramInt += 1;
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).getSupportActionBar().setTitle(2131099989);
    ((VideoListViewActivity)paramActivity).setFragmentTitle(getString(2131099989));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null)
    {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
      this.mDeleteFlagArray = new ArrayList();
      this.mDeleteCheckArray = new ArrayList();
      this.mDeleteMode = false;
      this.mEditMode = false;
    }
    paramBundle = getResources().getDisplayMetrics();
    this.REL_SWIPE_MIN_DISTANCE = ((int)(120.0F * paramBundle.densityDpi / 160.0F + 0.5D));
    this.REL_SWIPE_MAX_OFF_PATH = ((int)(250.0F * paramBundle.densityDpi / 160.0F + 0.5D));
    this.REL_SWIPE_THRESHOLD_VELOCITY = ((int)(200.0F * paramBundle.densityDpi / 160.0F + 0.5D));
    getActivity().setRequestedOrientation(1);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mInflater = paramLayoutInflater;
    this.mFragmentView = paramLayoutInflater.inflate(2130903111, paramViewGroup, false);
    this.mVideos = BlinkClipArray.instance();
    this.mVideos.context = getContext();
    this.mProgress = this.mFragmentView.findViewById(2131558538);
    this.mListView = ((ListView)this.mFragmentView.findViewById(2131558671));
    this.mListAdapter = new VideoSectionAdapter(null);
    this.mListView.setAdapter(this.mListAdapter);
    this.mEditButton = ((Button)this.mFragmentView.findViewById(2131558667));
    this.mEditButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        if (VideoListViewFragment.this.mDeleteMode) {
          VideoListViewFragment.this.cancelDeleteMode();
        }
        for (;;)
        {
          VideoListViewFragment.access$902(VideoListViewFragment.this, false);
          if (!VideoListViewFragment.this.mEditMode) {
            break;
          }
          VideoListViewFragment.this.mVideos.stop();
          return;
          if (VideoListViewFragment.this.mEditMode)
          {
            VideoListViewFragment.access$302(VideoListViewFragment.this, false);
            VideoListViewFragment.this.mEditButton.setText(2131099811);
            VideoListViewFragment.this.mMarkAll.setVisibility(4);
            VideoListViewFragment.this.mDeleteButton.setVisibility(4);
            int i = 0;
            while (i < VideoListViewFragment.this.mDeleteCheckArray.size())
            {
              VideoListViewFragment.this.mDeleteCheckArray.set(i, Integer.valueOf(0));
              i += 1;
            }
            VideoListViewFragment.this.mListAdapter.notifyDataSetChanged();
          }
          else
          {
            VideoListViewFragment.access$302(VideoListViewFragment.this, true);
            VideoListViewFragment.this.mListAdapter.notifyDataSetChanged();
          }
        }
        VideoListViewFragment.this.mVideos.refresh();
      }
    });
    this.mDeleteButton = ((Button)this.mFragmentView.findViewById(2131558669));
    this.mDeleteButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        do
        {
          return;
          if (VideoListViewFragment.this.mDeleteMode)
          {
            VideoListViewFragment.this.cancelDeleteMode();
            VideoListViewFragment.this.mVideos.refresh();
          }
        } while (!VideoListViewFragment.this.mEditMode);
        if (VideoListViewFragment.this.mMarkAllHasBeenSelected)
        {
          VideoListViewFragment.this.deleteAllVideos();
          return;
        }
        VideoListViewFragment.this.private_delete_lines();
      }
    });
    this.mMarkAll = ((Button)this.mFragmentView.findViewById(2131558668));
    this.mMarkAll.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        VideoListViewFragment.access$902(VideoListViewFragment.this, true);
        VideoListViewFragment.this.selectAllForDelete();
        VideoListViewFragment.this.mListAdapter.notifyDataSetChanged();
      }
    });
    this.mPollHandler = new PollHandler();
    return this.mFragmentView;
  }
  
  public void onDetach()
  {
    this.mVideos.stop();
    this.mVideos = null;
    super.onDetach();
  }
  
  public void onPause()
  {
    int i = 0;
    super.onPause();
    this.mPaused = true;
    this.mVideos.stop();
    Object localObject = LocalBroadcastManager.getInstance(BlinkApp.getApp().getApplicationContext());
    ((LocalBroadcastManager)localObject).unregisterReceiver(this.receiveWillProcess);
    ((LocalBroadcastManager)localObject).unregisterReceiver(this.receiveDidProcess);
    this.mListPosition = this.mListView.getFirstVisiblePosition();
    localObject = this.mListView.getChildAt(0);
    if (localObject == null) {}
    for (;;)
    {
      this.mListTop = i;
      this.mListAdapter.notifyDataSetChanged();
      return;
      i = ((View)localObject).getTop() - this.mListView.getPaddingTop();
    }
  }
  
  public void onResume()
  {
    super.onResume();
    this.mPaused = false;
    Object localObject = LocalBroadcastManager.getInstance(BlinkApp.getApp().getApplicationContext());
    ((LocalBroadcastManager)localObject).registerReceiver(this.receiveWillProcess, new IntentFilter("video_will_be_processed"));
    ((LocalBroadcastManager)localObject).registerReceiver(this.receiveDidProcess, new IntentFilter("video_was_processed"));
    if (this.mListView == null) {}
    do
    {
      return;
      localObject = getActivity();
    } while (localObject == null);
    ViewConfiguration.get((Context)localObject);
    localObject = new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        return this.val$gestureDetector.onTouchEvent(paramAnonymousMotionEvent);
      }
    };
    this.mListView.setOnTouchListener((View.OnTouchListener)localObject);
    this.mListAdapter.notifyDataSetChanged();
    this.mListView.setSelectionFromTop(this.mListPosition, this.mListTop);
    this.mProgress.setVisibility(0);
    if (this.mVideos == null) {
      this.mVideos = BlinkClipArray.instance();
    }
    this.mVideos.refresh();
    localObject = this.mPollHandler.obtainMessage(1, this);
    this.mPollHandler.sendMessageDelayed((Message)localObject, 20000L);
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }
  
  class ListGestureDetector
    extends GestureDetector.SimpleOnGestureListener
  {
    private int temp_position = -1;
    
    ListGestureDetector() {}
    
    public boolean onDown(MotionEvent paramMotionEvent)
    {
      this.temp_position = VideoListViewFragment.this.mListView.pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      return super.onDown(paramMotionEvent);
    }
    
    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      if (Math.abs(paramMotionEvent1.getY() - paramMotionEvent2.getY()) > VideoListViewFragment.this.REL_SWIPE_MAX_OFF_PATH) {}
      int i;
      do
      {
        do
        {
          do
          {
            return false;
            if ((paramMotionEvent1.getX() - paramMotionEvent2.getX() <= VideoListViewFragment.this.REL_SWIPE_MIN_DISTANCE) || (Math.abs(paramFloat1) <= VideoListViewFragment.this.REL_SWIPE_THRESHOLD_VELOCITY)) {
              break;
            }
            i = VideoListViewFragment.this.mListView.pointToPosition((int)paramMotionEvent1.getX(), (int)paramMotionEvent2.getY());
          } while ((i < 0) || (this.temp_position != i));
          VideoListViewFragment.this.getSwipeItem(false, i);
          return false;
        } while ((paramMotionEvent2.getX() - paramMotionEvent1.getX() <= VideoListViewFragment.this.REL_SWIPE_MIN_DISTANCE) || (Math.abs(paramFloat1) <= VideoListViewFragment.this.REL_SWIPE_THRESHOLD_VELOCITY));
        i = VideoListViewFragment.this.mListView.pointToPosition((int)paramMotionEvent1.getX(), (int)paramMotionEvent2.getY());
      } while ((i < 0) || (this.temp_position != i));
      VideoListViewFragment.this.getSwipeItem(true, i);
      return false;
    }
    
    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      if (VideoListViewFragment.this.mDeleteMode) {
        VideoListViewFragment.this.cancelDeleteMode();
      }
      for (;;)
      {
        return true;
        int i = VideoListViewFragment.this.mListView.pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
        VideoListViewFragment.this.rowClicked(0, i);
      }
    }
  }
  
  static class PollHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      ((VideoListViewFragment)paramMessage.obj).handleMessage(paramMessage);
      super.handleMessage(paramMessage);
    }
  }
  
  public static class VideoItemHolder
  {
    ImageView blue_dot;
    TextView camName;
    AppCompatCheckBox deleteCheck;
    TextView deleteItemButton;
    ImageView imageView;
    int row;
    TextView subTitle;
    TextView txtTitle;
    
    public VideoItemHolder(int paramInt, View paramView)
    {
      this.camName = ((TextView)paramView.findViewById(2131558674));
      this.txtTitle = ((TextView)paramView.findViewById(2131558675));
      this.subTitle = ((TextView)paramView.findViewById(2131558676));
      this.imageView = ((ImageView)paramView.findViewById(2131558627));
      this.deleteItemButton = ((TextView)paramView.findViewById(2131558677));
      this.deleteItemButton.setVisibility(8);
      this.deleteCheck = ((AppCompatCheckBox)paramView.findViewById(2131558673));
      this.deleteCheck.setVisibility(8);
      this.blue_dot = ((ImageView)paramView.findViewById(2131558672));
      this.blue_dot.setVisibility(4);
      this.row = paramInt;
      paramView.setTag(this);
    }
  }
  
  private class VideoSectionAdapter
    extends SectionAdapter
  {
    private VideoSectionAdapter() {}
    
    public Object getRowItem(int paramInt1, int paramInt2)
    {
      return VideoListViewFragment.this.getListRowItem(paramInt1, paramInt2);
    }
    
    public View getRowView(final int paramInt1, final int paramInt2, View paramView, ViewGroup paramViewGroup)
    {
      paramView = VideoListViewFragment.this.mInflater.inflate(2130903113, paramViewGroup, false);
      paramViewGroup = new VideoListViewFragment.VideoItemHolder(paramInt2, paramView);
      paramViewGroup.imageView.setVisibility(4);
      final Video localVideo = (Video)getRowItem(paramInt1, paramInt2);
      String[] arrayOfString = Util.reformatDate(Util.getLocalDateYearTime(localVideo.getCreated_at())).split("%");
      paramViewGroup.txtTitle.setText(arrayOfString[0]);
      paramViewGroup.subTitle.setText(arrayOfString[1]);
      paramViewGroup.camName.setText(localVideo.getCamera_name());
      if (localVideo.getViewed() == null)
      {
        paramViewGroup.blue_dot.setVisibility(0);
        if (!VideoListViewFragment.this.mDeleteMode) {
          break label316;
        }
        if (!((Boolean)VideoListViewFragment.this.mDeleteFlagArray.get(paramInt2)).booleanValue()) {
          break label294;
        }
        paramViewGroup.deleteItemButton.setVisibility(0);
        paramViewGroup.deleteItemButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {
              return;
            }
            VideoListViewFragment.this.deleteLine(paramInt1, paramInt2, localVideo);
          }
        });
        label180:
        paramViewGroup.deleteCheck.setVisibility(8);
        VideoListViewFragment.this.mMarkAll.setVisibility(4);
        VideoListViewFragment.this.mDeleteButton.setVisibility(4);
      }
      for (;;)
      {
        new ImageLoader(localVideo.getThumbnail(), paramViewGroup.imageView, true, 0);
        if (paramInt2 == VideoListViewFragment.this.mVideos.count() - 1) {
          VideoListViewFragment.this.mVideos.setCurrentIndex(paramInt2);
        }
        if (VideoListViewFragment.this.mSelection != paramInt2) {
          break label535;
        }
        paramView.findViewById(2131558678).setVisibility(0);
        return paramView;
        paramViewGroup.blue_dot.setVisibility(4);
        break;
        label294:
        paramViewGroup.deleteItemButton.setOnClickListener(null);
        paramViewGroup.deleteItemButton.setVisibility(8);
        break label180;
        label316:
        paramViewGroup.deleteItemButton.setVisibility(8);
        if (VideoListViewFragment.this.mEditMode)
        {
          paramViewGroup.deleteCheck.setVisibility(0);
          if ((((Integer)VideoListViewFragment.this.mDeleteCheckArray.get(paramInt2)).intValue() == 1) || (VideoListViewFragment.this.mMarkAllHasBeenSelected))
          {
            VideoListViewFragment.this.mDeleteCheckArray.set(paramInt2, Integer.valueOf(1));
            paramViewGroup.deleteCheck.setChecked(true);
          }
          for (;;)
          {
            paramViewGroup.deleteCheck.setTag(Integer.valueOf(paramInt2));
            paramViewGroup.deleteCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
              public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
              {
                boolean bool = true;
                int j = ((Integer)paramAnonymousCompoundButton.getTag()).intValue();
                int i;
                if (paramAnonymousBoolean)
                {
                  i = 1;
                  VideoListViewFragment.this.mDeleteCheckArray.set(j, Integer.valueOf(i));
                  paramAnonymousCompoundButton = VideoListViewFragment.this;
                  if (VideoListViewFragment.this.selectedForDeleteCount().intValue() != VideoListViewFragment.this.mVideoCount) {
                    break label92;
                  }
                }
                label92:
                for (paramAnonymousBoolean = bool;; paramAnonymousBoolean = false)
                {
                  VideoListViewFragment.access$902(paramAnonymousCompoundButton, paramAnonymousBoolean);
                  return;
                  i = 0;
                  break;
                }
              }
            });
            VideoListViewFragment.this.mEditButton.setText(2131099778);
            VideoListViewFragment.this.mMarkAll.setText(2131099877);
            VideoListViewFragment.this.mMarkAll.setVisibility(0);
            VideoListViewFragment.this.mDeleteButton.setVisibility(0);
            break;
            VideoListViewFragment.this.mDeleteCheckArray.set(paramInt2, Integer.valueOf(0));
            paramViewGroup.deleteCheck.setChecked(false);
          }
        }
        paramViewGroup.deleteCheck.setVisibility(8);
        paramViewGroup.deleteItemButton.setVisibility(8);
      }
      label535:
      paramView.findViewById(2131558678).setVisibility(4);
      return paramView;
    }
    
    public int getSectionHeaderItemViewType(int paramInt)
    {
      return 0;
    }
    
    public View getSectionHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      return null;
    }
    
    public int getSectionHeaderViewTypeCount()
    {
      return 0;
    }
    
    public boolean hasSectionHeaderView(int paramInt)
    {
      return false;
    }
    
    public int numberOfRows(int paramInt)
    {
      return VideoListViewFragment.this.mVideos.count();
    }
    
    public int numberOfSections()
    {
      return 1;
    }
    
    public void onRowItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt1, int paramInt2, long paramLong)
    {
      super.onRowItemClick(paramAdapterView, paramView, paramInt1, paramInt2, paramLong);
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/fragments/VideoListViewFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */