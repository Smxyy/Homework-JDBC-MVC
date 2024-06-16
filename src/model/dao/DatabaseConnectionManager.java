package model.dao;

import customexception.CustomException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager{
    final String url = "jdbc:postgresql://localhost:5433/postgres";
    final String user = "postgres";
    final String password = "Smey0204";
    public Connection getConnection() throws CustomException {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlException) {
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }
}
