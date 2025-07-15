package com.example.puskesmas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testRegisterLoginAndAccessProtectedEndpoint() {
        // ========== 1. Register User ==========
        String registerUrl = "http://localhost:" + port + "/api/auth/register";

        Map<String, String> registerBody = Map.of(
                "username", "testuser",  // ✅ Sudah cocok dengan DTO
                "password", "testpass",
                "email", "testuser@mail.com"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> registerRequest =
                new HttpEntity<>(registerBody, headers);

        ResponseEntity<String> registerResponse =
                restTemplate.postForEntity(registerUrl, registerRequest, String.class);

        assertEquals(HttpStatus.CREATED, registerResponse.getStatusCode());

        // ========== 2. Login User ==========
        String loginUrl = "http://localhost:" + port + "/api/auth/login";

        Map<String, String> loginBody = Map.of(
                "email", "testuser@mail.com",
                "password", "testpass"
        );

        HttpEntity<Map<String, String>> loginRequest =
                new HttpEntity<>(loginBody, headers);

        ResponseEntity<Map> loginResponse =
                restTemplate.postForEntity(loginUrl, loginRequest, Map.class);

        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        assertNotNull(loginResponse.getBody());

        String token = (String) loginResponse.getBody().get("token");
        assertNotNull(token);

        // ========== 3. Access Protected Endpoint ==========
        String protectedUrl = "http://localhost:" + port + "/patients";

        HttpHeaders jwtHeaders = new HttpHeaders();
        jwtHeaders.setBearerAuth(token);  // ✅ Header Authorization otomatis

        HttpEntity<Void> protectedRequest = new HttpEntity<>(jwtHeaders);

        ResponseEntity<String> protectedResponse =
                restTemplate.exchange(protectedUrl, HttpMethod.GET, protectedRequest, String.class);

        assertNotEquals(HttpStatus.UNAUTHORIZED, protectedResponse.getStatusCode());
        assertNotEquals(HttpStatus.FORBIDDEN, protectedResponse.getStatusCode());
    }
}
