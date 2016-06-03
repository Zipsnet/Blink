package com.immediasemi.blink.api.requests.Command;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Commands;

public class CommandRequest
  extends BlinkRequest
{
  private static final String path = "/network/:network/command/";
  private static final long serialVersionUID = -3175469647550486469L;
  protected String mCommand;
  
  public String getCommand()
  {
    return this.mCommand;
  }
  
  public String getPath()
  {
    return "/network/:network/command/" + this.mCommand;
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return Commands.class;
  }
  
  public void setCommand(String paramString)
  {
    this.mCommand = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Command/CommandRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */