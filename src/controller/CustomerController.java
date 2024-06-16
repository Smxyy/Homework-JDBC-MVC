package controller;

import customexception.CustomException;
import model.dto.CustomerDto;
import model.entity.Customer;
import model.service.CustomerService;
import model.service.CustomerServiceImpl;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private final CustomerService customerService = new CustomerServiceImpl();
    public List<CustomerDto> queryAllCustomers() throws CustomException {
        return customerService.queryAllCustomers();
    }

    public int addNewCustomer() throws CustomException{
        System.out.print("[+] Insert customer's name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.print("[+] Insert customer's email: ");
        String email = new Scanner(System.in).nextLine();
        System.out.print("[+] Insert customer's password: ");
        String password = new Scanner(System.in).nextLine();
        System.out.print("[+] Insert customer's bio: ");
        String bio = new Scanner(System.in).nextLine();
        return customerService.addNewCustomer(Customer.builder()
                            .id(1)
                            .name(name)
                            .email(email)
                            .password(password)
                            .bio(bio)
                            .isDeleted(false)
                            .createdDate(Date.valueOf(LocalDate.now()))
                .build());
    }

    public int updateCustomerByID() throws CustomException{
        System.out.print("[+] Insert customer's ID to update: ");
        return customerService.updateCustomerById(new Scanner(System.in).nextInt());
    }

    public int deleteCustomerById() throws CustomException{
        System.out.print("[+] Insert customer's ID to delete: ");
        return customerService.deleteCustomerById(new Scanner(System.in).nextInt());
    }

}
