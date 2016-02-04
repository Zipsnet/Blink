package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.activities.MainActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Onboarding.OnboardingDoneRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.utils.OnClick;

public class Onboard_8_Setup_Complete
  extends BaseFragment
{
  public static final int ONBOARD_INDEX = 8;
  public boolean mSMConnectedSuccessfully = false;
  private int mSectionNumber;
  private View mView;
  
  public static Onboard_8_Setup_Complete newInstance(int paramInt)
  {
    Onboard_8_Setup_Complete localOnboard_8_Setup_Complete = new Onboard_8_Setup_Complete();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localOnboard_8_Setup_Complete.setArguments(localBundle);
    return localOnboard_8_Setup_Complete;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099983));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 8).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903084, paramViewGroup, false);
    BlinkApp.getApp().setOnboardingUrl("http://172.16.97.199");
    BlinkAPI.BlinkOnboardingRequest(null, null, new OnboardingDoneRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError) {}
      
      public void onResult(BlinkData paramAnonymousBlinkData) {}
    }, 2000);
    BlinkApp.getApp().setOnboardingUrl(null);
    paramLayoutInflater = (Button)this.mView.findViewById(2131558552);
    if (this.mSMConnectedSuccessfully) {
      paramLayoutInflater.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!OnClick.ok()) {
            return;
          }
          PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().remove("onboard_reason").commit();
          Onboard_8_Setup_Complete.this.getActivity().setResult(-1);
          paramAnonymousView = new Intent(Onboard_8_Setup_Complete.this.getActivity(), MainActivity.class);
          paramAnonymousView.setFlags(67108864);
          Onboard_8_Setup_Complete.this.startActivity(paramAnonymousView);
          Onboard_8_Setup_Complete.this.getActivity().finish();
        }
      });
    }
    for (;;)
    {
      return this.mView;
      paramLayoutInflater.setText("Retry");
      ((TextView)this.mView.findViewById(2131558551)).setText(2131099900);
      paramLayoutInflater.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!OnClick.ok()) {
            return;
          }
          paramAnonymousView = Onboard_2_Wait_For_Blue_Light_Fragment.newInstance(2);
          Onboard_8_Setup_Complete.this.mListener.onFragmentInteraction(Onboard_8_Setup_Complete.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
        }
      });
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/Onboard_8_Setup_Complete.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */