import React, { FunctionComponent, useState } from "react";
import { IReview } from "../utils/types";
import { makeStyles } from "tss-react/mui";
import DeleteIcon from '@mui/icons-material/Delete';
import { Button, IconButton, Typography } from "@mui/material";
import { Colors } from "../constants/Colors";
import Rating from "./Rating";
import { getUserId } from "../services/user-service";
import MovieService from "../services/movies";


interface Props {
  review: IReview;
  fromProfile: boolean;
}

const Review: FunctionComponent<Props> = ({ review, fromProfile }) => {
  const { classes } = useStyles();
  const { id, createdOn, rating, comment, user, accountId, movieId } = review;
  const userId = getUserId() || 0;
  const [showMore, setShowMore] = useState(false);
  const toggleViewMore = () => setShowMore(currentValue => !currentValue);

  const isSeeMoreButtonVisible = comment.length > 100;

  const handleClick = async()=> {
    try{
       await MovieService.deleteReview(movieId,id);
       window.location.reload();
  }
  catch(error){
      console.log(error);
  }
  }

  return (
    <div className={classes.reviewContainer}>
      <div className={classes.titleAndRatingContainer}>
        <span>
          <Typography variant="h5">{fromProfile ? review.movieTitle : review.accountUsername}</Typography>
          <Typography variant="p">{createdOn.substring(0, 10)}</Typography>
        </span>
        <Rating rating={rating} />
      </div>
      <div className={classes.delete}>
      <div 
        className={`${classes.description} ${showMore ? classes.seeMoreDescription : ''}`}
      >
        <Typography variant="h5r">
          {comment}
        </Typography>
      </div>
      {  !!userId && !!(accountId==userId)  &&
      <IconButton aria-label="delete" onClick={handleClick}>
          <DeleteIcon className={classes.deleteIcon} />
        </IconButton>
        }
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
  deleteIcon:{
    color:Colors.white80
  },
  delete:{
    display:'flex'
  }
}));

export default Review;
