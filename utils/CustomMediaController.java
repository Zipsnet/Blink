package com.immediasemi.blink.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;

public class CustomMediaController
  extends MediaController
{
  private boolean mAllowAutoHide = true;
  private int mBlinkGreen;
  private FrameLayout mRoot;
  
  public CustomMediaController(Context paramContext)
  {
    super(paramContext);
    initDrawables(paramContext);
  }
  
  public CustomMediaController(Context paramContext, boolean paramBoolean)
  {
    super(paramContext, paramBoolean);
    initDrawables(paramContext);
  }
  
  private void findButtons(View paramView)
  {
    paramView.setBackgroundColor(0);
    if (ImageButton.class.isInstance(paramView)) {
      ((ImageButton)paramView).setColorFilter(this.mBlinkGreen);
    }
    for (;;)
    {
      return;
      if (ViewGroup.class.isInstance(paramView))
      {
        int i = 0;
        while (i < ((ViewGroup)paramView).getChildCount())
        {
          findButtons(((ViewGroup)paramView).getChildAt(i));
          i += 1;
        }
      }
    }
  }
  
  private void initDrawables(Context paramContext)
  {
    this.mBlinkGreen = paramContext.getResources().getColor(2131492875);
  }
  
  public void hide()
  {
    if (this.mAllowAutoHide) {
      super.hide();
    }
  }
  
  public void onFinishInflate()
  {
    super.onFinishInflate();
    findButtons(this);
  }
  
  public void show(int paramInt)
  {
    findButtons(this);
    int i = paramInt;
    if (paramInt < 0)
    {
      this.mAllowAutoHide = false;
      i = 0;
    }
    super.show(i);
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/utils/CustomMediaController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */