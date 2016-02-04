package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Cameras.GetAllCamerasStatusRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.DeviceStatus;
import com.immediasemi.blink.models.DeviceStatuses;

public class AddCamera_3_WaitForCameraFragment
  extends BaseFragment
{
  public static final int ADD_CAMERA_INDEX = 3;
  private static final int MAX_RETRIES = 10;
  private static final int ONBOARD_POLLING_INTERVAL = 5000;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      removeMessages(0);
      BlinkAPI.BlinkAPIRequest(null, null, new GetAllCamerasStatusRequest(), new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymous2BlinkError)
        {
          BlinkApp.getApp().setOnboardingUrl(null);
          if ((AddCamera_3_WaitForCameraFragment.this.getActivity() == null) || (AddCamera_3_WaitForCameraFragment.this.mListener == null) || (AddCamera_3_WaitForCameraFragment.this.isDetached())) {
            AddCamera_3_WaitForCameraFragment.this.tryAgain();
          }
          do
          {
            return;
            if (AddCamera_3_WaitForCameraFragment.access$106(AddCamera_3_WaitForCameraFragment.this) > 0) {
              break;
            }
          } while (AddCamera_3_WaitForCameraFragment.this.getActivity() == null);
          AddCamera_3_WaitForCameraFragment.this.tryAgain();
          return;
          AddCamera_3_WaitForCameraFragment.this.handler.sendEmptyMessageDelayed(0, 5000L);
        }
        
        public void onResult(BlinkData paramAnonymous2BlinkData)
        {
          paramAnonymous2BlinkData = ((DeviceStatuses)paramAnonymous2BlinkData).getDevicestatus();
          int j = Integer.valueOf(BlinkApp.getApp().getLastCameraId()).intValue();
          int i = 0;
          if (i < paramAnonymous2BlinkData.length)
          {
            Object localObject = paramAnonymous2BlinkData[i];
            if (((DeviceStatus)localObject).getCamera_id() == j)
            {
              BlinkApp.getApp().setLastCameraId(String.valueOf(((DeviceStatus)localObject).getCamera_id()));
              AddCamera_3_WaitForCameraFragment.this.success();
            }
          }
          do
          {
            return;
            i += 1;
            break;
            if (AddCamera_3_WaitForCameraFragment.access$106(AddCamera_3_WaitForCameraFragment.this) > 0) {
              break label111;
            }
          } while (AddCamera_3_WaitForCameraFragment.this.getActivity() == null);
          AddCamera_3_WaitForCameraFragment.this.tryAgain();
          return;
          label111:
          if ((AddCamera_3_WaitForCameraFragment.this.getActivity() == null) || (AddCamera_3_WaitForCameraFragment.this.mListener == null) || (AddCamera_3_WaitForCameraFragment.this.isDetached()))
          {
            AddCamera_3_WaitForCameraFragment.this.tryAgain();
            return;
          }
          AddCamera_3_WaitForCameraFragment.this.handler.sendEmptyMessageDelayed(0, 5000L);
        }
      }, false);
    }
  };
  private int mRetryCounter;
  private int mSectionNumber;
  private View mView;
  private ContentLoadingProgressBar pd;
  
  public static AddCamera_3_WaitForCameraFragment newInstance(int paramInt)
  {
    AddCamera_3_WaitForCameraFragment localAddCamera_3_WaitForCameraFragment = new AddCamera_3_WaitForCameraFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAddCamera_3_WaitForCameraFragment.setArguments(localBundle);
    return localAddCamera_3_WaitForCameraFragment;
  }
  
  private void success()
  {
    this.pd.setVisibility(4);
    if ((getActivity() == null) || (this.mListener == null) || (isDetached()))
    {
      PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 4).commit();
      return;
    }
    AddCamera_4_SuccessFragment localAddCamera_4_SuccessFragment = AddCamera_4_SuccessFragment.newInstance(-1);
    this.mListener.onFragmentInteraction(this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, localAddCamera_4_SuccessFragment);
  }
  
  private void tryAgain()
  {
    if ((getActivity() == null) || (this.mListener == null) || (isDetached()))
    {
      PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 8).commit();
      return;
    }
    AddCamera_8_RetryFragment localAddCamera_8_RetryFragment = AddCamera_8_RetryFragment.newInstance(-1);
    this.mListener.onFragmentInteraction(this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, localAddCamera_8_RetryFragment);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099983));
    this.handler.sendEmptyMessageDelayed(0, 100L);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 3).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
    this.mRetryCounter = 10;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903088, paramViewGroup, false);
    this.pd = ((ContentLoadingProgressBar)this.mView.findViewById(2131558538));
    this.pd.setVisibility(0);
    return this.mView;
  }
  
  public void onDetach()
  {
    this.pd.setVisibility(4);
    super.onDetach();
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/AddCamera_3_WaitForCameraFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */