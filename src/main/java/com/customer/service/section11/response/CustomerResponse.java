package com.customer.service.section11.response;

import com.customer.service.section11.enums.CustomerStatus;
import lombok.*;

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
 * Example JSON Response:
 * <pre>
 * {
 *     "code": 201,
 *     "message": "Customer created Successfully",
 *     "data": {
 *         "customerId": 5,
 *         "userName": "Aiyan",
 *         "customerAge": 2,
 *         "customerEmailAddress": "Aiyan@gamil.com",
 *         "customerMobileNumber": "0987654321",
 *         "customerAddress": "chinnamandem",
 *         "userStatus": "ACTIVE",
 *         "createdDate": "2025-08-13T17:55:12.534845",
 *         "updatedDate": "2025-08-13T17:55:12.534845"
 *     }
 * }
 * </pre>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private Long customerId;
    private String userName;
    private Integer customerAge;
    private String customerEmailAddress;
    private String customerMobileNumber;
    private String customerAddress;
    private CustomerStatus userStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}