package com.immediasemi.blink.utils;

public class OnClick
{
  private static final long MINIMUM_KEY_BOUNCE = 150L;
  private static boolean mBlockAllClicks = false;
  private static long mTimeStamp = 0L;
  
  public static void blockAllClicks(boolean paramBoolean)
  {
    mBlockAllClicks = paramBoolean;
  }
  
  public static boolean ok()
  {
    long l = System.currentTimeMillis();
    if (mBlockAllClicks)
    {
      mTimeStamp = l;
      return false;
    }
    if ((l - mTimeStamp > 150L) || (l - mTimeStamp < 0L))
    {
      mTimeStamp = l;
      return true;
    }
    mTimeStamp = l;
    return false;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/utils/OnClick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */