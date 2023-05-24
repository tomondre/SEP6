package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.projections.AccountProjection;
import com.sep6.backend.repository.AccountsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AccountsServiceImplTest
{
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccountsRepository repository;

    private AccountsService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new AccountsServiceImpl(passwordEncoder, repository);
    }

    @Test
    public void testAddMovieToAccountFavourites() {
        // Arrange
        FavouriteRequest request = new FavouriteRequest(1, 1);

        // Act
        FavouriteRequest result = service.addMovieToAccountFavourites(request);

        // Assert
        assertEquals(request, result);
        verify(repository, times(1)).addMovieToAccountFavourites(request.getAccountId(), request.getMovieId());
    }

    @Test
    public void testDeleteAccountFavourite() {
        // Arrange
        int accountId = 1;
        int movieId = 1;

        // Act
        service.deleteAccountFavourite(accountId, movieId);

        // Assert
        verify(repository, times(1)).deleteAccountFavourite(accountId, movieId);
    }

    @Test
    public void testGetAccountReviews() {
        // Arrange
        int accountId = 1;
        List<Review> expectedReviews = List.of(new Review(), new Review());
        when(repository.getAccountReviews(accountId)).thenReturn(expectedReviews);

        // Act
        List<Review> result = service.getAccountReviews(accountId);

        // Assert
        assertEquals(expectedReviews, result);
        verify(repository, times(1)).getAccountReviews(accountId);
    }

    @Test
    public void testGetAccountById() {
        // Arrange
        int accountId = 1;
        AccountProjection expectedAccount = mock(AccountProjection.class);
        when(repository.getAccountProjectionById(accountId)).thenReturn(Optional.of(expectedAccount));

        // Act
        AccountProjection result = service.getAccountById(accountId);

        // Assert
        assertEquals(expectedAccount, result);
        verify(repository, times(1)).getAccountProjectionById(accountId);
    }

    @Test
    public void testEditAccount() {
        // Arrange
        int accountId = 1;
        Account account = new Account();
        Account expectedAccount = new Account();
        when(repository.editAccount(accountId, account)).thenReturn(Optional.of(expectedAccount));

        // Act
        Account result = service.editAccount(accountId, account);

        // Assert
        assertEquals(expectedAccount, result);
        verify(repository, times(1)).editAccount(accountId, account);
    }

    @Test
    public void testDeleteAccount() {
        // Arrange
        int accountId = 1;

        // Act
        service.deleteAccount(accountId);

        // Assert
        verify(repository, times(1)).deleteAccount(accountId);
    }
}