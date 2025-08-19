package com.customer.service.section11.response;

import com.customer.service.section11.enums.CustomerStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * CustomerResponse is a **Data Transfer Object (DTO)** that represents the data
 * sent back to the client after performing operations such as create, update, delete, or fetch.
 * <p>
 * This class is typically used in the Controller layer to format and send
 * the output data as a JSON response.
 * <p>
 * Key Points:
 * <ul>
 *   <li>Contains all the necessary fields to display customer details in the response.</li>
 *   <li>Includes status and timestamp fields for better tracking.</li>
 *   <li>Uses Lombok to reduce boilerplate code for getters, setters, constructors, and builder pattern.</li>
 * </ul>
 * <p>
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
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}