package com.immediasemi.blink.models;

public class Client
  extends BlinkData
{
  private static final long serialVersionUID = -4049318806863276256L;
  protected int account_id;
  protected String client_specifier;
  protected Enum client_type;
  protected String created_at;
  protected String deleted_at;
  protected Boolean home;
  protected int id;
  protected String name;
  protected String notification_key;
  protected String updated_at;
  protected int user_id;
  
  public int getAccount_id()
  {
    return this.account_id;
  }
  
  public String getClient_specifier()
  {
    return this.client_specifier;
  }
  
  public Enum getClient_type()
  {
    return this.client_type;
  }
  
  public String getCreated_at()
  {
    return this.created_at;
  }
  
  public String getDeleted_at()
  {
    return this.deleted_at;
  }
  
  public Boolean getHome()
  {
    return this.home;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getNotification_key()
  {
    return this.notification_key;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
  
  public int getUser_id()
  {
    return this.user_id;
  }
  
  public void setAccount_id(int paramInt)
  {
    this.account_id = paramInt;
  }
  
  public void setClient_specifier(String paramString)
  {
    this.client_specifier = paramString;
  }
  
  public void setClient_type(Enum paramEnum)
  {
    this.client_type = paramEnum;
  }
  
  public void setCreated_at(String paramString)
  {
    this.created_at = paramString;
  }
  
  public void setDeleted_at(String paramString)
  {
    this.deleted_at = paramString;
  }
  
  public void setHome(Boolean paramBoolean)
  {
    this.home = paramBoolean;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setNotification_key(String paramString)
  {
    this.notification_key = paramString;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
  
  public void setUser_id(int paramInt)
  {
    this.user_id = paramInt;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/models/Client.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */