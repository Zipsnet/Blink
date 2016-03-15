package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.gson.Gson;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.models.AccessPoints;
import com.immediasemi.blink.utils.OnClick;

public class Onboard_4_Successful_Connect_Fragment
  extends BaseFragment
{
  public static final String ACCESS_POINTS_ARG = "AccessPoints";
  public static final int ONBOARD_INDEX = 4;
  private AccessPoints mAccessPoints;
  private int mSectionNumber;
  private View mView;
  
  public static Onboard_4_Successful_Connect_Fragment newInstance(int paramInt)
  {
    Onboard_4_Successful_Connect_Fragment localOnboard_4_Successful_Connect_Fragment = new Onboard_4_Successful_Connect_Fragment();
    Object localObject = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext());
    String str = "";
    if (((SharedPreferences)localObject).contains("AccessPoints")) {
      str = ((SharedPreferences)localObject).getString("AccessPoints", "");
    }
    localObject = new Bundle();
    ((Bundle)localObject).putInt("arg_section_number", paramInt);
    ((Bundle)localObject).putString("AccessPoints", str);
    localOnboard_4_Successful_Connect_Fragment.setArguments((Bundle)localObject);
    return localOnboard_4_Successful_Connect_Fragment;
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    paramBundle = Onboard_5_Show_WiFi_Fragment.newInstance(5);
    this.mListener.onFragmentInteraction(this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramBundle);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131100000));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 4).commit();
    if (getArguments() != null)
    {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
      this.mAccessPoints = ((AccessPoints)new Gson().fromJson(getArguments().getString("AccessPoints", ""), AccessPoints.class));
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903080, paramViewGroup, false);
    ((Button)this.mView.findViewById(2131558545)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        paramAnonymousView = Onboard_5_Show_WiFi_Fragment.newInstance(5);
        Onboard_4_Successful_Connect_Fragment.this.mListener.onFragmentInteraction(Onboard_4_Successful_Connect_Fragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
      }
    });
    return this.mView;
  }
  
  public void setAccessPoints(AccessPoints paramAccessPoints)
  {
    this.mAccessPoints = paramAccessPoints;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/fragments/Onboard_4_Successful_Connect_Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */