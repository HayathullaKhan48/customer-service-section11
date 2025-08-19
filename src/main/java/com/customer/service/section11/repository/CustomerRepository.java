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

    /**
     * Finds distinct customers by lastName and firstName.
     * Removes duplicates in the result based on the combination of these two fields.
     *
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @return a list of distinct matching customers
     */
    List<CustomerModel> findDistinctByLastNameAndFirstName(String firstName, String lastName);

    /**
     * Finds customers where both lastName AND firstName match the given values.
     *
     * @param lastName the last name of the customer
     * @param firstName the first name of the customer
     * @return a list of customers matching both names
     */
    List<CustomerModel> findByLastNameAndFirstName (String lastName, String firstName);

    /**
     * Finds customers where either lastName OR firstName match the given values.
     *
     * @param lastName the last name of the customer
     * @param firstName the first name of the customer
     * @return a list of customers matching either condition
     */
    List<CustomerModel> findByLastNameOrFirstName(String lastName, String firstName);

    /**
     * Finds customers by matching the given firstName.
     * Equivalent to SQL WHERE first_name = ?.
     *
     * @param firstName the first name of the customer
     * @return a list of customers with the given first name
     */
    List<CustomerModel> findByFirstName(String firstName);

    /**
     * Finds customers by matching the given firstName.
     * "Is" is functionally the same as {@link #findByFirstName(String)}.
     *
     * @param firstName the first name of the customer
     * @return a list of customers with the given first name
     */
    List<CustomerModel> findByFirstNameIs(String firstName);

    /**
     * Finds customers by matching the given firstName.
     * "Equals" is functionally the same as {@link #findByFirstName(String)}.
     *
     * @param firstName the first name of the customer
     * @return a list of customers with the given first name
     */
    List<CustomerModel> findByFirstNameEquals(String firstName);

}