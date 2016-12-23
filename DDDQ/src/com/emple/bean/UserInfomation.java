package com.emple.bean;

import java.util.List;


//我理解此处即为model
public class UserInfomation {

	/*
	 * {"message":1,"messageDetail":"执行成功","messagemodel":{"uid":"zpl","user_gname":"管理单元",
	 * "user_gid":0,"usn":54,"name":"周沛霖","uniid":"g0001","img":"http://elv.sunac.com.cn:8084/file/images/UserPic/default.jpg",
	 * "uniid_detailed":"g0001","user_email":"",
	 * "user_tel":"","USER_INTRODUCE":"","password":"保密","USER_SUMSCORES":2,
	 * "USER_EPVAL":0,"USER_ROWNUMBER":6,"USER_JOBNAME":"暂无",
	 * "unFinishedCourseCount":14,"unFinishedExamCount":1}}

	 * */
	String message;
	String messageDetail;
	
	public Messagemodel messagemodel;
	
	public String getMessage(){
		return message;
	}
	
	public String getMessageDetail(){
		return messageDetail;
	}
	
	public Messagemodel getMessagemodel(){
		
		return messagemodel;
	}

	 
	public static class Messagemodel{
		 
		String uid;
        String user_gname;
        String user_gid;
        String usn;
        String name;
        String uniid;
        String img;
        String uniid_detailed;
        String user_email;
        String user_tel;
        String password;
        String USER_SUMSCORES;
        String USER_EPVAL;
        String USER_ROWNUMBER;
        String USER_JOBNAME;
        String unFinishedCourseCount;
		
        //name
        public void setName(String name) {	
        	this.name = name; 	
        }
        
        public String getName() {      
        	return name;  
        } 
   	 
        //user_gname
        public void setUser_gname(String user_gname) {
        	this.user_gname = user_gname; 
        }
   	
        public String getUser_gname() {  
        	return user_gname;  
        } 
   	
        //uid
        public void setUid(String uid) {
        	this.uid = uid; 
        }
   		
        public String getUid() {          
        	return uid;  
        } 
        
      
        
	}
	
	
	//private List<ResultsBean> results;  json里面包含多个数组的情况

	 
//	 public boolean message() {
//          
//		 return message;
//      }
//
//	 public String messageDetail() {
//         
//		 return messageDetail;
//      }
//	
	
	
	
	
//	 public List<ResultsBean> getResults() {
//        
//		 return results;
//     }

	 
	 
//	 public static class ResultsBean {
//         
//		 String name;
//         String user_gname;
//         String uid;
//
//        
//         
//     }
		
}


