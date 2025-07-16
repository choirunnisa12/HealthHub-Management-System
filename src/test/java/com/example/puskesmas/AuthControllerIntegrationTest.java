package com.example.puskesmas;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerUser_success() throws Exception {
        // Arrange: Prepare a valid registration payload
        var payload = new java.util.HashMap<String, String>();
        payload.put("name", "Test User");
        payload.put("email", "testuser@example.com");
        payload.put("password", "password123");

        // Act: Perform the registration request
        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)));

        // Assert: Should return 201 Created and user data
        result.andExpect(status().isCreated())
              .andExpect(jsonPath("$.username").value("Test User"))
              .andExpect(jsonPath("$.email").value("testuser@example.com"));
    }

    @Test
    void registerUser_invalidEmail_returnsValidationError() throws Exception {
        // Arrange: Prepare a payload with invalid email
        var payload = new java.util.HashMap<String, String>();
        payload.put("name", "Test User");
        payload.put("email", "not-an-email");
        payload.put("password", "password123");

        // Act: Perform the registration request
        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)));

        // Assert: Should return 400 Bad Request and validation error message
        result.andExpect(status().isBadRequest())
              .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void loginUser_success() throws Exception {
        // Arrange: Register a user first
        var payload = new java.util.HashMap<String, String>();
        payload.put("name", "Login User");
        payload.put("email", "loginuser@example.com");
        payload.put("password", "password123");
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());

        // Act: Attempt to login with the same credentials
        var loginPayload = new java.util.HashMap<String, String>();
        loginPayload.put("email", "loginuser@example.com");
        loginPayload.put("password", "password123");
        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginPayload)));

        // Assert: Should return 200 OK and a JWT token
        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void loginUser_wrongPassword_returnsUnauthorized() throws Exception {
        // Arrange: Register a user first
        var payload = new java.util.HashMap<String, String>();
        payload.put("name", "Wrong Password User");
        payload.put("email", "wrongpass@example.com");
        payload.put("password", "password123");
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());

        // Act: Attempt to login with wrong password
        var loginPayload = new java.util.HashMap<String, String>();
        loginPayload.put("email", "wrongpass@example.com");
        loginPayload.put("password", "wrongpassword");
        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginPayload)));

        // Assert: Should return 401 Unauthorized
        result.andExpect(status().isUnauthorized())
              .andExpect(jsonPath("$.error").value("Invalid credentials"));
    }

    @Test
    void registerUser_missingFields_returnsValidationError() throws Exception {
        // Arrange: Prepare a payload missing the password field
        var payload = new java.util.HashMap<String, String>();
        payload.put("name", "No Password User");
        payload.put("email", "nopassword@example.com");
        // No password

        // Act: Perform the registration request
        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)));

        // Assert: Should return 400 Bad Request and validation error message
        result.andExpect(status().isBadRequest())
              .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void accessProtectedEndpoint_withoutToken_returnsUnauthorized() throws Exception {
        // Act: Try to access a protected endpoint without JWT
        ResultActions result = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));

        // Assert: Should return 401 Unauthorized
        result.andExpect(status().isUnauthorized());
    }

    @Test
    void accessProtectedEndpoint_withToken_returnsOk() throws Exception {
        // Arrange: Register and login to get a JWT token
        var payload = new java.util.HashMap<String, String>();
        payload.put("name", "Secured User");
        payload.put("email", "secured@example.com");
        payload.put("password", "password123");
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());

        var loginPayload = new java.util.HashMap<String, String>();
        loginPayload.put("email", "secured@example.com");
        loginPayload.put("password", "password123");
        String token = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginPayload)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Extract the token value from the JSON response
        String jwt = new com.fasterxml.jackson.databind.ObjectMapper().readTree(token).get("token").asText();

        // Act: Access a protected endpoint with the JWT token
        ResultActions result = mockMvc.perform(post("/api/users")
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));

        // Assert: Should not return 401 (could be 201 or 400 depending on payload, but not unauthorized)
        int status = result.andReturn().getResponse().getStatus();
        assertNotEquals(401, status, "Should not return 401 Unauthorized when JWT is provided");
    }
} 