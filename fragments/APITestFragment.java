package com.immediasemi.blink.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.immediasemi.blink.activities.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class APITestFragment
  extends BaseFragment
{
  private List<String> mApiRequests;
  private int mSectionNumber;
  
  public static APITestFragment newInstance(int paramInt)
  {
    APITestFragment localAPITestFragment = new APITestFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("arg_section_number", paramInt);
    localAPITestFragment.setArguments(localBundle);
    return localAPITestFragment;
  }
  
  public void launchRequestForm(String paramString)
  {
    paramString = APITestRequestFragment.newInstance(paramString);
    this.mListener.onFragmentInteraction(this.mSectionNumber, BaseFragment.OnFragmentInteractionListener.InteractionAction.REPLACE_FRAGMENT, paramString);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    ((BaseActivity)paramActivity).setActionBarTitle(getString(2131099978));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null) {
      this.mSectionNumber = getArguments().getInt("arg_section_number");
    }
    this.mApiRequests = new ArrayList();
    this.mApiRequests.add("Accounts.ChangePasswordRequest");
    this.mApiRequests.add("Accounts.DeleteAccountRequest");
    this.mApiRequests.add("Accounts.LoginRequest");
    this.mApiRequests.add("Accounts.LogoutRequest");
    this.mApiRequests.add("Accounts.RegistrationRequest");
    this.mApiRequests.add("Accounts.ResetPasswordRequest");
    this.mApiRequests.add("Accounts.RetreivalRequest");
    this.mApiRequests.add("Accounts.UpdateAccountRequest");
    this.mApiRequests.add("Cameras.AddCameraRequest");
    this.mApiRequests.add("Cameras.DeleteCameraRequest");
    this.mApiRequests.add("Cameras.DisableCameraAlertsRequest");
    this.mApiRequests.add("Cameras.DisableLiveViewRequest");
    this.mApiRequests.add("Cameras.EnableCameraAlertsRequest");
    this.mApiRequests.add("Cameras.EnableLiveViewRequest");
    this.mApiRequests.add("Cameras.GetAllCamerasStatusRequest");
    this.mApiRequests.add("Cameras.GetCameraInfoRequest");
    this.mApiRequests.add("Cameras.GetCameraVideoRequest");
    this.mApiRequests.add("Cameras.GetCameraVideosCountRequest_v2");
    this.mApiRequests.add("Cameras.GetCameraVideosRequest_v2");
    this.mApiRequests.add("Cameras.GetCameraVideosUnwatchedRequest");
    this.mApiRequests.add("Cameras.GetSingleCameraStatusRequest");
    this.mApiRequests.add("Cameras.RefreshCameraRequest");
    this.mApiRequests.add("Cameras.TakeSnapshotRequest");
    this.mApiRequests.add("Cameras.UpdateCameraRequest");
    this.mApiRequests.add("Cameras.WakeUpCameraRequest");
    this.mApiRequests.add("Clients.AddClientRequest");
    this.mApiRequests.add("Clients.DeleteClientRequest");
    this.mApiRequests.add("Clients.GetAccountClientsRequest");
    this.mApiRequests.add("Clients.GetUserClientsRequest");
    this.mApiRequests.add("Clients.UpdateClientRequest");
    this.mApiRequests.add("Command.CommandRequest");
    this.mApiRequests.add("Events.GetAllEventsRequest");
    this.mApiRequests.add("Events.GetEventsForSirenRequest");
    this.mApiRequests.add("Events.GetNetworkEventsRequest");
    this.mApiRequests.add("Health.CheckPortsRequest");
    this.mApiRequests.add("Health.CheckPresetPortsRequest");
    this.mApiRequests.add("Homescreen.HomescreenRequest");
    this.mApiRequests.add("Networks.AddNetworkRequest");
    this.mApiRequests.add("Networks.ArmNetworkRequest");
    this.mApiRequests.add("Networks.DeleteNetworkRequest");
    this.mApiRequests.add("Networks.DisarmNetworkRequest");
    this.mApiRequests.add("Networks.GetCommandStatus");
    this.mApiRequests.add("Networks.GetLastCommandStatus");
    this.mApiRequests.add("Networks.GetNetworkInformationRequest");
    this.mApiRequests.add("Networks.GetNetworkVideosCountRequest_v2");
    this.mApiRequests.add("Networks.GetNetworkVideosRequest_v2");
    this.mApiRequests.add("Networks.GetNetworkVideosUnwatchedRequest");
    this.mApiRequests.add("Networks.GetVideosForCameraDates");
    this.mApiRequests.add("Networks.ListNetworksRequest");
    this.mApiRequests.add("Networks.MakeOnboardingCommandRequest");
    this.mApiRequests.add("Networks.UpdateNetworkRequest");
    this.mApiRequests.add("Onboarding.OnboardBlinkRequest");
    this.mApiRequests.add("Onboarding.OnboardingDoneRequest");
    this.mApiRequests.add("Onboarding.SetSSIDRequest");
    this.mApiRequests.add("SyncModules.AddSyncModuleRequest");
    this.mApiRequests.add("SyncModules.DeleteSyncModuleRequest");
    this.mApiRequests.add("SyncModules.GetAllSyncModulesRequest");
    this.mApiRequests.add("SyncModules.GetSyncModuleConfigRequest");
    this.mApiRequests.add("SyncModules.GetSyncModuleRequest");
    this.mApiRequests.add("SyncModules.MakeCommandForSyncModuleRequest");
    this.mApiRequests.add("SyncModules.ResetSyncModuleRequest");
    this.mApiRequests.add("SyncModules.UpdateSyncModule");
    this.mApiRequests.add("Videos.GetVideoRequest");
    this.mApiRequests.add("Videos.GetVideosCountRequest_v2");
    this.mApiRequests.add("Videos.GetVideosRequest_v2");
    this.mApiRequests.add("Videos.GetVideoUnwatchedRequest");
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903094, paramViewGroup, false);
    paramViewGroup = (ListView)paramLayoutInflater.findViewById(16908298);
    paramViewGroup.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        APITestFragment.this.launchRequestForm((String)APITestFragment.this.mApiRequests.get(paramAnonymousInt));
      }
    });
    paramViewGroup.setAdapter(new TestArrayAdapter(getActivity(), 17367043, 16908308, this.mApiRequests));
    return paramLayoutInflater;
  }
  
  static class TestArrayAdapter
    extends ArrayAdapter
  {
    public TestArrayAdapter(Context paramContext, int paramInt1, int paramInt2, List paramList)
    {
      super(paramInt1, paramInt2, paramList);
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      return super.getView(paramInt, paramView, paramViewGroup);
    }
  }
}


/* Location:              /home/hectorc/Android/Apktool/blink-home-monitor-for-android-1-1-20-apkplz.com.jar!/com/immediasemi/blink/fragments/APITestFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */