package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.utils.OnClick;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityFilterFragment
  extends BaseFragment
{
  private static final int ACTIVITY_GROUP_MASK = 248;
  public static final int ALL_ACTIVITIES_MASK = 8;
  public static final int ALL_CAMERAS_MASK = 4;
  public static final int ANYTIME_MASK = 1;
  private static final String ARG_IS_EVENT_LIST = "arg_is_event_list";
  public static final int ARM_MASK = 64;
  public static final int BATTERY_MASK = 128;
  private static final String CHECK_CHAR = "√";
  public static final String FILTER_TAG = "FILTER_TAG";
  public static final int MOTION_RECORDINGS_MASK = 16;
  public static final int SPECIFIC_MASK = 2;
  public static final int TEMPERATURE_MASK = 32;
  private static final int TIME_GROUP_MASK = 3;
  private LinearLayout mActivityTypeGroup;
  private LinearLayout mCamerasGroup;
  private LinearLayout mEndDateGroup;
  private TextView mEndDateHeader;
  private TextView mEndDateValue;
  private LayoutInflater mInflater;
  private boolean mIsEventList;
  private int mSectionNumber;
  private LinearLayout mSpecificRangeGroup;
  private LinearLayout mStartDateGroup;
  private TextView mStartDateHeader;
  private TextView mStartDateValue;
  private LinearLayout mTimeFrameGroup;
  private List<FilterItem> mViews;
  private int mWhatsChecked;
  
  private void addTV(int paramInt, TextView paramTextView)
  {
    FilterItem localFilterItem = new FilterItem(null);
    localFilterItem.mask = paramInt;
    localFilterItem.tv = paramTextView;
    paramTextView.setTag(Integer.valueOf(this.mViews.size()));
    this.mViews.add(localFilterItem);
    paramTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        do
        {
          return;
          int i = ((Integer)paramAnonymousView.getTag()).intValue();
          paramAnonymousView = (ActivityFilterFragment.FilterItem)ActivityFilterFragment.this.mViews.get(i);
        } while ((paramAnonymousView.mask & ActivityFilterFragment.this.mWhatsChecked) != 0);
        if ((paramAnonymousView.mask & 0x3) != 0) {
          ActivityFilterFragment.access$472(ActivityFilterFragment.this, -4);
        }
        for (;;)
        {
          ActivityFilterFragment.access$476(ActivityFilterFragment.this, paramAnonymousView.mask);
          ActivityFilterFragment.this.updateChecks(true);
          return;
          if ((paramAnonymousView.mask & 0xF8) != 0) {
            ActivityFilterFragment.access$472(ActivityFilterFragment.this, 65287);
          }
        }
      }
    });
  }
  
  public static ActivityFilterFragment newInstance(int paramInt, boolean paramBoolean)
  {
    ActivityFilterFragment localActivityFilterFragment = new ActivityFilterFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localBundle.putBoolean("arg_is_event_list", paramBoolean);
    localActivityFilterFragment.setArguments(localBundle);
    return localActivityFilterFragment;
  }
  
  private void updateChecks(boolean paramBoolean)
  {
    int i = 0;
    while (i < this.mViews.size())
    {
      Object localObject2 = ((FilterItem)this.mViews.get(i)).tv.getText().toString();
      Object localObject1 = localObject2;
      if (((String)localObject2).startsWith("√")) {
        localObject1 = ((String)localObject2).substring(1).trim();
      }
      int j = this.mWhatsChecked;
      localObject2 = localObject1;
      if ((((FilterItem)this.mViews.get(i)).mask & j) != 0) {
        localObject2 = "√ " + (String)localObject1;
      }
      ((FilterItem)this.mViews.get(i)).tv.setText((CharSequence)localObject2);
      i += 1;
    }
    if ((this.mWhatsChecked & 0x2) != 0)
    {
      this.mSpecificRangeGroup.setVisibility(0);
      return;
    }
    this.mSpecificRangeGroup.setVisibility(8);
    this.mStartDateGroup.setVisibility(8);
    this.mEndDateGroup.setVisibility(8);
  }
  
  public String getEndDateValue()
  {
    return this.mEndDateValue.getText().toString();
  }
  
  public int getFilterMask()
  {
    return this.mWhatsChecked;
  }
  
  public String getStartDateValue()
  {
    return this.mStartDateValue.getText().toString();
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099978));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null)
    {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
      this.mIsEventList = getArguments().getBoolean("arg_is_event_list");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mInflater = paramLayoutInflater;
    paramLayoutInflater = paramLayoutInflater.inflate(2130903085, paramViewGroup, false);
    this.mViews = new ArrayList();
    this.mTimeFrameGroup = ((LinearLayout)paramLayoutInflater.findViewById(2131558553));
    this.mSpecificRangeGroup = ((LinearLayout)paramLayoutInflater.findViewById(2131558556));
    this.mStartDateHeader = ((TextView)paramLayoutInflater.findViewById(2131558557));
    this.mStartDateGroup = ((LinearLayout)paramLayoutInflater.findViewById(2131558558));
    this.mStartDateValue = ((TextView)paramLayoutInflater.findViewById(2131558559));
    this.mStartDateHeader.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        if (ActivityFilterFragment.this.mStartDateGroup.getVisibility() != 0)
        {
          ActivityFilterFragment.this.mStartDateGroup.setVisibility(0);
          return;
        }
        ActivityFilterFragment.this.mStartDateGroup.setVisibility(8);
      }
    });
    this.mStartDateValue.setText(new Date().toString());
    this.mEndDateHeader = ((TextView)paramLayoutInflater.findViewById(2131558560));
    this.mEndDateGroup = ((LinearLayout)paramLayoutInflater.findViewById(2131558561));
    this.mEndDateValue = ((TextView)paramLayoutInflater.findViewById(2131558562));
    this.mEndDateHeader.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        if (ActivityFilterFragment.this.mEndDateGroup.getVisibility() != 0)
        {
          ActivityFilterFragment.this.mEndDateGroup.setVisibility(0);
          return;
        }
        ActivityFilterFragment.this.mEndDateGroup.setVisibility(8);
      }
    });
    this.mEndDateValue.setText(new Date().toString());
    this.mCamerasGroup = ((LinearLayout)paramLayoutInflater.findViewById(2131558563));
    this.mActivityTypeGroup = ((LinearLayout)paramLayoutInflater.findViewById(2131558566));
    addTV(1, (TextView)paramLayoutInflater.findViewById(2131558554));
    addTV(2, (TextView)paramLayoutInflater.findViewById(2131558555));
    addTV(4, (TextView)paramLayoutInflater.findViewById(2131558564));
    addTV(8, (TextView)paramLayoutInflater.findViewById(2131558567));
    addTV(16, (TextView)paramLayoutInflater.findViewById(2131558568));
    addTV(32, (TextView)paramLayoutInflater.findViewById(2131558569));
    addTV(64, (TextView)paramLayoutInflater.findViewById(2131558570));
    addTV(128, (TextView)paramLayoutInflater.findViewById(2131558571));
    this.mWhatsChecked = 13;
    if (this.mIsEventList)
    {
      paramLayoutInflater.findViewById(2131558566).setVisibility(0);
      paramLayoutInflater.findViewById(2131558565).setVisibility(0);
    }
    for (;;)
    {
      updateChecks(false);
      return paramLayoutInflater;
      paramLayoutInflater.findViewById(2131558566).setVisibility(8);
      paramLayoutInflater.findViewById(2131558565).setVisibility(8);
    }
  }
  
  public void onDetach()
  {
    super.onDetach();
  }
  
  private class FilterItem
  {
    int mask;
    TextView tv;
    
    private FilterItem() {}
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/ActivityFilterFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */