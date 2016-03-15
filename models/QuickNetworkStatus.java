package com.immediasemi.blink.models;

public class QuickNetworkStatus
  extends BlinkData
{
  private static final long serialVersionUID = 975888500915676045L;
  protected Boolean armed;
  protected String error_msg;
  protected Integer notifications;
  protected String status;
  protected Integer warning;
  
  public Boolean getArmed()
  {
    return this.armed;
  }
  
  public String getError_msg()
  {
    return this.error_msg;
  }
  
  public Integer getNotifications()
  {
    return this.notifications;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public Integer getWarning()
  {
    return this.warning;
  }
  
  public void setArmed(Boolean paramBoolean)
  {
    this.armed = paramBoolean;
  }
  
  public void setError_msg(String paramString)
  {
    this.error_msg = paramString;
  }
  
  public void setNotifications(Integer paramInteger)
  {
    this.notifications = paramInteger;
  }
  
  public void setStatus(String paramString)
  {
    this.status = paramString;
  }
  
  public void setWarning(Integer paramInteger)
  {
    this.warning = paramInteger;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/models/QuickNetworkStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */