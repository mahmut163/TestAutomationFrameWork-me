package com.unitedcoder.classconcepts.package1;


public class Student{

    private String firstName;
    private String lastName;
    private String gender;
    String major;

    public Student() {
    }

    public Student(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    protected String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public static void main(String[] args) {
        Student student=new Student();
        student.major="";
        student.getLastName();
    }
}
