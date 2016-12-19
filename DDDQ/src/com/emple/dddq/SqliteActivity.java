package com.emple.dddq;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Contacts.Intents.Insert;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SqliteActivity extends Activity {

	SQLiteDatabase db = null;
	
	DBOpenHelper dbHelper = null;  
	
	protected static final String ACTIVITY_TAG="MyAndroid~~~~~";  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		
		//创建数据库      button1
		Button sqliteButton = (Button)findViewById(R.id.button1);
		sqliteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//DBOpenHelper helper = new DBOpenHelper(SqliteActivity.this);
				//调用 getWritableDatabase()或者 getReadableDatabase()其中一个方法将数据库建立    
				//db = helper.getWritableDatabase();  //得到的是SQLiteDatabase对象
				
				//Log.w(ACTIVITY_TAG, "创建数据库");
				OpenDb(); 
				
				db = dbHelper.getWritableDatabase();
				String sql = "create table if not exists TestUsers"+  
                        "(id int primary key,name varchar,sex varchar)";  
				try {  
		            db.execSQL(sql);  
		            
		            Log.i("true", "create table sucss");  
		        
				} catch (SQLException e) {  
		        
					Log.i("err", "create table failed");  
				}  
			
			}
		});
	
		
		//三.增删改查的实现   Button01
		//一:插入数据
		Button insertButton = (Button)findViewById(R.id.Button01);
		insertButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				insert();
	
			}
		});
	
		//二:查询数据   Button02
		Button queryButton = (Button)findViewById(R.id.Button02);
		queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				query();
				
			}
		});
		
		//三:修改数据   Button03
		Button updateButton = (Button)findViewById(R.id.Button03);
		updateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				update();
				
			}
		});
		
		//四:删除数据   Button04
		Button deleteButton = (Button)findViewById(R.id.Button04);
		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				delete();
				
			}
		});
		
		//删除指定表   Button05
		Button deleteTableButton = (Button)findViewById(R.id.Button05);
		deleteTableButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				drop();
				
			}
		});
		
		
	}
	
	/** 
    * 打开数据库 
    */  
    public void OpenDb(){  
        
    	dbHelper = new DBOpenHelper(this, "TestDb01");  
        
        db = dbHelper.getWritableDatabase();  
    }  

	//插入的方法  2种方法均可
	private void insert() {
		// TODO Auto-generated method stub

		//实例化常量值   	方法正确
		ContentValues cValues = new ContentValues();
		//id
		cValues.put("id", "01005");
		//添加用户名
		cValues.put("name", "xiaoming");
		//添加密码
		cValues.put("sex", "men");
		//调用insert()方法插入数据
		db.insert("TestUsers", null, cValues);
		
		 //插入数据SQL语句    方法正确
	     //String stu_sql="insert into stu_table(sname,snumber) values('xiaoming','01005')";       
	    //执行SQL语句   
	     //db.execSQL(stu_sql); 
		
		
		//使用语句插入 方法正确
//		 db = dbHelper.getWritableDatabase();  
//	        
//		 String sql = "insert into TestUsers (id,name,sex) values (2,'hongguang','men')";  
//		 
//		 try {         
//			 db.execSQL(sql);  
//			 
//			 Log.i("true", "insert table sucss");  
//		 
//		 } catch (SQLException e) {  
//	            
//			 Log.i("err", "insert failed");  
//		 }  
	}
	
	
	//查询的方法
	private void query() {
		// TODO Auto-generated method stub
		db = dbHelper.getWritableDatabase();  
		//查询获得游标  
		Cursor cursor = db.query("TestUsers",null,null,null,null,null,null);
		//判断游标是否为空  
		if (cursor.moveToFirst()) {
			//遍历游标
			for (int i = 0; i < cursor.getCount(); i++) {
				
				cursor.move(i);
				 //获得ID   
				int id = cursor.getInt(0);
				//获得用户名
				String userName = cursor.getString(1);
				//获得密码
				String password = cursor.getString(2);
				//输出用户信息
				System.out.println(id+":"+userName+":"+password);
				
				Toast.makeText(this, id+":"+userName+":"+password, Toast.LENGTH_SHORT).show();
			}
			
		}
					
		
	}
	
	//修改方法
	private void update() {
		// TODO Auto-generated method stub
		db = dbHelper.getWritableDatabase();  
		//实例化内容值
		ContentValues values = new ContentValues();   
		//在values中添加内容    
		values.put("name","anhong");  
		//修改条件    
	    String whereClause = "id=?";    
	    //修改添加参数   
	    //String[] whereArgs={String.valueOf(01005)};  此处修改参数错误     
	    //修改   
	    db.update("TestUsers",values,whereClause,new String[]{"01005"});  
	   
	    //语句  方法正确
//		String sql = "Update TestUsers set name = 'anhong',sex = 'men' where id = 01005";  
//		
//		 try {  
//			 db.execSQL(sql);  
//		 
//			 Log.i("true", "update success"); 
//		 } catch (SQLException e) {  
//			
//			 Log.i("err", "update failed");   
//		 }  
	}
	
	
	//删除方法
	private void delete() {
		// TODO Auto-generated method stub
		/*
		 * ①调用SQLiteDatabase的delete(String table,String whereClause,String[] whereArgs)方法，
		 * 参数一是表名称，参数二是删除条件，参数三是删除条件值数组；
		   ②编写删除SQL语句，调用SQLiteDatabase的execSQL()方法来执行删除。
		 * */
		
		//删除条件   
		String whereClause = "id=?";  
		//删除条件参数   
		//String[] whereArgs = {String.valueOf(2)};  
		//执行删除   
		db.delete("TestUsers",whereClause,new String[]{"01005"}); 
		
		
		//删除语句 调用
//		 db = dbHelper.getWritableDatabase();  
//		 String sql = "delete from TestUsers where id =010052";  
//		 try {  
//			 db.execSQL(sql);  
//		 
//		 } catch (SQLException e) {  
//		
//			 Log.i("err", "delete failed");  
//		 }  
		
	}
	
	/** 
     * 关闭数据库 
     */  
    public void CloseDb(){  
        dbHelper.close();  
    }  
	
    private void drop(){ 
    	db = dbHelper.getWritableDatabase(); 
        
    	try {
			
    		//删除表的SQL语句         
            String sql ="DROP TABLE TestUsers";             
            //执行SQL        
            db.execSQL(sql);     
    		
            Log.i("true", "delete tabl success"); 
            
		} catch (Exception e) {
			// TODO: handle exception
			
			 Log.i("err", "delete table failed"); 
		}
    	  
   }   
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite, menu);
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
