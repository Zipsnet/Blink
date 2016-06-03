package com.immediasemi.blink.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.immediasemi.blink.fragments.BaseFragment.OnFragmentInteractionListener;
import com.immediasemi.blink.fragments.BaseFragment.OnFragmentInteractionListener.InteractionAction;
import com.immediasemi.blink.utils.NotificationsUtils;
import com.immediasemi.blink.utils.OnClick;
import java.util.List;

public class BaseActivity
  extends AppCompatActivity
  implements BaseFragment.OnFragmentInteractionListener
{
  TextView mActionBarTitleTextView;
  boolean mBlockListener = false;
  BroadcastReceiver mNotificationListener;
  
  protected void initActionBar()
  {
    Object localObject = getSupportActionBar();
    ((ActionBar)localObject).setNavigationMode(0);
    ((ActionBar)localObject).setDisplayShowTitleEnabled(false);
    ((ActionBar)localObject).setDisplayOptions(16);
    ((ActionBar)localObject).setBackgroundDrawable(getResources().getDrawable(2130837573));
    ((ActionBar)localObject).setElevation(0.0F);
    View localView = getLayoutInflater().inflate(2130903069, null);
    this.mActionBarTitleTextView = ((TextView)localView.findViewById(2131558488));
    ((ActionBar)localObject).setCustomView(localView, new ActionBar.LayoutParams(-2, -1, 17));
    try
    {
      localObject = getPackageManager().getActivityInfo(getComponentName(), 128).loadLabel(getPackageManager()).toString();
      this.mActionBarTitleTextView.setText((CharSequence)localObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void onBackPressed()
  {
    FragmentManager localFragmentManager = getSupportFragmentManager();
    if ((localFragmentManager.getFragments() != null) && (localFragmentManager.getFragments().size() > 1) && (localFragmentManager.getBackStackEntryCount() > 0)) {
      localFragmentManager.popBackStack();
    }
    for (;;)
    {
      return;
      super.onBackPressed();
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(null);
    setResult(0);
    initActionBar();
    this.mNotificationListener = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if ((BaseActivity.this.mBlockListener) || (!NotificationsUtils.isNotificationEnabled(BaseActivity.this.getApplicationContext()))) {}
        for (;;)
        {
          return;
          BaseActivity.this.mBlockListener = true;
          paramAnonymousContext = new Runnable()
          {
            public void run()
            {
              String str = "Motion alert from " + this.val$videoDict.getString("camera_name") + " camera";
              new AlertDialog.Builder(BaseActivity.this).setMessage(str).setPositiveButton(2131100015, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                {
                  paramAnonymous3DialogInterface = new Intent(BaseActivity.this, VideoPlayerActivity.class);
                  paramAnonymous3DialogInterface.putExtra("video_dictionary", BaseActivity.1.1.this.val$videoDict);
                  paramAnonymous3DialogInterface.putExtra("start_alert_view", true);
                  BaseActivity.this.startActivity(paramAnonymous3DialogInterface);
                  BaseActivity.this.mBlockListener = false;
                }
              }).setNegativeButton(2131099809, null).create().show();
            }
          };
          BaseActivity.this.runOnUiThread(paramAnonymousContext);
        }
      }
    };
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  public void onFragmentInteraction(int paramInt, BaseFragment.OnFragmentInteractionListener.InteractionAction paramInteractionAction, Object paramObject)
  {
    if ((paramInteractionAction == BaseFragment.OnFragmentInteractionListener.InteractionAction.RESULT) || (paramInteractionAction == BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT)) {
      switch (paramInteractionAction)
      {
      }
    }
    for (;;)
    {
      return;
      if (((Boolean)paramObject).booleanValue() == true) {
        setResult(-1);
      }
      for (;;)
      {
        finish();
        break;
        setResult(0);
      }
      getSupportFragmentManager().beginTransaction().replace(2131558537, (Fragment)paramObject).commit();
      continue;
      onBackPressed();
    }
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
  }
  
  protected void onPause()
  {
    LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(this.mNotificationListener);
    OnClick.enableClicks(true);
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(this.mNotificationListener, new IntentFilter("new_video_notification"));
    if (getClass().equals(VideoPlayerActivity.class)) {}
    for (this.mBlockListener = true;; this.mBlockListener = false) {
      return;
    }
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void setActionBarTitle(String paramString)
  {
    if (this.mActionBarTitleTextView != null) {
      this.mActionBarTitleTextView.setText(paramString);
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */