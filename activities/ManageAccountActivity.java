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
    ((TextView)findViewById(2131558694)).setText(BlinkApp.getApp().getUserName());
    this.emailView = ((RelativeLayout)findViewById(2131558692));
    this.passwordView = ((RelativeLayout)findViewById(2131558695));
    this.emailView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ManageAccountActivity.this.emailView.setVisibility(8);
        ManageAccountActivity.this.passwordView.setVisibility(8);
        paramAnonymousView = new ManageEmailFragment();
        ManageAccountActivity.this.getSupportFragmentManager().beginTransaction().add(2131558697, paramAnonymousView).commit();
      }
    });
    this.passwordView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ManageAccountActivity.this.emailView.setVisibility(8);
        ManageAccountActivity.this.passwordView.setVisibility(8);
        paramAnonymousView = new ManagePasswordFragment();
        ManageAccountActivity.this.getSupportFragmentManager().beginTransaction().add(2131558697, paramAnonymousView).commit();
      }
    });
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/activities/ManageAccountActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */