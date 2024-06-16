package mapper;

import model.dto.CustomerDto;
import model.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapCustomerToCustomerDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .bio(customer.getBio())
                .build();
    }
}
