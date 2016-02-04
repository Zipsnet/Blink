package com.immediasemi.blink.api.requests.Networks;

import com.immediasemi.blink.api.requests.BlinkRequest;

public class UpdateNetworkRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/update";
  private static final long serialVersionUID = -5239282264737107196L;
  protected String description;
  protected String locale;
  protected String name;
  protected String time_zone;
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getLocale()
  {
    return this.locale;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPath()
  {
    return "/network/:network/update";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public String getTime_zone()
  {
    return this.time_zone;
  }
  
  public void setDescription(String paramString)
  {
    this.description = paramString;
  }
  
  public void setLocale(String paramString)
  {
    this.locale = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setTime_zone(String paramString)
  {
    this.time_zone = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Networks/UpdateNetworkRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */