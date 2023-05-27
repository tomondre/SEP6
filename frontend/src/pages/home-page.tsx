import { Button, FormControl, Grid, InputLabel, MenuItem, Pagination, Select, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { makeStyles } from 'tss-react/mui';
import CarouselComponent from '../components/Carousel';
import MovieCard from '../components/MovieCard';
import { Colors } from '../constants/Colors';
import { SelectChangeEvent } from '@mui/material';
import MovieService from "../services/movies";
import GenreFilter from '../components/GenreFilter';
import { IMovie } from "../utils/types";
import { getUserId } from '../services/user-service';
import { Link as RouterLink } from 'react-router-dom';

const HomePage = () => {
  const { classes } = useStyles();
  const [currentPage, setCurrentPage] = useState(1);
  const [movies, setMovies] = useState<IMovie[]>([]);
  const [selectedGenre, setSelectedGenre] = useState<number | "">("");
  const userId= getUserId();


  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const movies = await MovieService.getMovies(currentPage, selectedGenre);
        setMovies(movies);
      } catch (error) {
        console.error('Error fetching movies:', error);
      }
    };

    fetchMovies();
  }, [currentPage, selectedGenre]);

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  const handleGenreChange = (event: SelectChangeEvent<number>) => {
    const selectedGenreValue = event.target.value;
    setSelectedGenre(selectedGenreValue as number);
  };
  
  
  

  return (
    <Grid container>

      <Grid item lg={6} display='grid' justifyContent="flex-start">
        <Typography variant="h1" className={classes.heroText}>DISCOVER THE UNIVERSE OF MOVIES</Typography>
       {!userId && 
       <RouterLink className={classes.button} to="/sign-up">
        <Button variant="contained">
            Get Started
          </Button>
      </RouterLink>
      }
      </Grid>
      <Grid item lg={6}>
        <CarouselComponent movies={movies} />
      </Grid>

      <Grid item container>
        <Grid item lg={12} className={classes.genreContainer}>
          <GenreFilter selectedGenre={selectedGenre} onChange={handleGenreChange} />
          </Grid>

        {
           movies.map((movie: IMovie, index: number) => (
            <Grid key={index} item lg={3}>
              <MovieCard poster={movie.posterUrl} title={movie.title} id={movie.id}/>
            </Grid>
          ))
        }

      {selectedGenre === "" &&
      <Grid item className={classes.paginationContainer} lg={12}>
        <Pagination
          className={classes.pagination}
          count={21}
          page={currentPage}
          onChange={(event, page) => handlePageChange(page)}
        />
        </Grid>}
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
  },
  genreContainer:{
    display:'grid',
    justifyContent: 'end',
    padding: '1rem',
  },
}));


export default HomePage;
