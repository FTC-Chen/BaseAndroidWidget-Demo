package com.emple.dddq;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileStoredActivity extends Activity {

	private EditText editFirst;
	
	protected static final String ACTIVITY_TAG="MyAndroid------";    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_stored);
		
		//文件存储
		/*
		 * 核心原理: Context提供了两个方法来打开数据文件里的文件IO流 FileInputStream openFileInput(String name); 
		 * FileOutputStream(String name , int mode),
		 * 
		 * 这两个方法第一个参数 用于指定文件名，第二个参数指定打开文件的模式。
		 * 具体有以下值可选：
		 * */
		
		/*
		 * MODE_PRIVATE:为默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下,
		 * 写入的内容会覆盖原文件的内容,如果想把新写入的内容追加到原文件中.可以使用Context.MODE_APPEND
           MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件。
           MODE_WORLD_READABLE：表示当前文件可以被其他应用读取；
           MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
		 * */
		
		/*
		 * 除此之外，Context还提供了如下几个重要的方法：
		   getDir(String name , int mode):在应用程序的数据文件夹下获取或者创建name对应的子目录
		   File getFilesDir():获取该应用程序的数据文件夹得绝对路径
		   String[] fileList():返回该应用数据文件夹的全部文件            
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
				
				//自定义写入的方法
				write(Strmsg);
			}
		});
		
		
		//输出查看    Button01
		Button getButton = (Button)findViewById(R.id.Button01);
		getButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//自定义输出的方法
				//获取文件中的值
				String value = read();
				
				Toast.makeText(getApplicationContext(), "口令为:"+value, Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	
	/*openFileOutput()方法的第一参数用于指定文件名称，
	 * 不能包含路径分隔符“/” ，如果文件不存在，Android 会自动创建它。
	 * 创建的文件保存在/data/data/<package name>/files目录，
	 * 如： /data/data/cn.tony.app/files/message.txt，
	 * */
	
	//写入文件
	private void write(String msg) {
		// TODO Auto-generated method stub
		// 步骤1：获取输入值
		if (msg == null) {
			return;
		}
	
		try {
			
			// 步骤2:创建一个FileOutputStream对象,MODE_APPEND追加模式
			FileOutputStream outFile = openFileOutput("message1.txt", MODE_APPEND);
			// 步骤3：将获取过来的值放入文件
			outFile.write(msg.getBytes());
		    // 步骤4：关闭数据流
			outFile.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	} 
	
	//读取方法
	public String read() {
		try {
			FileInputStream inStream = this.openFileInput("message1.txt");
			byte[] buffer = new byte[1024];
			int hasRead = 0;
			StringBuilder strB = new StringBuilder();
	
			  while ((hasRead = inStream.read(buffer)) != -1) {
				  strB.append(new String(buffer, 0, hasRead));
	            }
             
			inStream.close();
			return strB.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_stored, menu);
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
