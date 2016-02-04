package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
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
  private int mSectionNumber;
  private View mView;
  
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
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099996));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 5).commit();
    if (getArguments() != null)
    {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
      paramBundle = (AccessPoints)new Gson().fromJson(getArguments().getString("AccessPoints", ""), AccessPoints.class);
      if (paramBundle != null) {
        this.mAccessPoints = paramBundle.getAccessPoints();
      }
    }
    if (this.mAccessPoints == null) {
      this.mAccessPoints = new AccessPoint[0];
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903081, paramViewGroup, false);
    this.mListView = ((ListView)this.mView.findViewById(2131558544));
    if ((this.mAccessPoints != null) && (this.mAccessPoints.length > 0))
    {
      this.mListView.setAdapter(new ArrayAdapter(getActivity(), 2130903124, this.mAccessPoints)
      {
        public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
        {
          if (paramAnonymousView == null) {
            paramAnonymousView = View.inflate(Onboard_5_Show_WiFi_Fragment.this.getActivity(), 2130903124, null);
          }
          for (;;)
          {
            paramAnonymousViewGroup = (TextView)paramAnonymousView.findViewById(2131558545);
            TextView localTextView1 = (TextView)paramAnonymousView.findViewById(2131558720);
            TextView localTextView2 = (TextView)paramAnonymousView.findViewById(2131558721);
            TextView localTextView3 = (TextView)paramAnonymousView.findViewById(2131558722);
            AccessPoint localAccessPoint = Onboard_5_Show_WiFi_Fragment.this.mAccessPoints[paramAnonymousInt];
            paramAnonymousViewGroup.setText(localAccessPoint.getSsid());
            localTextView1.setText(localAccessPoint.getQuality());
            localTextView2.setText(localAccessPoint.getSignal());
            localTextView3.setText(localAccessPoint.getEncryption());
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
    return this.mView;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/Onboard_5_Show_WiFi_Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */