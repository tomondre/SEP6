import React, { FunctionComponent } from "react";

import { makeStyles } from "tss-react/mui";
import StarIcon from "@mui/icons-material/Star";
import { Colors } from "../constants/Colors";
import { Typography } from "@mui/material";

interface Props {
  rating: number;
}

const Rating: FunctionComponent<Props> = ({ rating }) => {
  const { classes } = useStyles();

  return (
    <span className={classes.ratingContainer}>
      <StarIcon className={classes.star} />
      <Typography variant="h3" color={Colors.yellow}>
        {rating}
      </Typography>
      <Typography variant="h5r" color={Colors.yellow}>
        /10
      </Typography>
    </span>
  );
};

const useStyles = makeStyles()(() => ({
  reviewContainer: {
    backgroundColor: "rgba(0, 0, 0, 0.75)",
    color: "white",
    padding: "1rem 2rem",
    textAlign: "left",
    minWidth: "512px",
    aspectRatio: "3/1",
    borderRadius: "0.5rem",
  },
  description: {
    fontWeight: "normal",
  },
  titleAndRatingContainer: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
  },
  star: {
    fontSize: "4rem",
    color: Colors.yellow,
  },
  ratingContainer: {
    display: "flex",
    alignItems: "center",
    gap: "0.25rem",
  },
}));

export default Rating;
