package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.AddCameraActivity;
import com.immediasemi.blink.activities.MainActivity;
import com.immediasemi.blink.activities.OnboardingActivity;
import com.immediasemi.blink.activities.VideoListViewActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.api.requests.Cameras.DisableCameraAlertsRequest;
import com.immediasemi.blink.api.requests.Cameras.EnableCameraAlertsRequest;
import com.immediasemi.blink.api.requests.Cameras.GetSingleCameraStatusRequest;
import com.immediasemi.blink.api.requests.Cameras.TakeSnapshotRequest;
import com.immediasemi.blink.api.requests.Command.CommandRequest;
import com.immediasemi.blink.api.requests.Homescreen.HomescreenRequest;
import com.immediasemi.blink.api.requests.Networks.AddNetworkRequest;
import com.immediasemi.blink.api.requests.Networks.ArmNetworkRequest;
import com.immediasemi.blink.api.requests.Networks.DisarmNetworkRequest;
import com.immediasemi.blink.api.requests.Networks.ListNetworksRequest;
import com.immediasemi.blink.models.ANetwork;
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
import com.immediasemi.blink.utils.SystemPickerAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
  private View mMainHomeScreenview;
  private QuickNetworkStatus mNetwork;
  private String mNetworkId;
  private int mNetworkIndex;
  private String mNetworkName;
  private Networks mNetworks;
  private int mNumberOfNotifications;
  private boolean mPolling;
  private boolean mSMOnline;
  private int mSectionNumber;
  private int mState;
  private boolean mSwitchSetProgrammaticly;
  private String mSyncModuleId;
  private Button systemPickerButton;
  private PopupWindow systemPickerWindow;
  private ImageView tapToAddDevice;
  
  private void addSystem()
  {
    final AlertDialog localAlertDialog = new AlertDialog.Builder(getActivity()).create();
    localAlertDialog.setCustomTitle(View.inflate(getContext(), 2130903068, null));
    View localView = View.inflate(getContext(), 2130903067, null);
    ListView localListView = (ListView)localView.findViewById(2131558532);
    ArrayList localArrayList = new ArrayList(3);
    localArrayList.add("Add System");
    localArrayList.add("Need a System?");
    localArrayList.add("Cancel");
    localListView.setAdapter(new ArrayAdapter(getContext(), 2130903081, localArrayList));
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        switch (paramAnonymousInt)
        {
        }
        for (;;)
        {
          localAlertDialog.dismiss();
          return;
          HomeScreenFragment.this.createSystem();
          continue;
          HomeScreenFragment.this.buySystem();
        }
      }
    });
    localAlertDialog.setView(localView);
    localAlertDialog.show();
  }
  
  private void armNetwork(final boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Object localObject = new ArmNetworkRequest();; localObject = new DisarmNetworkRequest())
    {
      this.mArmedStatusString.setVisibility(4);
      this.mArmSwitchProgress.setVisibility(0);
      OnClick.enableClicks(false);
      this.mArmSwitch.setEnabled(false);
      cleanUpHandler();
      BlinkAPI.BlinkAPIRequest(null, null, (BlinkRequest)localObject, new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError)
        {
          HomeScreenFragment.this.mArmSwitchProgress.setVisibility(4);
          HomeScreenFragment.this.mArmedStatusString.setVisibility(0);
          if (paramAnonymousBlinkError.response != null) {}
          for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
          {
            HomeScreenFragment.this.retryDialog(new Runnable()new Runnable
            {
              public void run()
              {
                HomeScreenFragment.this.armNetwork(HomeScreenFragment.4.this.val$isChecked);
              }
            }, new Runnable()
            {
              public void run()
              {
                HomeScreenFragment.this.refresh();
              }
            }, paramAnonymousBlinkError);
            return;
          }
        }
        
        public void onResult(BlinkData paramAnonymousBlinkData)
        {
          HomeScreenFragment.access$302(HomeScreenFragment.this, (Command)paramAnonymousBlinkData);
          if (Command.mRawResponse.contains("No Cameras Enabled for Motion")) {
            HomeScreenFragment.this.refresh();
          }
          for (;;)
          {
            return;
            HomeScreenFragment.this.startPollForCommandDone(5);
          }
        }
      }, false);
      return;
    }
  }
  
  private void buySystem()
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://blinkforhome.com/product-pricing")));
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
          return;
        }
        if (HomeScreenFragment.this.getActivity() != null) {
          if (paramAnonymousBlinkError.response == null) {
            break label104;
          }
        }
        label104:
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
        {
          new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              HomeScreenFragment.this.refresh();
            }
          }).create().show();
          break;
          break;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if (((Commands)paramAnonymousBlinkData).isComplete()) {
          if (((Commands)paramAnonymousBlinkData).getStatus() == 0)
          {
            HomeScreenFragment.access$902(HomeScreenFragment.this, false);
            switch (HomeScreenFragment.this.mCommandType)
            {
            }
          }
        }
        for (;;)
        {
          return;
          HomeScreenFragment.access$1202(HomeScreenFragment.this, 7);
          paramAnonymousBlinkData = ((Commands)paramAnonymousBlinkData).getCommands()[0];
          HomeScreenFragment.access$1302(HomeScreenFragment.this, String.valueOf(paramAnonymousBlinkData.getCamera_id()));
          paramAnonymousBlinkData = HomeScreenFragment.handler.obtainMessage(HomeScreenFragment.this.mState, HomeScreenFragment.this);
          HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 100L);
          continue;
          HomeScreenFragment.this.refresh();
          continue;
          if (HomeScreenFragment.this.getActivity() != null)
          {
            new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setMessage(((Commands)paramAnonymousBlinkData).getStatus_msg()).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                HomeScreenFragment.this.refresh();
              }
            }).create().show();
            continue;
            HomeScreenFragment.access$1202(HomeScreenFragment.this, 5);
            paramAnonymousBlinkData = HomeScreenFragment.handler.obtainMessage(HomeScreenFragment.this.mState, HomeScreenFragment.this);
            HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 1000L);
          }
        }
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
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {
          return;
        }
        OnClick.enableClicks(true);
        if (paramAnonymousBlinkError.response != null) {}
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
        {
          new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              HomeScreenFragment.this.refresh();
            }
          }).create().show();
          break;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {}
        for (;;)
        {
          return;
          OnClick.enableClicks(true);
          DeviceStatus localDeviceStatus = ((CameraStatus)paramAnonymousBlinkData).getCamera_status();
          int m = localDeviceStatus.getCamera_id();
          int j = 0;
          int i = 0;
          for (;;)
          {
            if (i >= HomeScreenFragment.this.mDevices.length) {
              break label212;
            }
            if (HomeScreenFragment.this.mDevices[i].getDevice_id().intValue() == m)
            {
              paramAnonymousBlinkData = (HomeScreenFragment.CameraItem)HomeScreenFragment.this.mCameraListContainer.getChildAt(j).getTag();
              if (!localDeviceStatus.getThumbnail().equals(paramAnonymousBlinkData.thumbnailUrl))
              {
                new HomeScreenFragment.CameraItem(HomeScreenFragment.this, j, HomeScreenFragment.this.mDevices[i], localDeviceStatus.getThumbnail(), false);
                HomeScreenFragment.this.mCameraListContainer.invalidate();
                HomeScreenFragment.access$1202(HomeScreenFragment.this, 4);
                HomeScreenFragment.this.pollForHomescreen();
                break;
              }
            }
            int k = j;
            if (HomeScreenFragment.this.mDevices[i].getDevice_type() == QuickDeviceStatus.DEVICE_TYPE.camera) {
              k = j + 1;
            }
            i++;
            j = k;
          }
          label212:
          paramAnonymousBlinkData = HomeScreenFragment.handler.obtainMessage(7, HomeScreenFragment.this);
          HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 1000L);
        }
      }
    }, false);
  }
  
  private void createSystem()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new AddNetworkRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (HomeScreenFragment.this.getActivity() != null) {
          if (paramAnonymousBlinkError.response == null) {
            break label61;
          }
        }
        label61:
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
        {
          new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).create().show();
          return;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        paramAnonymousBlinkData = ((ANetwork)paramAnonymousBlinkData).getNetwork();
        BlinkApp.getApp().setlastSyncModuleId("");
        HomeScreenFragment.access$1802(HomeScreenFragment.this, HomeScreenFragment.this.mNetworks.getNetworks().length);
        BlinkApp.getApp().setLastNetworkIndex(Integer.valueOf(HomeScreenFragment.this.mNetworkIndex));
        HomeScreenFragment.access$1902(HomeScreenFragment.this, String.valueOf(paramAnonymousBlinkData.getId()));
        BlinkApp.getApp().setLastNetworkId(HomeScreenFragment.this.mNetworkId);
        HomeScreenFragment.access$2002(HomeScreenFragment.this, String.valueOf(paramAnonymousBlinkData.getName()));
        BlinkApp.getApp().setLastNetworkName(HomeScreenFragment.this.mNetworkName);
        HomeScreenFragment.this.systemPickerButton.setText(HomeScreenFragment.this.systemPickerTitleForSystem(null));
        HomeScreenFragment.this.updateNetworks();
        HomeScreenFragment.this.openOnboarding();
      }
    }, false);
  }
  
  private void dispatchMessage(Message paramMessage)
  {
    cleanUpHandler();
    if ((getActivity() == null) || (this.mListener == null) || (isDetached())) {}
    for (;;)
    {
      return;
      switch (paramMessage.what)
      {
      case 4: 
      default: 
        break;
      case 1: 
        updateNetworks();
        break;
      case 2: 
        updateHomescreen();
        break;
      case 3: 
        updateScreenStatus();
        break;
      case 5: 
        checkForCommandFinished();
        break;
      case 6: 
        updateCamera();
        break;
      case 7: 
        checkForThumbnailUpdated();
      }
    }
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
  
  private void openOnboarding()
  {
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("onboard_sequence", 0).apply();
    Intent localIntent = new Intent(getActivity(), OnboardingActivity.class);
    localIntent.putExtra("onboard_reason", 2);
    startActivity(localIntent);
  }
  
  private void pollForHomescreen()
  {
    Message localMessage = handler.obtainMessage(1, this);
    handler.sendMessageDelayed(localMessage, 20000L);
  }
  
  private void retryDialog(final Runnable paramRunnable1, final Runnable paramRunnable2, String paramString)
  {
    if (getActivity() != null) {
      new AlertDialog.Builder(getActivity()).setTitle(getString(2131100006)).setMessage(paramString).setPositiveButton(2131100024, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (HomeScreenFragment.this.getActivity() != null) {
            paramRunnable1.run();
          }
        }
      }).setNegativeButton(2131099888, new DialogInterface.OnClickListener()
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
  
  private String systemPickerTitleForSystem(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = this.mNetworkName;
    }
    return str.concat(" ▾");
  }
  
  private void takePicture(int paramInt, String paramString)
  {
    if (!BlinkApp.getApp().getLoggedIn()) {
      cleanUpHandler();
    }
    for (;;)
    {
      return;
      this.mCameraId = paramString;
      paramString = new TakeSnapshotRequest();
      cleanUpHandler();
      BlinkAPI.BlinkAPIRequest(null, null, paramString, new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError)
        {
          if (HomeScreenFragment.this.getActivity() != null) {
            if (paramAnonymousBlinkError.response == null) {
              break label68;
            }
          }
          label68:
          for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
          {
            new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                HomeScreenFragment.this.refresh();
              }
            }).create().show();
            return;
          }
        }
        
        public void onResult(BlinkData paramAnonymousBlinkData)
        {
          HomeScreenFragment.access$302(HomeScreenFragment.this, (Command)paramAnonymousBlinkData);
          HomeScreenFragment.this.startPollForCommandDone(1);
        }
      }, false);
    }
  }
  
  private void updateActionBar()
  {
    ActionBar localActionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
    if (localActionBar != null)
    {
      localActionBar.setCustomView(2130903071);
      if (this.systemPickerButton == null) {}
      this.systemPickerButton = ((Button)localActionBar.getCustomView().findViewById(2131558535));
      this.systemPickerButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          HomeScreenFragment.this.showPopup(HomeScreenFragment.this.systemPickerButton);
        }
      });
      this.systemPickerButton.setText(systemPickerTitleForSystem(null));
    }
  }
  
  private void updateCamera()
  {
    GetSingleCameraStatusRequest localGetSingleCameraStatusRequest = new GetSingleCameraStatusRequest();
    cleanUpHandler();
    BlinkAPI.BlinkAPIRequest(null, null, localGetSingleCameraStatusRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {
          return;
        }
        if (HomeScreenFragment.this.getActivity() != null) {
          if (paramAnonymousBlinkError.response == null) {
            break label94;
          }
        }
        label94:
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
        {
          new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).create().show();
          break;
          break;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {
          return;
        }
        paramAnonymousBlinkData = ((CameraStatus)paramAnonymousBlinkData).getCamera_status();
        int m = paramAnonymousBlinkData.getCamera_id();
        int k = 0;
        int i = 0;
        for (;;)
        {
          if (i < HomeScreenFragment.this.mDevices.length)
          {
            if (HomeScreenFragment.this.mDevices[i].getDevice_id().intValue() == m) {
              new HomeScreenFragment.CameraItem(HomeScreenFragment.this, k, HomeScreenFragment.this.mDevices[i], paramAnonymousBlinkData.getThumbnail(), false);
            }
          }
          else
          {
            HomeScreenFragment.access$1202(HomeScreenFragment.this, 4);
            HomeScreenFragment.this.pollForHomescreen();
            HomeScreenFragment.this.mCameraListContainer.invalidate();
            break;
          }
          int j = k;
          if (HomeScreenFragment.this.mDevices[i].getDevice_type() == QuickDeviceStatus.DEVICE_TYPE.camera) {
            j = k + 1;
          }
          i++;
          k = j;
        }
      }
    }, false);
  }
  
  private void updateHomescreen()
  {
    if (!BlinkApp.getApp().getLoggedIn()) {
      cleanUpHandler();
    }
    for (;;)
    {
      return;
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
          boolean bool = true;
          if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {}
          for (;;)
          {
            return;
            for (int i = HomeScreenFragment.this.mCameraListContainer.getChildCount() - 1 - 1; i >= 0; i--) {
              HomeScreenFragment.this.mCameraListContainer.removeViewAt(i);
            }
            paramAnonymousBlinkData = (HomescreenSummaryStatus)paramAnonymousBlinkData;
            HomeScreenFragment.access$3102(HomeScreenFragment.this, paramAnonymousBlinkData.getNetwork());
            HomeScreenFragment.access$1402(HomeScreenFragment.this, paramAnonymousBlinkData.getDevices());
            HomeScreenFragment.access$3202(HomeScreenFragment.this, HomeScreenFragment.this.mNetwork.getNotifications().intValue());
            HomeScreenFragment.access$1302(HomeScreenFragment.this, "");
            i = 0;
            HomeScreenFragment.access$3302(HomeScreenFragment.this, "0");
            BlinkApp.getApp().setlastSyncModuleId(HomeScreenFragment.this.mSyncModuleId);
            int j = 0;
            if (j < HomeScreenFragment.this.mDevices.length)
            {
              int k;
              if (HomeScreenFragment.this.mDevices[j].getDevice_type().equals(QuickDeviceStatus.DEVICE_TYPE.camera))
              {
                HomeScreenFragment.access$1302(HomeScreenFragment.this, String.valueOf(HomeScreenFragment.this.mDevices[j].getDevice_id()));
                new HomeScreenFragment.CameraItem(HomeScreenFragment.this, i, HomeScreenFragment.this.mDevices[j], null, true);
                k = i + 1;
              }
              for (;;)
              {
                j++;
                i = k;
                break;
                k = i;
                if (HomeScreenFragment.this.mDevices[j].getDevice_type().equals(QuickDeviceStatus.DEVICE_TYPE.sync_module))
                {
                  HomeScreenFragment.access$3302(HomeScreenFragment.this, String.valueOf(HomeScreenFragment.this.mDevices[j].getDevice_id()));
                  BlinkApp.getApp().setlastSyncModuleId(HomeScreenFragment.this.mSyncModuleId);
                  if (HomeScreenFragment.this.mDevices[j].getStatus() == QuickDeviceStatus.DEVICE_STATUS.offline)
                  {
                    HomeScreenFragment.access$3402(HomeScreenFragment.this, false);
                    k = i;
                  }
                  else
                  {
                    HomeScreenFragment.access$3402(HomeScreenFragment.this, true);
                    k = i;
                  }
                }
              }
            }
            if (Integer.valueOf(HomeScreenFragment.this.mSyncModuleId).intValue() <= 0)
            {
              j = HomeScreenFragment.this.getResources().getIdentifier("sm_icon_green", "drawable", HomeScreenFragment.this.getActivity().getPackageName());
              ((TextView)HomeScreenFragment.this.mMainHomeScreenview.findViewById(2131558636)).setText(2131099964);
              label420:
              HomeScreenFragment.this.tapToAddDevice.setImageResource(j);
              if (HomeScreenFragment.this.mAddCameraView != null)
              {
                if (i < 10) {
                  break label573;
                }
                HomeScreenFragment.this.mAddCameraView.setVisibility(8);
              }
              label459:
              paramAnonymousBlinkData = HomeScreenFragment.this.mArmSwitch;
              if (i <= 0) {
                break label587;
              }
              label471:
              paramAnonymousBlinkData.setEnabled(bool);
              if (HomeScreenFragment.this.mNumberOfNotifications != 0) {
                break label593;
              }
              ((MainActivity)HomeScreenFragment.this.getActivity()).setClipRollActionIcon(2130837608);
            }
            for (;;)
            {
              if (HomeScreenFragment.this.mDevices.length != 0) {
                break label611;
              }
              HomeScreenFragment.access$1202(HomeScreenFragment.this, 0);
              break;
              j = HomeScreenFragment.this.getResources().getIdentifier("camera_icon_green", "drawable", HomeScreenFragment.this.getActivity().getPackageName());
              ((TextView)HomeScreenFragment.this.mMainHomeScreenview.findViewById(2131558636)).setText(2131099963);
              break label420;
              label573:
              HomeScreenFragment.this.mAddCameraView.setVisibility(0);
              break label459;
              label587:
              bool = false;
              break label471;
              label593:
              ((MainActivity)HomeScreenFragment.this.getActivity()).setClipRollActionIcon(2130837607);
            }
            label611:
            HomeScreenFragment.access$1202(HomeScreenFragment.this, 3);
            paramAnonymousBlinkData = HomeScreenFragment.handler.obtainMessage(HomeScreenFragment.this.mState, HomeScreenFragment.this);
            HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 100L);
          }
        }
      }, false);
    }
  }
  
  private void updateNetworks()
  {
    if (!BlinkApp.getApp().getLoggedIn()) {
      cleanUpHandler();
    }
    for (;;)
    {
      return;
      ListNetworksRequest localListNetworksRequest = new ListNetworksRequest();
      cleanUpHandler();
      BlinkAPI.BlinkAPIRequest(null, null, localListNetworksRequest, new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError)
        {
          if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {}
          for (;;)
          {
            return;
            HomeScreenFragment.this.mCameraScrollView.setVisibility(0);
            if (HomeScreenFragment.this.getActivity() != null) {
              if (paramAnonymousBlinkError.getErrorMessage().equalsIgnoreCase("Unauthorized Access"))
              {
                ((MainActivity)HomeScreenFragment.this.getActivity()).logMeOut();
              }
              else if ((HomeScreenFragment.this.mNetworkId.length() > 0) && (Integer.parseInt(HomeScreenFragment.this.mNetworkId) > 0))
              {
                HomeScreenFragment.access$1202(HomeScreenFragment.this, 2);
                paramAnonymousBlinkError = HomeScreenFragment.handler.obtainMessage(HomeScreenFragment.this.mState, HomeScreenFragment.this);
                HomeScreenFragment.handler.sendMessageDelayed(paramAnonymousBlinkError, 1000L);
              }
              else
              {
                new AlertDialog.Builder(HomeScreenFragment.this.getActivity()).setTitle(HomeScreenFragment.this.getString(2131099816)).setMessage("Could not get system info").setPositiveButton(2131100006, new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                  {
                    paramAnonymous2DialogInterface = HomeScreenFragment.handler.obtainMessage(1, HomeScreenFragment.this);
                    HomeScreenFragment.handler.sendMessageDelayed(paramAnonymous2DialogInterface, 100L);
                  }
                }).create().show();
              }
            }
          }
        }
        
        public void onResult(BlinkData paramAnonymousBlinkData)
        {
          if ((HomeScreenFragment.this.getActivity() == null) || (HomeScreenFragment.this.mListener == null) || (HomeScreenFragment.this.isDetached())) {}
          for (;;)
          {
            return;
            HomeScreenFragment.access$1702(HomeScreenFragment.this, (Networks)paramAnonymousBlinkData);
            BlinkApp.getApp().setNetworkCount(Integer.valueOf(HomeScreenFragment.this.mNetworks.getNetworks().length));
            int i;
            switch (BlinkApp.getApp().getNetworkCount().intValue())
            {
            default: 
              i = 0;
            }
            for (;;)
            {
              if (i < HomeScreenFragment.this.mNetworks.getNetworks().length / 2)
              {
                paramAnonymousBlinkData = HomeScreenFragment.this.mNetworks.getNetworks()[i];
                HomeScreenFragment.this.mNetworks.getNetworks()[i] = HomeScreenFragment.this.mNetworks.getNetworks()[(HomeScreenFragment.this.mNetworks.getNetworks().length - i - 1)];
                HomeScreenFragment.this.mNetworks.getNetworks()[(HomeScreenFragment.this.mNetworks.getNetworks().length - i - 1)] = paramAnonymousBlinkData;
                i++;
                continue;
                ((MainActivity)HomeScreenFragment.this.getActivity()).logMeOut();
                break;
              }
            }
            HomeScreenFragment.access$1802(HomeScreenFragment.this, BlinkApp.getApp().getLastNetworkIndex().intValue());
            if (HomeScreenFragment.this.mNetworkIndex >= HomeScreenFragment.this.mNetworks.getNetworks().length) {
              HomeScreenFragment.access$1802(HomeScreenFragment.this, HomeScreenFragment.this.mNetworks.getNetworks().length - 1);
            }
            HomeScreenFragment.access$1902(HomeScreenFragment.this, String.valueOf(HomeScreenFragment.this.mNetworks.getNetworks()[HomeScreenFragment.this.mNetworkIndex].getId()));
            BlinkApp.getApp().setLastNetworkId(HomeScreenFragment.this.mNetworkId);
            HomeScreenFragment.access$2002(HomeScreenFragment.this, String.valueOf(HomeScreenFragment.this.mNetworks.getNetworks()[HomeScreenFragment.this.mNetworkIndex].getName()));
            BlinkApp.getApp().setLastNetworkName(HomeScreenFragment.this.mNetworkName);
            HomeScreenFragment.this.updateActionBar();
            HomeScreenFragment.this.updateHomescreen();
          }
        }
      }, false);
    }
  }
  
  private void updateScreenStatus()
  {
    boolean bool2 = this.mNetworks.getNetworks()[this.mNetworkIndex].isArmed();
    boolean bool1 = this.mNetworks.getNetworks()[this.mNetworkIndex].isSync_module_error();
    boolean bool3 = this.mNetworks.getNetworks()[this.mNetworkIndex].isCamera_error();
    label83:
    String str;
    if ((bool1) || (bool3))
    {
      this.mArmedStatusIcon.setImageResource(2130837576);
      this.mSwitchSetProgrammaticly = true;
      if (!bool2) {
        break label191;
      }
      this.mArmSwitch.setChecked(true);
      this.mSwitchSetProgrammaticly = false;
      if (!this.mSMOnline) {
        break label214;
      }
      OnClick.enableClicks(true);
      this.mArmSwitch.setEnabled(true);
      TextView localTextView = this.mArmedStatusString;
      if (!bool2) {
        break label202;
      }
      str = getString(2131099748);
      label126:
      localTextView.setText(str);
    }
    for (;;)
    {
      this.mArmedStatusString.setVisibility(0);
      this.mState = 4;
      pollForHomescreen();
      return;
      this.mSwitchSetProgrammaticly = true;
      if (bool2) {
        this.mArmedStatusIcon.setImageResource(2130837721);
      }
      for (;;)
      {
        this.mSwitchSetProgrammaticly = false;
        break;
        this.mArmedStatusIcon.setImageResource(2130837723);
      }
      label191:
      this.mArmSwitch.setChecked(false);
      break label83;
      label202:
      str = getString(2131099808);
      break label126;
      label214:
      OnClick.enableClicks(false);
      this.mArmSwitch.setEnabled(false);
      this.mArmedStatusString.setText("Sync Module offline");
    }
  }
  
  public void cleanUpHandler()
  {
    for (int i = 1; i <= 7; i++) {
      handler.removeMessages(i);
    }
  }
  
  public int dpToPx(int paramInt)
  {
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    return Math.round(paramInt * localDisplayMetrics.density);
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
    this.mMainHomeScreenview = paramLayoutInflater.inflate(2130903105, paramViewGroup, false);
    this.mCameraScrollView = ((ScrollView)this.mMainHomeScreenview.findViewById(2131558632));
    this.mCameraListContainer = ((LinearLayout)this.mMainHomeScreenview.findViewById(2131558633));
    this.mAddCameraView = this.mMainHomeScreenview.findViewById(2131558634);
    this.mArmedStatusIcon = ((ImageView)this.mMainHomeScreenview.findViewById(2131558637));
    this.mArmedStatusString = ((TextView)this.mMainHomeScreenview.findViewById(2131558641));
    this.mArmSwitchProgress = ((ContentLoadingProgressBar)this.mMainHomeScreenview.findViewById(2131558640));
    this.mArmSwitchProgress.setVisibility(4);
    this.mArmedStatusString.setVisibility(0);
    this.mArmSwitch = ((CustomSwitch)this.mMainHomeScreenview.findViewById(2131558642));
    this.mArmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (HomeScreenFragment.this.mSwitchSetProgrammaticly) {}
        for (;;)
        {
          return;
          HomeScreenFragment.this.armNetwork(paramAnonymousBoolean);
        }
      }
    });
    this.tapToAddDevice = ((ImageView)this.mMainHomeScreenview.findViewById(2131558635));
    this.tapToAddDevice.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().remove("add_camera_sequence").commit();
          paramAnonymousView = new Intent(HomeScreenFragment.this.getActivity(), AddCameraActivity.class);
          HomeScreenFragment.this.startActivity(paramAnonymousView);
        }
      }
    });
    this.mNetworkId = BlinkApp.getApp().getLastNetworkId();
    this.mNetworkName = BlinkApp.getApp().getLastNetworkName();
    this.mCameraId = BlinkApp.getApp().getLastCameraId();
    this.mNetworkIndex = BlinkApp.getApp().getLastNetworkIndex().intValue();
    paramLayoutInflater = ((AppCompatActivity)getActivity()).getSupportActionBar();
    if (paramLayoutInflater != null)
    {
      paramLayoutInflater.setCustomView(2130903071);
      this.systemPickerButton = ((Button)paramLayoutInflater.getCustomView().findViewById(2131558535));
      this.systemPickerButton.setText(systemPickerTitleForSystem(null));
      this.systemPickerButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          HomeScreenFragment.this.showPopup(HomeScreenFragment.this.systemPickerButton);
        }
      });
      setHasOptionsMenu(true);
    }
    updateActionBar();
    refresh();
    return this.mMainHomeScreenview;
  }
  
  public void onDetach()
  {
    OnClick.enableClicks(true);
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
    this.mMainHomeScreenview.setSystemUiVisibility(0);
    refresh();
  }
  
  public void refresh()
  {
    this.mState = 1;
    this.mArmSwitchProgress.setVisibility(4);
    Object localObject;
    if (!BlinkApp.getApp().getLoggedIn())
    {
      localObject = LoginFragment.newInstance(-1);
      this.mListener.onFragmentInteraction(this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, localObject);
      cleanUpHandler();
    }
    for (;;)
    {
      return;
      localObject = handler.obtainMessage(this.mState, this);
      handler.sendMessageDelayed((Message)localObject, 100L);
    }
  }
  
  public void showPopup(View paramView)
  {
    if (this.systemPickerWindow != null) {}
    for (;;)
    {
      return;
      cleanUpHandler();
      OnClick.enableClicks(false);
      this.systemPickerWindow = new PopupWindow(getContext());
      View localView = View.inflate(getContext(), 2130903147, null);
      this.systemPickerWindow.setContentView(localView);
      this.systemPickerWindow.setOutsideTouchable(true);
      this.systemPickerWindow.setFocusable(true);
      ArrayList localArrayList = new ArrayList(Arrays.asList(this.mNetworks.getNetworks()));
      SystemPickerAdapter localSystemPickerAdapter = new SystemPickerAdapter(getContext(), localArrayList);
      ListView localListView = (ListView)localView.findViewById(2131558764);
      localListView.setAdapter(localSystemPickerAdapter);
      this.systemPickerWindow.setWidth(-2);
      this.systemPickerWindow.setHeight(dpToPx(localArrayList.size() * 44 + 109));
      localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          HomeScreenFragment.access$1802(HomeScreenFragment.this, paramAnonymousInt);
          BlinkApp.getApp().setLastNetworkIndex(Integer.valueOf(HomeScreenFragment.this.mNetworkIndex));
          HomeScreenFragment.access$1902(HomeScreenFragment.this, String.valueOf(HomeScreenFragment.this.mNetworks.getNetworks()[HomeScreenFragment.this.mNetworkIndex].getId()));
          BlinkApp.getApp().setLastNetworkId(HomeScreenFragment.this.mNetworkId);
          HomeScreenFragment.access$2002(HomeScreenFragment.this, String.valueOf(HomeScreenFragment.this.mNetworks.getNetworks()[HomeScreenFragment.this.mNetworkIndex].getName()));
          BlinkApp.getApp().setLastNetworkName(HomeScreenFragment.this.mNetworkName);
          HomeScreenFragment.this.systemPickerButton.setText(HomeScreenFragment.this.systemPickerTitleForSystem(null));
          HomeScreenFragment.this.systemPickerWindow.dismiss();
        }
      });
      localView.findViewById(2131558765).setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          HomeScreenFragment.this.addSystem();
          HomeScreenFragment.this.systemPickerWindow.dismiss();
          return true;
        }
      });
      this.systemPickerWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
      {
        public void onDismiss()
        {
          HomeScreenFragment.this.updateHomescreen();
          HomeScreenFragment.access$2502(HomeScreenFragment.this, null);
          OnClick.enableClicks(true);
        }
      });
      this.systemPickerWindow.showAtLocation(paramView, 48, 0, paramView.getHeight());
    }
  }
  
  public void startClipList()
  {
    if (this.mDevices == null) {}
    for (;;)
    {
      return;
      this.mPolling = false;
      cleanUpHandler();
      Intent localIntent = new Intent(getActivity(), VideoListViewActivity.class);
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      for (int i = 0; i < this.mDevices.length; i++)
      {
        localArrayList2.add(this.mDevices[i].getName());
        localArrayList1.add(String.valueOf(this.mDevices[i].getDevice_id()));
      }
      localIntent.putExtra("arg_camera_name", localArrayList2);
      localIntent.putExtra("arg_camera_ids", localArrayList1);
      getActivity().startActivityForResult(localIntent, 107);
    }
  }
  
  public void updateHomeScreenImmediately()
  {
    this.mState = 1;
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
        this.view = HomeScreenFragment.this.mInflater.inflate(2130903121, HomeScreenFragment.this.mCameraListContainer, false);
        this.spinner = ((ContentLoadingProgressBar)this.view.findViewById(2131558541));
        this.spinner.hide();
        this.runningIcon = ((ImageView)this.view.findViewById(2131558698));
        if (!HomeScreenFragment.this.mNetworks.getNetworks()[HomeScreenFragment.this.mNetworkIndex].isArmed()) {
          break label414;
        }
        if (!paramQuickDeviceStatus.getEnabled().booleanValue()) {
          break label395;
        }
        this.runningIcon.setImageDrawable(HomeScreenFragment.this.getResources().getDrawable(2130837786));
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
            HomeScreenFragment.CameraItem.this.view.findViewById(2131558698).setVisibility(4);
            HomeScreenFragment.CameraItem.this.view.findViewById(2131558699).setVisibility(0);
            final boolean bool = paramQuickDeviceStatus.getEnabled().booleanValue();
            if (bool) {}
            for (paramAnonymousView = new DisableCameraAlertsRequest();; paramAnonymousView = new EnableCameraAlertsRequest())
            {
              OnClick.enableClicks(false);
              HomeScreenFragment.this.mArmSwitch.setEnabled(false);
              HomeScreenFragment.this.cleanUpHandler();
              BlinkAPI.BlinkAPIRequest(null, null, paramAnonymousView, new BlinkAPI.BlinkAPICallback()
              {
                public void onError(BlinkError paramAnonymous2BlinkError)
                {
                  OnClick.enableClicks(true);
                  HomeScreenFragment.this.mArmSwitch.setEnabled(true);
                  HomeScreenFragment.CameraItem.this.view.findViewById(2131558698).setVisibility(0);
                  HomeScreenFragment.CameraItem.this.view.findViewById(2131558699).setVisibility(4);
                  HomeScreenFragment.this.refresh();
                  if (HomeScreenFragment.CameraItem.1.this.val$cam.getEnabled().booleanValue()) {
                    HomeScreenFragment.CameraItem.this.runningIcon.setImageDrawable(HomeScreenFragment.this.getResources().getDrawable(2130837786));
                  }
                  for (;;)
                  {
                    return;
                    HomeScreenFragment.CameraItem.this.runningIcon.setImageDrawable(HomeScreenFragment.this.getResources().getDrawable(2130837787));
                  }
                }
                
                public void onResult(BlinkData paramAnonymous2BlinkData)
                {
                  HomeScreenFragment.access$302(HomeScreenFragment.this, (Command)paramAnonymous2BlinkData);
                  if (bool) {
                    HomeScreenFragment.this.startPollForCommandDone(3);
                  }
                  for (;;)
                  {
                    return;
                    HomeScreenFragment.this.startPollForCommandDone(2);
                  }
                }
              }, false);
              break;
            }
          }
        });
        this.thumbnail = ((ImageView)this.view.findViewById(2131558591));
        if (paramString == null) {
          break label425;
        }
        new ImageLoader(paramString, this.thumbnail, false, 2);
        this.spinner.setVisibility(8);
        this.thumbnail.invalidate();
      }
      for (;;)
      {
        this.statusLabel = ((TextView)this.view.findViewById(2131558696));
        this.statusIcon = ((ImageView)this.view.findViewById(2131558697));
        this.statusIcon.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            BlinkApp.getApp().setLastCameraId(String.valueOf(HomeScreenFragment.CameraItem.this.camera.getDevice_id()));
            ((MainActivity)HomeScreenFragment.this.getActivity()).startCameraSettings(HomeScreenFragment.CameraItem.this.camera.getDevice_id().intValue());
          }
        });
        this.liveButton = ((Button)this.view.findViewById(2131558690));
        this.liveButton.setOnClickListener(new View.OnClickListener()
        {
          /* Error */
          public void onClick(View paramAnonymousView)
          {
            // Byte code:
            //   0: invokestatic 39	com/immediasemi/blink/utils/OnClick:ok	()Z
            //   3: ifne +4 -> 7
            //   6: return
            //   7: aload_0
            //   8: getfield 21	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem$3:this$1	Lcom/immediasemi/blink/fragments/HomeScreenFragment$CameraItem;
            //   11: getfield 42	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem:this$0	Lcom/immediasemi/blink/fragments/HomeScreenFragment;
            //   14: iconst_0
            //   15: invokestatic 46	com/immediasemi/blink/fragments/HomeScreenFragment:access$902	(Lcom/immediasemi/blink/fragments/HomeScreenFragment;Z)Z
            //   18: pop
            //   19: invokestatic 52	com/immediasemi/blink/BlinkApp:getApp	()Lcom/immediasemi/blink/BlinkApp;
            //   22: aload_0
            //   23: getfield 21	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem$3:this$1	Lcom/immediasemi/blink/fragments/HomeScreenFragment$CameraItem;
            //   26: getfield 56	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem:camera	Lcom/immediasemi/blink/models/QuickDeviceStatus;
            //   29: invokevirtual 62	com/immediasemi/blink/models/QuickDeviceStatus:getName	()Ljava/lang/String;
            //   32: invokestatic 68	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
            //   35: invokevirtual 72	com/immediasemi/blink/BlinkApp:setLastCameraName	(Ljava/lang/String;)V
            //   38: aload_0
            //   39: getfield 21	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem$3:this$1	Lcom/immediasemi/blink/fragments/HomeScreenFragment$CameraItem;
            //   42: getfield 42	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem:this$0	Lcom/immediasemi/blink/fragments/HomeScreenFragment;
            //   45: invokevirtual 75	com/immediasemi/blink/fragments/HomeScreenFragment:cleanUpHandler	()V
            //   48: invokestatic 52	com/immediasemi/blink/BlinkApp:getApp	()Lcom/immediasemi/blink/BlinkApp;
            //   51: aload_0
            //   52: getfield 21	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem$3:this$1	Lcom/immediasemi/blink/fragments/HomeScreenFragment$CameraItem;
            //   55: getfield 42	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem:this$0	Lcom/immediasemi/blink/fragments/HomeScreenFragment;
            //   58: invokestatic 79	com/immediasemi/blink/fragments/HomeScreenFragment:access$1900	(Lcom/immediasemi/blink/fragments/HomeScreenFragment;)Ljava/lang/String;
            //   61: invokevirtual 82	com/immediasemi/blink/BlinkApp:setLastNetworkId	(Ljava/lang/String;)V
            //   64: invokestatic 52	com/immediasemi/blink/BlinkApp:getApp	()Lcom/immediasemi/blink/BlinkApp;
            //   67: aload_0
            //   68: getfield 21	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem$3:this$1	Lcom/immediasemi/blink/fragments/HomeScreenFragment$CameraItem;
            //   71: getfield 56	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem:camera	Lcom/immediasemi/blink/models/QuickDeviceStatus;
            //   74: invokevirtual 86	com/immediasemi/blink/models/QuickDeviceStatus:getDevice_id	()Ljava/lang/Integer;
            //   77: invokestatic 68	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
            //   80: invokevirtual 89	com/immediasemi/blink/BlinkApp:setLastCameraId	(Ljava/lang/String;)V
            //   83: aload_0
            //   84: getfield 21	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem$3:this$1	Lcom/immediasemi/blink/fragments/HomeScreenFragment$CameraItem;
            //   87: getfield 93	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem:thumbnail	Landroid/widget/ImageView;
            //   90: invokevirtual 99	android/widget/ImageView:getDrawable	()Landroid/graphics/drawable/Drawable;
            //   93: astore_2
            //   94: aload_2
            //   95: ifnull +125 -> 220
            //   98: aload_2
            //   99: invokevirtual 105	android/graphics/drawable/Drawable:getIntrinsicWidth	()I
            //   102: aload_2
            //   103: invokevirtual 108	android/graphics/drawable/Drawable:getIntrinsicHeight	()I
            //   106: getstatic 114	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
            //   109: invokestatic 120	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
            //   112: astore 5
            //   114: new 122	android/graphics/Canvas
            //   117: dup
            //   118: aload 5
            //   120: invokespecial 125	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
            //   123: astore_1
            //   124: aload_2
            //   125: iconst_0
            //   126: iconst_0
            //   127: aload_1
            //   128: invokevirtual 128	android/graphics/Canvas:getWidth	()I
            //   131: aload_1
            //   132: invokevirtual 131	android/graphics/Canvas:getHeight	()I
            //   135: invokevirtual 135	android/graphics/drawable/Drawable:setBounds	(IIII)V
            //   138: aload_2
            //   139: aload_1
            //   140: invokevirtual 139	android/graphics/drawable/Drawable:draw	(Landroid/graphics/Canvas;)V
            //   143: new 141	java/io/File
            //   146: dup
            //   147: aload_0
            //   148: getfield 21	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem$3:this$1	Lcom/immediasemi/blink/fragments/HomeScreenFragment$CameraItem;
            //   151: getfield 42	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem:this$0	Lcom/immediasemi/blink/fragments/HomeScreenFragment;
            //   154: invokevirtual 145	com/immediasemi/blink/fragments/HomeScreenFragment:getContext	()Landroid/content/Context;
            //   157: invokevirtual 151	android/content/Context:getCacheDir	()Ljava/io/File;
            //   160: ldc -103
            //   162: invokespecial 156	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
            //   165: astore 6
            //   167: aload 6
            //   169: invokevirtual 159	java/io/File:exists	()Z
            //   172: ifeq +9 -> 181
            //   175: aload 6
            //   177: invokevirtual 162	java/io/File:delete	()Z
            //   180: pop
            //   181: aconst_null
            //   182: astore_3
            //   183: aconst_null
            //   184: astore 4
            //   186: aload_3
            //   187: astore_1
            //   188: new 164	java/io/FileOutputStream
            //   191: astore_2
            //   192: aload_3
            //   193: astore_1
            //   194: aload_2
            //   195: aload 6
            //   197: invokespecial 167	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
            //   200: aload 5
            //   202: getstatic 173	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
            //   205: bipush 100
            //   207: aload_2
            //   208: invokevirtual 177	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
            //   211: pop
            //   212: aload_2
            //   213: ifnull +7 -> 220
            //   216: aload_2
            //   217: invokevirtual 180	java/io/FileOutputStream:close	()V
            //   220: new 182	android/content/Intent
            //   223: dup
            //   224: aload_0
            //   225: getfield 21	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem$3:this$1	Lcom/immediasemi/blink/fragments/HomeScreenFragment$CameraItem;
            //   228: getfield 42	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem:this$0	Lcom/immediasemi/blink/fragments/HomeScreenFragment;
            //   231: invokevirtual 186	com/immediasemi/blink/fragments/HomeScreenFragment:getActivity	()Landroid/support/v4/app/FragmentActivity;
            //   234: ldc -68
            //   236: invokespecial 191	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
            //   239: astore_1
            //   240: aload_0
            //   241: getfield 21	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem$3:this$1	Lcom/immediasemi/blink/fragments/HomeScreenFragment$CameraItem;
            //   244: getfield 42	com/immediasemi/blink/fragments/HomeScreenFragment$CameraItem:this$0	Lcom/immediasemi/blink/fragments/HomeScreenFragment;
            //   247: aload_1
            //   248: bipush 109
            //   250: invokevirtual 195	com/immediasemi/blink/fragments/HomeScreenFragment:startActivityForResult	(Landroid/content/Intent;I)V
            //   253: goto -247 -> 6
            //   256: astore_1
            //   257: aload_1
            //   258: invokevirtual 198	java/io/IOException:printStackTrace	()V
            //   261: goto -41 -> 220
            //   264: astore_3
            //   265: aload 4
            //   267: astore_2
            //   268: aload_2
            //   269: astore_1
            //   270: aload_3
            //   271: invokevirtual 199	java/lang/Exception:printStackTrace	()V
            //   274: aload_2
            //   275: ifnull -55 -> 220
            //   278: aload_2
            //   279: invokevirtual 180	java/io/FileOutputStream:close	()V
            //   282: goto -62 -> 220
            //   285: astore_1
            //   286: aload_1
            //   287: invokevirtual 198	java/io/IOException:printStackTrace	()V
            //   290: goto -70 -> 220
            //   293: astore_2
            //   294: aload_1
            //   295: astore_3
            //   296: aload_3
            //   297: ifnull +7 -> 304
            //   300: aload_3
            //   301: invokevirtual 180	java/io/FileOutputStream:close	()V
            //   304: aload_2
            //   305: athrow
            //   306: astore_1
            //   307: aload_1
            //   308: invokevirtual 198	java/io/IOException:printStackTrace	()V
            //   311: goto -7 -> 304
            //   314: astore_1
            //   315: aload_2
            //   316: astore_3
            //   317: aload_1
            //   318: astore_2
            //   319: goto -23 -> 296
            //   322: astore_3
            //   323: goto -55 -> 268
            // Local variable table:
            //   start	length	slot	name	signature
            //   0	326	0	this	3
            //   0	326	1	paramAnonymousView	View
            //   93	186	2	localObject1	Object
            //   293	23	2	localObject2	Object
            //   318	1	2	localView	View
            //   182	11	3	localObject3	Object
            //   264	7	3	localException1	Exception
            //   295	22	3	localObject4	Object
            //   322	1	3	localException2	Exception
            //   184	82	4	localObject5	Object
            //   112	89	5	localBitmap	android.graphics.Bitmap
            //   165	31	6	localFile	java.io.File
            // Exception table:
            //   from	to	target	type
            //   216	220	256	java/io/IOException
            //   188	192	264	java/lang/Exception
            //   194	200	264	java/lang/Exception
            //   278	282	285	java/io/IOException
            //   188	192	293	finally
            //   194	200	293	finally
            //   270	274	293	finally
            //   300	304	306	java/io/IOException
            //   200	212	314	finally
            //   200	212	322	java/lang/Exception
          }
        });
        this.picButton = ((Button)this.view.findViewById(2131558701));
        this.picButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {}
            for (;;)
            {
              return;
              BlinkApp.getApp().setLastNetworkId(HomeScreenFragment.this.mNetworkId);
              BlinkApp.getApp().setLastCameraId(String.valueOf(HomeScreenFragment.CameraItem.this.camera.getDevice_id()));
              HomeScreenFragment.CameraItem.this.spinner.show();
              OnClick.enableClicks(false);
              HomeScreenFragment.this.mArmSwitch.setEnabled(false);
              HomeScreenFragment.this.takePicture(HomeScreenFragment.CameraItem.this.index, String.valueOf(HomeScreenFragment.CameraItem.this.camera.getDevice_id()));
            }
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
        this.runningIcon.setImageDrawable(HomeScreenFragment.this.getResources().getDrawable(2130837787));
        break label137;
        label414:
        this.runningIcon.setVisibility(4);
        break label145;
        label425:
        paramQuickDeviceStatus = this.camera.getThumbnail();
        if (paramQuickDeviceStatus != null)
        {
          this.thumbnailUrl = paramQuickDeviceStatus;
          new ImageLoader(paramQuickDeviceStatus, this.thumbnail, false, 2);
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


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/HomeScreenFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */