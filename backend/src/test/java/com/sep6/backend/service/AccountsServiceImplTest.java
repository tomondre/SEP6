package com.sep6.backend.service;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.FavouriteRequest;
import com.sep6.backend.models.Movie;
import com.sep6.backend.models.Review;
import com.sep6.backend.projections.AccountProjection;
import com.sep6.backend.projections.FavouriteMovieProjection;
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
import java.util.stream.Collectors;

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
    public void testGetAccountFavourites() {
        // Arrange
        int accountId = 1;
        Movie movie1 = Movie.builder()
                .id(1)
                .title("Movie 1")
                .description("Description 1")
                .posterUrl("Poster")
                .runtime(120)
                .language("English")
                .budget(1000000)
                .boxOffice(1000000)
                .status("Released")
                .releaseDate(Date.valueOf("2023-12-12"))
                .rating(8.0).build();
        Movie movie2 = Movie.builder()
                .id(2)
                .title("Movie 2")
                .description("Description 2")
                .posterUrl("poster2.jpg")
                .runtime(90)
                .language("Spanish")
                .budget(2000000)
                .boxOffice(2000000)
                .status("Released")
                .releaseDate(Date.valueOf("2023-12-12"))
                .rating(7.5).build();
        Set<FavouriteMovieProjection> expectedFavourites = Set.of(
                createFavouriteMovieProjection(movie1),
                createFavouriteMovieProjection(movie2)
        );
        when(repository.getAccountFavouritesById(accountId)).thenReturn(expectedFavourites);

        // Act
        Set<FavouriteMovieProjection> result = service.getAccountFavourites(accountId);

        // Assert
        assertEquals(expectedFavourites, result);
        verify(repository, times(1)).getAccountFavouritesById(accountId);
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

    private FavouriteMovieProjection createFavouriteMovieProjection(Movie movie) {
        return new FavouriteMovieProjection() {
            @Override
            public int getId() {
                return movie.getId();
            }

            @Override
            public String getTitle() {
                return movie.getTitle();
            }

            @Override
            public String getPosterUrl() {
                return movie.getPosterUrl();
            }
        };
    }
}