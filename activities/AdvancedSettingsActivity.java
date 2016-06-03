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
    TextView localTextView2 = (TextView)this.mActionBarView.findViewById(2131558539);
    TextView localTextView1 = (TextView)this.mActionBarView.findViewById(2131558538);
    TextView localTextView3 = (TextView)this.mActionBarView.findViewById(2131558488);
    localTextView2.setVisibility(0);
    localTextView1.setVisibility(0);
    localTextView3.setText(getString(2131099997));
  }
  
  public void onCancelAdvSettingsClicked(View paramView)
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903074);
    paramBundle = getSupportFragmentManager();
    if (getIntent().getExtras() != null) {}
    paramBundle.beginTransaction().replace(2131558537, AdvancedSettingsFragment.newInstance(-1)).commit();
    this.mActionBarView = getLayoutInflater().inflate(2130903076, null);
    paramBundle = getSupportActionBar();
    paramBundle.setCustomView(this.mActionBarView);
    paramBundle.setDisplayOptions(16);
    this.mActionBarView.findViewById(2131558538).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          AdvancedSettingsActivity.this.onCancelAdvSettingsClicked(paramAnonymousView);
        }
      }
    });
    this.mActionBarView.findViewById(2131558539).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          AdvancedSettingsActivity.this.onDoneAdvSettingsClicked(paramAnonymousView);
        }
      }
    });
    setMenuItemVisibilty();
  }
  
  public void onDoneAdvSettingsClicked(View paramView)
  {
    finish();
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/AdvancedSettingsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */