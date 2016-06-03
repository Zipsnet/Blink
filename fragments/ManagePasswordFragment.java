package com.immediasemi.blink.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Accounts.ChangePasswordRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.utils.OnClick;
import java.util.HashMap;

public class ManagePasswordFragment
  extends BaseFragment
{
  public static final String CHANGE_EMAIL_TAG = "change_email_tag";
  private Button mChangePasswordButton;
  private LinearLayout mConfirmContainer;
  private String mErrorString;
  private EditText mNewPassword;
  private EditText mNewPasswordConfirm;
  private int mSectionNumber;
  private View mView;
  
  private void loginError(BlinkError paramBlinkError)
  {
    if (getActivity() != null) {
      if (paramBlinkError.response == null) {
        break label75;
      }
    }
    label75:
    for (paramBlinkError = (String)paramBlinkError.response.get("message");; paramBlinkError = "")
    {
      new AlertDialog.Builder(getActivity()).setMessage(paramBlinkError).setPositiveButton(2131099928, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      }).setNegativeButton(2131099778, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ManagePasswordFragment.this.getActivity().finish();
        }
      }).create().show();
      return;
    }
  }
  
  public static ManagePasswordFragment newInstance(int paramInt)
  {
    ManagePasswordFragment localManagePasswordFragment = new ManagePasswordFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localManagePasswordFragment.setArguments(localBundle);
    return localManagePasswordFragment;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mView = paramLayoutInflater.inflate(2130903110, paramViewGroup, false);
    this.mNewPassword = ((EditText)this.mView.findViewById(2131558667));
    this.mChangePasswordButton = ((Button)this.mView.findViewById(2131558669));
    this.mNewPassword.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        int j = 1;
        int i;
        if (paramAnonymousEditable.length() > 0)
        {
          i = 1;
          if (ManagePasswordFragment.this.mNewPasswordConfirm.getText().length() <= 0) {
            break label54;
          }
          label31:
          if ((i & j) == 0) {
            break label59;
          }
          ManagePasswordFragment.this.mChangePasswordButton.setVisibility(0);
        }
        for (;;)
        {
          return;
          i = 0;
          break;
          label54:
          j = 0;
          break label31;
          label59:
          ManagePasswordFragment.this.mChangePasswordButton.setVisibility(4);
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    this.mNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (!paramAnonymousBoolean)
        {
          paramAnonymousView = (EditText)paramAnonymousView;
          paramAnonymousView.setText(paramAnonymousView.getText().toString().trim());
        }
      }
    });
    this.mNewPasswordConfirm = ((EditText)this.mView.findViewById(2131558668));
    this.mNewPasswordConfirm.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        int j = 1;
        int i;
        if (paramAnonymousEditable.length() > 0)
        {
          i = 1;
          if (ManagePasswordFragment.this.mNewPassword.getText().length() <= 0) {
            break label54;
          }
          label31:
          if ((i & j) == 0) {
            break label59;
          }
          ManagePasswordFragment.this.mChangePasswordButton.setVisibility(0);
        }
        for (;;)
        {
          return;
          i = 0;
          break;
          label54:
          j = 0;
          break label31;
          label59:
          ManagePasswordFragment.this.mChangePasswordButton.setVisibility(4);
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    this.mChangePasswordButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(final View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          paramAnonymousView = ManagePasswordFragment.this.mNewPassword.getText().toString();
          String str = ManagePasswordFragment.this.mNewPasswordConfirm.getText().toString();
          if (paramAnonymousView.length() < 6)
          {
            new AlertDialog.Builder(ManagePasswordFragment.this.getActivity()).setMessage("Password must be at least 6 characters").setPositiveButton(2131099891, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
            }).create().show();
          }
          else if (!paramAnonymousView.equals(str))
          {
            new AlertDialog.Builder(ManagePasswordFragment.this.getActivity()).setMessage("Password does not match").setPositiveButton(2131099891, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
            }).create().show();
          }
          else
          {
            ChangePasswordRequest localChangePasswordRequest = new ChangePasswordRequest();
            localChangePasswordRequest.setOld_password(BlinkApp.getApp().getPassword());
            localChangePasswordRequest.setNew_password(paramAnonymousView);
            localChangePasswordRequest.setNew_password_confirm(str);
            BlinkAPI.BlinkAPIRequest(null, null, localChangePasswordRequest, new BlinkAPI.BlinkAPICallback()
            {
              public void onError(BlinkError paramAnonymous2BlinkError)
              {
                ManagePasswordFragment.this.loginError(paramAnonymous2BlinkError);
              }
              
              public void onResult(BlinkData paramAnonymous2BlinkData)
              {
                BlinkApp.getApp().setUserName(paramAnonymousView);
                if (ManagePasswordFragment.this.getActivity() != null) {
                  new AlertDialog.Builder(ManagePasswordFragment.this.getActivity()).setMessage("Password changed successfully").setPositiveButton(2131099891, new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                    {
                      ManagePasswordFragment.this.getActivity().finish();
                    }
                  }).create().show();
                }
              }
            }, false);
          }
        }
      }
    });
    this.mNewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean) {
          ((InputMethodManager)ManagePasswordFragment.this.getContext().getSystemService("input_method")).showSoftInput(ManagePasswordFragment.this.mNewPassword, 1);
        }
      }
    });
    this.mNewPassword.requestFocus();
    return this.mView;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/ManagePasswordFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */