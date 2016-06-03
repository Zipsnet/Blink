package com.immediasemi.blink.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NotificationsUtils
{
  private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
  private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
  public static final String TAG = "NotificationsUtils";
  
  public static boolean isNotificationEnabled(Context paramContext)
  {
    AppOpsManager localAppOpsManager = (AppOpsManager)paramContext.getSystemService("appops");
    Object localObject = paramContext.getApplicationInfo();
    paramContext = paramContext.getApplicationContext().getPackageName();
    int i = ((ApplicationInfo)localObject).uid;
    for (;;)
    {
      try
      {
        localObject = Class.forName(AppOpsManager.class.getName());
        i = ((Integer)((Class)localObject).getMethod("checkOpNoThrow", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localAppOpsManager, new Object[] { Integer.valueOf(((Integer)((Class)localObject).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), paramContext })).intValue();
        if (i != 0) {
          continue;
        }
        bool = true;
      }
      catch (ClassNotFoundException paramContext)
      {
        Log.e("NotificationsUtils", paramContext.toString());
        boolean bool = false;
        continue;
      }
      catch (NoSuchMethodException paramContext)
      {
        Log.e("NotificationsUtils", paramContext.toString());
        continue;
      }
      catch (NoSuchFieldException paramContext)
      {
        Log.e("NotificationsUtils", paramContext.toString());
        continue;
      }
      catch (InvocationTargetException paramContext)
      {
        Log.e("NotificationsUtils", paramContext.toString());
        continue;
      }
      catch (IllegalAccessException paramContext)
      {
        Log.e("NotificationsUtils", paramContext.toString());
        continue;
      }
      return bool;
      bool = false;
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/NotificationsUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */