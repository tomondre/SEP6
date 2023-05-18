import { Button, Grid, Pagination, Typography } from '@mui/material';
import React, { useState } from 'react';
import { makeStyles } from 'tss-react/mui';
import CarouselComponent from '../Components/Carousel';
import MovieCard from '../Components/MovieCard';
import { Colors } from '../Constants/Colors';
import MovieService from "../Services/movies";


const HomePage = () => {
  const { classes } = useStyles();
  const logo = require("../images/shrek.png");
  const [currentPage, setCurrentPage] = useState(1);

  const mockedMovies = [
    { id: 1, poster: logo, title: 'Shrek' },
    { id: 2, poster: logo, title: 'Shrek' },
    { id: 3, poster: logo, title: 'Shrek' },
    { id: 4, poster: logo, title: 'Shrek' },
    { id: 5, poster: logo, title: 'Shrek' },
    { id: 6, poster: logo, title: 'Shrek' },
  ]

  const handlePageChange = (page: number) => {
    console.log(page)
    // setCurrentPage(page);
  };

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
           mockedMovies.map((movie, index) => (
            <Grid key={index} item lg={3}>
              <MovieCard poster={movie.poster} title={movie.title} />
            </Grid>
          ))
        }
      <Grid item className={classes.paginationContainer} lg={12}>
        <Pagination
          className={classes.pagination}
          count={21}
          page={currentPage}
          onChange={(event, page) => handlePageChange(page)}
        />
        </Grid>
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
  paginationContainer:{
    display:'grid',
    justifyContent: 'center',
  },
  pagination:{
    color: Colors.lightCyan
  }
}));


export default HomePage;
