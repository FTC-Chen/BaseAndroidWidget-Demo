package com.emple.dddq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract.Directory;
import android.text.Layout.Directions;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileSdCardActivity extends Activity {

	private EditText editFirst;
	final String FILE_NAME = "/test.txt";
	protected static final String ACTIVITY_TAG="MyAndroid------";   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_sd_card);
		//读写sdcard上的文件
		
		/*其中读写步骤按如下进行:
		 * 1、调用Environment的getExternalStorageState()方法判断手机上是否插了sd卡,
		 * 且应用程序具有读写SD卡的权限，如下代码将返回true
		 * Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
		 * 2、调用Environment.getExternalStorageDirectory()方法来获取外部存储器，也就是SD卡的目录,
		 * 或者使用"/mnt/sdcard/"目录
		 * 3、使用IO流操作SD卡上的文件 
		 * 注意点：手机应该已插入SD卡，对于模拟器而言，可通过mksdcard命令来创建虚拟存储卡
		 * 
		   必须在AndroidManifest.xml上配置读写SD卡的权限
		   <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
		   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
		 * */
		
		//写入  editText1
		editFirst = (EditText)findViewById(R.id.editText1);
				
		//设置button   button1
		Button setButton = (Button)findViewById(R.id.button1);
		setButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Strmsg = editFirst.getText().toString().trim();
						
				Log.w(ACTIVITY_TAG, "~~~~"+Strmsg);
				//自定义写入的方法
				write(Strmsg);
			}
		});
		
		
		//获取  Button01
		Button getButton = (Button)findViewById(R.id.Button01);
		getButton.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				read();
			}
		});
		
	}

	
	private void write(String content) {
		// TODO Auto-generated method stub
		try {
			//如果sdcard存在
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				
				Log.w(ACTIVITY_TAG, "yyyyyy");
				//获取sd卡目录
				File sdCardDir = Environment.getExternalStorageDirectory();
				
				FileOutputStream outFileStream = new FileOutputStream(
						sdCardDir.getCanonicalPath()+FILE_NAME);
				outFileStream.write(content.getBytes());
				
				outFileStream.close();
				
				Log.w(ACTIVITY_TAG, "yyyyyy"+outFileStream.toString());
				
				Toast.makeText(this, "数据保存到text.txt文件夹了", Toast.LENGTH_SHORT).show();
				
			}		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	private void read() {
		// TODO Auto-generated method stub

		Log.w(ACTIVITY_TAG, "读取数据了!!!!");
		
		StringBuffer strsBuffer = new StringBuffer();  
		
		try {
			
			//如果手机插入了sd卡,而且应用程序有访问sd卡的权限
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
		
				Log.w(ACTIVITY_TAG, "应用程序有sd卡");
				
				 File file = new File(Environment.getExternalStorageDirectory()  
	                        .getCanonicalPath() + FILE_NAME);  
				
				 // 判断是否存在该文件  
	                if (file.exists()) {
	                	 // 打开文件输入流  
	                	FileInputStream fileR = new FileInputStream(file);
	                	BufferedReader readers = new BufferedReader(
	                			new InputStreamReader(fileR));
	                	String st = null;  
	                	 while ((st = readers.readLine()) != null) {  
	                         strsBuffer.append(st);  
	                     }  
	                	 fileR.close();
	                }else {
	                	 Toast.makeText(this, "该目录下文件不存在", Toast.LENGTH_LONG).show();  
					}  
			}else{
					
				Log.w(ACTIVITY_TAG, "应用程序没有检测到sd卡");	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Toast.makeText(this, "读取到的数据是：" + strsBuffer.toString() + "",  
                Toast.LENGTH_SHORT).show(); 	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_sd_card, menu);
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
