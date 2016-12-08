package com.emple.dddq;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends Activity {

	private ListView nowListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_view);
		
		/*
		 * 1．ListVeiw 用来展示列表的View。
		   2．适配器 用来把数据映射到ListView上的中介。
		   3．数据    具体的将被映射的字符串，图片，或者基本组件。
		   
		   根据列表的适配器类型，列表分为三种，ArrayAdapter，SimpleAdapter和SimpleCursorAdapter
		   
		   其中以ArrayAdapter最为简单，只能展示一行字。SimpleAdapter有最好的扩充性，可以自定义出各种效果。
		   SimpleCursorAdapter可以认为是SimpleAdapter对数据库的简单结合，
		   可以方面的把数据库的内容以列表的形式展示出来。
		 * */
		
		
		
		nowListView = new ListView(this);
		nowListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getdata()));

        setContentView(nowListView);	
        
        /*
         * ArrayAdapter(Context context, int textViewResourceId, List<T> objects)来装配数据，
         * 要装配这些数据就需要一个连接ListView视图对象和数组数据的适配器来两者的适配工作，
         * ArrayAdapter的构造需要三个参数，依次为this,布局文件（注意这里的布局文件描述的是列表的每一行的布局，
         * android.R.layout.simple_list_item_1是系统定义好的布局文件只显示一行文字，
         * 数据源(一个List集合)。同时用setAdapter（）完成适配的最后工作。
         * */
	}
	
	private List<String>getdata(){
		
		List<String>data = new ArrayList<String>();
		data.add("测试数据1");
		data.add("测试数据2");
		data.add("测试数据3");
		data.add("测试数据4");
		
		return data;		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
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
