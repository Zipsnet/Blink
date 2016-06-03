package com.immediasemi.blink.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class ClearEditSerialNumber
  extends ClearEditText
{
  private OnClearEditSerialNumberChangeListener mChangeListener;
  private boolean mProgrammaticChange;
  
  public ClearEditSerialNumber(Context paramContext)
  {
    super(paramContext);
  }
  
  public ClearEditSerialNumber(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public ClearEditSerialNumber(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void onTextChanged(EditText paramEditText, String paramString)
  {
    super.onTextChanged(paramEditText, paramString);
    if (this.mProgrammaticChange) {}
    for (;;)
    {
      return;
      String str = paramString.replaceAll("-", "");
      paramString = str;
      if (str.length() > 3) {
        paramString = str.substring(0, 3) + "-" + str.substring(3);
      }
      str = paramString;
      if (paramString.length() > 7) {
        str = paramString.substring(0, 7) + "-" + paramString.substring(7);
      }
      this.mProgrammaticChange = true;
      paramEditText.setText(str);
      paramEditText.setSelection(str.length());
      this.mProgrammaticChange = false;
      if (this.mChangeListener != null) {
        this.mChangeListener.onTextChanged(str);
      }
    }
  }
  
  public void setOnClearEditSerialNumberChangeListener(OnClearEditSerialNumberChangeListener paramOnClearEditSerialNumberChangeListener)
  {
    this.mChangeListener = paramOnClearEditSerialNumberChangeListener;
  }
  
  public static abstract interface OnClearEditSerialNumberChangeListener
  {
    public abstract void onTextChanged(String paramString);
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/ClearEditSerialNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */