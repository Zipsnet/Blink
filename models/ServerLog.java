package com.immediasemi.blink.models;

public class ServerLog
  extends BlinkData
{
  private static final long serialVersionUID = 7761763275196715851L;
  protected int account_id;
  protected String created_at;
  protected String deleted_at;
  protected int error_code;
  protected int id;
  protected String server;
  protected String server_instance;
  protected int severity;
  protected String text;
  protected String updated_at;
  
  public int getAccount_id()
  {
    return this.account_id;
  }
  
  public String getCreated_at()
  {
    return this.created_at;
  }
  
  public String getDeleted_at()
  {
    return this.deleted_at;
  }
  
  public int getError_code()
  {
    return this.error_code;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getServer()
  {
    return this.server;
  }
  
  public String getServer_instance()
  {
    return this.server_instance;
  }
  
  public int getSeverity()
  {
    return this.severity;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
  
  public void setAccount_id(int paramInt)
  {
    this.account_id = paramInt;
  }
  
  public void setCreated_at(String paramString)
  {
    this.created_at = paramString;
  }
  
  public void setDeleted_at(String paramString)
  {
    this.deleted_at = paramString;
  }
  
  public void setError_code(int paramInt)
  {
    this.error_code = paramInt;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setServer(String paramString)
  {
    this.server = paramString;
  }
  
  public void setServer_instance(String paramString)
  {
    this.server_instance = paramString;
  }
  
  public void setSeverity(int paramInt)
  {
    this.severity = paramInt;
  }
  
  public void setText(String paramString)
  {
    this.text = paramString;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/ServerLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */