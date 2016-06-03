package com.immediasemi.blink.models;

public class AccessPoint
  extends BlinkData
{
  private static final long serialVersionUID = 2567429316283101801L;
  protected String encryption;
  protected String quality;
  protected String signal;
  protected String ssid;
  
  public String getEncryption()
  {
    return this.encryption;
  }
  
  public String getQuality()
  {
    return this.quality;
  }
  
  public String getSignal()
  {
    return this.signal;
  }
  
  public String getSsid()
  {
    return this.ssid;
  }
  
  public void setEncryption(String paramString)
  {
    this.encryption = paramString;
  }
  
  public void setQuality(String paramString)
  {
    this.quality = paramString;
  }
  
  public void setSignal(String paramString)
  {
    this.signal = paramString;
  }
  
  public void setSsid(String paramString)
  {
    this.ssid = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/AccessPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */