package com.immediasemi.blink.api.requests.Accounts;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class UpdateAccountRequest
  extends BlinkRequest
{
  private static final String path = "/account/update";
  private static final long serialVersionUID = 1914843727459361114L;
  protected String email;
  protected String password;
  protected String phone;
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getPath()
  {
    return "/account/update";
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.password = paramString;
  }
  
  public void setPhone(String paramString)
  {
    this.phone = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Accounts/UpdateAccountRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */