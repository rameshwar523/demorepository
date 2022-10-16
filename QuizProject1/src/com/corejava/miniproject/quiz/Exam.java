package com.corejava.miniproject.quiz;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Exam 
{
	
	public static void startExam(String user_name) 
	{
		Random r=new Random();
		Scanner input=new Scanner(System.in);
		Scanner ste=new Scanner(System.in);
		ResultSet rs=null;
		Questions quizq=new Questions();
		ArrayList<Questions> qlist=new ArrayList<Questions>();
		
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("Instructions:There are 10 question on Java in this quiz. \n  Each question has four options as 1,2,3,and 4 \n Please choose one of them.");
		
		char ch;
		System.out.print("Do you want to start the Exam press(y/Y): ");
		ch=input.next().charAt(0);
		while(ch=='y'||ch=='Y'){
			
		int j=0;
		while(j<10) {
			int num=r.nextInt(20)+1;
		String query="select question_no,question,option1,option2,option3,option4,correct_ans from question1 where question_no="+num;
		rs=DBConnections.getTable(DBConnections.connect(),query);
		
		try {
			
			while(rs.next())
			{
				quizq.setQuestion(rs.getString(2));
				quizq.setOption1(rs.getString(3));
				quizq.setOption2(rs.getString(4));
				quizq.setOption3(rs.getString(5));
				quizq.setOption4(rs.getString(6));
				quizq.setCorrect_ans(rs.getString(7));
				qlist.add(new Questions(quizq.getQuestion(),quizq.getOption1(),quizq.getOption2(),quizq.getOption3(),quizq.getOption4(),quizq.getCorrect_ans()));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		j++;
		}		
	

		
		Iterator<Questions> itr=qlist.iterator();
		/*while(itr.hasNext()) {
			Questions questions=itr.next();
			System.out.println("Q : "+questions.getQuestion());
		}*/
		
		int i=1,choice=0,correct=0;
		String [] opt=new String[4];
		while(itr.hasNext()) {
			Questions q=itr.next();
			System.out.println("Q "+i +" : "+q.getQuestion());
			System.out.println("Choose the answer :\n 1)"+q.getOption1()+"\n2) "+q.getOption2()+" \n3) "+q.getOption3()+"\n4)"+q.getOption4());
			System.out.println("Please enter choice(1 to 4 only) here:");
			
			opt[0]=q.getOption1();
			opt[1]=q.getOption2();
			opt[2]=q.getOption3();
			opt[3]=q.getOption4();
			choice=input.nextInt();
			if(choice>4||choice<0) {
				System.err.println("Wrong option selected. \n Please choose 1 to 4 option :");
			}
			if(q.getCorrect_ans().equals(opt[choice-1])) {
				correct+=1;
				System.out.println("Your answer is correct!\n");
			}else{
				System.err.println("Your answer is wrong!");
				System.out.println("Correct answer is: "+q.getCorrect_ans());
			} 
			i++;
			
		}
		if(correct>=8) {
			System.out.println(" A Grade and score is : " +correct+" for user :"+user_name);
			
		}else if(correct>=6&&correct<8) {
			System.out.println(" B Grade and score is : " +correct+" for user :"+user_name);
		}else if(correct==5) {
			System.out.println(" C Grade and score is : " +correct+" for user :"+user_name);
		}else {
			System.out.println("You are fail! and score is : "+correct +" for user :"+user_name);
		}
		ch='n';
		}
	}
}
	
	

				
			
		
	
	


