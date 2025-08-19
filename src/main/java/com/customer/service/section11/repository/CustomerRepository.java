package com.customer.service.section11.repository;

import com.customer.service.section11.entity.CustomerModel;
import com.customer.service.section11.response.CustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * CustomerRepository acts as the Data Access Layer for interacting with the `CustomerModel` table.
 * <p>
 * It extends {@link JpaRepository} to inherit basic CRUD operations (Create, Read, Update, Delete).
 * <p>
 * Benefits:
 * - Automatically provides methods like save(), findAll(), findById(), delete(), etc.
 * - Supports custom finder methods based on naming conventions.
 * - Reduces manual coding by leveraging Spring Data JPA features.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    /**
     * Finds a customer by their mobile number.
     *
     * @param customerMobileNumber The unique mobile number of the customer.
     * @return Optional containing CustomerModel if found, else empty.
     */
    Optional<CustomerModel> findByCustomerMobileNumber(String customerMobileNumber);

    /**
     * Finds a customer by their username.
     *
     * @param userName The unique username of the customer.
     * @return Optional containing CustomerModel if found, else empty.
     */
    Optional<CustomerModel> findByUserName(String userName);

    /**
     * Finds a customer by their email address.
     *
     * @param customerEmailAddress The unique email address of the customer.
     * @return Optional containing CustomerModel if found, else empty.
     */
    Optional<CustomerModel> findByCustomerEmailAddress(String customerEmailAddress);

    /**
     * Checks whether a customer with the given username exists.
     *
     * @param userName The username to check.
     * @return true if exists, false otherwise.
     */
    boolean existsByUserName(String userName);

    /**
     * Checks whether a customer with the given email address exists.
     *
     * @param customerEmailAddress The email address to check.
     * @return true if exists, false otherwise.
     */
    boolean existsByCustomerEmailAddress(String customerEmailAddress);

    /**
     * Checks whether a customer with the given mobile number exists.
     *
     * @param customerMobileNumber The mobile number to check.
     * @return true if exists, false otherwise.
     */
    boolean existsByCustomerMobileNumber(String customerMobileNumber);

    /**
     * Finds all customers with the given firstname.
     */
    CustomerResponse findByFirstname(String firstname);

    /**
     * Finds all customers where firstname is exactly the given value.
     * (Same as findByFirstname, just more explicit).
     */
    CustomerResponse  findByFirstnameIs(String firstname);

    /**
     * Finds all customers where firstname equals the given value.
     */
    CustomerResponse  findByFirstnameEquals(String firstname);

    /**
     * Finds all customers whose startsDate is between the given range(inclusive).
     *
     * @param start Start date.
     * @param end End date.
     * @return List of matching customers.
     */
    List<CustomerModel> findByStartDateBetween(LocalDate start, LocalDate end);

    /**
     * Finds all customers whose age is strictly less than the given value.
     */
    List<CustomerModel> findByCustomerAgeLessThen(int age);

    /**
     * Finds all customers whose age is less than or equal to the given value.
     */
    List<CustomerModel> findByCustomerAgeLessThanEqual(int age);

    /**
     * Finds all customers whose age is strictly greater than the given value.
     */
    List<CustomerModel> findByCustomerAgeGreaterThan(int age);

    /**
     * Finds all customers whose age is greater than or equal to the given value.
     */
    List<CustomerModel> findByCustomerAgeGreaterThenEqual(int age);
}