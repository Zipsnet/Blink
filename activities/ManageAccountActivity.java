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
    setContentView(2130903122);
    ((TextView)findViewById(2131558704)).setText(BlinkApp.getApp().getUserName());
    this.emailView = ((RelativeLayout)findViewById(2131558702));
    this.passwordView = ((RelativeLayout)findViewById(2131558705));
    this.emailView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ManageAccountActivity.this.emailView.setVisibility(8);
        ManageAccountActivity.this.passwordView.setVisibility(8);
        paramAnonymousView = new ManageEmailFragment();
        ManageAccountActivity.this.getSupportFragmentManager().beginTransaction().add(2131558707, paramAnonymousView).commit();
      }
    });
    this.passwordView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ManageAccountActivity.this.emailView.setVisibility(8);
        ManageAccountActivity.this.passwordView.setVisibility(8);
        paramAnonymousView = new ManagePasswordFragment();
        ManageAccountActivity.this.getSupportFragmentManager().beginTransaction().add(2131558707, paramAnonymousView).commit();
      }
    });
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/ManageAccountActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */