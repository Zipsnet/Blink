package com.immediasemi.blink.api.requests.Health;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class CheckPresetPortsRequest
  extends BlinkRequest
{
  private static final String path = "/health/";
  private static final long serialVersionUID = -3046958839956058726L;
  
  public String getPath()
  {
    return "/health/";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Health/CheckPresetPortsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */