import { Button, Grid, Pagination, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { makeStyles } from 'tss-react/mui';
import CarouselComponent from '../Components/Carousel';
import MovieCard from '../Components/MovieCard';
import { Colors } from '../Constants/Colors';
import MovieService from "../Services/movies";

interface Movie {
  id: number;
  genres: { id: number; name: string }[];
  posterUrl: string;
  title: string;
}

const HomePage = () => {
  const { classes } = useStyles();
  const logo = require("../images/shrek.png");
  const [currentPage, setCurrentPage] = useState(1);
  const [movies, setMovies] = useState<Movie[]>([]);

  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const movies = await MovieService.filterMovies(currentPage);
        setMovies(movies);
      } catch (error) {
        console.error('Error fetching movies:', error);
      }
    };

    fetchMovies();
  }, [currentPage]);


  const mockedMovies = [
    { id: 1, poster: logo, title: 'Shrek' },
    { id: 2, poster: logo, title: 'Shrek' },
    { id: 3, poster: logo, title: 'Shrek' },
    { id: 4, poster: logo, title: 'Shrek' },
    { id: 5, poster: logo, title: 'Shrek' },
    { id: 6, poster: logo, title: 'Shrek' },
  ]

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
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
           movies.map((movie: Movie, index: number) => (
            <Grid key={index} item lg={3}>
              <MovieCard poster={movie.posterUrl} title={movie.title} />
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
  button:{
    width: '23.625rem',
  },
  paginationContainer:{
    display:'grid',
    justifyContent: 'center',
  },
  pagination:{
    "& .MuiPaginationItem-root": {
      color: Colors.lightCyan
    },
  }
}));


export default HomePage;
