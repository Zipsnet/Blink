package com.immediasemi.blink.fragments;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.models.Command;
import com.immediasemi.blink.utils.OnClick;

public class AddCamera_7_AddCameraFragment
  extends BaseFragment
{
  public static final int ADD_CAMERA_INDEX = 7;
  private static final int POLL_FOR_THUMBNAIL = 1;
  private static final int POLL_INTERVAL = 1000;
  private Command mCommandResponse;
  private boolean mPolling;
  private int mSectionNumber;
  private ImageView mTakeSnapImage;
  private View mView;
  
  public static AddCamera_7_AddCameraFragment newInstance(int paramInt)
  {
    AddCamera_7_AddCameraFragment localAddCamera_7_AddCameraFragment = new AddCamera_7_AddCameraFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAddCamera_7_AddCameraFragment.setArguments(localBundle);
    return localAddCamera_7_AddCameraFragment;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 7).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903092, paramViewGroup, false);
    ((ImageView)this.mView.findViewById(2131558588)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        paramAnonymousView = AddCamera_1_Explanation.newInstance(-1);
        AddCamera_7_AddCameraFragment.this.mListener.onFragmentInteraction(AddCamera_7_AddCameraFragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
      }
    });
    ((Button)this.mView.findViewById(2131558547)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().remove("add_camera_sequence").commit();
        AddCamera_7_AddCameraFragment.this.getActivity().setResult(-1);
        AddCamera_7_AddCameraFragment.this.getActivity().finish();
      }
    });
    return this.mView;
  }
  
  public void onDetach()
  {
    super.onDetach();
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/AddCamera_7_AddCameraFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */