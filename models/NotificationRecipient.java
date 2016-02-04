package com.immediasemi.blink.models;

public class NotificationRecipient
  extends BlinkData
{
  private static final long serialVersionUID = 1511937963021050415L;
  int account_id;
  int camera_id;
  String created_at;
  String deleted_at;
  int event_id;
  int id;
  String name;
  Notification.NOTIFICATION_TYPE notification_type;
  int siren_id;
  int sync_module_id;
  String updated_at;
  
  public int getAccount_id()
  {
    return this.account_id;
  }
  
  public int getCamera_id()
  {
    return this.camera_id;
  }
  
  public String getCreated_at()
  {
    return this.created_at;
  }
  
  public String getDeleted_at()
  {
    return this.deleted_at;
  }
  
  public int getEvent_id()
  {
    return this.event_id;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Notification.NOTIFICATION_TYPE getNotification_type()
  {
    return this.notification_type;
  }
  
  public int getSiren_id()
  {
    return this.siren_id;
  }
  
  public int getSync_module_id()
  {
    return this.sync_module_id;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
  
  public void setAccount_id(int paramInt)
  {
    this.account_id = paramInt;
  }
  
  public void setCamera_id(int paramInt)
  {
    this.camera_id = paramInt;
  }
  
  public void setCreated_at(String paramString)
  {
    this.created_at = paramString;
  }
  
  public void setDeleted_at(String paramString)
  {
    this.deleted_at = paramString;
  }
  
  public void setEvent_id(int paramInt)
  {
    this.event_id = paramInt;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setNotification_type(Notification.NOTIFICATION_TYPE paramNOTIFICATION_TYPE)
  {
    this.notification_type = paramNOTIFICATION_TYPE;
  }
  
  public void setSiren_id(int paramInt)
  {
    this.siren_id = paramInt;
  }
  
  public void setSync_module_id(int paramInt)
  {
    this.sync_module_id = paramInt;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/models/NotificationRecipient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */