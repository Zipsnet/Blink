package com.immediasemi.blink.api.requests;

import com.immediasemi.blink.models.BlinkData;

public class LogUploadRequest
  extends BlinkRequest
{
  private static final String path = "/app/logs/upload/?error_code=:command";
  private static final long serialVersionUID = -5374052653277297094L;
  private String log_file;
  
  public String getLog_file()
  {
    return this.log_file;
  }
  
  public String getPath()
  {
    return "/app/logs/upload/?error_code=:command";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public Class getResponseClass()
  {
    return BlinkData.class;
  }
  
  public void setLog_file(String paramString)
  {
    this.log_file = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/LogUploadRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */