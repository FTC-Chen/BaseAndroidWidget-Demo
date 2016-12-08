package com.emple.dddq;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SimpleCurActivity extends Activity {

	private ListView curListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
		 * 简单的说就是方便把从游标得到的数据进行列表显示，并可以把指定的列映射到对应的TextView中。
		 * 
		 * 下面的程序是从电话簿中把联系人显示到类表中。先在通讯录中添加一个联系人作为数据库的数据。
		 * 然后获得一个指向数据库的Cursor并且定义一个布局文件（当然也可以使用系统自带的）。
		 * */
		curListView = new ListView(this);
		Cursor cursor = getContentResolver().query(People.CONTENT_URI, null, null, null, null);
		startManagingCursor(cursor);
		
		ListAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, 
											cursor, 
											new String[]{People.NAME}, 
											new int[]{android.R.id.text1});
		
		curListView.setAdapter(listAdapter);
        
		setContentView(curListView);
	}
	
	/*
	 * 在你理解和使用 Android Cursor 的时候你必须先知道关于 Cursor 的几件事情： 
	   Cursor 是每行的集合。 
	   使用 moveToFirst() 定位第一行。
	   你必须知道每一列的名称。 
	   你必须知道每一列的数据类型。 
	   Cursor 是一个随机的数据源。 
	   所有的数据都是通过下标取得。 Cursor 位于 android.database.Cursor类，可见出它的设计是基于数据库服务产生的。 
	   在Android 查询数据是通过Cursor 类来实现的。
	   当我们使用 SQLiteDatabase.query()方法时，就会得到Cursor对象， Cursor所指向的就是每一条数据。 
	 * */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simple_cur, menu);
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
