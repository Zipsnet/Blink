package com.immediasemi.blink.models;

public class Events
  extends BlinkData
{
  private static final long serialVersionUID = 1010547420687329899L;
  protected Event[] event;
  
  public Event[] getEvents()
  {
    return this.event;
  }
  
  public void setEvents(Event[] paramArrayOfEvent)
  {
    this.event = paramArrayOfEvent;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/Events.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */