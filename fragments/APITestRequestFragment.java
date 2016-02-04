package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.activities.BaseActivity;
import com.immediasemi.blink.api.BlinkAPI;
import com.immediasemi.blink.api.BlinkAPI.BlinkAPICallback;
import com.immediasemi.blink.api.requests.BlinkRequest;
import com.immediasemi.blink.models.BlinkData;
import com.immediasemi.blink.models.BlinkError;
import com.immediasemi.blink.utils.OnClick;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class APITestRequestFragment
  extends BaseFragment
  implements TextWatcher
{
  private static final String REQUEST_NAME_KEY = "name";
  private Map<String, String> mApiRequestFields;
  private BlinkRequest mBlinkRequest;
  private List<String> mFieldNameArray;
  private LayoutInflater mInflater;
  private String mPath;
  private List<String> mPathParamNames;
  private List<String> mPathParamValues;
  private String mRequestName;
  
  public static APITestRequestFragment newInstance(String paramString)
  {
    APITestRequestFragment localAPITestRequestFragment = new APITestRequestFragment();
    localAPITestRequestFragment.setTitle(paramString);
    Bundle localBundle = new Bundle();
    localBundle.putString("name", paramString);
    localAPITestRequestFragment.setArguments(localBundle);
    return localAPITestRequestFragment;
  }
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(this.mRequestName);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null)
    {
      this.mRequestName = getArguments().getString("name");
      paramBundle = "com.immediasemi.blink.api.requests." + this.mRequestName;
      try
      {
        this.mBlinkRequest = ((BlinkRequest)Class.forName(paramBundle).getConstructor((Class[])null).newInstance(new Object[0]));
        this.mApiRequestFields = this.mBlinkRequest.getRequestBody(true);
        this.mPath = this.mBlinkRequest.getPath();
        if (this.mPath.contains(":"))
        {
          this.mPathParamNames = new ArrayList();
          this.mPathParamValues = new ArrayList();
          paramBundle = this.mPath.split(":");
          int i = 1;
          while (i < paramBundle.length)
          {
            if (paramBundle[i].startsWith("network"))
            {
              this.mPathParamNames.add(":network");
              this.mPathParamValues.add(BlinkApp.getApp().getLastNetworkId());
            }
            else if (paramBundle[i].startsWith("syncmodule"))
            {
              this.mPathParamNames.add(":syncmodule");
              this.mPathParamValues.add(BlinkApp.getApp().getLastSyncModuleId());
            }
            else if (paramBundle[i].startsWith("camera"))
            {
              this.mPathParamNames.add(":camera");
              this.mPathParamValues.add(BlinkApp.getApp().getLastCameraId());
            }
            else if (paramBundle[i].startsWith("page"))
            {
              this.mPathParamNames.add(":page");
              this.mPathParamValues.add(BlinkApp.getApp().getLastPage());
            }
            i += 1;
          }
        }
        return;
      }
      catch (Exception paramBundle) {}
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mInflater = paramLayoutInflater;
    paramLayoutInflater = paramLayoutInflater.inflate(2130903095, paramViewGroup, false);
    ((TextView)paramLayoutInflater.findViewById(2131558590)).setText(this.mPath);
    if (this.mPathParamNames != null)
    {
      paramLayoutInflater.findViewById(2131558591).setVisibility(0);
      paramViewGroup = (LinearLayout)paramLayoutInflater.findViewById(2131558592);
      int i = 0;
      while (i < this.mPathParamNames.size())
      {
        paramBundle = this.mInflater.inflate(2130903096, paramViewGroup, false);
        ((TextView)paramBundle.findViewById(2131558595)).setText((CharSequence)this.mPathParamNames.get(i));
        ((EditText)paramBundle.findViewById(2131558596)).setText((CharSequence)this.mPathParamValues.get(i));
        paramViewGroup.addView(paramBundle);
        i += 1;
      }
    }
    paramViewGroup = (ListView)paramLayoutInflater.findViewById(16908298);
    paramBundle = this.mApiRequestFields.keySet();
    this.mFieldNameArray = new ArrayList();
    paramBundle = paramBundle.iterator();
    while (paramBundle.hasNext()) {
      this.mFieldNameArray.add(paramBundle.next());
    }
    if (this.mFieldNameArray.size() == 0) {
      paramLayoutInflater.findViewById(2131558593).setVisibility(8);
    }
    paramViewGroup.setAdapter(new TestArrayAdapter(getActivity()));
    ((Button)paramLayoutInflater.findViewById(2131558594)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!OnClick.ok()) {
          return;
        }
        if (APITestRequestFragment.this.mPathParamNames != null)
        {
          paramAnonymousView = (LinearLayout)APITestRequestFragment.this.getView().findViewById(2131558592);
          int i = 0;
          if (i < APITestRequestFragment.this.mPathParamNames.size())
          {
            String str = ((EditText)paramAnonymousView.getChildAt(i).findViewById(2131558596)).getText().toString();
            if (((String)APITestRequestFragment.this.mPathParamNames.get(i)).contains("network")) {
              BlinkApp.getApp().setLastNetworkId(str);
            }
            for (;;)
            {
              i += 1;
              break;
              if (((String)APITestRequestFragment.this.mPathParamNames.get(i)).contains("syncmodule")) {
                BlinkApp.getApp().setlastSyncModuleId(str);
              } else if (((String)APITestRequestFragment.this.mPathParamNames.get(i)).contains("camera")) {
                BlinkApp.getApp().setLastCameraId(str);
              } else if (((String)APITestRequestFragment.this.mPathParamNames.get(i)).contains("page")) {
                BlinkApp.getApp().setLastPage(str);
              }
            }
          }
        }
        BlinkAPI.BlinkAPIRequest(null, null, APITestRequestFragment.this.mBlinkRequest, new BlinkAPI.BlinkAPICallback()
        {
          public void onError(BlinkError paramAnonymous2BlinkError)
          {
            if (APITestRequestFragment.this.getActivity() != null) {
              new AlertDialog.Builder(APITestRequestFragment.this.getActivity()).setTitle("Error").setMessage(paramAnonymous2BlinkError.getErrorMessage()).setPositiveButton(2131099886, null).create().show();
            }
          }
          
          public void onResult(BlinkData paramAnonymous2BlinkData)
          {
            paramAnonymous2BlinkData = BlinkData.mRawResponse;
            if (APITestRequestFragment.this.getActivity() != null) {
              new AlertDialog.Builder(APITestRequestFragment.this.getActivity()).setTitle("Results").setMessage(paramAnonymous2BlinkData).setPositiveButton(2131099886, null).create().show();
            }
          }
        }, true);
      }
    });
    return paramLayoutInflater;
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = getView().findFocus();
    try
    {
      paramInt1 = ((Integer)((EditText)localObject).getTag()).intValue();
      localObject = this.mBlinkRequest.getClass().getDeclaredField((String)this.mFieldNameArray.get(paramInt1));
      ((Field)localObject).setAccessible(true);
      Class localClass = ((Field)localObject).getType();
      if (localClass.getSimpleName().contains("String"))
      {
        ((Field)localObject).set(this.mBlinkRequest, paramCharSequence.toString());
        return;
      }
      if (localClass.getSimpleName().contains("Integer"))
      {
        ((Field)localObject).set(this.mBlinkRequest, Integer.valueOf(Integer.parseInt(paramCharSequence.toString())));
        return;
      }
    }
    catch (Exception paramCharSequence) {}
  }
  
  public void setTitle(String paramString)
  {
    this.mRequestName = paramString;
  }
  
  private class TestArrayAdapter
    extends ArrayAdapter
  {
    public TestArrayAdapter(Context paramContext)
    {
      super(2130903096, 2131558595, APITestRequestFragment.this.mFieldNameArray);
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView = paramView;
      if (paramView == null) {
        localView = APITestRequestFragment.this.mInflater.inflate(2130903096, paramViewGroup, false);
      }
      paramView = (TextView)localView.findViewById(2131558595);
      EditText localEditText = (EditText)localView.findViewById(2131558596);
      paramView.setText((CharSequence)APITestRequestFragment.this.mFieldNameArray.get(paramInt));
      paramViewGroup = (String)APITestRequestFragment.this.mApiRequestFields.get(APITestRequestFragment.this.mFieldNameArray.get(paramInt));
      paramView = paramViewGroup;
      if (paramViewGroup == null) {
        paramView = "";
      }
      localEditText.setText(paramView);
      localEditText.removeTextChangedListener(APITestRequestFragment.this);
      localEditText.addTextChangedListener(APITestRequestFragment.this);
      localEditText.setTag(new Integer(paramInt));
      return localView;
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/fragments/APITestRequestFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */