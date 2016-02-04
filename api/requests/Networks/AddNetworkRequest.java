package com.immediasemi.blink.api.requests.Networks;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.ANetwork;

public class AddNetworkRequest
  extends BlinkRequest
{
  private static final String path = "/network/add";
  private static final long serialVersionUID = -777820648338911679L;
  protected String description;
  protected String locale;
  protected String name;
  protected Integer network;
  protected String timezone;
  
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
  
  public int getNetwork()
  {
    return this.network.intValue();
  }
  
  public String getPath()
  {
    return "/network/add";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return ANetwork.class;
  }
  
  public String getTimezone()
  {
    return this.timezone;
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
  
  public void setNetwork(int paramInt)
  {
    this.network = Integer.valueOf(paramInt);
  }
  
  public void setTimezone(String paramString)
  {
    this.timezone = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/api/requests/Networks/AddNetworkRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */