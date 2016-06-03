package com.immediasemi.blink.models;

public class Region
  extends BlinkData
{
  private static final long serialVersionUID = 2085090200871262095L;
  protected String dnsSubdomain;
  protected String friendlyName;
  protected String regionCode;
  
  public String getDnsSubdomain()
  {
    return this.dnsSubdomain;
  }
  
  public String getFriendlyName()
  {
    return this.friendlyName;
  }
  
  public String getRegionCode()
  {
    return this.regionCode;
  }
  
  public void setDnsSubdomain(String paramString)
  {
    this.dnsSubdomain = paramString;
  }
  
  public void setFriendlyName(String paramString)
  {
    this.friendlyName = paramString;
  }
  
  public void setRegionCode(String paramString)
  {
    this.regionCode = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/Region.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */