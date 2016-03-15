package com.immediasemi.blink.api.requests.Networks;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetNetworkInformationRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network";
  private static final long serialVersionUID = -8328530704628132336L;
  
  public String getPath()
  {
    return "/network/:network";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Networks/GetNetworkInformationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */