package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Cameras.DeleteCameraRequest;
import com.immediasemi.blink.api.requests.Cameras.GetCameraInfoRequest;
import com.immediasemi.blink.api.requests.Cameras.GetCameraSignalStrength;
import com.immediasemi.blink.api.requests.Cameras.RefreshCameraRequest;
import com.immediasemi.blink.api.requests.Cameras.UpdateCameraRequest;
import com.immediasemi.blink.api.requests.Cameras.UpdateCameraRequest.ILLUMINATOR_ENABLE;
import com.immediasemi.blink.api.requests.Command.CommandRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.CameraConfig;
import com.immediasemi.blink.models.CameraConfigInfo;
import com.immediasemi.blink.models.Command;
import com.immediasemi.blink.models.Command.CONDITION_TYPE;
import com.immediasemi.blink.models.Commands;
import com.immediasemi.blink.models.SignalStrength;
import com.immediasemi.blink.utils.CustomSwitch;
import com.immediasemi.blink.utils.OnClick;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CameraSettingsFragment
  extends BaseFragment
{
  public static final String ARG_CAMERA_ID = "arg_camera_id";
  public static final String CAMERA_SETTINGS_TAG = "camera_settings_tag";
  private static final int COMMAND_POLL_FOR_SETTINGS_SAVE = 1;
  private static final int COMMAND_POLL_FOR_STATUS_REQUEST = 2;
  private static final int DELAY_MAX = 60;
  private static final int DELAY_MIN = 10;
  private static final int LENGTH_MAX = 10;
  private static final int LENGTH_MIN = 1;
  private static final int POLL_INTERVAL = 1000;
  private static final int SENSITIVITY_MAX = 9;
  private static final int SENSITIVITY_MIN = 1;
  private static ThisHandler handler = new ThisHandler();
  private CustomSwitch mAudioEnable;
  private ImageView mBatteryIV;
  private int mCamId;
  private CameraConfigInfo mCamera;
  private SeekBar mClipLengthSlider;
  private TextView mClipLengthValue;
  private Command mCommandResponse;
  private int mCommandType;
  private Button mDeleteCameraButton;
  private RadioButton mIlluminatorAuto;
  private RadioGroup mIlluminatorControl;
  private RadioButton mIlluminatorOff;
  private RadioButton mIlluminatorOn;
  private LayoutInflater mInflater;
  private RadioGroup mIntensityGroup;
  private RadioButton mIntensityHigh;
  private RadioButton mIntensityLow;
  private RadioButton mIntensityMedium;
  private TextView mLastUpdatedTV;
  private SeekBar mMotionDelaySlider;
  private TextView mMotionDelayValue;
  private CustomSwitch mMotionEnabled;
  private SeekBar mMotionSensitivitySlider;
  private TextView mMotionSensitivityValue;
  private EditText mNameET;
  private boolean mPolling;
  private Runnable mRunnable;
  private int mSectionNumber;
  private ImageView mSyncModuleStrengthIV;
  private TextView mTemperatureTV;
  private Button mUpdateButton;
  private View mView;
  private ImageView mWifiSignalStrengthIV;
  
  private void checkForCommandFinished()
  {
    CommandRequest localCommandRequest = new CommandRequest();
    localCommandRequest.setCommand(String.valueOf(this.mCommandResponse.getId()));
    handler.removeMessages(0);
    BlinkAPI.BlinkAPIRequest(null, null, localCommandRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        OnClick.blockAllClicks(false);
        if (CameraSettingsFragment.this.mPolling)
        {
          paramAnonymousBlinkError = CameraSettingsFragment.handler.obtainMessage(0, CameraSettingsFragment.this);
          CameraSettingsFragment.handler.sendMessageDelayed(paramAnonymousBlinkError, 100L);
        }
        while (CameraSettingsFragment.this.getActivity() == null) {
          return;
        }
        new AlertDialog.Builder(CameraSettingsFragment.this.getActivity()).setTitle(CameraSettingsFragment.this.getString(2131099811)).setMessage("Command failed").setPositiveButton(2131099886, null).create().show();
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if (((Commands)paramAnonymousBlinkData).isComplete() == true)
        {
          OnClick.blockAllClicks(false);
          if (((Commands)paramAnonymousBlinkData).getStatus() == 0)
          {
            CameraSettingsFragment.access$1402(CameraSettingsFragment.this, false);
            switch (CameraSettingsFragment.this.mCommandType)
            {
            }
          }
          while (CameraSettingsFragment.this.getActivity() == null)
          {
            return;
            CameraSettingsFragment.this.mRunnable.run();
            return;
            CameraSettingsFragment.this.loadStatus();
            return;
          }
          new AlertDialog.Builder(CameraSettingsFragment.this.getActivity()).setTitle(CameraSettingsFragment.this.getString(2131099811)).setMessage("Command failed").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              CameraSettingsFragment.this.refresh();
            }
          }).create().show();
          return;
        }
        paramAnonymousBlinkData = CameraSettingsFragment.handler.obtainMessage(0, CameraSettingsFragment.this);
        CameraSettingsFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 1000L);
      }
    }, false);
  }
  
  private int convertSliderPositionToUserSetting(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1 + paramInt2;
    paramInt1 = i;
    if (i > paramInt3) {
      paramInt1 = paramInt3;
    }
    paramInt3 = paramInt1;
    if (paramInt1 < paramInt2) {
      paramInt3 = paramInt2;
    }
    return paramInt3;
  }
  
  private int convertUserSettingToSliderPosition(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1 - paramInt2;
    paramInt3 -= paramInt2;
    paramInt1 = i;
    if (i < 0) {
      paramInt1 = 0;
    }
    paramInt2 = paramInt1;
    if (paramInt1 > paramInt3) {
      paramInt2 = paramInt3;
    }
    return paramInt2;
  }
  
  private void deleteCamera()
  {
    DeleteCameraRequest localDeleteCameraRequest = new DeleteCameraRequest();
    localDeleteCameraRequest.setNetwork(Integer.valueOf(BlinkApp.getApp().getLastNetworkId()));
    localDeleteCameraRequest.setId(Integer.valueOf(BlinkApp.getApp().getLastCameraId()));
    BlinkAPI.BlinkAPIRequest(null, null, localDeleteCameraRequest, new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (CameraSettingsFragment.this.getActivity() != null) {
          new AlertDialog.Builder(CameraSettingsFragment.this.getActivity()).setTitle("Error").setMessage("Could not delete camera").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).create().show();
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if (CameraSettingsFragment.this.getActivity() != null) {
          CameraSettingsFragment.this.getActivity().onBackPressed();
        }
      }
    }, false);
  }
  
  private void dispatchMessage(Message paramMessage)
  {
    handler.removeMessages(0);
    if ((getActivity() == null) || (this.mListener == null) || (isDetached())) {
      return;
    }
    checkForCommandFinished();
  }
  
  private void loadSettings()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new GetCameraInfoRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (CameraSettingsFragment.this.getActivity() != null) {
          new AlertDialog.Builder(CameraSettingsFragment.this.getActivity()).setTitle("Error").setMessage("Could not load settings").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).create().show();
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if (CameraSettingsFragment.this.getActivity() != null)
        {
          paramAnonymousBlinkData = (CameraConfig)paramAnonymousBlinkData;
          if (paramAnonymousBlinkData.getCameraConfig().length > 0)
          {
            CameraSettingsFragment.access$602(CameraSettingsFragment.this, paramAnonymousBlinkData.getCameraConfig()[0]);
            CameraSettingsFragment.this.updateSettings();
          }
        }
      }
    }, false);
  }
  
  private void loadStatus()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new GetCameraSignalStrength(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (CameraSettingsFragment.this.getActivity() != null) {
          new AlertDialog.Builder(CameraSettingsFragment.this.getActivity()).setTitle("Error").setMessage("Could not load status").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).create().show();
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if (CameraSettingsFragment.this.getActivity() != null)
        {
          paramAnonymousBlinkData = (SignalStrength)paramAnonymousBlinkData;
          CameraSettingsFragment.this.updateStatus(paramAnonymousBlinkData);
        }
      }
    }, false);
  }
  
  public static CameraSettingsFragment newInstance(int paramInt1, int paramInt2)
  {
    CameraSettingsFragment localCameraSettingsFragment = new CameraSettingsFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt1);
    localBundle.putInt("arg_camera_id", paramInt2);
    localCameraSettingsFragment.setArguments(localBundle);
    return localCameraSettingsFragment;
  }
  
  private void requestStatus()
  {
    this.mView.findViewById(2131558615).setVisibility(4);
    this.mView.findViewById(2131558614).setVisibility(4);
    this.mView.findViewById(2131558617).setVisibility(4);
    this.mView.findViewById(2131558618).setVisibility(4);
    this.mView.findViewById(2131558619).setVisibility(4);
    this.mView.findViewById(2131558620).setVisibility(4);
    this.mView.findViewById(2131558616).setVisibility(0);
    BlinkAPI.BlinkAPIRequest(null, null, new RefreshCameraRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        if (CameraSettingsFragment.this.getActivity() != null)
        {
          CameraSettingsFragment.this.loadStatus();
          new AlertDialog.Builder(CameraSettingsFragment.this.getActivity()).setTitle("Error").setMessage("Could not refresh status").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).create().show();
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if (CameraSettingsFragment.this.getActivity() != null)
        {
          CameraSettingsFragment.access$1002(CameraSettingsFragment.this, (Command)paramAnonymousBlinkData);
          CameraSettingsFragment.this.startPollForCommandDone(2);
        }
      }
    }, false);
  }
  
  private void startPollForCommandDone(int paramInt)
  {
    this.mPolling = true;
    this.mCommandType = paramInt;
    OnClick.blockAllClicks(true);
    Message localMessage = handler.obtainMessage(0, this);
    handler.sendMessageDelayed(localMessage, 1000L);
  }
  
  private void updateSettings()
  {
    this.mNameET.setText(this.mCamera.getName());
    this.mMotionEnabled.setChecked(this.mCamera.isEnabled());
    int i = convertUserSettingToSliderPosition(this.mCamera.getAlert_interval(), 10, 60);
    this.mMotionDelaySlider.setProgress(i);
    this.mMotionDelayValue.setText(String.valueOf(this.mCamera.getAlert_interval()) + "s");
    i = convertUserSettingToSliderPosition((int)this.mCamera.getMotion_sensitivity(), 1, 9);
    this.mMotionSensitivitySlider.setProgress(i);
    this.mMotionSensitivityValue.setText(String.valueOf((int)this.mCamera.getMotion_sensitivity()));
    i = convertUserSettingToSliderPosition(this.mCamera.getVideo_length(), 1, 10);
    this.mClipLengthSlider.setProgress(i);
    this.mClipLengthValue.setText(String.valueOf(this.mCamera.getVideo_length()) + "s");
    switch (this.mCamera.getIlluminator_enable())
    {
    default: 
      this.mIlluminatorControl.check(this.mIlluminatorOff.getId());
      if (this.mCamera.getIlluminator_intensity() < 3) {
        this.mIntensityGroup.check(this.mIntensityLow.getId());
      }
      break;
    }
    for (;;)
    {
      this.mAudioEnable.setChecked(this.mCamera.isRecord_audio_enable());
      return;
      this.mIlluminatorControl.check(this.mIlluminatorOn.getId());
      break;
      this.mIlluminatorControl.check(this.mIlluminatorAuto.getId());
      break;
      if (this.mCamera.getIlluminator_intensity() < 7) {
        this.mIntensityGroup.check(this.mIntensityMedium.getId());
      } else {
        this.mIntensityGroup.check(this.mIntensityHigh.getId());
      }
    }
  }
  
  private void updateStatus(SignalStrength paramSignalStrength)
  {
    String str2 = paramSignalStrength.getUpdated_at();
    Object localObject = new SimpleDateFormat("yyyy-MM-dd h:mm:ssZ", Locale.getDefault());
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MMM dd h:mm:ss a", Locale.getDefault());
    str2 = str2.replace('T', ' ');
    try
    {
      localObject = localSimpleDateFormat.format(((SimpleDateFormat)localObject).parse(str2));
      this.mLastUpdatedTV.setText((CharSequence)localObject);
      i = paramSignalStrength.getTemp();
      if (!BlinkApp.getApp().isTempUnits())
      {
        i = (i - 32) * 5 / 9;
        localObject = String.valueOf(String.valueOf(i) + getString(2131099796));
        this.mTemperatureTV.setText(String.valueOf(localObject));
        switch (paramSignalStrength.getLfr())
        {
        default: 
          this.mSyncModuleStrengthIV.setImageResource(2130837595);
          switch (paramSignalStrength.getWifi())
          {
          default: 
            this.mWifiSignalStrengthIV.setImageResource(2130837595);
            switch (paramSignalStrength.getBattery())
            {
            default: 
              this.mBatteryIV.setImageResource(2130837601);
              this.mView.findViewById(2131558615).setVisibility(0);
              this.mView.findViewById(2131558614).setVisibility(0);
              this.mView.findViewById(2131558617).setVisibility(0);
              this.mView.findViewById(2131558618).setVisibility(0);
              this.mView.findViewById(2131558619).setVisibility(0);
              this.mView.findViewById(2131558620).setVisibility(0);
              this.mView.findViewById(2131558616).setVisibility(4);
              return;
            }
            break;
          }
          break;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        int i;
        localException.printStackTrace();
        continue;
        String str1 = String.valueOf(String.valueOf(i) + getString(2131099797));
        continue;
        this.mSyncModuleStrengthIV.setImageResource(2130837596);
        continue;
        this.mSyncModuleStrengthIV.setImageResource(2130837597);
        continue;
        this.mSyncModuleStrengthIV.setImageResource(2130837598);
        continue;
        this.mSyncModuleStrengthIV.setImageResource(2130837599);
        continue;
        this.mWifiSignalStrengthIV.setImageResource(2130837596);
        continue;
        this.mWifiSignalStrengthIV.setImageResource(2130837597);
        continue;
        this.mWifiSignalStrengthIV.setImageResource(2130837598);
        continue;
        this.mWifiSignalStrengthIV.setImageResource(2130837599);
        continue;
        this.mBatteryIV.setImageResource(2130837602);
        continue;
        this.mBatteryIV.setImageResource(2130837600);
      }
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099991));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null)
    {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
      this.mCamId = getArguments().getInt("arg_camera_id");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mInflater = paramLayoutInflater;
    this.mView = paramLayoutInflater.inflate(2130903097, paramViewGroup, false);
    this.mNameET = ((EditText)this.mView.findViewById(2131558597));
    this.mMotionEnabled = ((CustomSwitch)this.mView.findViewById(2131558598));
    this.mMotionDelaySlider = ((SeekBar)this.mView.findViewById(2131558599));
    this.mMotionDelaySlider.setMax(50);
    this.mMotionDelaySlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousInt = CameraSettingsFragment.this.convertSliderPositionToUserSetting(paramAnonymousInt, 10, 60);
        CameraSettingsFragment.this.mMotionDelayValue.setText(String.valueOf(paramAnonymousInt) + "s");
      }
      
      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar) {}
      
      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar) {}
    });
    this.mMotionDelayValue = ((TextView)this.mView.findViewById(2131558600));
    this.mMotionSensitivitySlider = ((SeekBar)this.mView.findViewById(2131558601));
    this.mMotionSensitivitySlider.setMax(8);
    this.mMotionSensitivitySlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousInt = CameraSettingsFragment.this.convertSliderPositionToUserSetting(paramAnonymousInt, 1, 9);
        CameraSettingsFragment.this.mMotionSensitivityValue.setText(String.valueOf(paramAnonymousInt));
      }
      
      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar) {}
      
      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar) {}
    });
    this.mMotionSensitivityValue = ((TextView)this.mView.findViewById(2131558602));
    this.mClipLengthSlider = ((SeekBar)this.mView.findViewById(2131558603));
    this.mClipLengthSlider.setMax(9);
    this.mClipLengthSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        paramAnonymousInt = CameraSettingsFragment.this.convertSliderPositionToUserSetting(paramAnonymousInt, 1, 10);
        CameraSettingsFragment.this.mClipLengthValue.setText(String.valueOf(paramAnonymousInt) + "s");
      }
      
      public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar) {}
      
      public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar) {}
    });
    this.mClipLengthValue = ((TextView)this.mView.findViewById(2131558604));
    this.mIlluminatorControl = ((RadioGroup)this.mView.findViewById(2131558605));
    this.mIlluminatorOff = ((RadioButton)this.mView.findViewById(2131558606));
    this.mIlluminatorOn = ((RadioButton)this.mView.findViewById(2131558607));
    this.mIlluminatorAuto = ((RadioButton)this.mView.findViewById(2131558608));
    this.mIntensityGroup = ((RadioGroup)this.mView.findViewById(2131558609));
    this.mIntensityLow = ((RadioButton)this.mView.findViewById(2131558610));
    this.mIntensityMedium = ((RadioButton)this.mView.findViewById(2131558611));
    this.mIntensityHigh = ((RadioButton)this.mView.findViewById(2131558612));
    this.mAudioEnable = ((CustomSwitch)this.mView.findViewById(2131558613));
    this.mLastUpdatedTV = ((TextView)this.mView.findViewById(2131558614));
    this.mUpdateButton = ((Button)this.mView.findViewById(2131558615));
    this.mUpdateButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        CameraSettingsFragment.this.requestStatus();
      }
    });
    this.mTemperatureTV = ((TextView)this.mView.findViewById(2131558617));
    this.mWifiSignalStrengthIV = ((ImageView)this.mView.findViewById(2131558618));
    this.mSyncModuleStrengthIV = ((ImageView)this.mView.findViewById(2131558619));
    this.mBatteryIV = ((ImageView)this.mView.findViewById(2131558620));
    this.mDeleteCameraButton = ((Button)this.mView.findViewById(2131558621));
    this.mDeleteCameraButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        while (CameraSettingsFragment.this.getActivity() == null) {
          return;
        }
        new AlertDialog.Builder(CameraSettingsFragment.this.getActivity()).setTitle("Delete Camera").setMessage("Do you wish to continue?").setPositiveButton(2131100015, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            CameraSettingsFragment.this.deleteCamera();
          }
        }).setNegativeButton(2131099883, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).create().show();
      }
    });
    loadSettings();
    loadStatus();
    return this.mView;
  }
  
  public void onPause()
  {
    super.onPause();
    if (this.mNameET != null)
    {
      this.mNameET.clearFocus();
      ((InputMethodManager)getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mNameET.getWindowToken(), 0);
    }
  }
  
  public void saveCameraSettings(Runnable paramRunnable)
  {
    this.mRunnable = paramRunnable;
    UpdateCameraRequest localUpdateCameraRequest = new UpdateCameraRequest();
    localUpdateCameraRequest.setCamera(Integer.valueOf(this.mCamId));
    localUpdateCameraRequest.setId(Integer.valueOf(this.mCamId));
    localUpdateCameraRequest.setNetwork(Integer.valueOf(this.mCamera.getNetwork_id()));
    localUpdateCameraRequest.setName(this.mNameET.getText().toString());
    localUpdateCameraRequest.setThumbnail(null);
    localUpdateCameraRequest.setMac_address(this.mCamera.getMac_address());
    localUpdateCameraRequest.setLiveview_enabled(com.immediasemi.blink.api.requests.Cameras.UpdateCameraRequest.LIVEVIEW_ENABLED.values()[this.mCamera.getLiveview_enabled().ordinal()]);
    localUpdateCameraRequest.setSiren_enable(this.mCamera.isSiren_enabled());
    localUpdateCameraRequest.setMotion_sensitivity(convertSliderPositionToUserSetting(this.mMotionSensitivitySlider.getProgress(), 1, 9));
    localUpdateCameraRequest.setMotion_alert(Boolean.valueOf(this.mMotionEnabled.isChecked()));
    localUpdateCameraRequest.setVideo_length(Integer.valueOf(convertSliderPositionToUserSetting(this.mClipLengthSlider.getProgress(), 1, 10)));
    localUpdateCameraRequest.setAlert_interval(Integer.valueOf(convertSliderPositionToUserSetting(this.mMotionDelaySlider.getProgress(), 10, 60)));
    localUpdateCameraRequest.setCamera_sequence(0);
    localUpdateCameraRequest.setBuzzer_on(this.mCamera.isAlert_tone_enable());
    localUpdateCameraRequest.setAlert_tone_volume(this.mCamera.getAlert_tone_volume());
    localUpdateCameraRequest.setAlert_repeat(com.immediasemi.blink.api.requests.Cameras.UpdateCameraRequest.ALERT_REPEAT.values()[this.mCamera.getAlert_repeat().ordinal()]);
    localUpdateCameraRequest.setTemp_alert(this.mCamera.isTemp_alarm_enable());
    localUpdateCameraRequest.setTemp_adjust(this.mCamera.getTemp_adjust());
    localUpdateCameraRequest.setTemp_min(this.mCamera.getTemp_min());
    localUpdateCameraRequest.setTemp_max(this.mCamera.getTemp_max());
    localUpdateCameraRequest.setTemp_hysteresis(this.mCamera.getTemp_hysteresis());
    int i;
    if (this.mIlluminatorOff.isChecked())
    {
      paramRunnable = UpdateCameraRequest.ILLUMINATOR_ENABLE.off;
      localUpdateCameraRequest.setIlluminator_enable(paramRunnable);
      localUpdateCameraRequest.setIlluminator_duration(this.mCamera.getIlluminator_duration());
      if (!this.mIntensityLow.isChecked()) {
        break label475;
      }
      i = 1;
    }
    for (;;)
    {
      localUpdateCameraRequest.setIlluminator_intensity(i);
      localUpdateCameraRequest.setBattery_alarm_enable(this.mCamera.isBattery_alarm_enable());
      localUpdateCameraRequest.setBattery_voltage_threshold(this.mCamera.getBattery_voltage_threshold());
      localUpdateCameraRequest.setBattery_voltage_hysteresis(this.mCamera.getBattery_voltage_hysteresis());
      localUpdateCameraRequest.setInvert_image(this.mCamera.isInvert_image());
      localUpdateCameraRequest.setFlip_image(this.mCamera.isFlip_image());
      localUpdateCameraRequest.setRecord_audio_enable(Boolean.valueOf(this.mCamera.isRecord_audio_enable()));
      localUpdateCameraRequest.setClip_bitrate(this.mCamera.getClip_rate());
      localUpdateCameraRequest.setLiveview_bitrate(this.mCamera.getLive_view_rate());
      localUpdateCameraRequest.setRecord_audio_enable(Boolean.valueOf(this.mAudioEnable.isChecked()));
      BlinkAPI.BlinkAPIRequest(null, null, localUpdateCameraRequest, new BlinkAPI.BlinkAPICallback()
      {
        public void onError(BlinkError paramAnonymousBlinkError)
        {
          if (CameraSettingsFragment.this.getActivity() != null) {
            new AlertDialog.Builder(CameraSettingsFragment.this.getActivity()).setTitle("Error").setMessage("Could not save settings").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                CameraSettingsFragment.this.mRunnable.run();
              }
            }).create().show();
          }
        }
        
        public void onResult(BlinkData paramAnonymousBlinkData)
        {
          if (CameraSettingsFragment.this.getActivity() != null)
          {
            CameraSettingsFragment.access$1002(CameraSettingsFragment.this, (Command)paramAnonymousBlinkData);
            if (CameraSettingsFragment.this.mCommandResponse.getState_condition() == Command.CONDITION_TYPE.done) {
              CameraSettingsFragment.this.mRunnable.run();
            }
          }
          else
          {
            return;
          }
          CameraSettingsFragment.this.startPollForCommandDone(1);
        }
      }, false);
      return;
      if (this.mIlluminatorOn.isChecked())
      {
        paramRunnable = UpdateCameraRequest.ILLUMINATOR_ENABLE.on;
        break;
      }
      paramRunnable = UpdateCameraRequest.ILLUMINATOR_ENABLE.auto;
      break;
      label475:
      if (this.mIntensityMedium.isChecked()) {
        i = 5;
      } else {
        i = 9;
      }
    }
  }
  
  static class ThisHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.obj != null) {
        ((CameraSettingsFragment)paramMessage.obj).dispatchMessage(paramMessage);
      }
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/CameraSettingsFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */