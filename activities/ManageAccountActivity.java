package com.immediasemi.blink.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.fragments.ManageEmailFragment;
import com.immediasemi.blink.fragments.ManagePasswordFragment;

public class ManageAccountActivity
  extends BaseActivity
{
  private RelativeLayout emailView;
  private RelativeLayout passwordView;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903118);
    ((TextView)findViewById(2131558698)).setText(BlinkApp.getApp().getUserName());
    this.emailView = ((RelativeLayout)findViewById(2131558696));
    this.passwordView = ((RelativeLayout)findViewById(2131558699));
    this.emailView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ManageAccountActivity.this.emailView.setVisibility(8);
        ManageAccountActivity.this.passwordView.setVisibility(8);
        paramAnonymousView = new ManageEmailFragment();
        ManageAccountActivity.this.getSupportFragmentManager().beginTransaction().add(2131558701, paramAnonymousView).commit();
      }
    });
    this.passwordView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ManageAccountActivity.this.emailView.setVisibility(8);
        ManageAccountActivity.this.passwordView.setVisibility(8);
        paramAnonymousView = new ManagePasswordFragment();
        ManageAccountActivity.this.getSupportFragmentManager().beginTransaction().add(2131558701, paramAnonymousView).commit();
      }
    });
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/activities/ManageAccountActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */