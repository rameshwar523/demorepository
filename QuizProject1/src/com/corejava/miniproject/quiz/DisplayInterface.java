package com.corejava.miniproject.quiz;

public interface DisplayInterface {
public abstract void menuMain();
public abstract void menuAdmin();
public abstract void menuChoice(int choice);
public abstract void menuChoiceAdmin(int choice);
public abstract void getStudent(String exam_name);
public abstract Students disRegForm();
public abstract void menuStudent(String user_name);
public abstract void menuChoiceStudent(String user_name,int choice);
public abstract String showStudentExamwise();

}
