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
public static synchronized ResultSet getTable(Connection con,String query,String exam_name)
{
	ResultSet rs=null;
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	pst.setString(1,exam_name);
	rs=pst.executeQuery();
	
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	
	return rs;
}
public static synchronized ResultSet getQuestions(Connection con,String query,String exam_name) {

	ResultSet rs=null;
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	pst.setString(1, exam_name);
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
public static synchronized int insert(Connection con,Students std)
{
	int stat=0;
	String query="insert into student_details (student_name,user_name,exam_password,student_id,exam_details)values(?,?,?,?,?)";
	
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	pst.setString(1,std.getStudent_name());
	pst.setString(2, std.getUser_name());
	pst.setString(3, std.getPassword());
	pst.setString(4,std.getStudent_id());
	pst.setString(5,std.getExam_details());
	
	stat=pst.executeUpdate();	
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	return stat;
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
	String query="select user_name,exam_password from student_details where user_name=?";
	ResultSet rs=null;
	boolean re=false;
	String pwd="";
	String name="";
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	pst.setString(1,user_name);
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
	String query="select * from login where user_id=?";
	ResultSet rs=null;
	boolean re=false;
	String pwd="";
	String name="";
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	pst.setString(1,user_name);
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
public static synchronized int updateScore(String query,String user_name,String grade,String score) {
	Connection con=DBConnections.connect();
	int res=0;
	try
	{
	PreparedStatement pst=con.prepareStatement(query);
	pst.setString(1,score);
	pst.setString(2,grade);
	pst.setString(3, user_name);
	
	res=pst.executeUpdate();	
	}
	catch (Exception e) 
	{
		System.out.println(e);
	}
	return res;
}
public static synchronized boolean checkExamStatus(String user_name) {
	boolean status=false;
	String query="select grade from student_details where user_name=?";
	Connection con=DBConnections.connect();
	ResultSet rs=null;
	PreparedStatement pst;
	try {
		pst = con.prepareStatement(query);
		pst.setString(1,user_name);
		rs=pst.executeQuery();
		while(rs.next()) {
			String gr=rs.getString(1);
			if(gr!=null) 
				status=true;
				else 
				status=false;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return status;	
}
public static synchronized boolean checkStudentRegister(String user_name) {
	boolean status=false;
	String query="select * from student_details where user_name=?";
	Connection con=DBConnections.connect();
	ResultSet rs=null;
	PreparedStatement pst;
	try {
		pst = con.prepareStatement(query);
		pst.setString(1,user_name);
		rs=pst.executeQuery();
		while(rs.next()) {
			status=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return status;	
}
public static synchronized String getExam(String user_name) {
	boolean status=false;
	String exam_name="";
	String query="select exam_details from student_details where user_name=?";
	Connection con=DBConnections.connect();
	ResultSet rs=null;
	PreparedStatement pst;
	try {
		pst = con.prepareStatement(query);
		pst.setString(1,user_name);
		rs=pst.executeQuery();
		while(rs.next()) {
			exam_name=rs.getString(1);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return exam_name;	

}
}

