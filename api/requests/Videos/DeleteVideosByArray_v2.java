package com.immediasemi.blink.api.requests.Videos;

import com.immediasemi.blink.api.requests.BlinkRequest;
import java.util.List;

public class DeleteVideosByArray_v2
  extends BlinkRequest
{
  private static final String path = "/api/v2/videos/delete";
  private static final long serialVersionUID = 1232928653918222925L;
  protected List<Integer> video_list;
  
  public String getPath()
  {
    return "/api/v2/videos/delete";
  }
  
  public int getRequestType()
  {
    return 1;
  }
  
  public List<Integer> getVideo_list()
  {
    return this.video_list;
  }
  
  public void setVideo_list(List<Integer> paramList)
  {
    this.video_list = paramList;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/api/requests/Videos/DeleteVideosByArray_v2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */