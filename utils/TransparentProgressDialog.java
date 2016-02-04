package com.immediasemi.blink.utils;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class TransparentProgressDialog
  extends FrameLayout
{
  private ImageView iv;
  private RotateAnimation mAnim;
  private ViewGroup mContainer;
  private boolean mIsShowing;
  private RelativeLayout mProgressDialog;
  
  public TransparentProgressDialog(Context paramContext)
  {
    super(paramContext);
  }
  
  public TransparentProgressDialog(Context paramContext, int paramInt, ViewGroup paramViewGroup)
  {
    super(paramContext);
    this.mContainer = paramViewGroup;
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
    localLayoutParams.gravity = 17;
    setLayoutParams(localLayoutParams);
    setClickable(false);
    this.mProgressDialog = new RelativeLayout(paramContext);
    setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    this.mProgressDialog.setClickable(false);
    this.mProgressDialog.setGravity(17);
    paramViewGroup.addView(this.mProgressDialog);
    this.iv = new ImageView(paramContext);
    paramContext = new ViewGroup.LayoutParams(-2, -2);
    this.iv.setLayoutParams(paramContext);
    this.iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
    this.iv.setAdjustViewBounds(true);
    this.iv.setImageResource(paramInt);
    this.mProgressDialog.addView(this.iv);
    paramViewGroup.addView(this);
  }
  
  public void dismiss()
  {
    this.mIsShowing = false;
    this.mAnim.cancel();
    this.mProgressDialog.setVisibility(8);
    this.mContainer.removeView(this);
  }
  
  public boolean isShowing()
  {
    return this.mIsShowing;
  }
  
  public void show()
  {
    this.mAnim = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
    this.mAnim.setInterpolator(new LinearInterpolator());
    this.mAnim.setRepeatCount(-1);
    this.mAnim.setDuration(1500L);
    this.iv.setAnimation(this.mAnim);
    this.iv.startAnimation(this.mAnim);
    this.mIsShowing = true;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/utils/TransparentProgressDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */