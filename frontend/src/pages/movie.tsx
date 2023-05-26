import React from "react";
import { useEffect, useState } from "react";
import { makeStyles } from "tss-react/mui";
import { Grid, Typography, Link, Button, Dialog, DialogActions, DialogContent, DialogTitle, Rating, TextField, styled, Box } from "@mui/material";
import { useNavigate } from "react-router-dom";
import StarIcon from "@mui/icons-material/Star";
import { Colors } from "../constants/Colors";
import { useIdFromUrl } from "../hooks/useIdFromUrl";
import { IMovie } from "../types";
import MovieService from "../services/movies";
import PeopleCard from "../components/PeopleCard";
import { getUserId } from "../services/user-service";
import FavoriteButton from "../components/FavoriteButton";
import profileService from "../services/account-service";
import { IReview } from "../types";
import { useForm } from "react-hook-form";
import Reviews from "../components/Reviews";
import { async } from "q";

const MoviePage = () => {
  const { classes } = useStyles();
  const navigate = useNavigate();
  const [movie, setMovie] = useState<IMovie>();
  const id = useIdFromUrl();
  const userId = getUserId() || 0;
  const [favMovie, setFavMovie] = useState<boolean>(false);
  const baseUrl = "https://image.tmdb.org/t/p/original";
  const [movieReviews, setMovieReviews] = useState<IReview[]>([]);
  const [open, setOpen] = React.useState(false);

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
  const onSubmit = handleSubmit((data) => handleData(data));

  const handleData = async (data: IReview) => {
    try {
      movie &&
        (await MovieService.addReview(
          movie.id,
          data.rating,
          data.comment,
          data.date,
          userId,
          movie.title
        ).then(
          () => {},
          (error) => {
            console.log(error);
          }
        ));
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    const fetchMovie = async () => {
      try {
        if (id !== -1) {
          const movie = await MovieService.getMovieById(id);
          setMovie(movie);
          const response = await profileService.getFavoriteMovies();

          for (let index = 0; index < response.length; index++) {
            const id = response[index].id;
            if (id === movie.id) {
              setFavMovie(true);
            }
          }
        const reviews = await MovieService.getReviewsByMovieId(movie.id);
        setMovieReviews(reviews)

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
            <StarIcon className={classes.star} />
            <Grid className={classes.rating}>{movie.rating}</Grid>
            <Grid className={classes.ratingGoal}>/ 10</Grid>
          </Grid>

          <Grid className={classes.moviecontainer}>
            <Grid className={classes.movieTitle}>
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
                  <Grid item lg={1} key={index}>
                    {genre.name}
                  </Grid>
                ))}
            </Grid>
          )}
          <Grid className={classes.specifications}>
            <Grid className={classes.status}>
              <Typography variant="h6">{movie.status}</Typography>
            </Grid>
            {movie.releaseDate && (
              <Grid className={classes.releaseDate}>
                <Typography variant="h6">
                  Release date: {movie.releaseDate.substring(0, 10)}
                </Typography>
              </Grid>
            )}
            <Grid className={classes.runtime}>
              <Typography variant="h6"> {movie.runtime} min.</Typography>
            </Grid>
            <Grid className={classes.language}>
              <Typography variant="h6">Language: {movie.language}</Typography>
            </Grid>
            <Grid className={classes.budget}>
              <Typography variant="h6">Budget: {movie.budget} $ </Typography>
            </Grid>
            <Grid className={classes.boxOffice}>
              <Typography variant="h6">
                Box office: {movie.boxOffice} ${" "}
              </Typography>
            </Grid>
          </Grid>

          <Grid className={classes.description}>
            <Typography variant="p">{movie.description}</Typography>
          </Grid>
          <Grid>
            <Button
              className={classes.button}
              variant="contained"
              onClick={handleClickOpen}
            >
              Add review
            </Button>
            <Dialog open={open} onClose={handleClose}>
              <DialogTitle className={classes.dialogTitle}>Review</DialogTitle>
              <form onSubmit={onSubmit}>
                <DialogContent>
                  <Box>
                    <Typography component="legend">Rating</Typography>
                    <Rating name="customized-10" defaultValue={2} max={10} />
                  </Box>
                  
                  <TextField
                    autoFocus
                    margin="dense"
                    id="name"
                    label="Rating"
                    type="text"
                    fullWidth
                    variant="standard"
                    {...register("rating")}
                  />

                  <TextField
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
                    className={classes.button}
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

      <Grid className={classes.starsLabel}>
        <Typography variant="h4">Movie stars:</Typography>
      </Grid>
      <Grid item container className={classes.personContainer}>
        {movie.people &&
          movie.people.map((person, index) => (
            <Grid item lg={2} key={index}>
              <PeopleCard
                id={person.id}
                profileImg={person.profileImg}
                name={person.name}
                type={person.type}
              />
            </Grid>
          ))}
      </Grid>


      <Grid className={classes.starsLabel}>
        <Typography variant="h4">Reviews:</Typography>
        <Reviews reviews={movieReviews} />
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
    height: "10%",
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
    height: "5rem",
    textAlign: "start",
    marginTop: "3rem",
  },
  movieDetails: {
    width: "70%",
    padding: "2rem",
  },
  specifications: {
    display: "flex",
    alignItems: "start",
    height: "10%",
  },
  status: {
    fontSize: "2rem",
  },
  releaseDate: {
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  runtime: {
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  language: {
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  genres: {
    display: "flex",
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "1rem",
    textAlign: "start",
    marginBottom: "1rem",
  },
  description: {
    textAlign: "justify",
    fontWeight: "300",
  },
  starsLabel: {
    margin: "4rem 4rem 4rem 0rem",
  },
  personContainer: {
    justifyContent: "space-around",
  },
  button: {
    fontSize: "2.188rem",
    width: "22rem",
    height: "4rem",
    marginTop: "6rem",
  },
  favorite: {
    color: Colors.red1,
    height: "3rem",
    width: "3rem",
  },
  moviecontainer: {
    display: "flex",
    alignItems: "center",
  },
  boxOffice: {
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  budget: {
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  dialogTitle: {
    color: Colors.black75,
    fontSize: "2rem",
  },
}));

export default MoviePage;