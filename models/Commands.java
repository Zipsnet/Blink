package com.immediasemi.blink.models;

public class Commands
  extends BlinkData
{
  private static final long serialVersionUID = -7755399719151333927L;
  public Command[] commands;
  public boolean complete;
  public int status;
  public String status_msg;
  
  public Command[] getCommands()
  {
    return this.commands;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public String getStatus_msg()
  {
    return this.status_msg;
  }
  
  public boolean isComplete()
  {
    return this.complete;
  }
  
  public void setCommands(Command[] paramArrayOfCommand)
  {
    this.commands = paramArrayOfCommand;
  }
  
  public void setComplete(boolean paramBoolean)
  {
    this.complete = paramBoolean;
  }
  
  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }
  
  public void setStatus_msg(String paramString)
  {
    this.status_msg = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/Commands.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */