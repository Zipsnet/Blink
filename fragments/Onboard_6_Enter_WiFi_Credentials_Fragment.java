package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Onboarding.SetSSIDRequest;
import com.immediasemi.blink.api.requests.Onboarding.SetSSIDRequest.SSId;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.utils.OnClick;
import java.util.HashMap;
import java.util.Map;

public class Onboard_6_Enter_WiFi_Credentials_Fragment
  extends BaseFragment
{
  public static final String ARG_ENCRYPTION = "arg_encryption";
  public static final String ARG_SSID = "arg_ssid";
  public static final int ONBOARD_INDEX = 6;
  private String mEncryption;
  private String mPassPhrase;
  private EditText mPassPhraseEntry;
  private String mSSID;
  private EditText mSSIDEntry;
  private int mSectionNumber;
  private View mView;
  
  public static Onboard_6_Enter_WiFi_Credentials_Fragment newInstance(int paramInt)
  {
    Onboard_6_Enter_WiFi_Credentials_Fragment localOnboard_6_Enter_WiFi_Credentials_Fragment = new Onboard_6_Enter_WiFi_Credentials_Fragment();
    Object localObject = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext());
    String str1 = ((SharedPreferences)localObject).getString("arg_ssid", "");
    String str2 = ((SharedPreferences)localObject).getString("arg_encryption", "");
    localObject = new Bundle();
    ((Bundle)localObject).putInt("arg_section_number", paramInt);
    ((Bundle)localObject).putString("arg_ssid", str1);
    ((Bundle)localObject).putString("arg_encryption", str2);
    localOnboard_6_Enter_WiFi_Credentials_Fragment.setArguments((Bundle)localObject);
    return localOnboard_6_Enter_WiFi_Credentials_Fragment;
  }
  
  private void setAccessPoint()
  {
    this.mSSID = this.mSSIDEntry.getText().toString();
    this.mPassPhrase = this.mPassPhraseEntry.getText().toString();
    SetSSIDRequest localSetSSIDRequest = new SetSSIDRequest();
    localSetSSIDRequest.setDns("name");
    localSetSSIDRequest.setDomain(BlinkApp.getApp().getDnsSubdomain());
    Object localObject = new SetSSIDRequest.SSId[1];
    localObject[0] = new SetSSIDRequest.SSId();
    localObject[0].setSsid(this.mSSID);
    localObject[0].setPassword(this.mPassPhrase);
    localObject[0].setEncryption(this.mEncryption);
    localSetSSIDRequest.setSsids((SetSSIDRequest.SSId[])localObject);
    localObject = new HashMap();
    ((HashMap)localObject).put("Cookie", BlinkApp.getApp().getLoginAuthToken());
    BlinkApp.getApp().setOnboardingUrl("http://172.16.97.199");
    BlinkAPI.BlinkOnboardingRequest(null, (Map)localObject, localSetSSIDRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        BlinkApp.getApp().setOnboardingUrl(null);
        if ((Onboard_6_Enter_WiFi_Credentials_Fragment.this.getActivity() == null) || (Onboard_6_Enter_WiFi_Credentials_Fragment.this.mListener == null) || (Onboard_6_Enter_WiFi_Credentials_Fragment.this.isDetached())) {}
        for (;;)
        {
          return;
          if (Onboard_6_Enter_WiFi_Credentials_Fragment.this.getActivity() != null) {
            new AlertDialog.Builder(Onboard_6_Enter_WiFi_Credentials_Fragment.this.getActivity()).setTitle("Error").setMessage("Could not connect to WiFi").setPositiveButton(2131099891, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                paramAnonymous2DialogInterface = Onboard_5_Show_WiFi_Fragment.newInstance(5);
                Onboard_6_Enter_WiFi_Credentials_Fragment.this.mListener.onFragmentInteraction(Onboard_6_Enter_WiFi_Credentials_Fragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymous2DialogInterface);
              }
            }).create().show();
          }
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        BlinkApp.getApp().setOnboardingUrl(null);
        if (Onboard_6_Enter_WiFi_Credentials_Fragment.this.isDetached()) {}
        for (;;)
        {
          return;
          ((InputMethodManager)Onboard_6_Enter_WiFi_Credentials_Fragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(Onboard_6_Enter_WiFi_Credentials_Fragment.this.mSSIDEntry.getWindowToken(), 0);
          paramAnonymousBlinkData = Onboard_7_Onboard_Network.newInstance(7);
          if ((Onboard_6_Enter_WiFi_Credentials_Fragment.this.getActivity() != null) && (Onboard_6_Enter_WiFi_Credentials_Fragment.this.mListener != null) && (!Onboard_6_Enter_WiFi_Credentials_Fragment.this.isDetached())) {
            Onboard_6_Enter_WiFi_Credentials_Fragment.this.mListener.onFragmentInteraction(Onboard_6_Enter_WiFi_Credentials_Fragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousBlinkData);
          }
        }
      }
    }, 25000);
    BlinkApp.getApp().setOnboardingUrl(null);
  }
  
  public void hideKeyboard()
  {
    ((InputMethodManager)getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mSSIDEntry.getWindowToken(), 0);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099990));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 6).commit();
    if (getArguments() != null)
    {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
      this.mSSID = getArguments().getString("arg_ssid");
      this.mEncryption = getArguments().getString("arg_encryption");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903086, paramViewGroup, false);
    this.mSSIDEntry = ((EditText)this.mView.findViewById(2131558552));
    this.mSSIDEntry.setText(this.mSSID);
    this.mSSIDEntry.setFocusable(this.mSSID.equals(""));
    this.mPassPhraseEntry = ((EditText)this.mView.findViewById(2131558553));
    this.mPassPhraseEntry.setTransformationMethod(null);
    this.mPassPhraseEntry.requestFocus();
    ((InputMethodManager)getActivity().getSystemService("input_method")).toggleSoftInput(2, 1);
    this.mPassPhraseEntry.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (((paramAnonymousKeyEvent != null) && (paramAnonymousKeyEvent.getKeyCode() == 66)) || (paramAnonymousInt == 6)) {
          Onboard_6_Enter_WiFi_Credentials_Fragment.this.setAccessPoint();
        }
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
    });
    if (this.mEncryption.equalsIgnoreCase("none")) {
      setAccessPoint();
    }
    for (;;)
    {
      return this.mView;
      ((Button)this.mView.findViewById(2131558554)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!OnClick.ok()) {}
          for (;;)
          {
            return;
            Onboard_6_Enter_WiFi_Credentials_Fragment.this.setAccessPoint();
          }
        }
      });
      this.mSSIDEntry.clearFocus();
      new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          super.handleMessage(paramAnonymousMessage);
          if (Onboard_6_Enter_WiFi_Credentials_Fragment.this.mSSIDEntry.requestFocus()) {
            ((InputMethodManager)Onboard_6_Enter_WiFi_Credentials_Fragment.this.getActivity().getSystemService("input_method")).showSoftInput(Onboard_6_Enter_WiFi_Credentials_Fragment.this.mSSIDEntry, 1);
          }
          Onboard_6_Enter_WiFi_Credentials_Fragment.this.mSSIDEntry.setSelection(Onboard_6_Enter_WiFi_Credentials_Fragment.this.mSSIDEntry.getText().length());
        }
      }.sendEmptyMessageDelayed(0, 250L);
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/Onboard_6_Enter_WiFi_Credentials_Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */