package com.immediasemi.blink.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.immediasemi.blink.fragments.VideoLiveViewFragment;

public class VideoLiveViewActivity
  extends BaseActivity
{
  private void updateLiveViewLayout()
  {
    final View localView = findViewById(2131558637);
    if (localView == null) {
      return;
    }
    localView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
        localLayoutParams.height = (localView.getWidth() / 16 * 9);
        localView.setLayoutParams(localLayoutParams);
        localView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
      }
    });
  }
  
  public void onBackPressed()
  {
    ((VideoLiveViewFragment)getSupportFragmentManager().findFragmentById(2131558534)).stopVideo();
    super.onBackPressed();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903071);
    if (paramBundle == null)
    {
      getSupportFragmentManager().beginTransaction().replace(2131558534, VideoLiveViewFragment.newInstance(-1)).commit();
      paramBundle = getSupportActionBar();
      if (paramBundle != null)
      {
        paramBundle.setDisplayHomeAsUpEnabled(false);
        paramBundle.hide();
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
  
  public void onResume()
  {
    super.onResume();
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/activities/VideoLiveViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */