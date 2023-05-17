import { Button, Grid, Typography } from '@mui/material';
import React from 'react';
import { makeStyles } from 'tss-react/mui';
import CarouselComponent from '../Components/Carousel';
import MovieCard from '../Components/MovieCard';

const HomePage = () => {
  const { classes } = useStyles();
  const logo = require("../images/shrek.png");

  const mockedMovies = [
    { id: 1, poster: logo, title: 'Shrek' },
    { id: 2, poster: logo, title: 'Shrek' },
    { id: 3, poster: logo, title: 'Shrek' },
    { id: 4, poster: logo, title: 'Shrek' },
    { id: 5, poster: logo, title: 'Shrek' },
    { id: 6, poster: logo, title: 'Shrek' },
  ]

  return (
    <Grid container>

      <Grid item lg={6} display='grid' justifyContent="flex-start">
        <Typography variant="h1" className={classes.heroText}>DISCOVER THE UNIVERSE OF MOVIES</Typography>
        <Button className={classes.button} variant="contained">
          Get Started
        </Button>
      </Grid>
      <Grid item lg={6}>
        <CarouselComponent />
      </Grid>

      <Grid item container>
        {
           mockedMovies.map((movie) => (
            <Grid item lg={3}>
              <MovieCard poster={movie.poster} title={movie.title} />
            </Grid>
          ))
        }
      </Grid>
    </Grid>
  );
};

const useStyles = makeStyles()(() => ({
  heroText: {
    lineHeight: '8.5rem',
    textAlign: 'start',
    maxWidth: '35rem',
  },
  intro:{
  },
  button:{
    width: '23.625rem',
  },
}));


export default HomePage;
