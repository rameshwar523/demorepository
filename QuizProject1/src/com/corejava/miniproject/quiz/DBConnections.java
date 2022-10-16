package com.corejava.miniproject.quiz;
import java.sql.*;
public class DBConnections {
public static synchronized Connection connect() {
	Connection con=null;
	try {
		String url="jdbc:mysql://localhost:3306/";
		String db="quizdatabase";
		String driver="com.mysql.cj.jdbc.Driver";
		String user="root";
		String pass="goodluck";
		Class.forName(driver).newInstance();
	      con = DriverManager.getConnection(url+db, user, pass);
	     // System.out.println("Connected to database.");
	} catch (Exception e) {
	e.printStackTrace();
	}
	return con;
}
public static synchronized ResultSet getTable(Connection con,String query)
{
	ResultSet rs=null;
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	rs=pst.executeQuery();
	
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	
	return rs;
}
public static synchronized ResultSet getSelected(Connection con,String query)
{
	ResultSet rs=null;
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	rs=pst.executeQuery();	
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	System.out.println("Executing: "+query+"\nRows retrieved successfully");
	return rs;
}
//it is used to store the student details in student_details table
public static synchronized void insert(Connection con,String student_Name,String user_name,String pass)
{
	int stat=0;
	String query="insert into student_details (student_name,user_name,exam_password)values(?,?,?)";
	
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	pst.setString(1, student_Name);
	pst.setString(2, user_name);
	pst.setString(3, pass);
	stat=pst.executeUpdate();	
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	if(stat==1)
	{
		System.out.println("Executing: "+query+"\ninserted successfully");
	}
	else
	{
		System.out.println("Executing: "+query+"\ninsertion failed");
	}
}
public static synchronized void update(Connection con,String query)
{
	int stat=0;
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	stat=pst.executeUpdate();	
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	if(stat==1)
	{
		System.out.println("Executing: "+query+"\nupdated successfully");
	}
	else
	{
		System.out.println("Executing: "+query+"\nupdation failed");
	}
}
public static synchronized void deleteTable(Connection con,String tablename)
{
	int stat=0;
	try
	{
	PreparedStatement pst=con.prepareStatement("delete from "+tablename);
	stat=pst.executeUpdate();	
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	if(stat==1)
	{
		System.out.println(tablename+" deleted successfully");
	}
	else
	{
		System.out.println(tablename+" deletion failed");
	}
}
public static synchronized boolean studentLogin(String user_name,String pass) {
	Connection con=DBConnections.connect();
	String query="select user_name,exam_password from student_details where"+user_name;
	ResultSet rs=null;
	boolean re=false;
	String pwd="";
	String name="";
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	rs=pst.executeQuery();
	while(rs.next()) {
		name=rs.getString(1);
		pwd=rs.getString(2);
		}
	if(pass.equals(pwd)&&user_name.equals(name)) {
	re=true;
	}
else {
	
	re=false;
}
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	return re;
}


public static synchronized boolean authenticate(String user_name,String pass) {
	Connection con=DBConnections.connect();
	String query="select * from login where"+user_name;
	ResultSet rs=null;
	boolean re=false;
	String pwd="";
	String name="";
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	rs=pst.executeQuery();
	while(rs.next()) {
		name=rs.getString(1);
		pwd=rs.getString(2);
		}
	if(pass.equals(pwd)&&user_name.equals(name)) {
	re=true;
	}
else {
	
	re=false;
}
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	return re;
}
}