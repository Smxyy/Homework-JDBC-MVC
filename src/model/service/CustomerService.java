package model.service;

import customexception.CustomException;
import model.dto.CustomerDto;
import model.entity.Customer;
import java.util.List;

public interface CustomerService  {
    List<CustomerDto> queryAllCustomers() throws CustomException;
    int addNewCustomer(Customer customer) throws CustomException;
    int updateCustomerById(Integer id) throws CustomException;
    int deleteCustomerById(Integer id) throws CustomException;
}
