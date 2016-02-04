package com.immediasemi.blink.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class ClearEditText
  extends EditText
  implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener
{
  private Context context;
  private View.OnFocusChangeListener f;
  private View.OnTouchListener l;
  private Listener listener;
  private Drawable xD;
  
  public ClearEditText(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    init();
  }
  
  public ClearEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    init();
  }
  
  public ClearEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.context = paramContext;
    init();
  }
  
  private void init()
  {
    this.xD = getCompoundDrawables()[2];
    if (this.xD == null) {
      this.xD = getResources().getDrawable(2130837684);
    }
    this.xD.setBounds(0, 0, this.xD.getIntrinsicWidth(), this.xD.getIntrinsicHeight());
    setClearIconVisible(false);
    super.setOnTouchListener(this);
    super.setOnFocusChangeListener(this);
    addTextChangedListener(new TextWatcherAdapter(this, this));
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    boolean bool = false;
    if (paramBoolean)
    {
      if (!getText().toString().isEmpty()) {
        bool = true;
      }
      setClearIconVisible(bool);
    }
    for (;;)
    {
      if (this.f != null) {
        this.f.onFocusChange(paramView, paramBoolean);
      }
      return;
      setClearIconVisible(false);
    }
  }
  
  public void onTextChanged(EditText paramEditText, String paramString)
  {
    if (isFocused()) {
      if (getText().toString().isEmpty()) {
        break label28;
      }
    }
    label28:
    for (boolean bool = true;; bool = false)
    {
      setClearIconVisible(bool);
      return;
    }
  }
  
  public boolean onTouch(final View paramView, MotionEvent paramMotionEvent)
  {
    if (getCompoundDrawables()[2] != null)
    {
      if (paramMotionEvent.getX() > getWidth() - getPaddingRight() - this.xD.getIntrinsicWidth()) {}
      for (int i = 1; i != 0; i = 0)
      {
        if (paramMotionEvent.getAction() == 1)
        {
          setText("");
          if (this.listener != null) {
            this.listener.didClearText();
          }
          paramView.clearFocus();
          new Handler()
          {
            public void handleMessage(Message paramAnonymousMessage)
            {
              super.handleMessage(paramAnonymousMessage);
              if (paramView.requestFocus()) {
                ((InputMethodManager)ClearEditText.this.context.getSystemService("input_method")).showSoftInput(paramView, 1);
              }
            }
          }.sendEmptyMessageDelayed(0, 250L);
        }
        return true;
      }
    }
    if (this.l != null) {
      return this.l.onTouch(paramView, paramMotionEvent);
    }
    return false;
  }
  
  protected void setClearIconVisible(boolean paramBoolean)
  {
    boolean bool;
    if (getCompoundDrawables()[2] != null)
    {
      bool = true;
      if (paramBoolean != bool) {
        if (!paramBoolean) {
          break label54;
        }
      }
    }
    label54:
    for (Drawable localDrawable = this.xD;; localDrawable = null)
    {
      setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], localDrawable, getCompoundDrawables()[3]);
      return;
      bool = false;
      break;
    }
  }
  
  public void setListener(Listener paramListener)
  {
    this.listener = paramListener;
  }
  
  public void setOnFocusChangeListener(View.OnFocusChangeListener paramOnFocusChangeListener)
  {
    this.f = paramOnFocusChangeListener;
  }
  
  public void setOnTouchListener(View.OnTouchListener paramOnTouchListener)
  {
    this.l = paramOnTouchListener;
  }
  
  public static abstract interface Listener
  {
    public abstract void didClearText();
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/utils/ClearEditText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */