package com.emple.dddq;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharePersActivity extends Activity {

	private EditText editFirst;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_pers);
		
		/*
		 * 常用来存储应用中的用户偏好设置，例如应用的默认皮肤设置、记录用户上次登录信息等。
		 * 数据的存储格式为Key-Value。
		 * 适用范围：保存少量的数据，且这些数据的格式非常简单：字符串型、基本类型的值。
		 * 比如应用程序的各种配置信息（如是否打开音效、是否使用震动效果、小游戏的玩家积分等），解锁口 令密码等
		 * */
		
		/*
		 * 核心原理：保存基于XML文件存储的key-value键值对数据，通常用来存储一些简单的配置信息。
		 * 通过DDMS的File Explorer面板，展开文件浏览树,
		 * 很明显SharedPreferences数据总是存储在/data/data/<package name>/shared_prefs目录下。
		 * SharedPreferences对象本身只能获取数据而不支持存储和修改,
		 * 存储修改是通过SharedPreferences.edit()获取的内部接口Editor对象实现。 
		 * SharedPreferences本身是一个接口，程序无法直接创建SharedPreferences实例，
		 * 只能通过Context提供的getSharedPreferences(String name, int mode)方法来获取SharedPreferences实例，
		 * 该方法中name表示要操作的xml文件名，
		 * 第二个参数具体如下：
		 *       Context.MODE_PRIVATE: 指定该SharedPreferences数据只能被本应用程序读、写。

                 Context.MODE_WORLD_READABLE:  指定该SharedPreferences数据能被其他应用程序读，但不能写。

                 Context.MODE_WORLD_WRITEABLE:  指定该SharedPreferences数据能被其他应用程序读，写 
		 * */
		
		/*
		 * Editor有如下主要重要方法：
                 SharedPreferences.Editor clear():清空SharedPreferences里所有数据
                 SharedPreferences.Editor putXxx(String key , xxx value): 
                 向SharedPreferences存入指定key对应的数据，其中xxx 可以是boolean,float,int等各种基本类型据
                 SharedPreferences.Editor remove(): 删除SharedPreferences中指定key对应的数据项
                 boolean commit(): 当Editor编辑完成后，使用该方法提交修改
		 * 
		 * */
		
		//editText1
		editFirst = (EditText)findViewById(R.id.editText1);
		
		//设置button
		Button setButton = (Button)findViewById(R.id.button1);
		setButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//步骤1：获取输入值
				String code = editFirst.getText().toString().trim();
				
				//步骤2-1：创建一个SharedPreferences.Editor接口对象，lock表示要写入的XML文件名，
				//MODE_WORLD_WRITEABLE写操作
				SharedPreferences.Editor editor = getSharedPreferences("lock",MODE_WORLD_WRITEABLE).edit();
				//步骤2-2：将获取过来的值放入文件
				editor.putString("code", code);
				//步骤3：提交
				editor.commit();
				
				Toast.makeText(getApplicationContext(), "口令设置成功", Toast.LENGTH_SHORT).show();
		
			}
		});
	
		//获取button
		Button getButton = (Button)findViewById(R.id.button2);
		getButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//获取存储的数据
				//步骤1：创建一个SharedPreferences接口对象
				SharedPreferences read = getSharedPreferences("lock", MODE_WORLD_WRITEABLE);
				//获取文件中的值
				String value = read.getString("code", "");
				
				Toast.makeText(getApplicationContext(), "口令为:"+value, Toast.LENGTH_SHORT).show();
			}
		});
		
	
		/*
		 * 读写其他应用的SharedPreferences: 步骤如下
                1、在创建SharedPreferences时，指定MODE_WORLD_READABLE模式，表明该SharedPreferences数据可以被其他程序读取
                2、创建其他应用程序对应的Context:
                    Context pvCount = createPackageContext("com.tony.app", Context.CONTEXT_IGNORE_SECURITY);
                    这里的com.tony.app就是其他程序的包名
                3、使用其他程序的Context获取对应的SharedPreferences
                    SharedPreferences read = pvCount.getSharedPreferences("lock", Context.MODE_WORLD_READABLE);
                4、如果是写入数据，使用Editor接口即可，所有其他操作均和前面一致。
					SharedPreferences对象与SQLite数据库相比，免去了创建数据库，创建表，写SQL语句等诸多操作，相对而言更加方便，简洁。
					但是SharedPreferences也有其自身缺陷，比如其职能存储boolean，int，float，long和String五种简单的数据类型，
					比如其无法进行条件查询等。所以不论SharedPreferences的数据存储操作是如何简单，它也只能是存储方式的一种补充，
					而无法完全替代如SQLite数据库这样的其他数据存储方式。
		 * */
		
		
	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.share_pers, menu);
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
