package com.immediasemi.blink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import com.immediasemi.blink.fragments.VisitBlinkFragment;

public class VisitBlinkActivity
  extends BaseActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903074);
    Bundle localBundle = getIntent().getExtras();
    String str3 = getString(2131100017);
    String str2 = getString(2131099745);
    String str1 = str2;
    paramBundle = str3;
    if (localBundle != null)
    {
      paramBundle = localBundle.getString(VisitBlinkFragment.URL_STRING, str3);
      str1 = localBundle.getString(VisitBlinkFragment.TITLE_STRING, str2);
    }
    getSupportFragmentManager().beginTransaction().replace(2131558537, VisitBlinkFragment.newInstance(-1, paramBundle, str1)).commit();
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/VisitBlinkActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */