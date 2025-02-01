/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databasehandlerlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class JDBCStartup {
    private Connection conn;

    public JDBCStartup(String database) {
        String connStr = "jdbc:sqlite:" + database; 
        try {
            conn = DriverManager.getConnection(connStr);
            System.out.println("Connected to database successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
