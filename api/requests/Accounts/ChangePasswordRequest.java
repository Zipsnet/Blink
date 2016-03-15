package com.immediasemi.blink.api.requests.Accounts;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class ChangePasswordRequest
  extends BlinkRequest
{
  private static final String path = "/account/change_password";
  private static final long serialVersionUID = -3988566334851410801L;
  protected String new_password;
  protected String new_password_confirm;
  protected String old_password;
  
  public String getNew_password()
  {
    return this.new_password;
  }
  
  public String getNew_password_confirm()
  {
    return this.new_password_confirm;
  }
  
  public String getOld_password()
  {
    return this.old_password;
  }
  
  public String getPath()
  {
    return "/account/change_password";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public void setNew_password(String paramString)
  {
    this.new_password = paramString;
  }
  
  public void setNew_password_confirm(String paramString)
  {
    this.new_password_confirm = paramString;
  }
  
  public void setOld_password(String paramString)
  {
    this.old_password = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Accounts/ChangePasswordRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */