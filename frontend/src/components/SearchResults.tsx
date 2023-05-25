import React, { FunctionComponent } from "react";
import { IMovie } from "../types";
import { makeStyles } from "tss-react/mui";
import { Colors } from "../constants/Colors";
import Typography from "@mui/material/Typography";

import { movieSearchLimit } from "../constants/GeneralConstants";
import {Link as RouterLink } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import { Link } from "react-router-dom";


interface Props {
  movies?: IMovie[];
}

const SearchResults: FunctionComponent<Props> = ({ movies }) => {
  const { classes } = useStyles();

  const navigateToMovie = (movieId: number) => {
    const movieUrl = `/movies?id=${movieId}`;
    window.location.href = movieUrl;
  };

  if (!movies) return null;

  if (!movies.length)
    return (
      <div className={classes.resultContainer}>
        <Typography variant="p" color={Colors.black75}>
          No results found
        </Typography>
      </div>
    );

  return (
    <div className={classes.resultContainer}>
      <ul className={classes.ulStyling}>
        {movies.slice(0, movieSearchLimit).map((movie, index) => {
          const shouldHaveSeparator = index !== 0;

          return (
            <li
                key={movie.id}
                className={`${classes.result} ${shouldHaveSeparator &&
                  classes.separation}`}
                onClick={() => navigateToMovie(movie.id)}
              >
                <img
                  src={`https://image.tmdb.org/t/p/w200${movie.posterUrl}`}
                  className={classes.moviePoster}
                />
                <Typography variant="p" color={Colors.black75}>
                  {movie.title}
                </Typography>
              </li>
          );
        })}
      </ul>
    </div>
  );
};

const useStyles = makeStyles()(() => ({
  resultContainer: {
    zIndex: 2,
    position: "absolute",
    background: Colors.white,
    width: "32rem",
    marginTop: "-8px",
    borderRadius: "0.5rem",
  },
  ulStyling: {
    padding: 0,
    margin: 0,
  },
  result: {
    listStyleType: "none",
    display: "flex",
    alignItems: "center",
    width: "100%",
    cursor: "pointer",
    paddingBlock: "1rem",
    gap: "1.5rem",
    "&:hover": {
      backgroundColor: Colors.gray,
    },
  },
  moviePoster: {
    width: "3rem",
    height: "4.5rem",
    marginLeft: "1.5rem",
  },
  separation: {
    borderTop: `1px solid ${Colors.black50}`,
  },
}));

export default SearchResults;
