package com.immediasemi.blink.fragments;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Cameras.AddCameraRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.Camera;
import com.immediasemi.blink.models.OneCamera;
import com.immediasemi.blink.utils.ClearEditSerialNumber;
import com.immediasemi.blink.utils.ClearEditSerialNumber.OnClearEditSerialNumberChangeListener;
import com.immediasemi.blink.utils.OnClick;

public class AddCamera_2_Fragment
  extends BaseFragment
{
  public static final int ADD_CAMERA_INDEX = 2;
  private Button mEnterButton;
  private int mSectionNumber;
  private ClearEditSerialNumber mSerialNumber;
  private View mView;
  
  public static AddCamera_2_Fragment newInstance(int paramInt)
  {
    AddCamera_2_Fragment localAddCamera_2_Fragment = new AddCamera_2_Fragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAddCamera_2_Fragment.setArguments(localBundle);
    return localAddCamera_2_Fragment;
  }
  
  private boolean serialIsValid(String paramString)
  {
    paramString = Integer.valueOf(Integer.parseInt(paramString.replaceAll("-", "")));
    if (paramString.intValue() <= 100016000) {}
    Integer localInteger;
    do
    {
      return true;
      localInteger = Integer.valueOf(0);
      while (paramString.intValue() > 0)
      {
        localInteger = Integer.valueOf(localInteger.intValue() + paramString.intValue() % 10);
        paramString = Integer.valueOf(paramString.intValue() / 10);
      }
    } while (localInteger.intValue() % 10 == 0);
    return false;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 2).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903087, paramViewGroup, false);
    this.mSerialNumber = ((ClearEditSerialNumber)this.mView.findViewById(2131558575));
    this.mSerialNumber.setOnClearEditSerialNumberChangeListener(new ClearEditSerialNumber.OnClearEditSerialNumberChangeListener()
    {
      public void onTextChanged(String paramAnonymousString)
      {
        if ((!paramAnonymousString.startsWith("1")) && (paramAnonymousString.length() > 0)) {}
        for (int i = 1; i != 0; i = 0)
        {
          new AlertDialog.Builder(AddCamera_2_Fragment.this.getActivity()).setTitle("Invalid Serial Number").setMessage("Camera serial numbers begin with \"1\"").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              AddCamera_2_Fragment.this.mSerialNumber.setText("");
            }
          }).create().show();
          return;
        }
        if (paramAnonymousString.length() >= 11)
        {
          if (!AddCamera_2_Fragment.this.serialIsValid(paramAnonymousString))
          {
            new AlertDialog.Builder(AddCamera_2_Fragment.this.getActivity()).setTitle("Invalid Serial Number").setMessage("Check your camera's serial number and try again").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                AddCamera_2_Fragment.this.mSerialNumber.setText("");
              }
            }).create().show();
            return;
          }
          ((InputMethodManager)AddCamera_2_Fragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(AddCamera_2_Fragment.this.mSerialNumber.getWindowToken(), 2);
          AddCamera_2_Fragment.this.mSerialNumber.clearFocus();
          AddCamera_2_Fragment.this.mEnterButton.setVisibility(0);
          AddCamera_2_Fragment.this.mEnterButton.requestFocus();
          return;
        }
        AddCamera_2_Fragment.this.mEnterButton.setVisibility(4);
      }
    });
    this.mSerialNumber.clearFocus();
    new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        if (AddCamera_2_Fragment.this.mSerialNumber.requestFocus()) {
          ((InputMethodManager)AddCamera_2_Fragment.this.getActivity().getSystemService("input_method")).showSoftInput(AddCamera_2_Fragment.this.mSerialNumber, 1);
        }
      }
    }.sendEmptyMessageDelayed(0, 250L);
    this.mEnterButton = ((Button)this.mView.findViewById(2131558574));
    this.mEnterButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        paramAnonymousView = new AddCameraRequest();
        paramAnonymousView.setNetwork(Integer.valueOf(BlinkApp.getApp().getLastNetworkId()).intValue());
        BlinkApp.getApp().setLastCameraName(AddCamera_2_Fragment.this.mSerialNumber.getText().toString());
        paramAnonymousView.setSerial(AddCamera_2_Fragment.this.mSerialNumber.getText().toString().replaceAll("-", ""));
        BlinkAPI.BlinkAPIRequest(null, null, paramAnonymousView, new BlinkAPI.BlinkAPICallback()
        {
          public void onError(BlinkError paramAnonymous2BlinkError)
          {
            if (AddCamera_2_Fragment.this.getActivity() != null) {
              new AlertDialog.Builder(AddCamera_2_Fragment.this.getActivity()).setMessage("Add Camera Failed").setPositiveButton(2131099886, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                {
                  AddCamera_2_Fragment.this.getActivity().finish();
                }
              }).create().show();
            }
          }
          
          public void onResult(BlinkData paramAnonymous2BlinkData)
          {
            paramAnonymous2BlinkData = ((OneCamera)paramAnonymous2BlinkData).getCamera();
            BlinkApp.getApp().setLastCameraId(String.valueOf(paramAnonymous2BlinkData.getId()));
            ((InputMethodManager)AddCamera_2_Fragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(AddCamera_2_Fragment.this.mSerialNumber.getWindowToken(), 0);
            paramAnonymous2BlinkData = AddCamera_3_WaitForCameraFragment.newInstance(3);
            AddCamera_2_Fragment.this.mListener.onFragmentInteraction(AddCamera_2_Fragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymous2BlinkData);
          }
        }, false);
      }
    });
    this.mEnterButton.setVisibility(4);
    return this.mView;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/AddCamera_2_Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */