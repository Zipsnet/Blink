package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
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
import android.widget.Toast;
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
  private int REL_SWIPE_MAX_OFF_PATH;
  private int REL_SWIPE_MIN_DISTANCE;
  private int REL_SWIPE_THRESHOLD_VELOCITY;
  private BroadcastReceiver gcmVideoNotify = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (VideoListViewFragment.this.getActivity() == null) {}
      for (;;)
      {
        return;
        BlinkClipArray.instance().refresh();
      }
    }
  };
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
  private boolean mListViewEnabled;
  private Button mMarkAll;
  private boolean mMarkAllHasBeenSelected;
  private boolean mPaused = true;
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
      for (;;)
      {
        return;
        if ((!VideoListViewFragment.this.mDeleteMode) && (!VideoListViewFragment.this.mEditMode))
        {
          int i = VideoListViewFragment.this.mVideos.count();
          if (i != VideoListViewFragment.this.mVideoCount) {
            VideoListViewFragment.access$1702(VideoListViewFragment.this, i);
          }
          if (VideoListViewFragment.this.mDeleteFlagArray.size() != VideoListViewFragment.this.mVideoCount) {
            for (i = VideoListViewFragment.this.mDeleteFlagArray.size(); i < VideoListViewFragment.this.mVideos.count(); i++) {
              VideoListViewFragment.this.mDeleteFlagArray.add(0, Boolean.valueOf(false));
            }
          }
          if (VideoListViewFragment.this.mDeleteCheckArray.size() != VideoListViewFragment.this.mVideoCount) {
            for (i = VideoListViewFragment.this.mDeleteCheckArray.size(); i < VideoListViewFragment.this.mVideos.count(); i++) {
              VideoListViewFragment.this.mDeleteCheckArray.add(0, Integer.valueOf(0));
            }
          }
          ((VideoListViewFragment.VideoSectionAdapter)VideoListViewFragment.this.mListView.getAdapter()).notifyDataSetChanged();
          VideoListViewFragment.this.mListView.setSelectionFromTop(VideoListViewFragment.this.mListPosition, VideoListViewFragment.this.mListTop);
          if (VideoListViewFragment.this.mVideos.getClipCount() == 0)
          {
            VideoListViewFragment.this.mFragmentView.findViewById(2131558676).setVisibility(0);
            VideoListViewFragment.this.mProgress.setVisibility(4);
            VideoListViewFragment.access$2102(VideoListViewFragment.this, true);
          }
          else
          {
            VideoListViewFragment.this.mFragmentView.findViewById(2131558676).setVisibility(4);
            if (VideoListViewFragment.this.mVideoCount == 0)
            {
              VideoListViewFragment.this.mProgress.setVisibility(0);
              VideoListViewFragment.access$2102(VideoListViewFragment.this, false);
            }
            else
            {
              VideoListViewFragment.this.mProgress.setVisibility(4);
              VideoListViewFragment.access$2102(VideoListViewFragment.this, true);
            }
          }
        }
      }
    }
  };
  private BroadcastReceiver receiveWillProcess = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = 0;
      if (VideoListViewFragment.this.getActivity() == null) {
        return;
      }
      if ((!VideoListViewFragment.this.mDeleteMode) && (!VideoListViewFragment.this.mEditMode))
      {
        VideoListViewFragment.access$1402(VideoListViewFragment.this, VideoListViewFragment.this.mListView.getFirstVisiblePosition());
        paramAnonymousIntent = VideoListViewFragment.this.mListView.getChildAt(0);
        paramAnonymousContext = VideoListViewFragment.this;
        if (paramAnonymousIntent != null) {
          break label81;
        }
      }
      for (;;)
      {
        VideoListViewFragment.access$1602(paramAnonymousContext, i);
        break;
        break;
        label81:
        i = paramAnonymousIntent.getTop() - VideoListViewFragment.this.mListView.getPaddingTop();
      }
    }
  };
  
  private void cancelDeleteMode()
  {
    this.mEditButton.setText(2131099812);
    this.mDeleteButton.setVisibility(4);
    for (int i = 0; i < this.mDeleteFlagArray.size(); i++) {
      this.mDeleteFlagArray.set(i, Boolean.valueOf(false));
    }
    for (i = 0; i < this.mDeleteCheckArray.size(); i++) {
      this.mDeleteCheckArray.set(i, Integer.valueOf(0));
    }
    this.mDeleteMode = false;
    this.mEditMode = false;
    this.mListAdapter.notifyDataSetChanged();
  }
  
  private void deleteAllVideos()
  {
    this.mProgress.setVisibility(0);
    this.mListViewEnabled = false;
    this.mVideos.deleteAllVideos();
    this.mDeleteFlagArray.clear();
    this.mDeleteCheckArray.clear();
    this.mMarkAllHasBeenSelected = false;
    this.mEditMode = false;
    this.mEditButton.setText(2131099812);
    this.mMarkAll.setVisibility(4);
    this.mDeleteButton.setVisibility(4);
  }
  
  private void deleteLine(int paramInt1, int paramInt2, Video paramVideo)
  {
    BlinkApp.getApp().setLastVideoId(String.valueOf(paramVideo.getId()));
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(paramVideo.getId()));
    this.mProgress.setVisibility(0);
    this.mListViewEnabled = false;
    this.mVideos.deleteVideoIDs(localArrayList);
    this.mVideoCount -= 1;
    this.mDeleteFlagArray.remove(paramInt2);
    this.mDeleteCheckArray.remove(paramInt2);
    int i = 1;
    paramInt1 = 0;
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
        break label148;
      }
      cancelDeleteMode();
    }
    for (;;)
    {
      return;
      paramInt1++;
      break;
      label148:
      this.mListAdapter.notifyDataSetChanged();
    }
  }
  
  private Object getListRowItem(int paramInt1, int paramInt2)
  {
    return this.mVideos.objectAtIndex(paramInt2);
  }
  
  private void launchVideoPlayer(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != 0) || (paramInt2 < 0) || (paramInt2 > this.mVideos.count())) {}
    for (;;)
    {
      return;
      Object localObject2 = (Video)getListRowItem(paramInt1, paramInt2);
      if (localObject2 == null)
      {
        Toast.makeText(BlinkApp.getApp().getApplicationContext(), getString(2131099889), 0).show();
      }
      else
      {
        String str = ((Video)localObject2).getCamera_name();
        Object localObject3 = Util.reformatDate(Util.getLocalDateYearTime(((Video)localObject2).getCreated_at())).split("%");
        Intent localIntent = new Intent(getActivity(), VideoPlayerActivity.class);
        Object localObject1 = new Bundle();
        ((Bundle)localObject1).putString("camera_name", str);
        ((Bundle)localObject1).putStringArray("camera_dates", (String[])localObject3);
        ((Bundle)localObject1).putSerializable("camera_video", (Serializable)localObject2);
        ((Bundle)localObject1).putInt("current_selection", paramInt2);
        BlinkApp.getApp().setLastVideoId(String.valueOf(((Video)localObject2).getId()));
        BlinkApp.getApp().setLastCameraId(String.valueOf(((Video)localObject2).getCamera_id()));
        BlinkApp.getApp().setLastCameraName(str);
        BlinkApp.getApp().setLastNetworkId(String.valueOf(((Video)localObject2).getNetwork_id()));
        localObject2 = new Videos();
        localObject3 = new Video[this.mVideos.count()];
        for (paramInt1 = 0; paramInt1 < localObject3.length; paramInt1++) {
          localObject3[paramInt1] = ((Video)getListRowItem(0, paramInt1));
        }
        ((Videos)localObject2).setVideos((Video[])localObject3);
        ((Bundle)localObject1).putSerializable("video_list", (Serializable)localObject2);
        localIntent.putExtras((Bundle)localObject1);
        getActivity().startActivityForResult(localIntent, 1);
        localObject1 = this.mVideos.objectAtIndex(paramInt2);
        if (localObject1 != null) {
          ((Video)localObject1).setViewed(Util.getTodayFormatted());
        }
      }
    }
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
    for (int i = this.mDeleteCheckArray.size() - 1; i >= 0; i--) {
      if (((Integer)this.mDeleteCheckArray.get(i)).intValue() > 0)
      {
        removeMarkedFromLists(i);
        localArrayList.add(localList.get(i));
      }
    }
    this.mProgress.setVisibility(0);
    this.mListViewEnabled = false;
    this.mVideos.deleteVideoList(localArrayList);
    this.mEditMode = false;
    this.mEditButton.setText(2131099812);
    this.mMarkAll.setVisibility(4);
    this.mDeleteButton.setVisibility(4);
    this.mListAdapter.notifyDataSetChanged();
  }
  
  private void removeMarkedFromLists(int paramInt)
  {
    this.mDeleteFlagArray.set(paramInt, Boolean.valueOf(false));
    this.mDeleteCheckArray.set(paramInt, Integer.valueOf(-1));
    int j = 1;
    for (int i = 0;; i++)
    {
      paramInt = j;
      if (i < this.mDeleteCheckArray.size())
      {
        if (((Integer)this.mDeleteCheckArray.get(i)).intValue() > 0) {
          paramInt = 0;
        }
      }
      else
      {
        if (paramInt == 0) {
          return;
        }
        for (paramInt = this.mDeleteCheckArray.size() - 1; paramInt >= 0; paramInt--) {
          if (((Integer)this.mDeleteCheckArray.get(paramInt)).intValue() == -1)
          {
            this.mVideoCount -= 1;
            this.mDeleteFlagArray.remove(paramInt);
            this.mDeleteCheckArray.remove(paramInt);
          }
        }
      }
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
        break label120;
      }
      localList.set(paramInt, Boolean.valueOf(bool));
      if (paramBoolean) {
        j = 1;
      }
    }
    for (int i = 0;; i++)
    {
      paramInt = j;
      if (i < this.mVideos.count())
      {
        if (((Boolean)this.mDeleteFlagArray.get(i)).booleanValue()) {
          paramInt = 0;
        }
      }
      else
      {
        if (paramInt != 0) {
          cancelDeleteMode();
        }
        this.mListAdapter.notifyDataSetChanged();
        return;
        label120:
        bool = false;
        break;
      }
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).getSupportActionBar().setTitle(2131099992);
    ((VideoListViewActivity)paramActivity).setFragmentTitle(getString(2131099992));
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
    this.mFragmentView = paramLayoutInflater.inflate(2130903115, paramViewGroup, false);
    this.mVideos = BlinkClipArray.instance();
    this.mVideos.context = getContext();
    this.mProgress = this.mFragmentView.findViewById(2131558541);
    this.mListView = ((ListView)this.mFragmentView.findViewById(2131558677));
    this.mListViewEnabled = true;
    this.mListAdapter = new VideoSectionAdapter(null);
    this.mListView.setAdapter(this.mListAdapter);
    this.mEditButton = ((Button)this.mFragmentView.findViewById(2131558673));
    this.mEditButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          if (VideoListViewFragment.this.mDeleteMode) {
            VideoListViewFragment.this.cancelDeleteMode();
          }
          for (;;)
          {
            VideoListViewFragment.access$902(VideoListViewFragment.this, false);
            if (!VideoListViewFragment.this.mEditMode) {
              break label186;
            }
            VideoListViewFragment.this.mVideos.stop();
            break;
            if (VideoListViewFragment.this.mEditMode)
            {
              VideoListViewFragment.access$302(VideoListViewFragment.this, false);
              VideoListViewFragment.this.mEditButton.setText(2131099812);
              VideoListViewFragment.this.mMarkAll.setVisibility(4);
              VideoListViewFragment.this.mDeleteButton.setVisibility(4);
              for (int i = 0; i < VideoListViewFragment.this.mDeleteCheckArray.size(); i++) {
                VideoListViewFragment.this.mDeleteCheckArray.set(i, Integer.valueOf(0));
              }
              VideoListViewFragment.this.mListAdapter.notifyDataSetChanged();
            }
            else
            {
              VideoListViewFragment.access$302(VideoListViewFragment.this, true);
              VideoListViewFragment.this.mListAdapter.notifyDataSetChanged();
            }
          }
          label186:
          VideoListViewFragment.this.mVideos.refresh();
        }
      }
    });
    this.mDeleteButton = ((Button)this.mFragmentView.findViewById(2131558675));
    this.mDeleteButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          if (VideoListViewFragment.this.mDeleteMode)
          {
            VideoListViewFragment.this.cancelDeleteMode();
            VideoListViewFragment.this.mVideos.refresh();
          }
          if (VideoListViewFragment.this.mEditMode) {
            if (VideoListViewFragment.this.mMarkAllHasBeenSelected) {
              VideoListViewFragment.this.deleteAllVideos();
            } else {
              VideoListViewFragment.this.private_delete_lines();
            }
          }
        }
      }
    });
    this.mMarkAll = ((Button)this.mFragmentView.findViewById(2131558674));
    this.mMarkAll.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          VideoListViewFragment.access$902(VideoListViewFragment.this, true);
          VideoListViewFragment.this.selectAllForDelete();
          VideoListViewFragment.this.mListAdapter.notifyDataSetChanged();
        }
      }
    });
    return this.mFragmentView;
  }
  
  public void onDetach()
  {
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
    ((LocalBroadcastManager)localObject).unregisterReceiver(this.gcmVideoNotify);
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
    ((LocalBroadcastManager)localObject).registerReceiver(this.gcmVideoNotify, new IntentFilter("new_video_notification"));
    if (this.mListView == null) {}
    for (;;)
    {
      return;
      localObject = getActivity();
      if (localObject != null)
      {
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
      }
    }
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
      if (!VideoListViewFragment.this.mListViewEnabled) {}
      for (;;)
      {
        return false;
        if (Math.abs(paramMotionEvent1.getY() - paramMotionEvent2.getY()) <= VideoListViewFragment.this.REL_SWIPE_MAX_OFF_PATH)
        {
          int i;
          if ((paramMotionEvent1.getX() - paramMotionEvent2.getX() > VideoListViewFragment.this.REL_SWIPE_MIN_DISTANCE) && (Math.abs(paramFloat1) > VideoListViewFragment.this.REL_SWIPE_THRESHOLD_VELOCITY))
          {
            i = VideoListViewFragment.this.mListView.pointToPosition((int)paramMotionEvent1.getX(), (int)paramMotionEvent2.getY());
            if ((i >= 0) && (this.temp_position == i)) {
              VideoListViewFragment.this.getSwipeItem(false, i);
            }
          }
          else if ((paramMotionEvent2.getX() - paramMotionEvent1.getX() > VideoListViewFragment.this.REL_SWIPE_MIN_DISTANCE) && (Math.abs(paramFloat1) > VideoListViewFragment.this.REL_SWIPE_THRESHOLD_VELOCITY))
          {
            i = VideoListViewFragment.this.mListView.pointToPosition((int)paramMotionEvent1.getX(), (int)paramMotionEvent2.getY());
            if ((i >= 0) && (this.temp_position == i)) {
              VideoListViewFragment.this.getSwipeItem(true, i);
            }
          }
        }
      }
    }
    
    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      boolean bool = false;
      if (!VideoListViewFragment.this.mListViewEnabled) {
        return bool;
      }
      if (VideoListViewFragment.this.mDeleteMode) {
        VideoListViewFragment.this.cancelDeleteMode();
      }
      for (;;)
      {
        bool = true;
        break;
        int i = VideoListViewFragment.this.mListView.pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
        VideoListViewFragment.this.rowClicked(0, i);
      }
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
      this.camName = ((TextView)paramView.findViewById(2131558680));
      this.txtTitle = ((TextView)paramView.findViewById(2131558681));
      this.subTitle = ((TextView)paramView.findViewById(2131558682));
      this.imageView = ((ImageView)paramView.findViewById(2131558630));
      this.deleteItemButton = ((TextView)paramView.findViewById(2131558683));
      this.deleteItemButton.setVisibility(8);
      this.deleteCheck = ((AppCompatCheckBox)paramView.findViewById(2131558679));
      this.deleteCheck.setVisibility(8);
      this.blue_dot = ((ImageView)paramView.findViewById(2131558678));
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
      View localView = paramView;
      if (paramView == null) {
        localView = VideoListViewFragment.this.mInflater.inflate(2130903117, paramViewGroup, false);
      }
      paramView = new VideoListViewFragment.VideoItemHolder(paramInt2, localView);
      paramView.imageView.setVisibility(4);
      final Video localVideo = (Video)getRowItem(paramInt1, paramInt2);
      if (localVideo == null) {}
      for (;;)
      {
        return localView;
        paramViewGroup = Util.reformatDate(Util.getLocalDateYearTime(localVideo.getCreated_at())).split("%");
        paramView.txtTitle.setText(paramViewGroup[0]);
        paramView.subTitle.setText(paramViewGroup[1]);
        paramView.camName.setText(localVideo.getCamera_name());
        if (localVideo.getViewed() == null)
        {
          paramView.blue_dot.setVisibility(0);
          label130:
          if (!VideoListViewFragment.this.mDeleteMode) {
            break label322;
          }
          if (!((Boolean)VideoListViewFragment.this.mDeleteFlagArray.get(paramInt2)).booleanValue()) {
            break label302;
          }
          paramView.deleteItemButton.setVisibility(0);
          paramView.deleteItemButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              if (!OnClick.ok()) {}
              for (;;)
              {
                return;
                VideoListViewFragment.this.deleteLine(paramInt1, paramInt2, localVideo);
              }
            }
          });
          label189:
          paramView.deleteCheck.setVisibility(8);
          VideoListViewFragment.this.mMarkAll.setVisibility(4);
          VideoListViewFragment.this.mDeleteButton.setVisibility(4);
        }
        for (;;)
        {
          new ImageLoader(localVideo.getThumbnail(), paramView.imageView, true, 0);
          if (paramInt2 == VideoListViewFragment.this.mVideos.count() - 1) {
            VideoListViewFragment.this.mVideos.setCurrentIndex(paramInt2);
          }
          if (VideoListViewFragment.this.mSelection != paramInt2) {
            break label533;
          }
          localView.findViewById(2131558684).setVisibility(0);
          break;
          paramView.blue_dot.setVisibility(4);
          break label130;
          label302:
          paramView.deleteItemButton.setOnClickListener(null);
          paramView.deleteItemButton.setVisibility(8);
          break label189;
          label322:
          paramView.deleteItemButton.setVisibility(8);
          if (VideoListViewFragment.this.mEditMode)
          {
            paramView.deleteCheck.setVisibility(0);
            if ((((Integer)VideoListViewFragment.this.mDeleteCheckArray.get(paramInt2)).intValue() == 1) || (VideoListViewFragment.this.mMarkAllHasBeenSelected))
            {
              VideoListViewFragment.this.mDeleteCheckArray.set(paramInt2, Integer.valueOf(1));
              paramView.deleteCheck.setChecked(true);
            }
            for (;;)
            {
              paramView.deleteCheck.setTag(Integer.valueOf(paramInt2));
              paramView.deleteCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
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
              VideoListViewFragment.this.mMarkAll.setText(2131099878);
              VideoListViewFragment.this.mMarkAll.setVisibility(0);
              VideoListViewFragment.this.mDeleteButton.setVisibility(0);
              break;
              VideoListViewFragment.this.mDeleteCheckArray.set(paramInt2, Integer.valueOf(0));
              paramView.deleteCheck.setChecked(false);
            }
          }
          paramView.deleteCheck.setVisibility(8);
          paramView.deleteItemButton.setVisibility(8);
        }
        label533:
        localView.findViewById(2131558684).setVisibility(4);
      }
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


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/VideoListViewFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */