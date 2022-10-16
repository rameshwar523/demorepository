package com.corejava.miniproject.quiz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {
	
	public static void clearConsole()
	{
		Sysou
    
	}
	
public static void display() {	
	String user_name="";
	String password="";
	int option=0;
	Scanner sc=new Scanner(System.in);
	Scanner sc1=new Scanner(System.in);
	System.out.flush();
	
	System.out.println("========================================================================================================================\n");
	System.out.println("-------------------------Welcome to Quiz System Project-----------------------------------------------------------------\n\n");
	System.out.println("\n 1)Sign (Admin)\n 2)Sign (Student)\n 3-Sign Up (Not registered students)\n Choose option:");
	option=sc1.nextInt();
	switch(option) {
	case 1:
		System.out.print(" Please enter User Name: ");
		user_name=sc.nextLine();
		System.out.print(" Please enter Password: ");
		password=sc.nextLine();
		Login.logAdmin(user_name,password);
         break;
	case 2:
		System.out.print(" Please enter User Name: ");
		user_name=sc.nextLine();
		System.out.print(" Please enter Password: ");
		password=sc.nextLine();
		Login.logStudent(user_name,password);
         break;
		default:System.out.println("Wrong option selected");
		
	}
	
	
}
public static void main(String[] args) {
	
Test.display();

}	
}
