package com.immediasemi.blink.api.requests.Clients;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class DeleteClientRequest
  extends BlinkRequest
{
  private static final String path = "/client/:client/delete";
  private static final long serialVersionUID = -7041475117072682249L;
  
  public String getPath()
  {
    return "/client/:client/delete";
  }
  
  public int getRequestType()
  {
    return 1;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Clients/DeleteClientRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */