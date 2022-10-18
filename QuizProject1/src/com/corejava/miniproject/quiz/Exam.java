package com.corejava.miniproject.quiz;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Exam implements ExamInterface 
{
	private static int score=0;
	private static String grade=null;
    private static boolean status=false;
    Scanner input=new Scanner(System.in);
    Display dis=new Display();
    ArrayList<Questions> qlist=new ArrayList<Questions>();
    ArrayList<Questions> shuf=new ArrayList<Questions>();
	public static int getScore() {
		return score;
	}
	public static void setScore(int score) {
		Exam.score = score;
	}
	public static String getGrade() {
		return grade;
	}
	public static void setGrade(String grade) {
		Exam.grade = grade;
	}
	public  void startExam(String user_name) 
	{
		
		//Scanner ste=new Scanner(System.in);
		String exam_name=DBConnections.getExam(user_name);
		this.setQuestions(exam_name);
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("Instructions:There are 10 question on Java in this quiz. \n  Each question has four options as 1,2,3,and 4 \n Please choose one of them.");
		char ch;
		System.out.print("Do you want to continue:(Y/y): ");
		ch=input.next().charAt(0);
		while(ch=='y'||ch=='Y'){
	    shuf.addAll(qlist);
		Collections.shuffle(shuf);
        Iterator<Questions> itr=shuf.iterator();
		int i=1,choice=0;
		score=0;
		String [] opt=new String[4];
		while(itr.hasNext()&&i<=10) {
			Questions q=itr.next();
			System.out.println("--------------------------------------------------------------------------------------------------------\n"+"Q "+i +" : "+q.getQuestion());
			System.out.println("Choose the answer :\n1)"+q.getOption1()+"\n2)"+q.getOption2()+" \n3)"+q.getOption3()+"\n4)"+q.getOption4());
			System.out.println("Please enter your choice between 1 to 4 only.\nEnter choice here:");
			
			opt[0]=q.getOption1();
			opt[1]=q.getOption2();
			opt[2]=q.getOption3();
			opt[3]=q.getOption4();
			choice=input.nextInt();
			// check student answer is correct or not
			if(q.getCorrect_ans().equals(opt[choice-1])) {
				score+=1;
				System.out.println("Your answer is correct!\n");
			}else{
				System.err.println("Your answer is wrong!\n");
				System.out.println("Correct answer is:"+q.getCorrect_ans()+"\n");
				
			} 
		
			i++;
			
		}
		grade=this.showResult(user_name,score);
		ch='n';
		}
		this.storeExamDetails(user_name, grade, score);
		dis.menuStudent(user_name);
	}
	
	public  void storeExamDetails(String user_name,String grade,int score) {
		String query="update student_details set score=?,grade=? where user_name=?";
		ResultSet rs=null;
		String scd=Integer.toString(score);
		 int i=DBConnections.updateScore(query,user_name,grade,scd);
		 if(i==1) {
				System.out.println("Marks updated successfully...");
			}else {
				System.err.println("Marks not updated...");
			}
	
	}
	

	@Override
	public String showResult(String user_name,int score) {
		// TODO Auto-generated method stub
		if(score>=8) {
			System.out.println(" A Grade and score is : " +score+" for user :"+user_name);
			grade="A";
		}else if(score>=6&&score<8) {
			grade="B";
			System.out.println(" B Grade and score is : " +score+" for user :"+user_name);
		}else if(score==5) {
			grade="C";
			System.out.println(" C Grade and score is : " +score+" for user :"+user_name);
		}else {
			grade="Fail";
			System.out.println("You are fail! and score is :"+score +" for user :"+user_name);
		}
		return grade;
	}
	@Override
	public void setQuestions(String exam_name) {
		// TODO Auto-generated method stub
		
		ResultSet rs=null;
		Questions quizq=new Questions();
		
		//int j=0;
		//while(j<20) {
			
		String query="select question_no,question,option1,option2,option3,option4,correct_ans from question1 where exam_name=?";
		
		rs=DBConnections.getQuestions(DBConnections.connect(),query,exam_name);
		
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
		//j++;
		//}		
	}
}
	
	

				
			
		
	
	


