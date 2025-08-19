package com.customer.service.section11.request;

import lombok.*;

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
 * Example JSON request body for creating a customer:
 * <pre>
 * {
 *   "userName": "HayathullaUpdate",
 *   "customerAge": 24,
 *   "customerMobileNumber": "6304474604",
 *   "customerEmailAddress": "Hayath@gamil.com",
 *   "customerAddress": " Main Street",
 *   "password": "01021401021"
 * }
 * </pre>
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
