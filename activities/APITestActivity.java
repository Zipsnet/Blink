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
    setContentView(2130903071);
    getSupportFragmentManager().beginTransaction().replace(2131558534, APITestFragment.newInstance(-1)).commit();
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
    default: 
      return;
    }
    getSupportFragmentManager().beginTransaction().replace(2131558534, (Fragment)paramObject).commit();
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/activities/APITestActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */