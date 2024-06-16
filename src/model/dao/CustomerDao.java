package model.dao;

import customexception.CustomException;
import model.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllCustomers() throws CustomException;
    int deleteCustomerById(Integer id) throws CustomException;
    int updateCustomerById(Integer id) throws CustomException;
    int addNewCustomer(Customer customer) throws CustomException;
    Customer searchCustomerById(Integer id) throws CustomException;
}
