package com.immediasemi.blink.api.requests.Accounts;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.AuthToken;

public class LoginRequest
  extends BlinkRequest
{
  private static final String path = "/login";
  private static final long serialVersionUID = 2637077361038775221L;
  protected String client_name;
  protected String client_specifier;
  protected String client_type;
  protected String client_version;
  protected String email;
  protected String notification_key;
  protected String password;
  
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
  
  public String getNotification_key()
  {
    return this.notification_key;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getPath()
  {
    return "/login";
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
  
  public void setNotification_key(String paramString)
  {
    this.notification_key = paramString;
  }
  
  public void setPassword(String paramString)
  {
    this.password = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Accounts/LoginRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */