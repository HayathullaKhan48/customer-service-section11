package com.customer.service.section11.mapper;

import com.customer.service.section11.entity.CustomerModel;
import com.customer.service.section11.enums.CustomerStatus;
import com.customer.service.section11.request.CustomerRequest;
import com.customer.service.section11.response.CustomerResponse;

import java.time.LocalDateTime;

import static com.customer.service.section11.util.CustomerUtil.autoGenerateHashPassword;

/**
 * The CustomerMapper class is responsible for converting between
 * CustomerRequest <--> CustomerModel and CustomerModel <--> CustomerResponse.
 * <p>
 * This ensures a clean separation between API layer (DTOs) and persistence layer (Entity).
 */
public class CustomerMapper {

    /**
     * Converts a CustomerRequest object (from API input) to a CustomerModel object (entity).
     *
     * @param request the input data from the client
     * @return a fully constructed CustomerModel with encrypted password, default ACTIVE status,
     * and timestamps for createdDate and updatedDate.
     */
    public static CustomerModel toCustomerModel(CustomerRequest request) {
        return CustomerModel.builder()
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .customerAge(request.getCustomerAge())
                .customerMobileNumber(request.getCustomerMobileNumber())
                .customerEmailAddress(request.getCustomerEmailAddress())
                .customerAddress(request.getCustomerAddress())
                .userStatus(CustomerStatus.ACTIVE)
                .password(autoGenerateHashPassword())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }

    /**
     * Converts a CustomerModel object (entity) to a CustomerResponse object (API output).
     *
     * @param model the entity from the database
     * @return a CustomerResponse that includes all fields except the password
     */
    public static CustomerResponse toCustomerResponse(CustomerModel model) {
        return CustomerResponse.builder()
                .customerId(model.getCustomerId())
                .userName(model.getUserName())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .customerAge(model.getCustomerAge())
                .customerMobileNumber(model.getCustomerMobileNumber())
                .customerEmailAddress(model.getCustomerEmailAddress())
                .customerAddress(model.getCustomerAddress())
                .userStatus(model.getUserStatus())
                .createdDate(model.getCreatedDate())
                .updatedDate(model.getUpdatedDate())
                .build();
    }
}
