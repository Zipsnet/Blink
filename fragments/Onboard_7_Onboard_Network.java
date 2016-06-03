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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.SyncModules.GetAllSyncModulesRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.QuickSyncModuleInfo;
import com.immediasemi.blink.models.SyncModules;
import com.immediasemi.blink.utils.OnClick;
import java.util.HashMap;

public class Onboard_7_Onboard_Network
  extends BaseFragment
{
  private static final int MAX_RETRIES = 60;
  public static final int ONBOARD_INDEX = 7;
  private static final int ONBOARD_POLLING_INTERVAL = 1000;
  private StaticHandler handler = new StaticHandler(null);
  private int mRetryCounter;
  private int mSectionNumber;
  private View mView;
  
  private void getAllSyncModules()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new GetAllSyncModulesRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        BlinkApp.getApp().setOnboardingUrl(null);
        if ((Onboard_7_Onboard_Network.this.getActivity() == null) || (Onboard_7_Onboard_Network.this.mListener == null) || (Onboard_7_Onboard_Network.this.isDetached())) {}
        for (;;)
        {
          return;
          if (Onboard_7_Onboard_Network.access$206(Onboard_7_Onboard_Network.this) <= 0)
          {
            if (Onboard_7_Onboard_Network.this.getActivity() != null)
            {
              if (paramAnonymousBlinkError.response != null) {}
              for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = "Could not add Sync Module")
              {
                new AlertDialog.Builder(Onboard_7_Onboard_Network.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099865, new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                  {
                    Onboard_7_Onboard_Network.access$202(Onboard_7_Onboard_Network.this, 60);
                    paramAnonymous2DialogInterface = Onboard_7_Onboard_Network.this.handler.obtainMessage(0, Onboard_7_Onboard_Network.this);
                    Onboard_7_Onboard_Network.this.handler.sendMessageDelayed(paramAnonymous2DialogInterface, 100L);
                  }
                }).setNegativeButton(2131099778, new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                  {
                    Onboard_7_Onboard_Network.this.getActivity().finish();
                  }
                }).create().show();
                break;
              }
            }
          }
          else
          {
            paramAnonymousBlinkError = Onboard_7_Onboard_Network.this.handler.obtainMessage(0, Onboard_7_Onboard_Network.this);
            Onboard_7_Onboard_Network.this.handler.sendMessageDelayed(paramAnonymousBlinkError, 1000L);
          }
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((Onboard_7_Onboard_Network.this.getActivity() == null) || (Onboard_7_Onboard_Network.this.mListener == null) || (Onboard_7_Onboard_Network.this.isDetached())) {}
        for (;;)
        {
          return;
          paramAnonymousBlinkData = ((SyncModules)paramAnonymousBlinkData).getSyncmodule();
          if ((Onboard_7_Onboard_Network.access$206(Onboard_7_Onboard_Network.this) <= 0) || (paramAnonymousBlinkData != null)) {
            break;
          }
          BlinkApp.getApp().setOnboardingUrl(null);
          paramAnonymousBlinkData = Onboard_7_Onboard_Network.this.handler.obtainMessage(0, Onboard_7_Onboard_Network.this);
          Onboard_7_Onboard_Network.this.handler.sendMessageDelayed(paramAnonymousBlinkData, 1000L);
        }
        Onboard_8_Setup_Complete localOnboard_8_Setup_Complete = Onboard_8_Setup_Complete.newInstance(8);
        if (Onboard_7_Onboard_Network.this.mRetryCounter <= 0) {
          localOnboard_8_Setup_Complete.mSMConnectedSuccessfully = false;
        }
        for (;;)
        {
          Onboard_7_Onboard_Network.this.mListener.onFragmentInteraction(Onboard_7_Onboard_Network.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, localOnboard_8_Setup_Complete);
          break;
          localOnboard_8_Setup_Complete.mSMConnectedSuccessfully = true;
          int i = paramAnonymousBlinkData.getId();
          BlinkApp.getApp().setlastSyncModuleId(String.valueOf(i));
        }
      }
    }, false);
  }
  
  public static Onboard_7_Onboard_Network newInstance(int paramInt)
  {
    Onboard_7_Onboard_Network localOnboard_7_Onboard_Network = new Onboard_7_Onboard_Network();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localOnboard_7_Onboard_Network.setArguments(localBundle);
    return localOnboard_7_Onboard_Network;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099990));
    paramActivity = this.handler.obtainMessage(0, this);
    this.handler.sendMessageDelayed(paramActivity, 100L);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 7).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
    this.mRetryCounter = 60;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903087, paramViewGroup, false);
    paramLayoutInflater = (Button)this.mView.findViewById(2131558557);
    paramLayoutInflater.setVisibility(4);
    paramLayoutInflater.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          Onboard_7_Onboard_Network.this.getActivity().finish();
        }
      }
    });
    return this.mView;
  }
  
  private static class StaticHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      removeMessages(0);
      ((Onboard_7_Onboard_Network)paramMessage.obj).getAllSyncModules();
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/Onboard_7_Onboard_Network.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */