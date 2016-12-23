package com.emple.dddq;

public class Person {

	//private int id;
	public String name;
	public String number;

//	public Person(String name, String number) {
//		// TODO Auto-generated constructor stub
//		name = name;
//		
//		number = number;
//	}

	public void setName(String name) {
			
		 this.name = name; 
	}
	 
	public String getName() {  
	        
		return name;  
	} 
	
	public void setNumber(String number) {
		
		 this.number = number; 
	}
	
	 
	public String getNumber() {  
	        
		return number;  
	} 
	
	public Person(String name ,String number) {
		// TODO Auto-generated constructor stub
		
		this.name = name;
		
		this.number = number;
	}
	
}
