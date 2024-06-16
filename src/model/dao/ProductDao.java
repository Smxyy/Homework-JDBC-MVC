package model.dao;

import customexception.CustomException;
import model.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> queryAllProducts() throws CustomException;
    int addNewProduct(Product product) throws CustomException;
    int updateProductById(Integer id) throws CustomException;
    int deleteProductById(Integer id) throws CustomException;
    Product searchProductById(Integer id) throws CustomException;
}
