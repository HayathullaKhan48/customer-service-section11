package com.customer.service.section11.response;

import com.customer.service.section11.enums.CustomerStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * CustomerResponseData is the inner DTO that represents customer details
 * returned in API responses. This separates metadata (code/message)
 * from actual customer data.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private Long customerId;
    private String userName;
    private String firstName;
    private String lastName;
    private Integer customerAge;
    private String customerEmailAddress;
    private String customerMobileNumber;
    private String customerAddress;
    private CustomerStatus userStatus;
    private LocalDate startDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}