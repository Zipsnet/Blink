package com.immediasemi.blink.models;

public class AccessLogs
  extends BlinkData
{
  private static final long serialVersionUID = 7717028231316979948L;
  protected int account_id;
  protected String action;
  protected String created_at;
  protected String deleted_at;
  protected int id;
  protected boolean is_admin;
  protected String updated_at;
  
  public int getAccount_id()
  {
    return this.account_id;
  }
  
  public String getAction()
  {
    return this.action;
  }
  
  public String getCreated_at()
  {
    return this.created_at;
  }
  
  public String getDeleted_at()
  {
    return this.deleted_at;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
  
  public boolean is_admin()
  {
    return this.is_admin;
  }
  
  public void setAccount_id(int paramInt)
  {
    this.account_id = paramInt;
  }
  
  public void setAction(String paramString)
  {
    this.action = paramString;
  }
  
  public void setCreated_at(String paramString)
  {
    this.created_at = paramString;
  }
  
  public void setDeleted_at(String paramString)
  {
    this.deleted_at = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setIs_admin(boolean paramBoolean)
  {
    this.is_admin = paramBoolean;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/AccessLogs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */