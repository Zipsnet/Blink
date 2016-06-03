package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.Accounts.LoginRequest;
import com.immediasemi.blink.api.requests.Accounts.ResetPasswordRequest;
import com.immediasemi.blink.models.AuthToken;
import com.immediasemi.blink.models.AuthToken.AuthTokenContents;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.models.MessageResponse;
import com.immediasemi.blink.utils.LiveViewWhiteList;
import com.immediasemi.blink.utils.OnClick;
import com.immediasemi.blink.utils.Validation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class LoginFragment
  extends BaseFragment
{
  public static final int MAX_PASSWORD_LENGTH = 128;
  public static final int MIN_PASSWORD_LENGTH = 6;
  private LinearLayout mConfirmContainer;
  private EditText mConfirmPassword;
  private String mErrorString;
  private View mForgotPassword;
  private Button mLoginButton;
  private EditText mPassword;
  private int mSectionNumber;
  private View mShowPWSwitch;
  private TextView mTermsAndConditions;
  private EditText mUserName;
  private View mView;
  
  private void loginError(BlinkError paramBlinkError)
  {
    if (getActivity() != null) {
      if (paramBlinkError.response == null) {
        break label62;
      }
    }
    label62:
    for (paramBlinkError = (String)paramBlinkError.response.get("message");; paramBlinkError = "Could not log in")
    {
      new AlertDialog.Builder(getActivity()).setMessage(paramBlinkError).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      }).create().show();
      return;
    }
  }
  
  public static LoginFragment newInstance(int paramInt)
  {
    LoginFragment localLoginFragment = new LoginFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localLoginFragment.setArguments(localBundle);
    return localLoginFragment;
  }
  
  private boolean validateInputs()
  {
    boolean bool = false;
    if (!Validation.validateEmail(this.mUserName.getText().toString())) {
      this.mErrorString = getString(2131099860);
    }
    for (;;)
    {
      return bool;
      if (this.mPassword.getText().length() == 0) {
        this.mErrorString = getString(2131099862);
      } else if (this.mPassword.getText().length() > 128) {
        this.mErrorString = getString(2131099862);
      } else {
        bool = true;
      }
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099987));
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
    this.mView = paramLayoutInflater.inflate(2130903107, paramViewGroup, false);
    this.mUserName = ((EditText)this.mView.findViewById(2131558651));
    this.mUserName.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        int j = 1;
        int i;
        if (paramAnonymousEditable.length() > 0)
        {
          i = 1;
          if (LoginFragment.this.mPassword.getText().length() <= 0) {
            break label54;
          }
          label31:
          if ((i & j) == 0) {
            break label59;
          }
          LoginFragment.this.mLoginButton.setVisibility(0);
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
          LoginFragment.this.mLoginButton.setVisibility(4);
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    this.mUserName.setOnFocusChangeListener(new View.OnFocusChangeListener()
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
    this.mPassword = ((EditText)this.mView.findViewById(2131558653));
    this.mPassword.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        int j = 1;
        int i;
        if (paramAnonymousEditable.length() > 0)
        {
          i = 1;
          if (LoginFragment.this.mUserName.getText().length() <= 0) {
            break label54;
          }
          label31:
          if ((i & j) == 0) {
            break label59;
          }
          LoginFragment.this.mLoginButton.setVisibility(0);
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
          LoginFragment.this.mLoginButton.setVisibility(4);
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    this.mShowPWSwitch = this.mView.findViewById(2131558655);
    this.mShowPWSwitch.setVisibility(8);
    this.mForgotPassword = this.mView.findViewById(2131558654);
    this.mForgotPassword.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          String str = LoginFragment.this.mUserName.getText().toString();
          if ((str.length() == 0) || (!Validation.validateEmail(str)))
          {
            new AlertDialog.Builder(LoginFragment.this.getActivity()).setTitle(2131099880).setMessage(2131099815).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
            }).create().show();
          }
          else
          {
            paramAnonymousView = new ResetPasswordRequest();
            paramAnonymousView.setEmail(str);
            BlinkAPI.BlinkAPIRequest(null, null, paramAnonymousView, new BlinkAPI.BlinkAPICallback()
            {
              public void onError(BlinkError paramAnonymous2BlinkError)
              {
                LoginFragment.this.loginError(paramAnonymous2BlinkError);
              }
              
              public void onResult(BlinkData paramAnonymous2BlinkData)
              {
                paramAnonymous2BlinkData = ((MessageResponse)paramAnonymous2BlinkData).getMessage();
                if (LoginFragment.this.getActivity() != null) {
                  new AlertDialog.Builder(LoginFragment.this.getActivity()).setTitle("Password Reset").setMessage(paramAnonymous2BlinkData).setPositiveButton(2131099891, new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                    {
                      LoginFragment.this.getActivity().finish();
                    }
                  }).create().show();
                }
              }
            }, false);
          }
        }
      }
    });
    this.mTermsAndConditions = ((TextView)this.mView.findViewById(2131558658));
    this.mTermsAndConditions.setVisibility(4);
    this.mLoginButton = ((Button)this.mView.findViewById(2131558657));
    this.mLoginButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {}
        for (;;)
        {
          return;
          LoginRequest localLoginRequest;
          int i;
          if (LoginFragment.this.validateInputs())
          {
            localLoginRequest = new LoginRequest();
            localLoginRequest.setEmail(LoginFragment.this.mUserName.getText().toString());
            localLoginRequest.setPassword(LoginFragment.this.mPassword.getText().toString());
            str = "?";
            i = 0;
            paramAnonymousView = str;
          }
          try
          {
            PackageInfo localPackageInfo = LoginFragment.this.getActivity().getPackageManager().getPackageInfo(LoginFragment.this.getActivity().getPackageName(), 0);
            paramAnonymousView = str;
            str = localPackageInfo.versionName;
            paramAnonymousView = str;
            int j = localPackageInfo.versionCode;
            paramAnonymousView = str;
            i = j;
          }
          catch (Exception localException)
          {
            for (;;) {}
          }
          String str = " ||" + LiveViewWhiteList.getProcCPUInfoHardware() + "||";
          localLoginRequest.setClient_specifier(Build.MANUFACTURER + " " + Build.MODEL + " | " + String.valueOf(Build.VERSION.SDK_INT) + " | " + paramAnonymousView + " | " + i + str);
          localLoginRequest.setClient_version(paramAnonymousView);
          localLoginRequest.setClient_type("android");
          localLoginRequest.setClient_name(Build.MANUFACTURER + " " + Build.MODEL);
          if ((BlinkApp.getApp().getDeviceToken() != null) && (BlinkApp.getApp().getDeviceToken().length() > 0)) {
            localLoginRequest.setNotification_key(BlinkApp.getApp().getDeviceToken());
          }
          BlinkAPI.BlinkAPIRequest(null, null, localLoginRequest, new BlinkAPI.BlinkAPICallback()
          {
            public void onError(BlinkError paramAnonymous2BlinkError)
            {
              LoginFragment.this.loginError(paramAnonymous2BlinkError);
            }
            
            public void onResult(BlinkData paramAnonymous2BlinkData)
            {
              String str = ((AuthToken)paramAnonymous2BlinkData).getAuthtoken().getAuthtoken();
              if (str == null)
              {
                paramAnonymous2BlinkData = new BlinkError();
                paramAnonymous2BlinkData.setErrorMessage(LoginFragment.this.getString(2131099721));
                LoginFragment.this.loginError(paramAnonymous2BlinkData);
              }
              for (;;)
              {
                return;
                BlinkApp.getApp().setLoginAuthToken(str);
                BlinkApp.getApp().setUserName(LoginFragment.this.mUserName.getText().toString());
                BlinkApp.getApp().setPassword(LoginFragment.this.mPassword.getText().toString());
                BlinkApp.getApp().setLoggedIn(true);
                BlinkApp.getApp().setLastNetworkId("");
                BlinkApp.getApp().setLastCameraId("");
                HashMap localHashMap = ((AuthToken)paramAnonymous2BlinkData).getRegion();
                if ((localHashMap != null) && (!localHashMap.keySet().isEmpty()))
                {
                  paramAnonymous2BlinkData = localHashMap.keySet().iterator();
                  while (paramAnonymous2BlinkData.hasNext())
                  {
                    str = (String)paramAnonymous2BlinkData.next();
                    BlinkApp.getApp().setServerUrlForDNSSubdomain(str);
                    BlinkApp.getApp().setRegionFriendlyName((String)localHashMap.get(str));
                  }
                }
                if (LoginFragment.this.getActivity() != null) {
                  LoginFragment.this.getActivity().finish();
                }
              }
            }
          }, false);
          continue;
          if (LoginFragment.this.getActivity() != null)
          {
            if (LoginFragment.this.mErrorString == null) {
              LoginFragment.access$502(LoginFragment.this, LoginFragment.this.getString(2131099861));
            }
            new AlertDialog.Builder(LoginFragment.this.getActivity()).setTitle(LoginFragment.this.mErrorString).setMessage(LoginFragment.this.getString(2131100009)).setPositiveButton(2131099891, null).show();
          }
        }
      }
    });
    this.mLoginButton.setVisibility(4);
    this.mUserName.clearFocus();
    new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        if (LoginFragment.this.mUserName.requestFocus()) {
          ((InputMethodManager)LoginFragment.this.getActivity().getSystemService("input_method")).showSoftInput(LoginFragment.this.mUserName, 1);
        }
        LoginFragment.this.mUserName.setSelection(LoginFragment.this.mUserName.getText().length());
      }
    }.sendEmptyMessageDelayed(0, 250L);
    return this.mView;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/fragments/LoginFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */