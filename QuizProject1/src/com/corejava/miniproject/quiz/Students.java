package com.corejava.miniproject.quiz;

import java.sql.ResultSet;
import java.util.Scanner;

public class Students {
	private String user_name;
	private String student_id;
	private String exam_details;
	private String score;
	private String student_name;
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getExam_details() {
		return exam_details;
	}
	public void setExam_details(String exam_details) {
		this.exam_details = exam_details;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	private String grade;
	public Students() {
		
	}
	public Students(String student_name,String user_name,String student_id,String exam_details,String score,String grade) {
		this.student_name=student_name;
		this.user_name=user_name;
		this.student_id=student_id;
		this.exam_details=exam_details;
		this.score=score;
		this.grade=grade;
	}
	@Override
	public String toString() {
		return "Students [user_name=" + user_name + ", student_id=" + student_id + ", exam_details=" + exam_details
				+ ", score=" + score + ", student_name=" + student_name + ", grade=" + grade + "]";
	}
}
