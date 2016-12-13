package com.emple.dddq;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.os.IInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.Window;


public class MainActivity extends Activity {

	private EditText editFirst;
	private EditText editSeco;
	private EditText editRes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	editFirst = (EditText)findViewById(R.id.editText1);
	editSeco = (EditText)findViewById(R.id.EditText2);
	editRes = (EditText)findViewById(R.id.EditTextRes);
	
	//计算button
	Button resButton = (Button)findViewById(R.id.button1);
	
	resButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String firNum = editFirst.getText().toString().trim();
			String seconNum = editSeco.getText().toString().trim();
			
			int a = Integer.parseInt(firNum);
			int b = Integer.parseInt(seconNum);
			
			int sum = a+b;
			editRes.setText(Integer.toString(sum));
		}
	});

	
	//跳转结果button
	Button skipButton = (Button)findViewById(R.id.buttonSkip);
	
	skipButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(); 
			intent.setClass(MainActivity.this, SecondActivity.class);
			
			//传值
			String resNum = editRes.getText().toString().trim();
			intent.putExtra("Res", resNum);
	
			startActivity(intent);
		}
	});
	
	//跳转回传值  
	Button backValueButton = (Button)findViewById(R.id.Button07);
	
	backValueButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(); 
			intent.setClass(MainActivity.this, BackValueActivity.class);
			
			//startActivity(intent);
			//使用startActivityForResult来启动
			startActivityForResult(intent, 0);
		}
	});


	//跳转imgViewbutton
		Button skipImgButton = (Button)findViewById(R.id.Button01);
		
		skipImgButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intent = new Intent(); 
				intent.setClass(MainActivity.this, ImgViewActivity.class);
				startActivity(intent);
			}
		});
	
	
	//跳转线性布局
		Button layoutButton = (Button)findViewById(R.id.Button02);
		
		layoutButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intent = new Intent(); 
				intent.setClass(MainActivity.this, LayoutActivity.class);
				startActivity(intent);
			}
		});	
	
	//跳转相对布局
		Button relativeButton = (Button)findViewById(R.id.Button03);
		
		relativeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intent = new Intent(); 
				intent.setClass(MainActivity.this, RelativeActivity.class);
				startActivity(intent);
			}
		});	
		
	//跳转单帧布局  
		Button frameButton = (Button)findViewById(R.id.Button04);
		
		frameButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intent = new Intent(); 
				intent.setClass(MainActivity.this, FrameActivity.class);
				startActivity(intent);
			}
		});
		
	//跳转绝对布局
		Button	absoluteButton = (Button)findViewById(R.id.Button05);
		
		absoluteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intent = new Intent(); 
				intent.setClass(MainActivity.this, TableActivity.class);
				startActivity(intent);
			}
		});
		
	//跳转Toast界面
		Button	toastButton = (Button)findViewById(R.id.Button06);
				
		toastButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(MainActivity.this, ToastActivity.class);
			startActivity(intent);
			}
		});
		
		
	//跳转listView界面  Button08
		Button	ListButton = (Button)findViewById(R.id.Button08);
		
		ListButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(MainActivity.this, ListViewActivity.class);
			startActivity(intent);
			}
		});
	
	//SimpleCursorAdapter    Button09
		Button	simpleCurButton = (Button)findViewById(R.id.Button09);
		
		simpleCurButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(MainActivity.this, SimpleCurActivity.class);
			startActivity(intent);
			}
		});
		
	//SimpleAdapter           Button10	
		Button	simpleAdButton = (Button)findViewById(R.id.Button10);
		
		simpleAdButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(MainActivity.this, SimpleAdActivity.class);
			startActivity(intent);
			}
		});

	//跳转xml数据的listView      Button11
		Button	xmlDataButton = (Button)findViewById(R.id.Button11);
		
		xmlDataButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(MainActivity.this, XmlDataActivity.class);
			startActivity(intent);
			}
		});
		
	//跳转数据存储的界面    Button12
		Button dataStoreButton = (Button)findViewById(R.id.Button12);
		
		dataStoreButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
					
			Intent intent = new Intent(); 
			intent.setClass(MainActivity.this, DataStoredActivity.class);
			startActivity(intent);
			}
		});
		
	}

	//复写onActivityResult方法 
    //* 当SecondActivity页面关闭时，接收SecondActiviy页面传递过来的数据。 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Bundle bundle = data.getExtras();
		String param = bundle.getString("param");
		editRes.setText(param);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
