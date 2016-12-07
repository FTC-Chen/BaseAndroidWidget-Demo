package com.emple.dddq;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends Activity {

	//使用handler时首先要创建一个handler
    //要用handler来处理多线程可以使用runnable接口，这里先定义该接口
    //线程中运行该接口的run函数
	Handler handler=new Handler(){  
		 @Override  
		 public void handleMessage(Message msg) {
			 int what=msg.what;
			 switch (what) {  
	            case 1:  
	                showToast();  
	                break;  
	            default:  
	                break;  
	            }  
			 super.handleMessage(msg);  
		 }
    };  
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toast);
		
		//-------默认toast
		Button	toastButton = (Button)findViewById(R.id.button1);	
		toastButton.setOnClickListener(new OnClickListener() {		
			
			
			
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
			
			// 第一个参数：当前的上下文环境。可用getApplicationContext()或this  
			// 第二个参数：要显示的字符串。也可是R.string中字符串ID  
			// 第三个参数：显示的时间长短。Toast默认的有两个LENGTH_LONG(长)和LENGTH_SHORT(短)，也可以使用毫秒如2000ms  
			Toast toast = Toast.makeText(ToastActivity.this, "默认的Toast", Toast.LENGTH_SHORT);
			//显示toast
			toast.show();	
			}
		});
		
		
		//-------自定义位置toast
		Button	customButton = (Button)findViewById(R.id.Button01);	
		customButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
			// TODO Auto-generated method stub
				
			Toast toast = Toast.makeText(ToastActivity.this, "自定义位置的Toast", Toast.LENGTH_SHORT);
			
			//第一个参数：设置toast在屏幕中显示的位置。我现在的设置是居中靠顶  
	        //第二个参数：相对于第一个参数设置toast位置的横向X轴的偏移量，正数向右偏移，负数向左偏移  
	        //第三个参数：同的第二个参数道理一样  
	        //如果你设置的偏移量超过了屏幕的范围，toast将在屏幕内靠近超出的那个边界显示  
	        //toast.setGravity(Gravity.TOP|Gravity.CENTER, -50, 100);   
	        //屏幕居中显示，X轴和Y轴偏移量都是0  
	        toast.setGravity(Gravity.CENTER, 0, 0); //y,正式向下.负是相反 //x,正是向右,负是向左
			
			//显示toast
			toast.show();	
			}
		});
		
		
		//-------带图片的toast
				Button	picCustomButton = (Button)findViewById(R.id.Button02);	
				picCustomButton.setOnClickListener(new OnClickListener() {		
					@Override
					public void onClick(View v) {
					// TODO Auto-generated method stub
						
					Toast toast = Toast.makeText(ToastActivity.this, "带图片的Toast", Toast.LENGTH_SHORT);
					
					//第一个参数：设置toast在屏幕中显示的位置。我现在的设置是居中靠顶  
			        //第二个参数：相对于第一个参数设置toast位置的横向X轴的偏移量，正数向右偏移，负数向左偏移  
			        //第三个参数：同的第二个参数道理一样  
			        //如果你设置的偏移量超过了屏幕的范围，toast将在屏幕内靠近超出的那个边界显示  
			        //toast.setGravity(Gravity.TOP|Gravity.CENTER, -50, 100);   
			        //屏幕居中显示，X轴和Y轴偏移量都是0  
			        //toast.setGravity(Gravity.CENTER, 0, 0); //y,正式向下.负是相反 //x,正是向右,负是向左
					//创建imgView
			        ImageView imgView = new ImageView(getApplicationContext());
			        //设置图片
			        imgView.setImageResource(R.drawable.dddheckmark);
			        //获得toast的布局  
			        LinearLayout toastView = (LinearLayout)toast.getView();
			        //设置此布局为横向的
			        toastView.setOrientation(LinearLayout.VERTICAL);
			        //将ImageView在加入到此布局中的第一个位置 
			        toastView.addView(imgView, 0); 
					//显示toast
					toast.show();	
					}
				});
		
		//-------完全自定义的toast
		Button	allCustomButton = (Button)findViewById(R.id.Button03);	
		allCustomButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
				//自定义方法
				showToast();
			}
		});
		
		//hander 不明白
		//使用handler时首先要创建一个handler
	    //Handler handler = new Handler();
	    //要用handler来处理多线程可以使用runnable接口，这里先定义该接口
	    //线程中运行该接口的run函数
	
		
		//其他线程调用的toast
		Button	threadCustomButton = (Button)findViewById(R.id.Button04);	
		threadCustomButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						 showToast(); 
					}
				});
	
			}
		});
	
		
		//-------弹出系统提示框
		Button	alertButton = (Button)findViewById(R.id.Button05);	
		alertButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//弹出系统提示框
				new AlertDialog.Builder(ToastActivity.this)
							   .setTitle("系统提示")
							   .setMessage("确认退出系统吗")
							   .setPositiveButton("确定", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
									finish();
								}
							})
							   .setNegativeButton("取消", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									
									showToast();
								}
							})
							   .show();			 
			}
		});
		
		
	}
	
	public void run() {  
        handler.sendEmptyMessage(1);  
    }  
	

	private void showToast(){
		
		 LayoutInflater inflater = getLayoutInflater();
	    
		 View view = inflater.inflate(R.layout.all_custom_title, null);
	     
	     ImageView image = (ImageView) view.findViewById(R.id.image);  
	     //设置布局中图片视图中图片  
	     image.setImageResource(R.drawable.dddheckmark);  
	     
	     TextView title = (TextView) view.findViewById(R.id.title);  
	     //设置标题  
	     title.setText("标题栏");  
	     TextView text = (TextView) view.findViewById(R.id.content);  
	     //设置内容  
	     text.setText("完全自定义的Toast"); 
	     
	     Toast toast= new Toast(getApplicationContext());  
	     toast.setGravity(Gravity.CENTER , 0, 0);  
	     toast.setDuration(Toast.LENGTH_SHORT);  
	     toast.setView(view);  
	     toast.show();  
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.toast, menu);
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
