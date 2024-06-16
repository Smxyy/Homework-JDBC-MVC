package model.service;

import customexception.CustomException;
import mapper.CustomerMapper;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public List<CustomerDto> queryAllCustomers() throws CustomException {
        try {
            List<Customer> customers = customerDao.queryAllCustomers();
            if (!(customers.isEmpty())) {
                return customerDao.queryAllCustomers().stream().map(CustomerMapper::mapCustomerToCustomerDto).toList();
            } else {
                return new ArrayList<>();
            }
        }catch (Exception exception){
            throw new CustomException("Error: " + exception.getMessage());
        }
    }

    @Override
    public int addNewCustomer(Customer customer) throws CustomException {
        return customerDao.addNewCustomer(customer);
    }

    @Override
    public int updateCustomerById(Integer id) throws CustomException {
        return customerDao.updateCustomerById(id);
    }

    @Override
    public int deleteCustomerById(Integer id) throws CustomException {
        return customerDao.deleteCustomerById(id);
    }
}
