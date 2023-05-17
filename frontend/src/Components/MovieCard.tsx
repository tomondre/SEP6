import { Typography } from "@mui/material";
import React from "react";
import { makeStyles } from "tss-react/mui";

type MovieCardProps = {
  poster: string;
  title: string;
};

const MovieCard: React.FC<MovieCardProps> = ({ poster, title }) => {
  const { classes } = useStyles();

  return (
    <div>
      <img src={poster} className={classes.image} alt={title} />
      <Typography variant="h4" className={classes.movieTitle}>
        {title}
      </Typography>
    </div>
  );
};

const useStyles = makeStyles()(() => ({
    image:{
        marginTop: '3rem',
    },
    movieTitle:{
        padding: '1rem 0',
    }
}));
  

export default MovieCard;
