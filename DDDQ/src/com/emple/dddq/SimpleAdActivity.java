package com.emple.dddq;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SimpleAdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_ad);
		//绑定Layout里面的ListView  
        ListView list = (ListView) findViewById(R.id.ListView01);  
        //list.setLayoutParams(new Gallery.LayoutParams(50,50));
        
        //生成动态数组，加入数据  
        ArrayList<HashMap<String, Object>>listItem = new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<10;i++){
        	
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("ItemImage", R.drawable.img01);
        	map.put("ItemTitle", "level"+i);
        	map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
        	listItem.add(map);
        }
        
      //生成适配器的Item和动态数组对应的元素  
        SimpleAdapter listAdapter = new SimpleAdapter(this, listItem, 
        							R.layout.simpleadlist, 
        							new String[]{"ItemImage","ItemTitle","ItemText"}, 
        							new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText});
        
        //添加并且显示  
        list.setAdapter(listAdapter);  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simple_ad, menu);
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
