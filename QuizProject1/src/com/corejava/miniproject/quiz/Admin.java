package com.corejava.miniproject.quiz;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
public class Admin {
	
	private static void displayStudentDetails() {
		Scanner scb=new Scanner(System.in);

		System.out.println("======================================================================================================\n");
		System.out.println("-------------------------Welcome to Quiz System Project-----------------------------------------------------------------\\n");
		System.out.println("Login successfully..");		
		System.out.print("\n Choose Menu:");
		System.out.println("1-View student details: ");
			int option=scb.nextInt();
			ResultSet rs=null;
			Students std=new Students();
			ArrayList<Students> stlist=new ArrayList<Students>();

			String query="select * from student_details ";
			switch(option) {
			case 1:
			try {
			rs=DBConnections.getTable(DBConnections.connect(),query);
				
			while(rs.next()) {
					std.setStudent_name(rs.getString("student_name"));
					std.setUser_name(rs.getString("user_name"));
					std.setStudent_id(rs.getString("student_id"));
					std.setExam_details(rs.getString("exam_details"));
					std.setScore(rs.getString("score"));
					std.setGrade(rs.getString("grade"));
					stlist.add(new Students(std.getStudent_name(),std.getUser_name(),std.getStudent_id(),std.getExam_details(),std.getScore(),std.getGrade()));
				}
			Iterator<Students> itr1=stlist.iterator();
			while(itr1.hasNext()) {
				Students st=itr1.next();
				System.out.println("User Name:"+st.getUser_name());
				System.out.println("Student Name:"+st.getStudent_name());
				System.out.println("Studnent ID:"+st.getStudent_id());
				System.out.println("Exam Details:"+st.getExam_details());
				System.out.println("Score:"+st.getScore());
				System.out.println("Grade:"+st.getGrade());
					
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
				break;
				default: Test.display();break;
				
			}
	}
	protected static void admin() 
	{
Admin.displayStudentDetails();
	}


}
