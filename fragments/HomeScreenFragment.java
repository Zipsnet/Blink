package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.AddCameraActivity;
import com.immediasemi.blink.activities.MainActivity;
import com.immediasemi.blink.activities.OnboardingActivity;
import com.immediasemi.blink.activities.VideoListViewActivity;
import com.immediasemi.blink.activities.VideoLiveViewActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.api.requests.Cameras.DisableCameraAlertsRequest;
import com.immediasemi.blink.api.requests.Cameras.EnableCameraAlertsRequest;
import com.immediasemi.blink.api.requests.Cameras.GetSingleCameraStatusRequest;
import com.immediasemi.blink.api.requests.Cameras.TakeSnapshotRequest;
import com.immediasemi.blink.api.requests.Command.CommandRequest;
import com.immediasemi.blink.api.requests.Homescreen.HomescreenRequest;
import com.immediasemi.blink.api.requests.Networks.ArmNetworkRequest;
import com.immediasemi.blink.api.requests.Networks.DisarmNetworkRequest;
import com.immediasemi.blink.api.requests.Networks.ListNetworksRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.CameraStatus;
import com.immediasemi.blink.models.Command;
import com.immediasemi.blink.models.Commands;
import com.immediasemi.blink.models.DeviceStatus;
import com.immediasemi.blink.models.HomescreenSummaryStatus;
import com.immediasemi.blink.models.Network;
import com.immediasemi.blink.models.Networks;
import com.immediasemi.blink.models.QuickDeviceStatus;
import com.immediasemi.blink.models.QuickDeviceStatus.DEVICE_STATUS;
import com.immediasemi.blink.models.QuickDeviceStatus.DEVICE_TYPE;
import com.immediasemi.blink.models.QuickNetworkStatus;
import com.immediasemi.blink.utils.CustomSwitch;
import com.immediasemi.blink.utils.ImageLoader;
import com.immediasemi.blink.utils.OnClick;
import java.util.ArrayList;

public class HomeScreenFragment
  extends BaseFragment
{
  private static final int COMMAND_POLL_FOR_ARM_DISARM = 5;
  private static final int COMMAND_POLL_FOR_CAMERA_DISABLE = 3;
  private static final int COMMAND_POLL_FOR_CAMERA_ENABLE = 2;
  private static final int COMMAND_POLL_FOR_CAMERA_SETTINGS = 4;
  private static final int COMMAND_POLL_FOR_CAMERA_THUMBNAIL = 1;
  private static final long HOME_SCREEN_POLL_INTERVAL = 20000L;
  public static final String HOME_SCREEN_TAG = "home_screen_tag";
  private static final int IDLE = 4;
  private static final int POLL_FOR_CHANGED_THUMBNAIL = 7;
  private static final int POLL_FOR_COMMAND = 5;
  private static final int POLL_INTERVAL = 1000;
  public static final String TAG = "HomeScreenFragment";
  private static final int UPDATE_CAMERA = 6;
  private static final int UPDATE_HOMESCREEN = 2;
  private static final int UPDATE_NETWORKS = 1;
  private static final int UPDATE_SCREEN_STATUS = 3;
  private static ThisHandler handler = new ThisHandler();
  private View mAddCameraView;
  private CustomSwitch mArmSwitch;
  private ContentLoadingProgressBar mArmSwitchProgress;
  private ImageView mArmedStatusIcon;
  private TextView mArmedStatusString;
  private boolean mBooting;
  private String mCameraId;
  private LinearLayout mCameraListContainer;
  private ScrollView mCameraScrollView;
  private Command mCommandResponse;
  private int mCommandType;
  private QuickDeviceStatus[] mDevices;
  private LayoutInflater mInflater;
  private QuickNetworkStatus mNetwork;
  private String mNetworkId;
  private int mNetworkIndex;
  private Networks mNetworks;
  private int mNumberOfNotifications;
  private boolean mPolling;
  private boolean mSMOnline;
  private int mSectionNumber;
  private int mState;
  private boolean mSwitchSetProgrammaticly;
  private String mSyncModuleId;
  
  private void armNetwork(final boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Object localObject = new ArmNetworkRequest();; localObject = new DisarmNetworkRequest())
    {
      this.mArmedStatusString.setVisibility(4);
      this.mArmSwitchProgress.setVisibility(0);
      OnClick.blockAllClicks(true);
      cleanUpHandler();
      BlinkAPI.BlinkAPIRequest(null, null, (BlinkRequest)localObject, new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError)
        {
          OnClick.blockAllClicks(false);
          HomeScreenFragment.this.mArmSwitchProgress.setVisibility(4);
          HomeScreenFragment.this.mArmedStatusString.setVisibility(0);
          HomeScreenFragment.this.retryDialog(new Runnable()new Runnable
          {
            public void run()
            {
              HomeScreenFragment.this.armNetwork(HomeScreenFragment.3.this.val$isChecked);
            }
          }, new Runnable()
          {
            public void run()
            {
              HomeScreenFragment.this.refresh();
            }
          });
        }
        
        public void onResult(BlinkData paramAnonymousBlinkData)
        {
          HomeScreenFragment.access$202(HomeScreenFragment.this, (Command)paramAnonymousBlinkData);
          if (Command.mRawResponse.contains("No Cameras Enabled for Motion"))
          {
            HomeScreenFragment.this.refresh();
            return;
          }
          HomeScreenFragment.this.startPollForCommandDone(5);
        }
      }, false);
      return;
    }
  }
  
  private void checkForCommandFinished()
  {
    CommandRequest localCommandRequest = new CommandRequest();
    localCommandRequest.setCommand(String.valueOf(this.mCommandResponse.getId()));
    cleanUpHandler();
    BlinkAPI.BlinkAPIRequest(null, null, localCommandRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (HomeScreenFragment.this.mPolling)
        {
          paramAnonymousBlinkError = HomeScreenFragment.handler.obtainMessage(5, HomeScreenFragment.this);
          HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkError, 100L);
        }
        while (HomeScreenFragment.this.getActivity() == null) {
          return;
        }
        new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setTitle(HomeScreenFragment.this.getString(2131099811)).setMessage("Command failed").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            HomeScreenFragment.this.refresh();
          }
        }).create().show();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if (((Commands)paramAnonymousBlinkData).isComplete() == true)
        {
          if (((Commands)paramAnonymousBlinkData).getStatus() == 0)
          {
            HomeScreenFragment.access$802(HomeScreenFragment.this, false);
            switch (HomeScreenFragment.this.mCommandType)
            {
            }
          }
          while (HomeScreenFragment.this.getActivity() == null)
          {
            return;
            HomeScreenFragment.access$1102(HomeScreenFragment.this, 7);
            paramAnonymousBlinkData = ((Commands)paramAnonymousBlinkData).getCommands()[0];
            HomeScreenFragment.access$1202(HomeScreenFragment.this, String.valueOf(paramAnonymousBlinkData.getCamera_id()));
            paramAnonymousBlinkData = HomeScreenFragment.handler.obtainMessage(HomeScreenFragment.this.mState, HomeScreenFragment.this);
            HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 100L);
            return;
            HomeScreenFragment.this.refresh();
            OnClick.blockAllClicks(false);
            return;
            HomeScreenFragment.this.refresh();
            OnClick.blockAllClicks(false);
            return;
            HomeScreenFragment.this.refresh();
            return;
          }
          new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setTitle(HomeScreenFragment.this.getString(2131099811)).setMessage("Command failed").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              HomeScreenFragment.this.refresh();
            }
          }).create().show();
          return;
        }
        HomeScreenFragment.access$1102(HomeScreenFragment.this, 5);
        paramAnonymousBlinkData = HomeScreenFragment.handler.obtainMessage(HomeScreenFragment.this.mState, HomeScreenFragment.this);
        HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 1000L);
      }
    }, false);
  }
  
  private void checkForThumbnailUpdated()
  {
    GetSingleCameraStatusRequest localGetSingleCameraStatusRequest = new GetSingleCameraStatusRequest();
    cleanUpHandler();
    BlinkAPI.BlinkAPIRequest(null, null, localGetSingleCameraStatusRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {}
        while (HomeScreenFragment.this.getActivity() == null) {
          return;
        }
        new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setTitle("Error").setMessage("Could not update thumbnail").setPositiveButton(2131099886, null).create().show();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {
          return;
        }
        paramAnonymousBlinkData = ((CameraStatus)paramAnonymousBlinkData).getCamera_status();
        int m = paramAnonymousBlinkData.getCamera_id();
        int j = 0;
        int i = 0;
        while (i < HomeScreenFragment.this.mDevices.length)
        {
          if (HomeScreenFragment.this.mDevices[i].getDevice_id().intValue() == m)
          {
            HomeScreenFragment.CameraItem localCameraItem = (HomeScreenFragment.CameraItem)HomeScreenFragment.this.mCameraListContainer.getChildAt(j).getTag();
            if (!paramAnonymousBlinkData.getThumbnail().equals(localCameraItem.thumbnailUrl))
            {
              new HomeScreenFragment.CameraItem(HomeScreenFragment.this, j, HomeScreenFragment.this.mDevices[i], paramAnonymousBlinkData.getThumbnail(), false);
              HomeScreenFragment.this.mCameraListContainer.invalidate();
              HomeScreenFragment.access$1102(HomeScreenFragment.this, 4);
              HomeScreenFragment.this.pollForHomescreen();
              return;
            }
          }
          int k = j;
          if (HomeScreenFragment.this.mDevices[i].getDevice_type() == QuickDeviceStatus.DEVICE_TYPE.camera) {
            k = j + 1;
          }
          i += 1;
          j = k;
        }
        paramAnonymousBlinkData = HomeScreenFragment.handler.obtainMessage(7, HomeScreenFragment.this);
        HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 1000L);
      }
    }, false);
    OnClick.blockAllClicks(false);
  }
  
  private void cleanUpHandler()
  {
    int i = 1;
    while (i <= 7)
    {
      handler.removeMessages(i);
      i += 1;
    }
  }
  
  private void dispatchMessage(Message paramMessage)
  {
    cleanUpHandler();
    if ((getActivity() == null) || (this.mListener == null) || (isDetached())) {
      return;
    }
    switch (paramMessage.what)
    {
    case 4: 
    default: 
      return;
    case 1: 
      updateNetworks();
      return;
    case 2: 
      updateHomescreen();
      return;
    case 3: 
      updateScreenStatus();
      return;
    case 5: 
      checkForCommandFinished();
      return;
    case 6: 
      updateCamera();
      return;
    }
    checkForThumbnailUpdated();
  }
  
  public static HomeScreenFragment newInstance(int paramInt, boolean paramBoolean)
  {
    HomeScreenFragment localHomeScreenFragment = new HomeScreenFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localBundle.putBoolean("arg_app_is_bootin", paramBoolean);
    localHomeScreenFragment.setArguments(localBundle);
    return localHomeScreenFragment;
  }
  
  private void pollForHomescreen()
  {
    Message localMessage = handler.obtainMessage(1, this);
    handler.sendMessageDelayed(localMessage, 20000L);
  }
  
  private void retryDialog(final Runnable paramRunnable1, final Runnable paramRunnable2)
  {
    if (getActivity() != null) {
      new AlertDialog.Builder(getActivity()).setTitle(getString(2131099999)).setMessage("System is busy").setPositiveButton(2131100015, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (HomeScreenFragment.this.getActivity() != null) {
            paramRunnable1.run();
          }
        }
      }).setNegativeButton(2131099883, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (HomeScreenFragment.this.getActivity() != null) {
            paramRunnable2.run();
          }
        }
      }).create().show();
    }
  }
  
  private void startPollForCommandDone(int paramInt)
  {
    this.mPolling = true;
    this.mCommandType = paramInt;
    Message localMessage = handler.obtainMessage(5, this);
    handler.sendMessageDelayed(localMessage, 1000L);
  }
  
  private void takePicture(int paramInt, String paramString)
  {
    if (!BlinkApp.getApp().getLoggedIn())
    {
      cleanUpHandler();
      return;
    }
    this.mCameraId = paramString;
    paramString = new TakeSnapshotRequest();
    cleanUpHandler();
    BlinkAPI.BlinkAPIRequest(null, null, paramString, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (HomeScreenFragment.this.getActivity() != null) {
          new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setTitle(HomeScreenFragment.this.getString(2131099811)).setMessage("Could not update thumbnail").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              HomeScreenFragment.this.refresh();
            }
          }).create().show();
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        HomeScreenFragment.access$202(HomeScreenFragment.this, (Command)paramAnonymousBlinkData);
        HomeScreenFragment.this.startPollForCommandDone(1);
      }
    }, false);
  }
  
  private void updateCamera()
  {
    GetSingleCameraStatusRequest localGetSingleCameraStatusRequest = new GetSingleCameraStatusRequest();
    cleanUpHandler();
    BlinkAPI.BlinkAPIRequest(null, null, localGetSingleCameraStatusRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {}
        while (HomeScreenFragment.this.getActivity() == null) {
          return;
        }
        new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setTitle("Error").setMessage("Could not get camera info").setPositiveButton(2131099886, null).create().show();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {
          return;
        }
        paramAnonymousBlinkData = ((CameraStatus)paramAnonymousBlinkData).getCamera_status();
        int m = paramAnonymousBlinkData.getCamera_id();
        int j = 0;
        int i = 0;
        for (;;)
        {
          if (i < HomeScreenFragment.this.mDevices.length)
          {
            if (HomeScreenFragment.this.mDevices[i].getDevice_id().intValue() == m) {
              new HomeScreenFragment.CameraItem(HomeScreenFragment.this, j, HomeScreenFragment.this.mDevices[i], paramAnonymousBlinkData.getThumbnail(), false);
            }
          }
          else
          {
            HomeScreenFragment.access$1102(HomeScreenFragment.this, 4);
            HomeScreenFragment.this.pollForHomescreen();
            HomeScreenFragment.this.mCameraListContainer.invalidate();
            return;
          }
          int k = j;
          if (HomeScreenFragment.this.mDevices[i].getDevice_type() == QuickDeviceStatus.DEVICE_TYPE.camera) {
            k = j + 1;
          }
          i += 1;
          j = k;
        }
      }
    }, false);
  }
  
  private void updateHomescreen()
  {
    if (!BlinkApp.getApp().getLoggedIn())
    {
      cleanUpHandler();
      return;
    }
    HomescreenRequest localHomescreenRequest = new HomescreenRequest();
    cleanUpHandler();
    BlinkAPI.BlinkAPIRequest(null, null, localHomescreenRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        HomeScreenFragment.this.pollForHomescreen();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {
          return;
        }
        int i = HomeScreenFragment.this.mCameraListContainer.getChildCount() - 1 - 1;
        while (i >= 0)
        {
          HomeScreenFragment.this.mCameraListContainer.removeViewAt(i);
          i -= 1;
        }
        paramAnonymousBlinkData = (HomescreenSummaryStatus)paramAnonymousBlinkData;
        HomeScreenFragment.access$2102(HomeScreenFragment.this, paramAnonymousBlinkData.getNetwork());
        HomeScreenFragment.access$1302(HomeScreenFragment.this, paramAnonymousBlinkData.getDevices());
        HomeScreenFragment.access$2202(HomeScreenFragment.this, HomeScreenFragment.this.mNetwork.getNotifications().intValue());
        HomeScreenFragment.access$1202(HomeScreenFragment.this, "");
        int j = 0;
        i = 0;
        if (i < HomeScreenFragment.this.mDevices.length)
        {
          int k;
          if (HomeScreenFragment.this.mDevices[i].getDevice_type().equals(QuickDeviceStatus.DEVICE_TYPE.camera))
          {
            HomeScreenFragment.access$1202(HomeScreenFragment.this, String.valueOf(HomeScreenFragment.this.mDevices[i].getDevice_id()));
            new HomeScreenFragment.CameraItem(HomeScreenFragment.this, j, HomeScreenFragment.this.mDevices[i], null, true);
            k = j + 1;
          }
          for (;;)
          {
            i += 1;
            j = k;
            break;
            k = j;
            if (HomeScreenFragment.this.mDevices[i].getDevice_type().equals(QuickDeviceStatus.DEVICE_TYPE.sync_module))
            {
              HomeScreenFragment.access$2302(HomeScreenFragment.this, String.valueOf(HomeScreenFragment.this.mDevices[i].getDevice_id()));
              BlinkApp.getApp().setlastSyncModuleId(HomeScreenFragment.this.mSyncModuleId);
              if (HomeScreenFragment.this.mDevices[i].getStatus() == QuickDeviceStatus.DEVICE_STATUS.offline)
              {
                HomeScreenFragment.access$2402(HomeScreenFragment.this, false);
                k = j;
              }
              else
              {
                HomeScreenFragment.access$2402(HomeScreenFragment.this, true);
                k = j;
              }
            }
          }
        }
        if (HomeScreenFragment.this.mNumberOfNotifications == 0) {
          ((MainActivity)HomeScreenFragment.this.getActivity()).setClipRollActionIcon(2130837608);
        }
        while (HomeScreenFragment.this.mDevices.length == 0)
        {
          PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 2).commit();
          paramAnonymousBlinkData = new Intent(HomeScreenFragment.this.getActivity(), OnboardingActivity.class);
          paramAnonymousBlinkData.putExtra("onboard_reason", 2);
          HomeScreenFragment.this.startActivity(paramAnonymousBlinkData);
          HomeScreenFragment.access$1102(HomeScreenFragment.this, 0);
          HomeScreenFragment.this.pollForHomescreen();
          return;
          ((MainActivity)HomeScreenFragment.this.getActivity()).setClipRollActionIcon(2130837607);
        }
        HomeScreenFragment.access$1102(HomeScreenFragment.this, 3);
        paramAnonymousBlinkData = HomeScreenFragment.handler.obtainMessage(HomeScreenFragment.this.mState, HomeScreenFragment.this);
        HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 100L);
      }
    }, false);
  }
  
  private void updateNetworks()
  {
    if (!BlinkApp.getApp().getLoggedIn())
    {
      cleanUpHandler();
      return;
    }
    ListNetworksRequest localListNetworksRequest = new ListNetworksRequest();
    cleanUpHandler();
    BlinkAPI.BlinkAPIRequest(null, null, localListNetworksRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {}
        do
        {
          return;
          HomeScreenFragment.this.mCameraScrollView.setVisibility(0);
        } while (HomeScreenFragment.this.getActivity() == null);
        if (paramAnonymousBlinkError.getErrorMessage().equalsIgnoreCase("Unauthorized Access"))
        {
          if (HomeScreenFragment.this.mBooting)
          {
            ((MainActivity)HomeScreenFragment.this.getActivity()).logMeOut();
            return;
          }
          new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setTitle(HomeScreenFragment.this.getString(2131099811)).setMessage("Please sign in again").setPositiveButton("Ok", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              ((MainActivity)HomeScreenFragment.this.getActivity()).logMeOut();
            }
          }).create().show();
          return;
        }
        if ((HomeScreenFragment.this.mNetworkId.length() > 0) && (Integer.parseInt(HomeScreenFragment.this.mNetworkId) > 0))
        {
          HomeScreenFragment.access$1102(HomeScreenFragment.this, 2);
          paramAnonymousBlinkError = HomeScreenFragment.handler.obtainMessage(HomeScreenFragment.this.mState, HomeScreenFragment.this);
          HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkError, 1000L);
          return;
        }
        new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setTitle(HomeScreenFragment.this.getString(2131099811)).setMessage("Could not get system info").setPositiveButton(2131099999, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = HomeScreenFragment.handler.obtainMessage(1, HomeScreenFragment.this);
            HomeScreenFragment.handler.sendMessageDelayed(paramAnonymous2DialogInterface, 100L);
          }
        }).create().show();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {
          return;
        }
        HomeScreenFragment.access$1602(HomeScreenFragment.this, (Networks)paramAnonymousBlinkData);
        if (HomeScreenFragment.this.mNetworks.getNetworks().length > 0)
        {
          if (HomeScreenFragment.this.mNetworkIndex >= HomeScreenFragment.this.mNetworks.getNetworks().length) {
            HomeScreenFragment.access$1702(HomeScreenFragment.this, 0);
          }
          HomeScreenFragment.access$1802(HomeScreenFragment.this, String.valueOf(HomeScreenFragment.this.mNetworks.getNetworks()[HomeScreenFragment.this.mNetworkIndex].getId()));
          BlinkApp.getApp().setLastNetworkId(String.valueOf(HomeScreenFragment.this.mNetworkId));
          HomeScreenFragment.access$1102(HomeScreenFragment.this, 2);
          paramAnonymousBlinkData = HomeScreenFragment.handler.obtainMessage(HomeScreenFragment.this.mState, HomeScreenFragment.this);
          HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 100L);
          return;
        }
        PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 1).commit();
        paramAnonymousBlinkData = new Intent(HomeScreenFragment.this.getActivity(), OnboardingActivity.class);
        paramAnonymousBlinkData.putExtra("onboard_reason", 2);
        HomeScreenFragment.this.startActivity(paramAnonymousBlinkData);
      }
    }, false);
  }
  
  private void updateScreenStatus()
  {
    boolean bool1 = this.mNetworks.getNetworks()[this.mNetworkIndex].isArmed();
    boolean bool2 = this.mNetworks.getNetworks()[this.mNetworkIndex].isSync_module_error();
    boolean bool3 = this.mNetworks.getNetworks()[this.mNetworkIndex].isCamera_error();
    label87:
    String str;
    if ((bool2) || (bool3))
    {
      this.mArmedStatusIcon.setImageResource(2130837576);
      this.mSwitchSetProgrammaticly = true;
      if (!bool1) {
        break label187;
      }
      this.mArmSwitch.setChecked(true);
      this.mSwitchSetProgrammaticly = false;
      if (!this.mSMOnline) {
        break label209;
      }
      OnClick.blockAllClicks(false);
      TextView localTextView = this.mArmedStatusString;
      if (!bool1) {
        break label198;
      }
      str = getString(2131099744);
      label120:
      localTextView.setText(str);
    }
    for (;;)
    {
      this.mArmedStatusString.setVisibility(0);
      this.mState = 4;
      OnClick.blockAllClicks(false);
      pollForHomescreen();
      return;
      this.mSwitchSetProgrammaticly = true;
      if (bool1) {
        this.mArmedStatusIcon.setImageResource(2130837719);
      }
      for (;;)
      {
        this.mSwitchSetProgrammaticly = false;
        break;
        this.mArmedStatusIcon.setImageResource(2130837721);
      }
      label187:
      this.mArmSwitch.setChecked(false);
      break label87;
      label198:
      str = getString(2131099803);
      break label120;
      label209:
      OnClick.blockAllClicks(true);
      this.mArmedStatusString.setText("Sync Module offline");
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null)
    {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
      this.mBooting = getArguments().getBoolean("arg_app_is_bootin");
    }
    this.mState = 1;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mInflater = paramLayoutInflater;
    paramLayoutInflater = paramLayoutInflater.inflate(2130903101, paramViewGroup, false);
    this.mCameraScrollView = ((ScrollView)paramLayoutInflater.findViewById(2131558625));
    this.mCameraListContainer = ((LinearLayout)paramLayoutInflater.findViewById(2131558626));
    this.mAddCameraView = paramLayoutInflater.findViewById(2131558627);
    this.mArmedStatusIcon = ((ImageView)paramLayoutInflater.findViewById(2131558630));
    this.mArmedStatusString = ((TextView)paramLayoutInflater.findViewById(2131558634));
    this.mArmSwitchProgress = ((ContentLoadingProgressBar)paramLayoutInflater.findViewById(2131558633));
    this.mArmSwitchProgress.setVisibility(4);
    this.mArmedStatusString.setVisibility(0);
    this.mArmSwitch = ((CustomSwitch)paramLayoutInflater.findViewById(2131558635));
    this.mArmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (HomeScreenFragment.this.mSwitchSetProgrammaticly) {
          return;
        }
        HomeScreenFragment.this.armNetwork(paramAnonymousBoolean);
      }
    });
    paramLayoutInflater.findViewById(2131558628).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().remove("add_camera_sequence").commit();
        paramAnonymousView = new Intent(HomeScreenFragment.this.getActivity(), AddCameraActivity.class);
        HomeScreenFragment.this.startActivity(paramAnonymousView);
      }
    });
    this.mNetworkId = BlinkApp.getApp().getLastNetworkId();
    this.mCameraId = BlinkApp.getApp().getLastCameraId();
    this.mNetworkIndex = 0;
    refresh();
    return paramLayoutInflater;
  }
  
  public void onDetach()
  {
    OnClick.blockAllClicks(false);
    cleanUpHandler();
    super.onDetach();
  }
  
  public void onPause()
  {
    cleanUpHandler();
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    refresh();
  }
  
  public void refresh()
  {
    this.mState = 1;
    this.mArmSwitchProgress.setVisibility(4);
    if (!BlinkApp.getApp().getLoggedIn())
    {
      localObject = LoginFragment.newInstance(-1);
      this.mListener.onFragmentInteraction(this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, localObject);
      cleanUpHandler();
      return;
    }
    Object localObject = handler.obtainMessage(this.mState, this);
    handler.sendMessageDelayed((Message)localObject, 100L);
  }
  
  public void startClipList()
  {
    if (this.mDevices == null) {
      return;
    }
    this.mPolling = false;
    cleanUpHandler();
    Intent localIntent = new Intent(getActivity(), VideoListViewActivity.class);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    while (i < this.mDevices.length)
    {
      localArrayList2.add(this.mDevices[i].getName());
      localArrayList1.add(String.valueOf(this.mDevices[i].getDevice_id()));
      i += 1;
    }
    localIntent.putExtra("arg_camera_name", localArrayList2);
    localIntent.putExtra("arg_camera_ids", localArrayList1);
    getActivity().startActivityForResult(localIntent, 107);
  }
  
  public void updateHomeScreenImmediately()
  {
    this.mState = 2;
    Message localMessage = handler.obtainMessage(this.mState, this);
    handler.sendMessage(localMessage);
  }
  
  public class CameraItem
  {
    boolean addCamera;
    QuickDeviceStatus camera;
    int index;
    Button liveButton;
    Button picButton;
    ImageView runningIcon;
    ContentLoadingProgressBar spinner;
    ImageView statusIcon;
    TextView statusLabel;
    ImageView thumbnail;
    String thumbnailUrl;
    View view;
    
    public CameraItem(int paramInt, final QuickDeviceStatus paramQuickDeviceStatus, String paramString, boolean paramBoolean)
    {
      this.camera = paramQuickDeviceStatus;
      this.index = paramInt;
      this.addCamera = paramBoolean;
      this.thumbnailUrl = paramString;
      if (paramBoolean)
      {
        this.view = HomeScreenFragment.this.mInflater.inflate(2130903117, HomeScreenFragment.this.mCameraListContainer, false);
        this.spinner = ((ContentLoadingProgressBar)this.view.findViewById(2131558538));
        this.spinner.hide();
        this.runningIcon = ((ImageView)this.view.findViewById(2131558689));
        if (!HomeScreenFragment.this.mNetworks.getNetworks()[HomeScreenFragment.this.mNetworkIndex].isArmed()) {
          break label414;
        }
        if (!paramQuickDeviceStatus.getEnabled().booleanValue()) {
          break label395;
        }
        this.runningIcon.setImageDrawable(HomeScreenFragment.this.getResources().getDrawable(2130837784));
        label137:
        this.runningIcon.setVisibility(0);
        label145:
        this.runningIcon.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {
              return;
            }
            BlinkApp.getApp().setLastCameraId(String.valueOf(HomeScreenFragment.CameraItem.this.camera.getDevice_id()));
            HomeScreenFragment.CameraItem.this.view.findViewById(2131558689).setVisibility(4);
            HomeScreenFragment.CameraItem.this.view.findViewById(2131558690).setVisibility(0);
            final boolean bool = paramQuickDeviceStatus.getEnabled().booleanValue();
            if (bool) {}
            for (paramAnonymousView = new DisableCameraAlertsRequest();; paramAnonymousView = new EnableCameraAlertsRequest())
            {
              OnClick.blockAllClicks(true);
              HomeScreenFragment.this.cleanUpHandler();
              BlinkAPI.BlinkAPIRequest(null, null, paramAnonymousView, new BlinkAPI.BlinkAPICallback()
              {
                public void onError(BlinkError paramAnonymous2BlinkError)
                {
                  OnClick.blockAllClicks(false);
                  HomeScreenFragment.CameraItem.this.view.findViewById(2131558689).setVisibility(0);
                  HomeScreenFragment.CameraItem.this.view.findViewById(2131558690).setVisibility(4);
                  HomeScreenFragment.this.refresh();
                  if (HomeScreenFragment.CameraItem.1.this.val$cam.getEnabled().booleanValue())
                  {
                    HomeScreenFragment.CameraItem.this.runningIcon.setImageDrawable(HomeScreenFragment.this.getResources().getDrawable(2130837784));
                    return;
                  }
                  HomeScreenFragment.CameraItem.this.runningIcon.setImageDrawable(HomeScreenFragment.this.getResources().getDrawable(2130837785));
                }
                
                public void onResult(BlinkData paramAnonymous2BlinkData)
                {
                  HomeScreenFragment.access$202(HomeScreenFragment.this, (Command)paramAnonymous2BlinkData);
                  if (bool)
                  {
                    HomeScreenFragment.this.startPollForCommandDone(3);
                    return;
                  }
                  HomeScreenFragment.this.startPollForCommandDone(2);
                }
              }, false);
              return;
            }
          }
        });
        this.thumbnail = ((ImageView)this.view.findViewById(2131558584));
        if (paramString == null) {
          break label425;
        }
        new ImageLoader(paramString, this.thumbnail, true, 2);
        this.spinner.setVisibility(8);
        this.thumbnail.invalidate();
      }
      for (;;)
      {
        this.statusLabel = ((TextView)this.view.findViewById(2131558687));
        this.statusIcon = ((ImageView)this.view.findViewById(2131558688));
        this.statusIcon.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {
              return;
            }
            BlinkApp.getApp().setLastCameraId(String.valueOf(HomeScreenFragment.CameraItem.this.camera.getDevice_id()));
            ((MainActivity)HomeScreenFragment.this.getActivity()).startCameraSettings(HomeScreenFragment.CameraItem.this.camera.getDevice_id().intValue());
          }
        });
        this.liveButton = ((Button)this.view.findViewById(2131558681));
        this.liveButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {
              return;
            }
            HomeScreenFragment.access$802(HomeScreenFragment.this, false);
            BlinkApp.getApp().setLastCameraName(String.valueOf(HomeScreenFragment.CameraItem.this.camera.getName()));
            HomeScreenFragment.this.cleanUpHandler();
            BlinkApp.getApp().setLastNetworkId(HomeScreenFragment.this.mNetworkId);
            BlinkApp.getApp().setLastCameraId(String.valueOf(HomeScreenFragment.CameraItem.this.camera.getDevice_id()));
            if ((0 != 0) && (Build.VERSION.SDK_INT == 19))
            {
              new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setMessage(HomeScreenFragment.this.getString(2131099866)).setPositiveButton(2131099886, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  HomeScreenFragment.this.refresh();
                }
              }).create().show();
              return;
            }
            paramAnonymousView = new Intent(HomeScreenFragment.this.getActivity(), VideoLiveViewActivity.class);
            HomeScreenFragment.this.startActivityForResult(paramAnonymousView, 109);
          }
        });
        this.picButton = ((Button)this.view.findViewById(2131558691));
        this.picButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {
              return;
            }
            BlinkApp.getApp().setLastNetworkId(HomeScreenFragment.this.mNetworkId);
            BlinkApp.getApp().setLastCameraId(String.valueOf(HomeScreenFragment.CameraItem.this.camera.getDevice_id()));
            HomeScreenFragment.CameraItem.this.spinner.show();
            OnClick.blockAllClicks(true);
            HomeScreenFragment.this.takePicture(HomeScreenFragment.CameraItem.this.index, String.valueOf(HomeScreenFragment.CameraItem.this.camera.getDevice_id()));
          }
        });
        this.statusLabel.setText(this.camera.getName());
        this.view.setTag(this);
        if (this.addCamera) {
          HomeScreenFragment.this.mCameraListContainer.addView(this.view, HomeScreenFragment.this.mCameraListContainer.getChildCount() - 1);
        }
        return;
        this.view = HomeScreenFragment.this.mCameraListContainer.getChildAt(this.index);
        break;
        label395:
        this.runningIcon.setImageDrawable(HomeScreenFragment.this.getResources().getDrawable(2130837785));
        break label137;
        label414:
        this.runningIcon.setVisibility(4);
        break label145;
        label425:
        paramQuickDeviceStatus = this.camera.getThumbnail();
        if (paramQuickDeviceStatus != null)
        {
          this.thumbnailUrl = paramQuickDeviceStatus;
          new ImageLoader(paramQuickDeviceStatus, this.thumbnail, true, 2);
          this.spinner.setVisibility(8);
          this.thumbnail.invalidate();
        }
      }
    }
  }
  
  static class ThisHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.obj != null) {
        ((HomeScreenFragment)paramMessage.obj).dispatchMessage(paramMessage);
      }
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/HomeScreenFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */