package com.immediasemi.blink.api.requests.Networks;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Networks;

public class ListNetworksRequest
  extends BlinkRequest
{
  private static final String path = "/networks";
  private static final long serialVersionUID = -2915408066229499980L;
  
  public String getPath()
  {
    return "/networks";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return Networks.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Networks/ListNetworksRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */