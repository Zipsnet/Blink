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


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Clients/GetAccountClientsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */