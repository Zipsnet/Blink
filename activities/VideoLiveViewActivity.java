package com.immediasemi.blink.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import com.immediasemi.blink.fragments.VideoLiveViewFragment;

public class VideoLiveViewActivity
  extends BaseActivity
{
  public void onBackPressed()
  {
    Fragment localFragment = getSupportFragmentManager().findFragmentById(2131558537);
    if ((localFragment != null) && ((localFragment instanceof VideoLiveViewFragment))) {
      ((VideoLiveViewFragment)localFragment).stopVideo();
    }
    super.onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903074);
    if (paramBundle == null)
    {
      getSupportFragmentManager().beginTransaction().replace(2131558537, VideoLiveViewFragment.newInstance(-1)).commit();
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
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/VideoLiveViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */