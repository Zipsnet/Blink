package com.immediasemi.blink.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Networks.DeleteNetworkRequest;
import com.immediasemi.blink.api.requests.Networks.UpdateNetworkRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.utils.OnClick;

public class ManageSystemsActivity
  extends BaseActivity
{
  private static Activity activity;
  private Button deleteSystem;
  private EditText systemName;
  
  private void changeSystemName(final String paramString)
  {
    if (paramString.compareTo(BlinkApp.getApp().getLastNetworkName()) == 0) {}
    for (;;)
    {
      return;
      UpdateNetworkRequest localUpdateNetworkRequest = new UpdateNetworkRequest();
      localUpdateNetworkRequest.setName(paramString);
      BlinkAPI.BlinkAPIRequest(null, null, localUpdateNetworkRequest, new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError) {}
        
        public void onResult(BlinkData paramAnonymousBlinkData)
        {
          BlinkApp.getApp().setLastNetworkName(paramString);
          ManageSystemsActivity.this.finish();
        }
      }, false);
    }
  }
  
  private void setTempSwitch(boolean paramBoolean)
  {
    RadioButton localRadioButton1 = (RadioButton)findViewById(2131558712);
    RadioButton localRadioButton2 = (RadioButton)findViewById(2131558713);
    int j = getResources().getColor(2131492875);
    int i = getResources().getColor(2131492972);
    if (paramBoolean)
    {
      localRadioButton1.setChecked(true);
      localRadioButton2.setChecked(false);
      localRadioButton1.setBackground(getResources().getDrawable(2130837590));
      localRadioButton2.setBackground(getResources().getDrawable(2130837588));
      localRadioButton1.setTextColor(i);
      localRadioButton2.setTextColor(j);
    }
    for (;;)
    {
      return;
      localRadioButton1.setChecked(false);
      localRadioButton2.setChecked(true);
      localRadioButton2.setBackground(getResources().getDrawable(2130837591));
      localRadioButton1.setBackground(getResources().getDrawable(2130837588));
      localRadioButton2.setTextColor(i);
      localRadioButton1.setTextColor(j);
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (((0xFFFF & paramInt1) != 113) || (paramInt2 == 0)) {}
    for (;;)
    {
      return;
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903123);
    paramBundle = (RadioGroup)findViewById(2131558711);
    if (BlinkApp.getApp().isTempUnits()) {
      setTempSwitch(true);
    }
    for (;;)
    {
      paramBundle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
      {
        public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
        {
          if (paramAnonymousInt == 2131558712) {}
          for (boolean bool = true;; bool = false)
          {
            BlinkApp.getApp().setTempUnits(bool);
            ManageSystemsActivity.this.setTempSwitch(bool);
            return;
          }
        }
      });
      findViewById(2131558714).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!OnClick.ok()) {}
          for (;;)
          {
            return;
            paramAnonymousView = new Intent(ManageSystemsActivity.this, EventsActivity.class);
            ManageSystemsActivity.this.startActivityForResult(paramAnonymousView, 108);
          }
        }
      });
      PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().remove("onboard_sequence").apply();
      findViewById(2131558716).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!OnClick.ok()) {}
          for (;;)
          {
            return;
            new AlertDialog.Builder(ManageSystemsActivity.this).setTitle(ManageSystemsActivity.this.getString(2131099923)).setMessage(2131099746).setPositiveButton(2131100024, new DialogInterface.OnClickListener()
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
        }
      });
      this.systemName = ((EditText)findViewById(2131558709));
      this.systemName.setText(BlinkApp.getApp().getLastNetworkName());
      this.systemName.setOnEditorActionListener(new TextView.OnEditorActionListener()
      {
        public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          if (((paramAnonymousKeyEvent != null) && (paramAnonymousKeyEvent.getKeyCode() == 66)) || (paramAnonymousInt == 6)) {
            ManageSystemsActivity.this.changeSystemName(ManageSystemsActivity.this.systemName.getText().toString());
          }
          for (boolean bool = true;; bool = false) {
            return bool;
          }
        }
      });
      this.deleteSystem = ((Button)findViewById(2131558718));
      if (BlinkApp.getApp().getNetworkCount().intValue() > 1) {
        this.deleteSystem.setVisibility(0);
      }
      this.deleteSystem.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          new ManageSystemsActivity.DeleteDialogFragment().show(ManageSystemsActivity.this.getFragmentManager(), "delete_system");
        }
      });
      activity = this;
      return;
      setTempSwitch(false);
    }
  }
  
  public static class DeleteDialogFragment
    extends DialogFragment
  {
    public Dialog onCreateDialog(Bundle paramBundle)
    {
      paramBundle = new AlertDialog.Builder(getActivity());
      paramBundle.setTitle("Delete system - " + BlinkApp.getApp().getLastNetworkName()).setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          BlinkAPI.BlinkAPIRequest(null, null, new DeleteNetworkRequest(), new BlinkAPI.BlinkAPICallback()
          {
            public void onError(BlinkError paramAnonymous2BlinkError) {}
            
            public void onResult(BlinkData paramAnonymous2BlinkData)
            {
              if (ManageSystemsActivity.activity != null) {
                ManageSystemsActivity.activity.finish();
              }
            }
          }, false);
        }
      }).setNegativeButton("Cancel", null);
      return paramBundle.create();
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/ManageSystemsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */