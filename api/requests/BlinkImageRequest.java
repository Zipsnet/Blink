package com.immediasemi.blink.api.requests;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.ImageView.ScaleType;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.models.BlinkBitmap;
import java.util.HashMap;
import java.util.Map;

public class BlinkImageRequest
  extends ImageRequest
{
  public BlinkImageRequest(String paramString, BlinkAPI.BlinkAPICallback paramBlinkAPICallback, int paramInt1, int paramInt2, ImageView.ScaleType paramScaleType, Bitmap.Config paramConfig)
  {
    super(BlinkApp.getApp().getServerUrl() + paramString + ".jpg", new Response.Listener()
    {
      public void onResponse(Bitmap paramAnonymousBitmap)
      {
        BlinkImageRequest.this.onResult(new BlinkBitmap(paramAnonymousBitmap));
      }
    }, paramInt1, paramInt2, paramScaleType, paramConfig, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        BlinkImageRequest.this.onError(null);
      }
    });
    setTag("BlinkTagXYZ");
    BlinkApp.getVollyRequestQueue().add(this);
  }
  
  protected void deliverResponse(Bitmap paramBitmap)
  {
    super.deliverResponse(paramBitmap);
  }
  
  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    Object localObject = new HashMap();
    if (BlinkApp.getApp().getLoggedIn()) {
      ((Map)localObject).put("TOKEN_AUTH", BlinkApp.getApp().getLoginAuthToken());
    }
    for (;;)
    {
      return (Map<String, String>)localObject;
      localObject = super.getHeaders();
    }
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/BlinkImageRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */