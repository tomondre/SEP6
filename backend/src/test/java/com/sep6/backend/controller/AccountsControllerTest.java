package com.sep6.backend.controller;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.projections.AccountProjection;
import com.sep6.backend.service.AccountsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AccountsControllerTest
{
    @Mock
    private AccountsService accountsService;

    private AccountsController accountsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountsController = new AccountsController(accountsService);
    }

    @Test
    void testEditAccount() {
        // Arrange
        int id = 1;
        Account account = new Account();
        when(accountsService.editAccount(id, account)).thenReturn(account);

        // Act
        ResponseEntity<Account> response = accountsController.editAccount(id, account);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(account, response.getBody());
        verify(accountsService, times(1)).editAccount(id, account);
    }

    @Test
    void testEditAccount_NonexistentAccount() {
        // Arrange
        int id = 1;
        Account account = new Account();
        when(accountsService.editAccount(id, account)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.editAccount(id, account));
        verify(accountsService, times(1)).editAccount(id, account);
    }

    @Test
    void testEditAccount_InternalServerError() {
        // Arrange
        int id = 1;
        Account account = new Account();
        when(accountsService.editAccount(id, account)).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.editAccount(id, account));
        verify(accountsService, times(1)).editAccount(id, account);
    }

    @Test
    void testDeleteAccount() {
        // Arrange
        int id = 1;

        // Act
        ResponseEntity<String> response = accountsController.deleteAccount(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account deleted successfully", response.getBody());
        verify(accountsService, times(1)).deleteAccount(id);
    }

    @Test
    void testDeleteAccount_NonexistentAccount() {
        // Arrange
        int id = 1;
        doThrow(IllegalArgumentException.class).when(accountsService).deleteAccount(id);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.deleteAccount(id));
        verify(accountsService, times(1)).deleteAccount(id);
    }

    @Test
    void testDeleteAccount_InternalServerError() {
        // Arrange
        int id = 1;
        doThrow(RuntimeException.class).when(accountsService).deleteAccount(id);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.deleteAccount(id));
        verify(accountsService, times(1)).deleteAccount(id);
    }

    @Test
    void testGetAccountById() {
        // Arrange
        int id = 1;
        AccountProjection accountProjection = mock(AccountProjection.class);
        when(accountsService.getAccountById(id)).thenReturn(accountProjection);

        // Act
        ResponseEntity<AccountProjection> response = accountsController.getAccountById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountProjection, response.getBody());
        verify(accountsService, times(1)).getAccountById(id);
    }

    @Test
    void testGetAccountById_NonexistentAccount() {
        // Arrange
        int id = 1;
        when(accountsService.getAccountById(id)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.getAccountById(id));
        verify(accountsService, times(1)).getAccountById(id);
    }

    @Test
    void testGetAccountById_InternalServerError() {
        // Arrange
        int id = 1;
        when(accountsService.getAccountById(id)).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.getAccountById(id));
        verify(accountsService, times(1)).getAccountById(id);
    }

    @Test
    void testAddMovieToAccountFavourites() {
        // Arrange
        int id = 1;
        FavouriteRequest request = new FavouriteRequest(id, id);
        when(accountsService.addMovieToAccountFavourites(request)).thenReturn(request);

        // Act
        ResponseEntity<FavouriteRequest> response = accountsController.addMovieToAccountFavourites(id, request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(request, response.getBody());
        verify(accountsService, times(1)).addMovieToAccountFavourites(request);
    }

    @Test
    void testAddMovieToAccountFavourites_NonexistentAccount() {
        // Arrange
        int id = 1;
        FavouriteRequest request = new FavouriteRequest(id, id);
        when(accountsService.addMovieToAccountFavourites(request)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.addMovieToAccountFavourites(id, request));
        verify(accountsService, times(1)).addMovieToAccountFavourites(request);
    }

    @Test
    void testAddMovieToAccountFavourites_InternalServerError() {
        // Arrange
        int id = 1;
        FavouriteRequest request = new FavouriteRequest(id, id);
        when(accountsService.addMovieToAccountFavourites(request)).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.addMovieToAccountFavourites(id, request));
        verify(accountsService, times(1)).addMovieToAccountFavourites(request);
    }

    @Test
    void testGetAccountFavourites_NonexistentAccount() {
        // Arrange
        int id = 1;
        when(accountsService.getAccountFavourites(id)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.getAccountFavourites(id));
        verify(accountsService, times(1)).getAccountFavourites(id);
    }

    @Test
    void testGetAccountFavourites_InternalServerError() {
        // Arrange
        int id = 1;
        when(accountsService.getAccountFavourites(id)).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.getAccountFavourites(id));
        verify(accountsService, times(1)).getAccountFavourites(id);
    }

    @Test
    void testDeleteAccountFavourite() {
        // Arrange
        int accountId = 1;
        int movieId = 123;

        // Act
        ResponseEntity<String> response = accountsController.deleteAccountFavourite(accountId, movieId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Favourite deleted successfully", response.getBody());
        verify(accountsService, times(1)).deleteAccountFavourite(accountId, movieId);
    }

    @Test
    void testDeleteAccountFavourite_NonexistentAccount() {
        // Arrange
        int accountId = 1;
        int movieId = 123;
        doThrow(IllegalArgumentException.class).when(accountsService).deleteAccountFavourite(accountId, movieId);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.deleteAccountFavourite(accountId, movieId));
        verify(accountsService, times(1)).deleteAccountFavourite(accountId, movieId);
    }

    @Test
    void testDeleteAccountFavourite_InternalServerError() {
        // Arrange
        int accountId = 1;
        int movieId = 123;
        doThrow(RuntimeException.class).when(accountsService).deleteAccountFavourite(accountId, movieId);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.deleteAccountFavourite(accountId, movieId));
        verify(accountsService, times(1)).deleteAccountFavourite(accountId, movieId);
    }

    @Test
    void testGetAccountReviews() {
        // Arrange
        int id = 1;
        List<Review> reviews = new ArrayList<>();
        when(accountsService.getAccountReviews(id)).thenReturn(reviews);

        // Act
        ResponseEntity<List<Review>> response = accountsController.getAccountReviews(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviews, response.getBody());
        verify(accountsService, times(1)).getAccountReviews(id);
    }

    @Test
    void testGetAccountReviews_NonexistentAccount() {
        // Arrange
        int id = 1;
        when(accountsService.getAccountReviews(id)).thenThrow(NoSuchElementException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.getAccountReviews(id));
        verify(accountsService, times(1)).getAccountReviews(id);
    }

    @Test
    void testGetAccountReviews_InternalServerError() {
        // Arrange
        int id = 1;
        when(accountsService.getAccountReviews(id)).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> accountsController.getAccountReviews(id));
        verify(accountsService, times(1)).getAccountReviews(id);
    }
}