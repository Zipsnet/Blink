package com.immediasemi.blink.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Onboarding.OnboardingDoneRequest;
import com.immediasemi.blink.fragments.Onboard_1_Fragment;
import com.immediasemi.blink.fragments.Onboard_2_Wait_For_Blue_Light_Fragment;
import com.immediasemi.blink.fragments.Onboard_3_Connect_To_Blink_Fragment;
import com.immediasemi.blink.fragments.Onboard_4_Successful_Connect_Fragment;
import com.immediasemi.blink.fragments.Onboard_5_Show_WiFi_Fragment;
import com.immediasemi.blink.fragments.Onboard_6_Enter_WiFi_Credentials_Fragment;
import com.immediasemi.blink.fragments.Onboard_7_Onboard_Network;
import com.immediasemi.blink.fragments.Onboard_8_Setup_Complete;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;

public class OnboardingActivity
  extends BaseActivity
{
  public static final String ONBOARD_REASON = "onboard_reason";
  public static final int ONBOARD_REASON_INITIAL_CREATE_ACCOUNT = 1;
  public static final int ONBOARD_REASON_START_ONBOARD_FROM_MAIN = 2;
  public static final String ONBOARD_SEQUENCE = "onboard_sequence";
  private ActionBar actionbar;
  private int mCurrentOnboardFragment = 0;
  
  private void cancelSMSetup()
  {
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).getInt("onboard_sequence", -1);
    getSupportFragmentManager();
    goToHomeScreen();
  }
  
  private void goToHomeScreen()
  {
    setResult(-1);
    Intent localIntent = new Intent(this, MainActivity.class);
    localIntent.setFlags(67108864);
    startActivity(localIntent);
  }
  
  private void killSMAP()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new OnboardingDoneRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        OnboardingActivity.this.goToHomeScreen();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        OnboardingActivity.this.goToHomeScreen();
      }
    }, false);
  }
  
  private void logMeOut()
  {
    BlinkApp.getApp().setLoginAuthToken("");
    BlinkApp.getApp().setUserName("");
    BlinkApp.getApp().setPassword("");
    Intent localIntent = new Intent(this, SplashActivity.class);
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    finish();
  }
  
  public void onBackPressed()
  {
    cancelSMSetup();
    super.onBackPressed();
  }
  
  public void onCancelClicked(View paramView)
  {
    cancelSMSetup();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903074);
    paramBundle = getSupportFragmentManager();
    Object localObject = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext());
    if (((SharedPreferences)localObject).contains("onboard_sequence")) {
      this.mCurrentOnboardFragment = ((SharedPreferences)localObject).getInt("onboard_sequence", 0);
    }
    if (this.mCurrentOnboardFragment <= 0)
    {
      localObject = getIntent().getExtras();
      if (localObject != null) {
        this.mCurrentOnboardFragment = ((Bundle)localObject).getInt("onboard_reason", 0);
      }
    }
    switch (this.mCurrentOnboardFragment)
    {
    default: 
      paramBundle.beginTransaction().replace(2131558537, Onboard_1_Fragment.newInstance(-1)).commit();
    }
    for (;;)
    {
      this.actionbar = getSupportActionBar();
      this.actionbar.setDisplayHomeAsUpEnabled(false);
      this.actionbar.setDisplayOptions(16);
      paramBundle = getLayoutInflater().inflate(2130903080, null);
      this.actionbar.setCustomView(paramBundle);
      this.actionbar.setTitle(2131099990);
      setResult(0);
      return;
      paramBundle.beginTransaction().replace(2131558537, Onboard_2_Wait_For_Blue_Light_Fragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558537, Onboard_3_Connect_To_Blink_Fragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558537, Onboard_4_Successful_Connect_Fragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558537, Onboard_5_Show_WiFi_Fragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558537, Onboard_6_Enter_WiFi_Credentials_Fragment.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558537, Onboard_7_Onboard_Network.newInstance(-1)).commit();
      continue;
      paramBundle.beginTransaction().replace(2131558537, Onboard_8_Setup_Complete.newInstance(-1)).commit();
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
  
  protected void onStop()
  {
    super.onStop();
    this.mCurrentOnboardFragment = -1;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/OnboardingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */