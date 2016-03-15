package com.immediasemi.blink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.immediasemi.blink.fragments.AdvancedSettingsFragment;
import com.immediasemi.blink.utils.OnClick;

public class AdvancedSettingsActivity
  extends BaseActivity
{
  protected View mActionBarView;
  
  private void setMenuItemVisibilty()
  {
    TextView localTextView1 = (TextView)this.mActionBarView.findViewById(2131558536);
    TextView localTextView2 = (TextView)this.mActionBarView.findViewById(2131558535);
    TextView localTextView3 = (TextView)this.mActionBarView.findViewById(2131558488);
    localTextView1.setVisibility(0);
    localTextView2.setVisibility(0);
    localTextView3.setText(getString(2131099994));
  }
  
  public void onCancelAdvSettingsClicked(View paramView)
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903071);
    paramBundle = getSupportFragmentManager();
    if (getIntent().getExtras() != null) {}
    paramBundle.beginTransaction().replace(2131558534, AdvancedSettingsFragment.newInstance(-1)).commit();
    this.mActionBarView = getLayoutInflater().inflate(2130903073, null);
    paramBundle = getSupportActionBar();
    paramBundle.setCustomView(this.mActionBarView);
    paramBundle.setDisplayOptions(16);
    this.mActionBarView.findViewById(2131558535).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        AdvancedSettingsActivity.this.onCancelAdvSettingsClicked(paramAnonymousView);
      }
    });
    this.mActionBarView.findViewById(2131558536).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        AdvancedSettingsActivity.this.onDoneAdvSettingsClicked(paramAnonymousView);
      }
    });
    setMenuItemVisibilty();
  }
  
  public void onDoneAdvSettingsClicked(View paramView)
  {
    finish();
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/activities/AdvancedSettingsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */