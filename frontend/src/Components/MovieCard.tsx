import { Typography } from "@mui/material";
import React from "react";
import { makeStyles } from "tss-react/mui";
import {Link as RouterLink } from "react-router-dom";

type MovieCardProps = {
  poster: string;
  title: string;
  id: number;
};

const MovieCard: React.FC<MovieCardProps> = ({ poster, title, id }) => {
  const { classes } = useStyles();

  return (
    <div>
      <RouterLink to={`/movies?id=${id}`}>
        <img src={`https://image.tmdb.org/t/p/w200${poster}`} className={classes.image} alt={title} />
        <Typography variant="h6" className={classes.movieTitle}>
          {title}
        </Typography>
      </RouterLink>

    </div>
  );
};

const useStyles = makeStyles()(() => ({
    image:{
        marginTop: '3rem',
        width :'16rem',
        height: '24rem'
    },
    movieTitle:{
        padding: '1rem 0',
    }
}));
  

export default MovieCard;
