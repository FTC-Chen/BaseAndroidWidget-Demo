package com.emple.dddq;

import android.R.string;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.provider.ContactsContract.RawContacts;  

public class ContenProActivity extends Activity {

	 private EditText nameET;  
	 private EditText numberET;  
	 private Button insertBtn;  
	 private Button deleteBtn;  
	 private Button queryBtn;  
	 private ListView contentView;  
	 
	 
	 protected static final String ACTIVITY_TAG= " MyAndroid1----";  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conten_pro);
		
		//ContentProvider分为系统的和自定义的，系统的也就是例如联系人，图片等数据。
		//通过获得这些ContentProvider可以查询它们包含的数据，当然前提是已获得适当的读取权限。
		
		//http://blog.csdn.Net/qq_27280457/article/details/51819299
		
		//http://blog.csdn.net/zuolongsnail/article/details/6566317
		
		/*
		 * public boolean onCreate() 在创建ContentProvider时调用
		   public Cursor query(Uri, String[], String, String[], String) 
		   用于查询指定Uri的ContentProvider，返回一个Cursor
		   public Uri insert(Uri, ContentValues) 用于添加数据到指定Uri的ContentProvider中
		   public int update(Uri, ContentValues, String, String[]) 
		   用于更新指定Uri的ContentProvider中的数据
		   public int delete(Uri, String, String[]) 用于从指定Uri的ContentProvider中删除数据
		   public String getType(Uri) 用于返回指定的Uri中的数据的MIME类型
		 * */
		
		/**如果操作的数据属于集合类型，那么MIME类型字符串应该以vnd.android.cursor.dir/开头。
		   例如：要得到所有person记录的Uri为content://contacts/person，
		   那么返回的MIME类型字符串为"vnd.android.cursor.dir/person"。
		   如果要操作的数据属于非集合类型数据，那么MIME类型字符串应该以vnd.android.cursor.item/开头。 
		 * */
		
		/*
		 * 例如：要得到id为10的person记录的Uri为content://contacts/person/10，
		 * 那么返回的MIME类型字符串应为"vnd.android.cursor.item/person"。
		 * */
		
		/*
		 * 2.ContentResolver
			当外部应用需要对ContentProvider中的数据进行添加、删除、修改和查询操作时，可以使用ContentResolver类来完成，
			要获取ContentResolver对象，可以使用Context提供的getContentResolver()方法。
		 * */
		
		/*ContentResolver提供的方法和ContentProvider提供的方法对应的有以下几个方法。
		  public Uri insert(Uri uri, ContentValues values) 用于添加数据到指定Uri的ContentProvider中。
		  public int delete(Uri uri, String selection, String[] selectionArgs) 
		  用于从指定Uri的ContentProvider中删除数据。
	      public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) 
	      用于更新指定Uri的ContentProvider中的数据。
		  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) 
		  用于查询指定Uri的ContentProvider。
		 * */
		
		/*Uri指定了将要操作的ContentProvider，其实可以把一个Uri看作是一个网址，我们把Uri分为三部分。
          第一部分是"content://"。可以看作是网址中的"http://"。
          第二部分是主机名或authority，用于唯一标识这个ContentProvider，
          外部应用需要根据这个标识来找到它。可以看作是网址中的主机名，比如"blog.csdn.NET"。
          第三部分是路径名，用来表示将要操作的数据。可以看作网址中细分的内容路径。
		 * */
		
		nameET = (EditText) findViewById(R.id.editText1);  
		numberET = (EditText) findViewById(R.id.EditText01);  
		insertBtn = (Button) findViewById(R.id.button1);  
		deleteBtn = (Button) findViewById(R.id.Button01);  
		queryBtn = (Button) findViewById(R.id.Button02);  
		
        // 用于显示数据  
        contentView = (ListView) findViewById(R.id.listView1);
        insertBtn.setOnClickListener(new OperateOnClickListener());  
        deleteBtn.setOnClickListener(new OperateOnClickListener());  
        queryBtn.setOnClickListener(new OperateOnClickListener());  
	}

	class OperateOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 String name1 = nameET.getText().toString();  
	         String number2 = numberET.getText().toString();  
	         Person p = new Person(name1, number2);
	         
	         switch (v.getId()) {  
	            // 插入数据  
	            case R.id.button1:  
	                insert(p);  
	                view();  
	                break;  
	            // 删除数据  
	            case R.id.Button01:  
	                delete(name1);  
	                view();  
	                break;  
	            // 查询数据  
	            case R.id.Button02:  
	                view();  
	                break;  
	            }      
		}
	}
	
	 // 显示数据  
    private void view() {  
        Cursor c = query("");  
        ListAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.contactslist,  
                c, new String[] { People._ID, People.NAME, People.NUMBER },  
                new int[] { R.id.id, R.id.name, R.id.number});  
        
        contentView.setAdapter(listAdapter);  
    }  
	
	
	//// 插入联系人  
	 @SuppressWarnings("deprecation")
	private void insert(Person p) {  
		 // 获得ContentResolver对象 
		 Log.w(ACTIVITY_TAG, "..."+p.name); 
		 Log.w(ACTIVITY_TAG, "..."+p.number);
		  
//		 ContentResolver contentRes = getContentResolver();
//		 ContentValues values = new ContentValues();
//		 values.put(People.NAME, p.name);
////		 
////		 // 表示是否把联系人添加到收藏（加星），1表示加入，0表示不加入，这行代码注释默认是不加入。 
//		 values.put(Contacts.People.STARRED, 1);
////		 
//		 Uri uri = Contacts.People.createPersonInMyContactsGroup(contentRes,values);
//		 //Uri uri = Uri.parse("content://com.android.contacts/contacts"); // 访问所有联系人  
//		// 获得联系人People表的Uri 
//		 Uri url = uri.withAppendedPath(uri, Contacts.People.Phones.CONTENT_DIRECTORY);
//		 values.clear();
//		 values.put(Contacts.Phones.TYPE, Contacts.Phones.NUMBER);
//		 values.put(Contacts.Phones.NUMBER, p.number);
//		 
//		 // 插入操作  
//		 contentRes.insert(url, values); 
		 
		 
		 try {
			 Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");  
		        
			 ContentResolver resolver = getApplicationContext().getContentResolver();  
		     
			 ContentValues values = new ContentValues();  
		     
			 long contactId = ContentUris.parseId(resolver.insert(uri, values)); 
			 
			 /* 往 data 中添加数据（要根据前面获取的id号） */  
		        // 添加姓名  
		        uri = Uri.parse("content://com.android.contacts/data");  
		        values.put("raw_contact_id", contactId);  
		        values.put("mimetype", "vnd.android.cursor.item/name");  
		        values.put("data2", p.name);  
		        resolver.insert(uri, values); 
		        
		     // 添加电话  
		        values.clear();  
		        values.put("raw_contact_id", contactId);  
		        values.put("mimetype", "vnd.android.cursor.item/phone_v2");  
		        values.put("data2", "2");  
		        values.put("data1", p.number);  
		        resolver.insert(uri, values); 
		        
		        Log.i("true", "create table success"); 
		        
		        // 添加Email  
//		        values.clear();  
//		        values.put("raw_contact_id", contactId);  
//		        values.put("mimetype", "vnd.android.cursor.item/email_v2");  
//		        values.put("data2", "2");  
//		        values.put("data1", "zhouguoping@qq.com");  //传过来email地址
//		        resolver.insert(uri, values);  
			
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("err", "create table failed"); 
		} 
	 }
	 
	 // 删除联系人  
	    private void delete(String name) {  
	        // 获得ContentResolver对象  
	        ContentResolver cr = getContentResolver();  
	        Uri url = Contacts.People.CONTENT_URI;  
	        // 设置删除条件  
	        String where = People.NAME + "=?";  
	        String[] selectionArgs = { name };  
	        cr.delete(url, where, selectionArgs);  
	    }  
	 
	 
	 // 查询联系人 
	 private Cursor query(String name) {
		// 获得ContentResolver对象  
		ContentResolver cr = getContentResolver(); 
		Uri uri = Contacts.People.CONTENT_URI;
		// 查询对象 
		String[] projection = {People._ID, People.NAME, People.NUMBER};
		//设置查询条件，这里我把selection和selectionArgs参数都设为null，表示查询全部数据 
		
		String selection = null;
		String[] selectionArgs = null;  
			
		if (!"".equals(name)) {
			selection = People.NAME + "=?";	
			selectionArgs = new String[] { name };  
		}
		// 设置排序条件  
		String sortOrder = Contacts.People._ID;  
		Cursor cur = cr.query(uri, projection, selection, selectionArgs, sortOrder);
		
		return cur;  
	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conten, menu);
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
