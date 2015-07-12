package com.jebysun.android.maptracker.util;

import java.io.File;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * 工具类
 * @author JebySun
 *
 */
public class AndroidUtil {
	
	
	/** 
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的 
     * @param context 
     * @return true 表示开启 
     */  
    public boolean isGPSOpen(Context context) {  
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);  
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）  
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);  
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）  
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);  
        if (gps || network) {  
            return true;  
        }  
        return false;  
    } 
    
   /** 判断服务是否运行.
    * @param context
    * @param className 判断的服务名字
    * @return true 在运行; false不在运行
    */
   public static boolean isServiceRunning(Context mContext, String fullClassName) {
       boolean isRunning = false;
       ActivityManager activityManager = (ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE); 
       List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(100);
       if (serviceList.size()==0) {
           return false;
       }
       for (int i=0; i<serviceList.size(); i++) {
           if (serviceList.get(i).service.getClassName().equals(fullClassName) == true) {
               isRunning = true;
               break;
           }
       }
       return isRunning;
   }
   
   
   /**
    * 获取手机MIEI信息
    * @param context
    * @return
    */
   public static String getPhoneMIEI(Context context) {
	   return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();	   
   }
   
	/**
	 * 普通Json数据解析
	 * @param strResult
	 */
	public static String getValueFromJSON(String strJson, String key) {
		String propertyValue = null;
		try {
			JSONObject jsonObj = new JSONObject(strJson);
			propertyValue = jsonObj.getString(key);
		} catch (JSONException e) {
			return null;
		}
		return propertyValue;
	}
	
    /**
     * 设置程序图标是否可见
     */
    public static void setAppVisible(Context context, ComponentName componentName, boolean isVisible) {
    	int appState = 0;
    	if(isVisible) {
    		appState = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
    	} else {
    		appState = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    	}
		PackageManager p = context.getPackageManager();
		p.setComponentEnabledSetting(componentName, appState, PackageManager.DONT_KILL_APP);
    }

    
    /**
     * 从Manifest文件中获取配置信息
     * @param context
     * @return key
     */
    public static String getAppPropertyValue(Context context, String key) {
        Bundle metaData = null;
        String value = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != appInfo) {
            	metaData = appInfo.metaData;
            }
            if (null != metaData) {
                value = metaData.getString(key);
            }
        } catch (NameNotFoundException e) {
        	
        }
        return value;
    }
    
    
    /** 
     * 获取已安装app版本代码
     * @param context 
     * @return 
     */  
    public static int getAppVersionCode(Context context) {  
    	int versionCode = 0;
    	try {  
    		PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    		versionCode = packageInfo.versionCode;
    	} catch (NameNotFoundException e) {  
    		e.printStackTrace();
    	}
    	return versionCode;
    } 
    /** 
     * 获取已安装版本名称
     * @param context 
     * @return 
     */  
    public static String getAppVersionName(Context context) {  
    	String versionName = "";
    	try {  
    		PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    		versionName = packageInfo.versionName;
    	} catch (NameNotFoundException e) {  
    		e.printStackTrace();
    	}
    	return versionName;
    }
    
    /** 
     * 安装apk文件 
     */  
    public static void installApk(Context context, String filePath) { 
    	File apkFile = new File(filePath);
        Intent intent = new Intent();  
        intent.setAction(Intent.ACTION_VIEW); 
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");  
        context.startActivity(intent);  
    }  
    
    /**
     * 将浮点数转换为保留指定小数点位数的字符串
     * @param value
     * @param decimal
     * @return
     */
    public static String formatFloat2String(float value, int decimal) {
    	String strValue = Float.toString(value);
    	int dotIndex = strValue.indexOf('.');
    	if (dotIndex != -1 && (dotIndex+decimal<strValue.length()-1)) {
    		strValue = strValue.substring(0, dotIndex+decimal+1);
    	}
    	return strValue;
    }
    
    
}




