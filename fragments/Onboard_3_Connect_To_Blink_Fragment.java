package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.gson.Gson;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Onboarding.OnboardBlinkRequest;
import com.immediasemi.blink.models.AccessPoints;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;

public class Onboard_3_Connect_To_Blink_Fragment
  extends BaseFragment
{
  private static final int NUMBER_OF_RETRIES = 12;
  public static final int ONBOARD_INDEX = 3;
  private static final int SYNC_POLLING_INTERVAL = 3000;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      removeMessages(0);
      BlinkApp.getApp().setOnboardingUrl("http://172.16.97.199");
      BlinkAPI.BlinkOnboardingRequest(null, null, new OnboardBlinkRequest(), new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymous2BlinkError)
        {
          BlinkApp.getApp().setOnboardingUrl(null);
          if ((Onboard_3_Connect_To_Blink_Fragment.this.getActivity() == null) || (Onboard_3_Connect_To_Blink_Fragment.this.mListener == null) || (Onboard_3_Connect_To_Blink_Fragment.this.isDetached())) {}
          do
          {
            return;
            if (Onboard_3_Connect_To_Blink_Fragment.this.mRetryCounter > 0) {
              break;
            }
          } while (Onboard_3_Connect_To_Blink_Fragment.this.getActivity() == null);
          new AlertDialog.Builder(Onboard_3_Connect_To_Blink_Fragment.this.getActivity()).setTitle("Error").setMessage("Could not connect to Sync Module").setPositiveButton(2131099890, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
            {
              paramAnonymous3DialogInterface = Onboard_2_Wait_For_Blue_Light_Fragment.newInstance(2);
              Onboard_3_Connect_To_Blink_Fragment.this.mListener.onFragmentInteraction(Onboard_3_Connect_To_Blink_Fragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymous3DialogInterface);
            }
          }).create().show();
          return;
          Onboard_3_Connect_To_Blink_Fragment.this.handler.sendEmptyMessageDelayed(0, 3000L);
        }
        
        public void onResult(BlinkData paramAnonymous2BlinkData)
        {
          BlinkApp.getApp().setOnboardingUrl(null);
          if ((Onboard_3_Connect_To_Blink_Fragment.this.getActivity() == null) || (Onboard_3_Connect_To_Blink_Fragment.this.mListener == null) || (Onboard_3_Connect_To_Blink_Fragment.this.isDetached())) {}
          do
          {
            return;
            Onboard_3_Connect_To_Blink_Fragment.access$102(Onboard_3_Connect_To_Blink_Fragment.this, (AccessPoints)paramAnonymous2BlinkData);
          } while ((Onboard_3_Connect_To_Blink_Fragment.this.mPaused) || (Onboard_3_Connect_To_Blink_Fragment.this.mListener == null));
          Onboard_3_Connect_To_Blink_Fragment.this.gotoNextScreen();
        }
      }, 3000);
    }
  };
  private AccessPoints mAccessPoints;
  private boolean mPaused;
  private int mRetryCounter;
  private int mSectionNumber;
  private View mView;
  private boolean mWifiSettingsDisplayed = false;
  
  private void gotoNextScreen()
  {
    if (this.mListener == null) {
      return;
    }
    Onboard_4_Successful_Connect_Fragment localOnboard_4_Successful_Connect_Fragment = Onboard_4_Successful_Connect_Fragment.newInstance(4);
    SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit();
    localEditor.putString("AccessPoints", new Gson().toJson(this.mAccessPoints));
    localEditor.commit();
    this.mListener.onFragmentInteraction(this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, localOnboard_4_Successful_Connect_Fragment);
  }
  
  public static Onboard_3_Connect_To_Blink_Fragment newInstance(int paramInt)
  {
    Onboard_3_Connect_To_Blink_Fragment localOnboard_3_Connect_To_Blink_Fragment = new Onboard_3_Connect_To_Blink_Fragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localOnboard_3_Connect_To_Blink_Fragment.setArguments(localBundle);
    return localOnboard_3_Connect_To_Blink_Fragment;
  }
  
  private void openWifiSettings()
  {
    this.mWifiSettingsDisplayed = true;
    startActivity(new Intent("android.settings.WIFI_SETTINGS"));
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099987));
    this.handler.sendEmptyMessageDelayed(0, 100L);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 3).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
    this.mRetryCounter = 12;
  }
  
  public View onCreateView(final LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903079, paramViewGroup, false);
    paramLayoutInflater = (Button)this.mView.findViewById(2131558542);
    paramLayoutInflater.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Onboard_3_Connect_To_Blink_Fragment.this.openWifiSettings();
        paramLayoutInflater.setVisibility(4);
      }
    });
    return this.mView;
  }
  
  public void onDetach()
  {
    super.onDetach();
    BlinkApp.getApp().setOnboardingUrl(null);
  }
  
  public void onPause()
  {
    super.onPause();
    this.mPaused = true;
  }
  
  public void onResume()
  {
    super.onResume();
    this.mRetryCounter = 12;
    this.mPaused = false;
    if (this.mAccessPoints != null) {
      gotoNextScreen();
    }
    while ((getActivity() == null) || (this.mListener == null) || (isDetached())) {
      return;
    }
    this.handler.sendEmptyMessageDelayed(0, 3000L);
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/fragments/Onboard_3_Connect_To_Blink_Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */