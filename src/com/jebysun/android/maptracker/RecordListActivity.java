package com.jebysun.android.maptracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class RecordListActivity extends Activity implements OnItemClickListener {
	
	private List<Map<String, Object>> listItemData;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_record_list);
		listView = (ListView)this.findViewById(R.id.listview);
		
		listItemData = getListItemData();
		
		SimpleAdapter adapter = new SimpleAdapter(
				this,
				listItemData,
				R.layout.list_item_record_list,
				new String[]{"year", "month", "week", "title", "mileage", "timetake", "speed", "time"},
				new int[]{R.id.tv_year, R.id.tv_month, R.id.tv_week, R.id.title, R.id.tv_mileage, R.id.tv_timetake, R.id.tv_speed, R.id.tv_time});
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(this);
		 
	}
	
	
	private List<Map<String, Object>> getListItemData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
				
		map = new HashMap<String, Object>();
    	map.put("year", "2015");
    	map.put("month", "02");
    	map.put("week", "星期一");
    	map.put("title", "第一次行记");
    	map.put("mileage", "12.5km");
    	map.put("timetake", "02:25:56");
    	map.put("speed", "3.6km/h");
    	map.put("time", "02-23 10:55:15");
    	list.add(map);
    	
    	map = new HashMap<String, Object>();
    	map.put("year", "2015");
    	map.put("month", "02");
    	map.put("week", "星期二");
    	map.put("title", "测试轨迹数据");
    	map.put("mileage", "12.5km");
    	map.put("timetake", "02:25:56");
    	map.put("speed", "3.6km/h");
    	map.put("time", "02-23 10:55:15");
    	list.add(map);
    	
    	map = new HashMap<String, Object>();
    	map.put("year", "2015");
    	map.put("month", "02");
    	map.put("week", "星期四");
    	map.put("title", "第一次行记");
    	map.put("mileage", "12.5km");
    	map.put("timetake", "02:25:56");
    	map.put("speed", "3.6km/h");
    	map.put("time", "02-23 10:55:15");
    	list.add(map);
    	
    	map = new HashMap<String, Object>();
    	map.put("year", "2015");
    	map.put("month", "02");
    	map.put("week", "星期无");
    	map.put("title", "星期日逛街买东西");
    	map.put("mileage", "12.5km");
    	map.put("timetake", "02:25:56");
    	map.put("speed", "3.6km/h");
    	map.put("time", "02-23 10:55:15");
    	list.add(map);
    	
    	map = new HashMap<String, Object>();
    	map.put("year", "2015");
    	map.put("month", "02");
    	map.put("week", "星期日");
    	map.put("title", "环太湖游记");
    	map.put("mileage", "12.5km");
    	map.put("timetake", "02:25:56");
    	map.put("speed", "3.6km/h");
    	map.put("time", "02-23 10:55:15");
    	list.add(map);
    	
        return list;
    }
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, "position="+position, 0).show();
	}
	
	
	

}
