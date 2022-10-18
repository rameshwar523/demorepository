package com.corejava.miniproject.quiz;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Display implements DisplayInterface {
	Scanner scb=new Scanner(System.in);
	int option;
	/* this method displays the menu */
	@Override
	public  void menuMain() {
		Scanner scb=new Scanner(System.in);
		System.out.println("*************************************************************************************************************************\n");
		System.out.println("-------------------------Welcome to Quiz System Project-----------------------------------------------------------------\n");
		System.out.println("\n1.Sign (Admin)\n2.Sign (Student)\n3.Sign Up (Not registered students)\n4.Exit \nPress here your choice:");
		option=scb.nextInt();
		// validation for input
         if(option<=4&&option>0) {
			this.menuChoice(option);
			}else {
				System.out.println("Wrong input.\n Try agin with correction input.");
				this.menuMain();
			}
	}
	@Override
	public  void menuChoice(int choice) {
		String user_name="";
		String password="";
		Scanner scb=new Scanner(System.in);
		
		switch(choice) {
		case 1:
			System.out.print(" Please enter User Name: ");
			user_name=scb.nextLine();
			System.out.print(" Please enter Password: ");
			password=scb.nextLine();
			Login.logAdmin(user_name,password);
	         break;
		case 2:
			System.out.print(" Please enter User Name: ");
			user_name=scb.nextLine();
			System.out.print(" Please enter Password: ");
			password=scb.nextLine();
			Login.logStudent(user_name,password);
	         break;
		case 3:Login l2=new Login();
		l2.registerStd();
			break;
		case 4:System.out.println("Successfully Exit...");
		    break;
			default:System.out.println("Wrong option selected");
        	break;
		}
	
	}
	@Override
	public void menuAdmin() {
		for(double i=0;i<100;i=i+0.10);
		Scanner scb=new Scanner(System.in);
		System.out.println("=========================================================================================================================\n");
		System.out.println("-------------------------Welcome to Quiz System Project------------------------------------------------------------------\n");
		System.out.println("***********************************Welcome Admin**************************************************************************");		
		System.out.print("\n+++++++++++++++++++++++++++++++++++Admin Menu++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("\n1.View student details.");
		System.out.print("\n2.Main menu.");
		System.out.print("\nChoose Menu:");
		option=scb.nextInt();
		if(option<=2&&option>0) {
		this.menuChoiceAdmin(option);
		}else {
			System.out.println("Wrong input.\n Try agin with correction input.");
			this.menuAdmin();
		}
	}
	@Override
	public void menuChoiceAdmin(int choice) {
		
		switch(choice) {
		case 1:
			this.getStudent(this.showStudentExamwise());
		       break;
		case 2:this.menuMain();
		break;
		default: this.menuMain();
		break;
		}
	}
	/*this method is written for display the students who are register and given exam */
	@Override
	public void getStudent(String exam_name) {
		
		ResultSet rs=null;
		Students std=new Students();
		ArrayList<Students> stlist=new ArrayList<Students>();
        String query="select * from student_details where exam_details=?";
        rs=DBConnections.getTable(DBConnections.connect(),query,exam_name);
		try {
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
		int i=0;
		while(itr1.hasNext()) {
			Students st=itr1.next();
			i++;
			System.out.println("--------------------------Record number:"+i+"---------------------------------------------------------");
			System.out.println("Studnent ID:"+st.getStudent_id()+"  Student Name:"+st.getStudent_name()+"  User Name:"+st.getUser_name());
			System.out.println();
			System.out.println("Exam Details:"+st.getExam_details()+"  Score:"+st.getScore()+"  Grade:"+st.getGrade());
			System.out.println();
			
		}
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.println("-----------Total number of Record(s) : "+i+"---------------------------------------------------------");
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.menuAdmin();
	}
	@Override
	public Students disRegForm() {
		// Here new student registered
		Students std=new Students();
		Scanner sbh=new Scanner(System.in);
		Scanner inputExam=new Scanner(System.in);
		System.out.print("\nPlease enter User Name:");
		String userName=sbh.nextLine();
		if(DBConnections.checkStudentRegister(userName)) {
			System.err.println("User name already exist.\nPlease try another one.");
			this.disRegForm();
		}
		std.setUser_name(userName);
		System.out.print("\nPlease enter Name:");
		std.setStudent_name(sbh.nextLine());
		System.out.print("\nPlease enter Password:");
		std.setPassword(sbh.nextLine());
		System.out.print("\nPlease enter Student id:");
		std.setStudent_id(sbh.nextLine().toString());
		System.out.println("\nPlease enter Exam Name:\n1.Java.\n2.C.\n3.C++.");
		int ex=inputExam.nextInt();
		if(ex>0&&ex<=3) {
			if(ex==1) {
				std.setExam_details("Java");
			}
			else if(ex==2) {
				std.setExam_details("C");
			}
			else {
				std.setExam_details("C++");
			}
		}
		
		return std;
	}
	@Override
	public void menuStudent(String user_name) {
		//menu options for student
		Scanner scb=new Scanner(System.in);
		System.out.println("-------------------------Welcome to Quiz System Project-----------------------------------------------------------------\n");
		System.out.println("Login successfully..******************************************************************************************Signed:"+user_name);		
		System.out.println("Student Menu options.");
		System.out.print("\n1.Start the Exam.");
		System.out.print("\n2.Logout. ");
		System.out.print("\nChoose Menu:");
		int option=scb.nextInt();
		
		if(option<=3&&option>0) {
			this.menuChoiceStudent(user_name,option);
			}else {
				System.out.println("Wrong input.\n Try agin with correction input.");
				this.menuStudent(user_name);
			}
		scb.close();
	}
	@Override
	public void menuChoiceStudent(String user_name,int choice) {
		// menu selection for student
		switch(choice) {
		case 1:if(DBConnections.checkExamStatus(user_name)) {
			System.err.println("You have already given same exam!");
		}else{
			Exam e=new Exam();
			e.startExam(user_name);
		}
		this.menuMain();
		break;
		case 2:this.menuMain();
		break;
		default:System.out.println("Wrong selection so exit!");
		break;
		}
	}
	@Override
	public String showStudentExamwise() {
		Scanner sh=new Scanner(System.in);
		String exam_name="";
		System.out.println("----------------------------------------------Welcome to Quiz System Project ---------------------------------------");
		System.out.println("Menu\n1.Java.\n2.C++.\n3.C.\n Choose option:");
		option=sh.nextInt();
		switch(option) {
		case 1:exam_name="Java";
		break;
		case 2:exam_name="C++";
		break;
		case 3:exam_name="C";
		break;
		default:break;
		}
		return exam_name;
	}
	}


