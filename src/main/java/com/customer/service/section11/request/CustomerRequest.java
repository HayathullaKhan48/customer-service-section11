package com.customer.service.section11.request;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

/**
 * CustomerRequest is a **Data Transfer Object (DTO)** that represents
 * the input payload received from the client when creating or updating a customer.
 * <p>
 * This class is typically used in the Controller layer to map the JSON request body
 * into a Java object before passing it to the Service layer for processing.
 * <p>
 * Key Points:
 * <ul>
 *   <li>Contains only the fields required from the client — no business logic.</li>
 *   <li>Acts as an input model for HTTP POST/PUT/PATCH requests.</li>
 *   <li>Uses Lombok annotations to auto-generate boilerplate code (getters, setters, constructors, builder).</li>
 * </ul>
 * <p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    private String userName;
    private String firstName;
    private String lastName;
    private Integer customerAge;
    private String customerMobileNumber;
    private String customerEmailAddress;
    private String customerAddress;
}
