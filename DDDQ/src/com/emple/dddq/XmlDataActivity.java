package com.emple.dddq;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class XmlDataActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xml_data);
		
		//界面中的ListView是View，View在layout目录下通过xml文件格式生成，用getViewById()获取
        ListView list = (ListView) findViewById(R.id.listView1);  
        //控制数据怎样在ListView中显示是Controller
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, 
        														getData());
        
        list.setAdapter(adapter);
        
	}
	
	private List<String> getData() {
		
		List<String> data = new ArrayList<String>();
		
		Resources res = getResources();
		//取xml文件格式的字符数组
		String[] good = res.getStringArray(R.array.good);
		
		for (int i = 0; i < good.length; i++) {		
			
			data.add(good[i]);
		}
		return data;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xml_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
