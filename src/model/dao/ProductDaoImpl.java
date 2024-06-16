package model.dao;

import customexception.CustomException;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao {
    DatabaseConnectionManager driverManager = new DatabaseConnectionManager();
    @Override
    public List<Product> queryAllProducts() throws CustomException {
        String sql = """
                SELECT * FROM "product"
                """;
        try(
                Connection connection = driverManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ){
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_code"),
                        resultSet.getBoolean("is_deleted"),
                        resultSet.getDate("imported_at"),
                        resultSet.getDate("expired_at"),
                        resultSet.getString("product_description")
                ));
            }
            return productList;
        }catch (SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());   
        }
    }

    @Override
    public int addNewProduct(Product product) throws CustomException {
        String sql = """
                INSERT INTO product (id, product_name, product_code, is_deleted, imported_at, expired_at, product_description)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductCode());
            preparedStatement.setBoolean(4, product.getIsDeleted());
            preparedStatement.setDate(5, product.getImportedDate());
            preparedStatement.setDate(6, product.getExpiredDate());
            preparedStatement.setString(7, product.getProductDescription());
            return preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }

    @Override
    public int updateProductById(Integer id) throws CustomException {
        String sql = """
                UPDATE "product" 
                SET product_name=?, product_code=?, product_description=?
                WHERE id=?
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            Product product = searchProductById(id);
            if(product != null){
                System.out.print("Enter new product name: ");
                preparedStatement.setString(1, new Scanner(System.in).nextLine());
                System.out.print("Enter new product code: ");
                preparedStatement.setString(2, new Scanner(System.in).nextLine());
                System.out.print("Enter new description: ");
                preparedStatement.setString(3, new Scanner(System.in).nextLine());
                preparedStatement.setInt(4, id);
                return preparedStatement.executeUpdate();
            }
            else{
                throw new CustomException("[!] Product not found");
            }
        }catch(SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }

    @Override
    public int deleteProductById(Integer id) throws CustomException {
        String sql = """
                DELETE FROM "product" WHERE id=?
                """;
        try(
                Connection connection = driverManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, id);
            //String message = rowAffected > 0 ? "Product deleted successfully" : "Failed to delete product with id ";
            return preparedStatement.executeUpdate();
        }catch(SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }

    @Override
    public Product searchProductById(Integer id) throws CustomException {
        String sql = """
                SELECT * FROM "product" WHERE id = ?
                """;
        try(
                Connection conn = driverManager.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product = new Product();

            while (resultSet.next()) {
                product = Product.builder()
                        .id(resultSet.getInt("id"))
                        .productName(resultSet.getString("product_name"))
                        .productName(resultSet.getString("product_code"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .importedDate(resultSet.getDate("imported_at"))
                        .expiredDate(resultSet.getDate("expired_at"))
                        .productDescription(resultSet.getString("product_description"))
                        .build();
            }
            return product;
        }catch (SQLException sqlException){
            throw new CustomException("Error: " + sqlException.getMessage());
        }
    }
}
