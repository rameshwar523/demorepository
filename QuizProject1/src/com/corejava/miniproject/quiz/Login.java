package com.corejava.miniproject.quiz;
import java.sql.*;
public class Login implements RegisterInterface{
	
public static void logAdmin(String user_name,String pass) {
	
	boolean res=DBConnections.authenticate(user_name,pass);
	
	if(res) {
		
		Admin.admin();
			}
	else {
		System.out.println("Invalid User id or Password");
		Display d2=new Display();
		d2.menuMain();
	}
}
public static void logStudent(String user_name,String pass) {
	
	boolean res=DBConnections.studentLogin(user_name,pass);
	
	if(res) {
		Display d=new Display();
		d.menuStudent(user_name);
			}
	else {
		System.out.println("Invalid User id or Password");
		Display d2=new Display();
		d2.menuMain();
	}
}
@Override
public void registerStd() {
	// Student get register here
	Display d2=new Display();
	Students s1=d2.disRegForm();
	int status=DBConnections.insert(DBConnections.connect(),s1);
    if(status==1) {
    	System.out.println("Registration done Successfully..");
    	d2.menuMain();
    }else{
    	System.err.println("Registration not done Successfully..");
    	d2.disRegForm();
    }
}
}
