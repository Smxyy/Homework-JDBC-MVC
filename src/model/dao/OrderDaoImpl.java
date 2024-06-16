package model.dao;

import customexception.CustomException;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl implements OrderDao {
    final DatabaseConnectionManager driverManager = new DatabaseConnectionManager();
    @Override
    public List<Order> queryAllOrders() throws CustomException {
        String sql = """
                SELECT * FROM "order"
                INNER JOIN public.customer c on c.id = "order".cus_id
                """;
        try(
                Connection connection = driverManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ){
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                orderList.add(Order
                        .builder()
                        .id(resultSet.getInt("id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderDescription(resultSet.getString("order_description"))
                        .orderedAt(resultSet.getDate("ordered_at"))
                        .customer(Customer
                                .builder()
                                .id(resultSet.getInt("cus_id"))
                                .name(resultSet.getString("name"))
                                .email(resultSet.getString("email"))
                                .createdDate(resultSet.getDate("created_date"))
                                .build())
                        .build()
                );
            }
            return orderList;
        }catch (SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }

    @Override
    public int deleteOrderById(Integer id) throws CustomException {
        String sql = """
                DELETE FROM "order" WHERE id = ?
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement pre = connection.prepareStatement(sql)
        ){
            pre.setInt(1, id);
            int rowAffected = pre.executeUpdate();
            return rowAffected;
        }catch (SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }

    @Override
    public int updateOrderById(Integer id) throws CustomException {
        String sql = """
                UPDATE "order" SET order_name = ?, order_description = ? WHERE id = ?
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
        ){
            Order order = searchOrderById(id);
            if (order == null) {
                throw new CustomException("No order in the table");
            }else{
                System.out.print("New Order Name:");
                pre.setString(1,new Scanner(System.in).next());
                System.out.print("New Order Description:");
                pre.setString(2,new Scanner(System.in).next());
                pre.setInt(3,id);
                return pre.executeUpdate();
            }
        }catch (SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }

    @Override
    public int addNewOrder(Order order) throws CustomException {
        String sql = """
                INSERT INTO "order" (id, order_name, order_description, cus_id, ordered_at)
                VALUES (?, ?, ?, ?, ?)
                """;
        String sql1 = """
                INSERT INTO "product_order" (pro_id, order_id)
                VALUES (?,?)
                """;
        try(
                Connection connection = driverManager.getConnection() ;
                PreparedStatement pre = connection.prepareStatement(sql);
                PreparedStatement pre1 = connection.prepareStatement(sql1);
        ){
            pre.setInt(1, order.getId());
            pre.setString(2, order.getOrderName());
            pre.setString(3, order.getOrderDescription());
            pre.setInt(4, order.getCustomer().getId());
            pre.setDate(5,order.getOrderedAt());
            for(Product product : order.getProductList()){
                pre1.setInt(1, product.getId());
                pre1.setInt(2, order.getId());
            }
            int rowAffected = pre.executeUpdate();
            pre1.executeUpdate();
            return rowAffected;
        }catch (SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }

    @Override
    public Order searchOrderById(Integer id) throws CustomException {
        String sql = """
                SELECT * FROM "order" WHERE id = ?
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement pre = connection.prepareStatement(sql);
        ) {
            pre.setInt(1, id);
            ResultSet resultSet = pre.executeQuery();
            Order order = null;
            while (resultSet.next()) {
                order = Order
                        .builder()
                        .id(resultSet.getInt("id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderDescription(resultSet.getString("order_description"))
                        .orderedAt(resultSet.getDate("ordered_at"))
                        .build();
            }
            return order;
        }catch (SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }
}
