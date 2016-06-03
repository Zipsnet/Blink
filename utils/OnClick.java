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
    if ((l - mLastClick > 150L) || (l - mLastClick < 0L)) {
      mLastClick = l;
    }
    for (boolean bool = mEnableClicks;; bool = false)
    {
      return bool;
      mLastClick = l;
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/OnClick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */