package com.jebysun.android.maptracker;

import java.util.ArrayList;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private MapView mMapView;
	private BaiduMap mBaiduMap;
	// 定位相关
	private LocationClient mLocClient;
	private MyLocationListenner locListener;
	
	private LatLng locationPoint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext());  
        
		setContentView(R.layout.activity_main);
		
		mMapView = (MapView) findViewById(R.id.bmapView);  
		mBaiduMap = mMapView.getMap();
		mMapView.showZoomControls(false);
		mBaiduMap.getUiSettings().setOverlookingGesturesEnabled(false);
		
		locationInit();
		
		addLocationOverylay();
		
		drawLine();
	}
	
	public void locationInit() {
		// 定位初始化
		mLocClient = new LocationClient(this);
		locListener = new MyLocationListenner();
		mLocClient.registerLocationListener(locListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}
	
	public void addLocationOverylay() {
		//定义Maker坐标点  
		LatLng point = new LatLng(39.963175, 116.400244);  
		//构建Marker图标  
		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_location);  
		//构建MarkerOption，用于在地图上添加Marker  
		OverlayOptions option = new MarkerOptions()  
		    .position(point)  
		    .icon(bitmap);  
		//在地图上添加Marker，并显示  
		mBaiduMap.addOverlay(option);
	}
	
	public void drawLine() {
		// 定义点
		LatLng pt1 = new LatLng(39.93923, 116.357428);
		LatLng pt2 = new LatLng(39.91923, 116.327428);
		LatLng pt3 = new LatLng(39.89923, 116.347428);
		LatLng pt4 = new LatLng(39.89923, 116.367428);
		LatLng pt5 = new LatLng(39.91923, 116.387428);
		 
		List<LatLng> points = new ArrayList<LatLng>();
		points.add(pt1);//点元素
		points.add(pt2);//点元素
		points.add(pt3);//点元素
		points.add(pt4);//点元素
		points.add(pt5);//点元素
		//构造对象
		OverlayOptions ooPolyline = new PolylineOptions().color(0xFF336600).points(points);
		//添加到地图
		mBaiduMap.addOverlay(ooPolyline);
	}
	
	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mBaiduMap.setMyLocationEnabled(false);
		mLocClient.stop();
		mMapView.onDestroy();
		super.onDestroy();
	}
	
	
	
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不再处理新接收的位置
			if (location == null || mMapView == null)
				return;
			locationPoint = new LatLng(location.getLatitude(), location.getLongitude());
			
//			MyLocationData locData = new MyLocationData.Builder()
//					.accuracy(location.getRadius())
//					// 此处设置开发者获取到的方向信息，顺时针0-360
//					.direction(0)
//					.latitude(location.getLatitude())
//					.longitude(location.getLongitude())
//					.build();
//			mBaiduMap.setMyLocationData(locData);
			
			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(locationPoint);
			mBaiduMap.animateMapStatus(u);
		}
		
	}

}
