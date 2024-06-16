package model.service;

import customexception.CustomException;
import mapper.OrderMapper;
import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import model.dto.OrderDto;
import model.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<OrderDto> queryAllOrders() throws CustomException {
        try {
            List<Order> orders = orderDao.queryAllOrders();
            if(!(orders.isEmpty())){
                return orderDao.queryAllOrders().stream()
                        .map(OrderMapper::mapOrderToOrderDto).toList();
            }else {
                return new ArrayList<>();
            }
        }catch (Exception exception){
            throw new CustomException("Error: " + exception.getMessage());
        }
    }

    @Override
    public int addNewOrder(Order order) throws CustomException {
        return orderDao.addNewOrder(order);
    }

    @Override
    public int updateOrderById(Integer id) throws CustomException {
        return orderDao.updateOrderById(id);
    }

    @Override
    public int deleteOrderById(Integer id) throws CustomException {
        return orderDao.deleteOrderById(id);
    }
}
