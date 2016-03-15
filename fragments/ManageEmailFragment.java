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
import com.immediasemi.blink.api.requests.Accounts.UpdateAccountRequest;
import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.utils.OnClick;
import com.immediasemi.blink.utils.Validation;
import java.util.HashMap;

public class ManageEmailFragment
  extends BaseFragment
{
  public static final String CHANGE_EMAIL_TAG = "change_email_tag";
  private Button mChangeEmailButton;
  private LinearLayout mConfirmContainer;
  private String mErrorString;
  private EditText mNewEmail;
  private EditText mNewEmailConfirm;
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
      new AlertDialog.Builder(getActivity()).setMessage(paramBlinkError).setPositiveButton(2131099926, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      }).setNegativeButton(2131099778, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ManageEmailFragment.this.getActivity().finish();
        }
      }).create().show();
      return;
    }
  }
  
  public static ManageEmailFragment newInstance(int paramInt)
  {
    ManageEmailFragment localManageEmailFragment = new ManageEmailFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localManageEmailFragment.setArguments(localBundle);
    return localManageEmailFragment;
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
    this.mView = paramLayoutInflater.inflate(2130903105, paramViewGroup, false);
    this.mNewEmail = ((EditText)this.mView.findViewById(2131558658));
    this.mChangeEmailButton = ((Button)this.mView.findViewById(2131558660));
    this.mNewEmail.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        int j = 1;
        int i;
        if (paramAnonymousEditable.length() > 0)
        {
          i = 1;
          if (ManageEmailFragment.this.mNewEmailConfirm.getText().length() <= 0) {
            break label54;
          }
        }
        for (;;)
        {
          if ((i & j) == 0) {
            break label59;
          }
          ManageEmailFragment.this.mChangeEmailButton.setVisibility(0);
          return;
          i = 0;
          break;
          label54:
          j = 0;
        }
        label59:
        ManageEmailFragment.this.mChangeEmailButton.setVisibility(4);
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    this.mNewEmail.setOnFocusChangeListener(new View.OnFocusChangeListener()
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
    this.mNewEmailConfirm = ((EditText)this.mView.findViewById(2131558659));
    this.mNewEmailConfirm.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        int j = 1;
        int i;
        if (paramAnonymousEditable.length() > 0)
        {
          i = 1;
          if (ManageEmailFragment.this.mNewEmail.getText().length() <= 0) {
            break label54;
          }
        }
        for (;;)
        {
          if ((i & j) == 0) {
            break label59;
          }
          ManageEmailFragment.this.mChangeEmailButton.setVisibility(0);
          return;
          i = 0;
          break;
          label54:
          j = 0;
        }
        label59:
        ManageEmailFragment.this.mChangeEmailButton.setVisibility(4);
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    this.mChangeEmailButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(final View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        paramAnonymousView = ManageEmailFragment.this.mNewEmail.getText().toString();
        Object localObject = ManageEmailFragment.this.mNewEmailConfirm.getText().toString();
        if (!Validation.validateEmail(paramAnonymousView))
        {
          new AlertDialog.Builder(ManageEmailFragment.this.getActivity()).setMessage(2131099814).setPositiveButton(2131099890, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).create().show();
          return;
        }
        if (!paramAnonymousView.equals(localObject))
        {
          new AlertDialog.Builder(ManageEmailFragment.this.getActivity()).setMessage("Email does not match").setPositiveButton(2131099890, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).create().show();
          return;
        }
        localObject = new UpdateAccountRequest();
        ((UpdateAccountRequest)localObject).setEmail(paramAnonymousView);
        ((UpdateAccountRequest)localObject).setPassword(BlinkApp.getApp().getPassword());
        BlinkAPI.BlinkAPIRequest(null, null, (BlinkRequest)localObject, new BlinkAPI.BlinkAPICallback()
        {
          public void onError(BlinkError paramAnonymous2BlinkError)
          {
            ManageEmailFragment.this.loginError(paramAnonymous2BlinkError);
          }
          
          public void onResult(BlinkData paramAnonymous2BlinkData)
          {
            BlinkApp.getApp().setUserName(paramAnonymousView);
            if (ManageEmailFragment.this.getActivity() != null) {
              new AlertDialog.Builder(ManageEmailFragment.this.getActivity()).setMessage("Email changed successfully").setPositiveButton(2131099890, new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                {
                  ManageEmailFragment.this.getActivity().finish();
                }
              }).create().show();
            }
          }
        }, false);
      }
    });
    this.mNewEmail.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean) {
          ((InputMethodManager)ManageEmailFragment.this.getContext().getSystemService("input_method")).showSoftInput(ManageEmailFragment.this.mNewEmail, 1);
        }
      }
    });
    this.mNewEmail.requestFocus();
    return this.mView;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/fragments/ManageEmailFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */