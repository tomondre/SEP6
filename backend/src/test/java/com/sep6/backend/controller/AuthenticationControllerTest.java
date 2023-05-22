package com.sep6.backend.controller;

import com.sep6.backend.models.auth.AuthenticationRequest;
import com.sep6.backend.models.auth.AuthenticationResponse;
import com.sep6.backend.models.auth.RegisterRequest;
import com.sep6.backend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest
{
    @Mock
    private AuthenticationService authenticationService;

    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationController = new AuthenticationController(authenticationService);
    }

    @Test
    void testRegister_ValidRequest_ReturnsOkResponse() {
        // Arrange
        RegisterRequest registerRequest = mock(RegisterRequest.class);
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token", "refreshToken");
        when(authenticationService.register(registerRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<AuthenticationResponse> response = authenticationController.register(registerRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(authenticationService, times(1)).register(registerRequest);
    }

    @Test
    void testRegister_InvalidRequest_ThrowsBadRequestException() {
        // Arrange
        RegisterRequest registerRequest = mock(RegisterRequest.class);
        when(authenticationService.register(registerRequest)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> authenticationController.register(registerRequest));
    }

    @Test
    void testRegister_ExceptionInService_ThrowsInternalServerErrorException() {
        // Arrange
        RegisterRequest registerRequest = mock(RegisterRequest.class);
        when(authenticationService.register(registerRequest)).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> authenticationController.register(registerRequest));
    }

    @Test
    void testAuthenticate_ValidRequest_ReturnsOkResponse() {
        // Arrange
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("email", "password");
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token", "refreshToken");
        when(authenticationService.authenticate(authenticationRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<AuthenticationResponse> response = authenticationController.authenticate(authenticationRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(authenticationService, times(1)).authenticate(authenticationRequest);
    }

    @Test
    void testAuthenticate_InvalidRequest_ThrowsBadRequestException() {
        // Arrange
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("email", "");
        when(authenticationService.authenticate(authenticationRequest)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> authenticationController.authenticate(authenticationRequest));
    }

    @Test
    void testAuthenticate_ExceptionInService_ThrowsInternalServerErrorException() {
        // Arrange
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");
        when(authenticationService.authenticate(request)).thenThrow(new RuntimeException("Internal server error"));

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> authenticationController.authenticate(request));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("Something went wrong, please try again later", exception.getReason());
        verify(authenticationService, times(1)).authenticate(request);
    }

    @Test
    void testRefreshToken_ValidRequest_SuccessfullyRefreshesToken() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        doNothing().when(authenticationService).refreshToken(request, response);

        // Act & Assert
        assertDoesNotThrow(() -> authenticationController.refreshToken(request, response));
        verify(authenticationService, times(1)).refreshToken(request, response);
    }
}