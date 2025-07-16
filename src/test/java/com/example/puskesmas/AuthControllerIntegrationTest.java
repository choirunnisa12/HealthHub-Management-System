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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerUser_success() throws Exception {
        String unique = UUID.randomUUID().toString();
        var payload = new java.util.HashMap<String, String>();
        payload.put("username", "TestUser_" + unique);
        payload.put("email", "testuser_" + unique + "@example.com");
        payload.put("password", "password123");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("TestUser_" + unique))
                .andExpect(jsonPath("$.email").value("testuser_" + unique + "@example.com"));
    }

    @Test
    void registerUser_invalidEmail_returnsValidationError() throws Exception {
        var payload = new java.util.HashMap<String, String>();
        payload.put("username", "InvalidEmailUser");
        payload.put("email", "not-an-email");
        payload.put("password", "password123");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void loginUser_success() throws Exception {
        String unique = UUID.randomUUID().toString();
        // Arrange: Register a user first
        var payload = new java.util.HashMap<String, String>();
        payload.put("username", "LoginUser_" + unique);
        payload.put("email", "loginuser_" + unique + "@example.com");
        payload.put("password", "password123");
        payload.put("role", "DOCTOR");
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());

        // Act: Attempt to login with the same credentials
        var loginPayload = new java.util.HashMap<String, String>();
        loginPayload.put("email", "loginuser_" + unique + "@example.com");
        loginPayload.put("password", "password123");
        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginPayload)));

        // Assert: Should return 200 OK and a JWT token
        String response = result.andExpect(status().isOk())
              .andExpect(jsonPath("$.token").exists())
              .andReturn().getResponse().getContentAsString();
        String jwt = new com.fasterxml.jackson.databind.ObjectMapper().readTree(response).get("token").asText();

        // Use the same secret and key handling as in production JwtUtil
        String secret = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcd"; // from application.properties
        // Use Keys.hmacShaKeyFor to ensure the secret is a valid key for HS256, matching the application logic
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .parseClaimsJws(jwt)
                .getBody();
        assertEquals("DOCTOR", claims.get("role"));
    }

    @Test
    void loginUser_wrongPassword_returnsUnauthorized() throws Exception {
        String unique = UUID.randomUUID().toString();
        var payload = new java.util.HashMap<String, String>();
        payload.put("username", "WrongPasswordUser_" + unique);
        payload.put("email", "wrongpass_" + unique + "@example.com");
        payload.put("password", "password123");
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());

        var loginPayload = new java.util.HashMap<String, String>();
        loginPayload.put("email", "wrongpass_" + unique + "@example.com");
        loginPayload.put("password", "wrongpassword");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginPayload)));

        result.andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Invalid credentials"));
    }

    @Test
    void registerUser_missingFields_returnsValidationError() throws Exception {
        var payload = new java.util.HashMap<String, String>();
        payload.put("username", "NoPasswordUser");
        payload.put("email", "nopassword@example.com");
        // No password

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void accessProtectedEndpoint_withoutToken_returnsUnauthorized() throws Exception {
        // Act: Try to access a protected endpoint without JWT
        ResultActions result = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));
        // Assert: Should return 403 Forbidden (Spring Security default for missing/invalid token)
        result.andExpect(status().isForbidden());
    }

    @Test
    void accessProtectedEndpoint_withToken_returnsOk() throws Exception {
        String unique = UUID.randomUUID().toString();
        // Arrange: Register and login to get a JWT token
        var payload = new java.util.HashMap<String, String>();
        payload.put("username", "SecuredUser_" + unique);
        payload.put("email", "secured_" + unique + "@example.com");
        payload.put("password", "password123");
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated());

        var loginPayload = new java.util.HashMap<String, String>();
        loginPayload.put("email", "secured_" + unique + "@example.com");
        loginPayload.put("password", "password123");
        String token = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginPayload)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String jwt = objectMapper.readTree(token).get("token").asText();

        ResultActions result = mockMvc.perform(post("/api/users")
                .header("Authorization", "Bearer " + jwt)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));

        int status = result.andReturn().getResponse().getStatus();
        assertNotEquals(401, status, "Should not return 401 Unauthorized when JWT is provided");
    }
}
