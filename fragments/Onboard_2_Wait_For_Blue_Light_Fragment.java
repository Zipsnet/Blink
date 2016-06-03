package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings.Global;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.SyncModules.ResetSyncModuleRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.utils.OnClick;

public class Onboard_2_Wait_For_Blue_Light_Fragment
  extends BaseFragment
{
  public static final int ONBOARD_INDEX = 2;
  private static final int SYNC_POLLING_INTERVAL = 5000;
  private static final String WIFI_WATCHDOG_POOR_NETWORK_TEST_ENABLED = "wifi_watchdog_poor_network_test_enabled";
  private static final String mWatchdogEnabled = "1";
  private boolean mAdvancedWifiSettingsDisplayed = false;
  private int mSectionNumber;
  private View mView;
  
  private boolean needsWifiWatchdogDisabled()
  {
    String str = Settings.Global.getString(getContext().getContentResolver(), "wifi_watchdog_poor_network_test_enabled");
    if ((str != null) && (str.equals("1"))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static Onboard_2_Wait_For_Blue_Light_Fragment newInstance(int paramInt)
  {
    Onboard_2_Wait_For_Blue_Light_Fragment localOnboard_2_Wait_For_Blue_Light_Fragment = new Onboard_2_Wait_For_Blue_Light_Fragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localOnboard_2_Wait_For_Blue_Light_Fragment.setArguments(localBundle);
    return localOnboard_2_Wait_For_Blue_Light_Fragment;
  }
  
  private void showWifiWatchdogDialog()
  {
    if (!this.mAdvancedWifiSettingsDisplayed) {}
    for (String str = getString(2131100021);; str = getString(2131100022))
    {
      new AlertDialog.Builder(getContext()).setMessage(str).setPositiveButton("Go to Settings", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Onboard_2_Wait_For_Blue_Light_Fragment.access$102(Onboard_2_Wait_For_Blue_Light_Fragment.this, true);
          paramAnonymousDialogInterface = new Intent("android.settings.WIFI_IP_SETTINGS");
          Onboard_2_Wait_For_Blue_Light_Fragment.this.startActivity(paramAnonymousDialogInterface);
        }
      }).create().show();
      return;
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099990));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 2).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903082, paramViewGroup, false);
    ((Button)this.mView.findViewById(2131558543)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          paramAnonymousView = Onboard_3_Connect_To_Blink_Fragment.newInstance(3);
          Onboard_2_Wait_For_Blue_Light_Fragment.this.mListener.onFragmentInteraction(Onboard_2_Wait_For_Blue_Light_Fragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
        }
      }
    });
    if (BlinkApp.getApp().getLastSyncModuleId().length() > 0) {
      BlinkAPI.BlinkAPIRequest(null, null, new ResetSyncModuleRequest(), new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError)
        {
          BlinkApp.getApp().setlastSyncModuleId("");
        }
        
        public void onResult(BlinkData paramAnonymousBlinkData)
        {
          BlinkApp.getApp().setlastSyncModuleId("");
        }
      }, false);
    }
    return this.mView;
  }
  
  public void onResume()
  {
    super.onResume();
    if (needsWifiWatchdogDisabled()) {
      showWifiWatchdogDialog();
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/Onboard_2_Wait_For_Blue_Light_Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */