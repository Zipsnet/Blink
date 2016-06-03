package com.immediasemi.blink.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import com.immediasemi.blink.BlinkApp;

public class CustomMediaController
  extends MediaController
{
  public static final String MediaController_BackButton_Notification = "media_backbutton_notification";
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
      if (ViewGroup.class.isInstance(paramView)) {
        for (int i = 0; i < ((ViewGroup)paramView).getChildCount(); i++) {
          findButtons(((ViewGroup)paramView).getChildAt(i));
        }
      }
    }
  }
  
  private void initDrawables(Context paramContext)
  {
    this.mBlinkGreen = paramContext.getResources().getColor(2131492875);
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    boolean bool = true;
    if ((paramKeyEvent.getKeyCode() == 4) && (paramKeyEvent.getAction() == 1))
    {
      LocalBroadcastManager localLocalBroadcastManager = LocalBroadcastManager.getInstance(BlinkApp.getApp().getApplicationContext());
      paramKeyEvent = new Intent();
      paramKeyEvent.setAction("media_backbutton_notification");
      localLocalBroadcastManager.sendBroadcast(paramKeyEvent);
    }
    for (;;)
    {
      return bool;
      bool = super.dispatchKeyEvent(paramKeyEvent);
    }
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
    int i = paramInt;
    if (paramInt < 0)
    {
      this.mAllowAutoHide = false;
      i = 0;
    }
    super.show(i);
  }
  
  public void unConditionalHide()
  {
    super.hide();
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/CustomMediaController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */