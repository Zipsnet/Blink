package com.immediasemi.blink.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

public abstract class SectionAdapter
  extends BaseAdapter
  implements AdapterView.OnItemClickListener
{
  private int mCount = -1;
  
  private int numberOfCellsInSection(int paramInt)
  {
    int i = numberOfRows(paramInt);
    if (hasSectionHeaderView(paramInt)) {}
    for (paramInt = 1;; paramInt = 0) {
      return paramInt + i;
    }
  }
  
  public boolean disableHeaders()
  {
    return false;
  }
  
  public final int getCount()
  {
    if (this.mCount < 0) {
      this.mCount = numberOfCellsBeforeSection(numberOfSections());
    }
    return this.mCount;
  }
  
  public final Object getItem(int paramInt)
  {
    int i = getSection(paramInt);
    Object localObject;
    if (isSectionHeader(paramInt)) {
      if (hasSectionHeaderView(i)) {
        localObject = getSectionHeaderItem(i);
      }
    }
    for (;;)
    {
      return localObject;
      localObject = null;
      continue;
      localObject = getRowItem(i, getRowInSection(paramInt));
    }
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public final int getItemViewType(int paramInt)
  {
    int i = getSection(paramInt);
    if (isSectionHeader(paramInt)) {}
    for (paramInt = getRowViewTypeCount() + getSectionHeaderItemViewType(i);; paramInt = getRowItemViewType(i, getRowInSection(paramInt))) {
      return paramInt;
    }
  }
  
  protected int getRowInSection(int paramInt)
  {
    int j = getSection(paramInt);
    int i = paramInt - numberOfCellsBeforeSection(j);
    paramInt = i;
    if (hasSectionHeaderView(j)) {
      paramInt = i - 1;
    }
    return paramInt;
  }
  
  public abstract Object getRowItem(int paramInt1, int paramInt2);
  
  public int getRowItemViewType(int paramInt1, int paramInt2)
  {
    return 0;
  }
  
  public abstract View getRowView(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup);
  
  public int getRowViewTypeCount()
  {
    return 1;
  }
  
  protected int getSection(int paramInt)
  {
    int i = 0;
    int j = 0;
    while ((j <= paramInt) && (i <= numberOfSections()))
    {
      j += numberOfCellsInSection(i);
      i++;
    }
    return i - 1;
  }
  
  public Object getSectionHeaderItem(int paramInt)
  {
    return null;
  }
  
  public int getSectionHeaderItemViewType(int paramInt)
  {
    return 0;
  }
  
  public View getSectionHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return null;
  }
  
  public int getSectionHeaderViewTypeCount()
  {
    return 1;
  }
  
  public final View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = getSection(paramInt);
    if (isSectionHeader(paramInt)) {
      if (hasSectionHeaderView(i)) {
        paramView = getSectionHeaderView(i, paramView, paramViewGroup);
      }
    }
    for (;;)
    {
      return paramView;
      paramView = null;
      continue;
      paramView = getRowView(i, getRowInSection(paramInt), paramView, paramViewGroup);
    }
  }
  
  public final int getViewTypeCount()
  {
    return getRowViewTypeCount() + getSectionHeaderViewTypeCount();
  }
  
  public boolean hasSectionHeaderView(int paramInt)
  {
    return false;
  }
  
  public boolean isEmpty()
  {
    if (getCount() == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isEnabled(int paramInt)
  {
    if (((disableHeaders()) || (!isSectionHeader(paramInt))) && (isRowEnabled(getSection(paramInt), getRowInSection(paramInt)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isRowEnabled(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  protected boolean isSectionHeader(int paramInt)
  {
    int i = getSection(paramInt);
    if ((hasSectionHeaderView(i)) && (numberOfCellsBeforeSection(i) == paramInt)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void notifyDataSetChanged()
  {
    this.mCount = numberOfCellsBeforeSection(numberOfSections());
    super.notifyDataSetChanged();
  }
  
  public void notifyDataSetInvalidated()
  {
    this.mCount = numberOfCellsBeforeSection(numberOfSections());
    super.notifyDataSetInvalidated();
  }
  
  protected int numberOfCellsBeforeSection(int paramInt)
  {
    int j = 0;
    for (int i = 0; i < Math.min(numberOfSections(), paramInt); i++) {
      j += numberOfCellsInSection(i);
    }
    return j;
  }
  
  public abstract int numberOfRows(int paramInt);
  
  public abstract int numberOfSections();
  
  public final void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    onRowItemClick(paramAdapterView, paramView, getSection(paramInt), getRowInSection(paramInt), paramLong);
  }
  
  public void onRowItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt1, int paramInt2, long paramLong) {}
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/SectionAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */