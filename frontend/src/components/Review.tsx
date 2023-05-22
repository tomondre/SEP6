import React, { FunctionComponent } from "react";
import { IReview } from "../types";
import { makeStyles } from "tss-react/mui";

import { Typography } from "@mui/material";
import { Colors } from "../constants/Colors";
import Rating from "./Rating";

interface Props {
  review: IReview;
}

const Review: FunctionComponent<Props> = ({ review }) => {
  const { classes } = useStyles();
  const { id, date, rating, comment, user } = review;

  return (
    <div className={classes.reviewContainer}>
      <div className={classes.titleAndRatingContainer}>
        <span>
          <Typography variant="h5">{user}</Typography>
          <Typography variant="p">{date}</Typography>
        </span>
        <Rating rating={rating} />
      </div>
      <Typography variant="h5r" className={classes.description}>
        {comment}
      </Typography>
    </div>
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

export default Review;
