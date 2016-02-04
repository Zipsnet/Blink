package com.immediasemi.blink.models;

public class AccessPoints
  extends BlinkData
{
  private static final long serialVersionUID = -5926434145829962443L;
  protected AccessPoint[] access_points;
  
  public AccessPoint[] getAccessPoints()
  {
    return this.access_points;
  }
  
  public void setAccessPoints(AccessPoint[] paramArrayOfAccessPoint)
  {
    this.access_points = paramArrayOfAccessPoint;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/models/AccessPoints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */