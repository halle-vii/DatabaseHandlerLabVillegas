/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databasehandlerlab;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    private static String students = "jdbc:sqlite:/Users/user/Desktop/LAB2/students";

    // Drop and create Students table
    public static void initializeStudents() {
        String dropSql = "DROP TABLE IF EXISTS Students;";
        String createSql = "CREATE TABLE Students ("
                + "student_number TEXT NOT NULL,"
                + "student_fname TEXT NOT NULL,"
                + "student_mname TEXT,"
                + "student_lname TEXT NOT NULL,"
                + "student_sex TEXT NOT NULL,"
                + "student_birth TEXT NOT NULL,"
                + "student_start INTEGER NOT NULL,"
                + "student_department TEXT NOT NULL,"
                + "student_units INTEGER NOT NULL,"
                + "student_address TEXT,"
                + "CONSTRAINT Students_PK PRIMARY KEY (student_number)"
                + ");";

        try (Connection c = DriverManager.getConnection(students);
             PreparedStatement drop = c.prepareStatement(dropSql);
             PreparedStatement create = c.prepareStatement(createSql)) {

            drop.executeUpdate();
            System.out.println("Table dropped successfully (if it existed).");

            create.executeUpdate();
            System.out.println("Table 'Students' created successfully.");
        } catch (SQLException e) {
            System.out.println("Error in table creation: " + e.getMessage());
        }
    }

    // Retrieve a student by student number
    public static Student getStudent(String studentNumber) {
        String sql = "SELECT * FROM Students WHERE student_number = ?;";

        try (Connection c = DriverManager.getConnection(students);
             PreparedStatement select = c.prepareStatement(sql)) {

            select.setString(1, studentNumber);
            ResultSet rs = select.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString("student_number"),
                        rs.getString("student_fname"),
                        rs.getString("student_mname"),
                        rs.getString("student_lname"),
                        rs.getString("student_sex"),
                        rs.getString("student_birth"),
                        rs.getInt("student_start"),
                        rs.getString("student_department"),
                        rs.getInt("student_units"),
                        rs.getString("student_address")
                );
            }
        } catch (SQLException e) {
            System.out.println("Retrieval unsuccessful. " + e.getMessage());
        }
        return null;
    }

    // Retrieve a student by full name
    public static Student getStudent(String studentFname, String studentMname, String studentLname) {
        String sql = "SELECT * FROM Students WHERE student_fname = ? AND student_mname = ? AND student_lname = ?;";

        try (Connection c = DriverManager.getConnection(students);
             PreparedStatement select = c.prepareStatement(sql)) {

            select.setString(1, studentFname);
            select.setString(2, studentMname);
            select.setString(3, studentLname);
            ResultSet rs = select.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString("student_number"),
                        rs.getString("student_fname"),
                        rs.getString("student_mname"),
                        rs.getString("student_lname"),
                        rs.getString("student_sex"),
                        rs.getString("student_birth"),
                        rs.getInt("student_start"),
                        rs.getString("student_department"),
                        rs.getInt("student_units"),
                        rs.getString("student_address")
                );
            }
        } catch (SQLException e) {
            System.out.println("Retrieval unsuccessful. " + e.getMessage());
        }
        return null;
    }

    // Retrieve all students
    public static Student[] getStudents() {
        String sql = "SELECT * FROM Students;";
        try (Connection c = DriverManager.getConnection(students);
             PreparedStatement select = c.prepareStatement(sql);
             ResultSet rs = select.executeQuery()) {
            
            List<Student> studentList = new ArrayList<>();
            while (rs.next()) {
                studentList.add(new Student(
                        rs.getString("student_number"),
                        rs.getString("student_fname"),
                        rs.getString("student_mname"),
                        rs.getString("student_lname"),
                        rs.getString("student_sex"),
                        rs.getString("student_birth"),
                        rs.getInt("student_start"),
                        rs.getString("student_department"),
                        rs.getInt("student_units"),
                        rs.getString("student_address")
                ));
            }
            return studentList.toArray(new Student[0]);

        } catch (SQLException e) {
            System.out.println("Retrieval unsuccessful. " + e.getMessage());
        }
        return new Student[0];
    }


    // Remove a student by student_number
    public static boolean removeStudent(String studentNumber) {
        String sql = "DELETE FROM Students WHERE student_number = ?;";

        try (Connection c = DriverManager.getConnection(students);
             PreparedStatement delete = c.prepareStatement(sql)) {

            delete.setString(1, studentNumber);
            int result = delete.executeUpdate();

            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error removing student: " + e.getMessage());
        }
        return false;
    }

    // Retrieve students by year (assuming student_number encodes year)
    public static Student[] getStudentsByYear(int year) {
        String sql = "SELECT * FROM Students WHERE student_number LIKE ?;";
        try (Connection c = DriverManager.getConnection(students);
             PreparedStatement select = c.prepareStatement(sql)) {

            select.setString(1, year + "010____");
            ResultSet rs = select.executeQuery();

            List<Student> studentList = new ArrayList<>();
            while (rs.next()) {
                studentList.add(new Student(
                        rs.getString("student_number"),
                        rs.getString("student_fname"),
                        rs.getString("student_mname"),
                        rs.getString("student_lname"),
                        rs.getString("student_sex"),
                        rs.getString("student_birth"),
                        rs.getInt("student_start"),
                        rs.getString("student_department"),
                        rs.getInt("student_units"),
                        rs.getString("student_address")
                ));
            }

            return studentList.toArray(new Student[0]);

        } catch (SQLException e) {
            System.out.println("Retrieval unsuccessful. " + e.getMessage());
        }
        return new Student[0];
    }



    // Update student information
    public static boolean updateStudentInfo(String studentNumber, Student studentInfo) {
        String sql = "UPDATE Students SET student_fname = ?, student_mname = ?, student_lname = ?, student_department = ?, student_address = ? WHERE student_number = ?;";

        try (Connection c = DriverManager.getConnection(students);
             PreparedStatement update = c.prepareStatement(sql)) {

            update.setString(1, studentInfo.getStudentFname());
            update.setString(2, studentInfo.getStudentMname());
            update.setString(3, studentInfo.getStudentLname());
            update.setString(4, studentInfo.getStudentDepartment());
            update.setString(5, studentInfo.getStudentAddress());
            update.setString(6, studentNumber);

            int result = update.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Update unsuccessful. " + e.getMessage());
        }
        return false;
    }

    // Update student units
    public static boolean updateStudentUnits(int subtractedUnits, String studentNumber) {
        String sql = "UPDATE Students SET student_units = student_units - ? WHERE student_number = ?;";

        try (Connection c = DriverManager.getConnection(students);
             PreparedStatement update = c.prepareStatement(sql)) {

            update.setInt(1, subtractedUnits);
            update.setString(2, studentNumber);

            int result = update.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error updating student units: " + e.getMessage());
        }
        return false;
    }

    // Insert a new student
    public static boolean insertStudent(Student newStudent) {
        String sql = "INSERT INTO Students (student_number, student_fname, student_mname, student_lname, student_sex, student_birth, student_start, student_department, student_units, student_address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection c = DriverManager.getConnection(students);
             PreparedStatement insert = c.prepareStatement(sql)) {

            insert.setString(1, newStudent.getStudentNumber());
            insert.setString(2, newStudent.getStudentFname());
            insert.setString(3, newStudent.getStudentMname());
            insert.setString(4, newStudent.getStudentLname());
            insert.setString(5, newStudent.getStudentSex());
            insert.setString(6, newStudent.getStudentBirth());
            insert.setInt(7, newStudent.getStudentStart());
            insert.setString(8, newStudent.getStudentDepartment());
            insert.setInt(9, newStudent.getStudentUnits());
            insert.setString(10, newStudent.getStudentAddress());

            int result = insert.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
        return false;
    }
}
