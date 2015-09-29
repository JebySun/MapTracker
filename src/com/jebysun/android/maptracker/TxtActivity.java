package com.jebysun.android.maptracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TxtActivity extends Activity implements OnItemClickListener {
	
	private List<Map<String, Object>> listItemData;
	private ListView listView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_txt);
		
		listView = (ListView)this.findViewById(R.id.listview);
		
		listItemData = getListItemData();
		
		SimpleAdapter adapter = new SimpleAdapter(
				this,
				listItemData,
				R.layout.list_item_record_detail,
				new String[]{"index", "address", "timestap", "accuracy"},
				new int[]{R.id.tv_index, R.id.tv_address, R.id.tv_timestap, R.id.tv_accuracy});
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(this);
	}

	private List<Map<String, Object>> getListItemData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
				
		map = new HashMap<String, Object>();
    	map.put("index", "1");
    	map.put("address", "江苏省苏州市吴中区中山西路18号");
    	map.put("timestap", "08:15:55");
    	map.put("accuracy", "精确到20米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "2");
    	map.put("address", "江苏省苏州市吴中区香溪西路18号");
    	map.put("timestap", "08:16:55");
    	map.put("accuracy", "精确到60米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "3");
    	map.put("address", "江苏省苏州市吴中区香溪西路13-11号");
    	map.put("timestap", "08:17:55");
    	map.put("accuracy", "精确到120米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "4");
    	map.put("address", "江苏省苏州市吴中区香溪西路14-10号");
    	map.put("timestap", "08:18:55");
    	map.put("accuracy", "精确到40米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "5");
    	map.put("address", "江苏省苏州市吴中区香溪西路5号");
    	map.put("timestap", "08:19:55");
    	map.put("accuracy", "精确到400米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "6");
    	map.put("address", "江苏省苏州市吴中区香溪路1号");
    	map.put("timestap", "08:20:55");
    	map.put("accuracy", "精确到1200米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "7");
    	map.put("address", "江苏省苏州市吴中区中市街1号");
    	map.put("timestap", "08:21:55");
    	map.put("accuracy", "精确到100米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "8");
    	map.put("address", "江苏省苏州市吴中区翠坊南街40号");
    	map.put("timestap", "08:22:55");
    	map.put("accuracy", "精确到320米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "9");
    	map.put("address", "江苏省苏州市吴中区姑苏路18-9号");
    	map.put("timestap", "08:23:55");
    	map.put("accuracy", "精确到440米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "10");
    	map.put("address", "江苏省苏州市吴中区姑苏路42-1号");
    	map.put("timestap", "08:24:55");
    	map.put("accuracy", "精确到240米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "11");
    	map.put("address", "江苏省苏州市吴中区姑苏路");
    	map.put("timestap", "08:25:55");
    	map.put("accuracy", "精确到500米");
    	list.add(map);
    	map = new HashMap<String, Object>();
    	map.put("index", "12");
    	map.put("address", "江苏省苏州市吴中区X207(金枫南路)");
    	map.put("timestap", "08:26:55");
    	map.put("accuracy", "精确到50米");
    	list.add(map);

    	
        return list;
    }
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, "position="+position, 0).show();
	}
	

}


