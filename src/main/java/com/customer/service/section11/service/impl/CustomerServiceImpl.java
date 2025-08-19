package com.customer.service.section11.service.impl;

import com.customer.service.section11.entity.CustomerModel;
import com.customer.service.section11.enums.CustomerStatus;
import com.customer.service.section11.exceptions.CustomerAlreadyExistsException;
import com.customer.service.section11.exceptions.CustomerNotExistsException;
import com.customer.service.section11.mapper.CustomerMapper;
import com.customer.service.section11.repository.CustomerRepository;
import com.customer.service.section11.request.CustomerRequest;
import com.customer.service.section11.response.CustomerResponse;
import com.customer.service.section11.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.customer.service.section11.constant.CustomerConstant.CUSTOMER_ALREADY_EXISTS;
import static com.customer.service.section11.constant.CustomerConstant.CUSTOMER_NOT_EXISTS;

/**
 * Implementation of {@link CustomerService} that contains
 * the business logic for managing customer data.
 * <p>
 * This service:
 * <ul>
 *   <li>Performs validations before persisting/updating customer data.</li>
 *   <li>Handles duplicate checks for username, email, and mobile number.</li>
 *   <li>Uses soft deletion by changing {@link CustomerStatus} instead of deleting records.</li>
 * </ul>
 * <p>
 * All database interactions are handled through {@link CustomerRepository}.
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    /** Repository for accessing and modifying customer data. */
    private final CustomerRepository customerRepository;

    /**
     * Creates a new customer.
     * <ul>
     *   <li>Validates uniqueness of username, email, and mobile number.</li>
     *   <li>Sets default status to {@code ACTIVE}.</li>
     *   <li>Sets creation and update timestamps.</li>
     * </ul>
     *
     * @param request The customer creation request payload.
     * @return The created customer as a {@link CustomerResponse}.
     * @throws CustomerAlreadyExistsException if duplicate fields are found.
     */
    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        List<String> duplicates = new ArrayList<>();
        if (customerRepository.existsByUserName(request.getUserName())) {
            duplicates.add("userName");
        }
        if (customerRepository.existsByCustomerEmailAddress(request.getCustomerEmailAddress())) {
            duplicates.add("emailAddress");
        }
        if (customerRepository.existsByCustomerMobileNumber(request.getCustomerMobileNumber())) {
            duplicates.add("mobileNumber");
        }

        if (!duplicates.isEmpty()) {
            String message = "Duplicate fields: " + String.join(", ", duplicates) + " _ " + CUSTOMER_ALREADY_EXISTS;
            throw new CustomerAlreadyExistsException(message);
        }
        CustomerModel model = CustomerMapper.toCustomerModel(request);
        model.setUserStatus(CustomerStatus.ACTIVE);
        model.setCreatedDate(LocalDateTime.now());
        model.setUpdatedDate(LocalDateTime.now());

        CustomerModel saved = customerRepository.saveAndFlush(model);
        return CustomerMapper.toCustomerResponse(saved);
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of {@link CustomerResponse}.
     */
    @Override
    public List<CustomerResponse> getAllCustomersData() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a customer by mobile number.
     *
     * @param mobileNumber The customer's mobile number.
     * @return The customer as a {@link CustomerResponse}.
     * @throws CustomerNotExistsException if the customer does not exist.
     */
    @Override
    public CustomerResponse getByCustomerMobileNumber(String mobileNumber) {
        CustomerModel model = customerRepository.findByCustomerMobileNumber(mobileNumber)
                .orElseThrow(() -> new CustomerNotExistsException(mobileNumber + " " + CUSTOMER_NOT_EXISTS));
        return CustomerMapper.toCustomerResponse(model);
    }

    /**
     * Retrieves a customer by username.
     *
     * @param CustomerName The customer's username.
     * @return The customer as a {@link CustomerResponse}.
     * @throws CustomerNotExistsException if the customer does not exist.
     */
    @Override
    public CustomerResponse getByCustomerName(String CustomerName) {
        CustomerModel model = customerRepository.findByUserName(CustomerName)
                .orElseThrow(() -> new CustomerNotExistsException(CustomerName + " " + CUSTOMER_NOT_EXISTS));
        return CustomerMapper.toCustomerResponse(model);
    }

    /**
     * Retrieves a customer by email address.
     *
     * @param emailAddress The customer's email address.
     * @return The customer as a {@link CustomerResponse}.
     * @throws CustomerNotExistsException if the customer does not exist.
     */
    @Override
    public CustomerResponse getByEmailAddress(String emailAddress) {
        CustomerModel model = customerRepository.findByCustomerEmailAddress(emailAddress)
                .orElseThrow(() -> new CustomerNotExistsException(emailAddress + " " + CUSTOMER_NOT_EXISTS));
        return CustomerMapper.toCustomerResponse(model);
    }

    /**
     * Updates a customer's details using their mobile number.
     * <p>Note: Mobile number and status are not updated here.</p>
     *
     * @param request The updated customer details.
     * @return The updated customer as a {@link CustomerResponse}.
     * @throws CustomerNotExistsException if the customer does not exist.
     */
    @Override
    public CustomerResponse updateCustomer(CustomerRequest request) {
        CustomerModel model = customerRepository.findByCustomerMobileNumber(request.getCustomerMobileNumber())
                .orElseThrow(() -> new CustomerNotExistsException(request.getCustomerMobileNumber() + " " + CUSTOMER_NOT_EXISTS));
        model.setFirstName(request.getFirstName());
        model.setLastName(request.getLastName());
        model.setUserName(request.getUserName());
        model.setCustomerAge(request.getCustomerAge());
        model.setCustomerAddress(request.getCustomerAddress());
        model.setCustomerEmailAddress(request.getCustomerEmailAddress());
        model.setStartDate(request.getStartDate());
        model.setUpdatedDate(LocalDateTime.now());

        CustomerModel saved = customerRepository.saveAndFlush(model);
        return CustomerMapper.toCustomerResponse(saved);
    }

    /**
     * Soft deletes a customer by setting their status to {@code INACTIVE}.
     *
     * @param mobile The customer's mobile number.
     * @return The updated customer as a {@link CustomerResponse}.
     * @throws CustomerNotExistsException if the customer does not exist.
     */
    @Override
    public CustomerResponse deleteCustomer(String mobile) {
        CustomerModel model = customerRepository.findByCustomerMobileNumber(mobile)
                .orElseThrow(() -> new CustomerNotExistsException(mobile + " " + CUSTOMER_NOT_EXISTS));

        model.setUserStatus(CustomerStatus.INACTIVE);
        model.setUpdatedDate(LocalDateTime.now());

        CustomerModel saved = customerRepository.saveAndFlush(model);
        return CustomerMapper.toCustomerResponse(saved);
    }

    /**
     * Updates a customer's mobile number using their username.
     *
     * @param userName     The customer's username.
     * @param mobileNumber The new mobile number.
     * @return The updated customer as a {@link CustomerResponse}.
     * @throws CustomerNotExistsException if the customer does not exist.
     * @throws CustomerAlreadyExistsException if the mobile number is already taken.
     */
    @Override
    public CustomerResponse updateMobileNumber(String userName, String mobileNumber) {
        CustomerModel model = customerRepository.findByUserName(userName)
                .orElseThrow(() -> new CustomerNotExistsException(userName + " " + CUSTOMER_NOT_EXISTS));

        customerRepository.findByCustomerMobileNumber(mobileNumber).ifPresent(existing -> {
            throw new CustomerAlreadyExistsException(mobileNumber + " " + CUSTOMER_ALREADY_EXISTS);
        });

        model.setCustomerMobileNumber(mobileNumber);
        model.setUpdatedDate(LocalDateTime.now());

        CustomerModel saved = customerRepository.saveAndFlush(model);
        return CustomerMapper.toCustomerResponse(saved);    }

    /**
     * Updates a customer's status using their mobile number.
     *
     * @param mobileNumber The customer's mobile number.
     * @param status       The new {@link CustomerStatus}.
     * @return The updated customer as a {@link CustomerResponse}.
     * @throws CustomerNotExistsException if the customer does not exist.
     */
    @Override
    public CustomerResponse updateStatusByMobile(String mobileNumber, CustomerStatus status) {
        CustomerModel model = customerRepository.findByCustomerMobileNumber(mobileNumber)
                .orElseThrow(() -> new CustomerNotExistsException(mobileNumber + " " + CUSTOMER_NOT_EXISTS));
        model.setUserStatus(status);
        model.setUpdatedDate(LocalDateTime.now());
        CustomerModel saved = customerRepository.saveAndFlush(model);
        return CustomerMapper.toCustomerResponse(saved);    }

    @Override
    public CustomerResponse getByFirstname(String firstname) {
        CustomerModel customer = customerRepository.findByUserName(firstname)
                .orElseThrow(() -> new RuntimeException("Customer not found with firstname: " + firstname));
        return CustomerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse getByFirstnameIs(String firstname) {
        CustomerModel customer = customerRepository.findByUserNameIs(firstname)
                .orElseThrow(() -> new RuntimeException("Customer not found with firstname (Is): " + firstname));
        return CustomerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse getByFirstnameEquals(String firstname) {
        CustomerModel customer = customerRepository.findByUserNameEquals(firstname)
                .orElseThrow(() -> new RuntimeException("Customer not found with firstname (Equals): " + firstname));
        return CustomerMapper.toResponse(customer);
    }

    @Override
    public List<CustomerResponse> getByStartDateBetween(LocalDate startDate, LocalDate endDate) {
        return customerRepository.findByStartDateBetween(startDate, endDate)
                .stream()
                .map(CustomerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponse> getByAgeLessThan(int age) {
        return customerRepository.findByCustomerAgeLessThen(age)
                .stream()
                .map(CustomerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponse> getByAgeLessThanEqual(int age) {
        return customerRepository.findByCustomerAgeLessThanEqual(age)
                .stream()
                .map(CustomerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponse> getByAgeGreaterThan(int age) {
        return customerRepository.findByCustomerAgeGreaterThan(age)
                .stream()
                .map(CustomerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponse> getByAgeGreaterThanEqual(int age) {
        return customerRepository.findByCustomerAgeGreaterThenEqual(age)
                .stream()
                .map(CustomerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }
}
