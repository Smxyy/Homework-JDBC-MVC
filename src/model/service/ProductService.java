package model.service;

import customexception.CustomException;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> queryAllProducts() throws CustomException;
    int addNewProduct(Product product) throws CustomException;
    int updateProductById(Integer id) throws CustomException;
    int deleteProductById(Integer id) throws CustomException;
}
