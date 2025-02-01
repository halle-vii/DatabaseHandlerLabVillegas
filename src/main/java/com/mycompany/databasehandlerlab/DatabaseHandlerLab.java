/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.databasehandlerlab;

import java.sql.*;

/**
 *
 * @author user
 */
public class DatabaseHandlerLab {

    public static void main(String[] args) {
        JDBCStartup db = new JDBCStartup("/Users/user/Desktop/LAB2/students");
        Connection c = db.getConnection();

        if (c != null) {
            System.out.println("Database connection is active.");
        }

        // Initialize database (drop and create table)
        System.out.println("Initializing database...");
        DatabaseHandler.initializeStudents();

        // Insert records
        System.out.println("\nInserting records...");
        Student student1 = new Student("20240101111", "Luke", "Robert", "Hemmings", "M", "2000-01-01", 2018, "CICS", 30, "123 Main St.");
        Student student2 = new Student("20200102122", "Hayley", "Nichole", "Williams", "F", "1999-05-15", 2017, "ARTS_AND_LETTERS", 32, "456 Oak St.");
        Student student3 = new Student("20240103133", "Ashton", "Fletcher", "Irwin", "M", "2001-02-20", 2019, "COMMERCE", 28, "789 Pine St.");
        DatabaseHandler.insertStudent(student1);
        DatabaseHandler.insertStudent(student2);
        DatabaseHandler.insertStudent(student3);

        // Get all students
        System.out.println("\nGetting all students...");
        Student[] allStudents = DatabaseHandler.getStudents();
        for (Student student : allStudents) {
            System.out.println(student);
        }

        // Retrieve a student by student number
        System.out.println("\nRetrieving student by ID (20240101111)...");
        Student retrievedStudentById = DatabaseHandler.getStudent("20240101111");
        if (retrievedStudentById != null) {
            System.out.println(retrievedStudentById);
        } else {
            System.out.println("Student not found.");
        }

        // Retrieve a student by full name
        System.out.println("\nRetrieving student by full name (Hayley Nichole Williams)...");
        Student retrievedStudentByName = DatabaseHandler.getStudent("Hayley", "Nichole", "Williams");
        if (retrievedStudentByName != null) {
            System.out.println(retrievedStudentByName);
        } else {
            System.out.println("Student not found.");
        }

        // Update a student's information
        System.out.println("\nUpdating student (20240103133)...");
        Student updatedStudent = new Student("20240103133", "Michael", "Gordon", "Clifford", "M", "2000-01-01", 2019, "FOE", 30, "987 Elm St.");
        boolean updateResult = DatabaseHandler.updateStudentInfo("20240103133", updatedStudent);
        System.out.println(updateResult ? "Student updated successfully." : "Student update failed.");

        // Update student units
        System.out.println("\nUpdating student units (20200102122)...");
        boolean unitsUpdateResult = DatabaseHandler.updateStudentUnits(35, "20200102122");
        System.out.println(unitsUpdateResult ? "Student units updated successfully." : "Student units update failed.");

        // Delete a student
        System.out.println("\nDeleting student (20240103133)...");
        boolean deleteResult = DatabaseHandler.removeStudent("20240103133");
        System.out.println(deleteResult ? "Student deleted successfully." : "Student deletion failed.");

        // Retrieve students who started in 2020
        System.out.println("\nRetrieving students who started in 2020...");
        Student[] studentsByYear = DatabaseHandler.getStudentsByYear(2020);
        if (studentsByYear.length > 0) {
            for (Student student : studentsByYear) {
                System.out.println(student);
            }
        } else {
            System.out.println("No students found.");
        }
    }
}
