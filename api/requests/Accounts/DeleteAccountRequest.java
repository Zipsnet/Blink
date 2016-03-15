package com.immediasemi.blink.api.requests.Accounts;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.MessageResponse;

public class DeleteAccountRequest
  extends BlinkRequest
{
  private static final String path = "/account/delete";
  private static final long serialVersionUID = -7318170456231447978L;
  protected String password;
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getPath()
  {
    return "/account/delete";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return MessageResponse.class;
  }
  
  public void setPassword(String paramString)
  {
    this.password = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Accounts/DeleteAccountRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */