package com.immediasemi.blink.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.immediasemi.blink.BlinkApp;
import com.immediasemi.blink.gcm.BlinkGcmRegistrationIntentService;
import com.immediasemi.blink.utils.ChooseDialog;
import com.immediasemi.blink.utils.ChooseDialog.NoticeDialogListener;
import com.immediasemi.blink.utils.OnClick;

public class SplashActivity
  extends BaseActivity
  implements ChooseDialog.NoticeDialogListener
{
  private ChooseDialog mChooseDialog;
  private long mTimeStamp;
  private String[] mUrls;
  private boolean mWasLongClicked;
  
  private void showChooseServer()
  {
    int i = this.mUrls.length - 1;
    for (;;)
    {
      if ((i < 0) || (this.mUrls[i].equals(BlinkApp.getApp().getServerUrl())))
      {
        Bundle localBundle = new Bundle();
        localBundle.putString("Title", getString(2131099782));
        localBundle.putStringArray("List", this.mUrls);
        localBundle.putInt("Current_selection", i);
        localBundle.putInt("Layout_id_key", 0);
        localBundle.putString("Ok_Button_Label", getResources().getString(2131099886));
        this.mChooseDialog = new ChooseDialog();
        this.mChooseDialog.setArguments(localBundle);
        this.mChooseDialog.show(getSupportFragmentManager(), "ChooseDialog");
        return;
      }
      i -= 1;
    }
  }
  
  public void enterNewIP(String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Change this IP?");
    final EditText localEditText = new EditText(this);
    localEditText.setText(paramString);
    localEditText.setInputType(1);
    localBuilder.setView(localEditText);
    localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = localEditText.getText().toString();
        BlinkApp.getApp().setServerUrl(paramAnonymousDialogInterface);
        SplashActivity.this.mUrls[3] = paramAnonymousDialogInterface;
        SplashActivity.this.mChooseDialog.dismiss();
        SplashActivity.this.showChooseServer();
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.show();
  }
  
  protected void initActionBar()
  {
    getSupportActionBar().hide();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903141);
    this.mUrls = getResources().getStringArray(2131427331);
    BlinkApp.getApp().getServerUrl();
    if (BlinkApp.getApp().getDeviceToken() == null) {
      startService(new Intent(this, BlinkGcmRegistrationIntentService.class));
    }
    paramBundle = (Button)findViewById(2131558651);
    Button localButton = (Button)findViewById(2131558747);
    TextView localTextView = (TextView)findViewById(2131558749);
    try
    {
      PackageInfo localPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
      String[] arrayOfString = (getString(2131100006) + localPackageInfo.versionName).split("\\.");
      localTextView.setText(arrayOfString[0] + "." + arrayOfString[1] + "." + localPackageInfo.versionCode);
      if ((getApplicationInfo().flags & 0x2) != 0)
      {
        i = 1;
        if (i != 0) {
          break label253;
        }
        BlinkApp.getApp().setServerUrl(getString(2131099915));
        paramBundle.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {
              return;
            }
            paramAnonymousView = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(paramAnonymousView, null);
          }
        });
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {
              return;
            }
            paramAnonymousView = new Intent(SplashActivity.this, CreateAccountActivity.class);
            SplashActivity.this.startActivity(paramAnonymousView, null);
          }
        });
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
        continue;
        int i = 0;
        continue;
        label253:
        View localView = findViewById(2131558745);
        localView.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            SplashActivity.access$002(SplashActivity.this, System.currentTimeMillis());
            SplashActivity.access$102(SplashActivity.this, true);
            return true;
          }
        });
        localView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!OnClick.ok()) {}
            do
            {
              return;
              long l = System.currentTimeMillis();
              if (l - SplashActivity.this.mTimeStamp > 3000L) {
                SplashActivity.access$102(SplashActivity.this, false);
              }
              SplashActivity.access$002(SplashActivity.this, l);
            } while (!SplashActivity.this.mWasLongClicked);
            SplashActivity.access$102(SplashActivity.this, false);
            SplashActivity.this.showChooseServer();
          }
        });
      }
    }
  }
  
  public void onDialogExtraButtonClick(DialogFragment paramDialogFragment) {}
  
  public void onDialogListClick(DialogFragment paramDialogFragment, int paramInt)
  {
    BlinkApp.getApp().setServerUrl(this.mUrls[paramInt]);
    if (paramInt == 3) {
      enterNewIP(this.mUrls[paramInt]);
    }
  }
  
  public void onDialogNegativeClick(DialogFragment paramDialogFragment) {}
  
  public void onDialogPositiveClick(DialogFragment paramDialogFragment) {}
  
  protected void onResume()
  {
    super.onResume();
    if (BlinkApp.getApp().getLoggedIn())
    {
      Intent localIntent = new Intent(this, MainActivity.class);
      Bundle localBundle = new Bundle();
      localBundle.putBoolean("arg_app_is_bootin", true);
      localIntent.putExtras(localBundle);
      startActivity(localIntent, null);
      finish();
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/Blick_output_jar.jar!/com/immediasemi/blink/activities/SplashActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */