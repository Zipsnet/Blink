package com.immediasemi.blink.api.requests.Health;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class CheckPortsRequest
  extends BlinkRequest
{
  private static final String path = "/health/:numbers";
  private static final long serialVersionUID = 794073045479806672L;
  
  public String getPath()
  {
    return "/health/:numbers";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Health/CheckPortsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */