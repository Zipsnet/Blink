package com.immediasemi.blink.models;

import java.util.ArrayList;
import java.util.Iterator;

public class RegionManager
  extends BlinkData
{
  private static final long serialVersionUID = -6112444585322708907L;
  protected boolean getRegionsSuceeded;
  protected Integer indexOfPreferredRegion;
  protected String preferred;
  protected ArrayList<Region> regions;
  
  public ArrayList<String> getAllDNSSubdomains()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.regions.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((Region)localIterator.next()).dnsSubdomain);
    }
    return localArrayList;
  }
  
  public ArrayList<String> getAllFriendlyNames()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.regions.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((Region)localIterator.next()).friendlyName);
    }
    return localArrayList;
  }
  
  public Integer getIndexOfPreferredRegion()
  {
    return this.indexOfPreferredRegion;
  }
  
  public String getPreferred()
  {
    return this.preferred;
  }
  
  public ArrayList<Region> getRegions()
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
  
  public void setRegions(ArrayList<Region> paramArrayList)
  {
    this.regions = paramArrayList;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/RegionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */