import React, { FunctionComponent, useState } from "react";
import { IReview } from "../types";
import { makeStyles } from "tss-react/mui";

import { Button, Typography } from "@mui/material";
import { Colors } from "../constants/Colors";
import Rating from "./Rating";

interface Props {
  review: IReview;
}

const Review: FunctionComponent<Props> = ({ review }) => {
  const { classes } = useStyles();
  const { id, date, rating, comment, user } = review;

  const [showMore, setShowMore] = useState(false);
  const toggleViewMore = () => setShowMore(currentValue => !currentValue);

  const isSeeMoreButtonVisible = comment.length > 100;

  return (
    <div className={classes.reviewContainer}>
      <div className={classes.titleAndRatingContainer}>
        <span>
          <Typography variant="h5">{review.movieTitle}</Typography>
          <Typography variant="p">{date}</Typography>
        </span>
        <Rating rating={rating} />
      </div>
      <div 
        className={`${classes.description} ${showMore ? classes.seeMoreDescription : ''}`}
      >
        <Typography variant="h5r">
          {comment}
        </Typography>
      </div>
      {
        isSeeMoreButtonVisible && (
          <Button onClick={toggleViewMore}>
            {showMore ? 'See less' : 'See more'}
          </Button>
        )
      }
    </div>
  );
};

const useStyles = makeStyles()(() => ({
  reviewContainer: {
    backgroundColor: "rgba(0, 0, 0, 0.75)",
    color: Colors.white,
    padding: "1rem 2rem",
    textAlign: "left",
    width: "512px",
    borderRadius: "0.5rem",
    flexShrink:0,
    height: 'fit-content'
  },
  description: {
    fontWeight: "normal",
    width:'100%',
    wordBreak: 'break-all',
    overflow:'hidden',
    whiteSpace: 'nowrap',
    textOverflow: 'ellipsis',
  },
  seeMoreDescription : {
    overflow: 'visible',
    whiteSpace: 'normal',
    height: 'auto',
  },
  automaticHeight :{
    height: 'auto'
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
