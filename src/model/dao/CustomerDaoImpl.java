package model.dao;

import customexception.CustomException;
import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao {
    final DatabaseConnectionManager driverManager= new DatabaseConnectionManager();
    @Override
    public List<Customer> queryAllCustomers() throws CustomException{
        String sql = """
                SELECT * FROM "customer"
                """;
        try(
                Connection connection = driverManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            List<Customer> customerList = new ArrayList<>();
            while(resultSet.next()){
                customerList.add(new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("is_deleted"),
                        resultSet.getDate("created_date"),
                        resultSet.getString("bio")
                ));
            }
            return customerList;
        }catch (SQLException sqlException) {
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }
    @Override
    public int deleteCustomerById(Integer id) throws CustomException{
        String sql = """
                DELETE FROM "customer" WHERE id = ?
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        )
        {
            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected;
        }catch (SQLException sqlException) {
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }
    @Override
    public int updateCustomerById(Integer id) throws CustomException{
        String sql = """
                UPDATE "customer" 
                SET name = ? , email = ?
                WHERE id = ?
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            Customer customer = searchCustomerById(id);
            if(customer == null){
                throw new CustomException("No Customer in the table");
                //return -1;
            }else {
                System.out.print("[+] Insert name: ");
                preparedStatement.setString(1, new Scanner(System.in).next());
                System.out.print("[+] Insert email: ");
                preparedStatement.setString(2, new Scanner(System.in).next());
                preparedStatement.setInt(3, id);
                int rowAffected = preparedStatement.executeUpdate();
                return rowAffected;
            }
        }catch (SQLException sqlException) {
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }
    @Override
    public int addNewCustomer(Customer customer) throws CustomException{
        String sql = """
                INSERT INTO "customer" (name, email, password, is_deleted, created_date, bio) 
                VALUES(?, ?, ?, ?, ?, ?)
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
        ){
            pre.setString(1, customer.getName());
            pre.setString(2, customer.getEmail());
            pre.setString(3, customer.getPassword());
            pre.setBoolean(4, customer.getIsDeleted());
            pre.setDate(5, customer.getCreatedDate());
            pre.setString(6, customer.getBio());
            int rowAffected = pre.executeUpdate();
            return rowAffected;
        }catch (SQLException sqlException) {
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }
    @Override
    public Customer searchCustomerById(Integer id) throws CustomException{
        String sql = """
                SELECT * FROM "customer" WHERE id = ?
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement preparedStatement
                        = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Customer customer = null;
            while(resultSet.next()) {
                customer = Customer
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .createdDate(resultSet.getDate("created_date"))
                        .bio(resultSet.getString("bio"))
                        .build();
            }
            return customer;
        }catch (SQLException sqlException) {
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }
}
