package mapper;

import model.dto.ProductDto;
import model.entity.Product;

public class ProductMapper {
    public static ProductDto mapProductToProductDto(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDto.builder()
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .importedDate(product.getImportedDate())
                .expiredDate(product.getExpiredDate())
                .build();
    }
}
