package com.immediasemi.blink.models;

public class DeviceStatus
  extends BlinkData
{
  private static final long serialVersionUID = -666428303138324740L;
  protected boolean ac_power;
  protected boolean battery_alert_status;
  protected int battery_level;
  protected int camera_id;
  protected String camera_version;
  protected String cc3100_version;
  protected String created_at;
  protected String deleted_at;
  protected int dev_1;
  protected int dev_2;
  protected int dev_3;
  protected int dhcp_failure_count;
  protected String error_codes;
  protected int id;
  protected String ip_address;
  protected String ipv;
  protected String isi108_version;
  protected int lfr_108_wakeups;
  protected int lfr_strength;
  protected int lfr_tb_wakeups;
  protected int light_sensor_ch0;
  protected int light_sensor_ch1;
  protected boolean light_sensor_data_new;
  protected boolean light_sensor_data_valid;
  protected String mac;
  protected String msp430_version;
  protected String name;
  protected int siren_id;
  protected String siren_version;
  protected String sm_version;
  protected int socket_failure_count;
  protected int sync_module_id;
  protected boolean temp_alert_status;
  protected int temperature;
  protected String thumbnail;
  protected int time_108_boot;
  protected int time_dhcp_lease;
  protected int time_dns_resolve;
  protected int time_first_video;
  protected int time_wlan_connect;
  protected int total_108_wakeups;
  protected int total_tb_wakeups;
  protected String updated_at;
  protected int wifi_connect_failure_count;
  protected int wifi_strength;
  
  public int getBattery_level()
  {
    return this.battery_level;
  }
  
  public int getCamera_id()
  {
    return this.camera_id;
  }
  
  public String getCamera_version()
  {
    return this.camera_version;
  }
  
  public String getCc3100_version()
  {
    return this.cc3100_version;
  }
  
  public String getCreated_at()
  {
    return this.created_at;
  }
  
  public String getDeleted_at()
  {
    return this.deleted_at;
  }
  
  public int getDev_1()
  {
    return this.dev_1;
  }
  
  public int getDev_2()
  {
    return this.dev_2;
  }
  
  public int getDev_3()
  {
    return this.dev_3;
  }
  
  public int getDhcp_failure_count()
  {
    return this.dhcp_failure_count;
  }
  
  public String getError_codes()
  {
    return this.error_codes;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public String getIp_address()
  {
    return this.ip_address;
  }
  
  public String getIpv()
  {
    return this.ipv;
  }
  
  public String getIsi108_version()
  {
    return this.isi108_version;
  }
  
  public int getLfr_108_wakeups()
  {
    return this.lfr_108_wakeups;
  }
  
  public int getLfr_strength()
  {
    return this.lfr_strength;
  }
  
  public int getLfr_tb_wakeups()
  {
    return this.lfr_tb_wakeups;
  }
  
  public int getLight_sensor_ch0()
  {
    return this.light_sensor_ch0;
  }
  
  public int getLight_sensor_ch1()
  {
    return this.light_sensor_ch1;
  }
  
  public String getMac()
  {
    return this.mac;
  }
  
  public String getMsp430_version()
  {
    return this.msp430_version;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getSiren_id()
  {
    return this.siren_id;
  }
  
  public String getSiren_version()
  {
    return this.siren_version;
  }
  
  public String getSm_version()
  {
    return this.sm_version;
  }
  
  public int getSocket_failure_count()
  {
    return this.socket_failure_count;
  }
  
  public int getSync_module_id()
  {
    return this.sync_module_id;
  }
  
  public int getTemperature()
  {
    return this.temperature;
  }
  
  public String getThumbnail()
  {
    return this.thumbnail;
  }
  
  public int getTime_108_boot()
  {
    return this.time_108_boot;
  }
  
  public int getTime_dhcp_lease()
  {
    return this.time_dhcp_lease;
  }
  
  public int getTime_dns_resolve()
  {
    return this.time_dns_resolve;
  }
  
  public int getTime_first_video()
  {
    return this.time_first_video;
  }
  
  public int getTime_wlan_connect()
  {
    return this.time_wlan_connect;
  }
  
  public int getTotal_108_wakeups()
  {
    return this.total_108_wakeups;
  }
  
  public int getTotal_tb_wakeups()
  {
    return this.total_tb_wakeups;
  }
  
  public String getUpdated_at()
  {
    return this.updated_at;
  }
  
  public int getWifi_connect_failure_count()
  {
    return this.wifi_connect_failure_count;
  }
  
  public int getWifi_strength()
  {
    return this.wifi_strength;
  }
  
  public boolean isAc_power()
  {
    return this.ac_power;
  }
  
  public boolean isBattery_alert_status()
  {
    return this.battery_alert_status;
  }
  
  public boolean isLight_sensor_data_new()
  {
    return this.light_sensor_data_new;
  }
  
  public boolean isLight_sensor_data_valid()
  {
    return this.light_sensor_data_valid;
  }
  
  public boolean isTemp_alert_status()
  {
    return this.temp_alert_status;
  }
  
  public void setAc_power(boolean paramBoolean)
  {
    this.ac_power = paramBoolean;
  }
  
  public void setBattery_alert_status(boolean paramBoolean)
  {
    this.battery_alert_status = paramBoolean;
  }
  
  public void setBattery_level(int paramInt)
  {
    this.battery_level = paramInt;
  }
  
  public void setCamera_id(int paramInt)
  {
    this.camera_id = paramInt;
  }
  
  public void setCamera_version(String paramString)
  {
    this.camera_version = paramString;
  }
  
  public void setCc3100_version(String paramString)
  {
    this.cc3100_version = paramString;
  }
  
  public void setCreated_at(String paramString)
  {
    this.created_at = paramString;
  }
  
  public void setDeleted_at(String paramString)
  {
    this.deleted_at = paramString;
  }
  
  public void setDev_1(int paramInt)
  {
    this.dev_1 = paramInt;
  }
  
  public void setDev_2(int paramInt)
  {
    this.dev_2 = paramInt;
  }
  
  public void setDev_3(int paramInt)
  {
    this.dev_3 = paramInt;
  }
  
  public void setDhcp_failure_count(int paramInt)
  {
    this.dhcp_failure_count = paramInt;
  }
  
  public void setError_codes(String paramString)
  {
    this.error_codes = paramString;
  }
  
  public void setId(int paramInt)
  {
    this.id = paramInt;
  }
  
  public void setIp_address(String paramString)
  {
    this.ip_address = paramString;
  }
  
  public void setIpv(String paramString)
  {
    this.ipv = paramString;
  }
  
  public void setIsi108_version(String paramString)
  {
    this.isi108_version = paramString;
  }
  
  public void setLfr_108_wakeups(int paramInt)
  {
    this.lfr_108_wakeups = paramInt;
  }
  
  public void setLfr_strength(int paramInt)
  {
    this.lfr_strength = paramInt;
  }
  
  public void setLfr_tb_wakeups(int paramInt)
  {
    this.lfr_tb_wakeups = paramInt;
  }
  
  public void setLight_sensor_ch0(int paramInt)
  {
    this.light_sensor_ch0 = paramInt;
  }
  
  public void setLight_sensor_ch1(int paramInt)
  {
    this.light_sensor_ch1 = paramInt;
  }
  
  public void setLight_sensor_data_new(boolean paramBoolean)
  {
    this.light_sensor_data_new = paramBoolean;
  }
  
  public void setLight_sensor_data_valid(boolean paramBoolean)
  {
    this.light_sensor_data_valid = paramBoolean;
  }
  
  public void setMac(String paramString)
  {
    this.mac = paramString;
  }
  
  public void setMsp430_version(String paramString)
  {
    this.msp430_version = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setSiren_id(int paramInt)
  {
    this.siren_id = paramInt;
  }
  
  public void setSiren_version(String paramString)
  {
    this.siren_version = paramString;
  }
  
  public void setSm_version(String paramString)
  {
    this.sm_version = paramString;
  }
  
  public void setSocket_failure_count(int paramInt)
  {
    this.socket_failure_count = paramInt;
  }
  
  public void setSync_module_id(int paramInt)
  {
    this.sync_module_id = paramInt;
  }
  
  public void setTemp_alert_status(boolean paramBoolean)
  {
    this.temp_alert_status = paramBoolean;
  }
  
  public void setTemperature(int paramInt)
  {
    this.temperature = paramInt;
  }
  
  public void setThumbnail(String paramString)
  {
    this.thumbnail = paramString;
  }
  
  public void setTime_108_boot(int paramInt)
  {
    this.time_108_boot = paramInt;
  }
  
  public void setTime_dhcp_lease(int paramInt)
  {
    this.time_dhcp_lease = paramInt;
  }
  
  public void setTime_dns_resolve(int paramInt)
  {
    this.time_dns_resolve = paramInt;
  }
  
  public void setTime_first_video(int paramInt)
  {
    this.time_first_video = paramInt;
  }
  
  public void setTime_wlan_connect(int paramInt)
  {
    this.time_wlan_connect = paramInt;
  }
  
  public void setTotal_108_wakeups(int paramInt)
  {
    this.total_108_wakeups = paramInt;
  }
  
  public void setTotal_tb_wakeups(int paramInt)
  {
    this.total_tb_wakeups = paramInt;
  }
  
  public void setUpdated_at(String paramString)
  {
    this.updated_at = paramString;
  }
  
  public void setWifi_connect_failure_count(int paramInt)
  {
    this.wifi_connect_failure_count = paramInt;
  }
  
  public void setWifi_strength(int paramInt)
  {
    this.wifi_strength = paramInt;
  }
}


/* Location:              /home/zips/Android/Apktool/Blink4Home/Blink-136-dex2jar.jar!/com/immediasemi/blink/models/DeviceStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */