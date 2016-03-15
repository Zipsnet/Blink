package com.immediasemi.blink.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.utils.OnClick;

public class ManageSystemsActivity
  extends BaseActivity
{
  private void setTempSwitch(boolean paramBoolean)
  {
    RadioButton localRadioButton1 = (RadioButton)findViewById(2131558704);
    RadioButton localRadioButton2 = (RadioButton)findViewById(2131558705);
    int i = getResources().getColor(2131492875);
    int j = getResources().getColor(2131492970);
    if (paramBoolean)
    {
      localRadioButton1.setChecked(true);
      localRadioButton2.setChecked(false);
      localRadioButton1.setBackground(getResources().getDrawable(2130837590));
      localRadioButton2.setBackground(getResources().getDrawable(2130837588));
      localRadioButton1.setTextColor(j);
      localRadioButton2.setTextColor(i);
      return;
    }
    localRadioButton1.setChecked(false);
    localRadioButton2.setChecked(true);
    localRadioButton2.setBackground(getResources().getDrawable(2130837591));
    localRadioButton1.setBackground(getResources().getDrawable(2130837588));
    localRadioButton2.setTextColor(j);
    localRadioButton1.setTextColor(i);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (((0xFFFF & paramInt1) != 113) || (paramInt2 == 0)) {
      return;
    }
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903119);
    paramBundle = (RadioGroup)findViewById(2131558703);
    if (BlinkApp.getApp().isTempUnits()) {
      setTempSwitch(true);
    }
    for (;;)
    {
      paramBundle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
      {
        public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
        {
          if (paramAnonymousInt == 2131558704) {}
          for (boolean bool = true;; bool = false)
          {
            BlinkApp.getApp().setTempUnits(bool);
            ManageSystemsActivity.this.setTempSwitch(bool);
            return;
          }
        }
      });
      findViewById(2131558706).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!OnClick.ok()) {
            return;
          }
          paramAnonymousView = new Intent(ManageSystemsActivity.this, EventsActivity.class);
          ManageSystemsActivity.this.startActivityForResult(paramAnonymousView, 108);
        }
      });
      PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().remove("onboard_sequence").apply();
      findViewById(2131558708).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!OnClick.ok()) {
            return;
          }
          new AlertDialog.Builder(ManageSystemsActivity.this).setTitle(ManageSystemsActivity.this.getString(2131099922)).setMessage(2131099746).setPositiveButton(2131100021, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().remove("onboard_sequence").commit();
              paramAnonymous2DialogInterface = new Intent(ManageSystemsActivity.this, OnboardingActivity.class);
              paramAnonymous2DialogInterface.putExtra("onboard_reason", 2);
              ManageSystemsActivity.this.startActivityForResult(paramAnonymous2DialogInterface, 113);
            }
          }).setNegativeButton(2131099778, null).create().show();
        }
      });
      return;
      setTempSwitch(false);
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/activities/ManageSystemsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */