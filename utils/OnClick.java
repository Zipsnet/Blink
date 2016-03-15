package com.immediasemi.blink.utils;

public class OnClick
{
  private static final long MINIMUM_KEY_BOUNCE = 150L;
  private static boolean mEnableClicks = true;
  private static long mLastClick = 0L;
  
  public static void enableClicks(boolean paramBoolean)
  {
    mEnableClicks = paramBoolean;
  }
  
  public static boolean ok()
  {
    long l = System.currentTimeMillis();
    if ((l - mLastClick > 150L) || (l - mLastClick < 0L))
    {
      mLastClick = l;
      return mEnableClicks;
    }
    mLastClick = l;
    return false;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/utils/OnClick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */