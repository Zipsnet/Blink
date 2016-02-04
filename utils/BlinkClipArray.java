package com.immediasemi.blink.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.api.requests.Videos.DeleteAllVideosRequest_v2;
import com.immediasemi.blink.api.requests.Videos.DeleteVideoByIdRequest_v2;
import com.immediasemi.blink.api.requests.Videos.GetVideosCountRequest_v2;
import com.immediasemi.blink.api.requests.Videos.GetVideosRequest_v2;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.Count;
import com.immediasemi.blink.models.Video;
import com.immediasemi.blink.models.Videos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class BlinkClipArray
{
  public static final String BLINK_DID_PROCESS_VIDEOS_NOTIFICATION = "video_was_processed";
  public static final String BLINK_WILL_PROCESS_VIDEOS_NOTIFICATION = "video_will_be_processed";
  private static final int DELETE_ALL_VIDEOS_DELAYED = 2;
  private static final int DELETE_VIDEO_IDS_DELAYED = 5;
  private static final int DELETE_VIDEO_ID_DELAYED = 4;
  private static final int GET_VIDEO_PAGE_DELAYED = 3;
  private static final int PAGE_LENGTH = 10;
  private static final int POLL_FOR_REFRESH = 1;
  private static final int REFRESH_INTERVAL = 20000;
  private static BlinkClipArray mBlinkClipArray;
  public Context context;
  private TreeSet<Video> mBackingStore = null;
  private Object[] mBackingStoreArray = null;
  private boolean mBusy = false;
  private int mClipCount = 0;
  private int mCurrentIndex = 0;
  private int mMaxVideoID = 0;
  private int mMinVideoID = 0;
  private PollHandler mPollHandler = null;
  private List<Integer> mVideosToDelete;
  
  private void deleteVideoID(int paramInt)
  {
    if (this.mBusy)
    {
      if (this.mPollHandler == null) {
        this.mPollHandler = new PollHandler();
      }
      localObject = this.mPollHandler.obtainMessage(4, paramInt, 0, this);
      this.mPollHandler.sendMessageDelayed((Message)localObject, 100L);
      return;
    }
    this.mBusy = true;
    BlinkApp.getApp().setLastVideoId(String.valueOf(paramInt));
    Object localObject = new DeleteVideoByIdRequest_v2();
    BlinkAPI.BlinkAPIRequest(((DeleteVideoByIdRequest_v2)localObject).getQuery(), null, (BlinkRequest)localObject, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        Toast.makeText(BlinkApp.getApp().getApplicationContext(), "Couldn't delete video clip", 0).show();
        BlinkClipArray.access$102(BlinkClipArray.this, false);
        BlinkClipArray.this.deleteVideoIDs(BlinkClipArray.this.mVideosToDelete);
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        BlinkClipArray.access$102(BlinkClipArray.this, false);
        BlinkClipArray.this.deleteVideoIDs(BlinkClipArray.this.mVideosToDelete);
      }
    }, false);
  }
  
  private void getVideoCount()
  {
    postVideosWillProcessNotification();
    GetVideosCountRequest_v2 localGetVideosCountRequest_v2 = new GetVideosCountRequest_v2();
    BlinkAPI.BlinkAPIRequest(localGetVideosCountRequest_v2.getQuery(), null, localGetVideosCountRequest_v2, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        BlinkClipArray.access$202(BlinkClipArray.this, 0);
        BlinkClipArray.access$102(BlinkClipArray.this, false);
        BlinkClipArray.this.rePoll();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        BlinkClipArray.access$102(BlinkClipArray.this, false);
        int i = ((Count)paramAnonymousBlinkData).getCount();
        if (i != BlinkClipArray.this.mClipCount)
        {
          BlinkClipArray.access$202(BlinkClipArray.this, i);
          BlinkClipArray.this.getVideoPage(1);
          return;
        }
        BlinkClipArray.this.postVideosWillProcessNotification();
        BlinkClipArray.this.postVideosDidProcessNotification();
      }
    }, false);
  }
  
  private void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default: 
      return;
    case 1: 
      refresh();
      return;
    case 2: 
      deleteAllVideos();
      return;
    case 3: 
      getVideoPage(paramMessage.arg1);
      return;
    case 4: 
      deleteVideoID(paramMessage.arg1);
      return;
    }
    deleteVideoIDs(this.mVideosToDelete);
  }
  
  public static BlinkClipArray instance()
  {
    if (mBlinkClipArray == null) {
      mBlinkClipArray = new BlinkClipArray();
    }
    return mBlinkClipArray;
  }
  
  private void rePoll()
  {
    if (this.mPollHandler == null) {
      this.mPollHandler = new PollHandler();
    }
    this.mPollHandler.removeMessages(1);
    Message localMessage = this.mPollHandler.obtainMessage(1, this);
    this.mPollHandler.sendMessageDelayed(localMessage, 20000L);
  }
  
  public void addObject(Video paramVideo)
  {
    this.mBackingStore.add(paramVideo);
    this.mBackingStoreArray = this.mBackingStore.toArray();
    updateMinMaxIDs();
  }
  
  public void addObjectsFromArray(Video[] paramArrayOfVideo)
  {
    int j = paramArrayOfVideo.length;
    int i = 0;
    while (i < j)
    {
      Video localVideo = paramArrayOfVideo[i];
      this.mBackingStore.add(localVideo);
      i += 1;
    }
    this.mBackingStoreArray = this.mBackingStore.toArray();
    updateMinMaxIDs();
  }
  
  public int count()
  {
    return this.mBackingStore.size();
  }
  
  public void deleteAllVideos()
  {
    if (this.mBusy)
    {
      if (this.mPollHandler == null) {
        this.mPollHandler = new PollHandler();
      }
      localObject = this.mPollHandler.obtainMessage(2, this);
      this.mPollHandler.sendMessageDelayed((Message)localObject, 100L);
      return;
    }
    this.mBusy = true;
    Object localObject = new DeleteAllVideosRequest_v2();
    BlinkAPI.BlinkAPIRequest(((DeleteAllVideosRequest_v2)localObject).getQuery(), null, (BlinkRequest)localObject, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        Toast.makeText(BlinkApp.getApp().getApplicationContext(), "Couldn't delete all video clips", 0).show();
        BlinkClipArray.this.reload();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        BlinkClipArray.this.reload();
      }
    }, false);
  }
  
  public void deleteVideoIDs(List<Integer> paramList)
  {
    if (this.mBusy)
    {
      this.mVideosToDelete = paramList;
      if (this.mPollHandler == null) {
        this.mPollHandler = new PollHandler();
      }
      paramList = this.mPollHandler.obtainMessage(5, this);
      this.mPollHandler.sendMessageDelayed(paramList, 100L);
      return;
    }
    if ((paramList == null) || (paramList.size() == 0))
    {
      refresh();
      return;
    }
    int i = ((Integer)paramList.get(0)).intValue();
    removeObjectId(i);
    paramList.remove(0);
    this.mVideosToDelete = paramList;
    deleteVideoID(i);
  }
  
  public int getClipCount()
  {
    return this.mClipCount;
  }
  
  public void getVideoPage(final int paramInt)
  {
    if (this.mBusy)
    {
      if (this.mPollHandler == null) {
        this.mPollHandler = new PollHandler();
      }
      localObject = this.mPollHandler.obtainMessage(3, paramInt, 0, this);
      this.mPollHandler.sendMessageDelayed((Message)localObject, 100L);
      return;
    }
    this.mBusy = true;
    Object localObject = new GetVideosRequest_v2();
    BlinkApp.getApp().setLastPage(String.valueOf(paramInt));
    BlinkAPI.BlinkAPIRequest(((GetVideosRequest_v2)localObject).getQuery(), null, (BlinkRequest)localObject, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        BlinkClipArray.access$102(BlinkClipArray.this, false);
        BlinkClipArray.this.rePoll();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        BlinkClipArray.access$102(BlinkClipArray.this, false);
        if (paramAnonymousBlinkData != null)
        {
          paramAnonymousBlinkData = ((Videos)paramAnonymousBlinkData).getVideos();
          BlinkClipArray.this.processVideosForPage(paramAnonymousBlinkData, paramInt);
        }
        BlinkClipArray.this.rePoll();
      }
    }, false);
  }
  
  public boolean hasNext()
  {
    return this.mCurrentIndex < count() - 1;
  }
  
  public boolean hasPrevious()
  {
    return this.mCurrentIndex > 0;
  }
  
  public int idForIndex(int paramInt)
  {
    if (paramInt < count()) {
      return objectAtIndex(paramInt).getId();
    }
    return 0;
  }
  
  public int indexOfVideoID(int paramInt)
  {
    int j;
    if (paramInt == 0)
    {
      j = -1;
      return j;
    }
    int i = 0;
    Object localObject = videoIDs();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        if (!((Iterator)localObject).hasNext()) {
          break label63;
        }
        j = i;
        if (((Integer)((Iterator)localObject).next()).intValue() == paramInt) {
          break;
        }
        i += 1;
      }
    }
    label63:
    return -1;
  }
  
  public void insertObjectAtIndex(Video paramVideo, int paramInt)
  {
    this.mBackingStore.add(paramVideo);
    this.mBackingStoreArray = this.mBackingStore.toArray();
    if (paramInt <= this.mCurrentIndex) {
      this.mCurrentIndex += 1;
    }
    updateMinMaxIDs();
  }
  
  public Video objectAtIndex(int paramInt)
  {
    if ((this.mBackingStoreArray == null) || (this.mBackingStore.size() != this.mBackingStoreArray.length)) {
      this.mBackingStoreArray = this.mBackingStore.toArray();
    }
    return (Video)this.mBackingStoreArray[paramInt];
  }
  
  public void postVideosDidProcessNotification()
  {
    this.mBusy = false;
    LocalBroadcastManager localLocalBroadcastManager = LocalBroadcastManager.getInstance(BlinkApp.getApp().getApplicationContext());
    Intent localIntent = new Intent();
    localIntent.setAction("video_was_processed");
    localLocalBroadcastManager.sendBroadcast(localIntent);
  }
  
  public void postVideosWillProcessNotification()
  {
    LocalBroadcastManager localLocalBroadcastManager = LocalBroadcastManager.getInstance(BlinkApp.getApp().getApplicationContext());
    Intent localIntent = new Intent();
    localIntent.setAction("video_will_be_processed");
    localLocalBroadcastManager.sendBroadcast(localIntent);
  }
  
  public boolean processVideosForPage(Video[] paramArrayOfVideo, int paramInt)
  {
    postVideosWillProcessNotification();
    if (paramArrayOfVideo.length == 0)
    {
      postVideosDidProcessNotification();
      return true;
    }
    if ((paramArrayOfVideo[0].getId() == this.mMaxVideoID) && (this.mClipCount == count()))
    {
      postVideosDidProcessNotification();
      return true;
    }
    int j = paramArrayOfVideo.length;
    int i = 0;
    while (i < j)
    {
      Video localVideo = paramArrayOfVideo[i];
      if (indexOfVideoID(localVideo.getId()) < 0) {
        addObject(localVideo.mutableCopy());
      }
      i += 1;
    }
    getVideoPage(paramInt + 1);
    postVideosDidProcessNotification();
    return true;
  }
  
  public void refresh()
  {
    if (this.mBusy)
    {
      rePoll();
      return;
    }
    if (this.mPollHandler == null) {
      this.mPollHandler = new PollHandler();
    }
    this.mPollHandler.removeMessages(1);
    this.mBusy = true;
    getVideoCount();
  }
  
  public void reload()
  {
    stop();
    this.mBackingStore.clear();
    this.mBackingStoreArray = null;
    this.mCurrentIndex = 0;
    updateMinMaxIDs();
    this.mBusy = false;
    refresh();
  }
  
  public void removeAllObjects()
  {
    this.mBackingStore.clear();
    this.mBackingStoreArray = null;
  }
  
  public void removeLastObject()
  {
    removeObjectAtIndex(this.mBackingStore.size() - 1);
  }
  
  public void removeObjectAtIndex(int paramInt)
  {
    Video localVideo = objectAtIndex(paramInt);
    int i = localVideo.getId();
    if ((i > 0) && (count() > paramInt))
    {
      deleteVideoID(i);
      this.mBackingStore.remove(localVideo);
      this.mBackingStoreArray = this.mBackingStore.toArray();
      if ((this.mCurrentIndex >= paramInt) && (paramInt != 0)) {
        this.mCurrentIndex -= 1;
      }
      updateMinMaxIDs();
    }
  }
  
  public void removeObjectId(int paramInt)
  {
    if (this.mBackingStore.size() > 0)
    {
      Iterator localIterator = this.mBackingStore.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if (((Video)localObject).getId() == paramInt)
        {
          this.mBackingStore.remove(localObject);
          this.mBackingStoreArray = this.mBackingStore.toArray();
        }
      }
    }
  }
  
  public void replaceObjectAtIndexWithObject(int paramInt, Video paramVideo)
  {
    removeObjectAtIndex(paramInt);
    addObject(paramVideo);
  }
  
  public void setCurrentIndex(int paramInt)
  {
    if (count() == 0)
    {
      this.mCurrentIndex = 0;
      return;
    }
    if (paramInt >= count())
    {
      this.mCurrentIndex = (count() - 1);
      return;
    }
    this.mCurrentIndex = paramInt;
  }
  
  public void stop()
  {
    if (this.mPollHandler != null)
    {
      this.mPollHandler.removeMessages(1);
      this.mPollHandler.removeMessages(2);
      this.mPollHandler.removeMessages(3);
      this.mPollHandler.removeMessages(4);
      this.mPollHandler.removeMessages(5);
    }
    this.mPollHandler = null;
  }
  
  public void updateMinMaxIDs()
  {
    if (count() > 0)
    {
      this.mMinVideoID = idForIndex(0);
      this.mMaxVideoID = idForIndex(count() - 1);
      return;
    }
    this.mMinVideoID = 0;
    this.mMaxVideoID = 0;
    this.mCurrentIndex = 0;
  }
  
  public List<Integer> videoIDs()
  {
    if (this.mBackingStore.size() > 0)
    {
      if (this.mBackingStoreArray == null) {
        this.mBackingStoreArray = this.mBackingStore.toArray();
      }
      ArrayList localArrayList2 = new ArrayList();
      int i = 0;
      for (;;)
      {
        localArrayList1 = localArrayList2;
        if (i >= this.mBackingStoreArray.length) {
          break;
        }
        localArrayList2.add(Integer.valueOf(((Video)this.mBackingStoreArray[i]).getId()));
        i += 1;
      }
    }
    ArrayList localArrayList1 = null;
    return localArrayList1;
  }
  
  static class PollHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      ((BlinkClipArray)paramMessage.obj).handleMessage(paramMessage);
      super.handleMessage(paramMessage);
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/utils/BlinkClipArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */