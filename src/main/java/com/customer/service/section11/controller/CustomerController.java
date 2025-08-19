package com.customer.service.section11.controller;

import com.customer.service.section11.enums.CustomerStatus;
import com.customer.service.section11.request.CustomerRequest;
import com.customer.service.section11.response.ApiResponse;
import com.customer.service.section11.response.CustomerResponse;
import com.customer.service.section11.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.customer.service.section11.constant.CustomerConstant.*;

/**
 * REST controller for handling Customer-related operations.
 * Provides endpoints for creating, retrieving, updating, and soft-deleting customer records.
 * Each endpoint returns a standardized {@link ApiResponse}.
 */
@RestController
@RequestMapping("/api/customer/v1")
@RequiredArgsConstructor
@Tag(name = "Customer Management", description = "CRUD operations and status management for customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Create a new customer record.
     *
     * @param request Request body containing new customer details.
     * @return ResponseEntity containing ApiResponse with created customer details.
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new customer", description = "Adds a new customer with unique username, email, and mobile number.")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(HttpStatus.CREATED.value(), CUSTOMER_CREATED_SUCCESS, response));
    }

    /**
     * Retrieve all customers.
     *
     * @return ResponseEntity containing ApiResponse with a list of all customers.
     */
    @GetMapping("/getAllData")
    @Operation(summary = "Get all customers", description = "Fetches a list of all registered customers.")
    public ResponseEntity<ApiResponse> getAllCustomers() {
        List<CustomerResponse> response = customerService.getAllCustomersData();
        return ResponseEntity.ok(new ApiResponse(HttpStatus.MULTI_STATUS.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    /**
     * Retrieve a customer by mobile number.
     *
     * @param customerMobileNumber Customer's mobile number.
     * @return ResponseEntity containing ApiResponse with matching customer details.
     */
    @GetMapping("/getByMobile/{customerMobileNumber}")
    @Operation(summary = "Get customer by mobile", description = "Fetches customer details using their mobile number.")
    public ResponseEntity<ApiResponse> getByMobile(@PathVariable String customerMobileNumber) {
        CustomerResponse response = customerService.getByCustomerMobileNumber(customerMobileNumber);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    /**
     * Retrieve a customer by username.
     *
     * @param userName Customer's username.
     * @return ResponseEntity containing ApiResponse with matching customer details.
     */
    @GetMapping("/getByUserName/{userName}")
    @Operation(summary = "Get customer by username", description = "Fetches customer details using their username.")
    public ResponseEntity<ApiResponse> getByUserName(@PathVariable String userName) {
        CustomerResponse response = customerService.getByCustomerName(userName);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    /**
     * Retrieve a customer by email address.
     *
     * @param emailAddress Customer's email address.
     * @return ResponseEntity containing ApiResponse with matching customer details.
     */
    @GetMapping("/getByEmailAddress/{emailAddress}")
    @Operation(summary = "Get customer by email", description = "Fetches customer details using their email address.")
    public ResponseEntity<ApiResponse> getByEmail(@PathVariable String emailAddress) {
        CustomerResponse response = customerService.getByEmailAddress(emailAddress);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    /**
     * Update an existing customer's details by matching mobile number.
     *
     * @param request Request body containing updated customer details.
     * @return ResponseEntity containing ApiResponse with updated customer details.
     */
    @PutMapping("/update")
    @Operation(summary = "Update customer details", description = "Updates an existing customer’s details using their mobile number.")
    public ResponseEntity<ApiResponse> updateCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.updateCustomer(request);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.UPGRADE_REQUIRED.value(), CUSTOMER_UPDATED_SUCCESS, response));
    }

    /**
     * Softly delete a customer by setting status to INACTIVE using mobile number.
     *
     * @param mobileNumber Mobile number of the customer to be soft deleted.
     * @return ResponseEntity containing ApiResponse with updated status.
     */
    @DeleteMapping("/delete/{mobileNumber}")
    @Operation(summary = "Delete customer", description = "Performs a soft delete by setting customer status to INACTIVE.")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable String mobileNumber) {
        CustomerResponse response = customerService.deleteCustomer(mobileNumber);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.TEMPORARY_REDIRECT.value(), CUSTOMER_DELETED_SUCCESS, response));
    }

    /**
     * Update a customer's mobile number using their username.
     *
     * @param userName     Username of the customer.
     * @param mobileNumber New mobile number.
     * @return ResponseEntity containing ApiResponse with updated customer details.
     */
    @PatchMapping("/updateMobileNumber/{userName}/{mobileNumber}")
    @Operation(summary = "Update mobile number", description = "Updates a customer’s mobile number using their username.")
    public ResponseEntity<ApiResponse> updateMobileNumber(@PathVariable String userName, @PathVariable String mobileNumber) {
        CustomerResponse response = customerService.updateMobileNumber(userName, mobileNumber);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.UPGRADE_REQUIRED.value(), CUSTOMER_UPDATED_SUCCESS, response));
    }

    /**
     * Update a customer's status (e.g., ACTIVE/INACTIVE) using their mobile number.
     *
     * @param mobileNumber Customer's mobile number.
     * @param status       New status to be set (ACTIVE or INACTIVE).
     * @return ResponseEntity containing ApiResponse with updated customer status.
     */
    @PatchMapping("/status/{mobileNumber}/{status}")
    @Operation(summary = "Update status", description = "Updates the status of a customer (ACTIVE/INACTIVE) using their mobile number.")
    public ResponseEntity<ApiResponse> updateStatusByMobile(@PathVariable String mobileNumber, @PathVariable CustomerStatus status) {
        CustomerResponse response = customerService.updateStatusByMobile(mobileNumber, status);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.UPGRADE_REQUIRED.value(), CUSTOMER_STATUS_UPDATED_SUCCESS, response));
    }

    @GetMapping("/getByFirstname/{firstname}")
    @Operation(summary = "Find by firstname", description = "Fetches customer details by firstname.")
    public ResponseEntity<ApiResponse> getByFirstname(@PathVariable String firstname) {
        List<CustomerResponse> response = customerService.getByFirstname(firstname);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    @GetMapping("/getByFirstnameIs/{firstname}")
    @Operation(summary = "Find by firstname (Is)", description = "Fetches customer details by firstname using 'Is' keyword.")
    public ResponseEntity<ApiResponse> getByFirstnameIs(@PathVariable String firstname) {
        List<CustomerResponse> response = customerService.getByFirstnameIs(firstname);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    @GetMapping("/getByFirstnameEquals/{firstname}")
    @Operation(summary = "Find by firstname (Equals)", description = "Fetches customer details by firstname using 'Equals' keyword.")
    public ResponseEntity<ApiResponse> getByFirstnameEquals(@PathVariable String firstname) {
        List<CustomerResponse> response = customerService.getByFirstnameEquals(firstname);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    @GetMapping("/getByStartDateBetween/{start}/{end}")
    @Operation(summary = "Find by start date range", description = "Fetches customers whose start date is between two dates.")
    public ResponseEntity<ApiResponse> getByStartDateBetween(@PathVariable String start, @PathVariable String end) {
        List<CustomerResponse> response = customerService.getByStartDateBetween(LocalDate.parse(start), LocalDate.parse(end));
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    @GetMapping("/getByAgeLessThan/{age}")
    @Operation(summary = "Find by age less than", description = "Fetches customers whose age is less than given value.")
    public ResponseEntity<ApiResponse> getByAgeLessThan(@PathVariable int age) {
        List<CustomerResponse> response = customerService.getByAgeLessThan(age);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    @GetMapping("/getByAgeLessThanEqual/{age}")
    @Operation(summary = "Find by age less than or equal", description = "Fetches customers whose age is less than or equal to given value.")
    public ResponseEntity<ApiResponse> getByAgeLessThanEqual(@PathVariable int age) {
        List<CustomerResponse> response = customerService.getByAgeLessThanEqual(age);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    @GetMapping("/getByAgeGreaterThan/{age}")
    @Operation(summary = "Find by age greater than", description = "Fetches customers whose age is greater than given value.")
    public ResponseEntity<ApiResponse> getByAgeGreaterThan(@PathVariable int age) {
        List<CustomerResponse> response = customerService.getByAgeGreaterThan(age);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }

    @GetMapping("/getByAgeGreaterThanEqual/{age}")
    @Operation(summary = "Find by age greater than or equal", description = "Fetches customers whose age is greater than or equal to given value.")
    public ResponseEntity<ApiResponse> getByAgeGreaterThanEqual(@PathVariable int age) {
        List<CustomerResponse> response = customerService.getByAgeGreaterThanEqual(age);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), CUSTOMER_FETCHED_SUCCESS, response));
    }
}
