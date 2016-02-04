package com.immediasemi.blink.api.requests.Events;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetEventsForSirenRequest
  extends BlinkRequest
{
  private static final String path = "/events/network/:network/siren/:siren";
  private static final long serialVersionUID = -1959489221435657070L;
  
  public String getPath()
  {
    return "/events/network/:network/siren/:siren";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Events/GetEventsForSirenRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */