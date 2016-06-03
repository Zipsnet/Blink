package com.immediasemi.blink.api.requests.Networks;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class DeleteNetworkRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/delete";
  private static final long serialVersionUID = -7519158060555478635L;
  
  public String getPath()
  {
    return "/network/:network/delete";
  }
  
  public int getRequestType()
  {
    return 1;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Networks/DeleteNetworkRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */