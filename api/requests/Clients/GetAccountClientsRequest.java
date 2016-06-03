package com.immediasemi.blink.api.requests.Clients;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class GetAccountClientsRequest
  extends BlinkRequest
{
  private static final String path = "/account/clients";
  private static final long serialVersionUID = 7998593154510046363L;
  
  public String getPath()
  {
    return "/account/clients";
  }
  
  public int getRequestType()
  {
    return 0;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Clients/GetAccountClientsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */