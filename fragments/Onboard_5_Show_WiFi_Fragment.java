package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.models.AccessPoint;
import com.immediasemi.blink.models.AccessPoints;

public class Onboard_5_Show_WiFi_Fragment
  extends BaseFragment
{
  public static final int ONBOARD_INDEX = 5;
  private AccessPoint[] mAccessPoints;
  private ListView mListView;
  private String mSMFirmwareVersion;
  private int mSectionNumber;
  private boolean mSupportsManualSSID;
  private View mView;
  
  private String encrpytionType(int paramInt)
  {
    String str = "";
    switch (paramInt)
    {
    }
    for (;;)
    {
      return str;
      str = "WPA2";
      continue;
      str = "WPA";
      continue;
      str = "WEP";
      continue;
      str = "none";
    }
  }
  
  public static Onboard_5_Show_WiFi_Fragment newInstance(int paramInt)
  {
    Onboard_5_Show_WiFi_Fragment localOnboard_5_Show_WiFi_Fragment = new Onboard_5_Show_WiFi_Fragment();
    Object localObject = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext());
    String str = "";
    if (((SharedPreferences)localObject).contains("AccessPoints")) {
      str = ((SharedPreferences)localObject).getString("AccessPoints", "");
    }
    localObject = new Bundle();
    ((Bundle)localObject).putInt("arg_section_number", paramInt);
    ((Bundle)localObject).putString("AccessPoints", str);
    localOnboard_5_Show_WiFi_Fragment.setArguments((Bundle)localObject);
    return localOnboard_5_Show_WiFi_Fragment;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131100003));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 5).commit();
    if (getArguments() != null)
    {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
      paramBundle = (AccessPoints)new Gson().fromJson(getArguments().getString("AccessPoints", ""), AccessPoints.class);
      if (paramBundle != null)
      {
        this.mAccessPoints = paramBundle.getAccessPoints();
        this.mSupportsManualSSID = paramBundle.getManual_ssid();
        this.mSMFirmwareVersion = paramBundle.getVersion();
      }
    }
    if (this.mAccessPoints == null) {
      this.mAccessPoints = new AccessPoint[0];
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903085, paramViewGroup, false);
    this.mListView = ((ListView)this.mView.findViewById(2131558551));
    if ((this.mAccessPoints != null) && (this.mAccessPoints.length > 0))
    {
      this.mListView.setAdapter(new ArrayAdapter(getActivity(), 2130903128, this.mAccessPoints)
      {
        public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
        {
          if (paramAnonymousView == null) {
            paramAnonymousView = View.inflate(Onboard_5_Show_WiFi_Fragment.this.getActivity(), 2130903128, null);
          }
          for (;;)
          {
            TextView localTextView3 = (TextView)paramAnonymousView.findViewById(2131558552);
            paramAnonymousViewGroup = (TextView)paramAnonymousView.findViewById(2131558733);
            TextView localTextView2 = (TextView)paramAnonymousView.findViewById(2131558734);
            TextView localTextView1 = (TextView)paramAnonymousView.findViewById(2131558735);
            AccessPoint localAccessPoint = Onboard_5_Show_WiFi_Fragment.this.mAccessPoints[paramAnonymousInt];
            localTextView3.setText(localAccessPoint.getSsid());
            paramAnonymousViewGroup.setText(localAccessPoint.getQuality());
            localTextView2.setText(localAccessPoint.getSignal());
            localTextView1.setText(localAccessPoint.getEncryption());
            return paramAnonymousView;
          }
        }
      });
      this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = Onboard_5_Show_WiFi_Fragment.this.mAccessPoints[paramAnonymousInt];
          paramAnonymousView = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit();
          paramAnonymousView.putString("arg_ssid", paramAnonymousAdapterView.getSsid());
          paramAnonymousView.putString("arg_encryption", paramAnonymousAdapterView.getEncryption());
          paramAnonymousView.commit();
          paramAnonymousAdapterView = Onboard_6_Enter_WiFi_Credentials_Fragment.newInstance(6);
          Onboard_5_Show_WiFi_Fragment.this.mListener.onFragmentInteraction(Onboard_5_Show_WiFi_Fragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousAdapterView);
        }
      });
    }
    paramLayoutInflater = (Button)this.mView.findViewById(2131558550);
    if (!this.mSupportsManualSSID) {
      paramLayoutInflater.setVisibility(4);
    }
    paramLayoutInflater.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        new AlertDialog.Builder(Onboard_5_Show_WiFi_Fragment.this.getActivity()).setTitle("Select security type").setItems(2131427331, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit();
            paramAnonymous2DialogInterface.putString("arg_ssid", "");
            paramAnonymous2DialogInterface.putString("arg_encryption", Onboard_5_Show_WiFi_Fragment.this.encrpytionType(paramAnonymous2Int));
            paramAnonymous2DialogInterface.commit();
            paramAnonymous2DialogInterface = Onboard_6_Enter_WiFi_Credentials_Fragment.newInstance(6);
            Onboard_5_Show_WiFi_Fragment.this.mListener.onFragmentInteraction(Onboard_5_Show_WiFi_Fragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymous2DialogInterface);
          }
        }).create().show();
      }
    });
    return this.mView;
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.mSMFirmwareVersion == null) {}
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/Onboard_5_Show_WiFi_Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */