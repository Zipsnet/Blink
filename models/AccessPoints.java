package com.immediasemi.blink.models;

public class AccessPoints
  extends BlinkData
{
  private static final long serialVersionUID = -5926434145829962443L;
  protected AccessPoint[] access_points;
  protected boolean manual_ssid;
  protected String version;
  
  public AccessPoint[] getAccessPoints()
  {
    return this.access_points;
  }
  
  public boolean getManual_ssid()
  {
    return this.manual_ssid;
  }
  
  public String getVersion()
  {
    return this.version;
  }
  
  public void setAccessPoints(AccessPoint[] paramArrayOfAccessPoint)
  {
    this.access_points = paramArrayOfAccessPoint;
  }
  
  public void setManual_ssid(boolean paramBoolean)
  {
    this.manual_ssid = paramBoolean;
  }
  
  public void setVersion(String paramString)
  {
    this.version = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/AccessPoints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */