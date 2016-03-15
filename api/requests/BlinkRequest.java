package com.immediasemi.blink.api.requests;

import com.immediasemi.blink.models.BlinkData;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public abstract class BlinkRequest
  implements Serializable
{
  private static final long serialVersionUID = 4825423151192735291L;
  
  public abstract String getPath();
  
  public Map<String, String> getQuery()
  {
    return null;
  }
  
  public Map<String, String> getRequestBody(boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    Field[] arrayOfField = getClass().getDeclaredFields();
    int j = arrayOfField.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Field localField = arrayOfField[i];
        int k = localField.getModifiers();
        if ((!Modifier.isAbstract(k)) && (!Modifier.isFinal(k)) && (!Modifier.isStatic(k)) && (!Modifier.isInterface(k)) && ((Modifier.isPrivate(k)) || (Modifier.isPublic(k)) || (Modifier.isProtected(k)))) {
          try
          {
            localField.setAccessible(true);
            Object localObject = localField.get(this);
            if ((!paramBoolean) && (localObject == null)) {
              break label152;
            }
            localHashMap.put(localField.getName(), String.valueOf(localObject));
          }
          catch (Exception localException) {}
        }
      }
      else
      {
        return localHashMap;
      }
      label152:
      i += 1;
    }
  }
  
  public abstract int getRequestType();
  
  public Class getResponseClass()
  {
    return BlinkData.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/BlinkRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */