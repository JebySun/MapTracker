package com.jebysun.android.maptracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_main);
		
		Button btnTrack = (Button)this.findViewById(R.id.btn_track);
		Button btnRecords = (Button)this.findViewById(R.id.btn_record_list);
		Button btnMapView = (Button)this.findViewById(R.id.btn_map_view);
		Button btnTxtView = (Button)this.findViewById(R.id.btn_txt_view);
		
		MainMenuClickListener mainListener = new MainMenuClickListener();
		
		btnTrack.setOnClickListener(mainListener);
		btnRecords.setOnClickListener(mainListener);
		btnMapView.setOnClickListener(mainListener);
		btnTxtView.setOnClickListener(mainListener);
		
		
	}
	
	class MainMenuClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getId()==R.id.btn_track) {
				startActivity(new Intent(MainActivity.this, TrackActivity.class));
			} else if (v.getId()==R.id.btn_record_list) {
				startActivity(new Intent(MainActivity.this, RecordListActivity.class));
			} else if (v.getId()==R.id.btn_map_view) {
				startActivity(new Intent(MainActivity.this, MapActivity.class));
			} else if (v.getId()==R.id.btn_txt_view) {
				startActivity(new Intent(MainActivity.this, TxtActivity.class));
			}
		}
		
	}
	
	
	

}
