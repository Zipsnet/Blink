package com.immediasemi.blink.api.requests.Onboarding;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.MessageResponse;

public class SetSSIDRequest
  extends BlinkRequest
{
  private static final String path = "/api/set/ssid";
  private static final long serialVersionUID = 258018989152879778L;
  protected String dns;
  protected String domain;
  protected SSId[] ssids;
  
  public String getDns()
  {
    return this.dns;
  }
  
  public String getDomain()
  {
    return this.domain;
  }
  
  public String getPath()
  {
    return "/api/set/ssid";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return MessageResponse.class;
  }
  
  public SSId[] getSsids()
  {
    return this.ssids;
  }
  
  public void setDns(String paramString)
  {
    this.dns = paramString;
  }
  
  public void setDomain(String paramString)
  {
    this.domain = paramString;
  }
  
  public void setSsids(SSId[] paramArrayOfSSId)
  {
    this.ssids = paramArrayOfSSId;
  }
  
  public static class SSId
  {
    protected String password;
    protected String ssid;
    
    public String getPassword()
    {
      return this.password;
    }
    
    public String getSsid()
    {
      return this.ssid;
    }
    
    public void setPassword(String paramString)
    {
      this.password = paramString;
    }
    
    public void setSsid(String paramString)
    {
      this.ssid = paramString;
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Onboarding/SetSSIDRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */