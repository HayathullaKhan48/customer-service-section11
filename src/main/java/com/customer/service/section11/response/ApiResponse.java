package com.customer.service.section11.response;

/**
 * ApiResponse is a generic response wrapper class used to send
 * consistent responses for all API endpoints.
 * Features:
 * - Stores HTTP status code(e.g., 200,201,404,500)
 * - Stores a message describing the API result
 * - Stores any kind of data (object) returned from the API
 */
public record ApiResponse(int code, String message, Object data) {
}
