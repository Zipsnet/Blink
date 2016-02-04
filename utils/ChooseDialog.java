package com.immediasemi.blink.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;

public class ChooseDialog
  extends DialogFragment
{
  public static final String CUR_SELECT = "Current_selection";
  public static final String HAS_EXTRA_BUTTON = "Has_extra_button_key";
  public static final String LAYOUT_ID_KEY = "Layout_id_key";
  public static final String LIST_KEY = "List";
  public static final String OK_TITLE_KEY = "Ok_Button_Label";
  public static final String TAG = "ChooseDialog";
  public static final String TITLE_KEY = "Title";
  private DlgHandler handler = new DlgHandler(null);
  protected int mCurSelection = -1;
  protected View mCustomView;
  protected boolean mHasExtraButton = false;
  protected CharSequence[] mItems;
  protected int mLayoutId;
  protected NoticeDialogListener mListener;
  protected String mOKButton;
  protected String[] mRawItems;
  protected String mTitle;
  
  public View getCustomView()
  {
    return this.mCustomView;
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    try
    {
      this.mListener = ((NoticeDialogListener)paramActivity);
      return;
    }
    catch (ClassCastException localClassCastException)
    {
      throw new ClassCastException(paramActivity.toString() + " must implement NoticeDialogListener");
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    paramBundle = getArguments();
    int i;
    if (paramBundle != null)
    {
      this.mTitle = paramBundle.getString("Title");
      this.mRawItems = paramBundle.getStringArray("List");
      if ((this.mRawItems != null) && (this.mRawItems.length > 0))
      {
        this.mItems = new CharSequence[this.mRawItems.length];
        i = 0;
        while (i < this.mRawItems.length)
        {
          localObject = this.mRawItems[i];
          this.mItems[i] = ((String)localObject).subSequence(0, ((String)localObject).length());
          i += 1;
        }
      }
      this.mLayoutId = paramBundle.getInt("Layout_id_key", 0);
      this.mOKButton = paramBundle.getString("Ok_Button_Label");
      this.mHasExtraButton = paramBundle.getBoolean("Has_extra_button_key", false);
      this.mCurSelection = paramBundle.getInt("Current_selection", -1);
    }
    paramBundle = new AlertDialog.Builder(getActivity());
    Object localObject = getActivity().getLayoutInflater();
    if (this.mLayoutId > 0) {
      this.mCustomView = ((LayoutInflater)localObject).inflate(this.mLayoutId, null);
    }
    if ((this.mCustomView == null) && (this.mItems.length > 0)) {
      if ((this.mCurSelection >= 0) && (this.mCurSelection < this.mItems.length))
      {
        paramBundle.setSingleChoiceItems(this.mItems, this.mCurSelection, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            ChooseDialog.this.mListener.onDialogListClick(ChooseDialog.this, paramAnonymousInt);
          }
        });
        if (this.mTitle != null) {
          paramBundle.setTitle(this.mTitle);
        }
      }
    }
    label483:
    label493:
    for (;;)
    {
      paramBundle.setView(this.mCustomView);
      if (this.mHasExtraButton)
      {
        localObject = (TextView)this.mCustomView.findViewById(2131558744);
        if (localObject != null) {
          ((TextView)localObject).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              ChooseDialog.this.mListener.onDialogExtraButtonClick(ChooseDialog.this);
            }
          });
        }
      }
      paramBundle.setPositiveButton(this.mOKButton, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ChooseDialog.this.mListener.onDialogPositiveClick(ChooseDialog.this);
        }
      }).setNegativeButton(2131099774, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ChooseDialog.this.mListener.onDialogNegativeClick(ChooseDialog.this);
        }
      });
      return paramBundle.create();
      paramBundle.setItems(this.mItems, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ChooseDialog.this.mListener.onDialogListClick(ChooseDialog.this, paramAnonymousInt);
        }
      });
      break;
      if (this.mTitle != null)
      {
        if ((this.mCustomView == null) || (this.mCustomView.findViewById(2131558488) == null)) {
          break label483;
        }
        ((TextView)this.mCustomView.findViewById(2131558488)).setText(this.mTitle);
      }
      for (;;)
      {
        if (this.mCustomView == null) {
          break label493;
        }
        i = 0;
        while (i < ((ViewGroup)this.mCustomView).getChildCount())
        {
          localObject = ((ViewGroup)this.mCustomView).getChildAt(i);
          if (EditText.class.isAssignableFrom(localObject.getClass()))
          {
            ((View)localObject).clearFocus();
            localObject = (EditText)localObject;
            localObject = this.handler.obtainMessage(0, localObject);
            this.handler.sendMessageDelayed((Message)localObject, 250L);
          }
          i += 1;
        }
        break;
        paramBundle.setTitle(this.mTitle);
      }
    }
  }
  
  private static class DlgHandler
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      if (((EditText)paramMessage.obj).requestFocus()) {
        ((InputMethodManager)BlinkApp.getApp().getSystemService("input_method")).showSoftInput((EditText)paramMessage.obj, 1);
      }
      ((EditText)paramMessage.obj).setSelection(((EditText)paramMessage.obj).getText().length());
    }
  }
  
  public static abstract interface NoticeDialogListener
  {
    public abstract void onDialogExtraButtonClick(DialogFragment paramDialogFragment);
    
    public abstract void onDialogListClick(DialogFragment paramDialogFragment, int paramInt);
    
    public abstract void onDialogNegativeClick(DialogFragment paramDialogFragment);
    
    public abstract void onDialogPositiveClick(DialogFragment paramDialogFragment);
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/utils/ChooseDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */