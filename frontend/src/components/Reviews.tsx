import React, { FunctionComponent } from "react";
import { IReview } from "../utils/types";
import { makeStyles } from "tss-react/mui";

import { Typography } from "@mui/material";
import Review from "./Review";
import { useHorizontalScroll } from "../hooks/useHorizontalScroll";

interface Props {
  reviews: IReview[];
  fromProfile: boolean;
}

const Reviews: FunctionComponent<Props> = ({ reviews, fromProfile }) => {
  const { classes } = useStyles();

  const horizontalScrollRef = useHorizontalScroll();

  return (
    <section className={classes.reviewsSection}>
      <Typography variant="h5">Reviews</Typography>
      <div
        className={`${classes.reviewsContaier} ${classes.scrollbar}`}
        ref={horizontalScrollRef as any}
      >
        {reviews.map((review, index) => (
          <Review 
          key={index}
          fromProfile={fromProfile}
          review={review} />
        ))}
      </div>
    </section>
  );
};

const useStyles = makeStyles()(() => ({
  reviewsSection: {
    paddingBlock: "2rem",
    width: "100%",
    textAlign:'left'
  },
  reviewsContaier: {
    display: "flex",
    gap: "2rem",
    width: "100%",
    justifyContent: "space-between",
    paddingBlock: "2rem",
    overflowX: "scroll",
  },
  scrollbar: {
    "&::-webkit-scrollbar": {
      backgroundColor: "rgba(0,0,0, 0)",
    },
    "&::-webkit-scrollbar-thumb": {
      backgroundColor: "rgba(255, 255, 255, 0.4)",
      borderRadius: "4px",
      height: "10px",
    },
  },
}));

export default Reviews;
