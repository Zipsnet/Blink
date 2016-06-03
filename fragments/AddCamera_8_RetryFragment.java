package com.immediasemi.blink.fragments;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.utils.OnClick;

public class AddCamera_8_RetryFragment
  extends BaseFragment
{
  public static final int ADD_CAMERA_INDEX = 8;
  private int mSectionNumber;
  private View mView;
  
  public static AddCamera_8_RetryFragment newInstance(int paramInt)
  {
    AddCamera_8_RetryFragment localAddCamera_8_RetryFragment = new AddCamera_8_RetryFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAddCamera_8_RetryFragment.setArguments(localBundle);
    return localAddCamera_8_RetryFragment;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 8).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903114, paramViewGroup, false);
    ((Button)this.mView.findViewById(2131558559)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          paramAnonymousView = AddCamera_1_Explanation.newInstance(1);
          AddCamera_8_RetryFragment.this.mListener.onFragmentInteraction(AddCamera_8_RetryFragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
        }
      }
    });
    return this.mView;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/AddCamera_8_RetryFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */