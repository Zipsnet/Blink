package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.immediasemi.blink.activities.BaseActivity;

public class AdvancedSettingsFragment
  extends BaseFragment
{
  private int mSectionNumber;
  private View mView;
  
  public static AdvancedSettingsFragment newInstance(int paramInt)
  {
    AdvancedSettingsFragment localAdvancedSettingsFragment = new AdvancedSettingsFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAdvancedSettingsFragment.setArguments(localBundle);
    return localAdvancedSettingsFragment;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099997));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903097, paramViewGroup, false);
    return this.mView;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/AdvancedSettingsFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */