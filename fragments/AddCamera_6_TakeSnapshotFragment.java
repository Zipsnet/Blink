package com.immediasemi.blink.fragments;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Cameras.GetSingleCameraStatusRequest;
import com.immediasemi.blink.api.requests.Cameras.TakeSnapshotRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.CameraStatus;
import com.immediasemi.blink.models.Command;
import com.immediasemi.blink.models.DeviceStatus;
import com.immediasemi.blink.utils.ImageLoader;
import com.immediasemi.blink.utils.OnClick;
import java.util.HashMap;

public class AddCamera_6_TakeSnapshotFragment
  extends BaseFragment
{
  public static final int ADD_CAMERA_INDEX = 6;
  private static final int POLL_FOR_THUMBNAIL = 1;
  private static final int POLL_INTERVAL = 1000;
  static ThisHandler handler = new ThisHandler();
  private Command mCommandResponse;
  private boolean mPolling;
  private int mSectionNumber;
  private ImageView mTakeSnapImage;
  private ImageView mThumbnailImageView;
  private View mView;
  
  private void checkForThumbnailUpdated()
  {
    BlinkAPI.BlinkAPIRequest(null, null, new GetSingleCameraStatusRequest(), new BlinkAPI.BlinkAPICallback()
    {
      public void onError(BlinkError paramAnonymousBlinkError)
      {
        AddCamera_6_TakeSnapshotFragment.this.mView.findViewById(2131558541).setVisibility(4);
        if ((AddCamera_6_TakeSnapshotFragment.this.getActivity() == null) || (AddCamera_6_TakeSnapshotFragment.this.mListener == null) || (AddCamera_6_TakeSnapshotFragment.this.isDetached())) {
          return;
        }
        if (AddCamera_6_TakeSnapshotFragment.this.getActivity() != null) {
          if (paramAnonymousBlinkError.response == null) {
            break label110;
          }
        }
        label110:
        for (paramAnonymousBlinkError = (String)paramAnonymousBlinkError.response.get("message");; paramAnonymousBlinkError = paramAnonymousBlinkError.getErrorMessage())
        {
          new AlertDialog.Builder(AddCamera_6_TakeSnapshotFragment.this.getActivity()).setMessage(paramAnonymousBlinkError).setPositiveButton(2131099891, null).create().show();
          break;
          break;
        }
      }
      
      public void onResult(BlinkData paramAnonymousBlinkData)
      {
        if ((AddCamera_6_TakeSnapshotFragment.this.getActivity() == null) || (AddCamera_6_TakeSnapshotFragment.this.mListener == null) || (AddCamera_6_TakeSnapshotFragment.this.isDetached())) {}
        for (;;)
        {
          return;
          paramAnonymousBlinkData = ((CameraStatus)paramAnonymousBlinkData).getCamera_status();
          if ((paramAnonymousBlinkData.getThumbnail() != null) && (paramAnonymousBlinkData.getThumbnail().length() > 0))
          {
            new ImageLoader(paramAnonymousBlinkData.getThumbnail(), AddCamera_6_TakeSnapshotFragment.this.mThumbnailImageView, true, 2);
            AddCamera_6_TakeSnapshotFragment.this.mThumbnailImageView.invalidate();
            AddCamera_6_TakeSnapshotFragment.this.mView.findViewById(2131558541).setVisibility(4);
            AddCamera_6_TakeSnapshotFragment.this.mView.findViewById(2131558554).setVisibility(0);
          }
          else
          {
            paramAnonymousBlinkData = AddCamera_6_TakeSnapshotFragment.handler.obtainMessage(1, AddCamera_6_TakeSnapshotFragment.this);
            AddCamera_6_TakeSnapshotFragment.handler.sendMessageDelayed(paramAnonymousBlinkData, 1000L);
          }
        }
      }
    }, false);
  }
  
  private void cleanupHandler()
  {
    handler.removeMessages(1);
  }
  
  private void dispatchMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    }
    for (;;)
    {
      return;
      checkForThumbnailUpdated();
    }
  }
  
  public static AddCamera_6_TakeSnapshotFragment newInstance(int paramInt)
  {
    AddCamera_6_TakeSnapshotFragment localAddCamera_6_TakeSnapshotFragment = new AddCamera_6_TakeSnapshotFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAddCamera_6_TakeSnapshotFragment.setArguments(localBundle);
    return localAddCamera_6_TakeSnapshotFragment;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 6).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903095, paramViewGroup, false);
    this.mTakeSnapImage = ((ImageView)this.mView.findViewById(2131558593));
    this.mThumbnailImageView = ((ImageView)this.mView.findViewById(2131558591));
    this.mTakeSnapImage.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          AddCamera_6_TakeSnapshotFragment.this.mView.findViewById(2131558541).setVisibility(0);
          BlinkAPI.BlinkAPIRequest(null, null, new TakeSnapshotRequest(), new BlinkAPI.BlinkAPICallback()
          {
            public void onError(BlinkError paramAnonymous2BlinkError)
            {
              AddCamera_6_TakeSnapshotFragment.this.mView.findViewById(2131558541).setVisibility(4);
              if (AddCamera_6_TakeSnapshotFragment.this.getActivity() != null) {
                if (paramAnonymous2BlinkError.response == null) {
                  break label86;
                }
              }
              label86:
              for (paramAnonymous2BlinkError = (String)paramAnonymous2BlinkError.response.get("message");; paramAnonymous2BlinkError = paramAnonymous2BlinkError.getErrorMessage())
              {
                new AlertDialog.Builder(AddCamera_6_TakeSnapshotFragment.this.getActivity()).setMessage(paramAnonymous2BlinkError).setPositiveButton(2131099891, null).create().show();
                return;
              }
            }
            
            public void onResult(BlinkData paramAnonymous2BlinkData)
            {
              AddCamera_6_TakeSnapshotFragment.access$102(AddCamera_6_TakeSnapshotFragment.this, (Command)paramAnonymous2BlinkData);
              AddCamera_6_TakeSnapshotFragment.access$202(AddCamera_6_TakeSnapshotFragment.this, true);
              paramAnonymous2BlinkData = AddCamera_6_TakeSnapshotFragment.handler.obtainMessage(1, AddCamera_6_TakeSnapshotFragment.this);
              AddCamera_6_TakeSnapshotFragment.handler.sendMessageDelayed(paramAnonymous2BlinkData, 1000L);
            }
          }, false);
        }
      }
    });
    ((Button)this.mView.findViewById(2131558554)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          AddCamera_6_TakeSnapshotFragment.this.cleanupHandler();
          paramAnonymousView = AddCamera_7_AddCameraFragment.newInstance(-1);
          AddCamera_6_TakeSnapshotFragment.this.mListener.onFragmentInteraction(AddCamera_6_TakeSnapshotFragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
        }
      }
    });
    return this.mView;
  }
  
  public void onDetach()
  {
    super.onDetach();
    cleanupHandler();
  }
  
  static class ThisHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.obj != null) {
        ((AddCamera_6_TakeSnapshotFragment)paramMessage.obj).dispatchMessage(paramMessage);
      }
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/AddCamera_6_TakeSnapshotFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */