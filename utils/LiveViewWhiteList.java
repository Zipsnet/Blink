package com.immediasemi.blink.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.Log;
import com.immediasemi.blink.BlinkApp;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LiveViewWhiteList
{
  private static Map<String, String> getCpuInfoMap()
  {
    localHashMap = new HashMap();
    try
    {
      Scanner localScanner = new Scanner(new File("/proc/cpuinfo"));
      while (localScanner.hasNextLine())
      {
        String[] arrayOfString = localScanner.nextLine().split(": ");
        if (arrayOfString.length > 1) {
          localHashMap.put(arrayOfString[0].trim(), arrayOfString[1].trim());
        }
      }
      return localHashMap;
    }
    catch (Exception localException)
    {
      Log.e("getCpuInfoMap", Log.getStackTraceString(localException));
    }
  }
  
  public static String getProcCPUInfoHardware()
  {
    return (String)getCpuInfoMap().get("Hardware");
  }
  
  public static boolean requiresLibAV()
  {
    if ((Build.VERSION.SDK_INT >= 21) && (Build.VERSION.SDK_INT >= 22)) {}
    for (;;)
    {
      return true;
      String[] arrayOfString = BlinkApp.getApp().getApplicationContext().getResources().getStringArray(2131427330);
      String str1 = getProcCPUInfoHardware();
      int i = 0;
      while (i < arrayOfString.length)
      {
        String str2 = arrayOfString[i];
        if ((str1.length() > 0) && (str1.equals(str2))) {
          return false;
        }
        i += 1;
      }
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/utils/LiveViewWhiteList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */