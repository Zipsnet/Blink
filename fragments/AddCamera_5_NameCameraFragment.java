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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Cameras.UpdateCameraRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.utils.OnClick;

public class AddCamera_5_NameCameraFragment
  extends BaseFragment
{
  public static final int ADD_CAMERA_INDEX = 5;
  private EditText mCamName;
  private String mCameraName = "";
  private int mSectionNumber;
  private View mView;
  
  public static AddCamera_5_NameCameraFragment newInstance(int paramInt)
  {
    AddCamera_5_NameCameraFragment localAddCamera_5_NameCameraFragment = new AddCamera_5_NameCameraFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAddCamera_5_NameCameraFragment.setArguments(localBundle);
    return localAddCamera_5_NameCameraFragment;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    PreferenceManager.getDefaultSharedPreferences(BlinkApp.getApp().getApplicationContext()).edit().putInt("add_camera_sequence", 5).commit();
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
    this.mCameraName = BlinkApp.getApp().getLastCameraName();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903090, paramViewGroup, false);
    this.mCamName = ((EditText)this.mView.findViewById(2131558585));
    if ((this.mCameraName != null) && (this.mCameraName.length() > 0)) {
      this.mCamName.setText(this.mCameraName);
    }
    this.mCamName.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        AddCamera_5_NameCameraFragment.access$002(AddCamera_5_NameCameraFragment.this, paramAnonymousEditable.toString());
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    this.mCamName.clearFocus();
    new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        if (AddCamera_5_NameCameraFragment.this.mCamName.requestFocus()) {
          ((InputMethodManager)AddCamera_5_NameCameraFragment.this.getActivity().getSystemService("input_method")).showSoftInput(AddCamera_5_NameCameraFragment.this.mCamName, 1);
        }
        AddCamera_5_NameCameraFragment.this.mCamName.setSelection(AddCamera_5_NameCameraFragment.this.mCamName.getText().length());
      }
    }.sendEmptyMessageDelayed(0, 250L);
    ((Button)this.mView.findViewById(2131558547)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        AddCamera_5_NameCameraFragment.this.mCamName.clearFocus();
        ((InputMethodManager)AddCamera_5_NameCameraFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(AddCamera_5_NameCameraFragment.this.mCamName.getWindowToken(), 0);
        if (AddCamera_5_NameCameraFragment.this.mCameraName.length() > 0)
        {
          BlinkApp.getApp().setLastCameraName(AddCamera_5_NameCameraFragment.this.mCameraName);
          paramAnonymousView = new UpdateCameraRequest();
          paramAnonymousView.setCamera(Integer.valueOf(Integer.parseInt(BlinkApp.getApp().getLastCameraId())));
          paramAnonymousView.setId(Integer.valueOf(Integer.parseInt(BlinkApp.getApp().getLastCameraId())));
          paramAnonymousView.setNetwork(Integer.valueOf(Integer.parseInt(BlinkApp.getApp().getLastNetworkId())));
          paramAnonymousView.setName(AddCamera_5_NameCameraFragment.this.mCameraName);
          BlinkAPI.BlinkAPIRequest(null, null, paramAnonymousView, new BlinkAPI.BlinkAPICallback()
          {
            public void onError(BlinkError paramAnonymous2BlinkError)
            {
              if (AddCamera_5_NameCameraFragment.this.getActivity() != null) {
                new AlertDialog.Builder(AddCamera_5_NameCameraFragment.this.getActivity()).setTitle("Error").setMessage("Camera naming failed").setPositiveButton(2131099922, new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int) {}
                }).setNegativeButton(2131099774, new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int) {}
                }).create().show();
              }
            }
            
            public void onResult(BlinkData paramAnonymous2BlinkData)
            {
              paramAnonymous2BlinkData = AddCamera_6_TakeSnapshotFragment.newInstance(-1);
              AddCamera_5_NameCameraFragment.this.mListener.onFragmentInteraction(AddCamera_5_NameCameraFragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymous2BlinkData);
            }
          }, false);
          return;
        }
        paramAnonymousView = AddCamera_6_TakeSnapshotFragment.newInstance(-1);
        AddCamera_5_NameCameraFragment.this.mListener.onFragmentInteraction(AddCamera_5_NameCameraFragment.this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramAnonymousView);
      }
    });
    return this.mView;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/AddCamera_5_NameCameraFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */