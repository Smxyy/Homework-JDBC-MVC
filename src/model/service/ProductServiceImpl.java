package model.service;

import customexception.CustomException;
import mapper.ProductMapper;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<ProductDto> queryAllProducts() throws CustomException {
        try {
            List<Product> products = productDao.queryAllProducts();
            if (!(products.isEmpty())) {
                return productDao.queryAllProducts().stream().map(ProductMapper::mapProductToProductDto).toList();
            }else {
                return new ArrayList<>();
            }
        }catch (Exception exception){
            throw new CustomException("Error: " + exception.getMessage());
        }
    }

    @Override
    public int addNewProduct(Product product) throws CustomException {
        return productDao.addNewProduct(product);
    }

    @Override
    public int updateProductById(Integer id) throws CustomException {
        return productDao.updateProductById(id);
    }

    @Override
    public int deleteProductById(Integer id) throws CustomException{
        return productDao.deleteProductById(id);
    }
}
