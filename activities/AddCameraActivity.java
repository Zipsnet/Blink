package com.immediasemi.blink.activities;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.fragments.AddCamera_1_Explanation;
import com.immediasemi.blink.fragments.AddCamera_2_Fragment;
import com.immediasemi.blink.fragments.AddCamera_3_WaitForCameraFragment;
import com.immediasemi.blink.fragments.AddCamera_4_SuccessFragment;
import com.immediasemi.blink.fragments.AddCamera_5_NameCameraFragment;
import com.immediasemi.blink.fragments.AddCamera_6_TakeSnapshotFragment;
import com.immediasemi.blink.fragments.AddCamera_7_AddCameraFragment;
import com.immediasemi.blink.fragments.AddCamera_8_RetryFragment;
import com.immediasemi.blink.utils.OnClick;

public class AddCameraActivity
  extends BaseActivity
{
  public static final String ADD_CAMERA_SEQUENCE = "add_camera_sequence";
  private View mActionBarView;
  private int mAddCameraSequence = 0;
  
  public void onCancelClicked(View paramView)
  {
    if (!OnClick.ok()) {
      return;
    }
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().remove("add_camera_sequence").commit();
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903071);
    paramBundle = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext());
    if (paramBundle.contains("add_camera_sequence")) {
      this.mAddCameraSequence = paramBundle.getInt("add_camera_sequence", 0);
    }
    paramBundle = getSupportFragmentManager();
    switch (this.mAddCameraSequence)
    {
    default: 
      paramBundle.beginTransaction().replace(2131558534, AddCamera_1_Explanation.newInstance(-1)).commit();
    }
    for (;;)
    {
      paramBundle = getSupportActionBar();
      paramBundle.setDisplayHomeAsUpEnabled(false);
      this.mActionBarView = getLayoutInflater().inflate(2130903072, null);
      paramBundle.setCustomView(this.mActionBarView);
      paramBundle.setDisplayOptions(16);
      this.mActionBarView.findViewById(2131558535).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AddCameraActivity.this.onCancelClicked(paramAnonymousView);
        }
      });
      return;
      paramBundle.beginTransaction().replace(2131558534, AddCamera_2_Fragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558534, AddCamera_3_WaitForCameraFragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558534, AddCamera_4_SuccessFragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558534, AddCamera_5_NameCameraFragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558534, AddCamera_6_TakeSnapshotFragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558534, AddCamera_7_AddCameraFragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558534, AddCamera_8_RetryFragment.newInstance(-1)).commit();
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
  
  protected void onStop()
  {
    super.onStop();
    this.mAddCameraSequence = -1;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/activities/AddCameraActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */