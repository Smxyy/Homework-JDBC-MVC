package model.service;

import customexception.CustomException;
import model.dto.OrderDto;
import model.entity.Order;
import java.util.List;

public interface OrderService {
    List<OrderDto> queryAllOrders() throws CustomException;
    int addNewOrder(Order order) throws CustomException;
    int updateOrderById(Integer id) throws CustomException;
    int deleteOrderById(Integer id) throws CustomException;
}