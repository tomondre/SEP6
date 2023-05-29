import React from "react";
import { useEffect, useState } from "react";
import { makeStyles } from "tss-react/mui";
import { Grid, Typography, Link, Button, Dialog, DialogActions, DialogContent, Rating, DialogTitle, TextField, Box } from "@mui/material";
import { useNavigate } from "react-router-dom";
import StarIcon from "@mui/icons-material/Star";
import { Colors } from "../constants/Colors";
import { useIdFromUrl } from "../hooks/useIdFromUrl";
import { IMovie } from "../utils/types";
import MovieService from "../services/movies";
import PeopleCard from "../components/PeopleCard";
import { getUserId } from "../services/user-service";
import FavoriteButton from "../components/FavoriteButton";
import profileService from "../services/account-service";
import { IReview } from "../utils/types";
import { useForm } from "react-hook-form";
import Reviews from "../components/Reviews";
import RatingStars from "../components/Rating";

const MoviePage = () => {
  const { classes } = useStyles();
  const navigate = useNavigate();
  const [movie, setMovie] = useState<IMovie>();
  const id = useIdFromUrl();
  const userId = getUserId();
  const [favMovie, setFavMovie] = useState<boolean>(false);
  const baseUrl = "https://image.tmdb.org/t/p/original";
  const [movieReviews, setMovieReviews] = useState<IReview[]>([]);
  const [open, setOpen] = React.useState(false);
  const [rating, setRating] = useState<number>(1);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<IReview>();

  const changeRating = (event: any, value: number | null) => {
    if (value) setRating(value);
  };

  const onSubmit = handleSubmit((data) => handleData(data));

  const handleData = async (data: IReview) => {
    try {
      movie && rating && userId &&
        (await MovieService.addReview(
          movie.id,
          rating,
          data.comment,
          data.createdOn,
          userId,
          movie.title
        ).then(
          () => {},
          (error) => {
            console.log(error);
          }
        ));
      window.location.reload();
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    const fetchMovie = async () => {
      try {
        if (id !== -1) {
          const retrievedMovie = await MovieService.getMovieById(id);
          setMovie(retrievedMovie);

          if (userId) {
            const favoriteMovies = await profileService.getFavoriteMovies();
            const foundMovieInFavoriteMovies = favoriteMovies.find(
              (movie: IMovie) => movie.id === id
            );

            if (foundMovieInFavoriteMovies) {
              setFavMovie(true);
            }
          }

          if (retrievedMovie.reviews) {
            setMovieReviews(retrievedMovie.reviews);
          }
        }
      } catch (error) {
        console.error("Error fetching movies:", error);
      }
    };

    fetchMovie();
  }, []);

  if (!movie) {
    return <div>Loading...</div>;
  }

  return (
    <Grid container>
      <Grid className={classes.topContainer}>
        <Grid>
          <img
            src={`${baseUrl}${movie.posterUrl}`}
            className={classes.poster}
            alt="movie"
          />
        </Grid>

        <Grid className={classes.movieDetails}>
          <Grid className={classes.ratingGroup}>
            {!(typeof movie.rating === undefined) && (
              <RatingStars rating={movie.rating} />
            )}
          </Grid>

          <Grid className={classes.moviecontainer}>
            <Grid item lg={10} className={classes.movieTitle}>
              <Typography variant="h2">{movie.title}</Typography>
            </Grid>
            <Grid>
              {userId && (
                <FavoriteButton movieId={movie.id} isFave={favMovie} />
              )}
            </Grid>
          </Grid>

          {movie.genres && (
            <Grid className={classes.genres}>
              {movie.genres &&
                movie.genres.map((genre, index) => (
                  <Grid item lg={2} key={index}>
                    {genre.name}
                  </Grid>
                ))}
            </Grid>
          )}
          <Grid className={classes.specifications}>
            <Grid>
              <Typography variant="p">{movie.status}</Typography>
            </Grid>
            {movie.releaseDate && (
              <Grid >
                <Typography variant="p">
                  Release date: {movie.releaseDate.substring(0, 10)}
                </Typography>
              </Grid>
            )}
            <Grid>
              <Typography variant="p"> {movie.runtime} min.</Typography>
            </Grid>
            <Grid>
              <Typography variant="p">Language: {movie.language}</Typography>
            </Grid>
            <Grid>
              <Typography variant="p">Budget: {movie.budget} $ </Typography>
            </Grid>
            <Grid>
              <Typography variant="p">
                Box office: {movie.boxOffice} ${" "}
              </Typography>
            </Grid>
          </Grid>

          <Grid className={classes.description}>
            <Typography variant="p">{movie.description}</Typography>
          </Grid>
          <Grid>
            {userId && (
              <Button
                className={classes.button}
                variant="contained"
                onClick={handleClickOpen}
              >
                Add review
              </Button>
            )}
            <Dialog open={open} onClose={handleClose}>
              <DialogTitle className={classes.dialogTitle}>Review</DialogTitle>
              <form onSubmit={onSubmit}>
                <DialogContent>
                  <Box>
                    <Rating
                      name="customized-10"
                      defaultValue={1}
                      max={10}
                      onChange={changeRating}
                    />
                  </Box>
                  <TextField
                    className={classes.commentArea}
                    autoFocus
                    margin="dense"
                    id="name"
                    label="Comment"
                    type="text"
                    fullWidth
                    variant="standard"
                    {...register("comment")}
                  />
                </DialogContent>
                <DialogActions>
                  <Button
                    className={classes.submitButton}
                    type="submit"
                    variant="contained"
                    onClick={handleClose}
                  >
                    Submit
                  </Button>
                </DialogActions>
              </form>
            </Dialog>
          </Grid>
        </Grid>
      </Grid>

      {movie.people && !!movie.people.length && (
      <Grid item container lg={12}>
        <Grid lg={12} className={classes.starsLabel}>
            <Typography variant="h5">Movie stars</Typography>
        </Grid>

        {movie.people.map((person, index) => (
            <Grid item xs={12} sm={6} md={4} lg={3} key={index}>
              <PeopleCard
                id={person.id}
                profileImg={person.profileImg}
                name={person.name}
                type={person.type}
              />
            </Grid>
          ))}
      </Grid>
        )}


      <Grid item lg={12}>
        {!!movieReviews.length && <Reviews fromProfile={false} reviews={movieReviews} />}
      </Grid>
    </Grid>
  );
};

const useStyles = makeStyles()(() => ({
  topContainer: {
    display: "flex",
    justifyContent: "space-around",
  },
  poster: {
    height: "46rem",
  },
  ratingGroup: {
    display: "flex",
    alignItems: "center",
  },
  star: {
    color: Colors.yellow,
    fontSize: "2rem",
  },
  rating: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  ratingGoal: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
  },
  movieTitle: {
    width: "100%",
    textAlign: "start",
  },
  movieDetails: {
    width: "70%",
    padding: "2rem",
  },
  specifications: {
    display: "flex",
    alignItems: "start",
    justifyContent: "space-between",
    height: "15%",
  },
  genres: {
    display: "flex",
    color: Colors.yellow,
    fontWeight: "800",
    textAlign: "start",
    marginBottom: "1rem",
  },
  description: {
    textAlign: "justify",
    fontWeight: "300",
  },
  starsLabel: {
    marginTop: "4rem",
    display: "flex",
    justifyContent: "flex-start",
  },
  button: {
    fontSize: "2.188rem",
    width: "22rem",
    height: "4rem",
    marginTop: "6rem",
  },
  submitButton: {
    fontSize: "2.188rem",
    width: "16rem",
    height: "3rem",
  },
  favorite: {
    color: Colors.red1,
    height: "3rem",
    width: "3rem",
  },
  moviecontainer: {
    display: "flex",
    alignItems: "center",
    marginTop: "3rem",
  },
  dialogTitle: {
    color: Colors.black75,
    fontSize: "2rem",
  },
  commentArea: {
    width: "35rem",
  },
}));

export default MoviePage;
