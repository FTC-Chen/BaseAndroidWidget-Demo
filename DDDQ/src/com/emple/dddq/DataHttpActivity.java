package com.emple.dddq;

import java.io.IOException;
import java.util.List;
import java.util.logging.MemoryHandler;

import com.emple.bean.UserInfomation;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Type;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;



public class DataHttpActivity extends Activity implements View.OnClickListener{

	private Button bt_get;
    private Button bt_post;
    
    UserInfomation userInfo;

    final OkHttpClient client = new OkHttpClient();
    
    public Handler handler = new Handler();
    
    public Handler MHandler = new Handler(){
	  
    	public void handleMessage(Message msg) {
    		
    		switch (msg.what) {
			case 1:
				showMToast();
				break;

			default:
				break;
			}
    		 //my_layout.invalidate();  
             super.handleMessage(msg);  
    	};
   } ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_http);
		
		//HttpUrlConnection
		/*
		 * HttpUrlConnection是Java.NET包中提供的API，我们知道Android SDK是基于Java的，
		 * 所以当然优先考虑HttpUrlConnection这种最原始最基本的API，
		 * 其实大多数开源的联网框架基本上也是基于JDK的HttpUrlConnection进行的封装罢了，
		 * 掌握HttpUrlConnection需要以下几个步骤： 
		 * */
		
		/*
		 * 1、将访问的路径转换成URL。
		 * URL url = new URL(path);
		 * 
		 * 2、通过URL获取连接.
		 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		 * 
		 * 3、设置请求方式。
		 * conn.setRequestMethod(GET);
		 * 
		 * 4、设置连接超时时间。
		 * conn.setConnectTimeout(5000);
		 * 
		 * 5、设置请求头的信息。
		 * conn.setRequestProperty(User-Agent, Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0));
		 * 
		 * 7、针对不同的响应码，做不同的操作（请求码200，表明请求成功，获取返回内容的输入流）
		 * */
		
		//okhttp的使用  移动会议系统那个使用的是  Volley
//		
//		OkHttpClient client = new OkHttpClient(); 
//		String run(String url) throws IOException {
//		    Request request = new Request.Builder().url(url).build();
//		    Response response = client.newCall(request).execute();
//		    if (response.isSuccessful()) {
//		        return response.body().string();
//		    } else {
//		        throw new IOException("Unexpected code " + response);
//		    }
//		}
		
		 bt_get=(Button)findViewById(R.id.get);
		 bt_post=(Button)findViewById(R.id.post);
		 
		 bt_get.setOnClickListener(this);  
		 bt_post.setOnClickListener(this); 
		 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.get:
			getRequest();
			break;
		case R.id.post:
			postRequest();
			break;

		
		}
	}

	private void getRequest() {
		Log.i("WY","此处点击get");
		// TODO Auto-generated method stub
		final Request request = new Request.Builder()
				 				.get()
				 				.tag(this)
				 				.url("https://www.baidu.com")
				 				.build();
		 
		 new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Response response = null;
				
				try {
					response = client.newCall(request).execute();
					if (response.isSuccessful()) {
						Log.i("WY","打印GET响应的数据：" + response.body().string());
						
						//showMToast();//此线程中调用会失败,必须用handle去调用
						
						//发送一个handle的message
						 Message message=new Message();  
						 message.what=1;  
						 MHandler.sendMessage(message);  
					        
					}else {
						Log.i("WY","请求失败");
						throw new IOException("Unexpected code"+"~~~~~~"+response);
					}
				} catch (Exception e) {
					// TODO: handle exception
					 e.printStackTrace();
					 Log.i("WY","11111111   错误了");
				}
			}
		}).start();
		
		//---------第二种方法  也可成功 打印出返回的数据 但是对比上一个方法感觉很慢
		//创建一个Request
//		final Request request = new Request.Builder()
//				                .url("https://github.com/hongyangAndroid")
//				                .build();
//		//new call
//		Call call = client.newCall(request); 
//		//请求加入调度
//		call.enqueue(new Callback() {
//					
//			@Override
//			public void onResponse(Call arg0, Response arg1) throws IOException {
//				// TODO Auto-generated method stub
//						
//				Log.i("WY","请求成功");
//				
//				Log.i("WY","打印GET响应的数据：" + arg1.body().string());
//			}		
//			@Override
//			public void onFailure(Call arg0, IOException arg1) {
//				// TODO Auto-generated method stub
//			
//				Log.i("WY","请求失败");
//			}
//		});
	}
	
	
	//post --------------登录
	//http://www.cnblogs.com/whoislcj/p/5526431.html
	private void postRequest() {
			
		Log.i("WY","此处点击post");
		/*
		 * 
		okhttp3.FormBody instead of FormEncodingBuilder.（OkHttp3.x，FormEncodingBuilder已被FormBody取代）
		
		OkHttpClient client = new OkHttpClient();
		FormBody body = new FormBody.Builder()
     	.add("your_param_1", "your_value_1")
     	.add("your_param_2", "your_value_2")
     	.build();
		
		Request request = new Request.Builder()
    	.url("http://my.wonderfull.url/to/post")
    	.post(body)
    	.build();	
    	
    	Response response = client.newCall(request).execute();
		 * */

		
		 handler.postDelayed(new Runnable() {
	            @Override
	            public void run() {
	                    
	            	getData();
	            }
	        }, 100);

//		new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Response response = null;
//                try {
//                    response = client.newCall(request).execute();
//                    
//                    if (response.isSuccessful()) {
//                    	Log.i("WY","请求成功");
//                    	
//                        Log.i("WY","打印POST响应的数据：" + response.body().string()); 
//   
//                        //String res = response.body().string();
//                        
//                        //json解析 使用Gson
//                        //http://www.jb51.net/article/32547.htm
//                       // Gson gson = new Gson(); 
//                        
//                        //UserInfomation userInfo = gson.fromJson(res, UserInfomation.class);
//                        
//                        //List<UserInfomation.ResultsBean> results = userInfo.getResults();
//                        
//                        //打印看结果
////                        Log.i("WY","----用户名:"+userInfo.getData().getName());
////                        
////                        Log.i("WY","----部门:"+userInfo.getData().getUser_gname());
////                        
////                        Log.i("WY","----uid:"+userInfo.getData().getUid());   
//                    } else {
//                    	Log.i("WY","请求失败");
//                    	
//                        throw new IOException("Unexpected code " + response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.i("WY","错误了2222222");
//                }
//            }
//        
//		}).start();
	}

	 private void getData() {
		 	
		 FormBody body = new FormBody.Builder()
				 .add("name", "zpl")
				 .add("Password", "aaaa")
				 .build();
			
		 final Request request = new Request.Builder()
				 .url("http://elv.sunac.com.cn:8084/api/Login/POST")
				 .post(body)
				 .build();
			
		 //http://www.jianshu.com/p/48b2aab0efc1  此链接方法
		 //通过request的对象去构造得到一个Call对象，请求加入调度
		 client.newCall(request).enqueue(new Callback() {
				
			 @Override
			 public void onFailure(Call arg0, IOException arg1) {
				 // TODO Auto-generated method stub
					
				 DataHttpActivity.this.runOnUiThread(new Runnable() {
					 @Override
					 public void run() {
							
							Toast.makeText(DataHttpActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
						}
					});
				}
			
				//成功方法
				@Override
				public void onResponse(Call call, Response response) throws IOException {
					// TODO Auto-generated method stub
					
					if (!response.isSuccessful()) {
		                throw new IOException("Unexpected code " + response);
		            }
					//返回数据
					String res = response.body().string();
					Log.v("te::","-------返回数据"+res);
			          
					//解析json
					Gson gson = new Gson(); 
					
					userInfo = gson.fromJson(res, UserInfomation.class);
					
					
					DataHttpActivity.this.runOnUiThread(new Runnable() {
						@Override
			                public void run() {    
							
							Log.v("WY","----用户名:"+userInfo.getMessagemodel().getName());
							
			                }
			            });
					 //打印看结果
//					Log.v("WY","----用户名:"+userInfo.getData().getName());
//	            
//					Log.i("WY","----部门:"+userInfo.getData().getUser_gname());
//	            
//					Log.i("WY","----uid:"+userInfo.getData().getUid());   		
				}
			
			
			});

		 
	 }
	
	//请求成功 调用的方法 
	private void showMToast() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "请求成功调用方法", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_http, menu);
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
