package com.immediasemi.blink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import com.immediasemi.blink.fragments.BaseFragment.OnFragmentInteractionListener.InteractionAction;
import com.immediasemi.blink.fragments.VideoListViewFragment;

public class VideoListViewActivity
  extends BaseActivity
{
  private static final String SAVED_STATE_CAMIDS = "saved_state_camods";
  private static final String SAVED_STATE_CAMNAMES = "saved_state_camnames";
  public static final int VIDEO_PLAYER_REQUEST = 1;
  private View mActionBarView;
  
  private void setMenuItemVisibilty(boolean paramBoolean)
  {
    TextView localTextView1 = (TextView)this.mActionBarView.findViewById(2131558539);
    TextView localTextView3 = (TextView)this.mActionBarView.findViewById(2131558538);
    TextView localTextView2 = (TextView)this.mActionBarView.findViewById(2131558488);
    if (paramBoolean)
    {
      localTextView1.setVisibility(0);
      localTextView3.setVisibility(0);
      localTextView2.setText(getString(2131099991));
    }
    for (;;)
    {
      return;
      localTextView1.setVisibility(4);
      localTextView3.setVisibility(4);
      localTextView2.setText(getString(2131099992));
    }
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    getSupportActionBar().setTitle(2131099992);
    setFragmentTitle(getString(2131099992));
  }
  
  public void onCancelClicked(View paramView)
  {
    onBackPressed();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903074);
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    this.mActionBarView = getLayoutInflater().inflate(2130903078, null);
    paramBundle = getSupportActionBar();
    paramBundle.setCustomView(this.mActionBarView);
    paramBundle.setDisplayOptions(16);
    setMenuItemVisibilty(false);
    getIntent().getExtras();
    getSupportFragmentManager().beginTransaction().replace(2131558537, VideoListViewFragment.newInstance(-1)).commit();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }
  
  public void onDoneClicked(View paramView)
  {
    onBackPressed();
  }
  
  public void onFragmentInteraction(int paramInt, BaseFragment.OnFragmentInteractionListener.InteractionAction paramInteractionAction, Object paramObject)
  {
    switch (paramInteractionAction)
    {
    default: 
      super.onFragmentInteraction(paramInt, paramInteractionAction, paramObject);
    }
    for (;;)
    {
      return;
      getSupportFragmentManager().beginTransaction().replace(2131558537, (Fragment)paramObject).commit();
      continue;
      getSupportFragmentManager().beginTransaction().add(2131558537, (Fragment)paramObject).addToBackStack(((Fragment)paramObject).getTag()).commit();
    }
  }
  
  public void setFragmentTitle(String paramString)
  {
    setActionBarTitle(paramString);
    ((TextView)this.mActionBarView.findViewById(2131558488)).setText(paramString);
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/activities/VideoListViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */