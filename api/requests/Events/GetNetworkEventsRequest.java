package com.immediasemi.blink.api.requests.Events;

import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.Events;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GetNetworkEventsRequest
  extends BlinkRequest
{
  private static final String path = "/events/network/:network/";
  private static final long serialVersionUID = -8252380944231958254L;
  private Integer mDate;
  private Integer mDurationHours;
  private Integer mHour;
  
  public GetNetworkEventsRequest() {}
  
  public GetNetworkEventsRequest(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mDate = Integer.valueOf(paramInt1);
    this.mHour = Integer.valueOf(paramInt2);
    this.mDurationHours = Integer.valueOf(paramInt3);
  }
  
  private String getFormattedDate(int paramInt)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ", Locale.US);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(5, this.mDate.intValue());
    if (paramInt != 0)
    {
      localCalendar.set(11, this.mHour.intValue() + paramInt - 1);
      localCalendar.set(12, 59);
      localCalendar.set(13, 59);
      localCalendar.set(14, 999);
    }
    for (;;)
    {
      return localSimpleDateFormat.format(localCalendar.getTime()).replaceAll(" ", "T");
      localCalendar.set(11, this.mHour.intValue());
      localCalendar.set(12, 0);
      localCalendar.set(13, 0);
      localCalendar.set(14, 0);
    }
  }
  
  public String getPath()
  {
    return "/events/network/:network/";
  }
  
  public Map<String, String> getQuery()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("start", getFormattedDate(0));
    localHashMap.put("end", getFormattedDate(this.mDurationHours.intValue()));
    return localHashMap;
  }
  
  public int getRequestType()
  {
    return 0;
  }
  
  public Class getResponseClass()
  {
    return Events.class;
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/api/requests/Events/GetNetworkEventsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */