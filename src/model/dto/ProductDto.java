package model.dto;

import lombok.Builder;
import java.sql.Date;

@Builder
public record ProductDto(
        String productName,
        Date importedDate,
        Date expiredDate,
        String productDescription
) {
}
