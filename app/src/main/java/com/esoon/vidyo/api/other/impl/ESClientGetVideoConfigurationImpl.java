package com.esoon.vidyo.api.other.impl;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;


import com.esoon.vidyo.api.other.ESClientGetVideoConfiguration;

/**
 * 获取手机配置信息
 */

public class ESClientGetVideoConfigurationImpl implements ESClientGetVideoConfiguration{
    StringBuilder phoneInfo;
    @Override
    public StringBuilder GetVideoConfiguration(Activity    activity) {
        StringBuilder phoneInfo = new StringBuilder();
        phoneInfo.append("Product: " + android.os.Build.PRODUCT + System.getProperty("line.separator"));
        phoneInfo.append( "CPU_ABI: " + android.os.Build.CPU_ABI + System.getProperty("line.separator"));
        phoneInfo.append( "TAGS: " + android.os.Build.TAGS + System.getProperty("line.separator"));
        phoneInfo.append( "VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE + System.getProperty("line.separator"));
        phoneInfo.append( "MODEL: " + android.os.Build.MODEL + System.getProperty("line.separator"));
        phoneInfo.append( "SDK: " + android.os.Build.VERSION.SDK + System.getProperty("line.separator"));
        phoneInfo.append( "VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE + System.getProperty("line.separator"));
        phoneInfo.append( "DEVICE: " + android.os.Build.DEVICE + System.getProperty("line.separator"));
        phoneInfo.append( "DISPLAY: " + android.os.Build.DISPLAY + System.getProperty("line.separator"));
        phoneInfo.append( "BRAND: " + android.os.Build.BRAND + System.getProperty("line.separator"));
        phoneInfo.append( "BOARD: " + android.os.Build.BOARD + System.getProperty("line.separator"));
        phoneInfo.append( "FINGERPRINT: " + android.os.Build.FINGERPRINT + System.getProperty("line.separator"));
        phoneInfo.append( "ID: " + android.os.Build.ID + System.getProperty("line.separator"));
        phoneInfo.append( "MANUFACTURER: " + android.os.Build.MANUFACTURER + System.getProperty("line.separator"));
        phoneInfo.append( "USER: " + android.os.Build.USER + System.getProperty("line.separator"));
       TelephonyManager tm = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
        phoneInfo.append("DeviceId(IMEI) = " + tm.getDeviceId() + System.getProperty("line.separator"));
        phoneInfo.append("DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + System.getProperty("line.separator"));
        phoneInfo.append("Line1Number = " + tm.getLine1Number() + System.getProperty("line.separator"));
        phoneInfo.append("NetworkCountryIso = " + tm.getNetworkCountryIso() + System.getProperty("line.separator"));
        phoneInfo.append("NetworkOperator = " + tm.getNetworkOperator() + System.getProperty("line.separator"));
        phoneInfo.append("NetworkOperatorName = " + tm.getNetworkOperatorName() + System.getProperty("line.separator"));
        phoneInfo.append("NetworkType = " + tm.getNetworkType() + System.getProperty("line.separator"));
        phoneInfo.append("PhoneType = " + tm.getPhoneType() + System.getProperty("line.separator"));
        phoneInfo.append("SimCountryIso = " + tm.getSimCountryIso() + System.getProperty("line.separator"));
        phoneInfo.append("SimOperator = " + tm.getSimOperator() + System.getProperty("line.separator"));
        phoneInfo.append("SimOperatorName = " + tm.getSimOperatorName() + System.getProperty("line.separator"));
        phoneInfo.append("SimSerialNumber = " + tm.getSimSerialNumber() + System.getProperty("line.separator"));
        phoneInfo.append("SimState = " + tm.getSimState() + System.getProperty("line.separator"));
        phoneInfo.append("SubscriberId(IMSI) = " + tm.getSubscriberId() + System.getProperty("line.separator"));
        phoneInfo.append("VoiceMailNumber = " + tm.getVoiceMailNumber() + System.getProperty("line.separator"));







        return phoneInfo;
    }
}
