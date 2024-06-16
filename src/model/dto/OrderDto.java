package model.dto;

import lombok.Builder;
import model.entity.Customer;
import model.entity.Product;

import java.sql.Date;
import java.util.List;

@Builder
public record OrderDto(
        Integer id,
        String orderName,
        String orderDescription,
        CustomerDto customerDto,
        Date orderedAt
) {
}
