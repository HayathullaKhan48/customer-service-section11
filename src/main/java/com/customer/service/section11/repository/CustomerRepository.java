package com.customer.service.section11.repository;

import com.customer.service.section11.entity.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CustomerRepository acts as the Data Access Layer for interacting with the `CustomerModel` table.
 * <p>
 * It extends {@link JpaRepository} to inherit basic CRUD operations (Create, Read, Update, Delete)
 * without needing boilerplate SQL or manual queries.
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
     * <p>
     * - Returns an {@link Optional} to avoid `NullPointerException` if no match is found.
     * - The method name follows Spring Data JPA naming conventions, so no manual query is required.
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
     * <p>
     * - Used for validation before creating a new customer to avoid duplicates.
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

    Optional<CustomerModel> findDistinctByFirstNameAndLastName(String firstName, String lastName);

    List<CustomerModel> findByLastNameAndFirstName (String lastName, String firstName);
}