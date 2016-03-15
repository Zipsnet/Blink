package com.immediasemi.blink.api.requests.Accounts;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.AuthToken;

public class RegistrationRequest
  extends BlinkRequest
{
  private static final String path = "/account/register";
  private static final long serialVersionUID = -1411213089799187767L;
  protected String client_name;
  protected String client_specifier;
  protected String client_type;
  protected String client_version;
  protected String email;
  protected String name;
  protected String notification_key;
  protected String password;
  protected String password_confirm;
  protected String phone_number;
  
  public String getClient_name()
  {
    return this.client_name;
  }
  
  public String getClient_specifier()
  {
    return this.client_specifier;
  }
  
  public String getClient_type()
  {
    return this.client_type;
  }
  
  public String getClient_version()
  {
    return this.client_version;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getNotification_key()
  {
    return this.notification_key;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getPassword_confirm()
  {
    return this.password_confirm;
  }
  
  public String getPath()
  {
    return "/account/register";
  }
  
  public String getPhone_number()
  {
    return this.phone_number;
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return AuthToken.class;
  }
  
  public void setClient_name(String paramString)
  {
    this.client_name = paramString;
  }
  
  public void setClient_specifier(String paramString)
  {
    this.client_specifier = paramString;
  }
  
  public void setClient_type(String paramString)
  {
    this.client_type = paramString;
  }
  
  public void setClient_version(String paramString)
  {
    this.client_version = paramString;
  }
  
  public void setEmail(String paramString)
  {
    this.email = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setNotification_key(String paramString)
  {
    this.notification_key = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.password = paramString;
  }
  
  public void setPassword_confirm(String paramString)
  {
    this.password_confirm = paramString;
  }
  
  public void setPhone_number(String paramString)
  {
    this.phone_number = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Accounts/RegistrationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */