package model.dao;

import customexception.CustomException;
import model.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> queryAllOrders() throws CustomException;
    int deleteOrderById(Integer id) throws CustomException;
    int updateOrderById(Integer id) throws CustomException;
    int addNewOrder(Order order) throws CustomException;
    Order searchOrderById(Integer id) throws CustomException;
}
