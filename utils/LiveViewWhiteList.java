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
      Scanner localScanner = new java/util/Scanner;
      Object localObject = new java/io/File;
      ((File)localObject).<init>("/proc/cpuinfo");
      localScanner.<init>((File)localObject);
      while (localScanner.hasNextLine())
      {
        localObject = localScanner.nextLine().split(": ");
        if (localObject.length > 1) {
          localHashMap.put(localObject[0].trim(), localObject[1].trim());
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
    boolean bool2 = true;
    boolean bool1;
    if ((Build.VERSION.SDK_INT >= 21) && (Build.VERSION.SDK_INT >= 22))
    {
      bool1 = bool2;
      return bool1;
    }
    String[] arrayOfString = BlinkApp.getApp().getApplicationContext().getResources().getStringArray(2131427330);
    String str2 = getProcCPUInfoHardware();
    for (int i = 0;; i++)
    {
      bool1 = bool2;
      if (i >= arrayOfString.length) {
        break;
      }
      String str1 = arrayOfString[i];
      if ((str2.length() > 0) && (str2.equals(str1)))
      {
        bool1 = false;
        break;
      }
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/LiveViewWhiteList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */