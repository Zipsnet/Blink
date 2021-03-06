package com.immediasemi.blink.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.VideoLiveViewActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Cameras.GetCameraSignalStrength;
import com.immediasemi.blink.api.requests.Cameras.GetSingleCameraStatusRequest;
import com.immediasemi.blink.api.requests.Cameras.RefreshCameraRequest;
import com.immediasemi.blink.api.requests.Cameras.TakeSnapshotRequest;
import com.immediasemi.blink.api.requests.Command.CommandRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.CameraStatus;
import com.immediasemi.blink.models.Command;
import com.immediasemi.blink.models.Command.CONDITION_TYPE;
import com.immediasemi.blink.models.Commands;
import com.immediasemi.blink.models.DeviceStatus;
import com.immediasemi.blink.models.SignalStrength;
import com.immediasemi.blink.utils.ImageLoader;
import com.immediasemi.blink.utils.OnClick;
import java.util.HashMap;

public class AddCamera_4_SuccessFragment
  extends BaseFragment
{
  public static final int ADD_CAMERA_INDEX = 4;
  private static final int POLL_FOR_CHANGED_THUMBNAIL = 2;
  private static final int POLL_FOR_REFRESH = 3;
  private static final int POLL_FOR_REFRESH_DONE = 4;
  private static final int POLL_FOR_THUMBNAIL = 1;
  private static final int POLL_INTERVAL = 1000;
  private static ThisHandler handler = new ThisHandler();
  private Command mCommandResponse;
  private TextView mLFRText;
  private boolean mPolling;
  private ContentLoadingProgressBar mProgress;
  private int mSectionNumber;
  private Button mSignalStrength;
  private ImageView mSignalStrengthLFRView;
  private ImageView mSignalStrengthWiFiView;
  private TextView mSignalsStrengthText;
  private int mState;
  private ImageButton mTapToSee;
  private ImageView mThumbnail;
  private View mView;
  private TextView mWiFiText;
  
  private void checkForRefresh()
  {
    CommandRequest localCommandRequest = new CommandRequest();
    localCommandRequest.setCommand(String.valueOf(this.mCommandResponse.getId()));
    BlinkAPI.BlinkAPIRequest(null, null, localCommandRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (AddCamera_4_SuccessFragment.this.mPolling)
        {
          paramAnonymousBlinkError = AddCamera_4_SuccessFragment.handler.obtainMessage(3, AddCamera_4_SuccessFragment.this);
          AddCamera_4_SuccessFragment.handler.sendMessageDelayed(paramAnonymousBlinkError, 100L);
        }
        label108:
        for (;;)
        {
          return;
          AddCamera_4_SuccessFragment.this.hideSignalStrengthWidgets();
          if (paramAnonymousBlinkError.response != null) {}
          for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = "Command Failed")
          {
            if (AddCamera_4_SuccessFragment.this.getActivity() == null) {
              break label108;
            }
            new AlertDialog.Builder(AddCamera_4_SuccessFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).create().show();
            break;
          }
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        paramAnonymousBlinkData = ((Commands)paramAnonymousBlinkData).getCommands();
        int j = AddCamera_4_SuccessFragment.this.mCommandResponse.getId();
        int i = 0;
        if (i < paramAnonymousBlinkData.length)
        {
          Message localMessage = paramAnonymousBlinkData[i];
          if (localMessage.getId() == j)
          {
            if (!localMessage.getState_condition().equals(Command.CONDITION_TYPE.done)) {
              break label110;
            }
            AddCamera_4_SuccessFragment.access$402(AddCamera_4_SuccessFragment.this, false);
            AddCamera_4_SuccessFragment.access$1602(AddCamera_4_SuccessFragment.this, 4);
            localMessage = AddCamera_4_SuccessFragment.handler.obtainMessage(AddCamera_4_SuccessFragment.this.mState, AddCamera_4_SuccessFragment.this);
            AddCamera_4_SuccessFragment.handler.sendMessageDelayed(localMessage, 1000L);
          }
          for (;;)
          {
            i++;
            break;
            label110:
            AddCamera_4_SuccessFragment.access$1602(AddCamera_4_SuccessFragment.this, 3);
            localMessage = AddCamera_4_SuccessFragment.handler.obtainMessage(AddCamera_4_SuccessFragment.this.mState, AddCamera_4_SuccessFragment.this);
            AddCamera_4_SuccessFragment.handler.sendMessageDelayed(localMessage, 1000L);
          }
        }
      }
    }, false);
  }
  
  private void checkForThumbnailFinished()
  {
    CommandRequest localCommandRequest = new CommandRequest();
    localCommandRequest.setCommand(String.valueOf(this.mCommandResponse.getId()));
    BlinkAPI.BlinkAPIRequest(null, null, localCommandRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (AddCamera_4_SuccessFragment.this.mPolling)
        {
          paramAnonymousBlinkError = AddCamera_4_SuccessFragment.handler.obtainMessage(1, AddCamera_4_SuccessFragment.this);
          AddCamera_4_SuccessFragment.handler.sendMessageDelayed(paramAnonymousBlinkError, 100L);
        }
        do
        {
          return;
          AddCamera_4_SuccessFragment.this.mTapToSee.setVisibility(0);
          AddCamera_4_SuccessFragment.this.mThumbnail.setVisibility(8);
          AddCamera_4_SuccessFragment.this.mProgress.setVisibility(8);
        } while (AddCamera_4_SuccessFragment.this.getActivity() == null);
        if (paramAnonymousBlinkError.response != null) {}
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
        {
          new AlertDialog.Builder(AddCamera_4_SuccessFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).create().show();
          break;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        paramAnonymousBlinkData = ((Commands)paramAnonymousBlinkData).getCommands();
        int j = AddCamera_4_SuccessFragment.this.mCommandResponse.getId();
        int i = 0;
        if (i < paramAnonymousBlinkData.length)
        {
          Message localMessage = paramAnonymousBlinkData[i];
          if (localMessage.getId() == j)
          {
            if (!localMessage.getState_condition().equals(Command.CONDITION_TYPE.done)) {
              break label110;
            }
            AddCamera_4_SuccessFragment.access$402(AddCamera_4_SuccessFragment.this, false);
            AddCamera_4_SuccessFragment.access$1602(AddCamera_4_SuccessFragment.this, 2);
            localMessage = AddCamera_4_SuccessFragment.handler.obtainMessage(AddCamera_4_SuccessFragment.this.mState, AddCamera_4_SuccessFragment.this);
            AddCamera_4_SuccessFragment.handler.sendMessageDelayed(localMessage, 100L);
          }
          for (;;)
          {
            i++;
            break;
            label110:
            AddCamera_4_SuccessFragment.access$1602(AddCamera_4_SuccessFragment.this, 1);
            localMessage = AddCamera_4_SuccessFragment.handler.obtainMessage(AddCamera_4_SuccessFragment.this.mState, AddCamera_4_SuccessFragment.this);
            AddCamera_4_SuccessFragment.handler.sendMessageDelayed(localMessage, 1000L);
          }
        }
      }
    }, false);
  }
  
  private void checkForThumbnailUpdated()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new GetSingleCameraStatusRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if ((AddCamera_4_SuccessFragment.this.getActivity() == null) || (AddCamera_4_SuccessFragment.this.mListener == null) || (AddCamera_4_SuccessFragment.this.isDetached())) {
          return;
        }
        if (paramAnonymousBlinkError.response != null) {}
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
        {
          AddCamera_4_SuccessFragment.this.mTapToSee.setVisibility(0);
          AddCamera_4_SuccessFragment.this.mThumbnail.setVisibility(8);
          AddCamera_4_SuccessFragment.this.mProgress.setVisibility(8);
          if (AddCamera_4_SuccessFragment.this.getActivity() == null) {
            break;
          }
          new AlertDialog.Builder(AddCamera_4_SuccessFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).create().show();
          break;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((AddCamera_4_SuccessFragment.this.getActivity() == null) || (AddCamera_4_SuccessFragment.this.mListener == null) || (AddCamera_4_SuccessFragment.this.isDetached())) {}
        for (;;)
        {
          return;
          new ImageLoader(((CameraStatus)paramAnonymousBlinkData).getCamera_status().getThumbnail(), AddCamera_4_SuccessFragment.this.mThumbnail, true, 2);
          AddCamera_4_SuccessFragment.this.mThumbnail.invalidate();
          AddCamera_4_SuccessFragment.this.mThumbnail.setVisibility(0);
          AddCamera_4_SuccessFragment.this.mProgress.setVisibility(8);
        }
      }
    }, false);
  }
  
  private void cleanUpHandler()
  {
    handler.removeMessages(1);
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
      default: 
        break;
      case 1: 
        checkForThumbnailFinished();
        break;
      case 2: 
        checkForThumbnailUpdated();
        break;
      case 3: 
        checkForRefresh();
        break;
      case 4: 
        fetchSignalStrengths();
      }
    }
  }
  
  private void fetchSignalStrengths()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new GetCameraSignalStrength(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (AddCamera_4_SuccessFragment.this.getActivity() != null) {
          if (paramAnonymousBlinkError.response == null) {
            break label61;
          }
        }
        label61:
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
        {
          new AlertDialog.Builder(AddCamera_4_SuccessFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).create().show();
          return;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        paramAnonymousBlinkData = (SignalStrength)paramAnonymousBlinkData;
        switch (paramAnonymousBlinkData.getLfr())
        {
        default: 
          AddCamera_4_SuccessFragment.this.mSignalStrengthLFRView.setImageResource(2130837595);
          switch (paramAnonymousBlinkData.getWifi())
          {
          default: 
            AddCamera_4_SuccessFragment.this.mSignalStrengthWiFiView.setImageResource(2130837595);
          }
          break;
        }
        for (;;)
        {
          AddCamera_4_SuccessFragment.this.showSignalStrengthWidgets();
          return;
          AddCamera_4_SuccessFragment.this.mSignalStrengthLFRView.setImageResource(2130837596);
          break;
          AddCamera_4_SuccessFragment.this.mSignalStrengthLFRView.setImageResource(2130837597);
          break;
          AddCamera_4_SuccessFragment.this.mSignalStrengthLFRView.setImageResource(2130837598);
          break;
          AddCamera_4_SuccessFragment.this.mSignalStrengthLFRView.setImageResource(2130837599);
          break;
          AddCamera_4_SuccessFragment.this.mSignalStrengthWiFiView.setImageResource(2130837596);
          continue;
          AddCamera_4_SuccessFragment.this.mSignalStrengthWiFiView.setImageResource(2130837597);
          continue;
          AddCamera_4_SuccessFragment.this.mSignalStrengthWiFiView.setImageResource(2130837598);
          continue;
          AddCamera_4_SuccessFragment.this.mSignalStrengthWiFiView.setImageResource(2130837599);
        }
      }
    }, false);
  }
  
  private void fetchStatus()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new RefreshCameraRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        AddCamera_4_SuccessFragment.this.hideSignalStrengthWidgets();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        AddCamera_4_SuccessFragment.access$802(AddCamera_4_SuccessFragment.this, (Command)paramAnonymousBlinkData);
        AddCamera_4_SuccessFragment.access$402(AddCamera_4_SuccessFragment.this, true);
        paramAnonymousBlinkData = AddCamera_4_SuccessFragment.handler.obtainMessage(3, AddCamera_4_SuccessFragment.this);
        AddCamera_4_SuccessFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 1000L);
      }
    }, false);
  }
  
  private void hideSignalStrengthWidgets()
  {
    this.mSignalsStrengthText.setVisibility(0);
    this.mSignalStrength.setVisibility(0);
    this.mProgress.setVisibility(4);
    this.mSignalStrengthLFRView.setVisibility(4);
    this.mSignalStrengthWiFiView.setVisibility(4);
    this.mWiFiText.setVisibility(4);
    this.mLFRText.setVisibility(4);
  }
  
  public static AddCamera_4_SuccessFragment newInstance(int paramInt)
  {
    AddCamera_4_SuccessFragment localAddCamera_4_SuccessFragment = new AddCamera_4_SuccessFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAddCamera_4_SuccessFragment.setArguments(localBundle);
    return localAddCamera_4_SuccessFragment;
  }
  
  private void showSignalStrengthWidgets()
  {
    this.mProgress.setVisibility(4);
    this.mSignalsStrengthText.setVisibility(4);
    this.mSignalStrength.setVisibility(4);
    this.mSignalStrengthLFRView.setVisibility(0);
    this.mSignalStrengthWiFiView.setVisibility(0);
    this.mWiFiText.setVisibility(0);
    this.mLFRText.setVisibility(0);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        AddCamera_4_SuccessFragment.this.hideSignalStrengthWidgets();
      }
    }, 5000L);
  }
  
  private void takeAPicture()
  {
    this.mTapToSee.setVisibility(4);
    this.mThumbnail.setVisibility(0);
    this.mProgress.setVisibility(0);
    BlinkAPI.BlinkAPIRequest(null, null, new TakeSnapshotRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        AddCamera_4_SuccessFragment.this.mTapToSee.setVisibility(0);
        AddCamera_4_SuccessFragment.this.mThumbnail.setVisibility(8);
        if (AddCamera_4_SuccessFragment.this.getActivity() != null) {
          if (paramAnonymousBlinkError.response == null) {
            break label84;
          }
        }
        label84:
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
        {
          new AlertDialog.Builder(AddCamera_4_SuccessFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).create().show();
          return;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        AddCamera_4_SuccessFragment.access$802(AddCamera_4_SuccessFragment.this, (Command)paramAnonymousBlinkData);
        AddCamera_4_SuccessFragment.access$402(AddCamera_4_SuccessFragment.this, true);
        paramAnonymousBlinkData = AddCamera_4_SuccessFragment.handler.obtainMessage(1, AddCamera_4_SuccessFragment.this);
        AddCamera_4_SuccessFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 1000L);
      }
    }, false);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 4).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903093, paramViewGroup, false);
    this.mSignalStrengthLFRView = ((ImageView)this.mView.findViewById(2131558588));
    this.mSignalStrengthWiFiView = ((ImageView)this.mView.findViewById(2131558586));
    this.mSignalStrength = ((Button)this.mView.findViewById(2131558589));
    this.mWiFiText = ((TextView)this.mView.findViewById(2131558585));
    this.mLFRText = ((TextView)this.mView.findViewById(2131558587));
    this.mSignalStrength.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          AddCamera_4_SuccessFragment.this.mProgress.setVisibility(0);
          AddCamera_4_SuccessFragment.this.mSignalsStrengthText.setVisibility(4);
          AddCamera_4_SuccessFragment.this.mSignalStrength.setVisibility(4);
          AddCamera_4_SuccessFragment.this.fetchStatus();
        }
      }
    });
    this.mTapToSee = ((ImageButton)this.mView.findViewById(2131558590));
    this.mTapToSee.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          AddCamera_4_SuccessFragment.access$402(AddCamera_4_SuccessFragment.this, false);
          if ((0 != 0) && (Build.VERSION.SDK_INT == 19))
          {
            AddCamera_4_SuccessFragment.this.takeAPicture();
          }
          else
          {
            paramAnonymousView = new Intent(AddCamera_4_SuccessFragment.this.getActivity(), VideoLiveViewActivity.class);
            AddCamera_4_SuccessFragment.this.startActivityForResult(paramAnonymousView, 109);
          }
        }
      }
    });
    this.mThumbnail = ((ImageView)this.mView.findViewById(2131558591));
    this.mProgress = ((ContentLoadingProgressBar)this.mView.findViewById(2131558541));
    this.mSignalsStrengthText = ((TextView)this.mView.findViewById(2131558584));
    ((Button)this.mView.findViewById(2131558554)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          paramAnonymousView = AddCamera_5_NameCameraFragment.newInstance(-1);
          AddCamera_4_SuccessFragment.this.mListener.onFragmentInteraction(AddCamera_4_SuccessFragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
        }
      }
    });
    return this.mView;
  }
  
  static class ThisHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.obj != null) {
        ((AddCamera_4_SuccessFragment)paramMessage.obj).dispatchMessage(paramMessage);
      }
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/AddCamera_4_SuccessFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */