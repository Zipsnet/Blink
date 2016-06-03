package com.immediasemi.blink.api.requests;

import android.util.Log;
import com.immediasemi.blink.models.BlinkData;
import java.util.Locale;

public class BlinkRegionsRequest
  extends BlinkRequest
{
  private static final String path = String.format("/regions?locale=%s", new Object[] { Locale.getDefault().getCountry() });
  private static final long serialVersionUID = -2440009680173151643L;
  
  public String getPath()
  {
    Log.i("Regions endpoint", String.format("Regions endpoint: %s", new Object[] { path }));
    return path;
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return BlinkData.class;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/BlinkRegionsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */