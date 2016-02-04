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

public class AddCamera_1_Explanation
  extends BaseFragment
{
  public static final int ADD_CAMERA_INDEX = 1;
  private int mSectionNumber;
  private View mView;
  
  public static AddCamera_1_Explanation newInstance(int paramInt)
  {
    AddCamera_1_Explanation localAddCamera_1_Explanation = new AddCamera_1_Explanation();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAddCamera_1_Explanation.setArguments(localBundle);
    return localAddCamera_1_Explanation;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 1).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903086, paramViewGroup, false);
    ((Button)this.mView.findViewById(2131558552)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        paramAnonymousView = AddCamera_2_Fragment.newInstance(-1);
        AddCamera_1_Explanation.this.mListener.onFragmentInteraction(AddCamera_1_Explanation.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
      }
    });
    return this.mView;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/AddCamera_1_Explanation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */