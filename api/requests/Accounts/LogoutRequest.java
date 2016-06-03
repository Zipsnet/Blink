package com.immediasemi.blink.api.requests.Accounts;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.MessageResponse;

public class LogoutRequest
  extends BlinkRequest
{
  private static final String path = "/logout";
  private static final long serialVersionUID = 3656717094053385101L;
  
  public String getPath()
  {
    return "/logout";
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


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Accounts/LogoutRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */