package com.immediasemi.blink.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import com.immediasemi.blink.fragments.APITestFragment;
import com.immediasemi.blink.fragments.BaseFragment.OnFragmentInteractionListener.InteractionAction;

public class APITestActivity
  extends BaseActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903074);
    getSupportFragmentManager().beginTransaction().replace(2131558537, APITestFragment.newInstance(-1)).commit();
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
  
  public void onFragmentInteraction(int paramInt, BaseFragment.OnFragmentInteractionListener.InteractionAction paramInteractionAction, Object paramObject)
  {
    switch (paramInteractionAction)
    {
    }
    for (;;)
    {
      return;
      getSupportFragmentManager().beginTransaction().replace(2131558537, (Fragment)paramObject).commit();
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/APITestActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */