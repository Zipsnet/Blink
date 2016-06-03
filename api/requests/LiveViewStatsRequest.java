package com.immediasemi.blink.api.requests;

import com.immediasemi.blink.models.BlinkData;

public class LiveViewStatsRequest
  extends BlinkRequest
{
  private static final String path = "/app/logs/upload/?error_code=:command";
  private static final long serialVersionUID = 8117552560975387718L;
  private String live_view_stats;
  
  public String getLive_view_stats()
  {
    return this.live_view_stats;
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
  
  public void setLive_view_stats(String paramString)
  {
    this.live_view_stats = paramString;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/LiveViewStatsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */