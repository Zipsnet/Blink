package com.immediasemi.blink.api.requests.Onboarding;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.MessageResponse;

public class OnboardingDoneRequest
  extends BlinkRequest
{
  private static final String path = "/api/setup/done";
  private static final long serialVersionUID = 874983188644049816L;
  
  public String getPath()
  {
    return "/api/setup/done";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return MessageResponse.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Onboarding/OnboardingDoneRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */