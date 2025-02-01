/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databasehandlerlab;

/**
 *
 * @author user
 */
public class Student {
    private String studentNumber;
    private String studentFname;
    private String studentMname;
    private String studentLname;
    private String studentSex;
    private String studentBirth;
    private int studentStart;
    private String studentDepartment;
    private int studentUnits;
    private String studentAddress;

    public Student(String studentNumber, String studentFname, String studentMname, String studentLname,
                   String studentSex, String studentBirth, int studentStart, String studentDepartment,
                   int studentUnits, String studentAddress) {
        this.studentNumber = studentNumber;
        this.studentFname = studentFname;
        this.studentMname = studentMname;
        this.studentLname = studentLname;
        this.studentSex = studentSex;
        this.studentBirth = studentBirth;
        this.studentStart = studentStart;
        this.studentDepartment = studentDepartment;
        this.studentUnits = studentUnits;
        this.studentAddress = studentAddress;
    }

    // Getters and setters
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentFname() {
        return studentFname;
    }

    public void setStudentFname(String studentFname) {
        this.studentFname = studentFname;
    }

    public String getStudentMname() {
        return studentMname;
    }

    public void setStudentMname(String studentMname) {
        this.studentMname = studentMname;
    }

    public String getStudentLname() {
        return studentLname;
    }

    public void setStudentLname(String studentLname) {
        this.studentLname = studentLname;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentBirth() {
        return studentBirth;
    }

    public void setStudentBirth(String studentBirth) {
        this.studentBirth = studentBirth;
    }

    public int getStudentStart() {
        return studentStart;
    }

    public void setStudentStart(int studentStart) {
        this.studentStart = studentStart;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public int getStudentUnits() {
        return studentUnits;
    }

    public void setStudentUnits(int studentUnits) {
        this.studentUnits = studentUnits;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }
    
    @Override
    public String toString() {
        return "Student Number: " + studentNumber + "\n" +
                "First Name: " + studentFname + "\n" +
                "Middle Name: " + studentMname + "\n" +
                "Last Name: " + studentLname + "\n" +
                "Sex: " + studentSex + "\n" +
                "Birthday: " + studentBirth + "\n" +
                "Start Year: " + studentStart + "\n" +
                "Department: " + studentDepartment + "\n" +
                "Units: " + studentUnits + "\n" +
                "Address: " + studentAddress + "\n"
                ;
    }
}
