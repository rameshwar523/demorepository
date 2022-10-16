package com.corejava.miniproject.quiz;
import java.sql.*;
public class Login {
public static void logAdmin(String user_name,String pass) {
	
	boolean res=DBConnections.authenticate(user_name,pass);
	
	if(res) {
		
		Admin.admin();
			}
	else {
		System.out.println("Invalid User id or Password");
	}
}
public static void logStudent(String user_name,String pass) {
	
	boolean res=DBConnections.studentLogin(user_name,pass);
	
	if(res) {
		
		Exam.startExam(user_name);
			}
	else {
		System.out.println("Invalid User id or Password");
	}
}
}
