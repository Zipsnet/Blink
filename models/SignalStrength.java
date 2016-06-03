package com.immediasemi.blink.models;

public class SignalStrength
  extends BlinkData
{
  private static final long serialVersionUID = 2889625566302438113L;
  protected int battery;
  protected int lfr;
  protected int temp;
  protected String updated_at;
  protected int wifi;
  
  public int getBattery()
  {
    return this.battery;
  }
  
  public int getLfr()
  {
    return this.lfr;
  }
  
  public int getTemp()
  {
    return this.temp;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
  
  public int getWifi()
  {
    return this.wifi;
  }
  
  public void setBattery(int paramInt)
  {
    this.battery = paramInt;
  }
  
  public void setLfr(int paramInt)
  {
    this.lfr = paramInt;
  }
  
  public void setTemp(int paramInt)
  {
    this.temp = paramInt;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
  
  public void setWifi(int paramInt)
  {
    this.wifi = paramInt;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/SignalStrength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */