package com.customer.service.section11.controller;

import com.customer.service.section11.enums.CustomerStatus;
import com.customer.service.section11.request.CustomerRequest;
import com.customer.service.section11.response.ApiResponse;
import com.customer.service.section11.response.CustomerResponse;
import com.customer.service.section11.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.customer.service.section11.constant.CustomerConstant.*;

/**
 * REST controller for handling Customer-related operations.
 * Provides endpoints for creating, retrieving, updating, and soft-deleting customer records.
 * Each endpoint returns a standardized {@link ApiResponse} object.
 */
@RestController
@RequestMapping("/api/customer/v1")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Create a new customer record.
     * HTTP Method: POST
     * Endpoint: /api/customer/v1/create
     *
     * @param request Request body containing new customer details.
     * @return ResponseEntity containing ApiResponse with created customer details.
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(HttpStatus.CREATED.value(), CUSTOMER_CREATED_SUCCESS, response));
    }

    /**
     * Retrieve all customers.
     * HTTP Method: GET
     * Endpoint: /api/customer/v1/getAllData
     *
     * @return ResponseEntity containing ApiResponse with a list of all customers.
     */
    @GetMapping("/getAllData")
    @Operation(
            description = "Get Users Details"
    )
    public ResponseEntity<ApiResponse> getAllCustomers() {
        List<CustomerResponse> response = customerService.getAllCustomersData();
        return ResponseEntity
                .ok(new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), response));
    }

    /**
     * Retrieve a customer by mobile number.
     * HTTP Method: GET
     * Endpoint: /api/customer/v1/getByMobile/{customerMobileNumber}
     *
     * @param customerMobileNumber Customer's mobile number.
     * @return ResponseEntity containing ApiResponse with matching customer details.
     */
    @GetMapping("/getByMobile/{customerMobileNumber}")
    public ResponseEntity<ApiResponse> getByMobile(@PathVariable String customerMobileNumber) {
        CustomerResponse response = customerService.getByCustomerMobileNumber(customerMobileNumber);
        return ResponseEntity.ok(
                new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), response));
    }

    /**
     * Retrieve a customer by username.
     * HTTP Method: GET
     * Endpoint: /api/customer/v1/getByUserName/{userName}
     *
     * @param userName Customer's username.
     * @return ResponseEntity containing ApiResponse with matching customer details.
     */
    @GetMapping("/getByUserName/{userName}")
    public ResponseEntity<ApiResponse> getByUserName(@PathVariable String userName) {
        CustomerResponse response = customerService.getByCustomerName(userName);
        return ResponseEntity.ok(
                new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), response));
    }

    /**
     * Retrieve a customer by email address.
     * HTTP Method: GET
     * Endpoint: /api/customer/v1/getByEmailAddress/{emailAddress}
     *
     * @param emailAddress Customer's email address.
     * @return ResponseEntity containing ApiResponse with matching customer details.
     */
    @GetMapping("/getByEmailAddress/{emailAddress}")
    public ResponseEntity<ApiResponse> getByEmail(@PathVariable String emailAddress) {
        CustomerResponse response = customerService.getByEmailAddress(emailAddress);
        return ResponseEntity.ok(
                new ApiResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), response));
    }

    /**
     * Update an existing customer's details by matching mobile number.
     * HTTP Method: PUT
     * Endpoint: /api/customer/v1/update
     *
     * @param request Request body containing updated customer details.
     * @return ResponseEntity containing ApiResponse with updated customer details.
     */
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.updateCustomer(request);
        return ResponseEntity.ok(
                new ApiResponse(HttpStatus.OK.value(), CUSTOMER_UPDATED_SUCCESS, response));
    }

    /**
     * Softly delete a customer by setting status to INACTIVE using mobile number.
     * The record is not removed from the database.
     * HTTP Method: DELETE
     * Endpoint: /api/customer/v1/delete/{mobileNumber}
     *
     * @param mobileNumber Mobile number of the customer to be soft deleted.
     * @return ResponseEntity containing ApiResponse with updated status.
     */
    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable String mobileNumber) {
        CustomerResponse response = customerService.deleteCustomer(mobileNumber);
        return ResponseEntity.ok(
                new ApiResponse(HttpStatus.OK.value(), CUSTOMER_DELETED_SUCCESS, response));
    }

    /**
     * Update a customer's mobile number using their username.
     * HTTP Method: PATCH
     * Endpoint: /api/customer/v1/updateMobileNumber/{userName}/{mobileNumber}
     *
     * @param userName     Username of the customer.
     * @param mobileNumber New mobile number.
     * @return ResponseEntity containing ApiResponse with updated customer details.
     */
    @PatchMapping("/updateMobileNumber/{userName}/{mobileNumber}")
    public ResponseEntity<ApiResponse> updateMobileNumber(@PathVariable String userName, @PathVariable String mobileNumber) {
        CustomerResponse response = customerService.updateMobileNumber(userName, mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(HttpStatus.CREATED.value(), HttpStatus.OK.name(), response));
    }

    /**
     * Update a customer's status (e.g., ACTIVE/INACTIVE) using their mobile number.
     * HTTP Method: PATCH
     * Endpoint: /api/customer/v1/status/{mobileNumber}/{status}
     *
     * @param mobileNumber Customer's mobile number.
     * @param status       New status to be set (ACTIVE or INACTIVE).
     * @return ResponseEntity containing ApiResponse with updated customer status.
     */
    @PatchMapping("/status/{mobileNumber}/{status}")
    public ResponseEntity<ApiResponse> updateStatusByMobile(@PathVariable String mobileNumber, @PathVariable CustomerStatus status) {
        CustomerResponse response = customerService.updateStatusByMobile(mobileNumber, status);
        return ResponseEntity
                .ok(new ApiResponse(HttpStatus.OK.value(),
                        CUSTOMER_STATUS_UPDATED_SUCCESS, response));
    }

    /**
     * Fetches distinct customers by lastName AND firstName.
     * This ensures only unique customer records are returned when duplicates exist.
     *
     * @param lastName  The last name of the customer.
     * @param firstName The first name of the customer.
     * @return ResponseEntity with a list of distinct CustomerResponse objects.
     */
    @GetMapping("/getDistinctByLastNameAndFirstName/{lastname}/{firstname}")
    @Operation(summary = "Get distinct by lastname and firstname")
    public ResponseEntity<ApiResponse> getDistinctByLastNameAndFirstName(@PathVariable("lastname") String lastName,
                                                                         @PathVariable("firstname") String firstName) {
        List<CustomerResponse> response = customerService.getDistinctByLastNameAndFirstName(lastName, firstName);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Customers fetched successfully", response));
    }

    /**
     * Fetches customers by lastName AND firstName (both conditions must match).
     *
     * @param lastName  The last name of the customer.
     * @param firstName The first name of the customer.
     * @return ResponseEntity with a list of matching CustomerResponse objects.
     */
    @GetMapping("/getByLastNameAndFirstName/{lastname}/{firstname}")
    @Operation(summary = "Get by lastname AND firstname")
    public ResponseEntity<ApiResponse> getByLastNameAndFirstName(@PathVariable("lastname") String lastName,
                                                                 @PathVariable("firstname") String firstName) {
        List<CustomerResponse> response = customerService.getByLastNameAndFirstName(lastName, firstName);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Customers fetched successfully", response));
    }

    /**
     * Fetches customers where either lastName OR firstName matches.
     * Useful when you want results if at least one of the fields matches.
     *
     * @param lastName  The last name of the customer.
     * @param firstName The first name of the customer.
     * @return ResponseEntity with a list of matching CustomerResponse objects.
     */
    @GetMapping("/getByLastNameOrFirstName/{lastname}/{firstname}")
    @Operation(summary = "Get by lastname OR firstname")
    public ResponseEntity<ApiResponse> getByLastNameOrFirstName(@PathVariable("lastname") String lastName,
                                                                @PathVariable("firstname") String firstName) {
        List<CustomerResponse> response = customerService.getByLastNameOrFirstName(lastName, firstName);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Customers fetched successfully", response));
    }

    /**
     * Fetches customers by firstName.
     * Matches directly against the provided firstName.
     *
     * @param firstName The first name of the customer.
     * @return ResponseEntity with a list of CustomerResponse objects.
     */
    @GetMapping("/getByFirstName/{firstname}")
    @Operation(summary = "Get by firstName")
    public ResponseEntity<ApiResponse> getByFirstName(@PathVariable("firstname") String firstName) {
        List<CustomerResponse> response = customerService.getByFirstName(firstName);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Customers fetched successfully", response));
    }

    /**
     * Fetches customers by firstName using "Is" keyword.
     * This is semantically the same as equals, but can be clearer in method naming.
     *
     * @param firstName The first name of the customer.
     * @return ResponseEntity with a list of CustomerResponse objects.
     */
    @GetMapping("/getByFirstNameIs/{firstname}")
    @Operation(summary = "Get by firstName (Is)")
    public ResponseEntity<ApiResponse> getByFirstNameIs(@PathVariable("firstname") String firstName) {
        List<CustomerResponse> response = customerService.getByFirstNameIs(firstName);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Customers fetched successfully", response));
    }

    /**
     * Fetches customers by firstName using "Equals".
     * Explicitly checks if the field exactly matches the given firstName.
     *
     * @param firstName The first name of the customer.
     * @return ResponseEntity with a list of CustomerResponse objects.
     */
    @GetMapping("/getByFirstNameEquals/{firstname}")
    @Operation(summary = "Get by firstName (Equals)")
    public ResponseEntity<ApiResponse> getByFirstNameEquals(@PathVariable("firstname") String firstName) {
        List<CustomerResponse> response = customerService.getByFirstNameEquals(firstName);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Customers fetched successfully", response));
    }

}