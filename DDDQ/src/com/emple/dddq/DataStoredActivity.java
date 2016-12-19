package com.emple.dddq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DataStoredActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_stored);
		
		/*
		Android提供的数据持久化存储方式有以下几种。
		1.Shared Preferences：以Key-Value形式存储数据
		2.Internal Storage：数据文件存储在内存卡
		3.External Storage：数据存储在外部设备，如SD卡等
		4.SQLite Databases：SQLite存储方式
		5.Network Connection：通过WebService等网络通信方式存储数据。
		 * */
		
		//使用SharedPreferences存储数据  button1
		Button sharePresButton = (Button)findViewById(R.id.button1);
		
		sharePresButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(DataStoredActivity.this, SharePersActivity.class);
			startActivity(intent);
			}
		});
		
		//文件存储数据        Button01
		Button fielStoredButton = (Button)findViewById(R.id.Button01);
		
		fielStoredButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(DataStoredActivity.this, FileStoredActivity.class);
			startActivity(intent);
			}
		});
		
		//文件存储2:存储SD卡上的数据   Button02
		Button fielSdCardButton = (Button)findViewById(R.id.Button02);
		
		fielSdCardButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(DataStoredActivity.this, FileSdCardActivity.class);
			startActivity(intent);
			}
		});
		
		//SQLite数据库存储数据        Button03
		Button sqliteButton = (Button)findViewById(R.id.Button03);
		
		sqliteButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(DataStoredActivity.this, SqliteActivity.class);
			startActivity(intent);
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_stored, menu);
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
