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
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.utils.OnClick;

public class Onboard_1_Fragment
  extends BaseFragment
{
  public static final int ONBOARD_INDEX = 1;
  private int mSectionNumber;
  private View mView;
  
  public static Onboard_1_Fragment newInstance(int paramInt)
  {
    Onboard_1_Fragment localOnboard_1_Fragment = new Onboard_1_Fragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localOnboard_1_Fragment.setArguments(localBundle);
    return localOnboard_1_Fragment;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099976));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 1).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903108, paramViewGroup, false);
    ((Button)this.mView.findViewById(2131558660)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        paramAnonymousView = Onboard_2_Wait_For_Blue_Light_Fragment.newInstance(2);
        Onboard_1_Fragment.this.mListener.onFragmentInteraction(Onboard_1_Fragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
      }
    });
    return this.mView;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/Onboard_1_Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */