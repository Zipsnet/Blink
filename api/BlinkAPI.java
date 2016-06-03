package com.immediasemi.blink.api;

import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BlinkAPI
{
  public static final String BLINK_REQUEST_QUEUE_TAG = "BlinkTagXYZ";
  public static final String TOKEN_AUTH = "TOKEN_AUTH";
  
  public static void BlinkAPIRequest(Map<String, String> paramMap1, Map<String, String> paramMap2, BlinkRequest paramBlinkRequest, BlinkAPICallback paramBlinkAPICallback, boolean paramBoolean)
  {
    String str1 = resolvePath(paramMap1, paramBlinkRequest.getPath());
    int i = paramBlinkRequest.getRequestType();
    Object localObject = str1;
    if (paramMap1 != null)
    {
      localObject = str1;
      if (paramMap1.size() > 0)
      {
        localObject = "?";
        Iterator localIterator = paramMap1.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str2 = (String)localIterator.next();
          str2 = (String)localObject + str2 + "=" + (String)paramMap1.get(str2);
          localObject = str2;
          if (localIterator.hasNext()) {
            localObject = str2 + "&";
          }
        }
        localObject = str1 + (String)localObject;
      }
    }
    paramMap1 = new GsonRequest(i, BlinkApp.getApp().getServerUrl() + (String)localObject, paramBlinkRequest.getResponseClass(), null, paramMap2, paramBlinkRequest, new Response.Listener()new Response.ErrorListener
    {
      public void onResponse(BlinkData paramAnonymousBlinkData)
      {
        String str = BlinkData.mRawResponse;
        if (str != null) {
          Log.d("GsonResponse", str);
        }
        if (this.val$callback != null) {
          this.val$callback.onResult(paramAnonymousBlinkData);
        }
      }
    }, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        Log.d("GsonResponse", paramAnonymousVolleyError.toString());
        if (this.val$callback != null) {
          this.val$callback.onError(new BlinkError(paramAnonymousVolleyError));
        }
      }
    }, paramBoolean);
    if (i == 1) {
      paramMap1.setShouldCache(false);
    }
    paramMap1.setTag("BlinkTagXYZ");
    BlinkApp.getVollyRequestQueue().add(paramMap1);
  }
  
  public static void BlinkOnboardingRequest(Map<String, String> paramMap1, Map<String, String> paramMap2, BlinkRequest paramBlinkRequest, BlinkAPICallback paramBlinkAPICallback, int paramInt)
  {
    String str1 = resolvePath(paramMap1, paramBlinkRequest.getPath());
    int i = paramBlinkRequest.getRequestType();
    Object localObject = str1;
    if (paramMap1 != null)
    {
      localObject = str1;
      if (paramMap1.size() > 0)
      {
        localObject = "?";
        Iterator localIterator = paramMap1.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str2 = (String)localIterator.next();
          str2 = (String)localObject + str2 + "=" + (String)paramMap1.get(str2);
          localObject = str2;
          if (localIterator.hasNext()) {
            localObject = str2 + "&";
          }
        }
        localObject = str1 + (String)localObject;
      }
    }
    paramMap1 = new GsonRequest(i, BlinkApp.getApp().getServerUrl() + (String)localObject, paramBlinkRequest.getResponseClass(), null, paramMap2, paramBlinkRequest, new Response.Listener()new Response.ErrorListener
    {
      public void onResponse(BlinkData paramAnonymousBlinkData)
      {
        if (this.val$callback != null) {
          this.val$callback.onResult(paramAnonymousBlinkData);
        }
      }
    }, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        if (this.val$callback != null) {
          this.val$callback.onError(new BlinkError(paramAnonymousVolleyError));
        }
      }
    }, paramInt);
    if (i == 1) {
      paramMap1.setShouldCache(false);
    }
    paramMap1.setTag("BlinkTagXYZ");
    BlinkApp.getVollyRequestQueue().add(paramMap1);
  }
  
  protected static void cancelAllRequests()
  {
    BlinkApp.getVollyRequestQueue().cancelAll("BlinkTagXYZ");
  }
  
  private static String resolvePath(Map<String, String> paramMap, String paramString)
  {
    Object localObject = paramString;
    if (paramString.contains(":"))
    {
      String[] arrayOfString = paramString.split(":");
      int i = 1;
      String str = paramString;
      localObject = str;
      if (i < arrayOfString.length)
      {
        int j = arrayOfString[i].indexOf("/");
        if (j < 0)
        {
          localObject = arrayOfString[i];
          label56:
          paramString = null;
          if (paramMap != null) {
            paramString = (String)paramMap.get(localObject);
          }
          if (paramString == null) {
            break label128;
          }
          paramString = str.replace(":" + (String)localObject, paramString);
        }
        for (;;)
        {
          i++;
          str = paramString;
          break;
          localObject = arrayOfString[i].substring(0, j);
          break label56;
          label128:
          if (((String)localObject).equals("network"))
          {
            paramString = str.replace(":" + (String)localObject, BlinkApp.getApp().getLastNetworkId());
          }
          else if (((String)localObject).equals("syncmodule"))
          {
            paramString = str.replace(":" + (String)localObject, BlinkApp.getApp().getLastSyncModuleId());
          }
          else if (((String)localObject).equals("camera"))
          {
            paramString = str.replace(":" + (String)localObject, BlinkApp.getApp().getLastCameraId());
          }
          else if (((String)localObject).equals("page"))
          {
            paramString = str.replace(":" + (String)localObject, BlinkApp.getApp().getLastPage());
          }
          else if (((String)localObject).equals("command"))
          {
            paramString = str.replace(":" + (String)localObject, BlinkApp.getApp().getLastCommand());
          }
          else
          {
            paramString = str;
            if (((String)localObject).equals("video")) {
              paramString = str.replace(":" + (String)localObject, BlinkApp.getApp().getLastVideoId());
            }
          }
        }
      }
    }
    return (String)localObject;
  }
  
  public static abstract interface BlinkAPICallback
  {
    public abstract void onError(BlinkError paramBlinkError);
    
    public abstract void onResult(BlinkData paramBlinkData);
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/BlinkAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */