package com.immediasemi.blink.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.immediasemi.blink.models.Network;
import java.util.ArrayList;

public class SystemPickerAdapter
  extends ArrayAdapter<Network>
{
  public SystemPickerAdapter(Context paramContext, ArrayList<Network> paramArrayList)
  {
    super(paramContext, 0, paramArrayList);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Network localNetwork = (Network)getItem(paramInt);
    View localView = paramView;
    if (paramView == null) {
      localView = LayoutInflater.from(getContext()).inflate(2130903148, paramViewGroup, false);
    }
    paramViewGroup = (TextView)localView.findViewById(2131558767);
    paramView = (TextView)localView.findViewById(2131558768);
    paramViewGroup.setText(localNetwork.getName());
    paramView.setText(localNetwork.getArm_string());
    return localView;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/utils/SystemPickerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */