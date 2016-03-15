package com.immediasemi.blink.api.requests.Clients;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class AddClientRequest
  extends BlinkRequest
{
  private static final String path = "/client/add";
  private static final long serialVersionUID = -5054424375845468463L;
  protected String client_name;
  protected String client_specifier;
  protected String client_type;
  protected String client_version;
  protected String notification_key;
  
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
  
  public String getNotification_key()
  {
    return this.notification_key;
  }
  
  public String getPath()
  {
    return "/client/add";
  }
  
  public int getRequestType()
  {
    return 1;
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
  
  public void setNotification_key(String paramString)
  {
    this.notification_key = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Clients/AddClientRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */