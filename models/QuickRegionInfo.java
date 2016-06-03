package com.immediasemi.blink.models;

import java.util.HashMap;

public class QuickRegionInfo
  extends BlinkData
{
  private static final long serialVersionUID = 4604222410976062415L;
  protected boolean getRegionsSuceeded;
  protected Integer indexOfPreferredRegion;
  protected String preferred;
  protected HashMap<String, HashMap> regions;
  
  public Integer getIndexOfPreferredRegion()
  {
    return this.indexOfPreferredRegion;
  }
  
  public String getPreferred()
  {
    return this.preferred;
  }
  
  public HashMap<String, HashMap> getRegions()
  {
    return this.regions;
  }
  
  public boolean isGetRegionsSuceeded()
  {
    return this.getRegionsSuceeded;
  }
  
  public void setGetRegionsSuceeded(boolean paramBoolean)
  {
    this.getRegionsSuceeded = paramBoolean;
  }
  
  public void setIndexOfPreferredRegion(Integer paramInteger)
  {
    this.indexOfPreferredRegion = paramInteger;
  }
  
  public void setPreferred(String paramString)
  {
    this.preferred = paramString;
  }
  
  public void setRegions(HashMap<String, HashMap> paramHashMap)
  {
    this.regions = paramHashMap;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/QuickRegionInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */