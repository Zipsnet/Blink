package com.immediasemi.blink.api.requests.Events;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetAllEventsRequest
  extends BlinkRequest
{
  private static final String path = "/events";
  private static final long serialVersionUID = -5560374505701298392L;
  
  public String getPath()
  {
    return "/events";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Events/GetAllEventsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */