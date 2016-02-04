package com.immediasemi.blink.api.requests.Clients;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetUserClientsRequest
  extends BlinkRequest
{
  private static final String path = "/clients";
  private static final long serialVersionUID = 7775663828237181986L;
  
  public String getPath()
  {
    return "/clients";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Clients/GetUserClientsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */