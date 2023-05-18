import React from "react";
import { useEffect, useState } from "react";
import { makeStyles } from "tss-react/mui";
import MovieCard from "../Components/MovieCard";
import { Grid, IconButton, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import actorService from "../Services/actor-service";
import StarIcon from "@mui/icons-material/Star";
import { Colors } from "../Constants/Colors";

interface Actor {
  id: number;
  name: string;
  type: string;
  dateOfBirth: string;
  placeOfBirth: string;
  gender: string;
  biography: string;
  deathDate: string;
  profileImg: string;
}

const ActorPage = () => {
  const { classes } = useStyles();
  const navigate = useNavigate();
  const [actor, setActor] = useState<Actor>();

  const logo = require("../images/shrek.png");

  useEffect(() => {
    const fetchActor = async () => {
      try {
        const actor = await actorService.getSpecificActor(116);
        setActor(actor);
      } catch (error) {
        console.error("Error fetching movies:", error);
      }
    };

    fetchActor();
  });
  console.log(actor)

  const mockedMovies = [
    { id: 1, poster: logo, title: "Shrek" },
    { id: 2, poster: logo, title: "Shrek" },
    { id: 3, poster: logo, title: "Shrek" },
    { id: 4, poster: logo, title: "Shrek" },
    { id: 5, poster: logo, title: "Shrek" },
    { id: 6, poster: logo, title: "Shrek" },
  ];

  return (
    <>
      <Grid className={classes.container}>
        <Grid>
          <img src={logo} className={classes.image} alt="actor" />
        </Grid>

        <Grid className={classes.actorDetails}>
          <Grid className={classes.ratingGroup}>
            <StarIcon className={classes.star} />
            <Grid className={classes.rating}>Rating</Grid>
            <Grid className={classes.ratingGoal}>/ 10</Grid>
          </Grid>

          <Grid className={classes.actorName}>
            <Typography variant="h1">{actor?.name}</Typography>
          </Grid>

          <Grid className={classes.specifications}>
            <Grid className={classes.type}>
              <Typography variant="h6">Type</Typography>
            </Grid>
            <Grid className={classes.dateOfBirth}>
              <Typography variant="h6">DateOfBirth</Typography>
            </Grid>
            <Grid className={classes.deathDate}>
              <Typography variant="h6">DeathDate(if any)</Typography>
            </Grid>
          </Grid>

          <Grid>
            <Typography variant="h6">Biography</Typography>
          </Grid>
        </Grid>
      </Grid>

      <Grid item container>
        {mockedMovies.map((movie) => (
          <Grid item lg={3}>
            <MovieCard poster={movie.poster} title={movie.title} />
          </Grid>
        ))}
      </Grid>
    </>
  );
};

const useStyles = makeStyles()(() => ({
  container: {
    display: "flex",
    justifyContent: "space-around",
  },
  image: {
    height: "46rem",
  },
  ratingGroup: {
    display: "flex",
    alignItems: "center",
    height: "10%",
  },
  star: {
    color: Colors.yellow,
    fontSize: "2rem",
  },
  rating: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  ratingGoal: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
  },
  actorName: {
    width: "100%",
    height: "5rem",
    textAlign: "start",
    marginTop: "3rem",
    //border:'0.5rem solid red'
  },
  actorDetails: {
    width: "70%",
    padding: "2rem",
  },
  specifications: {
    display: "flex",
    alignItems: "start",
    height: "10%",
    //border:'0.5rem solid red'
  },
  type: {
    color: Colors.yellow,
    fontSize: "2rem",
  },
  dateOfBirth: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
  deathDate: {
    color: Colors.yellow,
    fontWeight: "800",
    fontSize: "2rem",
    alignContent: "center",
    marginLeft: "1rem",
  },
}));

export default ActorPage;
