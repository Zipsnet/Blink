package com.immediasemi.blink.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TextWatcherAdapter
  implements TextWatcher
{
  private final TextWatcherListener listener;
  private final EditText view;
  
  public TextWatcherAdapter(EditText paramEditText, TextWatcherListener paramTextWatcherListener)
  {
    this.view = paramEditText;
    this.listener = paramTextWatcherListener;
  }
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    this.listener.onTextChanged(this.view, paramCharSequence.toString());
  }
  
  public static abstract interface TextWatcherListener
  {
    public abstract void onTextChanged(EditText paramEditText, String paramString);
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/utils/TextWatcherAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */