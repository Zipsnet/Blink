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


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/models/Events.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */