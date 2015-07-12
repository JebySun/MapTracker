package com.jebysun.android.maptracker;

import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TrackActivity extends Activity {

	private MapView mMapView;
	private BaiduMap mBaiduMap;
	// 定位相关
	private LocationClient mLocClient;
	private MyLocationListenner locListener;
	
	private LatLng locationPoint;
	
	private ImageButton btnZoomIn;
	private ImageButton btnZoomOut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext());
        
		setContentView(R.layout.activity_track);
		
		mMapView = (MapView) findViewById(R.id.bmapView);  
		mBaiduMap = mMapView.getMap();
		mMapView.showZoomControls(false);
		mBaiduMap.getUiSettings().setOverlookingGesturesEnabled(false);
		
		btnZoomIn = (ImageButton)this.findViewById(R.id.btn_zoomin);
		btnZoomOut = (ImageButton)this.findViewById(R.id.btn_zoomout);
		btnZoomIn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				zoomMap("zoomin");
			}
		});
		btnZoomOut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				zoomMap("zoomout");
			}
		});
		
		locationInit();
	}
	
	/**
	 * 地图缩放
	 * @author JebySun
	 * @date 2015年1月24日 下午6:06:56
	 * @email jebysun@126.com
	 * @param zoom
	 */
	public void zoomMap(String zoom) {
		float zoomLevel = mBaiduMap.getMapStatus().zoom;
		if (zoom.equals("zoomin")) {
			zoomLevel++;
		} else {
			zoomLevel--;
		}
	    MapStatus mMapStatus = new MapStatus.Builder()
	    .zoom(zoomLevel)
	    .build();
	    MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
	    mBaiduMap.animateMapStatus(mMapStatusUpdate);
	}
	
	public void locationInit() {
		// 定位初始化
		mLocClient = new LocationClient(this);
		locListener = new MyLocationListenner();
		mLocClient.registerLocationListener(locListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
//		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
//		mLocClient.requestLocation();
	}
	
	public void addLocationOverylay() {
		//定义Maker坐标点  
		//构建Marker图标  
		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_current_location);  
		//构建MarkerOption，用于在地图上添加Marker  
		OverlayOptions option = new MarkerOptions()  
		    .position(locationPoint)  
		    .icon(bitmap);  
		//在地图上添加Marker，并显示  
		mBaiduMap.addOverlay(option);
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
			if (location == null || mMapView == null) {
				return;
			}
			mLocClient.stop();
			locationPoint = new LatLng(location.getLatitude(), location.getLongitude());
			
			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(locationPoint);
			mBaiduMap.animateMapStatus(u);
			
			addLocationOverylay();
		}
		
	}

}
