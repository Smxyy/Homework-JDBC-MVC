package controller;

import customexception.CustomException;
import model.dto.OrderDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.OrderService;
import model.service.OrderServiceImpl;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OrderController {
    private static final OrderService orderService = new OrderServiceImpl();
    public List<OrderDto> queryAllOrders() throws CustomException {
        return orderService.queryAllOrders();
    }

    public int addNewOrder() throws CustomException {
        System.out.print("[+] Insert order's name: ");
        String orderName = new Scanner(System.in).nextLine();
        System.out.print("[+] Insert order's description: ");
        String orderDescription = new Scanner(System.in).nextLine();
        System.out.print("[+] Insert customer's ID: ");
        int customerId = new Scanner(System.in).nextInt();
        System.out.print("[+] Insert product's ID: ");
        int productId = new Scanner(System.in).nextInt();
        return orderService.addNewOrder(Order.builder()
                .id(1)
                .orderName(orderName)
                .orderDescription(orderDescription)
                .orderedAt(Date.valueOf(LocalDate.now()))
                .customer(Customer.builder()
                        .id(customerId)
                        .build())
                .productList(new ArrayList<>(
                        List.of(Product.builder()
                                .id(productId)
                                .build())
                ))
                .build());
    }

    public int updateOrder() throws CustomException {
        System.out.print("[+] Insert order's ID to update: ");
        return orderService.updateOrderById(new Scanner(System.in).nextInt());
    }

    public int deleteOrder() throws CustomException {
        System.out.print("[+] Insert order's ID to delete: ");
       return orderService.deleteOrderById(new Scanner(System.in).nextInt());
    }
}

