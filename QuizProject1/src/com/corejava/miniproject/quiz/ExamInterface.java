package com.corejava.miniproject.quiz;

public interface ExamInterface {
public abstract void storeExamDetails(String user_name,String grade,int score);
public abstract String showResult(String user_name,int score);
public abstract void setQuestions(String exam_name);

}
