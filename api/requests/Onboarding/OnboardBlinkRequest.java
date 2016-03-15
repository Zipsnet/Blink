package com.immediasemi.blink.api.requests.Onboarding;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.AccessPoints;

public class OnboardBlinkRequest
  extends BlinkRequest
{
  private static final String path = "/api/ssids";
  private static final long serialVersionUID = -5714037260981037134L;
  
  public String getPath()
  {
    return "/api/ssids";
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return AccessPoints.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Onboarding/OnboardBlinkRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */