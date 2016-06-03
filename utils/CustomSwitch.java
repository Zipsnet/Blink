package com.immediasemi.blink.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.CompoundButton;
import com.immediasemi.blink.R.styleable;

public class CustomSwitch
  extends CompoundButton
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private static final int HORIZONTAL = 1;
  private static final int MONOSPACE = 3;
  private static final int SANS = 1;
  private static final int SERIF = 2;
  private static final String TAG = "CustomSwitch";
  private static final int TOUCH_MODE_DOWN = 1;
  private static final int TOUCH_MODE_DRAGGING = 2;
  private static final int TOUCH_MODE_IDLE = 0;
  private static final int VERTICAL = 0;
  final int FRAME_DURATION = 10;
  private Canvas backingLayer;
  private final Rect canvasClipBounds = new Rect();
  private boolean clickDisabled = false;
  private boolean fixed = false;
  private Bitmap leftBitmap;
  float mAnimDuration;
  private Drawable mDrawableOff;
  private Drawable mDrawableOn;
  Interpolator mInterpolator;
  private Drawable mLeftBackground;
  private Drawable mMaskDrawable;
  float mMaxAnimDuration = 250.0F;
  private int mMinFlingVelocity;
  private Layout mOffLayout;
  private OnChangeAttemptListener mOnChangeAttemptListener;
  private Layout mOnLayout;
  private int mOrientation = 1;
  private boolean mPushStyle;
  private Drawable mRightBackground;
  boolean mRunning = false;
  float mStartPosition;
  long mStartTime;
  private int mSwitchBottom;
  private int mSwitchHeight;
  private int mSwitchLeft;
  private int mSwitchMinHeight;
  private int mSwitchMinWidth;
  private int mSwitchPadding;
  private int mSwitchRight;
  private int mSwitchTop;
  private int mSwitchWidth;
  private ColorStateList mTextColors;
  private CharSequence mTextOff;
  private CharSequence mTextOn;
  private boolean mTextOnThumb;
  private TextPaint mTextPaint;
  private final Rect mThPad = new Rect();
  private Drawable mThumbDrawable;
  private int mThumbExtraMovement;
  private int mThumbHeight;
  private float mThumbPosition = 0.0F;
  private int mThumbTextPadding;
  private int mThumbWidth;
  private int mTouchMode;
  private int mTouchSlop;
  private float mTouchX;
  private float mTouchY;
  private Drawable mTrackDrawable;
  private final Rect mTrackPaddingRect = new Rect();
  private int mTrackTextPadding;
  private final Runnable mUpdater = new Runnable()
  {
    public void run()
    {
      float f2 = Math.min(1.0F, (float)(System.currentTimeMillis() - CustomSwitch.this.mStartTime) / CustomSwitch.this.mAnimDuration);
      float f1 = CustomSwitch.this.mInterpolator.getInterpolation(f2);
      CustomSwitch.this.setThumbPosition(CustomSwitch.this.mStartPosition * (1.0F - f1) + f1);
      if (f2 == 1.0F) {
        CustomSwitch.this.stopAnimation();
      }
      for (;;)
      {
        return;
        if (CustomSwitch.this.mRunning) {
          if (CustomSwitch.this.getHandler() != null) {
            CustomSwitch.this.getHandler().post(CustomSwitch.this.mUpdater);
          } else {
            CustomSwitch.this.stopAnimation();
          }
        }
      }
    }
  };
  private VelocityTracker mVelocityTracker = VelocityTracker.obtain();
  private Bitmap maskBitmap;
  private boolean onOrOff = true;
  private Bitmap pushBitmap;
  private Bitmap rightBitmap;
  private Bitmap tempBitmap;
  private Paint xferPaint;
  
  public CustomSwitch(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CustomSwitch(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 2130771968);
  }
  
  public CustomSwitch(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Object localObject = getResources();
    float f = ((Resources)localObject).getDisplayMetrics().scaledDensity;
    this.mTextPaint = new TextPaint(1);
    this.mTextPaint.density = ((Resources)localObject).getDisplayMetrics().density;
    this.mTextPaint.setShadowLayer(0.5F, 1.0F, 1.0F, -16777216);
    this.mTextPaint.setTextSize(16.0F * f);
    this.mTextPaint.setColor(-1);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CustomSwitch, paramInt, 0);
    this.mLeftBackground = localTypedArray.getDrawable(16);
    this.mRightBackground = localTypedArray.getDrawable(17);
    this.mOrientation = localTypedArray.getInteger(15, 1);
    this.mThumbDrawable = localTypedArray.getDrawable(0);
    this.mTrackDrawable = localTypedArray.getDrawable(1);
    this.mTextOn = localTypedArray.getText(2);
    this.mTextOff = localTypedArray.getText(3);
    this.mDrawableOn = localTypedArray.getDrawable(4);
    this.mDrawableOff = localTypedArray.getDrawable(5);
    this.mPushStyle = localTypedArray.getBoolean(6, false);
    this.mTextOnThumb = localTypedArray.getBoolean(7, true);
    this.mThumbExtraMovement = localTypedArray.getDimensionPixelSize(8, 0);
    this.mThumbTextPadding = localTypedArray.getDimensionPixelSize(9, (int)(5.0F * f));
    this.mTrackTextPadding = localTypedArray.getDimensionPixelSize(10, (int)(5.0F * f));
    this.mSwitchMinWidth = localTypedArray.getDimensionPixelSize(12, (int)(60.0F * f));
    this.mSwitchMinHeight = localTypedArray.getDimensionPixelSize(13, 0);
    this.mSwitchPadding = localTypedArray.getDimensionPixelSize(14, 0);
    GradientDrawable localGradientDrawable1;
    GradientDrawable localGradientDrawable2;
    if (this.mThumbDrawable == null)
    {
      StateListDrawable localStateListDrawable = new StateListDrawable();
      localGradientDrawable1 = new GradientDrawable();
      localGradientDrawable1.setColor(52224);
      localGradientDrawable1.setCornerRadius(12.0F * ((Resources)localObject).getDisplayMetrics().scaledDensity);
      paramAttributeSet = new GradientDrawable();
      paramAttributeSet.setColor(-16777216);
      paramAttributeSet.setCornerRadius(12.0F * ((Resources)localObject).getDisplayMetrics().scaledDensity);
      paramAttributeSet.setAlpha(255);
      localGradientDrawable2 = new GradientDrawable();
      localGradientDrawable2.setColor(52224);
      localGradientDrawable2.setCornerRadius(12.0F * ((Resources)localObject).getDisplayMetrics().scaledDensity);
      localGradientDrawable2.setAlpha(96);
      localStateListDrawable.addState(new int[] { -16842910 }, localGradientDrawable2);
      localStateListDrawable.addState(new int[] { -16842912 }, paramAttributeSet);
      localStateListDrawable.addState(new int[] { 16842919, 16842912 }, localGradientDrawable1);
      localStateListDrawable.addState(StateSet.WILD_CARD, localGradientDrawable1);
      this.mThumbDrawable = localStateListDrawable;
    }
    if (this.mTrackDrawable == null)
    {
      paramAttributeSet = new StateListDrawable();
      localGradientDrawable2 = new GradientDrawable();
      localGradientDrawable2.setColor(52224);
      localGradientDrawable2.setCornerRadius(10.0F * f);
      localGradientDrawable2.setAlpha(128);
      localGradientDrawable1 = new GradientDrawable();
      localGradientDrawable1.setColor(-16777216);
      localGradientDrawable1.setCornerRadius(12.0F * ((Resources)localObject).getDisplayMetrics().scaledDensity);
      localGradientDrawable1.setAlpha(192);
      paramAttributeSet.addState(new int[] { -16842912 }, localGradientDrawable1);
      paramAttributeSet.addState(StateSet.WILD_CARD, localGradientDrawable2);
      this.mTrackDrawable = new InsetDrawable(paramAttributeSet, 0, (int)f, 0, (int)f);
    }
    this.mTrackDrawable.getPadding(this.mTrackPaddingRect);
    this.mThumbDrawable.getPadding(this.mThPad);
    this.mMaskDrawable = localTypedArray.getDrawable(18);
    paramAttributeSet = null;
    if (this.mLeftBackground == null)
    {
      localObject = paramAttributeSet;
      if (this.mRightBackground == null) {}
    }
    else
    {
      localObject = paramAttributeSet;
      if (this.mMaskDrawable == null) {
        localObject = new IllegalArgumentException(localTypedArray.getPositionDescription() + " if left/right background is given, then a mask has to be there");
      }
    }
    if (this.mLeftBackground != null)
    {
      paramInt = 1;
      if (this.mRightBackground == null) {
        break label1015;
      }
    }
    label1015:
    for (int i = 1;; i = 0)
    {
      paramAttributeSet = (AttributeSet)localObject;
      if ((paramInt ^ i) != 0)
      {
        paramAttributeSet = (AttributeSet)localObject;
        if (this.mMaskDrawable == null) {
          paramAttributeSet = new IllegalArgumentException(localTypedArray.getPositionDescription() + " left and right background both should be there. only one is not allowed ");
        }
      }
      localObject = paramAttributeSet;
      if (this.mTextOnThumb)
      {
        localObject = paramAttributeSet;
        if (this.mPushStyle) {
          localObject = new IllegalArgumentException(localTypedArray.getPositionDescription() + " Text On Thumb and Push Style are mutually exclusive. Only one can be present ");
        }
      }
      this.xferPaint = new Paint(1);
      this.xferPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
      paramInt = localTypedArray.getResourceId(11, 0);
      if (paramInt != 0) {
        setSwitchTextAppearance(paramContext, paramInt);
      }
      localTypedArray.recycle();
      if (localObject == null) {
        break label1021;
      }
      throw ((Throwable)localObject);
      paramInt = 0;
      break;
    }
    label1021:
    paramContext = ViewConfiguration.get(paramContext);
    this.mTouchSlop = paramContext.getScaledTouchSlop();
    this.mMinFlingVelocity = paramContext.getScaledMinimumFlingVelocity();
    this.mInterpolator = new DecelerateInterpolator();
    refreshDrawableState();
    setChecked(isChecked());
    setClickable(true);
  }
  
  private void animateThumbToCheckedState(boolean paramBoolean)
  {
    setChecked(paramBoolean);
  }
  
  private void cancelSuperTouch(MotionEvent paramMotionEvent)
  {
    paramMotionEvent = MotionEvent.obtain(paramMotionEvent);
    paramMotionEvent.setAction(3);
    super.onTouchEvent(paramMotionEvent);
    paramMotionEvent.recycle();
  }
  
  private boolean getTargetCheckedState()
  {
    if (this.mThumbPosition >= getThumbScrollRange() / 2) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private float getThumbPosition()
  {
    float f2 = getThumbScrollRange();
    boolean bool2 = isChecked();
    boolean bool1;
    if (!this.mTextOnThumb)
    {
      bool1 = true;
      if (!(bool1 ^ bool2)) {
        break label50;
      }
    }
    label50:
    for (float f1 = f2;; f1 = 0.0F)
    {
      f2 -= f1;
      return (this.mThumbPosition - f2) / (f1 - f2);
      bool1 = false;
      break;
    }
  }
  
  private int getThumbScrollRange()
  {
    int i;
    if (this.mTrackDrawable == null) {
      i = 0;
    }
    for (;;)
    {
      return i;
      int j = 0;
      if (this.mOrientation == 0) {
        j = this.mSwitchHeight - this.mThumbHeight - this.mTrackPaddingRect.top - this.mTrackPaddingRect.bottom + this.mThumbExtraMovement * 2;
      }
      if (this.mOrientation == 1) {
        j = this.mSwitchWidth - this.mThumbWidth - this.mTrackPaddingRect.left - this.mTrackPaddingRect.right + this.mThumbExtraMovement * 2;
      }
      i = j;
      if (this.mPushStyle) {
        i = j + this.mTrackTextPadding * 2;
      }
    }
  }
  
  private boolean hitThumb(float paramFloat1, float paramFloat2)
  {
    boolean bool = true;
    int m;
    int n;
    int k;
    int i1;
    int i;
    int i2;
    int j;
    if (this.mOrientation == 1)
    {
      m = this.mSwitchTop;
      n = this.mTouchSlop;
      k = this.mSwitchLeft + (int)(this.mThumbPosition + 0.5F) - this.mTouchSlop;
      i1 = this.mThumbWidth;
      i = this.mTouchSlop;
      i2 = this.mSwitchBottom;
      j = this.mTouchSlop;
      if ((paramFloat1 <= k) || (paramFloat1 >= i1 + k + i) || (paramFloat2 <= m - n) || (paramFloat2 >= i2 + j)) {}
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      if (this.mSwitchHeight > 150)
      {
        i = this.mSwitchLeft;
        n = this.mTouchSlop;
        i2 = this.mSwitchTop + (int)(this.mThumbPosition + 0.5F) - this.mTouchSlop;
        m = this.mThumbHeight;
        k = this.mTouchSlop;
        i1 = this.mSwitchRight;
        j = this.mTouchSlop;
        if ((paramFloat1 <= i - n) || (paramFloat1 >= i1 + j) || (paramFloat2 <= i2) || (paramFloat2 >= m + i2 + k)) {
          bool = false;
        }
      }
      else if ((paramFloat1 <= this.mSwitchLeft) || (paramFloat1 >= this.mSwitchRight) || (paramFloat2 <= this.mSwitchTop) || (paramFloat2 >= this.mSwitchBottom))
      {
        bool = false;
      }
    }
  }
  
  private Layout makeLayout(CharSequence paramCharSequence)
  {
    return new StaticLayout(paramCharSequence, this.mTextPaint, (int)Math.ceil(Layout.getDesiredWidth(paramCharSequence, this.mTextPaint)), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
  }
  
  private void resetAnimation()
  {
    this.mStartTime = System.currentTimeMillis();
    this.mStartPosition = getThumbPosition();
    this.mAnimDuration = ((int)(this.mMaxAnimDuration * (1.0F - this.mStartPosition)));
  }
  
  private void setSwitchTypefaceByIndex(int paramInt1, int paramInt2)
  {
    Typeface localTypeface = null;
    switch (paramInt1)
    {
    }
    for (;;)
    {
      setSwitchTypeface(localTypeface, paramInt2);
      return;
      localTypeface = Typeface.SANS_SERIF;
      continue;
      localTypeface = Typeface.SERIF;
      continue;
      localTypeface = Typeface.MONOSPACE;
    }
  }
  
  private void setThumbPosition(float paramFloat)
  {
    float f2 = 0.0F;
    float f1 = 0.0F;
    if (0.0F == 0.0F) {
      f1 = getThumbScrollRange();
    }
    boolean bool2 = isChecked();
    if (!this.mTextOnThumb) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      if ((bool1 ^ bool2)) {
        f2 = f1;
      }
      f1 -= f2;
      this.mThumbPosition = ((f2 - f1) * paramFloat + f1);
      invalidate();
      return;
    }
  }
  
  private void startAnimation()
  {
    if (getHandler() != null)
    {
      resetAnimation();
      this.mRunning = true;
      getHandler().post(this.mUpdater);
    }
    for (;;)
    {
      invalidate();
      return;
      setThumbPosition(1.0F);
    }
  }
  
  private void stopAnimation()
  {
    this.mRunning = false;
    setThumbPosition(1.0F);
    if (getHandler() != null) {
      getHandler().removeCallbacks(this.mUpdater);
    }
    invalidate();
  }
  
  private void stopDrag(MotionEvent paramMotionEvent)
  {
    int j = 1;
    this.mTouchMode = 0;
    int i;
    label38:
    float f;
    boolean bool;
    if ((paramMotionEvent.getAction() == 1) && (isEnabled()))
    {
      i = 1;
      if ((i == 0) || (this.fixed)) {
        break label119;
      }
      i = 1;
      cancelSuperTouch(paramMotionEvent);
      if (i == 0) {
        break label192;
      }
      this.mVelocityTracker.computeCurrentVelocity(1000);
      if (this.mOrientation != 1) {
        break label139;
      }
      f = this.mVelocityTracker.getXVelocity();
      if (Math.abs(f) <= this.mMinFlingVelocity) {
        break label130;
      }
      if (f <= 0.0F) {
        break label124;
      }
      bool = true;
      label95:
      if (this.mTextOnThumb) {
        break label187;
      }
      i = j;
      label105:
      animateThumbToCheckedState(i ^ bool);
    }
    for (;;)
    {
      return;
      i = 0;
      break;
      label119:
      i = 0;
      break label38;
      label124:
      bool = false;
      break label95;
      label130:
      bool = getTargetCheckedState();
      break label95;
      label139:
      f = this.mVelocityTracker.getYVelocity();
      if (Math.abs(f) > this.mMinFlingVelocity)
      {
        if (f > 0.0F) {}
        for (bool = true;; bool = false) {
          break;
        }
      }
      bool = getTargetCheckedState();
      break label95;
      label187:
      i = 0;
      break label105;
      label192:
      animateThumbToCheckedState(isChecked());
      if ((this.fixed) && (this.mOnChangeAttemptListener != null)) {
        this.mOnChangeAttemptListener.onChangeAttempted(isChecked());
      }
    }
  }
  
  public void disableClick()
  {
    this.clickDisabled = true;
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    if (this.mThumbDrawable != null) {
      this.mThumbDrawable.setState(arrayOfInt);
    }
    if (this.mTrackDrawable != null) {
      this.mTrackDrawable.setState(arrayOfInt);
    }
    invalidate();
  }
  
  public void enableClick()
  {
    this.clickDisabled = false;
  }
  
  public void fixate(boolean paramBoolean)
  {
    this.fixed = paramBoolean;
  }
  
  public void fixate(boolean paramBoolean1, boolean paramBoolean2)
  {
    fixate(paramBoolean1);
    this.onOrOff = paramBoolean2;
    if (paramBoolean2) {
      setChecked(true);
    }
  }
  
  public int getCompoundPaddingRight()
  {
    int j = super.getCompoundPaddingRight() + this.mSwitchWidth;
    int i = j;
    if (!TextUtils.isEmpty(getText())) {
      i = j + this.mSwitchPadding;
    }
    return i;
  }
  
  public int getCompoundPaddingTop()
  {
    int j = super.getCompoundPaddingTop() + this.mSwitchHeight;
    int i = j;
    if (!TextUtils.isEmpty(getText())) {
      i = j + this.mSwitchPadding;
    }
    return i;
  }
  
  public CharSequence getCurrentText()
  {
    if (isChecked()) {}
    for (CharSequence localCharSequence = this.mTextOn;; localCharSequence = this.mTextOff) {
      return localCharSequence;
    }
  }
  
  public CharSequence getText(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (CharSequence localCharSequence = this.mTextOn;; localCharSequence = this.mTextOff) {
      return localCharSequence;
    }
  }
  
  public CharSequence getTextOff()
  {
    return this.mTextOff;
  }
  
  public CharSequence getTextOn()
  {
    return this.mTextOn;
  }
  
  public boolean isFixed()
  {
    return this.fixed;
  }
  
  protected int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (isChecked()) {
      mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
    }
    return arrayOfInt;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    int i3 = this.mSwitchLeft + this.mTrackPaddingRect.left;
    int i2 = this.mSwitchTop + this.mTrackPaddingRect.top;
    int i4 = this.mSwitchRight - this.mTrackPaddingRect.right;
    int k = this.mSwitchBottom - this.mTrackPaddingRect.bottom;
    int m = getThumbScrollRange();
    int n = (int)(this.mThumbPosition + 0.5F);
    int i1 = this.mTextPaint.getAlpha();
    this.mTextPaint.drawableState = getDrawableState();
    int i;
    int j;
    if (this.mOrientation == 0)
    {
      i = (i3 + i4) / 2;
      j = this.mSwitchRight;
      getThumbScrollRange();
      j = this.mThumbExtraMovement;
      j = this.mThumbHeight;
      if (!this.mPushStyle) {
        break label1189;
      }
      j = Math.max(this.mOnLayout.getHeight(), this.mOffLayout.getHeight());
      this.backingLayer.save();
      this.backingLayer.translate(0.0F, -m + n);
      this.backingLayer.drawBitmap(this.pushBitmap, 0.0F, 0.0F, null);
      this.backingLayer.restore();
      this.backingLayer.drawBitmap(this.maskBitmap, 0.0F, 0.0F, this.xferPaint);
      paramCanvas.drawBitmap(this.tempBitmap, 0.0F, 0.0F, null);
      this.mTrackDrawable.draw(paramCanvas);
      this.backingLayer.drawColor(16777216, PorterDuff.Mode.DST_IN);
      this.backingLayer.save();
      this.backingLayer.translate(0.0F, -m + n);
      this.backingLayer.translate(0.0F, this.mTrackPaddingRect.top);
      this.backingLayer.save();
      this.backingLayer.translate(0.0F, (j - this.mOffLayout.getHeight()) / 2);
      if (this.mDrawableOff != null) {
        this.mDrawableOff.draw(this.backingLayer);
      }
      this.backingLayer.translate(i - this.mOffLayout.getWidth() / 2, 0.0F);
      this.mOffLayout.draw(this.backingLayer);
      this.backingLayer.restore();
      this.backingLayer.translate(0.0F, this.mTrackTextPadding * 2 + j + (j - this.mOnLayout.getHeight()) / 2 + this.mThumbHeight);
      if (this.mDrawableOn != null) {
        this.mDrawableOn.draw(this.backingLayer);
      }
      this.backingLayer.translate(i - this.mOnLayout.getWidth() / 2, 0.0F);
      this.mOnLayout.draw(this.backingLayer);
      this.backingLayer.restore();
      this.backingLayer.drawBitmap(this.maskBitmap, 0.0F, 0.0F, this.xferPaint);
      paramCanvas.drawBitmap(this.tempBitmap, 0.0F, 0.0F, null);
      j = i2 + n - this.mThumbExtraMovement;
      i = i2 + n - this.mThumbExtraMovement + this.mThumbHeight;
      this.mThumbDrawable.setBounds(this.mSwitchLeft, j, this.mSwitchRight, i);
      this.mThumbDrawable.draw(paramCanvas);
      this.mTextPaint.setAlpha(i1);
      if (this.mTextOnThumb)
      {
        if (!getTargetCheckedState()) {
          break label1810;
        }
        localObject = this.mOnLayout;
        label567:
        paramCanvas.save();
        paramCanvas.translate((this.mSwitchLeft + this.mSwitchRight) / 2 - ((Layout)localObject).getWidth() / 2, (j + i) / 2 - ((Layout)localObject).getHeight() / 2);
        ((Layout)localObject).draw(paramCanvas);
        paramCanvas.restore();
      }
    }
    label670:
    label700:
    int i7;
    int i6;
    int i5;
    if (this.mOrientation == 1)
    {
      j = i3 + this.mThumbWidth;
      if (!this.mTextOnThumb) {
        break label1819;
      }
      i = (i3 + j) / 2 - this.mOffLayout.getWidth() / 2 + this.mTrackTextPadding - this.mThumbTextPadding;
      if (!this.mTextOnThumb) {
        break label1830;
      }
      j = (i3 + m + (j + m)) / 2 - this.mOnLayout.getWidth() / 2;
      i7 = (i2 + k) / 2;
      i6 = i3 + n - this.mThumbExtraMovement;
      i5 = i3 + n + this.mThumbWidth - this.mThumbExtraMovement;
      if (!this.mPushStyle) {
        break label1849;
      }
      i = Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth());
      this.backingLayer.save();
      this.backingLayer.translate(-m + n, 0.0F);
      this.backingLayer.drawBitmap(this.pushBitmap, 0.0F, 0.0F, null);
      this.backingLayer.restore();
      this.backingLayer.drawBitmap(this.maskBitmap, 0.0F, 0.0F, this.xferPaint);
      paramCanvas.drawBitmap(this.tempBitmap, 0.0F, 0.0F, null);
      this.mTrackDrawable.draw(paramCanvas);
      this.backingLayer.drawColor(16777216, PorterDuff.Mode.DST_IN);
      this.backingLayer.save();
      this.backingLayer.translate(-m + n, 0.0F);
      this.backingLayer.translate(this.mTrackPaddingRect.left, 0.0F);
      this.backingLayer.save();
      this.backingLayer.translate((i - this.mOffLayout.getWidth()) / 2, i7 - this.mOffLayout.getHeight() / 2);
      this.mOffLayout.draw(this.backingLayer);
      if (this.mDrawableOff != null) {
        this.mDrawableOff.draw(this.backingLayer);
      }
      this.backingLayer.restore();
      this.backingLayer.translate(this.mTrackTextPadding * 2 + i + (i - this.mOnLayout.getWidth()) / 2 + this.mThumbWidth, i7 - this.mOnLayout.getHeight() / 2);
      this.mOnLayout.draw(this.backingLayer);
      if (this.mDrawableOn != null) {
        this.mDrawableOn.draw(this.backingLayer);
      }
      this.backingLayer.restore();
      this.backingLayer.drawBitmap(this.maskBitmap, 0.0F, 0.0F, this.xferPaint);
      paramCanvas.drawBitmap(this.tempBitmap, 0.0F, 0.0F, null);
      this.mThumbDrawable.setBounds(i6, this.mSwitchTop, i5, this.mSwitchBottom);
      this.mThumbDrawable.draw(paramCanvas);
      if (this.mTextOnThumb)
      {
        this.mTextPaint.setAlpha(i1);
        if (!getTargetCheckedState()) {
          break label2547;
        }
      }
    }
    label1189:
    label1503:
    label1796:
    label1810:
    label1819:
    label1830:
    label1849:
    label2547:
    for (Object localObject = this.mOnLayout;; localObject = this.mOffLayout)
    {
      paramCanvas.save();
      paramCanvas.translate((i6 + i5) / 2 - ((Layout)localObject).getWidth() / 2, (i2 + k) / 2 - ((Layout)localObject).getHeight() / 2);
      ((Layout)localObject).draw(paramCanvas);
      paramCanvas.restore();
      return;
      if (this.rightBitmap != null)
      {
        paramCanvas.save();
        if (paramCanvas.getClipBounds(this.canvasClipBounds))
        {
          if (this.mOrientation == 1)
          {
            localObject = this.canvasClipBounds;
            ((Rect)localObject).left += this.mThumbWidth / 2 + n;
          }
          if (this.mOrientation == 0)
          {
            localObject = this.canvasClipBounds;
            ((Rect)localObject).top += this.mThumbHeight / 2 + n;
          }
          paramCanvas.clipRect(this.canvasClipBounds);
        }
        paramCanvas.drawBitmap(this.rightBitmap, 0.0F, 0.0F, null);
        paramCanvas.restore();
      }
      if (this.leftBitmap != null)
      {
        paramCanvas.save();
        if (paramCanvas.getClipBounds(this.canvasClipBounds))
        {
          if (this.mOrientation == 1)
          {
            localObject = this.canvasClipBounds;
            ((Rect)localObject).right -= m - n + this.mThumbWidth / 2;
          }
          if (this.mOrientation == 0) {
            this.canvasClipBounds.bottom = (this.canvasClipBounds.top + n + this.mThumbHeight / 2);
          }
          paramCanvas.clipRect(this.canvasClipBounds);
        }
        paramCanvas.drawBitmap(this.leftBitmap, 0.0F, 0.0F, null);
        paramCanvas.restore();
      }
      this.mTrackDrawable.draw(paramCanvas);
      paramCanvas.save();
      paramCanvas.clipRect(i3, this.mSwitchTop, i4, this.mSwitchBottom);
      if (this.mTextColors != null) {
        this.mTextPaint.setColor(this.mTextColors.getColorForState(getDrawableState(), this.mTextColors.getDefaultColor()));
      }
      if ((getTargetCheckedState() ^ this.mTextOnThumb))
      {
        this.mTextPaint.setAlpha(i1 / 4);
        j = getThumbScrollRange() * 1 + i2 - this.mThumbExtraMovement;
        i = this.mThumbHeight;
        paramCanvas.save();
        paramCanvas.translate(0.0F, (j + (j + i)) / 2 - this.mOnLayout.getHeight() / 2);
        if ((this.mDrawableOn != null) && (this.mTextPaint.getAlpha() == i1)) {
          this.mDrawableOn.draw(paramCanvas);
        }
        paramCanvas.translate((this.mSwitchLeft + this.mSwitchRight) / 2 - this.mOnLayout.getWidth() / 2, 0.0F);
        this.mOnLayout.draw(paramCanvas);
        paramCanvas.restore();
        if (this.mTextColors != null) {
          this.mTextPaint.setColor(this.mTextColors.getColorForState(getDrawableState(), this.mTextColors.getDefaultColor()));
        }
        if (!(getTargetCheckedState() ^ this.mTextOnThumb)) {
          break label1796;
        }
        this.mTextPaint.setAlpha(i1);
      }
      for (;;)
      {
        j = i2 - this.mThumbExtraMovement;
        i = this.mThumbHeight;
        paramCanvas.save();
        paramCanvas.translate(0.0F, (j + (j + i)) / 2 - this.mOffLayout.getHeight() / 2);
        if ((this.mDrawableOff != null) && (this.mTextPaint.getAlpha() == i1)) {
          this.mDrawableOff.draw(paramCanvas);
        }
        paramCanvas.translate((this.mSwitchLeft + this.mSwitchRight) / 2 - this.mOffLayout.getWidth() / 2, 0.0F);
        this.mOffLayout.draw(paramCanvas);
        paramCanvas.restore();
        paramCanvas.restore();
        break;
        this.mTextPaint.setAlpha(i1);
        break label1503;
        this.mTextPaint.setAlpha(i1 / 4);
      }
      localObject = this.mOffLayout;
      break label567;
      i = i3 + this.mTrackTextPadding;
      break label670;
      j = i4 - this.mOnLayout.getWidth() - this.mTrackTextPadding;
      break label700;
      if (this.rightBitmap != null)
      {
        paramCanvas.save();
        if (paramCanvas.getClipBounds(this.canvasClipBounds))
        {
          localObject = this.canvasClipBounds;
          ((Rect)localObject).left = ((int)(((Rect)localObject).left + (this.mThumbPosition + this.mThumbWidth / 2)));
          paramCanvas.clipRect(this.canvasClipBounds);
        }
        paramCanvas.drawBitmap(this.rightBitmap, 0.0F, 0.0F, null);
        paramCanvas.restore();
      }
      if (this.leftBitmap != null)
      {
        paramCanvas.save();
        if (paramCanvas.getClipBounds(this.canvasClipBounds))
        {
          localObject = this.canvasClipBounds;
          ((Rect)localObject).right = ((int)(((Rect)localObject).right - (m - this.mThumbPosition + this.mThumbWidth / 2)));
          paramCanvas.clipRect(this.canvasClipBounds);
        }
        paramCanvas.drawBitmap(this.leftBitmap, 0.0F, 0.0F, null);
        paramCanvas.restore();
      }
      this.mTrackDrawable.draw(paramCanvas);
      paramCanvas.save();
      paramCanvas.clipRect(i3, this.mSwitchTop, i4, this.mSwitchBottom);
      if (this.mTextColors != null) {
        this.mTextPaint.setColor(this.mTextColors.getColorForState(getDrawableState(), this.mTextColors.getDefaultColor()));
      }
      this.mTextPaint.setAlpha(i1 / 4);
      if (getTargetCheckedState())
      {
        paramCanvas.save();
        paramCanvas.translate(j, i7 - this.mOnLayout.getHeight() / 2);
        if (paramCanvas.getClipBounds(this.canvasClipBounds))
        {
          localObject = this.canvasClipBounds;
          ((Rect)localObject).left = ((int)(((Rect)localObject).left + (this.mThumbPosition + this.mThumbWidth / 2)));
          paramCanvas.clipRect(this.canvasClipBounds);
        }
        this.mOnLayout.draw(paramCanvas);
        if (this.mDrawableOn != null) {
          this.mDrawableOn.draw(paramCanvas);
        }
        paramCanvas.restore();
        if (!this.mTextOnThumb) {
          this.mTextPaint.setAlpha(i1);
        }
        paramCanvas.save();
        paramCanvas.translate(i, i7 - this.mOffLayout.getHeight() / 2);
        if (paramCanvas.getClipBounds(this.canvasClipBounds))
        {
          localObject = this.canvasClipBounds;
          ((Rect)localObject).right = ((int)(((Rect)localObject).right - (m - this.mThumbPosition + this.mThumbWidth / 2)));
          paramCanvas.clipRect(this.canvasClipBounds);
        }
        this.mOffLayout.draw(paramCanvas);
        if (this.mDrawableOff != null) {
          this.mDrawableOff.draw(paramCanvas);
        }
        paramCanvas.restore();
      }
      for (;;)
      {
        paramCanvas.restore();
        break;
        paramCanvas.save();
        paramCanvas.translate(i, i7 - this.mOffLayout.getHeight() / 2);
        if (paramCanvas.getClipBounds(this.canvasClipBounds))
        {
          localObject = this.canvasClipBounds;
          ((Rect)localObject).right = ((int)(((Rect)localObject).right - (m - this.mThumbPosition + this.mThumbWidth / 2)));
          paramCanvas.clipRect(this.canvasClipBounds);
        }
        this.mOffLayout.draw(paramCanvas);
        if (this.mDrawableOff != null) {
          this.mDrawableOff.draw(paramCanvas);
        }
        paramCanvas.restore();
        if (!this.mTextOnThumb) {
          this.mTextPaint.setAlpha(i1);
        }
        paramCanvas.save();
        paramCanvas.translate(j, i7 - this.mOnLayout.getHeight() / 2);
        if (paramCanvas.getClipBounds(this.canvasClipBounds))
        {
          localObject = this.canvasClipBounds;
          ((Rect)localObject).left = ((int)(((Rect)localObject).left + (this.mThumbPosition + this.mThumbWidth / 2)));
          paramCanvas.clipRect(this.canvasClipBounds);
        }
        this.mOnLayout.draw(paramCanvas);
        if (this.mDrawableOn != null) {
          this.mDrawableOn.draw(paramCanvas);
        }
        paramCanvas.restore();
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    float f;
    switch (getGravity() & 0x70)
    {
    default: 
      getPaddingTop();
      paramInt1 = this.mSwitchHeight;
      this.mSwitchBottom = (this.mSwitchHeight - getPaddingBottom());
      this.mSwitchTop = (this.mSwitchBottom - this.mSwitchHeight);
      this.mSwitchRight = (this.mSwitchWidth - getPaddingRight());
      this.mSwitchLeft = (this.mSwitchRight - this.mSwitchWidth);
      if (this.mTextOnThumb) {
        if (isChecked())
        {
          f = getThumbScrollRange();
          label127:
          this.mThumbPosition = f;
          this.mTrackDrawable.setBounds(this.mSwitchLeft, this.mSwitchTop, this.mSwitchRight, this.mSwitchBottom);
          if (this.mDrawableOn != null) {
            this.mDrawableOn.setBounds(0, 0, this.mDrawableOn.getIntrinsicWidth(), this.mDrawableOn.getIntrinsicHeight());
          }
          if (this.mDrawableOff != null) {
            this.mDrawableOff.setBounds(0, 0, this.mDrawableOff.getIntrinsicWidth(), this.mDrawableOff.getIntrinsicHeight());
          }
          if (this.mLeftBackground != null) {
            this.mLeftBackground.setBounds(this.mSwitchLeft, this.mSwitchTop, this.mSwitchRight, this.mSwitchBottom);
          }
          if (this.mRightBackground != null) {
            this.mRightBackground.setBounds(this.mSwitchLeft, this.mSwitchTop, this.mSwitchRight, this.mSwitchBottom);
          }
          if (this.mMaskDrawable == null) {
            break label648;
          }
          this.tempBitmap = Bitmap.createBitmap(this.mSwitchRight - this.mSwitchLeft, this.mSwitchBottom - this.mSwitchTop, Bitmap.Config.ARGB_8888);
          this.backingLayer = new Canvas(this.tempBitmap);
          this.mMaskDrawable.setBounds(this.mSwitchLeft, this.mSwitchTop, this.mSwitchRight, this.mSwitchBottom);
          this.mMaskDrawable.draw(this.backingLayer);
          this.maskBitmap = Bitmap.createBitmap(this.mSwitchRight - this.mSwitchLeft, this.mSwitchBottom - this.mSwitchTop, Bitmap.Config.ARGB_8888);
          paramInt4 = this.tempBitmap.getWidth();
          paramInt3 = this.tempBitmap.getHeight();
        }
      }
      break;
    }
    for (paramInt1 = 0;; paramInt1++)
    {
      if (paramInt1 >= paramInt4) {
        break label540;
      }
      paramInt2 = 0;
      for (;;)
      {
        if (paramInt2 < paramInt3)
        {
          this.maskBitmap.setPixel(paramInt1, paramInt2, this.tempBitmap.getPixel(paramInt1, paramInt2) & 0xFF000000);
          paramInt2++;
          continue;
          paramInt1 = (getPaddingTop() + getHeight() - getPaddingBottom()) / 2;
          paramInt1 = this.mSwitchHeight / 2;
          paramInt1 = this.mSwitchHeight;
          break;
          getHeight();
          getPaddingBottom();
          paramInt1 = this.mSwitchHeight;
          break;
          f = 0.0F;
          break label127;
          if (isChecked()) {}
          for (f = 0.0F;; f = getThumbScrollRange())
          {
            this.mThumbPosition = f;
            break;
          }
        }
      }
    }
    label540:
    if (this.mLeftBackground != null)
    {
      this.mLeftBackground.draw(this.backingLayer);
      this.backingLayer.drawBitmap(this.maskBitmap, 0.0F, 0.0F, this.xferPaint);
      this.leftBitmap = this.tempBitmap.copy(this.tempBitmap.getConfig(), true);
    }
    if (this.mRightBackground != null)
    {
      this.mRightBackground.draw(this.backingLayer);
      this.backingLayer.drawBitmap(this.maskBitmap, 0.0F, 0.0F, this.xferPaint);
      this.rightBitmap = this.tempBitmap.copy(this.tempBitmap.getConfig(), true);
    }
    label648:
    if (this.mPushStyle)
    {
      paramInt1 = (this.mSwitchTop + this.mTrackPaddingRect.top + (this.mSwitchBottom - this.mTrackPaddingRect.bottom)) / 2;
      paramInt1 = Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth());
      paramInt3 = Math.max(this.mOnLayout.getHeight(), this.mOffLayout.getHeight());
      paramInt1 = paramInt1 * 2 + this.mTrackPaddingRect.left + this.mTrackPaddingRect.right + this.mThumbWidth + this.mTrackTextPadding * 4;
      paramInt2 = this.mSwitchBottom - this.mSwitchTop;
      if (this.mOrientation == 0)
      {
        paramInt2 = this.mTrackPaddingRect.top + this.mTrackTextPadding + paramInt3 + this.mTrackTextPadding + this.mThumbHeight + this.mTrackTextPadding + paramInt3 + this.mTrackTextPadding + this.mTrackPaddingRect.bottom;
        paramInt1 = this.mSwitchRight - this.mSwitchLeft;
      }
      this.pushBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(this.pushBitmap);
      this.mTextPaint.drawableState = getDrawableState();
      if (this.mTextColors != null) {
        this.mTextPaint.setColor(this.mTextColors.getColorForState(getDrawableState(), this.mTextColors.getDefaultColor()));
      }
      Rect localRect;
      if (this.leftBitmap != null)
      {
        localCanvas.save();
        if (localCanvas.getClipBounds(this.canvasClipBounds))
        {
          if (this.mOrientation == 1)
          {
            localRect = this.canvasClipBounds;
            localRect.right -= paramInt1 / 2;
          }
          if (this.mOrientation == 0)
          {
            localRect = this.canvasClipBounds;
            localRect.bottom -= paramInt2 / 2;
          }
          localCanvas.clipRect(this.canvasClipBounds);
        }
        localCanvas.drawBitmap(this.leftBitmap, 0.0F, 0.0F, null);
        localCanvas.restore();
      }
      if (this.rightBitmap != null)
      {
        localCanvas.save();
        if (localCanvas.getClipBounds(this.canvasClipBounds))
        {
          if (this.mOrientation == 1)
          {
            localRect = this.canvasClipBounds;
            localRect.left += paramInt1 / 2;
          }
          if (this.mOrientation == 0)
          {
            localRect = this.canvasClipBounds;
            localRect.top += paramInt2 / 2;
          }
          localCanvas.clipRect(this.canvasClipBounds);
        }
        if (this.mOrientation == 1) {
          localCanvas.translate(paramInt1 / 2 - this.mTrackPaddingRect.right, 0.0F);
        }
        if (this.mOrientation == 0) {
          localCanvas.translate(0.0F, paramInt2 / 2 - this.mTrackPaddingRect.bottom);
        }
        localCanvas.drawBitmap(this.rightBitmap, 0.0F, 0.0F, null);
        localCanvas.restore();
      }
    }
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    int m = View.MeasureSpec.getMode(paramInt1);
    int k = View.MeasureSpec.getMode(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int n = View.MeasureSpec.getSize(paramInt2);
    if (this.mOnLayout == null) {
      this.mOnLayout = makeLayout(this.mTextOn);
    }
    if (this.mOffLayout == null) {
      this.mOffLayout = makeLayout(this.mTextOff);
    }
    int i2 = Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth());
    int i1 = Math.max(this.mOnLayout.getHeight(), this.mOffLayout.getHeight());
    this.mThumbWidth = (this.mThumbTextPadding * 2 + i2 + this.mThPad.left + this.mThPad.right);
    this.mThumbWidth = Math.max(this.mThumbWidth, this.mThumbDrawable.getIntrinsicWidth());
    if (!this.mTextOnThumb)
    {
      this.mThumbWidth = this.mThumbDrawable.getIntrinsicWidth();
      if (this.mThumbWidth < 15) {
        this.mThumbWidth = 15;
      }
    }
    this.mThumbHeight = (this.mThumbTextPadding * 2 + i1 + this.mThPad.bottom + this.mThPad.top);
    this.mThumbHeight = Math.max(this.mThumbHeight, this.mThumbDrawable.getIntrinsicHeight());
    if (!this.mTextOnThumb)
    {
      this.mThumbHeight = this.mThumbDrawable.getIntrinsicHeight();
      if (this.mThumbHeight < 15) {
        this.mThumbHeight = 15;
      }
    }
    int i;
    if (this.mOrientation == 1)
    {
      i = Math.max(this.mSwitchMinWidth, i2 * 2 + this.mThumbTextPadding * 2 + this.mTrackTextPadding * 2 + this.mTrackPaddingRect.left + this.mTrackPaddingRect.right);
      if (!this.mTextOnThumb) {
        i = Math.max(this.mThumbWidth + i2 + this.mTrackTextPadding * 2 + (this.mTrackPaddingRect.right + this.mTrackPaddingRect.left) / 2, this.mSwitchMinWidth);
      }
      if (this.mPushStyle) {
        i = Math.max(this.mSwitchMinWidth, this.mThumbWidth + i2 + this.mTrackTextPadding + (this.mTrackPaddingRect.left + this.mTrackPaddingRect.right) / 2);
      }
      i2 = Math.max(this.mSwitchMinWidth, i);
      i = this.mTrackDrawable.getIntrinsicHeight();
      int i3 = this.mThumbDrawable.getIntrinsicHeight();
      i = Math.max(Math.max(i, Math.max(this.mSwitchMinHeight, i1)), i3);
      if (this.mOrientation == 0)
      {
        i = this.mOnLayout.getHeight() + this.mOffLayout.getHeight() + this.mThumbTextPadding * 2 + this.mThPad.top + this.mThPad.bottom + this.mTrackPaddingRect.bottom + this.mTrackPaddingRect.top + this.mTrackTextPadding * 2;
        if (!this.mTextOnThumb) {
          i = Math.max(this.mThumbHeight + i1 + (this.mTrackPaddingRect.bottom + this.mTrackPaddingRect.top) / 2 + this.mTrackTextPadding * 2, this.mSwitchMinHeight);
        }
        if (this.mPushStyle) {
          i = Math.max(this.mSwitchMinHeight, this.mThumbHeight + i1 + this.mTrackTextPadding + (this.mTrackPaddingRect.top + this.mTrackPaddingRect.bottom) / 2);
        }
      }
      switch (m)
      {
      default: 
        label644:
        switch (k)
        {
        }
        break;
      }
    }
    for (;;)
    {
      this.mSwitchWidth = i2;
      this.mSwitchHeight = i;
      super.onMeasure(paramInt1, paramInt2);
      paramInt1 = getMeasuredHeight();
      paramInt2 = getMeasuredWidth();
      if (paramInt1 < i) {
        setMeasuredDimension(getMeasuredWidth(), i);
      }
      if (paramInt2 < i2) {
        setMeasuredDimension(i2, getMeasuredHeight());
      }
      return;
      i = Math.max(this.mThumbTextPadding * 2 + i2 + this.mThPad.left + this.mThPad.right, this.mThumbWidth);
      if ((!this.mPushStyle) && (this.mTextOnThumb)) {
        break;
      }
      i = Math.max(this.mTrackTextPadding * 2 + i2 + this.mTrackPaddingRect.left + this.mTrackPaddingRect.right, this.mThumbWidth);
      break;
      Math.min(j, i2);
      break label644;
      break label644;
      break label644;
      Math.min(n, i);
      continue;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = true;
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (paramMotionEvent.getActionMasked())
    {
    }
    for (;;)
    {
      boolean bool1 = super.onTouchEvent(paramMotionEvent);
      for (;;)
      {
        return bool1;
        float f1 = paramMotionEvent.getX();
        float f2 = paramMotionEvent.getY();
        if ((!isEnabled()) || (!hitThumb(f1, f2))) {
          break;
        }
        this.mTouchMode = 1;
        this.mTouchX = f1;
        this.mTouchY = f2;
        break;
        switch (this.mTouchMode)
        {
        case 0: 
        default: 
          break;
        case 1: 
          f2 = paramMotionEvent.getX();
          f1 = paramMotionEvent.getY();
          if ((Math.abs(f2 - this.mTouchX) <= this.mTouchSlop / 2) && (Math.abs(f1 - this.mTouchY) <= this.mTouchSlop / 2)) {
            break;
          }
          this.mTouchMode = 2;
          if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
          }
          this.mTouchX = f2;
          this.mTouchY = f1;
          bool1 = bool2;
          break;
        case 2: 
          if (this.mOrientation == 1)
          {
            f1 = paramMotionEvent.getX();
            f2 = this.mTouchX;
            f2 = Math.max(0.0F, Math.min(this.mThumbPosition + (f1 - f2), getThumbScrollRange()));
            bool1 = bool2;
            if (f2 != this.mThumbPosition)
            {
              this.mThumbPosition = f2;
              this.mTouchX = f1;
              invalidate();
              bool1 = bool2;
            }
          }
          else
          {
            if (this.mOrientation != 0) {
              break;
            }
            f1 = paramMotionEvent.getY();
            f2 = this.mTouchY;
            f2 = Math.max(0.0F, Math.min(this.mThumbPosition + (f1 - f2), getThumbScrollRange()));
            bool1 = bool2;
            if (f2 != this.mThumbPosition)
            {
              this.mThumbPosition = f2;
              this.mTouchY = f1;
              invalidate();
              bool1 = bool2;
              continue;
              if (this.mTouchMode != 2) {
                break label385;
              }
              stopDrag(paramMotionEvent);
              bool1 = bool2;
            }
          }
          break;
        }
      }
      label385:
      this.mTouchMode = 0;
      this.mVelocityTracker.clear();
    }
  }
  
  public boolean performClick()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!this.clickDisabled)
    {
      if (this.fixed) {
        break label25;
      }
      bool1 = super.performClick();
    }
    for (;;)
    {
      return bool1;
      label25:
      bool1 = bool2;
      if (this.mOnChangeAttemptListener != null)
      {
        this.mOnChangeAttemptListener.onChangeAttempted(isChecked());
        bool1 = bool2;
      }
    }
  }
  
  public void setChecked(boolean paramBoolean)
  {
    super.setChecked(paramBoolean);
    boolean bool;
    if (!this.mTextOnThumb)
    {
      bool = true;
      if (!(bool ^ paramBoolean)) {
        break label45;
      }
    }
    label45:
    for (float f = getThumbScrollRange();; f = 0.0F)
    {
      if (this.mThumbPosition != f) {
        startAnimation();
      }
      return;
      bool = false;
      break;
    }
  }
  
  public void setOnChangeAttemptListener(OnChangeAttemptListener paramOnChangeAttemptListener)
  {
    this.mOnChangeAttemptListener = paramOnChangeAttemptListener;
  }
  
  public void setSwitchTextAppearance(Context paramContext, int paramInt)
  {
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramInt, R.styleable.CustomSwitchTextAppearanceAttrib);
    paramContext = localTypedArray.getColorStateList(0);
    if (paramContext != null) {}
    for (this.mTextColors = paramContext;; this.mTextColors = getTextColors())
    {
      paramInt = localTypedArray.getDimensionPixelSize(1, 0);
      if ((paramInt != 0) && (paramInt != this.mTextPaint.getTextSize()))
      {
        this.mTextPaint.setTextSize(paramInt);
        requestLayout();
      }
      setSwitchTypefaceByIndex(localTypedArray.getInt(3, -1), localTypedArray.getInt(2, -1));
      localTypedArray.recycle();
      return;
    }
  }
  
  public void setSwitchTypeface(Typeface paramTypeface)
  {
    if (this.mTextPaint.getTypeface() != paramTypeface)
    {
      this.mTextPaint.setTypeface(paramTypeface);
      requestLayout();
      invalidate();
    }
  }
  
  public void setSwitchTypeface(Typeface paramTypeface, int paramInt)
  {
    boolean bool = false;
    int i;
    label31:
    float f;
    if (paramInt > 0) {
      if (paramTypeface == null)
      {
        paramTypeface = Typeface.defaultFromStyle(paramInt);
        setSwitchTypeface(paramTypeface);
        if (paramTypeface == null) {
          break label88;
        }
        i = paramTypeface.getStyle();
        paramInt &= (i ^ 0xFFFFFFFF);
        paramTypeface = this.mTextPaint;
        if ((paramInt & 0x1) != 0) {
          bool = true;
        }
        paramTypeface.setFakeBoldText(bool);
        paramTypeface = this.mTextPaint;
        if ((paramInt & 0x2) == 0) {
          break label94;
        }
        f = -0.25F;
        label73:
        paramTypeface.setTextSkewX(f);
      }
    }
    for (;;)
    {
      return;
      paramTypeface = Typeface.create(paramTypeface, paramInt);
      break;
      label88:
      i = 0;
      break label31;
      label94:
      f = 0.0F;
      break label73;
      this.mTextPaint.setFakeBoldText(false);
      this.mTextPaint.setTextSkewX(0.0F);
      setSwitchTypeface(paramTypeface);
    }
  }
  
  public void setTextOff(CharSequence paramCharSequence)
  {
    this.mTextOff = paramCharSequence;
    this.mOffLayout = null;
    requestLayout();
  }
  
  public void setTextOn(CharSequence paramCharSequence)
  {
    this.mTextOn = paramCharSequence;
    this.mOnLayout = null;
    requestLayout();
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    if ((super.verifyDrawable(paramDrawable)) || (paramDrawable == this.mThumbDrawable) || (paramDrawable == this.mTrackDrawable)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static abstract interface OnChangeAttemptListener
  {
    public abstract void onChangeAttempted(boolean paramBoolean);
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/CustomSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */