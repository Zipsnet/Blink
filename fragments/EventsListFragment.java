package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Events.GetNetworkEventsRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.Event;
import com.immediasemi.blink.models.Event.EVENT_TYPE;
import com.immediasemi.blink.models.Events;
import com.immediasemi.blink.utils.SectionAdapter;
import com.immediasemi.blink.utils.Util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;

public class EventsListFragment
  extends BaseFragment
{
  private static final int GET_EVENTS = 1;
  private static final String HEADER_TAG = "header_tag";
  private static final int IDLE_POLL_DELAY = 2000;
  private static final String ITEM_TAG = "item_tag";
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        super.handleMessage(paramAnonymousMessage);
        return;
      }
      if (EventsListFragment.this.mPaused)
      {
        sendMessageDelayed(obtainMessage(1, paramAnonymousMessage.arg1, paramAnonymousMessage.arg2), 2000L);
        return;
      }
      EventsListFragment.this.getEvents(paramAnonymousMessage.arg1, paramAnonymousMessage.arg2, EventsListFragment.this.hourInterval);
    }
  };
  private int hourInterval = 12;
  private TreeSet<Event> mEvents;
  private String mFilterEndTime;
  private int mFilterEndTimeStamp;
  private int mFilterMask;
  private String mFilterStartTime;
  private int mFilterStartTimeStamp;
  private LayoutInflater mInflater;
  private int mLastDate;
  private ListView mListView;
  private boolean mPaused = true;
  private int mSectionNumber;
  private int mStartDate;
  private int retries = 3;
  
  private Integer dateStringToInt(String paramString)
  {
    paramString = paramString.split("[- T]");
    return Integer.valueOf(Integer.valueOf(Integer.valueOf(paramString[0]).intValue() * 12 + Integer.valueOf(paramString[1]).intValue()).intValue() * 31 + Integer.valueOf(paramString[2]).intValue());
  }
  
  private Integer eventDateStringToInt(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd h:mm:ssZ", Locale.getDefault());
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd h:mm:ss", Locale.getDefault());
    paramString = paramString.replace('T', ' ');
    try
    {
      paramString = dateStringToInt(localSimpleDateFormat2.format(localSimpleDateFormat1.parse(paramString)));
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return Integer.valueOf(0);
  }
  
  private void getEvents(int paramInt1, int paramInt2, int paramInt3)
  {
    final int j = paramInt2;
    final int i = paramInt3;
    if (paramInt2 < 0)
    {
      i = paramInt3 + paramInt2;
      j = 0;
    }
    GetNetworkEventsRequest localGetNetworkEventsRequest = new GetNetworkEventsRequest(paramInt1, j, i);
    BlinkAPI.BlinkAPIRequest(localGetNetworkEventsRequest.getQuery(), null, localGetNetworkEventsRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if ((EventsListFragment.this.getActivity() == null) || (EventsListFragment.this.mListener == null) || (EventsListFragment.this.isDetached())) {}
        do
        {
          return;
          if ((EventsListFragment.this.retries > 0) && (EventsListFragment.this.mLastDate >= EventsListFragment.this.mStartDate))
          {
            EventsListFragment.access$910(EventsListFragment.this);
            if (EventsListFragment.this.hourInterval > 1) {
              EventsListFragment.access$236(EventsListFragment.this, 2);
            }
            for (;;)
            {
              paramAnonymousBlinkError = EventsListFragment.this.handler.obtainMessage(1, EventsListFragment.this.mLastDate, 24 - EventsListFragment.this.hourInterval);
              EventsListFragment.this.handler.sendMessageDelayed(paramAnonymousBlinkError, 100L);
              return;
              EventsListFragment.access$202(EventsListFragment.this, 1);
            }
          }
        } while (EventsListFragment.this.getActivity() == null);
        new AlertDialog.Builder(EventsListFragment.this.getActivity()).setTitle("Error").setMessage("Could not get activity list").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).create().show();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((EventsListFragment.this.getActivity() == null) || (EventsListFragment.this.mListener == null) || (EventsListFragment.this.isDetached())) {}
        do
        {
          return;
          Events localEvents = (Events)paramAnonymousBlinkData;
          int i = 0;
          if (i < localEvents.getEvents().length)
          {
            Event localEvent = localEvents.getEvents()[i];
            Event.EVENT_TYPE localEVENT_TYPE = localEvent.getType();
            paramAnonymousBlinkData = localEVENT_TYPE;
            if (localEVENT_TYPE == null) {
              paramAnonymousBlinkData = Event.EVENT_TYPE.unknown;
            }
            switch (EventsListFragment.4.$SwitchMap$com$immediasemi$blink$models$Event$EVENT_TYPE[paramAnonymousBlinkData.ordinal()])
            {
            }
            for (;;)
            {
              i += 1;
              break;
              if (localEvent.getCamera_name() != null) {
                EventsListFragment.this.mEvents.add(localEvent);
              }
            }
          }
          ((EventsListFragment.EventsSectionAdapter)EventsListFragment.this.mListView.getAdapter()).notifyDataSetChanged();
          if (j != 0) {
            break;
          }
          EventsListFragment.access$610(EventsListFragment.this);
        } while (EventsListFragment.this.mLastDate < EventsListFragment.this.mStartDate);
        for (paramAnonymousBlinkData = EventsListFragment.this.handler.obtainMessage(1, EventsListFragment.this.mLastDate, 24 - EventsListFragment.this.hourInterval);; paramAnonymousBlinkData = EventsListFragment.this.handler.obtainMessage(1, EventsListFragment.this.mLastDate, j - i))
        {
          EventsListFragment.this.handler.sendMessageDelayed(paramAnonymousBlinkData, 1L);
          return;
        }
      }
    }, false);
  }
  
  private void kickOffRequest()
  {
    int j = Calendar.getInstance().get(11) - this.hourInterval;
    int i = j;
    if (j < 0) {
      i = j + this.hourInterval;
    }
    Message localMessage = this.handler.obtainMessage(1, 0, i);
    this.handler.sendMessage(localMessage);
  }
  
  public static EventsListFragment newInstance(int paramInt)
  {
    EventsListFragment localEventsListFragment = new EventsListFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localEventsListFragment.setArguments(localBundle);
    return localEventsListFragment;
  }
  
  private Integer userDateStringToInt(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ssZ", Locale.getDefault());
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("EEE MMM dd h:mm:ss zzz yyyy", Locale.getDefault());
    try
    {
      paramString = dateStringToInt(localSimpleDateFormat2.format(localSimpleDateFormat1.parse(paramString)));
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return Integer.valueOf(0);
  }
  
  public boolean isAllActivity()
  {
    return (this.mFilterMask & 0x8) != 0;
  }
  
  public boolean isAllCameras()
  {
    return (this.mFilterMask & 0x4) != 0;
  }
  
  public boolean isAnytime()
  {
    return (this.mFilterMask & 0x1) != 0;
  }
  
  public boolean isBattery()
  {
    return (this.mFilterMask & 0x80) != 0;
  }
  
  public boolean isMotionRecordings()
  {
    return (this.mFilterMask & 0x10) != 0;
  }
  
  public boolean isSystemArmDisarm()
  {
    return (this.mFilterMask & 0x40) != 0;
  }
  
  public boolean isTemperatureAlerts()
  {
    return (this.mFilterMask & 0x20) != 0;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    try
    {
      this.mListener = ((BaseFragment.OnFragmentInteractionListener)paramActivity);
      ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099977));
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new ClassCastException(paramActivity.toString() + " must implement OnFragmentInteractionListener");
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
    this.mLastDate = 0;
    this.mStartDate = -7;
    this.mFilterMask = 13;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mInflater = paramLayoutInflater;
    paramLayoutInflater = paramLayoutInflater.inflate(2130903098, paramViewGroup, false);
    this.mEvents = new TreeSet(new Comparator()
    {
      public int compare(Event paramAnonymousEvent1, Event paramAnonymousEvent2)
      {
        return -Integer.compare(paramAnonymousEvent1.getId(), paramAnonymousEvent2.getId());
      }
    });
    this.mListView = ((ListView)paramLayoutInflater.findViewById(2131558622));
    this.mListView.setAdapter(new EventsSectionAdapter(null));
    kickOffRequest();
    return paramLayoutInflater;
  }
  
  public void onDetach()
  {
    this.handler.removeMessages(1);
    super.onDetach();
  }
  
  public void onPause()
  {
    super.onPause();
    this.mPaused = true;
  }
  
  public void onResume()
  {
    super.onResume();
    this.mPaused = false;
  }
  
  public void setEndTime(String paramString)
  {
    this.mFilterEndTime = paramString;
    this.mFilterEndTimeStamp = userDateStringToInt(paramString).intValue();
  }
  
  public void setFilterMask(int paramInt)
  {
    if (paramInt == this.mFilterMask) {
      return;
    }
    this.mFilterMask = paramInt;
    if (isAnytime())
    {
      this.mLastDate = 0;
      this.mStartDate = -7;
      kickOffRequest();
    }
    ((EventsSectionAdapter)this.mListView.getAdapter()).notifyDataSetChanged();
  }
  
  public void setStartTime(String paramString)
  {
    this.mFilterStartTime = paramString;
    this.mFilterStartTimeStamp = userDateStringToInt(paramString).intValue();
  }
  
  private class EventsSectionAdapter
    extends SectionAdapter
  {
    private EventsSectionAdapter() {}
    
    public Object getRowItem(int paramInt1, int paramInt2)
    {
      paramInt1 = 0;
      Iterator localIterator = EventsListFragment.this.mEvents.iterator();
      while (localIterator.hasNext())
      {
        Event localEvent = (Event)localIterator.next();
        if (paramInt1 == paramInt2) {
          return localEvent;
        }
        paramInt1 += 1;
      }
      return null;
    }
    
    public View getRowView(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup)
    {
      View localView;
      Event localEvent;
      TextView localTextView;
      ImageView localImageView;
      Object localObject;
      if ((paramView != null) && ("item_tag".equals((String)paramView.getTag())))
      {
        localView = paramView;
        localEvent = (Event)getRowItem(paramInt1, paramInt2);
        paramViewGroup = localEvent.getType();
        paramView = paramViewGroup;
        if (paramViewGroup == null) {
          paramView = Event.EVENT_TYPE.unknown;
        }
        paramViewGroup = (TextView)localView.findViewById(2131558624);
        localTextView = (TextView)localView.findViewById(2131558488);
        localImageView = (ImageView)localView.findViewById(2131558623);
        localObject = Util.reformatDate(Util.getLocalDateYearTime(localEvent.getCreated_at())).split("%");
        paramViewGroup.setText(localObject[0] + "\n" + localObject[1]);
        paramViewGroup = "";
        localObject = EventsListFragment.this.getResources().getDrawable(2130837576);
        paramInt1 = EventsListFragment.this.getResources().getColor(2131492874);
        switch (EventsListFragment.4.$SwitchMap$com$immediasemi$blink$models$Event$EVENT_TYPE[paramView.ordinal()])
        {
        default: 
          paramView = (View)localObject;
        }
      }
      for (;;)
      {
        localTextView.setText(paramViewGroup);
        paramView.mutate();
        paramView.setColorFilter(new LightingColorFilter(0, paramInt1));
        localImageView.setImageDrawable(paramView);
        return localView;
        localView = EventsListFragment.this.mInflater.inflate(2130903100, null, true);
        break;
        paramViewGroup = EventsListFragment.this.getString(2131099812);
        paramView = EventsListFragment.this.getResources().getDrawable(2130837603);
        paramInt1 = EventsListFragment.this.getResources().getColor(2131492875);
        continue;
        paramViewGroup = EventsListFragment.this.getString(2131099815);
        paramView = EventsListFragment.this.getResources().getDrawable(2130837604);
        paramInt1 = EventsListFragment.this.getResources().getColor(2131492875);
        continue;
        paramView = (View)localObject;
        if (localEvent.getCamera_name() != null)
        {
          paramViewGroup = localEvent.getCamera_name();
          paramView = EventsListFragment.this.getResources().getDrawable(2130837605);
          paramInt1 = EventsListFragment.this.getResources().getColor(2131492875);
          continue;
          paramViewGroup = localEvent.getCamera_name();
          paramView = EventsListFragment.this.getResources().getDrawable(2130837785);
          paramInt1 = EventsListFragment.this.getResources().getColor(2131492875);
        }
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
      return EventsListFragment.this.mEvents.size();
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


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/EventsListFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */