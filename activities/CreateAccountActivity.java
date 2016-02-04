package com.immediasemi.blink.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import com.immediasemi.blink.fragments.CreateAccountFragment;

public class CreateAccountActivity
  extends BaseActivity
{
  public void onCancelClicked(View paramView)
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903071);
    getSupportFragmentManager().beginTransaction().replace(2131558534, CreateAccountFragment.newInstance(-1)).commit();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/activities/CreateAccountActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */