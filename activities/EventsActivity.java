package com.immediasemi.blink.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.immediasemi.blink.fragments.ActivityFilterFragment;
import com.immediasemi.blink.fragments.EventsListFragment;
import com.immediasemi.blink.utils.OnClick;

public class EventsActivity
  extends BaseActivity
{
  private static final String EVENT_LIST_TAG = "event_list_tag";
  private static final String FILTER_TAG = "filter_tag";
  private View mActionBarView;
  
  private void setMenuItemVisibilty(boolean paramBoolean)
  {
    TextView localTextView1 = (TextView)this.mActionBarView.findViewById(2131558536);
    TextView localTextView2 = (TextView)this.mActionBarView.findViewById(2131558535);
    TextView localTextView3 = (TextView)this.mActionBarView.findViewById(2131558488);
    if (paramBoolean)
    {
      localTextView1.setVisibility(0);
      localTextView2.setVisibility(0);
      localTextView3.setText(getString(2131099982));
      return;
    }
    localTextView1.setVisibility(4);
    localTextView2.setVisibility(4);
    localTextView3.setText(getString(2131099981));
  }
  
  public void onCancelClicked(View paramView)
  {
    paramView = getSupportFragmentManager();
    paramView.beginTransaction().remove(paramView.findFragmentByTag("filter_tag")).commit();
    setMenuItemVisibilty(false);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903071);
    getSupportFragmentManager().beginTransaction().replace(2131558534, EventsListFragment.newInstance(-1), "event_list_tag").commit();
    paramBundle = getSupportActionBar();
    paramBundle.setDisplayHomeAsUpEnabled(false);
    this.mActionBarView = getLayoutInflater().inflate(2130903075, null);
    paramBundle.setCustomView(this.mActionBarView);
    paramBundle.setDisplayOptions(16);
    setMenuItemVisibilty(false);
    this.mActionBarView.findViewById(2131558535).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        EventsActivity.this.onCancelClicked(paramAnonymousView);
      }
    });
    this.mActionBarView.findViewById(2131558536).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        EventsActivity.this.onDoneClicked(paramAnonymousView);
      }
    });
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
  
  public void onDoneClicked(View paramView)
  {
    Object localObject = getSupportFragmentManager();
    paramView = (ActivityFilterFragment)((FragmentManager)localObject).findFragmentByTag("filter_tag");
    ((FragmentManager)localObject).beginTransaction().remove(paramView).commit();
    setMenuItemVisibilty(false);
    localObject = (EventsListFragment)((FragmentManager)localObject).findFragmentByTag("event_list_tag");
    ((EventsListFragment)localObject).setStartTime(paramView.getStartDateValue());
    ((EventsListFragment)localObject).setEndTime(paramView.getEndDateValue());
    ((EventsListFragment)localObject).setFilterMask(paramView.getFilterMask());
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/activities/EventsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */